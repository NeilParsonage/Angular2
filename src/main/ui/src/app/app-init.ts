import { KeycloakService } from 'keycloak-angular';
import { environment } from 'src/environments/environment';

// eslint-disable-next-line prefer-arrow/prefer-arrow-functions
export function initializer(keycloak: KeycloakService): () => Promise<any> {
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
        resolve(true);
      } catch (error) {
        reject(error);
      }
    });
  };
}
