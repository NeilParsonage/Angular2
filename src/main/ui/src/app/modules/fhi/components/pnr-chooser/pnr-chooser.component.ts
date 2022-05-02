import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { Auftrag } from '../../models/auftrag';

@Component({
  selector: 'app-pnr-chooser',
  templateUrl: './pnr-chooser.component.html',
  styleUrls: ['./pnr-chooser.component.scss'],
})
export class PnrChooserComponent implements OnInit {
  tabIndex = -1;

  @Input() currentAuftrag: Auftrag;

  @Input() auftragList: Auftrag[];

  @Output() auftragChanged = new EventEmitter<Auftrag>();

  ngOnInit(): void {
    this.getIndex();
  }

  actionChangePnr(event: MatTabChangeEvent) {
    this.auftragChanged.emit(this.auftragList[event.index]);
  }

  isAufsetzpunkt(auftrag: Auftrag) {
    return this.currentAuftrag.pnr === auftrag.pnr;
  }

  private getIndex(): void {
    let ind = 0;
    this.auftragList.forEach(e => {
      if (this.currentAuftrag.pnr === e.pnr) {
        this.tabIndex = ind;
        return;
      }
      ind = ind + 1;
    });
  }
}
