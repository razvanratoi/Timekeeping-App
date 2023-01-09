import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreedaysComponent } from './freedays.component';

describe('FreedaysComponent', () => {
  let component: FreedaysComponent;
  let fixture: ComponentFixture<FreedaysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreedaysComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreedaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
