import { TypeFilterEnum } from '../type-filter-enum';

export interface FilterCoarse {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  coreFilter?: string;

  typeFilter: TypeFilterEnum;
  etag?: string;
}
