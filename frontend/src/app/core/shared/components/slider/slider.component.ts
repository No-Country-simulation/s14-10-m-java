import { AfterViewInit, Component, ElementRef, EventEmitter, HostListener, OnInit, Output, ViewChild } from '@angular/core';
import { Specialty } from './interface/specialty.interface';
import { Specialties } from './specialties-data';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit, AfterViewInit {

  specialties: Specialty[] = [];
  sliderContainerWidth = 0;
  slideWidth = 0;
  elementsToShow = 1;
  sliderWidth = 0;
  sliderMarginLeft = 0;
  currentSlideIndex = 0;  // Keep track of current slide position (circular)
  centerIndex = 3;
  @Output() selectedSpecialty = new EventEmitter<Specialty>();

  

  @ViewChild('slideContainer')
  slideContainer!: ElementRef;

  @HostListener('window:resize', ['$event'])
  onScreenResize(event: any){
      this.setUpSlider();
  }

  constructor(){}


  ngAfterViewInit(): void {
    this.setUpSlider();
  }

  ngOnInit(): void {
    this.specialties = Specialties.concat(Specialties);

    }


  setUpSlider(){
    if(window.innerWidth<800){
      this.elementsToShow = 1;
      }else if(window.innerWidth<1200){
        this.elementsToShow = 3;
        }else if(window.innerWidth<1600){
          this.elementsToShow = 5;
          }else {
            this.elementsToShow = 7;
          }

    let container = this.slideContainer.nativeElement as HTMLElement;

    this.sliderContainerWidth = container.clientWidth;
    this.slideWidth = this.sliderContainerWidth/this.elementsToShow;

    this.sliderWidth = this.slideWidth*this.specialties.length;
    
  
  }


  updateSliderPosition() {
    this.sliderMarginLeft = -this.currentSlideIndex * this.slideWidth;
    if(this.sliderMarginLeft>0){
      this.sliderMarginLeft -= 1670;
    }
  }

  selectSpecialty(specialty: Specialty) {
    const selectedIndex = this.specialties.findIndex(s => s === specialty);
    
    let movementsNeeded = this.centerIndex - selectedIndex;
    this.centerIndex -= movementsNeeded;

    if(movementsNeeded>0){
      this.currentSlideIndex = (this.currentSlideIndex - movementsNeeded);

    } else {
      this.currentSlideIndex = (this.currentSlideIndex - movementsNeeded);

    }
    
    this.selectedSpecialty.emit(specialty);

    // Actualizamos la posición del carrusel
    this.updateSliderPosition();
  }
  isCenter(index: number): boolean {
    if(index==9 && this.centerIndex== 0){
      return true;
    }else if(index==10 && this.centerIndex== 1){
      return true;
    }else if(index==11 && this.centerIndex== 2){
      return true;
    }
   return index === this.centerIndex;
  }
}
  