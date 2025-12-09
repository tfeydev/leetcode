import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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
  private baseUrl = 'https://163.176.159.220/api/sql-files';

  getSqlFiles(): Observable<SqlFile[]> {
    return this.http.get<string[]>(this.baseUrl).pipe(
      map(files => files.map(path => {
        const parts = path.split('/');
        return {
          difficulty: parts[1],
          problem: parts[2]
        } as SqlFile;
      })),
      catchError(err => {
        console.error('Error loading SQL files', err);
        return of([]);
      })
    );
  }
}
