import { Protocol } from './protocol';
import { Sendung } from './sendung';

export interface SendungResponse {
  sendung: Sendung;
  errorMsgs: Array<String>;
  protocol?: Protocol;
}
