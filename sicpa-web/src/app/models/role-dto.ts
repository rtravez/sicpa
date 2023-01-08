import { GenericDto } from "./generic-dto";
import { RoleUserDto } from "./role-user-dto";

export class RoleDto extends GenericDto {

  id: number;
  name: string;
  roleUsers: Array<RoleUserDto>;

  constructor() {
    super();
  }
}
