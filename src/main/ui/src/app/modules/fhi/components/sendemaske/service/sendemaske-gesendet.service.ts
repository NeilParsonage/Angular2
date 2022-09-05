import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SendemaskeGesendetService {
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

  constructor() {}

  generateTableColumnsGesendet(sizeSternenhimmel: number) {
    // dynamische Spalten
    //   o Sternenhimmel 1..n Spalten
    //   o Ort (F - FHI | R - RHM) 1 Spalte (Umschaltbar)
    let entry: any = {};
    entry['band'] = 2;
    entry['pnr'] = '13641290';
    entry['tkl'] = 'H941 H424';
    entry['fzgbm'] = '964.026-12';

    // dynamic part
    for (let i = 1; i <= sizeSternenhimmel; i++) {
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
}
