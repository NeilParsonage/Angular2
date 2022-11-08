import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { Component, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { DaiFilterCode, DaiFilterType, DaiPageData, DaiPaginatorConfig, DaiTableConfig } from 'emst-table';
import { BehaviorSubject, combineLatest, concat, Observable, of, Subject } from 'rxjs';
import { switchMap, takeUntil } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { FormatUtil } from 'src/app/shared/utils/format-util';
import { AuftragshistoriePage } from '../../models/auftragshistoriePage';
import { AuftragHistorieService } from '../../services/auftrag-historie.service';
import { Auftragshistorie } from './../../models/auftragshistorie';

registerLocaleData(localeDe, localeDeExtra);

@Component({
  selector: 'app-auftragshistorie',
  templateUrl: './auftragshistorie.component.html',
  styleUrls: ['./auftragshistorie.component.scss'],
})
export class AuftragshistorieComponent implements OnDestroy {
  querySubject = new BehaviorSubject<string>('&page=0&size=10&sort=aufPnr,asc');
  queryValue$ = this.querySubject.asObservable();
  unsubscribe$ = new Subject<void>();

  daiTableConfig$: Observable<DaiTableConfig> = combineLatest([this.queryValue$]).pipe(
    switchMap(([query]) => concat(of(null), this.auftragsHistorieService.getAll(query).pipe(switchMap(data => this.initializeTable(data)))))
  );

  // zum Beispiel zum Laden einer Liste von Steuerbereichen
  // steuerbereiche$ = this.steuerbereichService.getAll().pipe(takeUntil(this.unsubscribe$), shareReplay(1));

  matPaginatorIntl: MatPaginatorIntl = new MatPaginatorIntl();
  daiPaginatorConfig: DaiPaginatorConfig = new DaiPaginatorConfig(true, this.matPaginatorIntl);

  displayedColumns = {
    pnr: {
      name: 'Pnr',
      sortable: true,
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Pnr',
        key: 'aufPnr',
        width: '80px',
      },
    },
    bandnr: {
      name: 'Bd',
      filter: {
        type: DaiFilterType.SELECT,
        code: DaiFilterCode.EQUAL,
        title: 'Band',
        width: '50px',
        list: [
          {
            text: '0',
            value: '0',
          },
          {
            text: '1',
            value: '1',
          },
          {
            text: '2',
            value: '2',
          },
        ],
      },
      sortable: true,
    },
    fzgbm: {
      name: 'Fzgbm',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Fzgbm',
        width: '80px',
      },
      sortable: true,
    },
    quelle: {
      name: 'Quelle',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Quelle',
        width: '80px',
      },
      sortable: true,
    },
    aktion: {
      name: 'Aktion',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Aktion',
        width: '200px',
      },
      sortable: true,
    },
    /*
    meldkenn: {
      name: 'Meld-Kennung',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Meld-Kennung',
        width: '100px',
      },
      sortable: true,
    },
    */
    sendetermin: {
      name: 'Aktionstermin',
      filter: {
        type: DaiFilterType.DATERANGE,
        code: DaiFilterCode.EQUAL,
        title: 'Aktionstermin',
        width: '150px',
      },
      function: function (element: Auftragshistorie) {
        if (element.sendetermin) return '<div> ' + FormatUtil.formatDateWithFormat(element.sendetermin, 'dd.MM.yyyy') + ' </div>';
      },
      sortable: true,
      direction: 'asc',
    },
    /*
    zeit: {
      name: 'Zeit',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Zeit',
        width: '100px',
      },
      sortable: true,
    },
    */
    ort: {
      name: 'Ort',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Ort',
        width: '50px',
      },
      sortable: true,
    },
    lfdNrGes: {
      name: 'Ges.lfdNr',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'lfdNrGes',
        width: '75px',
      },
      sortable: true,
    },
    lfdNrFhi: {
      name: 'Ist.lfd FHI',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'lfdNrFhi',
        width: '75px',
      },
      sortable: true,
    },
    /*
    lfdNrLmt: {
      name: 'Ist.lfd LMT',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'lfdNrLmt',
        width: '50px',
      },
      sortable: true,
    },
    */
    lfdNrRhm: {
      name: 'Ist.lfd RHM',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'lfdNrRhm',
        width: '75px',
      },
      sortable: true,
    },
    lfdNrUbm: {
      name: 'Ist.lfd UBM',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'lfdNrUbm',
        width: '75px',
      },
      sortable: true,
    },
    pat: {
      name: 'Pat',
      filter: {
        type: DaiFilterType.DATERANGE,
        code: DaiFilterCode.EQUAL,
        title: 'Pat',
        width: '125px',
      },
      function: function (element: Auftragshistorie) {
        if (element.sendetermin) return '<div> ' + FormatUtil.formatDateWithFormat(element.pat, 'dd.MM.yyyy') + ' </div>';
      },
      sortable: true,
      direction: 'asc',
    },
    gesLfdSoll: {
      name: 'Soll.Rf-G',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'gesLfdSoll',
        width: '75px',
      },
      sortable: true,
    },
    bdLfdSoll: {
      name: 'Soll.Rf-Bd',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'bdLfdSoll',
        width: '75px',
      },
      sortable: true,
    },
  };

  constructor(
    private contextService: ContextService,
    private auftragsHistorieService: AuftragHistorieService,
    public dialog: MatDialog,
    private router: Router
  ) {
    this.contextService
      .getForcePageRefresh()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(() => {
        this.contextService.storeScrollPosition();
      });

    // Übersetzung der Paginationtexte
    this.matPaginatorIntl.firstPageLabel = 'Erste Seite';
    this.matPaginatorIntl.lastPageLabel = 'Letzte Seite';
    this.matPaginatorIntl.itemsPerPageLabel = 'Auftragshistorie pro Seite:';
    this.matPaginatorIntl.nextPageLabel = 'nächste Seite';
    this.matPaginatorIntl.previousPageLabel = 'vorherige Seite';
  }

  initializeTable(data: AuftragshistoriePage): Observable<DaiTableConfig> {
    const daiPaginatorConfig = new DaiPaginatorConfig(true, this.matPaginatorIntl, ['10', '25', '50', '100']);
    const daiPageData = new DaiPageData(data?.totalElements, data?.number, data?.size);

    // return of('').pipe(
    //   concatMap(() => this.steuerbereiche$),
    //   tap(steuerbereiche => {
    //     this.steuerbereiche = steuerbereiche;
    //     this.displayedColumns.steuerbereich.filter.list = steuerbereiche.map(x => ({
    //       text: x.steuerbereich,
    //       value: x.steuerbereich,
    //     }));
    //   }),switchMap(() => {
    //     return of(
    //       new DaiTableConfig(
    //         data?.content,
    //         this.displayedColumns,
    //         daiPaginatorConfig,
    //         [],
    //         daiPageData,
    //         !this.querySubject.value
    //       )
    //     );
    //   })
    // );

    return of(new DaiTableConfig(data?.content, this.displayedColumns, daiPaginatorConfig, [], daiPageData, !this.querySubject.value));
  }

  queryChanged(query: any) {
    if (query.enterPressed) {
      console.log(query);
      this.querySubject.next(query.query);
    }
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
