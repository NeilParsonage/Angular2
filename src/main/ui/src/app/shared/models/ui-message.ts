import { Message } from './message';

export interface UiMessage {
  timestamp?: Date;
  showDuration: number;
  cssClass: string;
  message: Message;
}
