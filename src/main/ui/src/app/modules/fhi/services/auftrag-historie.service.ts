import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ContextService } from '../../../core/services/context.service';
import { Auftragshistorie } from '../models/auftragshistorie';
import { AuftragshistoriePage } from '../models/auftragshistoriePage';

@Injectable({
  providedIn: 'root',
})
export class AuftragHistorieService {
  private endpoint = 'priv/auftragshistorie';

  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private context: ContextService, private http: HttpClient, public router: Router) {}

  public getAllAuftragsHistorie(): Observable<Auftragshistorie[]> {
    const options = {};
    return this.http.get<Auftragshistorie[]>(`${this.endpoint}/listAllHistorieAuftraege`, options);
  }

  public getAll(query: string): Observable<AuftragshistoriePage> {
    return this.http.get<AuftragshistoriePage>(this.endpoint + '?search=' + query);
  }
}
