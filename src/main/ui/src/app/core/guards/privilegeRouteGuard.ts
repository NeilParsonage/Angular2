import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable()
export class PrivilegeRouteGuard extends KeycloakAuthGuard {
  constructor(protected router: Router, protected keycloakAngular: KeycloakService) {
    super(router, keycloakAngular);
  }

  isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    return new Promise(async (resolve, reject) => {
      if (!this.authenticated) {
        this.keycloakAngular.login();
        return;
      }
      const requiredRoles = route.data.roles;
      if (!requiredRoles || requiredRoles.length === 0) {
        return resolve(true);
      } else {
        let granted = false;
        if (this.keycloakAngular.getKeycloakInstance().resourceAccess.hasOwnProperty(this.keycloakAngular.getKeycloakInstance().clientId)) {
          granted = this.keycloakAngular
            .getKeycloakInstance()
            .resourceAccess[this.keycloakAngular.getKeycloakInstance().clientId].roles.some(elm => requiredRoles.indexOf(elm) > -1);
        } else {
          granted = false;
        }
        if (granted === false) {
          this.router.navigate(['forbidden']);
        }
        resolve(granted);
      }
    });
  }
}
