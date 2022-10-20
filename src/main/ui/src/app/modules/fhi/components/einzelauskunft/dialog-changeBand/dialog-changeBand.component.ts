import { DragRef, Point } from '@angular/cdk/drag-drop';
import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogStaticHelper } from 'src/app/shared/utils/dialog-static-helper';
import { Auftrag } from '../../../models/auftrag';
import { DialogEditRemarkOptions } from './dialog-changeBand.options.component';

@Component({
  selector: 'dialog-changeBand',
  templateUrl: 'dialog-changeBand.component.html',
  styleUrls: ['dialog-changeBand.component.scss'],
})
export class DialogChangeBandComponent implements OnInit {
  auftrag: Auftrag = null;
  titel: string = null;

  constructor(
    @Inject(MAT_DIALOG_DATA) public options: DialogEditRemarkOptions,
    public datepipe: DatePipe,
    public dialogRef: MatDialogRef<DialogChangeBandComponent>
  ) {}

  ngOnInit(): void {
    this.auftrag = this.options.auftrag;

    this.titel = this.options.titel;
  }

  onClickOk() {
    this.dialogRef.close();
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }
}
