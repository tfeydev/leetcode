import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { PageLayout } from '../../layout/page-layout/page-layout';

@Component({
  selector: 'app-java',
  imports: [CommonModule, RouterOutlet, PageLayout],
  templateUrl: './java.html',
  styleUrl: './java.css',
})
export class Java {

}
