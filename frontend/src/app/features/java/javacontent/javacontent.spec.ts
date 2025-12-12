import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Javacontent } from './javacontent';

describe('Javacontent', () => {
  let component: Javacontent;
  let fixture: ComponentFixture<Javacontent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Javacontent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Javacontent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
