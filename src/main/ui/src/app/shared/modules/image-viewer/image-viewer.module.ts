import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { MaterialModule } from '../material.module';
import { ToggleFullscreenDirective } from './fullscreen.directive';
import { ImageViewerConfig } from './image-viewer-config.model';
import { ImageViewerComponent } from './image-viewer.component';

@NgModule({
  imports: [CommonModule, MaterialModule],
  declarations: [ImageViewerComponent, ToggleFullscreenDirective],
  exports: [ImageViewerComponent, ToggleFullscreenDirective],
  providers: [],
})
export class ImageViewerModule {
  static forRoot(config?: ImageViewerConfig): ModuleWithProviders<ImageViewerModule> {
    return {
      ngModule: ImageViewerModule,
      providers: [{ provide: 'config', useValue: config }],
    };
  }
}
