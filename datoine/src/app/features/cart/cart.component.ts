import { Component, inject, OnInit } from '@angular/core';
import { ProductService } from '../product-display/services/product.service';
import { CartService } from '../../src/app/features/cart/services/cart.service';
import { CartItemComponent } from '../../src/app/features/cart/components/cart-item/cart-item.component';
import { CartItem } from './models/cartItem.model';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartItemComponent, CurrencyPipe],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent implements OnInit {
  productService = inject(ProductService);
  cartService = inject(CartService);
  cart = this.cartService.cart();
  ngOnInit(): void {
  }

}
