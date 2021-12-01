import { Component, Input, OnInit } from '@angular/core';
import { forkJoin, Observable } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragTermine } from '../../../models/auftragTermine';
import { AuftragService } from '../../../services/auftrag.service';

@Component({
  selector: 'app-einzelauskunft-termine',
  templateUrl: './einzelauskunft_termine.component.html',
  styleUrls: ['./einzelauskunft_termine.component.scss'],
})
export class EinzelauskunftTermineComponent implements OnInit {
  termine: AuftragTermine;
  constructor(private auftragService: AuftragService) {}

  einzelauskunft: Auftrag = null;
  dataSource$: Observable<any>;

  @Input()
  set daten(data: Auftrag) {
    if (!data) {
      return;
    }
    this.einzelauskunft = data;
    this.loadData(this.einzelauskunft);
  }

  ngOnInit(): void {
    console.log('on init' + this.einzelauskunft?.pnr);
    // hello my friend
  }

  private loadData(auftrag: Auftrag) {
    console.log('load Data ' + auftrag.pnr);
    const termineData = this.auftragService.getAuftragTermineByPnr(auftrag.pnr);
    const loadSources: any = [termineData];

    let srcIdx = 0;

    forkJoin(loadSources).subscribe(results => {
      if (!results) {
        return;
      }
      //console.log(results[srcIdx++]);
      // let termresult: AuftragTermine = results[srcIdx++] as AuftragTermine;
      this.termine = results[srcIdx++] as AuftragTermine;
      //console.log(this.termine);
    });
  }
}
