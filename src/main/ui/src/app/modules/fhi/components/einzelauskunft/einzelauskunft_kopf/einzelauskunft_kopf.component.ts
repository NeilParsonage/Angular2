import { Component, Input, OnInit } from '@angular/core';
import { Auftrag } from '../../../models/auftrag';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  constructor() {}

  einzelauskunft: Auftrag = { pnr: '124', lfdNrGes: 100, lfdNrLmt: 99, lfdNrFhi: 77 };

  @Input()
  set daten(data: Auftrag) {
    this.einzelauskunft = data;
  }

  ngOnInit(): void {
    console.log('on init' || this.einzelauskunft.pnr);
    // hello my friend
  }
}
