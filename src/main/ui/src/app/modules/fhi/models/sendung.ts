import { SendeTyp } from './sendeTyp';

export interface Sendung {
  pnr: string;
  version: number;
  sendeTyp: SendeTyp;
}
