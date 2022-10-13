import { DragRef, Point } from '@angular/cdk/drag-drop';
import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogStaticHelper } from 'src/app/shared/utils/dialog-static-helper';
import { AuftragHeberhaus } from '../../../models/auftragHeberhaus';
import { DialogShowHeberhausOptions } from './dialog-showHeberhaus.options.component';

@Component({
  selector: 'dialog-showHeberhaus',
  templateUrl: 'dialog-showHeberhaus.component.html',
  styleUrls: ['dialog-showHeberhaus.component.scss'],
})
export class DialogShowHeberhausComponent implements OnInit {
  auftragheberhaus: AuftragHeberhaus = null;
  titel: string = null;
  relevant: boolean = true;
  typs: { [key: string]: string } = {
    I: 'Ist',
    P: 'Plan',
    S: 'Soll',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public options: DialogShowHeberhausOptions,
    public datepipe: DatePipe,
    public dialogRef: MatDialogRef<DialogShowHeberhausComponent>
  ) {}

  ngOnInit(): void {
    this.auftragheberhaus = this.options.auftragHeberhaus;

    this.titel = this.options.titel;
  }

  onClickOk() {
    this.dialogRef.close();
  }

  public constrainPosition(point: Point, dragRef: DragRef): Point {
    return DialogStaticHelper.constrainPositionPreventDialogBecomesUnreachable(point);
  }

  setTyp(typ: string): string {
    if (!typ) {
      return '';
    }
    return '(' + this.typs[typ] + ')';
  }
}
