import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { AngularSplitModule } from 'angular-split';
import { LibEmstTableModule } from 'emst-table';
import { MaterialModule } from 'src/app/shared/modules/material.module';
import { TuebPipe } from 'src/app/shared/pipes/tueb.pipe';
import { AuftragshistorieComponent } from './components/auftragshistorie/auftragshistorie.component';
import { DialogChangeBandComponent } from './components/einzelauskunft/dialog-changeBand/dialog-changeBand.component';
import { DialogEditBemerkungComponent } from './components/einzelauskunft/dialog-editBemerkung/dialog-editBemerkung.component';
import { DialogShowCodesComponent } from './components/einzelauskunft/dialog-showCodes/dialog-showCodes.component';
import { DialogShowHeberhausComponent } from './components/einzelauskunft/dialog-showHeberhaus/dialog-showHeberhaus.component';
import { DialogShowKriterienComponent } from './components/einzelauskunft/dialog-showKriterien/dialog-showKriterien.component';
import { DialogShowlistComponent } from './components/einzelauskunft/dialog-showlist/dialog-showlist.component';
import { EinzelauskunftComponent } from './components/einzelauskunft/einzelauskunft.component';
import { EinzelauskunftAktuelleFPComponent } from './components/einzelauskunft/einzelauskunft_aktuelleFP/einzelauskunft_aktuelleFP.component';
import { EinzelauskunftAuditComponent } from './components/einzelauskunft/einzelauskunft_audit/einzelauskunft_audit.component';
import { EinzelauskunftKopfComponent } from './components/einzelauskunft/einzelauskunft_kopf/einzelauskunft_kopf.component';
import { EinzelauskunftOrtComponent } from './components/einzelauskunft/einzelauskunft_ort/einzelauskunft_ort.component';
import { EinzelauskunftRFComponent } from './components/einzelauskunft/einzelauskunft_rf/einzelauskunft_rf.component';
//import { EinzelauskunftSearchComponent } from './components/einzelauskunft/einzelauskunft_search/einzelauskunft_search.component';
import { DtToolbarModule } from '@ite/ng-daimlertruck';
import { EinzelauskunftSendestatusComponent } from './components/einzelauskunft/einzelauskunft_sendestatus/einzelauskunft_sendestatus.component';
import { EinzelauskunftSperrenComponent } from './components/einzelauskunft/einzelauskunft_sperren/einzelauskunft_sperren.component';
import { EinzelauskunftTermineComponent } from './components/einzelauskunft/einzelauskunft_termine/einzelauskunft_termine.component';
import { HeaderComponent } from './components/header/header.component';
import { PnrChooserComponent } from './components/pnr-chooser/pnr-chooser.component';
import { RecipeDetailComponent } from './components/recipes/recipe-detail/recipe-detail.component';
import { RecipeItemComponent } from './components/recipes/recipe-list/recipe-item/recipe-item.component';
import { RecipeListComponent } from './components/recipes/recipe-list/recipe-list.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { SendemaskeDebugComponent } from './components/sendemaske-debug/sendemaske-debug.component';
import { SendemaskeComponent } from './components/sendemaske/sendemaske.component';
import { ShoppingEditComponent } from './components/shopping-list/shopping-edit/shopping-edit.component';
import { ShoppingListComponent } from './components/shopping-list/shopping-list.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

@NgModule({
  declarations: [
    WelcomeComponent,
    AuftragshistorieComponent,
    EinzelauskunftComponent,
    EinzelauskunftRFComponent,
    EinzelauskunftKopfComponent,
    EinzelauskunftTermineComponent,
    EinzelauskunftSendestatusComponent,
    //  EinzelauskunftSearchComponent,
    EinzelauskunftAuditComponent,
    EinzelauskunftAktuelleFPComponent,
    EinzelauskunftOrtComponent,
    EinzelauskunftSperrenComponent,
    DialogShowHeberhausComponent,
    DialogEditBemerkungComponent,
    DialogChangeBandComponent,
    DialogShowlistComponent,
    DialogShowCodesComponent,
    DialogShowKriterienComponent,
    PnrChooserComponent,
    SendemaskeComponent,
    SendemaskeDebugComponent,
    TuebPipe,
    TutorialComponent,
    RecipesComponent,
    HeaderComponent,
    RecipeListComponent,
    RecipeDetailComponent,
    RecipeItemComponent,
    ShoppingListComponent,
    ShoppingEditComponent,
  ],
  providers: [TuebPipe],
  imports: [
    CommonModule,
    FlexLayoutModule,
    FormsModule,
    LibEmstTableModule,
    MaterialModule,
    ReactiveFormsModule,
    AngularSplitModule,
    TranslateModule.forRoot(),
    DtToolbarModule,
  ],
  exports: [TuebPipe],
})
export class FhiModule {}
