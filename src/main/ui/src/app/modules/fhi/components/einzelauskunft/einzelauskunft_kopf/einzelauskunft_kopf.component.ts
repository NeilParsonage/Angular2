import { Component, Input, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragAggregate } from '../../../models/auftragAggregate';
import { AuftragFhsLacke } from '../../../models/auftragFhsLacke';
import { AuftragKabelsaetze } from '../../../models/auftragKabelsatz';
import { AuftragService } from '../../../services/auftrag.service';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  constructor(private auftragService: AuftragService) {}

  einzelauskunft: Auftrag = null;
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

      console.log(this.kabelsaetzeliste);
    });
  }
}
