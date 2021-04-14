import { Component, OnInit, Input } from '@angular/core';
import { ContextService } from '../core/services/context.service';
import { Privileges } from '../modules/fhi/models/privileges';
import { KeycloakService } from 'keycloak-angular';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnInit {
  constructor(public context: ContextService,
    protected kc: KeycloakService) { }

  ngOnInit() { }

  hasPrivilegeRead():boolean {
    return this.isAdmin() || this.isEditor() || this.kc.isUserInRole(Privileges.FHI_READER);
  }

  hasPrivilegeEdit():boolean {
    return this.isAdmin() || this.isEditor();
  }

  isAdmin():boolean {
    return this.kc.isUserInRole(Privileges.FHI_ADMIN);
  }

  isEditor():boolean {
    return false;
  }

}
