import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SendemaskeDebugComponent } from './sendemaske-debug.component';

describe('SendemaskeComponent', () => {
  let component: SendemaskeDebugComponent;
  let fixture: ComponentFixture<SendemaskeDebugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SendemaskeDebugComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendemaskeDebugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
