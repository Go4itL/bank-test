import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthPayload } from 'src/app/models/auth-payload.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  submitted = false;

  constructor(private authService: AuthService,
    private readonly router: Router) {

  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'login': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.valid) {
      const value = this.loginForm.value;
      const payload: AuthPayload = new AuthPayload();
      payload.username = value.login;
      payload.password = value.password;

      this.authService.signIn(payload).subscribe(_result => {
        this.router.navigate(['/transaction']);
      });
    }
  }

}
