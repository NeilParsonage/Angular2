import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { forkJoin, Observable } from 'rxjs';
import { Auftrag } from '../../../models/auftrag';
import { AuftragHeberhaus } from '../../../models/auftragHeberhaus';
import { AuftragTermine } from '../../../models/auftragTermine';
import { AuftragTermineDetails } from '../../../models/auftragTermineDetails';
import { AuftragService } from '../../../services/auftrag.service';
import { DialogShowHeberhausComponent } from '../dialog-showHeberhaus/dialog-showHeberhaus.component';
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
  heberhaus: AuftragHeberhaus;
  constructor(private auftragService: AuftragService, public datepipe: DatePipe, public dialog: MatDialog, private translateService: TranslateService) {}

  einzelauskunft: Auftrag = null;
  dataSource$: Observable<any>;

  //displayedColumns: string[] = [];

  displayedColumns: string[] = ['Gewerk', 'Aktion', 'BeginnTermin', 'IstSequenzTermin', 'TeilsendungTermin', 'StornoTermin'];
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
  }

  private loadData(auftrag: Auftrag) {
    console.log('load Data ' + auftrag.pnr);
    const termineData = this.auftragService.getAuftragTermineByPnr(auftrag.pnr);
    const termineDetailsData = this.auftragService.getAuftragTermineDetailsByPnr(auftrag.pnr);
    const heberhausData = this.auftragService.getAuftragHeberhausByPnr(auftrag.pnr);
    const loadSources: any = [termineData, termineDetailsData, heberhausData];

    let srcIdx = 0;

    forkJoin(loadSources).subscribe(results => {
      if (!results) {
        return;
      }

      // let termresult: AuftragTermine = results[srcIdx++] as AuftragTermine;
      this.termine = results[srcIdx++] as AuftragTermine;
      this.terminDetails = results[srcIdx++] as AuftragTermineDetails[];
      this.heberhaus = results[srcIdx++] as AuftragHeberhaus;
      console.log('Heber' || this.heberhaus);
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

  ibSperreIsSet() {
    if (!this.termine) {
      return false;
    }
    if (!this.termine.ibSperre) {
      return false;
    } else {
      return true;
    }
  }

  showHeberhaus() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '600px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      auftragHeberhaus: this.heberhaus,
      titel: this.translateService.instant('text.einzelauskunft.heberhaus') + ' ' + this.einzelauskunft.pnr,
    };
    const dialogRef = this.dialog.open(DialogShowHeberhausComponent, dialogConfig);
  }
}
