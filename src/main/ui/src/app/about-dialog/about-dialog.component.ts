import { Component, OnInit } from '@angular/core';
import { AboutDialogService } from './about-dialog.service';

@Component({
  selector: 'app-about-dialog',
  templateUrl: './about-dialog.component.html',
  styleUrls: ['./about-dialog.component.scss']
})
export class AboutDialogComponent implements OnInit {

  constructor(public aboutDialogService: AboutDialogService) { }

  ngOnInit() {
  }

}
