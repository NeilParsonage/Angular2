import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { first } from 'rxjs/internal/operators/first';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ConfirmDialogOptions } from 'src/app/shared/models/confirm-dialog-options';
import { SendeTyp } from '../../models/sendeTyp';
import { Sendung } from '../../models/sendung';
import { AuftragService } from '../../services/auftrag.service';

@Component({
  selector: 'app-sendemaske',
  templateUrl: './sendemaske.component.html',
  styleUrls: ['./sendemaske.component.scss'],
})
export class SendemaskeComponent {
  selectedPnr: string;

  constructor(public dialog: MatDialog, private auftragService: AuftragService) {}

  async sendung() {
    const sendData: Sendung = {
      pnr: this.selectedPnr,
      version: 0,
      sendeTyp: SendeTyp.Komplett,
    };
    const result = await this.doSendung(sendData);
    this.openConfirmDialogSendung(JSON.stringify(result, null, 4));
  }

  private doSendung(sendData: Sendung) {
    return this.auftragService.sendung(sendData).pipe(first()).toPromise();
  }

  private openConfirmDialogSendung(pMessage: string) {
    const dialogOptions: ConfirmDialogOptions = {
      title: `Sendung der PNR '${this.selectedPnr}' :`,
      message: pMessage,
      buttonIconConfirm: 'ok',
      buttonTextConfirm: 'Ok',
      buttonIconAbort: 'cancel',
      buttonTextAbort: 'Abbrechen',
      onConfirm: () => {},
      onAbort: () => {},
    };
    this.dialog.open(ConfirmDialogComponent, {
      data: dialogOptions,
      disableClose: true,
    });
  }
}
