import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthPayload } from '../models/auth-payload.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private readonly httpClient: HttpClient) { }

  signIn(payload: AuthPayload): Observable<any> {
    return this.httpClient.post('/api/auth/signIn', payload)
      .pipe(tap((_result: any) => {
        localStorage.setItem('token', _result['token']);
      }));
  }

  logout(): void {
    localStorage.clear();
  }
}
