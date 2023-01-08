import { GenericDto } from "./generic-dto";
import { RoleDto } from "./role-dto";
import { UserDto } from "./user-dto";

export class RoleUserDto extends GenericDto {

  id: number;
  role: RoleDto;
  user: UserDto;

  constructor() {
    super();
  }
}
