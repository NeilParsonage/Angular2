import { RestLinks } from './rest-links';
import { RestPage } from './rest-page';

export interface RestResult {
  _embedded: any;
  _links?: RestLinks;
  page?: RestPage;
}
