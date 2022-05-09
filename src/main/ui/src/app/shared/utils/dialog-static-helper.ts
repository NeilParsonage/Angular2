import { Point } from '@angular/cdk/drag-drop';

export class DialogStaticHelper {
  static constrainPositionPreventDialogBecomesUnreachable(point: Point): Point {
    const height = window.innerHeight;
    const width = window.innerWidth;

    const minimalVisibleHeightOrWidth = 32;

    const minX = minimalVisibleHeightOrWidth;
    const minY = minimalVisibleHeightOrWidth;
    const maxX = width - minimalVisibleHeightOrWidth;
    const maxY = height - minimalVisibleHeightOrWidth;

    const setPoint: Point = { ...point };
    if (point.x < minX) {
      setPoint.x = minX;
    }
    if (point.y < minY) {
      setPoint.y = minY;
    }
    if (point.x > maxX) {
      setPoint.x = maxX;
    }
    if (point.y > maxY) {
      setPoint.y = maxY;
    }
    return setPoint;
  }
}
