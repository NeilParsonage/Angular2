import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Privileges } from 'src/app/modules/fhi/models/privileges';
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

  private scrollPos: { top: number; left: number };

  private rendered$ = new Subject<any>();

  private forcePageRefresh$ = new Subject<any>();

  constructor(private ngZone: NgZone, protected kc: KeycloakService) {
    console.log('+++++ new ContextService');
    this.initReadyRenderer();
  }

  initReadyRenderer() {
    // https://github.com/angular/components/issues/8068
    this.ngZone.onStable.subscribe(() => {
      console.log('Finished rendering');
      this.rendered$.next(true);
    });
  }

  afterRendered(): Observable<any> {
    return this.rendered$.asObservable();
  }

  clearScrollPosition() {
    this.scrollPos = null;
  }

  storeScrollPosition() {
    const pos = this.getScrollPosition();
    this.scrollPos = { top: pos.top, left: pos.left };
  }

  restoreScrollPosition() {
    if (!this.scrollPos) {
      return;
    }
    this.setScrollPosition(this.scrollPos.left, this.scrollPos.top);
  }

  public getScrollPosition() {
    const elem = this.getScrollElement();
    return { top: elem.scrollTop, left: elem.scrollLeft };
  }

  public setScrollPosition(left: number, top: number) {
    const elem = this.getScrollElement();
    elem.scrollTop = top;
    elem.scrollLeft = left;
  }

  private getScrollElement() {
    return document.getElementById('mainScroll');
  }

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

  public getForcePageRefresh() {
    return this.forcePageRefresh$.asObservable();
  }

  public hasPrivilegeEditAuftrag() {
    return this.kc.isUserInRole(Privileges.FHI_LEITWARTE) || this.kc.isUserInRole(Privileges.FHI_ADMIN);
  }
}
