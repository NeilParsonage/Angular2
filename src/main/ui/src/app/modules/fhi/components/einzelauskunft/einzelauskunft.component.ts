import { Component, OnInit } from '@angular/core';
import { Auftrag } from '../../models/auftrag';

@Component({
  selector: 'app-einzelauskunft',
  templateUrl: './einzelauskunft.component.html',
  styleUrls: ['./einzelauskunft.component.scss'],
})
export class EinzelauskunftComponent implements OnInit {
  constructor() {}

  auftrag: Auftrag = { pnr: '12223', lfdNrGes: 101, lfdNrLmt: 999, lfdNrFhi: 777 };

  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }
}
