import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { forkJoin } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragAenderungstexte } from '../../../models/auftragAenderungstexte';
import { AuftragAggregate } from '../../../models/auftragAggregate';
import { AuftragCodes } from '../../../models/auftragCodes';
import { AuftragKabelsaetze } from '../../../models/auftragKabelsatz';
import { AuftragKriterien } from '../../../models/auftragKriterien';
import { AuftragLacke } from '../../../models/auftragLacke';
import { AuftragService } from '../../../services/auftrag.service';
import { DialogShowCodesComponent } from '../dialog-showCodes/dialog-showCodes.component';
import { DialogShowKriterienComponent } from '../dialog-showKriterien/dialog-showKriterien.component';
import { DialogShowlistComponent } from '../dialog-showlist/dialog-showlist.component';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  aenderungstexteliste: AuftragAenderungstexte[];
  constructor(private auftragService: AuftragService, public dialog: MatDialog, private translateService: TranslateService) {}

  einzelauskunft: Auftrag = null;
  codesView: string = null;
  kriterienView: string = null;
  kabelsaetzeliste: AuftragKabelsaetze[];
  fhsLackeliste: AuftragLacke[];
  fzgLack: AuftragLacke;
  aggregateliste: AuftragAggregate[];
  codeliste: AuftragCodes[];
  kriterienliste: AuftragKriterien[];
  kabelsatz: AuftragKabelsaetze = null;
  fhsLack: AuftragLacke = null;
  aggregat: AuftragAggregate = null;
  buttonBemerkungAltDisabled = true;

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
    const codeData = this.auftragService.getListAuftragCodes(auftrag.pnr);
    const kriterienData = this.auftragService.getListAuftragKriterien(auftrag.pnr);
    const AenderungstexteData = this.auftragService.getListAuftragAenderungstexte(auftrag.pnr);
    const loadSources: any = [kabelsatzData, fhsLackeData, fzgLackeData, aggregateData, codeData, kriterienData, AenderungstexteData];

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
      this.codeliste = results[srcIdx++] as AuftragCodes[];
      this.kriterienliste = results[srcIdx++] as AuftragKriterien[];
      this.aenderungstexteliste = results[srcIdx++] as AuftragAenderungstexte[];
      this.kabelsatz = this.kabelsaetzeliste[0];
      this.fhsLack = this.fhsLackeliste[0];
      this.aggregat = this.aggregateliste[0];
      this.codesView = this.einzelauskunft.fhiRelCodes + ' - ' + this.einzelauskunft.bandRelCodes;
      this.kriterienView = this.einzelauskunft.fhiRelKrits + ' - ' + this.einzelauskunft.bandRelKrits;
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

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.fhsLacke'), '600px');
  }

  showListFzgLack() {
    let listeElements: string[] = [];

    listeElements[0] = this.fzgLack.lackschl + '  ' + this.fzgLack.lackLangText;

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.fzgLacke'));
  }

  showListBemerkungAlt() {
    let listeElements: string[] = [];

    listeElements[0] = this.einzelauskunft.bemerkungAlt;

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.bemerkungstextalt'));
  }

  showListAenderungstexte() {
    let listeElements: string[] = [];

    this.aenderungstexteliste.forEach((e, i) => {
      listeElements[i] = e.quelle + ' ' + e.text;
    });

    this.showListe(listeElements, this.translateService.instant('text.einzelauskunft.aenderungstexte'));
  }

  checkVisibleBemerkungAlt() {
    if (this.einzelauskunft?.bemerkungAlt == null || this.einzelauskunft?.bemerkungAlt == '') {
      return false;
    } else {
      return true;
    }
  }

  checkVisibleAenderungstexte() {
    if (this.einzelauskunft?.aufaenText == null || this.einzelauskunft?.aufaenText == '') {
      return false;
    } else {
      return true;
    }
  }

  showListKriterien() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '900px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      listeKriterien: this.kriterienliste,
      titel:
        this.translateService.instant('text.einzelauskunft.kriterien') +
        ' ' +
        this.translateService.instant('text.einzelauskunft.vonpnr') +
        ' ' +
        this.einzelauskunft.pnr,
    };
    const dialogRef = this.dialog.open(DialogShowKriterienComponent, dialogConfig);
  }

  showListCodes() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '900px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      listeCodes: this.codeliste,
      titel:
        this.translateService.instant('text.einzelauskunft.codes') +
        ' ' +
        this.translateService.instant('text.einzelauskunft.vonpnr') +
        ' ' +
        this.einzelauskunft.pnr,
    };
    const dialogRef = this.dialog.open(DialogShowCodesComponent, dialogConfig);
  }

  to_blank(text: string): string {
    if (text !== 'undefined' && text !== null) {
      return text;
    }
    return '';
  }

  setBackgroundColorBand(bandNr: number): string {
    let result: string;
    if (bandNr == null) {
      return 'background_white';
    }

    if (bandNr == 1) {
      result = 'background_white';
    } else if (bandNr == 2) {
      result = 'background_yelloq';
    } else if (bandNr == 3) {
      result = 'background_green';
    } else {
      result = 'background_magenta';
    }

    return result;
  }
}
