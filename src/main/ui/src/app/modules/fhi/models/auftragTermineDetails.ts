export interface AuftragTermineDetails {
  pnr: string;
  reihenfolge: number;
  gewerk: string;
  beginnTermin: string | Date;
  beginnTyp: string;
  istSequenzTermin: string | Date;
  istSequenzTyp: string;
  teilsendungTermin: string | Date;
  stornoTermin: string | Date;
}
