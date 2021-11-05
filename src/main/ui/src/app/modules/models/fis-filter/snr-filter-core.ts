import { TypeFilterEnum } from '../type-filter-enum';
import { TypePresentationEnum } from '../type-presentation-enum';
import { FilterCore } from './filter-core';
import { SnrFilterCoarse } from './snr-filter-coarse';
import { SnrFilterFine } from './snr-filter-fine';

export class SnrFilterCore implements FilterCore {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  specId: number;

  typeFilter: TypeFilterEnum;
  typePresentation: TypePresentationEnum;
  hint: string;
  orderNum: number;
  actionRequired: boolean;

  coarseFilters: Array<SnrFilterCoarse>;

  fineFilterRowCount: number;
  fineFilterRowCountUpdate: Date;
  fineFilters: Array<SnrFilterFine>;
  etag?: string;
}
