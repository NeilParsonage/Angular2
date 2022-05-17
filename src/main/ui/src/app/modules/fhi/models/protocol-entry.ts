import { ProtocolMessage } from './protocol-message';

export interface ProtocolEntry {
  protocolMessage: ProtocolMessage;
  severity: string;
  taskId: string;
  timestamp: string;
  userAcknowledged: boolean;
  messageNumber: number;
  important: boolean;
}
