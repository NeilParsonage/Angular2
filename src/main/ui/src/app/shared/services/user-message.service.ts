import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MessageCardStapleComponent } from '../components/message-card-staple/message-card-staple.component';
import { UiMessage } from '../models/ui-message';

/**
 * Dieser Service schickt neue Oberflächenmeldungen [UiMessage(s)] an die referenzierte Mat-Snackbar-Komponente.
 * Verarbeitungslogik :
 *  - Usability: Steuerung der Darstellungsdauer
 *    - beim Eingang einer neuen Meldung wird :
 *      - zuerst alle Nachrichten gem. deren Darstellungsdauern aussortiert (entfernt)
 *      - anschließend bleibt die Snackbar erstmal bis zur längsten Anzeigedauer innerhalb der Oberflächenmeldungen geöffnet
 * Bemerkung für die Zukunft:
 *  - Sollte die Snackbar-Komponente ausgetauscht werden, ist lediglich dieser Service
 *      für die applikationsweite Oberflächenmeldungsversorgung anzupassen.
 */
@Injectable({
  providedIn: 'root',
})
export class UserMessageService {
  private messages = new Array<UiMessage>();

  private snackBar: MatSnackBar;
  private customSnackBar;

  /**
   * Oberflächennachricht darstellen :
   * Fügt eine neue Oberflächenmeldung für die Applikation hinzu.
   * In die Snackbar-Instanz wird die Komponente 'MessageCardStapleComponent' integriert
   *
   * @param snackBar Referenz auf die Mat-Snackbar-Komponente.
   * @param uiMsg Oberflächennachrichten - UiMessage
   */
  process(snackBar: MatSnackBar, uiMsg: UiMessage) {
    this.snackBar = snackBar;
    this.addUserMessage(uiMsg);
    this.markStartShowing();
    this.processMessages();
  }

  private processMessages() {
    if (this.messages.length < 1) {
      return;
    }

    this.customSnackBar = this.snackBar.openFromComponent(MessageCardStapleComponent, {
      data: this.messages,
    });
    this.customSnackBar.instance.init(this.getUiMessages(), this.customSnackBar);
  }

  private markStartShowing() {
    this.messages.forEach(msg => {
      if (!msg.timestamp) {
        msg.timestamp = new Date();
      }
    });
  }

  private addUserMessage(uiMessage: UiMessage) {
    this.messages.push(uiMessage);

    setTimeout(() => {
      const index = this.messages.indexOf(uiMessage, 0);
      if (index > -1) {
        this.customSnackBar.instance.removeMessage(index);
      }
      this.processMessages();
    }, uiMessage.showDuration);
  }

  private getUiMessages(): Array<UiMessage> {
    return this.messages;
  }
}
