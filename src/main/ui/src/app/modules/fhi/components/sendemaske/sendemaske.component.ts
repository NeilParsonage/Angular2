import { AfterContentInit, Component, OnInit, ViewChild } from '@angular/core';
import { DaiPaginatorConfig, DaiTableConfig } from 'emst-table';
import { first } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { ProtocolEntry } from '../../models/protocol-entry';
import { ProtocolSeverity } from '../../models/protocol-severity';
import { SendemaskeGesendetService } from './service/sendemaske-gesendet.service';
import { SendemaskeUngesendetService } from './service/sendemaske-ungesendet.service';

// const ELEMENT_DATA: PeriodicElement[] = [{ position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' }];
@Component({
  selector: 'app-sendemaske',
  templateUrl: './sendemaske.component.html',
  styleUrls: ['./sendemaske.component.scss'],
})
export class SendemaskeComponent implements OnInit, AfterContentInit {
  // private dataSourceTop = [{ band: 2, pnr: '13641290', tkl: 'H941 H424', sternenhimmel: 'x' }];

  // private dataSourceDown = this.generateTableColumnsUngesendet();

  daiTableConfigTop: DaiTableConfig = null;

  daiTableConfigBottom: DaiTableConfig = null;

  sendSortedBy = 'ladispo';

  @ViewChild('sendemaske') selfComponent: any;

  constructor(
    private contextService: ContextService,
    private gesendetService: SendemaskeGesendetService,
    private ungesendetService: SendemaskeUngesendetService //  private host: ElementRef<HTMLElement>
  ) {}

  ngAfterContentInit(): void {}

  ngOnInit(): void {
    let dataSourceTop = this.gesendetService.generateTableColumnsGesendet(this.sizeSternenhimmel());
    let dataSourceDown = this.ungesendetService.generateTableColumnsUngesendet(this.sizeSternenhimmel());

    this.daiTableConfigTop = new DaiTableConfig(
      dataSourceTop,
      this.gesendetService.generateDisplayedColumnsGesendet(dataSourceTop[0]),
      this.getPaginatorConfig()
    );
    this.daiTableConfigBottom = new DaiTableConfig(
      dataSourceDown,
      this.ungesendetService.generateDisplayedColumnsUngesendet(dataSourceDown[0]),
      this.getPaginatorConfig()
    );

    // let elem = this.document.documentElement;
    this.contextService
      .afterRendered()
      .pipe(first())
      .subscribe(() => this.toggleFullscreen());
  }

  getPaginatorConfig(): DaiPaginatorConfig {
    return new DaiPaginatorConfig(true, null, ['100']);
  }

  toggleFullscreen() {
    // let elem1 = document.querySelector('app-sendemaske');
    let elem = this.selfComponent.nativeElement;
    // let elem = document.documentElement;

    if (!document.fullscreenElement) {
      elem.requestFullscreen().catch(err => {
        // alert(`Error attempting to enable fullscreen mode: ${err.message} (${err.name})`);
      });
    } else {
      document.exitFullscreen();
    }
  }

  getProtocolEntries(allEntries: ProtocolEntry[]): ProtocolEntry[] {
    const errorCases = Array<ProtocolEntry>();
    const warnCases = Array<ProtocolEntry>();
    allEntries.forEach(e => {
      if (e.severity === ProtocolSeverity.Error || e.severity === ProtocolSeverity.Fatal) {
        errorCases.push(e);
        return errorCases;
      }
      if (e.severity === ProtocolSeverity.Warning) {
        warnCases.push(e);
      }
    });
    if (errorCases.length > 0) {
      return errorCases;
    }
    return warnCases;
  }

  isProtocolWithErrors(entries: ProtocolEntry[]) {
    if (!entries || entries.length < 1) {
      return false;
    }
    return entries[0].severity === ProtocolSeverity.Error || entries[0].severity === ProtocolSeverity.Fatal;
  }

  getErrorMode(allEntries: ProtocolEntry[]): boolean {
    if (allEntries.find(e => e.severity === ProtocolSeverity.Error || e.severity === ProtocolSeverity.Fatal)) {
      return true;
    }
    return false;
  }

  pageChangeTop($event) {}

  pageChangeBottom($event) {}

  sizeSternenhimmel(): number {
    return 35;
  }
}
