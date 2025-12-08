import { Routes } from '@angular/router';
import { Content } from './layout/content/content';
export const routes: Routes = [
  
  { path: '', component: Content, title: 'TechThorDev - LeetCode' },
  
  { path: '**', redirectTo: '', pathMatch: 'full' },
];
