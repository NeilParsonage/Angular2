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
  selectedSendung: SendeTyp;

  options = [
    { value: SendeTyp.Komplett, viewValue: 'Komplettsendung' },
    { value: SendeTyp.TeilSendung_FHI, viewValue: 'Teilsendung FHI' },
    { value: SendeTyp.TeilSendung_LMT, viewValue: 'Teilsendung LMT' },
    { value: SendeTyp.TeilSendung_RHM, viewValue: 'Teilsendung RHM' },
    { value: SendeTyp.TeilSendung_UBM, viewValue: 'Teilsendung UBM' },
  ];

  constructor(public dialog: MatDialog, private auftragService: AuftragService) {}

  async sendung() {
    const sendData: Sendung = {
      pnr: this.selectedPnr,
      version: 0,
      sendeTyp: this.selectedSendung,
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
