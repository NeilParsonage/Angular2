import { Auftrag } from './auftrag';

export interface AuftragVorgangStatus {
  vorgangId: number;
  status: number;
  auftrag?: Auftrag;
}
