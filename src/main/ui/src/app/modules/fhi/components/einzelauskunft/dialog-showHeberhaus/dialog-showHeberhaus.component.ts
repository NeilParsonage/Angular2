import { DragRef, Point } from '@angular/cdk/drag-drop';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogStaticHelper } from 'src/app/utils/dialog-static-helper';
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

  constructor(@Inject(MAT_DIALOG_DATA) public options: DialogShowHeberhausOptions, public dialogRef: MatDialogRef<DialogShowHeberhausComponent>) {}

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
}
