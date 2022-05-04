import { DragRef, Point } from '@angular/cdk/drag-drop';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConfirmDialogOptions } from '../../models/confirm-dialog-options';
import { DialogStaticHelper } from '../../utils/dialog-static-helper';
import { ConfirmDialogChoice } from './confirm-dialog-choice';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss'],
})
export class ConfirmDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public options: ConfirmDialogOptions) {}

  // [cdkDragConstrainPosition]="constrainPosition" not supported

  ngOnInit(): void {
    this.dialogRef.afterClosed().subscribe(result => {
      if (result === ConfirmDialogChoice.CONFIRM) {
        this.options.onConfirm();
        return;
      }
      this.options.onAbort();
    });
  }

  onChoiceConfirm(): void {
    this.dialogRef.close(ConfirmDialogChoice.CONFIRM);
  }

  onChoiceAbort(): void {
    this.dialogRef.close(ConfirmDialogChoice.ABORT);
  }

  getButtonIconConfirm(): string {
    return this.options.buttonIconConfirm ? this.options.buttonIconConfirm : null;
  }

  getButtonTextConfirm(): string {
    return this.options.buttonTextConfirm ? this.options.buttonTextConfirm : ConfirmDialogChoice.DEFAULT_BUTTON_TEXT_CONFIRM;
  }

  getButtonIconAbort(): string {
    return this.options.buttonIconAbort ? this.options.buttonIconAbort : null;
  }

  getButtonTextAbort(): string {
    return this.options.buttonTextAbort ? this.options.buttonTextAbort : ConfirmDialogChoice.DEFAULT_BUTTON_TEXT_ABORT;
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }
}
