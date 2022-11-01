import { DecimalPipe, registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { forkJoin, Observable } from 'rxjs';
import { first, tap } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { Auftragshistorie } from '../../models/auftragshistorie';

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
  displayedColumns: string[] = ['Pnr', 'Bd', 'Fzgbm', 'Quelle', 'Aktion', 'Aktionstermin', 'Ort'];

  data: MatTableDataSource<Auftragshistorie>;
  matPaginatorIntl: MatPaginatorIntl = new MatPaginatorIntl();
  decimalPipe = new DecimalPipe(navigator.language);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  refreshSubscription: any;

  constructor(
    private contextService: ContextService,
    private auftragsHistorieService: AuftragsHistorieService,
    public dialog: MatDialog,
    private router: Router
  ) {
    this.contextService.setCurrentPageInfo(
      'Auftragshistorie bearbeiten',
      'https://wiki.dewoe.corpintra.net/wikiemst/index.php/W060.WKAL.F.Frontend.Produebistzahlen'
    );
    this.refreshSubscription = this.contextService.getForcePageRefresh().subscribe(data => {
      this.contextService.storeScrollPosition();
      this.loadData();
    });
  }

  private loadData() {
    console.log('load Data');
    const auftragsHistorieData = this.auftragsHistorieService.getAllAuftragsHistorie();

    let loadSources: any = [auftragsHistorieData];

    let srcIdx = 0;

    this.dataSource$ = forkJoin(loadSources).pipe(
      tap(results => {
        this.auftragshistorie = results[srcIdx++];

        this.data = new MatTableDataSource(this.auftragshistorie);
        //this.refreshFilter();

        console.log('loaded data', this.data);

        this.contextService
          .afterRendered()
          .pipe(first())
          .subscribe(() => this.contextService.restoreScrollPosition());
      })
    );
  }

  ngOnInit(): void {}
}
