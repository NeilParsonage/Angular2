import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppInfo } from 'emst-ui-frame';
import { BehaviorSubject } from 'rxjs';
import { InfoDialog } from './info-dialog';

@Injectable({
  providedIn: 'root',
})
export class InfoDialogService {
  private appVersionFile = 'assets/version.json';

  appInfo$ = new BehaviorSubject<Array<AppInfo>>(this.createAppInfo(undefined));

  constructor(private http: HttpClient) {
    this.init();
  }

  init() {
    this.http.get<InfoDialog>(this.appVersionFile).subscribe(result => this.initInfoDialog(result));
  }

  initInfoDialog(infoDialogData: InfoDialog): void {
    const result = this.createAppInfo(infoDialogData);
    this.appInfo$.next(result);
  }

  private createAppInfo(infoDialogData: InfoDialog): Array<AppInfo> {
    const result = new Array<AppInfo>();
    let entry: AppInfo;
    entry = {
      displayText: 'Name',
      displayInformation: infoDialogData?.appName ?? '',
    };
    result.push(entry);

    entry = {
      displayText: 'Version',
      displayInformation: `${infoDialogData?.version} (${infoDialogData?.shortSHA})`,
    };
    result.push(entry);

    entry = {
      displayText: 'Branch',
      displayInformation: infoDialogData?.branch ?? '',
    };
    result.push(entry);

    entry = {
      displayText: 'Last Commit',
      displayInformation: infoDialogData?.lastCommitTime ?? '',
    };
    result.push(entry);

    return result;
  }
}
