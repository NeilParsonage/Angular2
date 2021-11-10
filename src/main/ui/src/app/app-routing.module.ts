import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './modules/fhi/components/welcome/welcome.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'welcome',
    pathMatch: 'full',
    data: {
      roles: [],
      displaySidemenu: false,
    },
  },
  {
    path: 'welcome',
    component: WelcomeComponent,
    data: {
      title: 'Welcome',
      roles: [],
      displaySidemenu: true,
      displayText: 'Welcome to FHI',
      displayIcon: 'home',
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
