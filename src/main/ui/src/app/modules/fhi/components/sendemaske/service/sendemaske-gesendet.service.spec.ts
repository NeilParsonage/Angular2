import { TestBed } from '@angular/core/testing';
import { SendemaskeGesendetService } from './sendemaske-gesendet.service';

describe('SendemaskeGesendetServiceService', () => {
  let service: SendemaskeGesendetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendemaskeGesendetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
