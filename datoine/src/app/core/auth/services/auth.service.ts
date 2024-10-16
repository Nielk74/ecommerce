import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { Credentials } from '../models/crendentials.model';
import { User } from '../user.model';
import { JwtService } from './jwt.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  setUserFromLocalStorage(): void {
    const user = this.jwtService.getUser();
    if (user){
      this.setAuth(JSON.parse(user));
    }
  }

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
    this.jwtService.destroyUser();
    this.userSignal.set(null);
  }

  setAuth(user: User): void {
    this.jwtService.saveUser(user);
    this.userSignal.set(user);
  }

  logout(): void {
    this.purgeAuth;
    this.router.navigate(['/']);
  }
}
