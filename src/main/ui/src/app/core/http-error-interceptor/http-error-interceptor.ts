import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, finalize } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { MessageUtil } from 'src/app/shared/util/message-util';
import { ContextService } from './../services/context.service';
import { FhiErrorMessageHandler } from 'src/app/modules/fhi/fhi-error-message.handler';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor { // over engineered? HttpDataInterceptor better as generic entry point
  constructor(private context: ContextService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((response: HttpErrorResponse) => {
        let errorMessage: string;
        if (this.context.getCustomErrorMessageHandler()) {
          const errorHandler = this.context.getCustomErrorMessageHandler();
          const msg = errorHandler(response);
          if (msg) {
            errorMessage = msg;
          }
        } else if (typeof response.error === 'string') {
          errorMessage = response.error;
        } else if (response.error) {
          errorMessage = response.error.error;
        }  else if (response.statusText) {
          errorMessage = response.statusText;
        }
        console.error('error while request: ', response);
        if (!errorMessage) {
          errorMessage = 'Unbekannter Fehler!';
        }
        const uiMessage = MessageUtil.createErrorMsg(errorMessage);
        this.context.addUserMessage(uiMessage);
        return throwError(response);
      }),
      finalize(() => {
        this.context.setBackendRequestInProgress(false);
      })
    );
  }
}
