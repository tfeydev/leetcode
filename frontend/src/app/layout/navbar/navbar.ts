import { Component, OnInit, signal } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

// Angular Material Imports
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

interface NavLink {
  title: string;
  path: string;
  isExternal: boolean;
  subLinks?: { title: string; path: string; isExternal: boolean }[];
}

@Component({
  selector: 'app-navbar',
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive,
    // Angular Material Module
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
  ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  isMenuOpen = signal(false);
  activeSubLinks = signal<NavLink['subLinks']>([]);
  activeSubMenuLink = signal<NavLink | null>(null);

  navLinks = signal<NavLink[]>([
    { title: 'Home', path: '/', isExternal: false },
    { title: 'PostgreSQL', path: 'postgresql', isExternal: false },
    {
      title: 'Java',
      path: 'java',
      isExternal: false,
      subLinks: [
        { title: 'Algorithms', path: 'java/algorithms', isExternal: false },
        { title: 'Concurrency', path: 'java/concurrency', isExternal: false },
      ],
    },
  ]);

  constructor() {}

  ngOnInit(): void {}

  toggleMenu(): void {
    this.isMenuOpen.update((v) => !v);
    if (!this.isMenuOpen()) {
      this.activeSubMenuLink.set(null);
    }
  }

  setActiveSubLinks(subLinks: NavLink['subLinks']): void {
    this.activeSubLinks.set(subLinks || []);
  }

  toggleSubmenu(link: NavLink): void {
    if (!link.subLinks) return;

    if (this.activeSubMenuLink() === link) {
      this.activeSubMenuLink.set(null);
    } else {
      this.activeSubMenuLink.set(link);
    }
  }

  isSubMenuOpen(link: NavLink): boolean {
    return this.activeSubMenuLink() === link;
  }
}
