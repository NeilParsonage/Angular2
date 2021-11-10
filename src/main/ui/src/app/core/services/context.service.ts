import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UiMessage } from 'src/app/shared/models/ui-message';
import { MessageUtil } from 'src/app/shared/utils/message-util';

@Injectable({
  providedIn: 'root',
})
export class ContextService {
  connectionOk = true;

  lastWorkingRequest = new Date();

  private isBackendRequestInProgress$ = new BehaviorSubject<boolean>(false);

  private pageUserMessage$ = new Subject<UiMessage>();

  updateConnectionWorkingState(isworking: boolean): void {
    this.connectionOk = isworking;
    if (isworking) {
      this.lastWorkingRequest = new Date();
    }
  }

  public isBackendRequestInProgress() {
    return this.isBackendRequestInProgress$.asObservable();
  }

  public addHttpError(error: HttpErrorResponse): void {
    const errorMessage = MessageUtil.createErrorMsgByResponse(error);
    this.addUserMessage(errorMessage);
  }

  public addErrorString(errorString: string) {
    const errorMessage = MessageUtil.createErrorMsg(errorString);
    this.addUserMessage(errorMessage);
  }

  public addInfoString(infoString: string) {
    const infoMessage = MessageUtil.createInfoMsg(infoString);
    this.addUserMessage(infoMessage);
  }

  public addUserMessage(uiMessage: UiMessage) {
    this.pageUserMessage$.next(uiMessage);
  }

  public getPageUserMessages(): Observable<UiMessage> {
    return this.pageUserMessage$.asObservable();
  }

  public setBackendRequestInProgress(prog: boolean) {
    this.isBackendRequestInProgress$.next(prog);
  }
}
