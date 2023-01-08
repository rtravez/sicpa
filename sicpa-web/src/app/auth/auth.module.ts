import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RecoverComponent} from './recover/recover.component';
import {RegisterComponent} from './register/register.component';
import {ToastModule} from "primeng/toast";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserModule} from "@angular/platform-browser";
import {TranslateModule} from "@ngx-translate/core";


@NgModule({
  declarations: [LoginComponent, RegisterComponent, RecoverComponent],
  exports: [LoginComponent, RegisterComponent, RecoverComponent],
  imports: [CommonModule, RouterModule, FormsModule, ReactiveFormsModule, HttpClientModule, BrowserModule, BrowserAnimationsModule, ToastModule, TranslateModule],
})
export class AuthModule {
}
