export interface Specification {
  id?: number;
  serverUrl?: string; // _self.url from the hal representation
  coreFiltersUrl?: string; // url to fetch the coreFilters of this spec

  name: string;
  description: string;
  owner: string;
  active: boolean;
  actionRequired: boolean;
  etag?: number;
}
