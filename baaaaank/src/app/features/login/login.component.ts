import { Component } from '@angular/core';

import {Router, RouterOutlet} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {UserServiceService} from "../../core/service/user-service.service";
import {LoginDto, RegisterDto} from "../../core/model/user.model";
import {response} from "express";
import {AuthServiceService} from "../../core/service/auth-service.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterOutlet
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private authService: AuthServiceService, private router: Router, private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin() {
    if (this.loginForm.valid) {
      const loginDto: LoginDto = {
        userName: this.loginForm.get('userName')?.value,
        password: this.loginForm.get('password')?.value
      };
      this.authService.login(loginDto).subscribe( {
        next:(response) => {
          console.log(response)
          localStorage.setItem('token', response.token);
          this.router.navigate(['/dashboard']);
        },
        error:(err) => {
          console.error('Login failed', err);
        }
      });
    }
  }
}
