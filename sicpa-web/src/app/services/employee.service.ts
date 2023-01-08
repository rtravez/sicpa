import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {EmployeeDto} from '../models/employee-dto';
import {GenericService} from './generic.service';
import {catchError, Observable, throwError} from "rxjs";
import {BaseResponseDto} from "../models/common/base-response-dto";
import {DepartmentEmployeeDto} from "../models/department-employee-dto";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends GenericService<DepartmentEmployeeDto, number> {

  constructor(protected override http: HttpClient, protected override router: Router) {
    super(http, router, `${environment.api.baseUrl}/employees`);
  }


  findEmployeeAll(): Observable<BaseResponseDto> {
    return this.http.get<BaseResponseDto>(`${this.base}`)
      .pipe(catchError((e) => throwError(() => new Error(`findEmployeeAll ${e}`))));
  }
}
