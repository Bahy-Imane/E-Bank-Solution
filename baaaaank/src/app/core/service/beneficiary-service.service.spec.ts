import { TestBed } from '@angular/core/testing';

import { BeneficiaryServiceService } from './beneficiary-service.service';

describe('BeneficiaryServiceService', () => {
  let service: BeneficiaryServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeneficiaryServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
