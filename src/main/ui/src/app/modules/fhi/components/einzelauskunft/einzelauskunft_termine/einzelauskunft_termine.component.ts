import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { forkJoin, Observable } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragTermine } from '../../../models/auftragTermine';
import { AuftragTermineDetails } from '../../../models/auftragTermineDetails';
import { AuftragService } from '../../../services/auftrag.service';
@Component({
  selector: 'app-einzelauskunft-termine',
  templateUrl: './einzelauskunft_termine.component.html',
  styleUrls: ['./einzelauskunft_termine.component.scss'],
})
export class EinzelauskunftTermineComponent implements OnInit {
  typs: { [key: string]: string } = {
    I: 'Ist',
    P: 'Plan',
    S: 'Soll',
  };
  termine: AuftragTermine;
  terminDetails: AuftragTermineDetails[];
  constructor(private auftragService: AuftragService, public datepipe: DatePipe) {}

  einzelauskunft: Auftrag = null;
  dataSource$: Observable<any>;

  //displayedColumns: string[] = [];

  displayedColumns: string[] = ['Gewerk', 'BeginnTermin', 'IstSequenzTermin', 'TeilsendungTermin', 'StornoTermin'];
  matDataSource: MatTableDataSource<AuftragTermineDetails> = new MatTableDataSource<AuftragTermineDetails>();
  divider = '          ';

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
    const termineDetailsData = this.auftragService.getAuftragTermineDetailsByPnr(auftrag.pnr);
    const loadSources: any = [termineData, termineDetailsData];

    let srcIdx = 0;

    forkJoin(loadSources).subscribe(results => {
      if (!results) {
        return;
      }
      //console.log(results[srcIdx++]);
      // let termresult: AuftragTermine = results[srcIdx++] as AuftragTermine;
      this.termine = results[srcIdx++] as AuftragTermine;
      this.terminDetails = results[srcIdx++] as AuftragTermineDetails[];

      this.matDataSource = new MatTableDataSource<AuftragTermineDetails>(this.terminDetails);
      console.log(this.termine);
      console.log(this.terminDetails);
    });
  }

  setTermin(termin: Date, typ: string): string {
    if (!termin) {
      return '';
    }
    const latest_date = this.datepipe.transform(termin, 'dd.MM.yyyy HH:mm');

    return latest_date + '     ' + this.typs[typ];
  }
}
