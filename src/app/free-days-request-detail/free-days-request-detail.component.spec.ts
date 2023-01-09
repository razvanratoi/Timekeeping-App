import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreeDaysRequestDetailComponent } from './free-days-request-detail.component';

describe('FreeDaysRequestDetailComponent', () => {
  let component: FreeDaysRequestDetailComponent;
  let fixture: ComponentFixture<FreeDaysRequestDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreeDaysRequestDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreeDaysRequestDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
