import { ConfirmationPopupButton } from './confirmation-popup-button';

export interface ConfirmationPopupConfig {
  title: string;
  text: string;
  buttons: Array<ConfirmationPopupButton>;
}
