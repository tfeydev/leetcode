import { Component, signal } from '@angular/core';
import { Banner } from './layout/banner/banner';
import { Footer } from './layout/footer/footer';
import { Navbar } from './layout/navbar/navbar';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [
    RouterModule,
    RouterOutlet,
    Banner, 
    Navbar,
    Footer, 
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
