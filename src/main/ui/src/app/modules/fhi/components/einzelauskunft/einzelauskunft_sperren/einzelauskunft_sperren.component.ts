import { Component, Input, OnInit } from '@angular/core';
import { Auftrag } from '../../../models/auftrag';
import { AuftragService } from '../../../services/auftrag.service';
@Component({
  selector: 'app-einzelauskunft-sperren',
  templateUrl: './einzelauskunft_sperren.component.html',
  styleUrls: ['./einzelauskunft_sperren.component.scss'],
})
export class EinzelauskunftSperrenComponent implements OnInit {
  constructor(private auftragService: AuftragService) {}

  einzelauskunft: Auftrag = null;

  @Input()
  set daten(data: Auftrag) {
    if (!data) {
      return;
    }
    this.einzelauskunft = data;
  }
  ngOnInit(): void {
    console.log('on init' || this.einzelauskunft.pnr);
  }
}
