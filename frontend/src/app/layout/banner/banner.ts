// src/app/banner/banner.ts

import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Wichtig für Standalone

// Deine Definitionen
type BannerSize = 'large' | 'small';

@Component({
  selector: 'app-banner',
  standalone: true, // 💥 Hinzugefügt: Standalone Component
  imports: [CommonModule], // 💥 Hinzugefügt: Für Standalone notwendig
  templateUrl: './banner.html',
  styleUrls: ['./banner.css'],
})
export class Banner implements OnInit { // 💥 Deine Konvention: export class Banner
    
  // Input property to determine the size of the banner
  @Input() size: BannerSize = 'large'; 

  // Variable to hold the dynamically generated Tailwind CSS classes for the wrapper div
  // Die Variable ist im Template mit [class]="wrapperClasses" gebunden.
  wrapperClasses = ''; 

  /**
   * Initializes the wrapper classes based on the 'size' input.
   */
  ngOnInit(): void {
    // Base classes ensure the wrapper takes full width and hides any overflow (wichtig für object-cover)
    const baseClasses = 'w-full overflow-hidden'; 

    // 💥 Logik zur Layout-Stabilisierung:
    // Wir nutzen h-[15vh], min-h-40 (large) oder h-[8vh], min-h-32 (small)
    // Diese Kombination reserviert Platz (min-h) und nutzt den Viewport (vh) für responsive Größe.
    if (this.size === 'large') {
      this.wrapperClasses = `${baseClasses} h-[15vh] max-h-[15vh] min-h-40`;
    } else { // size === 'small'
      this.wrapperClasses = `${baseClasses} h-[8vh] max-h-[8vh] min-h-32`;
    }
  }
}