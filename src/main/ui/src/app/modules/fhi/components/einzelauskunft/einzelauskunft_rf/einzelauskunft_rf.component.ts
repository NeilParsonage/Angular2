import { Component, Input, OnInit } from '@angular/core';
import { Auftrag } from '../../../models/auftrag';

@Component({
  selector: 'app-einzelauskunft-rf',
  templateUrl: './einzelauskunft_rf.component.html',
  styleUrls: ['./einzelauskunft_rf.component.scss'],
})
export class EinzelauskunftRFComponent implements OnInit {
  constructor() {}

  einzelauskunft: Auftrag;

  @Input()
  set daten(data: Auftrag) {
    this.einzelauskunft = data;
  }

  ngOnInit(): void {
    console.log('on init' || this.einzelauskunft.pnr);
    // hello my friend
  }
}
