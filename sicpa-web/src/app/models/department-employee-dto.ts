import {DepartmentDto} from "./department-dto";
import {EmployeeDto} from "./employee-dto";
import {GenericDto} from "./generic-dto";

export class DepartmentEmployeeDto extends GenericDto {
  id: number;
  department: DepartmentDto = new DepartmentDto();
  employee: EmployeeDto = new EmployeeDto();

  constructor() {
    super();
  }
}
