import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { AuftragKriterien } from '../../../models/auftragKriterien';
import { DialogShowKriterienOptions } from './dialog-showKriterien.options.component';

@Component({
  selector: 'dialog-showKriterien',
  templateUrl: 'dialog-showKriterien.component.html',
  styleUrls: ['dialog-showKriterien.component.scss'],
})
export class DialogShowKriterienComponent implements OnInit {
  listeKriterien: AuftragKriterien[] = null;
  titel: string = null;
  relevant: boolean = true;

  displayedColumns: string[] = ['Kriterium', 'Text', 'Dichte', 'Intervall'];
  matDataSource: MatTableDataSource<AuftragKriterien> = new MatTableDataSource<AuftragKriterien>();

  constructor(@Inject(MAT_DIALOG_DATA) public options: DialogShowKriterienOptions, public dialogRef: MatDialogRef<DialogShowKriterienComponent>) {}

  ngOnInit(): void {
    this.listeKriterien = this.options.listeKriterien;
    this.fillListe();
    this.titel = this.options.titel;
  }

  fillListe() {
    const filteredKriterien = this.listeKriterien.filter(e => e.relevant === this.relevant);
    this.matDataSource = new MatTableDataSource<AuftragKriterien>(filteredKriterien);
  }

  onClickOk() {
    this.dialogRef.close();
  }
}
