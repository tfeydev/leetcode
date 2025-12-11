import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

export interface SqlFile {
  difficulty: string;
  problem: string;
}

@Injectable({
  providedIn: 'root'
})
export class SqlFilesService {
  private http = inject(HttpClient);
  private baseUrl = 'https://api.techthordev.com.br/sql-files';

  getSqlFiles(): Observable<SqlFile[]> {
    return this.http.get<any>(this.baseUrl).pipe(
      map(files => {
        if (!Array.isArray(files)) {
          return [];
        }
        return files.map(path => {
          const parts = String(path).split('/').filter(Boolean);
          return {
            difficulty: parts[1] ?? parts[0] ?? '',
            problem: parts[2] ?? parts[1] ?? ''
          } as SqlFile;
        });
      }),
      catchError(err => throwError(() => err))
    );
  }
}
