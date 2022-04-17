import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DaleOverviewComponent} from './dale-overview.component';
import {DarlehenService} from '../darlehen.service';
import {Darlehen} from '../darlehen';
import {defer, Observable} from 'rxjs';
import {Component, Input} from '@angular/core';
import {By} from '@angular/platform-browser';

@Component({selector: 'app-darlehen-overview-table', template: ''})
class DarlehenOverviewTableStubComponent {
    @Input()
    darlehen: Darlehen[];
}

describe('DaleOverviewComponent', () => {
    let component: DaleOverviewComponent;
    let fixture: ComponentFixture<DaleOverviewComponent>;
    let tableComponent: DarlehenOverviewTableStubComponent;

    beforeEach(async(() => {
        const service = jasmine.createSpyObj('DarlehenService', ['getDaleOverview']);
        service.getDaleOverview.and.returnValue(asyncData({
            darlehen: [{
                id: 1,
                status: 'ARCHIV',
                darlehensbetrag: 10000,
                dalenummerbank: '123',
                anwender: 'Anw',
                festzinsdatum: '55',
                laufzeitende: '44'
            }]
        }));
        TestBed.configureTestingModule({
            providers: [{provide: DarlehenService, useValue: service}],
            declarations: [DaleOverviewComponent, DarlehenOverviewTableStubComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(DaleOverviewComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should bind the data correctly', async(() => {

        fixture.whenStable().then(() => {
            fixture.detectChanges();

            const debugElement = fixture.debugElement.query(By.css('app-darlehen-overview-table'));
            expect(debugElement).toBeTruthy();

            tableComponent = debugElement.componentInstance;

            expect(tableComponent).toBeTruthy();

            expect(tableComponent.darlehen).toBeTruthy();
            expect(tableComponent.darlehen.length).toBe(1);

        });

    }));

});

function asyncData<T>(data: T): Observable<T> {
    return defer(() => {
        return Promise.resolve(data);
    });
}
