import { Directive, ElementRef, Input, OnChanges } from '@angular/core';
import * as screenfull from 'screenfull';

@Directive({
  selector: '[daiToggleFullscreen]',
})
export class ToggleFullscreenDirective implements OnChanges {
  @Input('daiToggleFullscreen')
  isFullscreen: boolean;

  constructor(private el: ElementRef) {}

  ngOnChanges() {
    if (this.isFullscreen && screenfull.isEnabled) {
      screenfull.request(this.el.nativeElement);
    } else if (screenfull.isEnabled) {
      screenfull.exit();
    }
  }
}
