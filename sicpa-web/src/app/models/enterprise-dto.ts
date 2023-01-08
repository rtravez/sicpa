import { DepartmentDto } from "./department-dto";
import { GenericDto } from "./generic-dto";

export class EnterpriseDto extends GenericDto {

  id: number;
  address: string;
  name: string
  phone: string;
  //departments: Array<DepartmentDto>;


  constructor() {
    super();
  }
}
