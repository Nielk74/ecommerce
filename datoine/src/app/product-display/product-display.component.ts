import { Component, inject, OnInit } from '@angular/core';
import { ProductService } from './services/product.service';
import { AsyncPipe } from '@angular/common';
import { ProductItemComponent } from './components/product-item/product-item.component';
import { Product } from './models/Product.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-display',
  standalone: true,
  imports: [AsyncPipe, ProductItemComponent],
  templateUrl: './product-display.component.html',
  styleUrl: './product-display.component.scss',
})
export class ProductDisplayComponent implements OnInit {
  productService = inject(ProductService);
  $products?: Observable<Product[]>;
  ngOnInit(): void {
    this.$products = this.productService.getAllProducts();
    this.$products.subscribe((a)=>console.log(a))
  }
}
