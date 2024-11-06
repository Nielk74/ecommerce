import {
  ChangeDetectionStrategy,
  Component,
  inject,
  input,
  signal,
} from '@angular/core';
import { Product } from '../../models/Product.model';
import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { CartService } from '../../../../src/app/features/cart/services/cart.service';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [CurrencyPipe],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductItemComponent {
  cartService = inject(CartService);
  product = input.required<Product>();

  addToCart() {
    this.cartService.addItem({
      product: this.product(),
      quantity: 1,
    });
  }
}
