import { DragRef, Point } from '@angular/cdk/drag-drop';
import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { first } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { DialogStaticHelper } from 'src/app/shared/utils/dialog-static-helper';
import { Auftrag } from '../../../models/auftrag';
import { AuftragVorgangStatus } from '../../../models/auftrag-status';
import { AuftragService } from '../../../services/auftrag.service';
import { changeBandChoice } from './changeBandChoice.';
import { DialogChangeBandOptions } from './dialog-changeBand.options.component';

@Component({
  selector: 'dialog-changeBand',
  templateUrl: 'dialog-changeBand.component.html',
  styleUrls: ['dialog-changeBand.component.scss'],
})
export class DialogChangeBandComponent implements OnInit {
  auftrag: Auftrag = null;
  titel: string = null;
  bandnr: number;

  constructor(
    @Inject(MAT_DIALOG_DATA) public options: DialogChangeBandOptions,
    public datepipe: DatePipe,
    public dialogRef: MatDialogRef<DialogChangeBandComponent>,
    private auftragService: AuftragService,
    private ctx: ContextService
  ) {
    this.auftrag = this.options.auftrag;

    this.titel = this.options.titel;
    this.bandnr = this.options.auftrag.bandNr;
  }

  ngOnInit(): void {}

  async onClickOk() {
    console.log('test Clik');
    if (this.auftrag.bandNr === this.bandnr) {
      return; // do nothing - no change detected
    }
    const updateAuftrag = { ...this.auftrag };
    updateAuftrag.bandNr = this.bandnr;

    const vorgang: AuftragVorgangStatus = await this.updateBandnr(updateAuftrag);
    if (vorgang.status > 2) {
      // Error Case
      this.ctx.addErrorString('Fehler!! TODO ErrorMessage');
    } else {
      this.ctx.addInfoString(`Bandwechsel der PNR ${updateAuftrag.pnr} von '${this.auftrag.bandNr}' nach '${updateAuftrag.bandNr}' geändert!`);
      // TODO besser refresh master Auftrag

      this.dialogRef.close(changeBandChoice.CONFIRM);
    }
  }

  onClickCancel() {
    this.dialogRef.close();
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }

  updateBandnr(auftrag: Auftrag) {
    return this.auftragService.changeBand(auftrag).pipe(first()).toPromise();
  }
}
