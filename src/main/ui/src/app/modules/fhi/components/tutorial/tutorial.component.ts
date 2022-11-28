import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tutorial',
  templateUrl: './tutorial.component.html',
  styleUrls: ['./tutorial.component.scss']
})
export class TutorialComponent implements OnInit {

  selected = ['kritsFhi', 'ort'];
  selection = ['codesFhi', 'ort', 'ortRhm', 'kritsFhi'];

  public loadedFeature = 'recipe';

  constructor() { }

  ngOnInit(): void {
  }

  onNavigate(feature: string){
    this.loadedFeature = feature;
  }

  onValChange() {
    console.log("onValChanged called")
    //this.querySubject.next(this.querySubject.value);
  }

}
