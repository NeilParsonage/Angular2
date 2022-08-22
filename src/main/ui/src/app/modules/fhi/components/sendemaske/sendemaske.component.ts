import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { first } from 'rxjs/internal/operators/first';
import { Auftrag } from '../../models/auftrag';
import { Protocol } from '../../models/protocol';
import { ProtocolEntry } from '../../models/protocol-entry';
import { ProtocolSeverity } from '../../models/protocol-severity';
import { SendeTyp } from '../../models/sendeTyp';
import { Sendung } from '../../models/sendung';
import { SendungResponse } from '../../models/sendungResponse';
import { AuftragService } from '../../services/auftrag.service';
import { UserConfirmDialogOptions } from '../user-confirm-dialog/user-confirm-dialog-options';
import { UserConfirmDialogComponent } from '../user-confirm-dialog/user-confirm-dialog.component';

import { Component, OnInit } from '@angular/core';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He' },
  { position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li' },
  { position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be' },
  { position: 5, name: 'Boron', weight: 10.811, symbol: 'B' },
  { position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C' },
  { position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N' },
  { position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O' },
  { position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F' },
  { position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne' },
];

@Component({
  selector: 'app-sendemaske',
  templateUrl: './sendemaske.component.html',
  styleUrls: ['./sendemaske.component.scss'],
})
export class SendemaskeComponent implements OnInit {
  displayedColumnsTop: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSourceTop = ELEMENT_DATA;

  displayedColumnsDown: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSourceDown = ELEMENT_DATA;

  constructor() {}

  ngOnInit(): void {}

  getProtocolEntries(allEntries: ProtocolEntry[]): ProtocolEntry[] {
    const errorCases = Array<ProtocolEntry>();
    const warnCases = Array<ProtocolEntry>();
    allEntries.forEach(e => {
      if (e.severity === ProtocolSeverity.Error || e.severity === ProtocolSeverity.Fatal) {
        errorCases.push(e);
        return errorCases;
      }
      if (e.severity === ProtocolSeverity.Warning) {
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
    return entries[0].severity === ProtocolSeverity.Error || entries[0].severity === ProtocolSeverity.Fatal;
  }

  getErrorMode(allEntries: ProtocolEntry[]): boolean {
    if (allEntries.find(e => e.severity === ProtocolSeverity.Error || e.severity === ProtocolSeverity.Fatal)) {
      return true;
    }
    return false;
  }

}
