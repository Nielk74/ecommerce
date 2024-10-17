import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../models/Product.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  http = inject(HttpClient);
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('/product/').pipe(
      map((products) =>
        products.map((product) => ({
          ...product,
          imageURL:
            'https://chevrolet.com.ph/wp-content/uploads/2024/04/What-Makes-a-Car-a-%E2%80%98Sports-Car.png', // Set the imageURL of each product to null
        }))
      )
    );
  }
}
