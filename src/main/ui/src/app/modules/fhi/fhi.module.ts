import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { LibEmstTableModule } from 'emst-table';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { TuebPipe } from 'src/app/shared/pipes/tueb.pipe';
import { DialogShowCodesComponent } from './components/einzelauskunft/dialog-showCodes/dialog-showCodes.component';
import { DialogShowKriterienComponent } from './components/einzelauskunft/dialog-showKriterien/dialog-showKriterien.component';
import { DialogShowlistComponent } from './components/einzelauskunft/dialog-showlist/dialog-showlist.component';
import { EinzelauskunftComponent } from './components/einzelauskunft/einzelauskunft.component';
import { EinzelauskunftAktuelleFPComponent } from './components/einzelauskunft/einzelauskunft_aktuelleFP/einzelauskunft_aktuelleFP.component';
import { EinzelauskunftAuditComponent } from './components/einzelauskunft/einzelauskunft_audit/einzelauskunft_audit.component';
import { EinzelauskunftKopfComponent } from './components/einzelauskunft/einzelauskunft_kopf/einzelauskunft_kopf.component';
import { EinzelauskunftOrtComponent } from './components/einzelauskunft/einzelauskunft_ort/einzelauskunft_ort.component';
import { EinzelauskunftRFComponent } from './components/einzelauskunft/einzelauskunft_rf/einzelauskunft_rf.component';
import { EinzelauskunftSendestatusComponent } from './components/einzelauskunft/einzelauskunft_sendestatus/einzelauskunft_sendestatus.component';
import { EinzelauskunftSperrenComponent } from './components/einzelauskunft/einzelauskunft_sperren/einzelauskunft_sperren.component';
import { EinzelauskunftTermineComponent } from './components/einzelauskunft/einzelauskunft_termine/einzelauskunft_termine.component';
import { PnrChooserComponent } from './components/pnr-chooser/pnr-chooser.component';
import { SendemaskeComponent } from './components/sendemaske/sendemaske.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

@NgModule({
  declarations: [
    WelcomeComponent,
    EinzelauskunftComponent,
    EinzelauskunftRFComponent,
    EinzelauskunftKopfComponent,
    EinzelauskunftTermineComponent,
    EinzelauskunftSendestatusComponent,
    EinzelauskunftAuditComponent,
    EinzelauskunftAktuelleFPComponent,
    EinzelauskunftOrtComponent,
    EinzelauskunftSperrenComponent,
    DialogShowlistComponent,
    DialogShowCodesComponent,
    DialogShowKriterienComponent,
    PnrChooserComponent,
    SendemaskeComponent,
    TuebPipe,
  ],
  providers: [],
  imports: [CommonModule, FlexLayoutModule, FormsModule, LibEmstTableModule, MaterialModule, ReactiveFormsModule, TranslateModule.forRoot()],
  exports: [TuebPipe],
})
export class FhiModule {}
