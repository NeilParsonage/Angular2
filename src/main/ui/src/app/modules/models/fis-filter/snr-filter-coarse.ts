import { TypeFilterEnum } from '../type-filter-enum';
import { FilterCoarse } from './filter-coarse';

export class SnrFilterCoarse implements FilterCoarse {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  coreFilter?: string;

  typeFilter: TypeFilterEnum;

  fp: string;
  snr: string;
  tfam: string;
  vo: string;
  etag?: string;
}
