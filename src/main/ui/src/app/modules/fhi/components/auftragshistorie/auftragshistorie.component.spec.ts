import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuftragshistorieComponent } from './augtragshistorie.component';

describe('AuftragshistorieComponent', () => {
  let component: AuftragshistorieComponent;
  let fixture: ComponentFixture<AuftragshistorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AuftragshistorieComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuftragshistorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
