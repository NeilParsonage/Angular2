import { Observable } from 'rxjs';

export interface DataLoadEntry {
  call: Observable<any>;
  dataFunc: (ctx: any, data: any) => void;
  isActive: (ctx: any) => boolean;
}
