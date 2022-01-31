import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Auftrag } from '../../models/auftrag';
import { AuftragService } from '../../services/auftrag.service';

@Component({
  selector: 'app-einzelauskunft',
  templateUrl: './einzelauskunft.component.html',
  styleUrls: ['./einzelauskunft.component.scss'],
})
export class EinzelauskunftComponent implements OnInit {
  auftrag: Auftrag = null;
  // eslint-disable-next-line @typescript-eslint/no-inferrable-types
  searchpnr: string = ''; //11177780

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
      });
  }
}
