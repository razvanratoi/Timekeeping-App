import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedHourDetailComponent } from './logged-hour-detail.component';

describe('LoggedHourDetailComponent', () => {
  let component: LoggedHourDetailComponent;
  let fixture: ComponentFixture<LoggedHourDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoggedHourDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedHourDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
