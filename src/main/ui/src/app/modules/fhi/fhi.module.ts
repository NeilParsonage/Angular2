import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LibEmstTableModule } from 'emst-table';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LabeledIconComponent } from './shared/labeled-icon/labeled-icon.component';
import { LabeledTextComponent } from './shared/labeled-text/labeled-text.component';

@NgModule({
  declarations: [LabeledTextComponent, WelcomeComponent, LabeledIconComponent],
  providers: [],
  imports: [CommonModule, FlexLayoutModule, FormsModule, LibEmstTableModule, MaterialModule, ReactiveFormsModule],
})
export class FhiModule {}
