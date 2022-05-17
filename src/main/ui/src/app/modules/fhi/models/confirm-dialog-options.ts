export interface ConfirmDialogOptions {
  title: string;
  message?: string;
  buttonIconConfirm?: string;
  buttonTextConfirm?: string;
  buttonIconAbort?: string;
  buttonTextAbort?: string;
  onConfirm: () => void;
  onAbort: () => void;
}
