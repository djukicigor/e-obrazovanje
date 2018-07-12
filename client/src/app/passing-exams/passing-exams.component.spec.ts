import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassingExamsComponent } from './passing-exams.component';

describe('PassingExamsComponent', () => {
  let component: PassingExamsComponent;
  let fixture: ComponentFixture<PassingExamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassingExamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassingExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
