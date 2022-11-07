import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { TuebPipe } from 'src/app/shared/pipes/tueb.pipe';
import { MessageUtil } from 'src/app/shared/utils/message-util';
import { ErrorDto } from '../models/error-dto';
import { ResponseMessageType } from '../models/response-message-type';
import { ResponseMessages } from '../models/response-messages';
import { ContextService } from '../services/context.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  /**
   * This prefix token is written in our backend in <b>RestResponseEntityExceptionHandler</b>.
   * So don't change here without adopting there!
   */
  CUSTOM_PREFIX = 'FHI - ';

  readonly noErrorHandlingWhitelistMap = new Map<string, Array<string>>();

  constructor(private contextService: ContextService, private tuebPipe: TuebPipe) {
    this.initErrorHandlingWhitelistMap();
  }

  // whitelist requests, that do their own error handling and dont want to use generic error message - e.g. zebra printer popup
  private initErrorHandlingWhitelistMap() {
    const whitelistArrayPost = new Array<string>();
    this.noErrorHandlingWhitelistMap.set('POST', whitelistArrayPost);
    // whitelistArrayPost.push(this.zebraPrintJobsEndpoint);

    const whitelistArrayGet = new Array<string>();
    this.noErrorHandlingWhitelistMap.set('GET', whitelistArrayGet);
    // whitelistArrayGet.push(this.documentResourcesEndpoint);
  }

  public intercept(req: HttpRequest<undefined>, next: HttpHandler): Observable<HttpEvent<undefined>> {
    // if url is whitelisted for its custom error handling -> call next handler
    const urlWhitelist = this.noErrorHandlingWhitelistMap.get(req.method);
    const isCustomErrorHandling = urlWhitelist?.filter(url => req.url.startsWith(url)).length > 0;
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          this.contextService.updateConnectionWorkingState(true);
        }
      }),
      catchError(err => {
        this.updateConnectionStatus(err);

        this.processUserMessages(err);

        /*

        let myError = err;
        try {
          myError = this.convertErrorResponse(myError);
        } catch (e) {
          myError = err;
        }

        // if generic error handling
        if (!isCustomErrorHandling) {
          // create usermessages
          this.contextService.addHttpError(myError);
        }*/

        return new Promise<undefined>((resolve, reject) => {
          try {
            reject(this.convertErrorResponse(err));
          } catch (e) {
            reject(err);
          }
        });
      })
    );
  }
  processUserMessages(pErr: any) {
    if (!pErr && !pErr.error) {
      return;
    }
    const error = pErr.error;
    if (error?.exception) {
      const uiMessage = MessageUtil.createErrorMsg(error.exception);
      this.contextService.addUserMessage(uiMessage);
    }
    if (error?.messages) {
      const msgs: ResponseMessages = error;

      // new handling
      const result = msgs.messages.filter(msg => msg.typ === ResponseMessageType.ERROR);
      if (result) {
        const entry = result[0];
        if (entry.tuebKey) {
          const translation = this.tuebPipe.transform(entry.tuebKey, entry.parameter);
          this.contextService.addErrorString(translation);
        } else {
          this.contextService.addErrorString(result[0].meldung);
        }
      }
    }
  }

  private updateConnectionStatus(errorEvent: HttpErrorResponse): void {
    // if error is text message and starts with DINA
    const errorDto = this.getErrorDto(errorEvent);
    if (this.isDinaError(errorDto)) {
      this.contextService.updateConnectionWorkingState(true);
    } else {
      // connection problem
      this.contextService.updateConnectionWorkingState(false);
    }
  }

  private convertErrorResponse(errorEvent: HttpErrorResponse): HttpErrorResponse {
    const errorDto = this.getErrorDto(errorEvent);

    // if error is text message and starts with DINA
    if (this.isDinaError(errorDto)) {
      return this.createModifiedDinaError(errorEvent, errorDto);
    }

    // if error structure is ok -> go on
    if (errorDto) {
      return this.createModifiedBackendError(errorEvent, errorDto);
    }

    // else try to construct errorDto
    return this.createModifiedError(errorEvent);
  }

  private getErrorDto(errorEvent: HttpErrorResponse): ErrorDto {
    const errorDto = errorEvent?.error;
    if (this.isString(errorDto?.error) && this.isString(errorDto?.message) && errorDto?.status !== undefined && errorDto?.timestamp !== undefined) {
      return errorDto;
    }
    if (errorEvent?.error instanceof ProgressEvent) {
      const progressEvent: any = errorEvent?.error;
      return {
        error: 'ProgressEvent error',
        message: errorEvent?.message,
        status: errorEvent?.status,
        timestamp: new Date(),
        isDinaError: false,
      } as ErrorDto;
    }
    return undefined;
  }

  private isDinaError(errorDto: ErrorDto): boolean {
    if (!errorDto) {
      return false;
    }
    const result = errorDto.error?.startsWith(this.CUSTOM_PREFIX);
    // set custom dina error flag if prefix found
    errorDto.isDinaError = result;
    return result;
  }

  private createModifiedDinaError(errorEvent: HttpErrorResponse, errorDto: ErrorDto): HttpErrorResponse {
    const errmsg = errorDto?.error?.toString();
    errorDto.error = errmsg?.substring(this.CUSTOM_PREFIX.length);
    return this.createErrorResponse(errorEvent, errorDto);
  }

  private createModifiedError(errorEvent: HttpErrorResponse): HttpErrorResponse {
    // build new error as message of error.error and error.message
    if (this.isString(errorEvent?.error)) {
      const errorDto: ErrorDto = {
        error: errorEvent?.error?.toString(),
      };
      return this.createErrorResponse(errorEvent, errorDto);
    }
    // no modification done
    return errorEvent;
  }

  private createModifiedBackendError(errorEvent: HttpErrorResponse, errorDto: ErrorDto): HttpErrorResponse {
    errorDto.error = errorDto?.error + ' - ' + errorDto?.message;
    return this.createErrorResponse(errorEvent, errorDto);
  }

  private createErrorResponse(errorEvent: HttpErrorResponse, errorDto: ErrorDto): HttpErrorResponse {
    return new HttpErrorResponse({
      error: errorDto,
      headers: errorEvent?.headers,
      status: errorEvent?.status,
      statusText: errorEvent?.statusText,
      url: errorEvent?.url,
    });
  }

  private isString(stringCandidate: any): boolean {
    return typeof stringCandidate === 'string' || stringCandidate instanceof String;
  }
}
