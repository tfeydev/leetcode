import { Routes } from '@angular/router';
import { Content } from './layout/content/content';
import { Postgresql } from './features/postgresql/postgresql';
import { Concurrency } from './features/java/concurrency/concurrency';
import { Algorithms } from './features/java/algorithms/algorithms';
import { Java } from './features/java/java';
import { Javacontent } from './features/java/javacontent/javacontent';

export const routes: Routes = [
  {
    path: '',
    component: Content,
    title: 'TechThorDev - LeetCode',
  },
  {
    path: 'postgresql',
    component: Postgresql,
    title: 'LeetCode - PostgreSQL Problems',
  },
  {
    path: 'java',
    component: Java,
    title: 'LeetCode - Java Problems',
    children: [
      {
        path: '',
        component: Javacontent,
        pathMatch: 'full',
      },
      {
        path: 'algorithms',
        component: Algorithms,
        title: 'LeetCode - Java Algorithms Problems',
      },
      {
        path: 'concurrency',
        component: Concurrency,
        title: 'LeetCode - Java Concurrency Problems',
      },
    ],
  },

  { path: '**', redirectTo: '', pathMatch: 'full' },
];
