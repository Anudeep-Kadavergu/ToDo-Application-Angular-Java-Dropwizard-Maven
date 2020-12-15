import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SharedService {
  readonly APIUrl = "http://127.0.0.1:8080";

  constructor( private http:HttpClient) { }

  getToDos():Observable<any[]>{
    return this.http.get<any[]>(this.APIUrl + '/todos');
  }

  getTags():Observable<any[]>{
    return this.http.get<any[]>(this.APIUrl + '/todos/tags');
  }

  addToDo(val:any){
    return this.http.post<number>(this.APIUrl + '/todos', val);
  }

  assignTag(val:any){
    return this.http.post<any[]>(this.APIUrl + '/todos/assigntag', val);
  }

  addTag(val:any){
    return this.http.post<any[]>(this.APIUrl + '/todos/tags', val);
  }
  updateToDo(val:any){
    return this.http.put(this.APIUrl + '/todos/', val);
  }
  updateToDoById(val:any){
    return this.http.put(this.APIUrl + '/todos/'+val.id, val);
  }
  completeToDo(val:any){
    return this.http.post(this.APIUrl + '/todos/markcomplete',val);
  }

  deleteToDo(val:any){
    console.log(val);
    return this.http.delete(this.APIUrl + '/todos/'+val);
  }
}
