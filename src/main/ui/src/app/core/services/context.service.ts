import { Injectable } from '@angular/core';
import { Subject, Observable, BehaviorSubject } from 'rxjs';
import { UiMessage } from '../../shared/models/ui-message';

@Injectable({
  providedIn: 'root'
})
export class ContextService {
  private pageTitle$ = new BehaviorSubject<string>('Startseite');

  private manualPage$ = new BehaviorSubject<string>('https://wiki.dewoe.corpintra.net/wikiemst/index.php/W060.FHI');

  private isBackendRequestInProgress$ = new BehaviorSubject<boolean>(false);

  private forcePageRefresh$ = new Subject<any>();

  private pageUserMessage$ = new BehaviorSubject<UiMessage>(null);

  private locale: string = 'de-DE';

  private customErrorMessageHandler: (response: any) => string;



  constructor() {
    console.log('+++++ new ContextService');
  }

  public setCurrentPageInfo(pageTitle: string, manualPage: string): void {
    this.pageTitle$.next(pageTitle);
    this.manualPage$.next(manualPage);
  }

  public getLocale(): string {
    return this.locale;
  }

  public getPageTitle() {
    return this.pageTitle$.asObservable();
  }

  public getManualPage() {
    return this.manualPage$.asObservable();
  }

  public isBackendRequestInProgress() {
    return this.isBackendRequestInProgress$.asObservable();
  }

  public getForcePageRefresh() {
    return this.forcePageRefresh$.asObservable();
  }

  public addUserMessage(uiMessage: UiMessage) {
    this.pageUserMessage$.next(uiMessage);
  }

  public getPageUserMessages(): Observable<UiMessage> {
    return this.pageUserMessage$.asObservable();
  }

  public setBackendRequestInProgress(prog: boolean) {
    // console.log('***context*** requestInProgress change '+ prog + ' date '+new Date().getMilliseconds());
    this.isBackendRequestInProgress$.next(prog);
  }

  public setCustomErrorMessageHandler(customHandler: ((response: any) => string) ) {
    this.customErrorMessageHandler = customHandler;
  }

  public getCustomErrorMessageHandler(): ((response: any) => string) {
    return this.customErrorMessageHandler;
  }

  public forcePageRefresh() {
    this.forcePageRefresh$.next();
  }

}
