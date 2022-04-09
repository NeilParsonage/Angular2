import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatTabChangeEvent } from '@angular/material/tabs';
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
  tabIndex = -1;
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

  form = new FormGroup({
    pnr: new FormControl(),
    art: new FormControl(),
  });

  dataSource$: any;
  constructor(private auftragService: AuftragService) {
    if (this.searchpnr !== '') {
      this.loadData(this.searchpnr);
    }
  }
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

  searchForm(searchInfo): void {
    console.log('Suche ' + this.selected.value + ' ' + this.selected.filter);
    console.log('Suche2 ' || this.form.value);
    this.searchpnr = this.selected.filter;
    this.reset();
    if (this.searchpnr !== '') {
      this.loadData(this.searchpnr, true);
    }
  }

  private loadData(pnr: string, blist?: boolean) {
    this.auftragService
      .getAuftragByPnr(pnr)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
        this.auftragSearch = this.auftrag;
        console.log(this.auftrag);
        if (blist) {
          this.loadList(this.auftragSearch.lfdNrGes);
        }
      });
  }

  private loadData2(pnr: string) {
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
          this.getIndex();
          console.log(this.tabIndex);
        });
    }
  }
  getIndex() {
    let ind: number = 0;
    this.auftragList.forEach(e => {
      if (this.searchpnr === e.pnr) {
        this.tabIndex = ind;
        return;
      }
      ind = ind + 1;
    });
  }

  onAuftragChanged($event) {
    this.auftragChoosen = $event;
    console.log('onAuftragChanged');
    console.log(this.auftragChoosen);
    if (this.auftragChoosen) {
      this.loadData2(this.auftragChoosen.pnr);
    }
  }
  actionChangePnr($event: MatTabChangeEvent) {
    this.auftragChoosen = this.auftragList[$event.index];
    console.log('actionChangePnr              Test');
    console.log(this.auftragChoosen);
    if (this.auftragChoosen) {
      this.loadData2(this.auftragChoosen.pnr);
    }
  }
}
