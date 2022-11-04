import { registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { Component, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { DaiFilterCode, DaiFilterType, DaiPageData, DaiPaginatorConfig, DaiTableConfig } from 'emst-table';
import { BehaviorSubject, combineLatest, Observable, of, Subject } from 'rxjs';
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
    switchMap(([query]) => {
      return this.auftragsHistorieService.getAll(query).pipe(switchMap(data => this.initializeTable(data)));
    })
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
        width: '100px',
      },
    },
    quelle: {
      name: 'Quelle',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Quelle',
      },
      sortable: true,
    },
    meldkenn: {
      name: 'Meld-Kennung',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Meld-Kennung',
      },
      sortable: true,
    },
    aktion: {
      name: 'Aktion',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Aktion',
      },
      sortable: true,
    },
    sendetermin: {
      name: 'Aktionstermin',
      filter: {
        type: DaiFilterType.DATERANGE,
        code: DaiFilterCode.EQUAL,
        title: 'Aktionstermin',
      },
      function: function (element: Auftragshistorie) {
        if (element.sendetermin) return '<div> ' + FormatUtil.formatDateWithFormat(element.sendetermin, 'dd.MM.yyyy') + ' </div>';
      },
      sortable: true,
      direction: 'asc',
    },
    zeit: {
      name: 'Zeit',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Zeit',
      },
      sortable: true,
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
      },
      sortable: true,
    },
    ort: {
      name: 'Ort',
      filter: {
        type: DaiFilterType.STRING,
        code: DaiFilterCode.IGNORECASELIKE,
        title: 'Ort',
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
    const daiPageData = new DaiPageData(data?.totalElements, data?.number);

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
