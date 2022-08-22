import { Component, OnInit } from '@angular/core';
import { ProtocolEntry } from '../../models/protocol-entry';
import { ProtocolSeverity } from '../../models/protocol-severity';

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
