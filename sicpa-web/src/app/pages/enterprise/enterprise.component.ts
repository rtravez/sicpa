import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {EnterpriseDto} from 'src/app/models/enterprise-dto';
import {EnterpriseService} from 'src/app/services/enterprise.service';
import {BaseResponseDto} from "../../models/common/base-response-dto";
import {AuthService} from "../../services/auth.service";
import {UserDto} from "../../models/user-dto";

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.css'],
  providers: [MessageService, ConfirmationService],
})
export class EnterpriseComponent implements OnInit {
  enterprises: Array<EnterpriseDto>;
  enterprise: EnterpriseDto;
  enterpriseDialog: boolean;
  submitted: boolean;
  user: UserDto;

  constructor(
    private enterpriseService: EnterpriseService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    public authService: AuthService
  ) {
    this.enterprises = [];
    this.enterprise = new EnterpriseDto();
    this.user = new UserDto();
  }

  ngOnInit(): void {
    this.user = this.authService.user;
    this.findAll();
  }

  findAll(): void {
    this.enterpriseService.findEnterpriseAll().subscribe((response: BaseResponseDto) => {
      this.enterprises = response.data;
    });
  }

  editEnterprise(enterprise: EnterpriseDto): void {
    this.enterprise = {...enterprise};
    this.enterpriseDialog = true;
  }

  deleteEnterprise(enterprise: EnterpriseDto): void {
    this.confirmationService.confirm({
      message: '¿Estás seguro quieres eliminar ' + enterprise.name + '?',
      header: 'Confirmar',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.enterpriseService.delete(enterprise.id).subscribe(enterprise => {

        });
        this.enterprises = this.enterprises.filter(val => val.id !== enterprise.id);
        this.enterprise = new EnterpriseDto();
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro eliminado', life: 3000});
      }
    });
  }

  openNew(): void {
    this.enterprise = new EnterpriseDto();
    this.submitted = false;
    this.enterpriseDialog = true;
  }

  hideDialog() {
    this.enterpriseDialog = false;
    this.submitted = false;
  }

  load(): void {
    this.findAll();
    this.enterpriseDialog = false;
    this.enterprise = new EnterpriseDto();
  }

  saveEnterprise() {
    this.submitted = true;

    if (undefined !== this.enterprise.name && this.enterprise.name.trim()) {
      if (this.enterprise.id) {

        this.enterprise.modifiedBy = this.user.username
        this.enterprise.modifiedDate = new Date();
        this.enterprise.modifiedHost = '127.0.0.1';
        this.enterpriseService.update(this.enterprise.id, this.enterprise).subscribe(enterprise => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Registro actualizado',
            life: 3000
          });
          this.load();
        })
      } else {
        this.enterprise.createdBy = this.user.username
        this.enterprise.createdDate = new Date();
        this.enterprise.createdHost = '127.0.0.1';
        this.enterprise.status = true;
        this.enterpriseService.save(this.enterprise).subscribe(enterprise => {
          this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Registro creado', life: 3000});
          this.load();
        })
      }
    }
  }
}
