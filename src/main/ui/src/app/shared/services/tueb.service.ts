import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tueb } from '../models/Tueb';

@Injectable({
  providedIn: 'root',
})
export class TuebService {
  private endpoint = 'priv/tueb';

  constructor(private http: HttpClient) {}

  public getAll(sprache: string): Observable<Tueb[]> {
    return this.http.get<Tueb[]>(`${this.endpoint}/` + sprache);
  }
}
