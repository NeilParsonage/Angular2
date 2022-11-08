import { Auftragshistorie } from './auftragshistorie';

export interface AuftragshistoriePage {
  content: Auftragshistorie[];
  totalElements: number;
  // eslint-disable-next-line id-blacklist
  number: number;
  size: number;
}
