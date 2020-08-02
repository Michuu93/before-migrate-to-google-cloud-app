import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Data} from "./data";
import {environment} from '../../environments/environment';
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class FormService {
  constructor(private http: HttpClient) {
  }

  upsertData(data: Data): Observable<Object> {
    console.log(`Sending ${JSON.stringify(data)}`);
    return this.http.post(`${environment.apiUrl}/api`, data);
  }

  findByUuid(uuid: string): Observable<Object> {
    console.log(`Find by uuid: ${uuid}`);
    return this.http.get(`${environment.apiUrl}/api/${uuid}`);
  }

  findAll(): Observable<Object[]> {
    console.log(`Find all`);
    return this.http.get<Object[]>(`${environment.apiUrl}/api`);
  }

  deleteAll(): Observable<Object> {
    console.log(`Delete all`);
    return this.http.delete(`${environment.apiUrl}/api`);
  }
}
