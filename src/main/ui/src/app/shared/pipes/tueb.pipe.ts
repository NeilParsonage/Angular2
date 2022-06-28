import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
  name: 'tuebPipe',
})
export class TuebPipe implements PipeTransform {
  constructor(private service: TranslateService) {}

  transform(value: string, args: string[]): string {
    let rawText: string = this.service.instant(value);
    if (!args) {
      return rawText.toString();
    }
    let idx: number = -1;
    args.forEach(e => {
      idx++;
      rawText = rawText.replaceAll(this.createPlaceholderByIndex(idx), e);
    });
    rawText = rawText.replaceAll(' --//-- ', '\n');
    return rawText;
  }

  private createPlaceholderByIndex(idx: number): string {
    return '{'.concat(idx.toString()).concat('}');
  }
}
