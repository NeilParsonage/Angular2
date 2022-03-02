import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogShowlistOptions } from './dialog-showlist.options.component';

@Component({
  selector: 'dialog-showlist.component.ts',
  templateUrl: 'dialog-showlist.component.html',
})
export class DialogShowlistComponent implements OnInit {
  liste: string[] = null;
  titel: string = null;

  constructor(@Inject(MAT_DIALOG_DATA) public options: DialogShowlistOptions, public dialogRef: MatDialogRef<DialogShowlistComponent>) {}

  ngOnInit(): void {
    this.liste = this.options.liste;
    this.titel = this.options.titel;
  }

  onClickOk() {
    this.dialogRef.close();
  }
}
