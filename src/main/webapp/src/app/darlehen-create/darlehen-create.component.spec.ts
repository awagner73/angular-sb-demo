import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DarlehenCreateComponent} from './darlehen-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DarlehenService} from '../darlehen.service';
import {Darlehen} from '../darlehen';
import {of} from 'rxjs';

describe('DarlehenCreateComponent', () => {
  let component: DarlehenCreateComponent;
  let fixture: ComponentFixture<DarlehenCreateComponent>;
  let darlehenServiceStub: Partial<DarlehenService> & {created: boolean};

  beforeEach(async(() => {

    darlehenServiceStub = {
      created: false
    };

    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [DarlehenCreateComponent],
      providers: [{provide: DarlehenService, useValue: darlehenServiceStub}]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DarlehenCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    expect(component.darlehenForm.valid).toBeFalsy();
  });

  it('anwender field validity', () => {
    const anwender = component.darlehenForm.controls.anwender;
    const errors = anwender.errors || {};
    expect(anwender.valid).toBeFalsy();
    expect(errors.required).toBeTruthy();

    anwender.setValue('Anw');
    expect(anwender.valid).toBeTruthy();
  });

  it('laufzeit field validity', () => {
    const laufzeitende = component.darlehenForm.controls.laufzeitende;
    let errors = laufzeitende.errors || {};
    expect(laufzeitende.valid).toBeFalsy();
    expect(errors.required).toBeTruthy();

    laufzeitende.setValue('AA');
    errors = laufzeitende.errors || {};
    expect(laufzeitende.valid).toBeFalsy();
    expect(errors.required).toBeFalsy();
    expect(errors.pattern).toBeTruthy();

    laufzeitende.setValue('01.09.2020');
    expect(laufzeitende.valid).toBeTruthy();
  });

  it('complete form', () => {

    darlehenServiceStub.createDarlehen = (darlehen: Darlehen) => {
      darlehenServiceStub.created = true;
      return of();
    };

    component.darlehenForm.setValue({
      status: 'ARCHIV',
      darlehensbetrag: 10000,
      dalenummerbank: '123',
      anwender: 'Anw',
      festzinsdatum: '01.09.2020',
      laufzeitende: '02.09.2020',
      restschuld: ''
    });

    expect(component.darlehenForm.valid).toBeTruthy();

    component.submit();

    expect(darlehenServiceStub.created).toBeTruthy();
  });
});
