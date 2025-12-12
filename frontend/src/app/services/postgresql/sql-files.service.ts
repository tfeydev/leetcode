import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

export interface SqlFile {
  difficulty: string;
  problem: string;
  fullPath: string;
}

@Injectable({ providedIn: 'root' })
export class SqlFilesService {
  private http = inject(HttpClient);
  private baseUrl = 'https://api.techthordev.com.br';

  getSqlFiles(): Observable<SqlFile[]> {
    return this.http.get<any[]>(`${this.baseUrl}/problems`).pipe(
      map(files => {
        return files.map(file => ({
          difficulty: file.problem ?? '',
          problem: file.path?.split('/')[2]?.replace('.sql', '') ?? '',
          fullPath: file.path ?? ''
        }));
      }),
      catchError(err => throwError(() => err))
    );
  }

  getSqlContent(path: string): Observable<string> {
    return this.http.get(`${this.baseUrl}/problems/sql`, {
      params: { path },
      responseType: 'text'
    });
  }

  executeProblem(path: string): Observable<any[]> {
    return this.http.post<any[]>(`${this.baseUrl}/problems/execute`, null, {
      params: { path }
    });
  }

}
