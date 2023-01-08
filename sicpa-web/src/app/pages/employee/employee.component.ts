import {Component, OnInit} from '@angular/core';
import {EmployeeDto} from "../../models/employee-dto";
import {UserDto} from "../../models/user-dto";
import {EmployeeService} from "../../services/employee.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {AuthService} from "../../services/auth.service";
import {BaseResponseDto} from "../../models/common/base-response-dto";
import {DepartmentService} from "../../services/department.service";
import {DepartmentDto} from "../../models/department-dto";
import {DepartmentEmployeeDto} from "../../models/department-employee-dto";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
  providers: [MessageService, ConfirmationService],
})
export class EmployeeComponent implements OnInit {
  departmentEmployees: Array<DepartmentEmployeeDto>;
  departments: Array<DepartmentDto>;
  departmentEmployee: DepartmentEmployeeDto;
  employeeDialog: boolean;
  submitted: boolean;
  user: UserDto;
  department: DepartmentDto;
  positions: Array<any>;

  constructor(
    private employeeService: EmployeeService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    public authService: AuthService,
    private departmentService: DepartmentService,
  ) {
    this.departmentEmployees = [];
    this.departments = [];
    this.departmentEmployee = new DepartmentEmployeeDto();
    this.user = new UserDto();
    this.department = new DepartmentDto();

    this.positions = [
      {name: 'Desarrollador', position: 'NY'},
      {name: 'Recepcionista', position: 'RM'},
      {name: 'Asistente', position: 'LDN'},
    ];
  }

  ngOnInit(): void {
    this.user = this.authService.user;
    this.findAll();
    this.findDepartmentAll();
  }


  findAll(): void {
    this.employeeService.findEmployeeAll().subscribe((response: BaseResponseDto) => {
      this.departmentEmployees = response.data;
    });
  }

  findDepartmentAll(): void {
    this.departmentService.findDepartmentAll().subscribe((response: BaseResponseDto) => {
      this.departments = response.data;
    });
  }

  editEmployee(departmentEmployee: DepartmentEmployeeDto): void {
    this.departmentEmployee = {...departmentEmployee};
    this.department = {...departmentEmployee.department};
    this.employeeDialog = true;
  }

  deleteEmployee(employee: EmployeeDto): void {
    this.confirmationService.confirm({
      message: '¿Estás seguro quieres eliminar ' + employee.name + '?',
      header: 'Confirmar',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.employeeService.delete(employee.id).subscribe(employee => {

        });
        this.departmentEmployees = this.departmentEmployees.filter(val => val.id !== employee.id);
        this.departmentEmployee = new DepartmentEmployeeDto()
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro eliminado', life: 3000});
      }
    });
  }

  openNew(): void {
    this.departmentEmployee = new DepartmentEmployeeDto();
    this.department = new DepartmentDto();
    this.submitted = false;
    this.employeeDialog = true;
  }

  hideDialog() {
    this.employeeDialog = false;
    this.submitted = false;
  }

  load(): void {
    this.findAll();
    this.employeeDialog = false;
    this.departmentEmployee = new DepartmentEmployeeDto();
    this.department = new DepartmentDto();
  }

  saveEmployee() {
    this.submitted = true;

    if (undefined !== this.departmentEmployee.employee.name && this.departmentEmployee.employee.name.trim()) {
      if (this.departmentEmployee.employee.id) {

        this.departmentEmployee.employee.modifiedBy = this.user.username
        this.departmentEmployee.employee.modifiedDate = new Date();
        this.departmentEmployee.employee.modifiedHost = '127.0.0.1';

        this.departmentEmployee.modifiedBy = this.user.username
        this.departmentEmployee.modifiedDate = new Date();
        this.departmentEmployee.modifiedHost = '127.0.0.1';


        this.departmentEmployee.department = this.department;
        this.employeeService.update(this.departmentEmployee.id, this.departmentEmployee).subscribe(employee => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Registro actualizado',
            life: 3000
          });
          this.load();
        })
      } else {
        this.departmentEmployee.employee.createdBy = this.user.username
        this.departmentEmployee.employee.createdDate = new Date();
        this.departmentEmployee.employee.createdHost = '127.0.0.1';
        this.departmentEmployee.employee.status = true;

        this.departmentEmployee.createdBy = this.user.username
        this.departmentEmployee.createdDate = new Date();
        this.departmentEmployee.createdHost = '127.0.0.1';
        this.departmentEmployee.status = true;

        this.departmentEmployee.department = this.department;
        this.employeeService.save(this.departmentEmployee).subscribe(employee => {
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro creado', life: 3000});
          this.load();
        })
      }
    }
  }

}
