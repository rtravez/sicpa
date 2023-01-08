import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from 'src/app/services/auth.service';
import {MessageService} from "primeng/api";
import {UserDto} from "../../models/user-dto";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {
  titulo: string;
  user: UserDto;

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {
    this.user = new UserDto();
  }

  ngOnInit(): void {
    this.titulo = 'Iniciar';
    if (this.authService.isAuthenticated()) {
      /* Swal.fire(
         'Login',
         `Hola ${this.authService.getUsuario.username} ya estás autenticado`,
         'info'
       );*/
      this.messageService.add({
        severity: 'info',
        summary: 'Acceso',
        detail: `Hola ${this.authService.user.username} ya estás autenticado`
      });
      this.router.navigate(['/']);
    }
  }

  login(): void {
    if (this.user.username == null || this.user.password == null) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error de inicio de sesión',
        detail: 'Usuario o contraseña incorrectos'
      });
    } else {
      this.authService.login(this.user).subscribe(
        {
          next: (response) => {
            this.authService.saveUser(response.access_token);
            this.authService.saveToken(response.access_token);

            this.messageService.add({
              severity: 'success',
              summary: 'Acceso',
              detail: `Bienvenido ${this.authService.user.username}`
            });
            this.router.navigate(['/']).then(() => {
            });
          },
          error: (err: any) => {
            if (err.status === 400) {
              this.messageService.add({
                severity: 'error',
                summary: 'Error de inicio de sesión',
                detail: 'Usuario o contraseña incorrectos'
              });
            }
          }
        }
      );
    }
  }
}
