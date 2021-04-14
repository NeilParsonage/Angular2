import { CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Injectable()
export class PrivilegeRouteGuard implements CanActivate {
  constructor(public keycloakService: KeycloakService) {}

  canActivate(route: ActivatedRouteSnapshot) {
    const expectedRole = route.data.expectedRole;
    return this.keycloakService.isUserInRole(expectedRole);
  }
}
