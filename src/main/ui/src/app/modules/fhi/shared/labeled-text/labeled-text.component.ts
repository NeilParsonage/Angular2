import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-labeled-text',
  templateUrl: './labeled-text.component.html',
  styleUrls: ['./labeled-text.component.scss'],
})
export class LabeledTextComponent {
  @Input() label: string;

  @Input() value: string;

  constructor() {}
}
