import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { first } from 'rxjs/internal/operators/first';
import { Auftrag } from '../../models/auftrag';
import { Protocol } from '../../models/protocol';
import { ProtocolEntry } from '../../models/protocol-entry';
import { SendeTyp } from '../../models/sendeTyp';
import { Sendung } from '../../models/sendung';
import { SendungResponse } from '../../models/sendungResponse';
import { AuftragService } from '../../services/auftrag.service';
import { UserConfirmDialogOptions } from '../user-confirm-dialog/user-confirm-dialog-options';
import { UserConfirmDialogComponent } from '../user-confirm-dialog/user-confirm-dialog.component';

@Component({
  selector: 'app-sendemaske',
  templateUrl: './sendemaske.component.html',
  styleUrls: ['./sendemaske.component.scss'],
})
export class SendemaskeComponent {
  selectedPnr: string;
  selectedSendung: SendeTyp;
  responseMessage: string;

  options = [
    { value: SendeTyp.Komplett, viewValue: 'Komplettsendung' },
    { value: SendeTyp.TeilSendung_FHI, viewValue: 'Teilsendung FHI' },
    { value: SendeTyp.TeilSendung_LMT, viewValue: 'Teilsendung LMT' },
    { value: SendeTyp.TeilSendung_RHM, viewValue: 'Teilsendung RHM' },
    { value: SendeTyp.TeilSendung_UBM, viewValue: 'Teilsendung UBM' },
  ];

  constructor(public dialog: MatDialog, private auftragService: AuftragService) {}

  async sendung() {
    const auftrag: Auftrag = await this.loadSendung(this.selectedPnr);
    const sendData: Sendung = {
      pnr: auftrag.pnr,
      version: auftrag.version,
      sendeTyp: this.selectedSendung,
    };
    const result: SendungResponse = await this.doSendung(sendData);
    const protocol = result.protocol;
    // this.openConfirmDialogSendung(JSON.stringify(result, null, 4));
    this.responseMessage = JSON.stringify(result, null, 4);
    this.openConfirmDialogSendung(protocol);
  }

  private loadSendung(pnr: string) {
    return this.auftragService.getAuftragByPnr(pnr).pipe(first()).toPromise();
  }

  private doSendung(sendData: Sendung) {
    return this.auftragService.sendung(sendData).pipe(first()).toPromise();
  }

  private openConfirmDialogSendung(pProtocol: Protocol) {
    const protocolEntries = this.getProtocolEntries(pProtocol.allEntries);
    console.log('protocolEntries', protocolEntries);
    if (protocolEntries.length < 1) {
      return; // no messages
    }

    const dialogOptions: UserConfirmDialogOptions = {
      title: `Sendung der PNR '${this.selectedPnr}' // ${pProtocol.actionForProtocol} :`,
      protocolEntries: protocolEntries,
      errorMode: this.getErrorMode(protocolEntries),
      buttonIconConfirm: 'ok',
      buttonTextConfirm: 'Ok',
      buttonIconAbort: 'cancel',
      buttonTextAbort: 'Abbrechen',
      onConfirm: () => {},
      onAbort: () => {},
    };
    this.dialog.open(UserConfirmDialogComponent, {
      data: dialogOptions,
      disableClose: true,
    });
  }

  getProtocolEntries(allEntries: ProtocolEntry[]): ProtocolEntry[] {
    const errorCases = Array<ProtocolEntry>();
    const warnCases = Array<ProtocolEntry>();
    allEntries.forEach(e => {
      if (e.severity === 'ERROR') {
        errorCases.push(e);
        return;
      }
      if (e.severity === 'WARN') {
        warnCases.push(e);
      }
    });
    if (errorCases.length > 0) {
      return errorCases;
    }
    return warnCases;
  }

  getErrorMode(allEntries: ProtocolEntry[]): boolean {
    if (allEntries.find(e => e.severity === 'ERROR')) {
      return true;
    }
    return false;
  }
}
