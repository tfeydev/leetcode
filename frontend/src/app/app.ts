import { Component, signal } from '@angular/core';
import { Banner } from './layout/banner/banner';
import { Footer } from './layout/footer/footer';
import { Navbar } from './layout/navbar/navbar';
import { RouterOutlet } from '@angular/router'
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [
    CommonModule,
    RouterOutlet,
    Banner, 
    Navbar,
    Footer, 
  ],
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
}
