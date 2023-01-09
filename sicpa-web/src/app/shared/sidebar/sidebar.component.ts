import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menu: any[];

  constructor() {
  }


  ngOnInit(): void {
    this.menu = [
      {
        label: 'Administración',
        icon: 'pi pi-plus',
        submenu: [
          {label: 'Empresa', icon: 'pi pi-plus', routerLink: ['/dashboard/enterprise'], queryParams: {'recent': 'true'}},
          {label: 'Departamento', icon: 'pi pi-plus', routerLink: ['/dashboard/department'], queryParams: {'recent': 'true'}},
          {label: 'Empleado', icon: 'pi pi-plus', routerLink: ['/dashboard/employee'], queryParams: {'recent': 'true'}}
        ]
      },
    ];
  }
}
