import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragAggregate } from '../../../models/auftragAggregate';
import { AuftragFhsLacke } from '../../../models/auftragFhsLacke';
import { AuftragKabelsaetze } from '../../../models/auftragKabelsatz';
import { AuftragService } from '../../../services/auftrag.service';
import { DialogShowlistComponent } from '../dialog-showlist/dialog-showlist.component';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  constructor(private auftragService: AuftragService, public dialog: MatDialog) {}

  einzelauskunft: Auftrag = null;
  codesView: string = null;
  kriterienView: string = null;
  kabelsaetzeliste: AuftragKabelsaetze[];
  fhsLackeliste: AuftragFhsLacke[];
  aggregateliste: AuftragAggregate[];
  kabelsatz: AuftragKabelsaetze = null;
  fhsLack: AuftragFhsLacke = null;
  aggregat: AuftragAggregate = null;

  @Input()
  set daten(data: Auftrag) {
    if (!data) {
      return;
    }
    this.einzelauskunft = data;
    this.loadData(this.einzelauskunft);
  }

  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }

  private loadData(auftrag: Auftrag) {
    console.log('load Data ' + auftrag.pnr);
    const kabelsatzData = this.auftragService.getAuftragKabelsaetzeByPnr(auftrag.pnr);
    const fhsLackeData = this.auftragService.getAuftragFhsLackeByPnr(auftrag.pnr);
    const aggregateData = this.auftragService.getAuftragAggregateByPnr(auftrag.pnr);
    const loadSources: any = [kabelsatzData, fhsLackeData, aggregateData];

    let srcIdx = 0;

    forkJoin(loadSources).subscribe(results => {
      if (!results) {
        return;
      }
      //console.log(results[srcIdx++]);
      // let termresult: AuftragTermine = results[srcIdx++] as AuftragTermine;

      this.kabelsaetzeliste = results[srcIdx++] as AuftragKabelsaetze[];
      this.fhsLackeliste = results[srcIdx++] as AuftragFhsLacke[];
      this.aggregateliste = results[srcIdx++] as AuftragAggregate[];
      this.kabelsatz = this.kabelsaetzeliste[0];
      this.fhsLack = this.fhsLackeliste[0];
      this.aggregat = this.aggregateliste[0];
      this.codesView = this.einzelauskunft.fhiRelCodes + ' - ' + this.einzelauskunft.bandRelCodes;
      this.kriterienView = this.einzelauskunft.fhiRelKrits + ' - ' + this.einzelauskunft.bandRelKrits;
      console.log(this.kabelsaetzeliste);
    });
  }
  showListe(listeElements: string[], titel: string) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '500px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      liste: listeElements,
      titel: titel + ' von PNR ' + this.einzelauskunft.pnr,
    };
    const dialogRef = this.dialog.open(DialogShowlistComponent, dialogConfig);
  }

  showListAggregate() {
    let listeElements: string[] = [];
    this.aggregateliste.forEach((e, i) => {
      listeElements[i] = e.aggregat;
    });

    this.showListe(listeElements, 'Aggregate');
  }

  showListKabelsaetzeOld() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.width = '500px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    let listeElements: string[] = [];
    this.kabelsaetzeliste.forEach((e, i) => {
      listeElements[i] = e.kabelsatz;
    });

    dialogConfig.data = {
      liste: listeElements,
      titel: 'Kabelsätze',
    };
    const dialogRef = this.dialog.open(DialogShowlistComponent, dialogConfig);
  }

  showListKabelsaetze() {
    let listeElements: string[] = [];
    this.kabelsaetzeliste.forEach((e, i) => {
      listeElements[i] = e.kabelsatz;
    });

    this.showListe(listeElements, 'Kabelsätze');
  }
}
