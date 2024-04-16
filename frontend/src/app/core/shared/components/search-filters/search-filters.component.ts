import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-filters',
  templateUrl: './search-filters.component.html',
  styleUrls: ['./search-filters.component.scss']
})
export class SearchFiltersComponent {
  @Input() title?: string;
  @Input() options?: string[];
  showOptions: boolean = true;
  selectedFilter: string = '';
  @Output() applyFilter: EventEmitter<string> = new EventEmitter<string>();

  valueMapping: { [key: string]: string } = {
    'PSIQUIATRÍA': 'PSYCHIATRY',
    'TRAUMATOLOGÍA': 'TRAUMATOLOGY',
    'NEUROLOGÍA': 'NEUROLOGY',
    'CARDIOLOGÍA': 'CARDIOLOGY',
    'RADIOLOGÍA': 'RADIOLOGY',
    'PEDIATRÍA': 'PEDIATRICS',
    'GERIATRÍA': 'GERIATRICS',
    'DERMATOLOGÍA': 'DERMATOLOGY',
    'HEMATOLOGÍA': 'HEMATOLOGY'
  };

  applyFilterOption(option: string) {
    this.selectedFilter = this.valueMapping[option] || option;
    this.applyFilter.emit(this.selectedFilter);
    console.log(this.selectedFilter);
  }

  toggleOptionsVisibility() {
    this.showOptions = !this.showOptions;
  }
}