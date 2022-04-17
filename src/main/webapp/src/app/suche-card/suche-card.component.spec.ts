import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SucheCardComponent } from './suche-card.component';

describe('SucheCardComponent', () => {
  let component: SucheCardComponent;
  let fixture: ComponentFixture<SucheCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SucheCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SucheCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
