import { CommonModule, TitleCasePipe } from '@angular/common'; // TitleCasePipe wird im Template benötigt
import { Component, Input, Output, EventEmitter } from '@angular/core';
import Prism from 'prismjs';
import 'prismjs/components/prism-sql';

// NEUE IMPORTE FÜR MATERIAL DESIGN
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card'; // Für den Modal-Container
import { MatTableModule, MatTableDataSource } from '@angular/material/table'; // Für die Ergebnistabelle
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-db-problem-modal',
  standalone: true,
  imports: [
    CommonModule,
    TitleCasePipe, // Wird für Spaltennamen verwendet
    // Material Module
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatTableModule,
    MatProgressSpinnerModule 
  ],
  templateUrl: './db-problem-modal.html',
})
export class DbProblemModal {
  @Input() type!: 'sql' | 'result';
  @Input() file: string = '';
  @Input() sqlContent: string = '';
  
  private _executionResult: any[] = [];
  public resultDataSource = new MatTableDataSource<any>([]);
  
  @Input() loading: boolean = false;

  @Input() 
  set executionResult(data: any[]) {
      this._executionResult = data;
      this.resultDataSource.data = data;
  }
  get executionResult(): any[] {
      return this._executionResult;
  }

  @Input() executionColumns: string[] = [];
  @Output() close = new EventEmitter<void>();

  // Die Spalten, die in der mat-table angezeigt werden sollen, sind die executionColumns
  get displayedResultColumns(): string[] {
    return this.executionColumns;
  }

  highlightSql(text: string) {
    return Prism.highlight(text.trimStart(), Prism.languages['sql'], 'sql');
  }
}