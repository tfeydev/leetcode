import { Component, inject } from '@angular/core';
import { SqlFilesService, SqlFile } from '../../services/postgresql/sql-files.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-postgresql',
  imports: [CommonModule],
  templateUrl: './postgresql.html',
  styleUrl: './postgresql.css',
})
export class Postgresql {
  private service = inject(SqlFilesService);
  rows: SqlFile[] = [];
  loading = true;
  error = false;

  ngOnInit() {
    this.service.getSqlFiles().subscribe({
      next: (data) => {
        this.rows = data;
        this.loading = false;
      },
      error: () => {
        this.error = true;
        this.loading = false;
      },
    });
  }
}
