import { AfterViewInit, Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { Specialty } from './interface/specialty.interface';
import { Specialties } from './specialties-data';

@Component({
  selector: 'app-specialties',
  templateUrl: './specialties.component.html',
  styleUrls: ['./specialties.component.scss']
})
export class SpecialtyComponent implements OnInit, AfterViewInit {

  specialties: Specialty[] = [];
  sliderContainerWidth = 0;
  slideWidth = 0;
  elementsToShow = 1;
  sliderWidth = 0;
  sliderMarginLeft = 0;



  @HostListener('window:resize', ['$event'])
  onScreenResize(event: any){
      this.setUpSlider();
  }

  constructor(){}
  @ViewChild('slideContainer')
  slideContainer!: ElementRef;
  ngAfterViewInit(): void {
    this.setUpSlider();
  }

  ngOnInit(): void {
    this.specialties = Specialties;
  }


  setUpSlider(){
    if(window.innerWidth<600)
      this.elementsToShow = 1;
    else if(window.innerWidth<1000)
      this.elementsToShow = 3;
    else if(window.innerWidth<1200)
      this.elementsToShow = 5;
    else 
      this.elementsToShow = 7;

    let container = this.slideContainer.nativeElement as HTMLElement;

    this.sliderContainerWidth = container.clientWidth;
    this.slideWidth = this.sliderContainerWidth/this.elementsToShow;
    this.sliderWidth = this.slideWidth*this.specialties.length;
  }

  prev(){
    if(this.sliderMarginLeft===0){
      return
    }
    this.sliderMarginLeft = this.sliderMarginLeft+this.slideWidth;

  }

  next(){
    const notShowingElementsCount = this.specialties.length-this.elementsToShow;
    const possibleMargin = -(notShowingElementsCount*this.slideWidth);

    if(this.sliderMarginLeft <= possibleMargin){
      return
    }
    this.sliderMarginLeft = this.sliderMarginLeft-this.slideWidth;
  }
}
  