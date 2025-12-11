import { Component, inject, OnDestroy, OnInit, ChangeDetectorRef } from '@angular/core';
import { SqlFilesService, SqlFile } from '../../services/postgresql/sql-files.service';
import { CommonModule } from '@angular/common';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-postgresql',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './postgresql.html',
  styleUrls: ['./postgresql.css'],
})
export class Postgresql implements OnInit, OnDestroy {
  private service = inject(SqlFilesService);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);
  private destroy$ = new Subject<void>();

  rows: SqlFile[] = [];
  loading = false;
  error = false;

  ngOnInit() {
    console.log('Postgresql ngOnInit');
    this.reload();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  reload() {
    this.loading = true;
    this.error = false;

    this.service.getSqlFiles()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          this.rows = data;
          this.loading = false;
          this.error = false;
          this.cdr.detectChanges();
        },
        error: () => {
          this.rows = [];
          this.loading = false;
          this.error = true;
          this.cdr.detectChanges();
        }
      });
  }

  trackByProblem(index: number, item: SqlFile) {
    return item.problem;
  }
}
