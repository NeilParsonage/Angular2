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
import { DialogEditRemarkOptions } from './dialog-editBemerkung.options.component';

@Component({
  selector: 'dialog-editBemerkung',
  templateUrl: 'dialog-editBemerkung.component.html',
  styleUrls: ['dialog-editBemerkung.component.scss'],
})
export class DialogEditBemerkungComponent implements OnInit {
  auftrag: Auftrag = null;
  titel: string = null;
  bemerkung: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) public options: DialogEditRemarkOptions,
    public datepipe: DatePipe,
    public dialogRef: MatDialogRef<DialogEditBemerkungComponent>,
    private auftragService: AuftragService,
    private ctx: ContextService
  ) {
    this.auftrag = this.options.auftrag;
    this.bemerkung = this.auftrag.bemerkung;
    this.titel = this.options.titel;
  }

  ngOnInit(): void {}

  async onClickOk() {
    if (this.auftrag.bemerkung === this.bemerkung) {
      return; // do nothing - no change detected
    }
    const updateAuftrag = { ...this.auftrag };
    updateAuftrag.bemerkung = this.bemerkung;

    const vorgang: AuftragVorgangStatus = await this.updateBemerkung(updateAuftrag);
    if (vorgang.status > 2) {
      // Error Case
      this.ctx.addErrorString('Fehler!! TODO ErrorMessage');
    } else {
      this.ctx.addInfoString(`Bemerkungstext der PNR ${updateAuftrag.pnr} von '${this.auftrag.bemerkung}' nach '${updateAuftrag.bemerkung}' geändert!`);
      // this.auftrag.bemerkung = this.bemerkung;
      this.auftrag.version = vorgang.auftrag.version; // update data from rest state TODO check OR page refresh ???
      this.auftrag.bemerkung = vorgang.auftrag.bemerkung;
      // TODO besser refresh master Auftrag
    }

    this.dialogRef.close();
  }

  onClickCancel() {
    this.dialogRef.close();
  }

  updateBemerkung(auftrag: Auftrag) {
    return this.auftragService.editBemerkungstext(auftrag).pipe(first()).toPromise<AuftragVorgangStatus>();
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }
}
