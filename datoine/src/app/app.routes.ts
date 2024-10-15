import { Routes } from '@angular/router';
import { ProductDisplayComponent } from './product-display/product-display.component';
import { AuthPageComponent } from './auth-page/auth-page.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { CartComponent } from './cart/cart.component';

export const routes: Routes = [
    { path: 'product', component: ProductDisplayComponent },
    { path: 'auth/signin', component: AuthPageComponent },
    { path: 'auth/signup', component: AuthPageComponent },
    { path: 'user', component: UserInfoComponent },
    { path: 'cart', component: CartComponent },
];