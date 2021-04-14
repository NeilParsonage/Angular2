import { KeycloakService } from 'keycloak-angular';


export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<void> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: 'pub/keycloak',
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: false
          },
          bearerExcludedUrls: []
        });
        resolve();
      } catch (error) {
        reject(error);
      }
    });
  };
}
