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
  templateUrl: './sendemaske-debug.component.html',
  styleUrls: ['./sendemaske-debug.component.scss'],
})
export class SendemaskeDebugComponent {
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

  async sendung(initProtocol?: Protocol) {
    const auftrag: Auftrag = await this.loadSendung(this.selectedPnr);
    const sendData: Sendung = {
      pnr: auftrag.pnr,
      version: auftrag.version,
      sendeTyp: this.selectedSendung,
    };
    let result: SendungResponse = null;
    if (!initProtocol) {
      result = await this.doSendung(sendData);
    } else {
      console.log('sendung', initProtocol);
      sendData.protocol = initProtocol;
      result = await this.doSendungWithProtocol(sendData);
    }
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

  private doSendungWithProtocol(sendData: Sendung) {
    return this.auftragService.sendungWithProtocol(sendData).pipe(first()).toPromise();
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
      buttonIconConfirm: 'done',
      buttonTextConfirm: 'Ok',
      buttonIconAbort: 'cancel',
      buttonTextAbort: 'Abbrechen',
      onConfirm: () => {
        if (!this.isProtocolWithErrors(protocolEntries)) {
          this.sendung(pProtocol); // resend
        }
      },
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
        return errorCases;
      }
      if (e.severity === 'WARNING') {
        warnCases.push(e);
      }
    });
    if (errorCases.length > 0) {
      return errorCases;
    }
    return warnCases;
  }

  isProtocolWithErrors(entries: ProtocolEntry[]) {
    if (!entries || entries.length < 1) {
      return false;
    }
    return entries[0].severity === 'ERROR';
  }

  getErrorMode(allEntries: ProtocolEntry[]): boolean {
    if (allEntries.find(e => e.severity === 'ERROR')) {
      return true;
    }
    return false;
  }
}
