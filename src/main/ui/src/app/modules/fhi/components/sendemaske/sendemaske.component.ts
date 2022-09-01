import { Component, OnInit } from '@angular/core';
import { DaiTableConfig } from 'emst-table';
import { ProtocolEntry } from '../../models/protocol-entry';
import { ProtocolSeverity } from '../../models/protocol-severity';

// const ELEMENT_DATA: PeriodicElement[] = [{ position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' }];
@Component({
  selector: 'app-sendemaske',
  templateUrl: './sendemaske.component.html',
  styleUrls: ['./sendemaske.component.scss'],
})
export class SendemaskeComponent implements OnInit {
  configColumnGesendet = {
    band: {
      name: 'Bd',
      sortable: false,
    },
    pnr: {
      name: 'PNR',
      sortable: false,
    },
    tkl: {
      name: 'TKL',
      sortable: false,
    },
    fzgbm: {
      name: 'FZGBM',
      sortable: false,
    },
    ortFhi: {
      name: 'F',
      sortable: false,
    },
    ortRhm: {
      name: 'R',
      sortable: false,
    },
    sendetermin: {
      name: 'Sendetermin',
      sortable: false,
    },
    gesLfdNr: {
      name: 'Ges. lfdNr',
      sortable: false,
    },
    istLfdFhi: {
      name: 'Ist lfd. FHI',
      sortable: false,
    },
    istLfdRhm: {
      name: 'Ist lfd. RHM',
      sortable: false,
    },
    istLfdLmt: {
      name: 'Ist lfd. LMT',
      sortable: false,
    },
    sendStatusFhi: {
      name: 'K-I FHI',
      sortable: false,
    },
    sendStatusRhm: {
      name: 'K-I RHM',
      sortable: false,
    },
    sendStatusLmt: {
      name: 'K-I LMT',
      sortable: false,
    },
    pat: {
      name: 'PAT',
      sortable: false,
    },
    sat: {
      name: 'SAT',
      sortable: false,
    },
    sollRfGesamt: {
      name: 'Soll Rf-G',
      sortable: false,
    },
    sollRfBand: {
      name: 'Soll Rf-Bd',
      sortable: false,
    },
    istTermin: {
      name: 'Isttermin',
    },
  };

  configColumnUngesendet = {
    band: {
      name: 'Bd',
      sortable: true,
    },
    pnr: {
      name: 'PNR',
      sortable: false,
    },
    tkl: {
      name: 'TKL / Länge',
      sortable: false,
    },
    fzgbm: {
      name: 'FZGBM',
      sortable: false,
    },
    ortFhi: {
      name: 'F',
      sortable: false,
    },
    ortRhm: {
      name: 'R',
      sortable: false,
    },
    ladispo: {
      name: 'LADISPO',
      sortable: false,
    },
    plantermFhi: {
      name: 'Plan T.-FHI',
      sortable: false,
    },
    plantermRhm: {
      name: 'Plan T.-RHM',
      sortable: false,
    },
    spFhi: {
      name: 'Sp Fhi',
      sortable: false,
    },
    spLmt: {
      name: 'Sp Lmt',
      sortable: false,
    },
    spSonst: {
      name: 'Sp Sonst',
      sortable: false,
    },
    an: {
      name: 'An',
      sortable: false,
    },
    sendStatusFhi: {
      name: 'K-I FHI',
      sortable: false,
    },
    sendStatusRhm: {
      name: 'K-I RHM',
      sortable: false,
    },
    sendStatusLmt: {
      name: 'K-I LMT',
      sortable: false,
    },
    sollFhi: {
      name: 'Soll FHI',
      sortable: false,
    },
    sollLmt: {
      name: 'Soll LMT',
      sortable: false,
    },
    pat: {
      name: 'PAT',
      sortable: false,
    },
    sat: {
      name: 'SAT',
      sortable: false,
    },
    sollRfG: {
      name: 'Soll Rf-G',
      sortable: false,
    },
    sollRfBand: {
      name: 'Soll Rf-Bd',
      sortable: false,
    },
  };

  // private dataSourceTop = [{ band: 2, pnr: '13641290', tkl: 'H941 H424', sternenhimmel: 'x' }];
  private dataSourceTop = this.generateTableColumnsGesendet();
  private dataSourceDown = this.generateTableColumnsUngesendet();

  daiTableConfigTop: DaiTableConfig = new DaiTableConfig(this.dataSourceTop, this.generateDisplayedColumnsGesendet(this.dataSourceTop[0]));

  daiTableConfigBottom: DaiTableConfig = new DaiTableConfig(this.dataSourceDown, this.generateDisplayedColumnsUngesendet(this.dataSourceDown[0]));

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

  generateTableColumnsGesendet() {
    // dynamische Spalten
    //   o Sternenhimmel 1..n Spalten
    //   o Ort (F - FHI | R - RHM) 1 Spalte (Umschaltbar)
    let entry: any = {};
    entry['band'] = 2;
    entry['pnr'] = '13641290';
    entry['tkl'] = 'H941 H424';
    entry['fzgbm'] = '964.026-12';

    // dynamic part
    for (let i = 1; i <= this.sizeSternenhimmel(); i++) {
      entry[String.fromCharCode(64 + i)] = '*';
    }

    entry.ortFhi = '611';
    entry.ortRhm = 'W060';
    entry.sendetermin = '24.05. 21:20';
    entry.gesLfdNr = '6078';
    entry.istLfdFhi = '601';
    entry.istLfdRhm = '639';
    entry.istLfdLmt = '671';
    entry.sendStatusFhi = 'I';
    entry.sendStatusRhm = 'P';
    entry.sendStatusLmt = 'P';
    entry.pat = '22152';
    entry.sat = '22152';
    entry.sollRfGesamt = '189';
    entry.sollRfBand = '60';
    entry.istTermin = '24.05. 21:20';

    let data = [];
    data.push(entry);

    return data;
  }

  generateDisplayedColumnsGesendet(entryExample: Object) {
    let columns: any = {};

    let k: keyof Object;
    for (k in entryExample) {
      // const v = entryExample[k]; // OK
      const cfg: any = this.configColumnGesendet[k];

      columns[k] = {};
      columns[k].name = cfg ? cfg.name : k;
      columns[k].sortable = cfg ? cfg.sortable : false;
    }

    return columns;
  }

  generateTableColumnsUngesendet() {
    // dynamische Spalten
    //   o TKL / Länge | FZGBM  1 Spalte (Umschaltbar)
    //   o Sternenhimmel 1..n Spalten
    //   o Ort (F - FHI | R - RHM) 1 Spalte (Umschaltbar)
    //   o Plan T-FHI | Plan T-RHM | LADISPO  1 Spalte (Umschaltbar)
    //   o PAT | SAT 1 Spalte (Umschaltbar)
    let entry: any = {};
    entry['band'] = 2;
    entry['pnr'] = '13641290';
    entry['tkl'] = 'H941 H424';
    entry['fzgbm'] = '963.424-22';

    // dynamic part
    for (let i = 1; i <= this.sizeSternenhimmel(); i++) {
      entry[String.fromCharCode(64 + i)] = '*';
    }

    entry['ortFhi'] = 'ZO_4';
    entry['ortRhm'] = 'EING';
    entry['ladispo'] = '24.05. 14:15';
    entry['plantermFhi'] = '24.05. 14:15';
    entry['plantermRhm'] = '24.05. 14:15';
    entry['spFhi'] = '0';
    entry['spLmt'] = '0';
    entry['spSonst'] = '0';

    entry['an'] = '0';
    entry['sendStatusFhi'] = '0';
    entry['sendStatusRhm'] = '0';
    entry['sendStatusLmt'] = '0';

    entry['sollFhi'] = '1';
    entry['sollLmt'] = '1';

    entry['pat'] = '22152';
    entry['sat'] = '22152';

    entry['sollRfG'] = '318';
    entry['sollRfBand'] = '100';

    let data = [];
    data.push(entry);

    return data;
  }

  generateDisplayedColumnsUngesendet(entryExample: Object) {
    let columns: any = {};

    let k: keyof Object;
    for (k in entryExample) {
      // const v = entryExample[k]; // OK
      const cfg: any = this.configColumnUngesendet[k];

      columns[k] = {};
      columns[k].name = cfg ? cfg.name : k;
      columns[k].sortable = cfg ? cfg.sortable : false;
    }

    return columns;
  }

  sizeSternenhimmel(): number {
    return 35;
  }
}
