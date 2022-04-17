import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RowInfoComponent } from './row-info.component';

describe('RowInfoComponent', () => {
  let component: RowInfoComponent;
  let fixture: ComponentFixture<RowInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RowInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RowInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
