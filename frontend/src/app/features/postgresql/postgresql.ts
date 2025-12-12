import { Component, inject, OnDestroy, OnInit, ChangeDetectorRef } from '@angular/core';
import { SqlFilesService, SqlFile } from '../../services/postgresql/sql-files.service';
import { CommonModule } from '@angular/common';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-postgresql',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './postgresql.html'
})
export class Postgresql implements OnInit, OnDestroy {
  private service = inject(SqlFilesService);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);
  private destroy$ = new Subject<void>();

  rows: SqlFile[] = [];
  loading = false;
  error = false;

  selectedFile: string | null = null;
  modalType: 'sql' | 'result' | null = null;

  sqlContent: string | null = null;
  executionResult: any[] = [];

  ngOnInit() {
    this.reload();

    this.route.queryParams
      .pipe(takeUntil(this.destroy$))
      .subscribe(params => {
        if (params['path']) {
          this.openModal('sql', params['path']);
        }
      });
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
          this.cdr.detectChanges();
        },
        error: () => {
          this.rows = [];
          this.error = true;
          this.loading = false;
          this.cdr.detectChanges();
        }
      });
  }

  openModal(type: 'sql' | 'result', path: string): void {
    this.selectedFile = path;
    this.modalType = type;
  
    if (type === 'sql') {
      this.service.getSqlContent(path)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (content) => this.sqlContent = content,
          error: (err) => {
            console.error('Error loading SQL content', err);
            this.sqlContent = 'Could not load SQL solution.';
          }
        });
    } else {
      this.service.executeProblem(path)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => this.executionResult = result,
          error: (err) => {
            console.error('Error executing SQL', err);
            this.executionResult = [{ error: 'Execution failed.' }];
          }
        });
    }
  }

  closeModal(): void {
    this.modalType = null;
    this.selectedFile = null;
    this.sqlContent = null;
    this.executionResult = [];
  }

  trackByProblem(index: number, item: SqlFile) {
    return item.problem;
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
