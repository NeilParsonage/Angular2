import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MomentPipe } from 'src/app/shared/pipes/moment-pipe';
import { PrimeModule } from 'src/app/shared/modules/prime.module';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';

@NgModule({
  declarations: [
    PagenotfoundComponent,
    MomentPipe,
  ],
  imports: [CommonModule, MaterialModule, PrimeModule, FlexLayoutModule, FormsModule, ReactiveFormsModule],
  exports: [
      PagenotfoundComponent,
      MomentPipe,
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
  ]
})
export class FhiModule {}
