import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import Prism from 'prismjs';
import 'prismjs/components/prism-sql';

@Component({
  selector: 'app-db-problem-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './db-problem-modal.html',
})
export class DbProblemModal {
  @Input() type!: 'sql' | 'result';
  @Input() file: string = '';
  @Input() sqlContent: string = '';
  @Input() executionResult: any[] = [];
  @Input() executionColumns: string[] = [];
  @Output() close = new EventEmitter<void>();

  highlightSql(text: string) {
    return Prism.highlight(text.trimStart(), Prism.languages['sql'], 'sql');
  }
}
