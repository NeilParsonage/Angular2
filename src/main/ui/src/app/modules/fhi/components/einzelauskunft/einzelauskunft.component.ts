import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { TranslateService } from '@ngx-translate/core';
import { first } from 'rxjs/operators';
import { Auftrag } from '../../models/auftrag';
import { AuftragService } from '../../services/auftrag.service';

@Component({
  selector: 'app-einzelauskunft',
  templateUrl: './einzelauskunft.component.html',
  styleUrls: ['./einzelauskunft.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EinzelauskunftComponent implements OnInit {
  auftrag: Auftrag = null;
  auftragSearch: Auftrag = null;
  auftragChoosen: Auftrag = null;
  auftragList: Auftrag[] = null;
  // eslint-disable-next-line @typescript-eslint/no-inferrable-types
  searchpnr: string = '';

  options = [
    { value: 'pnr', label: 'PNR', key: 'PNR', filter: '' },
    { value: 'gesamt', label: 'Gesamt lfd. Nr.', key: 'Gesamt lfd. Nr.', filter: '' },
    { value: 'fhi', label: 'Ist lfd. FHI', key: 'Ist lfd. FHI', filter: '' },
    { value: 'lmt', label: 'Ist lfd. LMT', key: 'Ist lfd. LMT', filter: '' },
  ];

  selected = this.options[0];

  dataSource$: any;
  constructor(private auftragService: AuftragService, private translateService: TranslateService) {}
  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }

  reset(): void {
    this.auftrag = null;
    this.auftragSearch = null;
    this.auftragChoosen = null;
    this.auftragList = null;
  }

  search(): void {
    console.log('Suche ' + this.selected.value + ' ' + this.selected.filter);

    this.reset();
    if (this.selected.value !== '' && this.selected.filter !== '') {
      this.loadDataFromSearch();
    }
  }

  private loadDataFromSearch() {
    this.auftragService
      .getAuftragBy(this.selected.value, this.selected.filter)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
        this.auftragSearch = this.auftrag;
        this.searchpnr = this.auftrag.pnr;
        console.log(this.auftrag);
        this.loadList(this.auftragSearch.lfdNrGes);
      });
  }

  private loadDataFromTab(pnr: string) {
    this.auftragService
      .getAuftragByPnr(pnr)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
      });
  }

  private loadList(lfdNrGes: number) {
    if (lfdNrGes) {
      this.auftragService
        .getListAuftraegebyLfdNrGes(lfdNrGes)
        .pipe(first())
        .subscribe(data => {
          this.auftragList = data;
          console.log(this.auftragList);
        });
    }
  }

  onAuftragChanged($event) {
    this.auftragChoosen = $event;
    console.log('onAuftragChanged');
    console.log(this.auftragChoosen);
    if (this.auftragChoosen) {
      this.loadDataFromTab(this.auftragChoosen.pnr);
    }
  }
  actionChangePnr($event: MatTabChangeEvent) {
    this.auftragChoosen = this.auftragList[$event.index];
    console.log('actionChangePnr              Test');
    console.log(this.auftragChoosen);
    if (this.auftragChoosen) {
      this.loadDataFromTab(this.auftragChoosen.pnr);
    }
  }
}
