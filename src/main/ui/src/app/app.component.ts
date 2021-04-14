import { Breakpoints, MediaMatcher, BreakpointObserver } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, OnInit, OnDestroy, AfterViewChecked } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { map, first } from 'rxjs/operators';
import { ContextService } from './core/services/context.service';
import { KeycloakService } from 'keycloak-angular';
import { AboutDialogComponent } from './about-dialog/about-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserMessageService } from './shared/services/user-message.service';
import { FhiErrorMessageHandler } from './modules/fhi/fhi-error-message.handler';
import { Title } from '@angular/platform-browser';
import { AppService } from './modules/fhi/services/app.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy, AfterViewChecked {
  title = 'fhiui';
  pageTitle = '';
  manualPage = './';
  appInfo = 'FHI '
  isHandset: boolean;

  fillerContent = Array.from(
    { length: 50 },
    () =>
      `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
       labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
       laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
       voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
       cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.`
  );

  private pageTitleSub: Subscription;
  private manualPageSub: Subscription;
  private pageUserMessages: Subscription;

  private isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.HandsetPortrait) // <=> breakpoint max-width 599px
    .pipe(map(result => result.matches));

  constructor(
    public dialog: MatDialog,
    public keycloakService: KeycloakService,
    public context: ContextService,
    public appService: AppService,
    private snackBar: MatSnackBar,
    private breakpointObserver: BreakpointObserver,
    private userMessageService: UserMessageService,
    private cdRef: ChangeDetectorRef,
    private titleService: Title
  ) {
    this.isHandset$.subscribe(isaHandset => {
      this.isHandset = isaHandset;
    });
    this.pageUserMessages = this.context.getPageUserMessages().subscribe(msg => {
      if (msg !== null && msg) {
        console.debug('next pageUserMessage' + msg.message.text);
        this.userMessageService.process(this.snackBar, msg);
      }
    });
    this.context.setCustomErrorMessageHandler((function(response:string): string {
      console.debug('call FhiErrorMessageHandler')
      return FhiErrorMessageHandler.process(response);
    }));

    this.appService.getInfo()
      .pipe(first())
        .subscribe(
            data => {
              let appInfoDetail: string = this.appInfo + data.werk + " " + data.umgebung;
              this.titleService.setTitle(appInfoDetail);
              this.appInfo = appInfoDetail;
            }
      );

  }

  ngOnInit() {
    this.pageTitleSub = this.context.getPageTitle().subscribe(pageTitle => (this.pageTitle = pageTitle));
    this.manualPageSub = this.context.getManualPage().subscribe(manualPage => (this.manualPage = manualPage));
  }

  ngOnDestroy() {
    this.pageTitleSub.unsubscribe();
    this.manualPageSub.unsubscribe();
    this.pageUserMessages.unsubscribe();
  }

  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }

  showAbout() {
    this.dialog.open(AboutDialogComponent, {
      height: '480px',
      width: '624px'
    });
  }
  public setTitle(newTitle: string) {
    this.titleService.setTitle(newTitle);
  }
}
