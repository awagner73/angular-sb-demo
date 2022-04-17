import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Darlehen} from '../darlehen';

@Component({
    selector: 'app-darlehen-overview-table',
    templateUrl: './darlehen-overview-table.component.html',
    styleUrls: ['./darlehen-overview-table.component.css']
})
export class DarlehenOverviewTableComponent implements OnInit {

    private statusBeschreibungDict = {
        PLAN: 'Plan',
        GUELTIG: 'Gültig',
        ARCHIV: 'Archiv',
    };

    @Input()
    darlehen: Darlehen[];

    @Output()
    action = new EventEmitter<Darlehen>();

    constructor() {
    }

    ngOnInit(): void {
    }

    statusBeschreibung(status: string): string {
        return this.statusBeschreibungDict[status];
    }

    clickX(darlehen: Darlehen): void {
        this.action.emit(darlehen);
    }

}
