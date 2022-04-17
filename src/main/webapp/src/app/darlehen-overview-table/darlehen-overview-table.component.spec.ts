import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DarlehenOverviewTableComponent} from './darlehen-overview-table.component';

describe('DarlehenOverviewTableComponent', () => {
    let component: DarlehenOverviewTableComponent;
    let fixture: ComponentFixture<DarlehenOverviewTableComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [DarlehenOverviewTableComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(DarlehenOverviewTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should render the table', () => {
        component.darlehen = [{
            id: 1,
            status: 'ARCHIV',
            darlehensbetrag: 10000,
            dalenummerbank: '123',
            anwender: 'Anw',
            festzinsdatum: '55',
            laufzeitende: '44'
        }];

        fixture.detectChanges();

        const tableRows = fixture.nativeElement.querySelectorAll('tbody tr');
        expect(tableRows.length).toBe(1);

        const anwenderSpalte = fixture.nativeElement.querySelector('tbody tr td:first-child');
        expect(anwenderSpalte.textContent).toBe('Anw');
    });
});
