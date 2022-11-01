import { Moment } from 'moment';

export interface Auftragshistorie {
  pnr: string;
  aufhId: number;
  quelle: string;
  meldkenn: string;
  aktion: string;
  sendetermin: string | Date | Moment;
  zeit: number;

  bandnr: number;
  fzgbm: string;
  ort: string;
}
