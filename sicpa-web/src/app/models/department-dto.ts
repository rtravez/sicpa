import { DepartmentEmployeeDto } from "./department-employee-dto";
import { EnterpriseDto } from "./enterprise-dto";
import { GenericDto } from "./generic-dto";

export class DepartmentDto extends GenericDto {

  id: number;
  description: string;
  name: string;
  phone: string;
  enterprise: EnterpriseDto= new EnterpriseDto();
  //departmentEmployees: Array<DepartmentEmployeeDto>;

  constructor() {
    super();
  }
}
