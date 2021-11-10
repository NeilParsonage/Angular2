import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserProfileElement } from '../models/user-profile-element';
import { UserProfileSpring } from '../models/user-profile-spring';

@Injectable({
  providedIn: 'root',
})
export class UserProfileService {
  readonly DEFAULT_TERMINAL_KEY = 'DEFAULT_TERMINAL_KEY';
  readonly DEFAULT_MOVISU_MODUS_KEY = 'DEFAULT_MOVISU_MODUS_KEY';

  private readonly userprofileEndpoint = environment.userprofileEndpointPrefix;

  username: string;

  userProfile: Array<UserProfileElement>;

  constructor(private httpClient: HttpClient, private keycloakService: KeycloakService) {}

  fetchUserProfile(): Observable<Array<UserProfileElement>> {
    this.username = this.keycloakService.getUsername();
    return this.httpClient.get<UserProfileSpring>(`${this.userprofileEndpoint}/search/findByUsernameIgnoreCase?username=${this.username}`).pipe(
      // eslint-disable-next-line no-underscore-dangle
      map(result => result._embedded?.userProfiles),
      tap(result => (this.userProfile = result))
    );
  }

  saveDefaultTerminalElement(terminalId: number): Observable<any> {
    return this.doSaveElement(this.DEFAULT_TERMINAL_KEY, terminalId.toString());
  }

  saveDefaultMovisuModusElement(isNacharbeit: boolean): Observable<any> {
    const value = isNacharbeit ? '1' : '0';
    return this.doSaveElement(this.DEFAULT_MOVISU_MODUS_KEY, value);
  }

  private doSaveElement(key: string, value: string): Observable<any> {
    let entry = this.getEntry(key);
    if (!!entry && !!entry.id) {
      let headers = new HttpHeaders();
      headers = headers.set('If-Match', entry.etag.toString());

      entry.value = value?.toString();
      return this.httpClient.put(`${this.userprofileEndpoint}/${entry.id.toString()}`, entry, { headers }).pipe(
        tap(resultEntry => {
          // update local profile data
          const index = this.userProfile.findIndex(next => next.id === resultEntry.id);
          if (index < 0) {
            this.userProfile.push(resultEntry);
          } else {
            this.userProfile[index] = resultEntry;
          }
        })
      );
    }
    entry = {
      key,
      value,
      username: this.username,
    };
    return this.httpClient.post(`${this.userprofileEndpoint}`, entry).pipe(tap(resultEntry => this.userProfile.push(resultEntry)));
  }

  getEntry(key: string): UserProfileElement {
    if (!!this.userProfile) {
      return this.userProfile.find(entry => entry.key === key);
    }
    return undefined;
  }

  getDefaultTerminalEntry(): UserProfileElement {
    return this.getEntry(this.DEFAULT_TERMINAL_KEY);
  }

  getDefaultMovisuModusEntry(): UserProfileElement {
    return this.getEntry(this.DEFAULT_MOVISU_MODUS_KEY);
  }
}
