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
  aufaenText?: string;
  bemerkung?: string;
  autor?: string;
  bemerkungAlt?: string;
  landesCode: string;
  land: string;
  gesamtLaenge: string;
  radStand: string;
  alleCodes: string;
  fhiRelCodes: string;
  bandRelCodes: string;
  alleKrits: string;
  fhiRelKrits: string;
  bandRelKrits: string;
  /*Sendestatus */
  /* Audit*/
  hrkAudit: number;
  fhiAudit: number;
}
