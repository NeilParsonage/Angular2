import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuftragshistorieComponent } from './modules/fhi/components/auftragshistorie/auftragshistorie.component';
import { EinzelauskunftComponent } from './modules/fhi/components/einzelauskunft/einzelauskunft.component';
import { RecipeDetailComponent } from './modules/fhi/components/recipes/recipe-detail/recipe-detail.component';
import { RecipeEditComponent } from './modules/fhi/components/recipes/recipe-edit/recipe-edit.component';
import { RecipeStartComponent } from './modules/fhi/components/recipes/recipe-start/recipe-start.component';
import { RecipesComponent } from './modules/fhi/components/recipes/recipes.component';
import { SendemaskeDebugComponent } from './modules/fhi/components/sendemaske-debug/sendemaske-debug.component';
import { SendemaskeComponent } from './modules/fhi/components/sendemaske/sendemaske.component';
import { ShoppingListComponent } from './modules/fhi/components/shopping-list/shopping-list.component';
import { TutorialComponent } from './modules/fhi/components/tutorial/tutorial.component';
import { WelcomeComponent } from './modules/fhi/components/welcome/welcome.component';
import { Privileges } from './modules/fhi/models/privileges';

const wikibase = 'https://wiki.dewoe.corpintra.net/wikiemst/index.php/';

export const routes: Routes = [


  {
    path: '',
    redirectTo: '/recipes',
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
      roles: [Privileges.FHI_LEITWARTE, Privileges.FHI_ADMIN, Privileges.FHI_READER],
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
      roles: [Privileges.FHI_ADMIN],
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
      roles: [Privileges.FHI_ADMIN],
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
  {
    path: 'Tutorial',
    component: TutorialComponent,
    data: {
      title: 'Tutorial',
      wikilink: wikibase + 'W060.FHI.F.Frontend.Tutorial',
      roles: [],
      displaySidemenu: true,
      displayText: 'Tutorial',
      displayIcon: 'book',
    },
  },
  {path: 'recipes', component: RecipesComponent, children:[
   { path: '', component: RecipeStartComponent},
   { path: 'new', component: RecipeEditComponent},
   { path: ':id', component: RecipeDetailComponent},
   { path: ':id/edit', component: RecipeEditComponent}
  ] },
  {path: 'shopping-list', component: ShoppingListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
