/*
 * acao service
 */
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Acao } from '../models/acao';

@Injectable()
export class AcaoService {

    constructor(private http: HttpClient) {}

    saveAcao(acao: Acao): Observable<HttpResponse<Acao>>  {
        return this.http.post<Acao>("/api/acoes/save", acao, {observe: 'response'});
    }

    getAcoes(): Observable<HttpResponse<Acao[]>> {
        return this.http.get<Acao[]>("/api/acoes", {observe: 'response'});
    }
}
