import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  auftragList: Auftrag[] = null;
  // eslint-disable-next-line @typescript-eslint/no-inferrable-types
  searchpnr: string = ''; //11177780
  tabs = ['First', 'Second', 'Third'];
  tabind = 1;

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
  searchForm(searchInfo) {
    this.searchpnr = this.form.value.pnr;
    if (this.searchpnr !== '') {
      this.loadData(this.searchpnr);
    }
  }

  private loadData(pnr: string) {
    this.auftragService
      .getAuftragByPnr(pnr)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
        console.log(this.auftrag);
        this.loadList(this.auftrag.lfdNrGes);
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
          console.log(this.tabind);
        });
    }
  }
  getIndex() {
    let ind: number = 0;
    this.auftragList.forEach(e => {
      if (this.searchpnr == e.pnr) {
        this.tabind = ind;
        return;
      }
      ind = ind + 1;
    });
  }
}
