import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { AuftragCodes } from '../../../models/auftragCodes';
import { DialogShowCodesOptions } from './dialog-showCodes.options.component';

@Component({
  selector: 'dialog-showCodes',
  templateUrl: 'dialog-showCodes.component.html',
  styleUrls: ['dialog-showCodes.component.scss'],
})
export class DialogShowCodesComponent implements OnInit {
  listeCodes: AuftragCodes[] = null;
  titel: string = null;
  relevant: boolean = true;

  displayedColumns: string[] = ['Code', 'Text'];
  matDataSource: MatTableDataSource<AuftragCodes> = new MatTableDataSource<AuftragCodes>();

  constructor(@Inject(MAT_DIALOG_DATA) public options: DialogShowCodesOptions, public dialogRef: MatDialogRef<DialogShowCodesComponent>) {}

  ngOnInit(): void {
    this.listeCodes = this.options.listeCodes;
    this.fillListe();
    this.titel = this.options.titel;
  }

  fillListe() {
    console.log('change' + this.relevant);

    const filteredCodes = this.listeCodes.filter(e => e.relevant === this.relevant);
    this.matDataSource = new MatTableDataSource<AuftragCodes>(filteredCodes);
  }

  onClickOk() {
    this.dialogRef.close();
  }
}
