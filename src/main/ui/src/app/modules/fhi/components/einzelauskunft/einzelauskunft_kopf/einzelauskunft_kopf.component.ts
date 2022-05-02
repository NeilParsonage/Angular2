import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { forkJoin } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragAggregate } from '../../../models/auftragAggregate';
import { AuftragKabelsaetze } from '../../../models/auftragKabelsatz';
import { AuftragLacke } from '../../../models/auftragLacke';
import { AuftragService } from '../../../services/auftrag.service';
import { DialogShowlistComponent } from '../dialog-showlist/dialog-showlist.component';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  constructor(private auftragService: AuftragService, public dialog: MatDialog, private translateService: TranslateService) {}

  einzelauskunft: Auftrag = null;
  codesView: string = null;
  kriterienView: string = null;
  kabelsaetzeliste: AuftragKabelsaetze[];
  fhsLackeliste: AuftragLacke[];
  fzgLack: AuftragLacke;
  aggregateliste: AuftragAggregate[];
  kabelsatz: AuftragKabelsaetze = null;
  fhsLack: AuftragLacke = null;
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
    const fzgLackeData = this.auftragService.getAuftragFzgLackByPnr(auftrag.pnr);
    const aggregateData = this.auftragService.getAuftragAggregateByPnr(auftrag.pnr);
    const loadSources: any = [kabelsatzData, fhsLackeData, fzgLackeData, aggregateData];

    let srcIdx = 0;

    forkJoin(loadSources).subscribe(results => {
      if (!results) {
        return;
      }
      //console.log(results[srcIdx++]);
      // let termresult: AuftragTermine = results[srcIdx++] as AuftragTermine;

      this.kabelsaetzeliste = results[srcIdx++] as AuftragKabelsaetze[];
      this.fhsLackeliste = results[srcIdx++] as AuftragLacke[];
      this.fzgLack = results[srcIdx++] as AuftragLacke;
      this.aggregateliste = results[srcIdx++] as AuftragAggregate[];
      console.log('fzglack' + this.fzgLack);
      console.log(this.fzgLack);
      this.kabelsatz = this.kabelsaetzeliste[0];
      this.fhsLack = this.fhsLackeliste[0];
      this.aggregat = this.aggregateliste[0];
      this.codesView = this.einzelauskunft.fhiRelCodes + ' - ' + this.einzelauskunft.bandRelCodes;
      this.kriterienView = this.einzelauskunft.fhiRelKrits + ' - ' + this.einzelauskunft.bandRelKrits;
      console.log(this.kabelsaetzeliste);
    });
  }
  showListe(listeElements: string[], titel: string, dialogWidth: string = '500px') {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = dialogWidth;
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      liste: listeElements,
      titel: titel + ' ' + this.translateService.instant('text.einzelauskunft.vonpnr') + ' ' + this.einzelauskunft.pnr,
    };
    const dialogRef = this.dialog.open(DialogShowlistComponent, dialogConfig);
  }

  showListAggregate() {
    let listeElements: string[] = [];
    this.aggregateliste.forEach((e, i) => {
      listeElements[i] = e.aggregat;
    });

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.aggregate'));
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

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.kabelsaetze'));
  }

  showListFhsLacke() {
    let listeElements: string[] = [];
    this.fhsLackeliste.forEach((e, i) => {
      listeElements[i] = e.lackschl + '  ' + e.lackLangText + '  ' + this.to_blank(e.lackzus) + '  ' + this.to_blank(e.lackzLangText);
    });

    this.showListe(listeElements, 'Fhs Lacke', '600px');
  }

  showListFzgLack() {
    let listeElements: string[] = [];

    listeElements[0] = this.fzgLack.lackschl + '  ' + this.fzgLack.lackLangText;

    this.showListe(listeElements, 'Fzg Lack');
  }

  to_blank(text: string): string {
    if (text !== 'undefined' && text !== null) {
      return text;
    }
    return '';
  }
}
