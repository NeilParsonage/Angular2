import { DecimalPipe, registerLocaleData } from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
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
  displayedColumns: string[] = ['Pnr', 'Bd', 'Fzgbm', 'Quelle', 'Aktion', 'Aktionstermin', 'Ort'];
  //displayedColumns: string[] = ['Pnr', 'Bd', 'Fzgbm'];

  //matDataSource: MatTableDataSource<Auftragshistorie> = new MatTableDataSource<Auftragshistorie>();

  matdataSource: MatTableDataSource<Auftragshistorie> = new MatTableDataSource<Auftragshistorie>();
  matPaginatorIntl: MatPaginatorIntl = new MatPaginatorIntl();
  decimalPipe = new DecimalPipe(navigator.language);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  refreshSubscription: any;

  constructor(
    private contextService: ContextService,
    private auftragsHistorieService: AuftragHistorieService,
    public dialog: MatDialog,
    private router: Router
  ) {
    this.refreshSubscription = this.contextService.getForcePageRefresh().subscribe(data => {
      this.contextService.storeScrollPosition();
      //this.loadData();
    });
  }

  async loadData() {
    console.log('load Data');

    const auftragHistorieData: Auftragshistorie[] = await this.getAuftragshistorieData();
    this.matdataSource.data = auftragHistorieData;
  }

  getAuftragshistorieData(): PromiseLike<Auftragshistorie[]> {
    return this.auftragsHistorieService.getAllAuftragsHistorie().pipe(first()).toPromise();
  }

  ngOnInit(): void {
    this.loadData();
  }
}
