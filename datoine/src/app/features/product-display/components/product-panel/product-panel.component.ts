import {
  ChangeDetectionStrategy,
  Component,
  inject,
  OnInit,
  output,
} from '@angular/core';
import { NgbAccordionModule } from '@ng-bootstrap/ng-bootstrap';
import { FilterCriteria, SortCriteria } from '../../product-display.component';
import { ProductService } from '../../services/product.service';
import { Category } from '../../models/category.model';
import { map } from 'rxjs';

@Component({
  selector: 'app-product-panel',
  standalone: true,
  imports: [NgbAccordionModule],
  templateUrl: './product-panel.component.html',
  styleUrl: './product-panel.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductPanelComponent implements OnInit {
  productService = inject(ProductService);
  sortEvent = output<SortCriteria>();
  filterEvent = output<FilterCriteria | null>();
  categories: Category[] = [];
  selectedCategory?: Category;
  private ascending = true;
  private lastSort = '';

  ngOnInit(): void {
    this.productService.fetchCategory().subscribe((categories) => {
      this.categories =categories;
    });
  }

  sendSortEvent(attribute: string) {
    if (this.lastSort === attribute) {
      this.ascending = !this.ascending;
      this.sortEvent.emit({ attribute, asc: this.ascending });
    } else {
      this.ascending = true;
      this.sortEvent.emit({ attribute, asc: this.ascending });
      this.lastSort = attribute;
    }
  }

  sendFilterEvent(category: Category) {
    if (category.id === this.selectedCategory?.id) {
      this.filterEvent.emit(null);
      this.selectedCategory = undefined
    } else {
      this.filterEvent.emit({ attribute: 'categoryId', value: String(category.id) });
      this.selectedCategory = category
    }
  }
}
