import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LectureExamsComponent } from './lecture-exams.component';

describe('LectureExamsComponent', () => {
  let component: LectureExamsComponent;
  let fixture: ComponentFixture<LectureExamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LectureExamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LectureExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
