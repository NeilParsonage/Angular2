import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { AppInfo } from '../models/app-info';
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
