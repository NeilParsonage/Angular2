import { TestBed } from '@angular/core/testing';

import { SendemaskeUngesendetService } from './sendemaske-ungesendet.service';

describe('SendemaskeUngesendetService', () => {
  let service: SendemaskeUngesendetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendemaskeUngesendetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
