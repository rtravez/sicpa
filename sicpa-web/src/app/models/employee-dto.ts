import {GenericDto} from "./generic-dto";

export class EmployeeDto extends GenericDto {
  id: number;
  name: string;
  surname: string;
  lastname: string
  age: number;
  email: string;
  position: string;

  constructor() {
    super();
  }
}
