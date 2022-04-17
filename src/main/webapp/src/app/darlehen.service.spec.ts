import {TestBed} from '@angular/core/testing';

import {DarlehenService} from './darlehen.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('DaleOverviewService', () => {
  let httpTestingController: HttpTestingController;
  let service: DarlehenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(DarlehenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should perform a get request', () => {

    service.getDaleOverview().subscribe(data => {
      expect(data.darlehen.length).toBe(1);
      expect(data.darlehen[0].anwender).toBe('Anwender');
    });

    const request = httpTestingController.expectOne('http://localhost:8080/api/dale/overview');

    expect(request.request.method).toBe('GET');

    request.flush({
      darlehen: [{anwender: 'Anwender'}]
    });
  });

  it('should perform a post request', () => {

    service.createDarlehen({
      id: 1,
      status: 'ARCHIV',
      darlehensbetrag: 10000,
      dalenummerbank: '123',
      anwender: 'Anw',
      festzinsdatum: '55',
      laufzeitende: '44'
    }).subscribe(data => {
      expect(data.success).toBe(true);
    });

    const request = httpTestingController.expectOne('http://localhost:8080/api/dale/create');

    expect(request.request.method).toBe('POST');

    request.flush({
      success: true
    });
  });
});
