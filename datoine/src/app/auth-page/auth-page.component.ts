import { Component, DestroyRef, inject, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../core/auth/services/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Credentials } from '../core/auth/models/crendentials.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-auth-page',
  standalone: true,
  templateUrl: './auth-page.component.html',
  styleUrl: './auth-page.component.scss',
  imports: [FormsModule],
})
export class AuthPageComponent implements OnInit {
  authService = inject(AuthService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  destroyRef = inject(DestroyRef);

  isSubmitting = false;
  title = '';
  authType = '';

  ngOnInit(): void {
    this.authType = this.route.snapshot.url.at(-1)!.path;
    this.title = this.authType === 'signin' ? 'Sign in' : 'Sign up';
  }

  onSubmit(form: NgForm) {
    this.isSubmitting = true;
    let observable =
      this.authType === 'signin'
        ? this.authService.login(form.value as Credentials)
        : this.authService.register(form.value as Credentials);

    observable.subscribe({
      next: ()=>void this.router.navigate(["/"]),
      error: (err)=>{
        console.error(err);
        this.isSubmitting = false;
      }
    })
  }
}
