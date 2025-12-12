import { Component, inject, OnDestroy, OnInit, ChangeDetectorRef } from '@angular/core';
import { SqlFilesService, SqlFile } from '../../services/postgresql/sql-files.service';
import { CommonModule } from '@angular/common';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { DbProblemModal } from '../postgresql/db-problem-modal/db-problem-modal';
import { PageLayout } from '../../layout/page-layout/page-layout';

@Component({
  selector: 'app-postgresql',
  standalone: true,
  imports: [CommonModule, PageLayout, DbProblemModal],
  templateUrl: './postgresql.html',
})
export class Postgresql implements OnInit, OnDestroy {
  private service = inject(SqlFilesService);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);
  private destroy$ = new Subject<void>();

  rows: SqlFile[] = [];
  loading = false;
  error = false;

  selectedFile: string = '';
  modalType: 'sql' | 'result' | null = null;

  sqlContent: string = '';
  executionResult: any[] = [];
  executionColumns: string[] = [];

  ngOnInit() {
    this.reload();

    this.route.queryParams.pipe(takeUntil(this.destroy$)).subscribe((params) => {
      if (params['path']) {
        this.openModal('sql', params['path']);
      }
    });
  }

  reload() {
    this.loading = true;
    this.error = false;
    this.service
      .getSqlFiles()
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
        },
      });
  }

  openModal(type: 'sql' | 'result', path: string): void {
    this.selectedFile = path;
    this.modalType = type;
    this.executionColumns = [];
    this.executionResult = [];
    this.sqlContent = '';

    if (type === 'sql') {
      this.service.getSqlContent(path).subscribe({
        next: (content) => {
          this.sqlContent = content;
          this.cdr.detectChanges(); // <<< Hinzugefügte Lösung
        },
        error: (err) => {
          console.error('Error loading SQL content', err);
          this.sqlContent = 'Could not load SQL solution.';
          this.cdr.detectChanges(); // <<< Hinzugefügte Lösung
        },
      });
    } else {
      this.service.executeProblem(path).subscribe({
        next: (result) => {
          // <<< Muss jetzt { verwenden, um cdr.detectChanges() hinzuzufügen
          this.executionResult = result;
          if (result && result.length > 0) {
            this.executionColumns = Object.keys(result[0]);
          } else {
            this.executionColumns = [];
          }
          this.cdr.detectChanges(); // <<< Hinzugefügte Lösung
        },
        error: (err) => {
          console.error('Error executing SQL', err);
          this.executionResult = [{ error: 'Execution failed.' }];
          this.cdr.detectChanges(); // <<< Hinzugefügte Lösung
        },
      });
    }
  }

  closeModal(): void {
    this.modalType = null;
    this.selectedFile = '';
    this.sqlContent = '';
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
