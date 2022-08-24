import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { searchOption } from '../../../models/searchOption';
@Component({
  selector: 'app-einzelauskunft-search',
  templateUrl: './einzelauskunft_search.component.html',
  styleUrls: ['./einzelauskunft_search.component.scss'],
})
export class EinzelauskunftSearchComponent implements OnInit {
  options = [
    { value: 'pnr', label: 'PNR', key: 'PNR', filter: '' },
    { value: 'gesamt', label: 'Gesamt lfd. Nr.', key: 'Gesamt lfd. Nr.', filter: '' },
    { value: 'fhi', label: 'Ist lfd. FHI', key: 'Ist lfd. FHI', filter: '' },
    { value: 'lmt1', label: 'Ist lfd. LMT Band 1', key: 'Ist lfd. LMT Band 1', filter: '' },
    { value: 'lmt2', label: 'Ist lfd. LMT Band 2', key: 'Ist lfd. LMT Band 2', filter: '' },
    { value: 'lmt3', label: 'Ist lfd. LMT Band 3', key: 'Ist lfd. LMT Band 3', filter: '' },
  ];

  selected = this.options[0];

  @Output() selectedSearch = new EventEmitter<any>();

  ngOnInit(): void {
    console.log('on init');
  }

  search(): void {
    console.log('Suche ' + this.selected.value + ' ' + this.selected.filter);
    let selectedOption: searchOption = new searchOption(this.selected.value, this.selected.label, this.selected.key, this.selected.filter);
    this.selectedSearch.emit(selectedOption);
  }
}
