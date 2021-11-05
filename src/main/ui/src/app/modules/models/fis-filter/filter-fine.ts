import { TypeFilterEnum } from '../type-filter-enum';

export interface FilterFine {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  coreFilterUrl?: string;

  typeFilter: TypeFilterEnum;

  selected: boolean;
  pristine: boolean;
  etag?: string;
}
