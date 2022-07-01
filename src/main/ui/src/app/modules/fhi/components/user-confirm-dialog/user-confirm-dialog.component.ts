import { DragRef, Point } from '@angular/cdk/drag-drop';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogStaticHelper } from '../../../../shared/utils/dialog-static-helper';
import { ProtocolEntry } from '../../models/protocol-entry';
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
  selectedOptions: string[] = [];
  oldSelectedOptions: string[] = [];

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
    return this.options.buttonIconConfirm ? this.options.buttonIconConfirm : UserConfirmDialogChoice.DEFAULT_BUTTON_ICON_CONFIRM;
  }

  getButtonTextConfirm(): string {
    return this.options.buttonTextConfirm ? this.options.buttonTextConfirm : UserConfirmDialogChoice.DEFAULT_BUTTON_TEXT_CONFIRM;
  }

  getButtonIconAbort(): string {
    return this.options.buttonIconAbort ? this.options.buttonIconAbort : UserConfirmDialogChoice.DEFAULT_BUTTON_ICON_ABORT;
  }

  getButtonTextAbort(): string {
    return this.options.buttonTextAbort ? this.options.buttonTextAbort : UserConfirmDialogChoice.DEFAULT_BUTTON_TEXT_ABORT;
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }

  onSelectedOptionChange(event: string[]) {
    console.log(`onSelectedOptionChange : new ${event} / old ${this.oldSelectedOptions} `);
    let caseDeleted: string[] = this.oldSelectedOptions.filter(e => event.indexOf(e) < 0);
    if (caseDeleted && caseDeleted.length > 0) {
      console.log('not selected : ', caseDeleted[0]);
      this.updateAcknowledged(caseDeleted[0], false);
    } else {
      const caseAdded: string[] = event.filter(e => this.oldSelectedOptions.indexOf(e) < 0);
      if (caseAdded) {
        console.log('selected : ', caseAdded[0]);
        this.updateAcknowledged(caseAdded[0], true);
      }
    }
    this.oldSelectedOptions = event;
  }

  updateAcknowledged(taskId: string, userAcknowledged: boolean) {
    const entries: ProtocolEntry[] = this.options.protocolEntries.filter(e => e.taskId === taskId);
    entries.forEach(e => {
      e.userAcknowledged = userAcknowledged;
    });
  }
}
