import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Acao, Analise } from "../models";

@Injectable()
export class AnaliseService {

    constructor(private http: HttpClient) {}

    save(analise: Analise): Observable<HttpResponse<Analise>>  {
        return this.http.post<Analise>("/api/analises/save", analise, {observe: 'response'});
    }

    delete(analise: Analise): Observable<HttpResponse<boolean>> {
      return this.http.delete<boolean>("/api/analises/delete/" + analise.id, {observe: 'response'});
    }

    list(): Observable<HttpResponse<Analise[]>> {
        return this.http.get<Analise[]>("/api/analises", {observe: 'response'});
    }
}
