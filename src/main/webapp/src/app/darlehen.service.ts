import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DaleOverview} from './dale-overview';
import {Observable} from 'rxjs';
import {Darlehen} from './darlehen';
import {environment} from '../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class DarlehenService {

    constructor(private http: HttpClient) {
    }

    getDaleOverview(): Observable<DaleOverview> {
        return this.http.get<DaleOverview>(`${environment.baseUrl}/api/dale/overview`);
    }

    createDarlehen(darlehen: Darlehen): Observable<any> {
        return this.http.post(`${environment.baseUrl}/api/dale/create`, darlehen);
    }

    deleteDarlehen(darlehen: Darlehen): Observable<any> {
        return this.http.delete(`${environment.baseUrl}/api/dale/delete/${darlehen.id}`);
    }
}
