import { BrowserModule, Title  } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { MaterialModule } from './shared/modules/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NavComponent } from './nav/nav.component';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';
import { initializer } from './app-init';
import { AboutDialogComponent } from './about-dialog/about-dialog.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpErrorInterceptor } from './core/http-error-interceptor/http-error-interceptor';
import { PrivilegeRouteGuard } from './core/auth/guards/privilegeRouteGuard';
import { MessageCardStapleComponent } from './shared/components/message-card-staple/message-card-staple.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FhiModule } from './modules/fhi/fhi.module';
import { ContextService } from './core/services/context.service';
import { AppService } from './modules/fhi/services/app.service';
import { MomentDateAdapter, MAT_MOMENT_DATE_FORMATS } from '@angular/material-moment-adapter';
import { MAT_DATE_LOCALE, MAT_DATE_FORMATS, DateAdapter } from '@angular/material/core';
import { ConfirmDialogComponent } from './shared/components/confirm-dialog/confirm-dialog.component';


export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MMMM YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AboutDialogComponent,
    MessageCardStapleComponent,
    ConfirmDialogComponent,
  ],
  imports: [
    BrowserModule,
    KeycloakAngularModule,
    BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FhiModule
  ],
  providers: [
    Title,
    AppService,
    ContextService,
    PrivilegeRouteGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true,
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService, ContextService],
    },
    { provide: MAT_DATE_LOCALE, useValue: 'de-DE' },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },

  ],
  entryComponents: [
    AboutDialogComponent,
    MessageCardStapleComponent,
    ConfirmDialogComponent,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
