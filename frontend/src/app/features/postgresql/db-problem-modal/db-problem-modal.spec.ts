import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DbProblemModal } from './db-problem-modal';

describe('DbProblemModal', () => {
  let component: DbProblemModal;
  let fixture: ComponentFixture<DbProblemModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DbProblemModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DbProblemModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
