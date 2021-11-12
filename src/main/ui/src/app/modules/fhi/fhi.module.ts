import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LibEmstTableModule } from 'emst-table';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { EinzelauskunftComponent } from './components/einzelauskunft/einzelauskunft.component';
import { EinzelauskunftKopfComponent } from './components/einzelauskunft/einzelauskunft_kopf/einzelauskunft_kopf.component';
import { EinzelauskunftRFComponent } from './components/einzelauskunft/einzelauskunft_rf/einzelauskunft_rf.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LabeledIconComponent } from './shared/labeled-icon/labeled-icon.component';
import { LabeledTextComponent } from './shared/labeled-text/labeled-text.component';

@NgModule({
  declarations: [LabeledTextComponent, WelcomeComponent, EinzelauskunftComponent, EinzelauskunftRFComponent, EinzelauskunftKopfComponent, LabeledIconComponent],
  providers: [],
  imports: [CommonModule, FlexLayoutModule, FormsModule, LibEmstTableModule, MaterialModule, ReactiveFormsModule],
})
export class FhiModule {}
