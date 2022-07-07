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
  constructor(public dialogRef: MatDialogRef<UserConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public options: UserConfirmDialogOptions) {
    this.initData();
  }

  selectedOptions: ProtocolEntry[] = [];
  oldSelectedOptions: ProtocolEntry[] = [];

  reducedProtocolEntries: Array<ProtocolEntry> = []; // reduced by tuebId + parameters

  ngOnInit(): void {
    this.dialogRef.afterClosed().subscribe(result => {
      if (result === UserConfirmDialogChoice.CONFIRM) {
        this.options.onConfirm();
        return;
      }
      this.options.onAbort();
    });
  }

  private initData() {
    const tmpReducedProtocolEntries: Array<ProtocolEntry> = [];
    console.log('initData data', this.options.protocolEntries);
    this.options.protocolEntries.forEach(e => {
      // if (false === this.existsUiProtocolMessage(tmpReducedProtocolEntries, e)) {
      if (false === this.existsMessage(tmpReducedProtocolEntries, e)) {
        if (e.userAcknowledged) {
          this.selectedOptions.push(e);
        }
        tmpReducedProtocolEntries.push(e);
      }
    });
    this.reducedProtocolEntries = tmpReducedProtocolEntries;
    console.log('initData', this.reducedProtocolEntries);
  }

  /*
  private existsUiProtocolMessage(tmpReducedProtocolEntries: Array<ProtocolEntry>, entry: ProtocolEntry): boolean {
    const result: ProtocolEntry[] = tmpReducedProtocolEntries.filter(e => this.isSameMessage(e, entry));
    if (result && result.length > 0) {
      return true;
    }
    return false;
  } */

  isSameMessage(a: ProtocolEntry, b: ProtocolEntry): boolean {
    if (!a || !b) {
      console.log('isSameMessage undefined case', a, b);
      return false;
    }
    const result: boolean =
      a.protocolMessage.tuebKey === b.protocolMessage.tuebKey && JSON.stringify(a.protocolMessage.parameter) === JSON.stringify(b.protocolMessage.parameter);
    console.log('isSameMessage ', result, a, b);
    return result;
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

  onSelectedOptionChange(entries: ProtocolEntry[]) {
    this.updateAllAcknowledged(entries);
    // console.log('onSelectedOptionChange : new vs. old ', entries, this.oldSelectedOptions);
    // let caseDeleted: ProtocolEntry[] = this.oldSelectedOptions.filter(e => this.notExistsMesssage(entries, e));
    // if (caseDeleted && caseDeleted.length > 0) {
    //   console.log('onSelectedOptionChange - deselected : ', caseDeleted[0]);
    //   this.updateAcknowledged(caseDeleted[0], false);
    // } else {
    //   const caseAdded: ProtocolEntry[] = entries.filter(e => this.notExistsMesssage(this.oldSelectedOptions, e));
    //   if (caseAdded) {
    //     console.log('onSelectedOptionChange - selected : ', caseAdded[0]);
    //     this.updateAcknowledged(caseAdded[0], true);
    //   }
    // }
    // this.oldSelectedOptions = [...entries];
  }

  private notExistsMesssage(entries: ProtocolEntry[], entry: ProtocolEntry): boolean {
    return false === this.existsMessage(entries, entry);
  }

  private existsMessage(entries: ProtocolEntry[], entry: ProtocolEntry): boolean {
    const result = entries.filter(e => this.isSameMessage(e, entry));
    if (result && result.length > 0) {
      return true;
    }
    return false;
  }

  // DEPRECATED
  // updateAcknowledged(entry: ProtocolEntry, userAcknowledged: boolean) {
  //   const entries: ProtocolEntry[] = this.options.protocolEntries.filter(e => this.isSameMessage(entry, e));
  //   entries.forEach(e => {
  //     e.userAcknowledged = userAcknowledged;
  //     console.log('change userAcknowledged : state | entry', userAcknowledged, e);
  //   });
  //   console.log('updateAcknowledged', this.options.protocolEntries);
  // }

  updateAllAcknowledged(selectedEntries: ProtocolEntry[]) {
    this.options.protocolEntries.forEach(e => {
      const isSelected = this.existsMessage(selectedEntries, e);
      e.userAcknowledged = isSelected;
    });
  }
}
