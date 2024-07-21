import { Component } from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { RegisterDto } from "../../core/model/user.model";
import {AuthServiceService} from "../../core/service/auth-service.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterOutlet
  ],
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(
    private authService: AuthServiceService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.registerForm = this.fb.group({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      const registerDto: RegisterDto = {
        userName: this.registerForm.get('userName')?.value,
        email: this.registerForm.get('email')?.value,
        address: this.registerForm.get('address')?.value,
        password: this.registerForm.get('password')?.value
      };

      this.authService.register(registerDto).subscribe({
        next: (response) => {
          console.log('Registration successful', response);
          localStorage.setItem('token', response.token);
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Registration failed', error);
        }
      });
    }
  }
}
