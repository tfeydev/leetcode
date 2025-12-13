import { Component } from '@angular/core';
import { PageLayout } from '../page-layout/page-layout';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-content',
  imports: [CommonModule, PageLayout, MatButtonModule],
  templateUrl: './content.html',
  styleUrl: './content.css',
})
export class Content { }