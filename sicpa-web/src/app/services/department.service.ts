import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {DepartmentDto} from '../models/department-dto';
import {GenericService} from './generic.service';
import {catchError, Observable, throwError} from "rxjs";
import {BaseResponseDto} from "../models/common/base-response-dto";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService extends GenericService<DepartmentDto, number> {

  constructor(protected override http: HttpClient, protected override router: Router) {
    super(http, router, `${environment.api.baseUrl}/departments`);
  }

  findDepartmentAll(): Observable<BaseResponseDto> {
    return this.http.get<BaseResponseDto>(`${this.base}`)
      .pipe(catchError((e) => throwError(() => new Error(`findDepartmentAll ${e}`))));
  }
}
