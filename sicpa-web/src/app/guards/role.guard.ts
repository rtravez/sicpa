import {Injectable} from '@angular/core';

import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree,} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../services/auth.service';
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    const role = next.data['role'] as string[];
    console.log(role);
    for (const r of role) {
      if (this.authService.hasRole(r)) {
        return true;
      }
    }
    /*Swal.fire(
      'Acceso denegado',
      `Hola ${this.authService.getUsuario.username} no tienes acceso a este recurso`,
      'warning'
    );*/

    this.messageService.add({
      severity: 'error',
      summary: 'Acceso denegado',
      detail: `Hola ${this.authService.user.username} no tienes acceso a este recurso`,
    });
    this.router.navigate(['/']);
    return false;
  }
}
