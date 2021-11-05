import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConfirmationPopupButton } from '../../models/confirmation-popup-button';
import { ConfirmationPopupConfig } from '../../models/confirmation-popup-config';

@Component({
  selector: 'app-confirmation-popup',
  templateUrl: './confirmation-popup.component.html',
  styleUrls: ['./confirmation-popup.component.scss'],
})
export class ConfirmationPopupComponent {
  title: string;
  text: string;
  buttons: Array<ConfirmationPopupButton>;

  constructor(private dialogRef: MatDialogRef<ConfirmationPopupComponent>, @Inject(MAT_DIALOG_DATA) public configValues: ConfirmationPopupConfig) {
    this.title = configValues.title;
    this.text = configValues.text;
    this.buttons = configValues.buttons;
  }

  actionButton(button: ConfirmationPopupButton): void {
    this.dialogRef.close(button.id);
  }
}
