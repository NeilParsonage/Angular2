import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContextService } from '../../../../core/services/context.service';

@Component({
  selector: 'app-pagenotfound',
  templateUrl: './pagenotfound.component.html',
  styleUrls: ['./pagenotfound.component.scss'],
})

export class PagenotfoundComponent implements OnInit {

  urlPath: string;
  constructor(public context: ContextService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.context.setCurrentPageInfo(
      'Fehler',
      'https://wiki.dewoe.corpintra.net/wikiemst/index.php/W060.FHI/PageNotFound'
    );

    this.route.url.subscribe(data => {
      this.urlPath = '/';
      for (const url of data) {
        this.urlPath = this.urlPath.concat(url.path);
        this.urlPath = this.urlPath.concat('/');
      }
      this.urlPath = this.urlPath.substring(0, this.urlPath.length - 1);
      console.error('Page not found: ' + this.urlPath, data);
    });
  }

}
