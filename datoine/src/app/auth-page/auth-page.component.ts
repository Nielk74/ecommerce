import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../core/auth/services/auth.service';

@Component({
  selector: 'app-auth-page',
  standalone: true,
  imports: [],
  templateUrl: './auth-page.component.html',
  styleUrl: './auth-page.component.scss'
})
export class AuthPageComponent implements OnInit{
authService = inject(AuthService);
ngOnInit(): void {
    this.authService.login({login: "antoine", password:"antoine"}).subscribe((a)=>console.log(a))
}

}
