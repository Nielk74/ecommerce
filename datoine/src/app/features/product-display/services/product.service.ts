import { computed, inject, Injectable, Signal, signal } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../models/Product.model';
import { HttpClient } from '@angular/common/http';
import { toObservable } from '@angular/core/rxjs-interop';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  http = inject(HttpClient);

  private productsSignal = signal<Product[]>([]);
  public products = this.productsSignal.asReadonly();
  public products$: Observable<Product[]> = toObservable(this.productsSignal);


  
  fetchProducts(): void {
    this.http.get<Product[]>('/product/').pipe(
      map((products) =>
        products.map((product) => ({
          ...product,
          imageURL:
            'https://chevrolet.com.ph/wp-content/uploads/2024/04/What-Makes-a-Car-a-%E2%80%98Sports-Car.png', // Set the imageURL of each product to null
        }))
      )
    ).subscribe((products: Product[])=> {
      this.productsSignal.set(products);
    })

  }



  fetchCategory(): Observable<Category[]> {
    return this.http.get<Category[]>('/category/');
  }
}
