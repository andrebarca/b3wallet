/*
 * acao service
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Acao, Option } from '../models';

@Injectable()
export class AcaoService {

    constructor(private http: HttpClient) {}

    save(acao: Acao): Observable<HttpResponse<Acao>>  {
        return this.http.post<Acao>('/api/acoes/save', acao, {observe: 'response'});
    }

    list(): Observable<HttpResponse<Acao[]>> {
        return this.http.get<Acao[]>('/api/acoes', {observe: 'response'});
    }

    listOptionsTipoPapel(): Observable<HttpResponse<Option[]>> {
        return this.http.get<Option[]>('/api/acoes/tipos', { observe: 'response' });
    }

}
