import {HTTP_INTERCEPTORS, HttpEvent} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';

import {TokenStorageService} from '../_services/token-storage.service';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {AuthService} from '../_services/auth.service';
import {Router} from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end
// const TOKEN_HEADER_KEY = 'x-access-token';   // for Node.js Express back-end

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService, private tokenStorageService: TokenStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.tokenStorageService.getToken();
    if (token != null) {
      // for Spring Boot back-end
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)});

      // for Node.js Express back-end
      // authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, token) });
    }
    return next.handle(authReq).pipe(tap({
      error: (error) => {
        if (error.status === 401) {
          this.authService.isLoggedIn.emit(false);
          this.tokenStorageService.signOut();
          this.router.navigateByUrl('/login');
        }
      }
    }));
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
