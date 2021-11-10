import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AppToolbarButton, AppToolbarListener, DaiUiFrameService } from 'emst-ui-frame';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakInstance } from 'keycloak-js';
import { Subscription } from 'rxjs';
import { routes } from './app-routing.module';
import { ContextService } from './core/services/context.service';
//import { InfoDialogService } from './shared/services/info-dialog/info-dialog.service';
import { UserMessageService } from './shared/services/user-message.service';

const appToolbarButtons: AppToolbarButton[] = [];
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'FHI';
  keycloakInstance: KeycloakInstance;
  appToolbarButtons = appToolbarButtons;
  appRoutes = routes;

  private pageUserMessages: Subscription;

  /**
   * config AppToolbarLister - custom name (for logging only), method that is called on raised events -
   * for method implementation see below
   * any component can add itself as listener to these events -
   * BEWARE: take care that no conflicting reactions happen across different handlers to the same event
   */
  readonly listenerConfig: AppToolbarListener = {
    name: 'my-component-name: AppComponent',
    onAppToolbarEvent: eventName => this.handleGlobalAppToolbarEvents(eventName),
  };

  constructor(
    public keycloakService: KeycloakService,
    public context: ContextService,
    private snackBar: MatSnackBar,
    private userMessageService: UserMessageService,
    private daiUiFrameService: DaiUiFrameService,
    private httpClient: HttpClient,
    public contextService: ContextService
  ) {}

  ngOnInit() {
    this.keycloakInstance = this.keycloakService.getKeycloakInstance();
    this.pageUserMessages = this.context.getPageUserMessages().subscribe(msg => {
      if (msg !== null && msg) {
        this.userMessageService.process(this.snackBar, msg);
      }
    });

    this.daiUiFrameService.registerAppToolbarButtonsGlobal(appToolbarButtons);
    this.daiUiFrameService.registerListener(this.listenerConfig);
  }

  ngOnDestroy(): void {
    this.pageUserMessages.unsubscribe();
  }

  private handleGlobalAppToolbarEvents(eventName: string): void {
    switch (eventName) {
      case 'global-settings-clicked':
        // eslint-disable-next-line no-console
        console.log('handle event: "global-settings-clicked"');
        break;
      case 'global-notifications-clicked':
        // eslint-disable-next-line no-console
        console.log('handle event: "global-notifications-clicked"');
        break;
      default:
        break;
    }
  }

  actionTestServerConnection(): void {
    this.httpClient.get('/actuator/info').subscribe();
  }
}
