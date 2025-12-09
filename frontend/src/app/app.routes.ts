import { Routes } from '@angular/router';
import { Content } from './layout/content/content';
import { Postgresql } from './features/postgresql/postgresql';
export const routes: Routes = [
  { path: '', component: Content, title: 'TechThorDev - LeetCode' },
  { path: 'postgresql', component: Postgresql },

  { path: '**', redirectTo: '', pathMatch: 'full' },
];
