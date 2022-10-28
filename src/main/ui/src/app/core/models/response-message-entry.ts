import { ResponseMessageType } from './response-message-type';

export interface ResponseMessageEntry {
  tuebKey: string;
  parameter: string[];
  meldung: string;
  typ: ResponseMessageType;
}
