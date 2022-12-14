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
  fin: string;
  vin: string;
  /* Details*/
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
  /* Sendestatus*/
  zielLapu?: string;
  zielSepu?: string;
  skidNr: number;
  seqnrSepu?: number;
  seqnrLapu?: number;
  ort: string;
  hrknr: string;
  fhiSendStatus: string;
  fhisendung: string;
  rhmSendStatus: string;
  rhmsendung: string;
  lmtSendStatus: string;
  lmtsendung: string;
  ubmSendStatus: string;
  ubmsendung: string;
  inWarteschlange01: number;
  inWarteschlangeTyp: string;
  inWarteschlangePos: number;
  anzahlAnkuendigungen: number;
  anzahlSperren: number;
  fpLmt: string;
  /*Fp_Lmt_Datum   */
  fpLmtBenennung: string;
  fpFhs: string;

  /*Fp_Fhs_Datum  */
  fpfhsBenennung: string;
  fpRhm: string;

  /*Fp_Rhm_Datum  */
  fpRhmBenennung: string;
  sendbar: string;
  zugebunden: string;
  /* Audit*/

  hrkAudit: string;
  fhiAudit: string;
  version: number;
}
