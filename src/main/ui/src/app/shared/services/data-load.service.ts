import { Injectable } from '@angular/core';
import { DataLoadOptions } from '../models/data-load-options';
import { forkJoin, Observable } from 'rxjs';
import { DataLoadEntry } from '../models/data-load-entry';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataLoadService {
  constructor() {
  }

  loadData(ctx: any, options: DataLoadOptions): Observable<any> {
    let loadSources: any = [];
    let optionsCopy = { ...options };
    optionsCopy.loadEntries.forEach( entry => {
      if (entry.isActive && entry.isActive(ctx) === true ) {
        loadSources = [...loadSources, entry.call];
      }
    });
    console.log('loadSources', loadSources);
    return forkJoin(loadSources)
      .pipe(
        tap(results => {
          if (!results) {
            return;
          }
          let index: number = 0;
          results.forEach((data: any) => {
            let entry: DataLoadEntry = optionsCopy.loadEntries[index];
            while (index < optionsCopy.loadEntries.length
              && entry.isActive && entry.isActive(ctx) !== true)  { // find next active entry
              index++;
              console.log('retry ', index);
              entry = optionsCopy.loadEntries[index];
            }
            console.log('each result ', index, data, entry);
            entry.dataFunc(ctx, data);
            index++;
          });
        }
      ));
  }
}
