import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthRoutingModule} from './auth/auth-routing.module';
import {Error404Component} from './errors/error-404/error-404.component';
import {PagesRoutingModule} from './pages/pages-routing.module';

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: '**', component: Error404Component},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PagesRoutingModule,
    AuthRoutingModule,
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
