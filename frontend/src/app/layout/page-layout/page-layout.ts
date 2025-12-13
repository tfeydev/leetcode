import { Component, Input, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';

type BannerSize = 'large' | 'small';

@Component({
  selector: 'app-page-layout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './page-layout.html',
})
export class PageLayout implements OnInit {
  @Input() bannerSize: BannerSize = 'large';
  contentPaddingTop = signal('pt-12');

  ngOnInit(): void {
    this.calculatePadding();
  }

  calculatePadding(): void {
    if (this.bannerSize === 'large') {
      this.contentPaddingTop.set('pt-12');
    } else {
      this.contentPaddingTop.set('pt-24');
    }
  }
}
