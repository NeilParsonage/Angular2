import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { first } from 'rxjs/operators';
import { ContextService } from 'src/app/core/services/context.service';
import { Auftrag } from '../../models/auftrag';
import { AuftragService } from '../../services/auftrag.service';
import { changeBandChoice } from './dialog-changeBand/changeBandChoice.';
import { DialogChangeBandComponent } from './dialog-changeBand/dialog-changeBand.component';
import { DialogEditBemerkungComponent } from './dialog-editBemerkung/dialog-editBemerkung.component';
import { editBemerkungChoice } from './dialog-editBemerkung/editBemerkungChoice.';

@Component({
  selector: 'app-einzelauskunft',
  templateUrl: './einzelauskunft.component.html',
  styleUrls: ['./einzelauskunft.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EinzelauskunftComponent implements OnInit {
  auftrag: Auftrag = null;
  auftragSearch: Auftrag = null;
  auftragChoosen: Auftrag = null;
  auftragList: Auftrag[] = null;
  // eslint-disable-next-line @typescript-eslint/no-inferrable-types
  searchpnr: string = '';

  options = [
    { value: 'pnr', label: 'PNR', key: 'PNR', filter: '' },
    { value: 'gesamt', label: 'Gesamt lfd. Nr.', key: 'Gesamt lfd. Nr.', filter: '' },
    { value: 'fhi', label: 'Ist lfd. FHI', key: 'Ist lfd. FHI', filter: '' },
    { value: 'lmt1', label: 'Ist lfd. LMT Band 1', key: 'Ist lfd. LMT Band 1', filter: '' },
    { value: 'lmt2', label: 'Ist lfd. LMT Band 2', key: 'Ist lfd. LMT Band 2', filter: '' },
    { value: 'lmt3', label: 'Ist lfd. LMT Band 3', key: 'Ist lfd. LMT Band 3', filter: '' },
  ];

  selected = this.options[0];

  dataSource$: any;
  constructor(
    private auftragService: AuftragService,
    private translateService: TranslateService,
    public dialog: MatDialog,
    private contextService: ContextService
  ) {}
  ngOnInit(): void {
    console.log('on init');
    // hello my friend
  }

  reset(): void {
    this.auftrag = null;
    this.auftragSearch = null;
    this.auftragChoosen = null;
    this.auftragList = null;
  }

  search(): void {
    console.log('Suche ' + this.selected.value + ' ' + this.selected.filter);

    this.reset();
    if (this.selected.value !== '' && this.selected.filter !== '') {
      this.loadDataFromSearch();
    }
  }

  private loadDataFromSearch() {
    this.auftragService
      .getAuftragBy(this.selected.value, this.selected.filter)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
        this.auftragSearch = this.auftrag;
        this.searchpnr = this.auftrag.pnr;
        console.log(this.auftrag);
        this.loadList(this.auftragSearch.lfdNrGes);
      });
  }

  private loadDataByPNR(pnr: string) {
    this.auftragService
      .getAuftragByPnr(pnr)
      .pipe(first())
      .subscribe(data => {
        this.auftrag = data;
      });
  }

  private loadDataFromTab(pnr: string) {
    this.loadDataByPNR(pnr);
  }

  private loadList(lfdNrGes: number) {
    if (lfdNrGes) {
      this.auftragService
        .getListAuftraegebyLfdNrGes(lfdNrGes)
        .pipe(first())
        .subscribe(data => {
          this.auftragList = data;
          console.log(this.auftragList);
        });
    }
  }

  onAuftragChanged($event) {
    this.auftragChoosen = $event;
    console.log('onAuftragChanged');
    console.log(this.auftragChoosen);
    if (this.auftragChoosen) {
      this.loadDataFromTab(this.auftragChoosen.pnr);
    }
  }

  isPNRChooserVisible(): boolean {
    if (this.auftragList && this.auftragSearch && this.auftragList.length > 0 && this.auftrag.ort != 'SATG' && this.auftrag.ort != 'BDAB') return true;
    else return false;
  }

  public onclickEditRemark() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '600px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      auftrag: this.auftrag,
      titel: 'Bemerkungstext ändern für PNR ' + this.auftrag.pnr,
    };
    const dialogRef = this.dialog.open(DialogEditBemerkungComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result === editBemerkungChoice.CONFIRM) {
        this.loadDataByPNR(this.auftrag.pnr);
      }
    });
  }
  public onclickChangeBand() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '600px';
    dialogConfig.height = 'auto';
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      auftrag: this.auftrag,
      titel: 'Band wechseln für PNR ' + this.auftrag.pnr,
    };
    const dialogRef = this.dialog.open(DialogChangeBandComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result === changeBandChoice.CONFIRM) {
        this.loadDataByPNR(this.auftrag.pnr);
      }
    });
  }

  hasPrivilegeEdit() {
    return this.contextService.hasPrivilegeEditAuftrag();
  }

  isEditable() {
    if (this.auftrag != null) {
      return true;
    } else {
      return false;
    }
  }
}
