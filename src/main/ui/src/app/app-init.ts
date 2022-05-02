import { TranslateService } from '@ngx-translate/core';
import { KeycloakService } from 'keycloak-angular';
import { TuebService } from 'src/app/shared/services/tueb.service';
import { environment } from 'src/environments/environment';

// eslint-disable-next-line prefer-arrow/prefer-arrow-functions
export function initializer(keycloak: KeycloakService, translateService: TranslateService, tuebService: TuebService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: environment.keycloakConfig,
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false,
          },
          bearerExcludedUrls: [],
          loadUserProfileAtStartUp: true,
        });
        const defaultLanguage = 'de';
        translateService.setDefaultLang(defaultLanguage);
        const translationDe = await tuebService.getAll('DEUT').toPromise();
        translateService.setTranslation('de', translationDe);
        resolve(true);
      } catch (error) {
        reject(error);
      }
    });
  };
}
