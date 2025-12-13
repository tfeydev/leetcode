import { Component, inject, OnDestroy, OnInit, ChangeDetectorRef } from '@angular/core';
import { SqlFilesService, SqlFile } from '../../services/postgresql/sql-files.service';
import { CommonModule } from '@angular/common';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { DbProblemModal } from '../postgresql/db-problem-modal/db-problem-modal';
import { PageLayout } from '../../layout/page-layout/page-layout';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-postgresql',
  standalone: true,
  imports: [
    CommonModule,
    PageLayout,
    DbProblemModal,
    MatIconModule,
    MatTableModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  templateUrl: './postgresql.html',
})
export class Postgresql implements OnInit, OnDestroy {
  private service = inject(SqlFilesService);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);
  private destroy$ = new Subject<void>();

  dataSource = new MatTableDataSource<SqlFile>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['difficulty', 'problem', 'actions'];

  modalLoading: boolean = false;
  
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

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
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
          this.dataSource.data = data;
          this.loading = false;
          this.cdr.detectChanges();
        },
        error: () => {
          this.rows = [];
          this.dataSource.data = [];
          this.error = true;
          this.loading = false;
          this.cdr.detectChanges();
        },
      });
  }

  openModal(type: 'sql' | 'result', path: string): void {
    this.selectedFile = path;
    this.modalType = type;
    this.modalLoading = true;
    this.executionColumns = [];
    this.executionResult = [];
    this.sqlContent = '';

    if (type === 'sql') {
      this.service.getSqlContent(path).subscribe({
        next: (content) => {
          this.sqlContent = content;
          this.modalLoading = false;
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('Error loading SQL content', err);
          this.sqlContent = 'Could not load SQL solution.';
          this.cdr.detectChanges();
        },
      });
    } else {
      this.service.executeProblem(path).subscribe({
        next: (result) => {
          this.executionResult = result;
          if (result && result.length > 0) {
            this.executionColumns = Object.keys(result[0]);
          } else {
            this.executionColumns = [];
          }
          this.modalLoading = false;
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('Error executing SQL', err);
          this.executionResult = [{ error: 'Execution failed.' }];
          this.modalLoading = false;
          this.cdr.detectChanges();
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
