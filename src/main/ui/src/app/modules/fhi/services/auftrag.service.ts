import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Auftrag } from '../models/auftrag';

@Injectable({
  providedIn: 'root',
})
export class AuftragService {
  private endpoint = 'pub/auftrag';

  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient, public router: Router) {}

  public getAuftragByPnr(pnr: string): Observable<Auftrag> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<Auftrag>(`${this.endpoint}/info`, options);
  }
}
