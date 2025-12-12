import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Concurrency } from './concurrency';

describe('Concurrency', () => {
  let component: Concurrency;
  let fixture: ComponentFixture<Concurrency>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Concurrency]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Concurrency);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
