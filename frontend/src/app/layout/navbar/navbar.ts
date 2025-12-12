import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

interface NavLink {
    title: string;
    path: string;
    isExternal: boolean;
    isSubMenuOpen?: boolean;
    subLinks?: { title: string; path: string; isExternal: boolean }[];
}

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  isMenuOpen: boolean = false;
  
  navLinks: NavLink[] = [ 
      { title: 'Home', path: '/', isExternal: false },
      { title: 'PostgreSQL', path: 'postgresql', isExternal: false },
      { title: 'Java', path: 'java', isExternal: false, isSubMenuOpen: false,
        subLinks: [
          { title: 'Algorithms', path: 'java/algorithms', isExternal: false },
          { title: 'Concurrency', path: 'java/concurrency', isExternal: false },
        ]}
    ];

  constructor() {}

  ngOnInit(): void {}

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }
  
  toggleSubmenu(link: NavLink): void {
      if (link.subLinks) {
        this.navLinks.forEach(l => {
            if (l !== link && l.subLinks) {
              l.isSubMenuOpen = false;
            }
          });
        link.isSubMenuOpen = !link.isSubMenuOpen;
      }
    }
}
