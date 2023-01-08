import { GenericDto } from "./generic-dto";
import { EmployeeDto } from "./employee-dto";
import { RoleUserDto } from "./role-user-dto";

export class UserDto extends GenericDto {
  id: number;
  password: string;
  username: string;
  roleUsers: Array<RoleUserDto> = [];
  employee: EmployeeDto = new EmployeeDto();
  roles: string[] = [];

  constructor() {
    super();
  }
}
