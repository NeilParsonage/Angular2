import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-labeled-icon',
  templateUrl: './labeled-icon.component.html',
  styleUrls: ['./labeled-icon.component.scss'],
})
export class LabeledIconComponent {
  @Input() label: string;

  @Input() item: string;

  @Input() itemText: string;

  constructor() {}
}
