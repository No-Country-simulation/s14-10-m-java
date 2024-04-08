import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements AfterViewInit {


  @ViewChild('slider')
  slider!:ElementRef;

  ngAfterViewInit(): void {
      
  }
}
