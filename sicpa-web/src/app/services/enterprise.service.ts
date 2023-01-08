import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {EnterpriseDto} from '../models/enterprise-dto';
import {GenericService} from './generic.service';
import {Observable, catchError, throwError} from 'rxjs';
import {BaseResponseDto} from "../models/common/base-response-dto";

@Injectable({
  providedIn: 'root',
})
export class EnterpriseService extends GenericService<EnterpriseDto, number> {
  constructor(
    protected override http: HttpClient,
    protected override router: Router
  ) {
    super(http, router, `${environment.api.baseUrl}/enterprises`);
  }

  findEnterpriseAll(): Observable<BaseResponseDto> {
    return this.http.get<BaseResponseDto>(`${this.base}`)
      .pipe(catchError((e) => throwError(() => new Error(`findEnterpriseAll ${e}`))));
  }
}
