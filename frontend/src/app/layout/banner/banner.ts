import { Component, Input, OnInit } from '@angular/core';

// Define the possible banner sizes for better type safety and clarity
type BannerSize = 'large' | 'small';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.html',
  styleUrls: ['./banner.css'],
})
export class Banner implements OnInit { // Changed class name convention to BannerComponent
  // Input property to determine the size of the banner
  @Input() size: BannerSize = 'large'; 

  // Variable to hold the dynamically generated Tailwind CSS classes for the wrapper div
  wrapperClasses = ''; // Renamed for better English readability

  /**
   * Initializes the wrapper classes based on the 'size' input.
   */
  ngOnInit(): void {
    // Base classes ensure the wrapper takes full width and hides any overflow (important with object-cover)
    const baseClasses = 'w-full overflow-hidden'; 

    if (this.size === 'large') {
      // Large size: Set height to 15vh (15% of viewport height).
      // max-h-[15vh] ensures it never exceeds this height on large screens.
      // min-h-40 (10rem) ensures a minimum height on very small viewports.
      this.wrapperClasses = `${baseClasses} h-[15vh] max-h-[15vh] min-h-40`;
    } else { // size === 'small'
      // Small size: Set height to 8vh (8% of viewport height).
      // max-h-[8vh] caps the height.
      // min-h-32 (8rem) ensures a minimum height.
      this.wrapperClasses = `${baseClasses} h-[8vh] max-h-[8vh] min-h-32`;
    }
  }
}