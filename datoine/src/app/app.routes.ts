import { Routes } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './core/auth/services/auth.service';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./product-display/product-display.component').then(
        (m) => m.ProductDisplayComponent
      ),
  },
  {
    path: 'auth',
    children: [
      {
        path: 'signin',
        loadComponent: () =>
          import('./auth-page/auth-page.component').then(
            (m) => m.AuthPageComponent
          ),
      },
      {
        path: 'signup',
        loadComponent: () =>
          import('./auth-page/auth-page.component').then(
            (m) => m.AuthPageComponent
          ),
      },
    ],
    canActivate: [() => !inject(AuthService).isAuthenticated()],
  },
  {
    path: 'user',
    loadComponent: () =>
      import('./user-info/user-info.component').then(
        (m) => m.UserInfoComponent
      ),
  },
  {
    path: 'cart',
    loadComponent: () =>
      import('./cart/cart.component').then((m) => m.CartComponent),
  },
];
