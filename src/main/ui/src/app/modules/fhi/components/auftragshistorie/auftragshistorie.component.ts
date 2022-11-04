import { DecimalPipe, registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DaiFilterCode, DaiFilterType, DaiPaginatorConfig, DaiTableConfig } from 'emst-table';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { Auftragshistorie } from '../../models/auftragshistorie';
import { AuftragHistorieService } from '../../services/auftrag-historie.service';

registerLocaleData(localeDe, localeDeExtra);

@Component({
  selector: 'app-auftragshistorie',
  templateUrl: './auftragshistorie.component.html',
  styleUrls: ['./auftragshistorie.component.scss'],
})
export class AuftragshistorieComponent implements OnInit {
  dataSource$: Observable<any>;
  auftragshistorie: Auftragshistorie[] = [];
  selectedAuftragshistorie: Auftragshistorie;
  expandedElement: Auftragshistorie;

  matdataSource: MatTableDataSource<Auftragshistorie> = new MatTableDataSource<Auftragshistorie>();
  matPaginatorIntl: MatPaginatorIntl = new MatPaginatorIntl();
  decimalPipe = new DecimalPipe(navigator.language);

  daiTableConfig: DaiTableConfig;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  refreshSubscription: any;

  displayedColumns = {
    pnr: {
      name: 'Pnr',
      sortable: true,
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Pnr',
        key: 'aufPnr',
      },
    },
    aufhId: {
      name: 'Id',
      sortable: true,
      direction: 'asc',
    },
    quelle: {
      name: 'Quelle',
      sortable: true,
    },
    meldkenn: {
      name: 'Meld-Kennung',
      sortable: true,
    },
    aktion: {
      name: 'Aktion',
      sortable: true,
    },
    sendetermin: {
      name: 'Aktionstermin',
      sortable: true,
    },
    zeit: {
      name: 'Zeit',
      sortable: true,
    },
    bandnr: {
      name: 'Bd',
      sortable: true,
    },
    fzgbm: {
      name: 'Fzgbm',
      sortable: true,
    },
    ort: {
      name: 'Ort',
      sortable: true,
    },
  };

  constructor(
    private contextService: ContextService,
    private auftragsHistorieService: AuftragHistorieService,
    public dialog: MatDialog,
    private router: Router
  ) {
    this.refreshSubscription = this.contextService.getForcePageRefresh().subscribe(data => {
      this.contextService.storeScrollPosition();
    });
  }

  async loadData() {
    console.log('load Data');

    const auftragHistorieData: Auftragshistorie[] = await this.getAuftragshistorieData();
    this.matdataSource.data = auftragHistorieData;
    this.auftragshistorie = auftragHistorieData;
    this.initDaiTableObjects();
  }

  getAuftragshistorieData(): PromiseLike<Auftragshistorie[]> {
    return this.auftragsHistorieService.getAllAuftragsHistorie().pipe(first()).toPromise();
  }

  // erzeugen des DaiTable Objektes
  initDaiTableObjects() {
    // Das Objekt dass die Tabelle erhält. Daten, Spaltendefinition. Dies ist die einfachste konfigurierbare Tabelle die es gibt.
    this.daiTableConfig = new DaiTableConfig(this.auftragshistorie, this.displayedColumns, this.getPaginatorConfig());
  }

  ngOnInit(): void {
    this.loadData();
  }

  getPaginatorConfig(): DaiPaginatorConfig {
    return new DaiPaginatorConfig(true, null, ['100']);
  }

  queryChanged(query: any) {
    console.log(query);
  }
}
