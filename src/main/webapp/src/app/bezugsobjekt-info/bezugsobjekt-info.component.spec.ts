import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BezugsobjektInfoComponent } from './bezugsobjekt-info.component';

describe('BezugsobjektInfoComponent', () => {
  let component: BezugsobjektInfoComponent;
  let fixture: ComponentFixture<BezugsobjektInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BezugsobjektInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BezugsobjektInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
