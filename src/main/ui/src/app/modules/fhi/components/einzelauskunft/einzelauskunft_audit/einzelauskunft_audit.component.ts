import { Component, Input, OnInit } from '@angular/core';
import { Auftrag } from '../../../models/auftrag';
import { AuftragService } from '../../../services/auftrag.service';
@Component({
  selector: 'app-einzelauskunft-audit',
  templateUrl: './einzelauskunft_audit.component.html',
  styleUrls: ['./einzelauskunft_audit.component.scss'],
})
export class EinzelauskunftAuditComponent implements OnInit {
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

  public checkStatus(status: string): boolean {
    if (status == null || status == '') {
      return false;
    }

    if (status === '1') {
      return true;
    } else {
      return false;
    }
  }
}
