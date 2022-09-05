import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SendemaskeUngesendetService {
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

  constructor() {}

  generateTableColumnsUngesendet(sizeSternenhimmel: number) {
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

    entry['ortFhi'] = 'ZO_4';
    entry['ortRhm'] = 'EING';

    entry['ladispo'] = '24.05. 14:15';
    entry['plantermFhi'] = '24.05. 14:15';
    entry['plantermRhm'] = '24.05. 14:15';

    entry['sendStatusFhi'] = '0';
    entry['sendStatusRhm'] = '0';
    entry['sendStatusLmt'] = '0';

    entry['sollFhi'] = '1';
    entry['sollLmt'] = '1';

    entry['spFhi'] = '0';
    entry['spLmt'] = '0';
    entry['spSonst'] = '0';

    entry['an'] = '0';

    // dynamic part
    for (let i = 1; i <= sizeSternenhimmel; i++) {
      entry[String.fromCharCode(64 + i)] = '*';
    }

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
}
