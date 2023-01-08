import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from './auth/auth.module';
import {Error400Component} from './errors/error-400/error-400.component';
import {Error404Component} from './errors/error-404/error-404.component';
import {AuthInterceptor} from './interceptors/auth.interceptor';
import {TokenInterceptor} from './interceptors/token.interceptor';
import {PagesModule} from './pages/pages.module';
import {ToastModule} from "primeng/toast";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MessageService} from "primeng/api";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

@NgModule({
  declarations: [AppComponent, Error404Component, Error400Component],
  imports: [BrowserModule, AppRoutingModule, PagesModule, AuthModule, ToastModule, BrowserAnimationsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      useDefaultLang: true,
      defaultLanguage: 'es',
      loader: {
        provide: TranslateLoader,
        useFactory: httpTranslateLoader,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    [MessageService],
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}

export function httpTranslateLoader(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient, './assets/i18n/', '.json');
}
