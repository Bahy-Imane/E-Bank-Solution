import {
  HttpInterceptorFn,
  HttpRequest,
  HttpHandlerFn,
  HttpErrorResponse,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { UserServiceService } from '../service/user-service.service';
import {AuthServiceService} from "../service/auth-service.service";

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<any>,
  next: HttpHandlerFn
) => {
  const authService = inject(AuthServiceService);
  const authToken = authService.getToken();

  let clonedReq = req;
  if (authToken) {
    clonedReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + authToken),
    });
  }

  return next(clonedReq).pipe(
    catchError((error: HttpErrorResponse) => {
      console.error('An error occurred:', error);
      // Pass through the actual error response
      return throwError(() => error);
    })
  );
};
