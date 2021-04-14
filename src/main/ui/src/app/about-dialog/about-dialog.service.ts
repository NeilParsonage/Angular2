import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PackageInfo } from './package-info';
import { Subscription } from 'rxjs/internal/Subscription';

@Injectable({
  providedIn: 'root',
})
export class AboutDialogService {
  public packageInfo: PackageInfo | null = null;
  public serviceSub: Subscription;

  constructor(private http: HttpClient) {
    this.serviceSub = http.get('assets/app-info.json').subscribe(
      (result: any) => {
        this.packageInfo = result;
      },
      (error) => {
        this.packageInfo = {
          name: '(error retrieving version info)',
          version: 'unbekannt',
          revision: 'unbekannt',
          buildBranch: 'unbekannt',
          buildTimestamp: 'unbekannt',
        };
        console.error('Beim Auslesen der Versionsinformationen ist ein Fehler aufgetreten:');
        console.error(error);
      }
    );
  }
}
