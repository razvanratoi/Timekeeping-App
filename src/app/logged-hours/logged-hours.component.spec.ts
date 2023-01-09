import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedHoursComponent } from './logged-hours.component';

describe('LoggedHoursComponent', () => {
  let component: LoggedHoursComponent;
  let fixture: ComponentFixture<LoggedHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoggedHoursComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
