import { DragRef, Point } from '@angular/cdk/drag-drop';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogStaticHelper } from '../../../../shared/utils/dialog-static-helper';
import { UserConfirmDialogChoice } from './user-confirm-dialog-choice';
import { UserConfirmDialogOptions } from './user-confirm-dialog-options';

@Component({
  selector: 'app-user-confirm-dialog',
  templateUrl: './user-confirm-dialog.component.html',
  styleUrls: ['./user-confirm-dialog.component.scss'],
})
export class UserConfirmDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<UserConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public options: UserConfirmDialogOptions) {}

  // [cdkDragConstrainPosition]="constrainPosition" not supported

  ngOnInit(): void {
    this.dialogRef.afterClosed().subscribe(result => {
      if (result === UserConfirmDialogChoice.CONFIRM) {
        this.options.onConfirm();
        return;
      }
      this.options.onAbort();
    });
  }

  onChoiceConfirm(): void {
    this.dialogRef.close(UserConfirmDialogChoice.CONFIRM);
  }

  onChoiceAbort(): void {
    this.dialogRef.close(UserConfirmDialogChoice.ABORT);
  }

  getButtonIconConfirm(): string {
    return this.options.buttonIconConfirm ? this.options.buttonIconConfirm : null;
  }

  getButtonTextConfirm(): string {
    return this.options.buttonTextConfirm ? this.options.buttonTextConfirm : UserConfirmDialogChoice.DEFAULT_BUTTON_TEXT_CONFIRM;
  }

  getButtonIconAbort(): string {
    return this.options.buttonIconAbort ? this.options.buttonIconAbort : null;
  }

  getButtonTextAbort(): string {
    return this.options.buttonTextAbort ? this.options.buttonTextAbort : UserConfirmDialogChoice.DEFAULT_BUTTON_TEXT_ABORT;
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }
}
