import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchBarComponent } from './search-bar.component';



@NgModule({
    declarations: [
        SearchBarComponent
    ],
    exports: [
        SearchBarComponent
    ],
    imports: [
        CommonModule
    ]
})
export class SearchBarModule { }
