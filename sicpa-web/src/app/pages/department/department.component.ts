import {Component, OnInit} from '@angular/core';
import {DepartmentDto} from "../../models/department-dto";
import {UserDto} from "../../models/user-dto";
import {ConfirmationService, MessageService} from "primeng/api";
import {AuthService} from "../../services/auth.service";
import {BaseResponseDto} from "../../models/common/base-response-dto";
import {DepartmentService} from "../../services/department.service";
import {EnterpriseDto} from "../../models/enterprise-dto";
import {EnterpriseService} from "../../services/enterprise.service";

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css'],
  providers: [MessageService, ConfirmationService],
})
export class DepartmentComponent implements OnInit {
  departments: Array<DepartmentDto>;
  enterprises: Array<EnterpriseDto>;
  department: DepartmentDto;
  departmentDialog: boolean;
  submitted: boolean;
  user: UserDto;

  constructor(
    private departmentService: DepartmentService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    public authService: AuthService,
    private enterpriseService: EnterpriseService,
  ) {
    this.departments = [];
    this.enterprises = [];
    this.department = new DepartmentDto();
    this.user = new UserDto();
  }

  ngOnInit(): void {
    this.user = this.authService.user;
    this.findAll();
    this.findEnterpriseAll();
  }

  findAll(): void {
    this.departmentService.findDepartmentAll().subscribe((response: BaseResponseDto) => {
      this.departments = response.data;
    });
  }

  findEnterpriseAll(): void {
    this.enterpriseService.findEnterpriseAll().subscribe((response: BaseResponseDto) => {
      this.enterprises = response.data;
    });
  }

  editDepartment(department: DepartmentDto): void {
    this.department = {...department};
    this.departmentDialog = true;
  }

  deleteDepartment(department: DepartmentDto): void {
    this.confirmationService.confirm({
      message: '¿Estás seguro quieres eliminar ' + department.name + '?',
      header: 'Confirmar',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.departmentService.delete(department.id).subscribe(department => {

        });
        this.departments = this.departments.filter(val => val.id !== department.id);
        this.department = new DepartmentDto();
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro eliminado', life: 3000});
      }
    });
  }

  openNew(): void {
    this.department = new DepartmentDto();
    this.submitted = false;
    this.departmentDialog = true;
  }

  hideDialog() {
    this.departmentDialog = false;
    this.submitted = false;
  }

  load(): void {
    this.findAll();
    this.departmentDialog = false;
    this.department = new DepartmentDto();
  }

  saveDepartment() {
    this.submitted = true;

    if (undefined !== this.department.name && this.department.name.trim()) {
      if (this.department.id) {

        this.department.modifiedBy = this.user.username
        this.department.modifiedDate = new Date();
        this.department.modifiedHost = '127.0.0.1';
        this.departmentService.update(this.department.id, this.department).subscribe(department => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Registro actualizado',
            life: 3000
          });
          this.load();
        })
      } else {
        this.department.createdBy = this.user.username
        this.department.createdDate = new Date();
        this.department.createdHost = '127.0.0.1';
        this.department.status = true;
        this.departmentService.save(this.department).subscribe(department => {
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro creado', life: 3000});
          this.load();
        })
      }
    }
  }

}
