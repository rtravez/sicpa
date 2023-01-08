import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AuthService} from '../services/auth.service';
import {MessageService} from "primeng/api";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((e) => {
        if (e.status === 401) {
          if (this.authService.isAuthenticated()) {
            this.authService.logout();
          }
          this.router.navigate(['/login']);
        }

        if (e.status === 403) {
          /*Swal.fire(
            'Acceso denegado',
            `Hola ${this.authService.getUsuario.username} no tienes acceso a este recurso!`,
            'warning'
          );*/

          this.messageService.add({
            severity: 'error',
            summary: 'Acceso denegado',
            detail: `Hola ${this.authService.user.username} no tienes acceso a este recurso`,
          });
          this.router.navigate(['/']);
        }
        //return throwError(e);
        return throwError(() => new Error(`intercept ${e}`));
      })
    );
  }
}
