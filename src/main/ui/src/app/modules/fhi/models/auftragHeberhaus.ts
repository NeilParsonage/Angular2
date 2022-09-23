export interface AuftragHeberhaus {
  pnr: string;
  bandNr: number;
  fertigungspunkt: number;
  terminHeberhaus: string | Date;
  terminHeberhausTyp?: string;
  istPnr?: string;
  istLfdNrLmt?: number;
  istTermin: string | Date;
}
