import { TypeFilterEnum } from '../type-filter-enum';
import { TypePresentationEnum } from '../type-presentation-enum';
import { FilterCoarse } from './filter-coarse';
import { FilterFine } from './filter-fine';

export interface FilterCore {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  specification?: string; // url from the hal representation
  snrCoarseFiltersUrl?: string;
  snrFineFiltersUrl?: string;

  specId: number;
  typeFilter: TypeFilterEnum;
  typePresentation: TypePresentationEnum;
  hint: string;
  orderNum: number;

  coarseFilters?: Array<FilterCoarse>;

  actionRequired: boolean;
  fineFilterRowCount: number;
  fineFilterRowCountUpdate: Date;
  fineFilters?: Array<FilterFine>;
  etag?: string;
}
