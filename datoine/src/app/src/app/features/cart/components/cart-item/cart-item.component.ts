import { ChangeDetectionStrategy, Component, effect, input, output } from '@angular/core';
import { CartItem } from '../../../../../../features/cart/models/cartItem.model';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CurrencyPipe],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CartItemComponent {
cartItem = input.required<CartItem>();
decreaseEvent = output();
increaseEvent = output();
removeEvent = output();

}
