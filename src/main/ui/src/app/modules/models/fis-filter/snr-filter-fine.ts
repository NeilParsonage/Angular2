import { TypeFilterEnum } from '../type-filter-enum';
import { FilterFine } from './filter-fine';

export class SnrFilterFine implements FilterFine {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  coreFilterUrl?: string;

  typeFilter: TypeFilterEnum.SNR;

  selected: boolean;
  pristine: boolean;

  fp: string;
  snr: string;
  tfam: string;
  vo: string;
  description: string;
  etag?: string;
}
