import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchBarComponent } from './search-bar.component';
import { SharedModule } from "../../shared.module";



@NgModule({
    declarations: [
        SearchBarComponent
    ],
    exports: [
        SearchBarComponent
    ],
    imports: [
        CommonModule,
        SharedModule
    ]
})
export class SearchBarModule { }
