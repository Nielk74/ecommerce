import { Injectable } from '@angular/core';
import { User } from '../user.model';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  getUser(): string {
    return window.localStorage["user"];
  }

  saveUser(user: User): void {
    window.localStorage["user"] = JSON.stringify(user);
  }

  destroyUser(): void {
    window.localStorage.removeItem("user");
  }
}
