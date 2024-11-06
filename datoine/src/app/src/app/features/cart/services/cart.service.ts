import { Injectable, signal } from '@angular/core';
import { CartItem } from '../../../../../features/cart/models/cartItem.model';
import { Cart } from '../../../../../features/cart/models/cart.model';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartSignal = signal<Cart>({ cartItems: [], total: 0 });
  public cart = this.cartSignal.asReadonly();

  constructor() {}

  addItem(newItem: CartItem) {
    this.cartSignal.update((cart) => {
      const existingCartItem = cart.cartItems.find(
        (cartItem) => cartItem.product.id === newItem.product.id
      );

      if (existingCartItem) {
        existingCartItem.quantity += newItem.quantity;
      } else {
        cart.cartItems.push(newItem);
      }

      cart.total += newItem.product.price * newItem.quantity;
      return cart;
    });
  }

  decreaseQuantity(updatedItem: CartItem) {
    this.cartSignal.update((cart) => {
      const existingCartItem = cart.cartItems.find(
        (cartItem) => cartItem.product.id === updatedItem.product.id
      );
      if (existingCartItem) {
        cart.total -= existingCartItem.product.price;

        cart.cartItems = cart.cartItems.map((cartItem) =>
          cartItem.product.id === updatedItem.product.id
            ? { ...cartItem, quantity: cartItem.quantity - 1 }
            : cartItem
        );
      }

      return cart;
    });
  }

  increaseQuantity(updatedItem: CartItem) {
    this.cartSignal.update((cart) => {
      const existingCartItem = cart.cartItems.find(
        (cartItem) => cartItem.product.id === updatedItem.product.id
      );

      if (existingCartItem) {
        existingCartItem.quantity += 1;
      }

      cart.total += updatedItem.product.price;
      return cart;
    });
  }

  removeProduct(productId: number) {
    this.cartSignal.update((cart) => {
      const existingCartItem = cart.cartItems.find(
        (cartItem) => cartItem.product.id === productId
      );

      if (existingCartItem) {
        cart.total -=
          existingCartItem.product.price * existingCartItem.quantity;
        cart.cartItems = cart.cartItems.filter(
          (cartItem) => cartItem.product.id !== productId
        );
      }
      return cart;
    });
  }
}
