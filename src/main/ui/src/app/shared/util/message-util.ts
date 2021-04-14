import { Message } from '../models/message';
import { MessageType } from '../models/message-type';
import { ContextService } from 'src/app/core/services/context.service';
import { UiMessage } from '../models/ui-message';

export class MessageUtil {
  static createInfoMsg(msg: string): UiMessage {
    return {
      message: { text: msg, type: MessageType.INFO },
      cssClass: 'msgInfo',
      showDuration: 4 * 1000
    };
  }

  static createErrorMsg(msg: string): UiMessage {
    return {
      message: { text: msg, type: MessageType.ERROR },
      cssClass: 'msgError',
      showDuration: 1000 * 1000
    };
  }
}
