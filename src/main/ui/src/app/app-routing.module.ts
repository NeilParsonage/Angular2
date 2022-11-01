import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuftragshistorieComponent } from './modules/fhi/components/auftragshistorie/auftragshistorie.component';
import { EinzelauskunftComponent } from './modules/fhi/components/einzelauskunft/einzelauskunft.component';
import { SendemaskeDebugComponent } from './modules/fhi/components/sendemaske-debug/sendemaske-debug.component';
import { SendemaskeComponent } from './modules/fhi/components/sendemaske/sendemaske.component';
import { WelcomeComponent } from './modules/fhi/components/welcome/welcome.component';

const wikibase = 'https://wiki.dewoe.corpintra.net/wikiemst/index.php/';

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
      wikilink: wikibase + 'W060.FHI',
      roles: [],
      displaySidemenu: true,
      displayText: 'Welcome to FHI',
      displayIcon: 'home',
    },
  },

  {
    path: 'Einzelauskunft',
    component: EinzelauskunftComponent,
    data: {
      title: 'Einzelauskunft',
      wikilink: wikibase + 'W060.FHI.F.Frontend.Einzelauskunft',
      roles: [],
      displaySidemenu: true,
      displayText: 'Einzelauskunft',
      displayIcon: 'info',
    },
  },

  {
    path: 'Sendemaske',
    component: SendemaskeComponent,
    data: {
      title: 'Sendemaske',
      wikilink: wikibase + 'W060.FHI.F.Frontend.Sendemaske',
      roles: [],
      displaySidemenu: true,
      displayText: 'Sendemaske',
      displayIcon: 'send',
    },
  },

  {
    path: 'SendemaskeDebug',
    component: SendemaskeDebugComponent,
    data: {
      title: 'Sendemaske',
      wikilink: wikibase + 'W060.FHI.F.Frontend.Sendemaske',
      roles: [],
      displaySidemenu: true,
      displayText: 'Sendemaske Debug',
      displayIcon: 'bug_report',
    },
  },

  {
    path: 'Auftragshistorie',
    component: AuftragshistorieComponent,
    data: {
      title: 'Auftragshistorie',
      wikilink: wikibase + 'W060.FHI.F.Frontend.Auftragshistorie',
      roles: [],
      displaySidemenu: true,
      displayText: 'Auftragshistorie',
      displayIcon: 'reorder',
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
