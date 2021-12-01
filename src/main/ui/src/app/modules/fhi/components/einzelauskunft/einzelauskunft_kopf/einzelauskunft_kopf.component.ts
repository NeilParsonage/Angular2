import { Component, Input, OnInit } from '@angular/core';
import { Auftrag } from '../../../models/auftrag';

@Component({
  selector: 'app-einzelauskunft-kopf',
  templateUrl: './einzelauskunft_kopf.component.html',
  styleUrls: ['./einzelauskunft_kopf.component.scss'],
})
export class EinzelauskunftKopfComponent implements OnInit {
  constructor() {}

  einzelauskunft: Auftrag = null;

  @Input()
  set daten(data: Auftrag) {
    if (!data) {
      return;
    }
    this.einzelauskunft = data;
  }

  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }
}
