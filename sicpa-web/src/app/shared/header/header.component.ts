import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/services/auth.service';
import {Router} from '@angular/router';
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(public authService: AuthService, private router: Router, private messageService: MessageService) {
  }

  ngOnInit(): void {
  }

  public logout(): void {
    const usuario = this.authService.user;
    this.authService.logout();
    /*Swal.fire(
      'Logout',
      `Adiós ${usuario.username} has cerrado sesión con éxito`,
      'success'
    );*/
    this.messageService.add({
      severity: 'success',
      summary: 'Cerrar sesión',
      detail: `Adiós ${usuario.username} has cerrado sesión con éxito`,
    });
    this.router.navigate(['/login']);
  }
}
