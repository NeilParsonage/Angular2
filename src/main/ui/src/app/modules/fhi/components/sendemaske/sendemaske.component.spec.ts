import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendemaskeComponent } from './sendemaske.component';

describe('SendemaskeComponent', () => {
  let component: SendemaskeComponent;
  let fixture: ComponentFixture<SendemaskeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendemaskeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendemaskeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
