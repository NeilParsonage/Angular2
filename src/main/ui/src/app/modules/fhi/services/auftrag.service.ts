import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Auftrag } from '../models/auftrag';
import { AuftragAggregate } from '../models/auftragAggregate';
import { AuftragKabelsaetze } from '../models/auftragKabelsatz';
import { AuftragLacke } from '../models/auftragLacke';
import { AuftragTermine } from '../models/auftragTermine';
import { AuftragTermineDetails } from '../models/auftragTermineDetails';
import { Sendung } from '../models/sendung';
import { SendungResponse } from '../models/sendungResponse';

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

  public getAuftragBy(option: string, filter?: string): Observable<Auftrag> {
    const options = {
      params: new HttpParams().set('option', option).append('key', filter),
    };
    return this.http.get<Auftrag>(`${this.endpoint}/search`, options);
  }

  public getAuftragTermineByPnr(pnr: string): Observable<AuftragTermine> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragTermine>(`${this.endpoint}/termine`, options);
  }
  public getAuftragTermineDetailsByPnr(pnr: string): Observable<AuftragTermineDetails[]> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragTermineDetails[]>(`${this.endpoint}/termineDetails`, options);
  }
  public getAuftragKabelsaetzeByPnr(pnr: string): Observable<AuftragKabelsaetze[]> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragKabelsaetze[]>(`${this.endpoint}/kabelsaetze`, options);
  }
  public getAuftragAggregateByPnr(pnr: string): Observable<AuftragAggregate[]> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragAggregate[]>(`${this.endpoint}/aggregate`, options);
  }
  public getAuftragFhsLackeByPnr(pnr: string): Observable<AuftragLacke[]> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragLacke[]>(`${this.endpoint}/fhsLacke`, options);
  }
  public getAuftragFzgLackByPnr(pnr: string): Observable<AuftragLacke> {
    const options = {
      params: new HttpParams().set('pnr', pnr),
    };
    return this.http.get<AuftragLacke>(`${this.endpoint}/fzgLack`, options);
  }
  public getListAuftraegebyLfdNrGes(lfdNrGes: number): Observable<Auftrag[]> {
    const options = {
      params: new HttpParams().set('lfdNrGes', lfdNrGes),
    };
    return this.http.get<Auftrag[]>(`${this.endpoint}/listAuftraegebyGesLfdNr`, options);
  }

  public sendung(sendung: Sendung) {
    return this.http.post<SendungResponse>(`${this.endpoint}/sendung`, sendung);
  }
}
