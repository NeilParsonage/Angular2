import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LibEmstTableModule } from 'emst-table';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { EinzelauskunftComponent } from './components/einzelauskunft/einzelauskunft.component';
import { EinzelauskunftKopfComponent } from './components/einzelauskunft/einzelauskunft_kopf/einzelauskunft_kopf.component';
import { EinzelauskunftRFComponent } from './components/einzelauskunft/einzelauskunft_rf/einzelauskunft_rf.component';
import { EinzelauskunftTermineComponent } from './components/einzelauskunft/einzelauskunft_termine/einzelauskunft_termine.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

@NgModule({
  declarations: [WelcomeComponent, EinzelauskunftComponent, EinzelauskunftRFComponent, EinzelauskunftKopfComponent, EinzelauskunftTermineComponent],
  providers: [],
  imports: [CommonModule, FlexLayoutModule, FormsModule, LibEmstTableModule, MaterialModule, ReactiveFormsModule],
})
export class FhiModule {}
