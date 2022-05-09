import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppInfo } from 'emst-ui-frame';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private endpoint = 'pub/app';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  constructor(private http: HttpClient) {}

  public getInfo(): Observable<AppInfo> {
    return this.http.get<AppInfo>(`${this.endpoint}/info`);
  }
}
