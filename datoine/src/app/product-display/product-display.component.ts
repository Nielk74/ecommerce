import { AsyncPipe } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ProductItemComponent } from './components/product-item/product-item.component';
import { ProductService } from './services/product.service';
import { ProductPanelComponent } from './components/product-panel/product-panel.component';
import { BehaviorSubject, combineLatest, map, Observable, Subject } from 'rxjs';
import { Product } from './models/Product.model';

export interface SortCriteria {
  attribute: string;
  asc: boolean;
}

export interface FilterCriteria {
  attribute: string;
  value: string;
}

@Component({
  selector: 'app-product-display',
  standalone: true,
  imports: [AsyncPipe, ProductItemComponent, ProductPanelComponent],
  templateUrl: './product-display.component.html',
  styleUrl: './product-display.component.scss',
})
export class ProductDisplayComponent implements OnInit {
  productService = inject(ProductService);
  filteredSortedProducts$!: Observable<Product[]>;

  filterSubject = new BehaviorSubject<FilterCriteria | null>(null);
  sortSubject = new BehaviorSubject<SortCriteria>({
    attribute: 'name',
    asc: true,
  });

  ngOnInit(): void {
    this.productService.fetchProducts();

    this.filteredSortedProducts$ = combineLatest([
      this.productService.products$,       // Original stream of products
      this.filterSubject,   // Filter criteria
      this.sortSubject,     // Sort criteria
    ]).pipe(
      map(([products, filter, sort]) => {
        // Apply filter if filter.value exists
        if (filter && filter.attribute && filter.value) {
          products = products.filter((product: Product) =>
            String(product[filter.attribute]) === String(filter.value)
          );
        }
    
        // Apply sorting logic
        products.sort((a, b) => {
          const aValue = a[sort.attribute];
          const bValue = b[sort.attribute];
    
          if (typeof aValue === 'number' && typeof bValue === 'number') {
            return sort.asc ? aValue - bValue : bValue - aValue;
          } else if (typeof aValue === 'string' && typeof bValue === 'string') {
            return sort.asc
              ? aValue.localeCompare(bValue)
              : bValue.localeCompare(aValue);
          }
          return 0;
        });
    
        return products;
      })
    );
    
  }

  handleSortEvent(event: SortCriteria) {
    this.sortSubject.next(event);
  }

  handleFilterEvent(event: FilterCriteria | null) {
    this.filterSubject.next(event);
  }
}
