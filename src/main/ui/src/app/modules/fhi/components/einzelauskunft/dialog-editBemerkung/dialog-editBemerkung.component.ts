import { DragRef, Point } from '@angular/cdk/drag-drop';
import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { first } from 'rxjs/operators';
import { DialogStaticHelper } from 'src/app/shared/utils/dialog-static-helper';
import { Auftrag } from '../../../models/auftrag';
import { Dummy } from '../../../models/Dummy';
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
  auftragService: AuftragService;

  constructor(
    @Inject(MAT_DIALOG_DATA) public options: DialogEditRemarkOptions,
    public datepipe: DatePipe,
    public dialogRef: MatDialogRef<DialogEditBemerkungComponent>
  ) {}

  ngOnInit(): void {
    this.auftrag = this.options.auftrag;

    this.titel = this.options.titel;
  }

  async onClickOk() {
    await this.updateBemerkung(this.auftrag);

    this.dialogRef.close();
  }

  onClickCancel() {
    this.dialogRef.close();
  }

  updateBemerkung(auftrag: Auftrag) {
    return this.auftragService.editBemerkungstext(auftrag).pipe(first()).toPromise<Dummy>();
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }
}
