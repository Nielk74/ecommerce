import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from "./core/layout/footer/footer.component";
import { HeaderComponent } from './core/layout/header/header.component';
import { AuthService } from './core/auth/services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HeaderComponent,
    FooterComponent
],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit{
  authService = inject(AuthService);
  ngOnInit(): void {
    this.authService.setUserFromLocalStorage();
  }
  
  
  title = 'datoine';

}
