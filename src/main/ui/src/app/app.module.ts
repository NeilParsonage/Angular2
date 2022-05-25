import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
// ... other import statements ...
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { LibEmstTableModule } from 'emst-table';
import { DaiUiFrameModule } from 'emst-ui-frame';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from './app-init';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PrivilegeRouteGuard } from './core/guards/privilegeRouteGuard';
import { BlobErrorHttpInterceptor } from './core/http-error-interceptor/blob-error-http-interceptor';
import { HttpErrorInterceptor } from './core/http-error-interceptor/http-error-interceptor';
import { UserConfirmDialogComponent } from './modules/fhi/components/user-confirm-dialog/user-confirm-dialog.component';
import { FhiModule } from './modules/fhi/fhi.module';
import { ConfirmDialogComponent } from './shared/components/confirm-dialog/confirm-dialog.component';
import { ConfirmationPopupComponent } from './shared/components/confirmation-popup/confirmation-popup.component';
import { MessageCardStapleComponent } from './shared/components/message-card-staple/message-card-staple.component';
import { MaterialModule } from './shared/modules/material.module';
import { TuebService } from './shared/services/tueb.service';

@NgModule({
  declarations: [AppComponent, ConfirmDialogComponent, UserConfirmDialogComponent, ConfirmationPopupComponent, MessageCardStapleComponent],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    DaiUiFrameModule,
    FhiModule,
    FlexLayoutModule,
    FormsModule,
    HttpClientModule,
    KeycloakAngularModule,
    LibEmstTableModule,
    MaterialModule,
    ReactiveFormsModule,
    TranslateModule.forRoot(),
  ],
  providers: [
    PrivilegeRouteGuard,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService, TranslateService, TuebService],
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BlobErrorHttpInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true,
    },
  ],
  entryComponents: [MessageCardStapleComponent],
  bootstrap: [AppComponent],
})
export class AppModule {}
