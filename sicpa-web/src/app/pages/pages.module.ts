import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {PagesComponent} from './pages.component';
import {ProfileComponent} from './profile/profile.component';
import {ToastModule} from "primeng/toast";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {EmployeeComponent} from './employee/employee.component';
import {EnterpriseComponent} from './enterprise/enterprise.component';
import {DepartmentComponent} from './department/department.component';
import {TableModule} from 'primeng/table';
import {ToolbarModule} from 'primeng/toolbar';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {InputTextModule} from 'primeng/inputtext';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {DropdownModule} from 'primeng/dropdown';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputMaskModule} from 'primeng/inputmask';


@NgModule({
  declarations: [
    DashboardComponent,
    PagesComponent,
    ProfileComponent,
    EmployeeComponent,
    EnterpriseComponent,
    DepartmentComponent

  ],
  exports: [DashboardComponent, PagesComponent],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule, BrowserAnimationsModule, ToastModule, TableModule, ToolbarModule, ButtonModule
    , DialogModule, ConfirmDialogModule, InputTextModule, InputTextareaModule, DropdownModule, InputNumberModule,InputMaskModule
  ],
})
export class PagesModule {
}
