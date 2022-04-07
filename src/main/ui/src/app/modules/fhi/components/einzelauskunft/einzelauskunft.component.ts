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

  form = new FormGroup({
    pnr: new FormControl(),
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
    this.searchpnr = this.form.value.pnr;
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
