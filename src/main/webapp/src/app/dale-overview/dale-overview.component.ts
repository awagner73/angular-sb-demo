import {Component, OnInit} from '@angular/core';
import {DarlehenService} from '../darlehen.service';
import {DaleOverview} from '../dale-overview';
import {BehaviorSubject, Observable} from 'rxjs';
import {Darlehen} from '../darlehen';
import {switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-dale-overview',
  templateUrl: './dale-overview.component.html',
  styleUrls: ['./dale-overview.component.css']
})
export class DaleOverviewComponent implements OnInit {

  private refresh$ = new BehaviorSubject(undefined);
  daleOverview$: Observable<DaleOverview>;

  constructor(private daleOverviewService: DarlehenService) {
  }

  ngOnInit(): void {
    this.daleOverview$ = this.refresh$.pipe(switchMap(() => this.daleOverviewService.getDaleOverview()));
  }

  deleteDarlehen(event: Darlehen): void {
    this.daleOverviewService.deleteDarlehen(event).subscribe(() => this.refresh$.next(undefined));
  }
}
