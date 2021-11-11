import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Auftrag } from '../../models/auftrag';
import { AuftragService } from '../../services/auftrag.service';

@Component({
  selector: 'app-einzelauskunft',
  templateUrl: './einzelauskunft.component.html',
  styleUrls: ['./einzelauskunft.component.scss'],
})
export class EinzelauskunftComponent implements OnInit {
  auftrag: Auftrag = { pnr: '12223', lfdNrGes: 101, lfdNrLmt: 999, lfdNrFhi: 777 };
  dataSource$: any;
  constructor(private auftragService: AuftragService) {
    this.loadData();
  }
  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }

  private loadData() {
    this.auftragService
      .getAuftragByPnr('11177780')
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
      });
  }
}
