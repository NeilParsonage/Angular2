export interface Auftrag {
  pnr: string;
  lfdNrGes: number;
  lfdNrFhi: number;
  lfdNrLmt: number;
  lfdNrUbm?: number;
  bandNr: number;
  bandNrRt2: number;
  bandNrRt5: number;
  fzgArt: string;
  fhsBaumuster: string;
  fzgBaumuster: string;
  fhsTaktklasse: string;
  fzgTaktklasse: string;
  anr: string;
  verkBez: string;
}
