import { NgModule } from '@angular/core';
import {CalendarModule} from 'primeng/calendar';


@NgModule({
  imports: [
    CalendarModule
  ],
  exports: [
    CalendarModule
  ]
})
export class PrimeModule {}
