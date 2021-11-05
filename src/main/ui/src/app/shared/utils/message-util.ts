import { HttpErrorResponse } from '@angular/common/http';
import { MessageType } from '../models/message-type';
import { UiMessage } from '../models/ui-message';

export class MessageUtil {
  static createFreieEinschreibenMsg(msg: string): UiMessage {
    return {
      message: { text: msg, type: MessageType.FREIE_EINSCHREIBEN },
      cssClass: 'msgFreieEinschreiben',
      showDuration: 5000 * 1000,
    };
  }

  static createInfoMsg(msg: string): UiMessage {
    return {
      message: { text: msg, type: MessageType.INFO },
      cssClass: 'msgInfo',
      showDuration: 5 * 1000,
    };
  }

  static createErrorMsg(msg: string): UiMessage {
    return {
      message: { text: msg, type: MessageType.ERROR },
      cssClass: 'msgError',
      showDuration: 1000 * 1000,
    };
  }

  static createErrorMsgByResponse(error: HttpErrorResponse): UiMessage {
    if (!!error.error.error) {
      MessageUtil.createErrorMsg(`${error.status} - ${error.error}`);
      return this.createErrorMsg(`${error.status} - ${error.error.error}`);
    }
    return this.createErrorMsg(`${error.status} - ${error.error}`);
  }
}
