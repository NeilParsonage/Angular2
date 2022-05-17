import { ProtocolEntry } from './protocol-entry';

export interface Protocol {
  debugString: string;
  actionForProtocol: string;
  allEntries: Array<ProtocolEntry>;
}
