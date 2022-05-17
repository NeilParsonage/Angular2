import { ProtocolEntry } from '../../models/protocol-entry';

export interface UserConfirmDialogOptions {
  title: string;
  errorMode: boolean;
  protocolEntries?: Array<ProtocolEntry>;
  buttonIconConfirm?: string;
  buttonTextConfirm?: string;
  buttonIconAbort?: string;
  buttonTextAbort?: string;
  onConfirm: () => void;
  onAbort: () => void;
}
