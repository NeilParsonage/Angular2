import { Component, EventEmitter, Inject, Input, OnInit, Optional, Output } from '@angular/core';
import { CustomEvent, ImageViewerConfig } from './image-viewer-config.model';

const DEFAULT_CONFIG: ImageViewerConfig = {
  btnClass: 'mat-fab',
  zoomFactor: 0.1,
  containerBackgroundColor: 'rgba(0,0,0,0.0)',
  wheelZoom: false,
  allowFullscreen: true,
  allowKeyboardNavigation: true,
  btnShow: {
    zoomIn: true,
    zoomOut: true,
    rotateClockwise: true,
    rotateCounterClockwise: true,
    next: true,
    prev: true,
  },
  btnIcons: {
    zoomIn: 'fa fa-plus',
    zoomOut: 'fa fa-minus',
    rotateClockwise: 'fa fa-repeat',
    rotateCounterClockwise: 'fa fa-undo',
    next: 'fa fa-arrow-right',
    prev: 'fa fa-arrow-left',
    fullscreen: 'fa fa-arrows-alt',
  },
};

const DINA_CONFIG: ImageViewerConfig = {
  wheelZoom: true, // If true, the mouse wheel can be used to zoom in
  allowFullscreen: false, // If true, the fullscreen button will be shown, allowing the user to entr fullscreen mode
  btnIcons: {
    // The icon classes that will apply to the buttons. By default, font-awesome is used.
    zoomIn: 'zoom_in',
    zoomOut: 'zoom_out',
  },
  btnShow: {
    zoomIn: true,
    zoomOut: true,
    rotateClockwise: false,
    rotateCounterClockwise: false,
    next: false,
    prev: false,
  },
  customBtns: [{ name: 'reset-zoom', icon: 'search' }],
};

@Component({
  selector: 'dai-image-viewer',
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.scss'],
})
export class ImageViewerComponent implements OnInit {
  @Input() src: string[];

  @Input() index = 0;

  @Input() config: ImageViewerConfig;

  @Output() indexChange: EventEmitter<number> = new EventEmitter();

  @Output() configChange: EventEmitter<ImageViewerConfig> = new EventEmitter();

  @Output() customEvent: EventEmitter<CustomEvent> = new EventEmitter();

  public style = { transform: '', msTransform: '', oTransform: '', webkitTransform: '' };
  public fullscreen = false;
  public loading = true;
  private scale = 1;
  private rotation = 0;
  private translateX = 0;
  private translateY = 0;
  private prevX: number;
  private prevY: number;
  private hovered = false;

  constructor(@Optional() @Inject('config') public moduleConfig: ImageViewerConfig) {}

  ngOnInit() {
    const merged = this.mergeConfig(DEFAULT_CONFIG, this.moduleConfig);
    const mergedDina = this.mergeConfig(DEFAULT_CONFIG, DINA_CONFIG);
    this.config = this.mergeConfig(mergedDina, this.config);
    this.triggerConfigBinding();
  }

  zoomIn() {
    this.scale *= 1 + this.config.zoomFactor;
    this.updateStyle();
  }

  zoomOut() {
    if (this.scale > this.config.zoomFactor) {
      this.scale /= 1 + this.config.zoomFactor;
    }
    this.updateStyle();
  }

  scrollZoom(evt: { deltaY: number }): void {
    if (this.config.wheelZoom) {
      if (evt.deltaY > 0) {
        this.zoomOut();
      } else {
        this.zoomIn();
      }
    }
  }

  // XXX - not useable via ui at the moment - buttons are not materialized
  rotateClockwise() {
    this.rotation += 90;
    this.updateStyle();
  }

  // XXX - not useable via ui at the moment - buttons are not materialized
  rotateCounterClockwise() {
    this.rotation -= 90;
    this.updateStyle();
  }

  onLoad() {
    this.loading = false;
  }

  onLoadStart() {
    this.loading = true;
  }

  onDragOver(evt: { clientX: number; clientY: number }) {
    this.translateX += evt.clientX - this.prevX;
    this.translateY += evt.clientY - this.prevY;
    this.prevX = evt.clientX;
    this.prevY = evt.clientY;
    this.updateStyle();
  }

  onDragStart(evt: {
    dataTransfer: { setDragImage: (arg0: any, arg1: number, arg2: number) => void };
    target: { nextElementSibling: any };
    clientX: number;
    clientY: number;
  }) {
    if (evt.dataTransfer && evt.dataTransfer.setDragImage) {
      evt.dataTransfer.setDragImage(evt.target.nextElementSibling, 0, 0);
    }
    this.prevX = evt.clientX;
    this.prevY = evt.clientY;
  }

  toggleFullscreen() {
    this.fullscreen = !this.fullscreen;
    if (!this.fullscreen) {
      this.reset();
    }
  }

  triggerIndexBinding() {
    this.indexChange.emit(this.index);
  }

  triggerConfigBinding() {
    this.configChange.next(this.config);
  }

  fireCustomEvent(name: any, imageIndex: any) {
    this.customEvent.emit(new CustomEvent(name, imageIndex));
  }

  reset() {
    this.scale = 1;
    this.rotation = 0;
    this.translateX = 0;
    this.translateY = 0;
    this.updateStyle();
  }

  private canNavigate(event: any) {
    return event == null || (this.config.allowKeyboardNavigation && this.hovered);
  }

  private updateStyle() {
    this.style.transform = `translate(${this.translateX}px, ${this.translateY}px) rotate(${this.rotation}deg) scale(${this.scale})`;
    this.style.msTransform = this.style.transform;
    this.style.webkitTransform = this.style.transform;
    this.style.oTransform = this.style.transform;
  }

  private mergeConfig(defaultValues: ImageViewerConfig, overrideValues: ImageViewerConfig): ImageViewerConfig {
    let result: ImageViewerConfig = { ...defaultValues };
    if (overrideValues) {
      result = { ...defaultValues, ...overrideValues };

      if (overrideValues.btnIcons) {
        result.btnIcons = { ...defaultValues.btnIcons, ...overrideValues.btnIcons };
      }
    }
    return result;
  }
}
