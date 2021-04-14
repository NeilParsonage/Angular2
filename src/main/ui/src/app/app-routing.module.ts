import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppAuthGuard } from './app.authguard';
import { Privileges } from './modules/fhi/models/privileges';
import { PagenotfoundComponent } from './modules/fhi/components/pagenotfound/pagenotfound.component';

const routes: Routes = [
  { path: '', redirectTo: '/kalender', pathMatch: 'full' },
  /*{
    path: 'kalender',
    component: KalenderUeberblickComponent,
    canActivate: [AppAuthGuard],
    data: { roles: [Privileges.FHI_READER,Privileges.FHI_ADMIN, Privileges.ASP_EDITOR, Privileges.FINISH_EDITOR, Privileges.LACK_EDITOR, Privileges.MONTAGE_EDITOR, Privileges.NA2000_EDITOR, Privileges.ROHBAU_EDITOR] }
  }, */
  {
    path: 'pagenotfound',
    component: PagenotfoundComponent,
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class AppRoutingModule {}
