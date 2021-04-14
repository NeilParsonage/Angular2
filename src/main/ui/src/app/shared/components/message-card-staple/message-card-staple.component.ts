import { Component, OnInit } from '@angular/core';
import { UiMessage } from '../../models/ui-message';
import { MatSnackBarRef } from '@angular/material/snack-bar';

/**
 * Die Komponente 'MessageCardStapleComponent' visualisiert mehrere UiMessages
 * als gestapelte Material-Cards.
 * Idee: Die Komponente kann in eine Material-Snackbar integriert werden,
 *   damit mehrere Meldungen dargestellt werden können.
 */
@Component({
  selector: 'app-message-card-staple',
  templateUrl: './message-card-staple.component.html',
  styleUrls: ['./message-card-staple.component.scss']
})
export class MessageCardStapleComponent implements OnInit {
  dataUiMessages: UiMessage[];
  snackBarRef: MatSnackBarRef<MessageCardStapleComponent>;

  constructor() {}

  ngOnInit() {}

  /**
   * Initialisiert die Komponente mit den anzuzeigenden Nachrichten und integriert diese in eine
   * Material-Snackbar-Instanz.
   * @param messages darzustellende Oberflächenmeldungen (UiMessages)
   * @param snackBarReference Referenz zu einer Snackbar
   */
  init(messages: UiMessage[], snackBarReference: MatSnackBarRef<MessageCardStapleComponent>) {
    this.dataUiMessages = messages;
    this.snackBarRef = snackBarReference;
  }

  /**
   * Entfernt eine Nachricht aus der Darstellung.
   * Sollten keine Nachrichten mehr vorhanden sein, wird die Mat-Snackbar geschlossen.
   * @param idx Position im Nachrichtenfeld.
   */
  removeMessage(idx: number) {
    console.log('message-card-staple: removeMessage(' + idx + ')');
    this.dataUiMessages.splice(idx, 1);
    if (this.dataUiMessages.length < 1) {
      this.snackBarRef.dismiss();
    }
  }
}
