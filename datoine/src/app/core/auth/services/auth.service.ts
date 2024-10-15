import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { User } from '../user.model';
import { JwtService } from './jwt.service';
import { Router } from '@angular/router';
import { Credentials } from '../models/crendentials.model';
import { LoginResponse } from '../models/login-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  http = inject(HttpClient);
  jwtService = inject(JwtService);
  router = inject(Router);

  private userSignal = signal<User | null>(null);
  public isAuthenticated = computed(() => !!this.userSignal());

  login(credentials: Credentials): Observable<User> {
    return this.http
      .post<User>('/auth/signin', credentials)
      .pipe(
        tap((loginResponse) =>
          this.setAuth({ token: loginResponse.token, login: credentials.login })
        )
      );
  }

  register(credentials: Credentials) {
    return this.http.post('/auth/signup', credentials);
  }

  purgeAuth(): void {
    this.jwtService.destroyToken();
    this.userSignal.set(null);
  }

  setAuth(user: User): void {
    this.jwtService.saveToken(user.token);
    this.userSignal.set(user);
  }

  logout(): void {
    this.purgeAuth;
    this.router.navigate(['/']);
  }
}
