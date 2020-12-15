import { Component } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  accountForm = new FormGroup({
    description: new FormControl(),
    tag: new FormControl(),
    startDate: new FormControl(),
    endDate: new FormControl()
  });

  accountForm1 = new FormGroup({
    tag: new FormControl(),
  });

  accountForm2 = new FormGroup({
    id: new FormControl(),
    description: new FormControl(),
    tag: new FormControl(),
    startDate: new FormControl(),
    endDate: new FormControl(),
    status: new FormControl()
  });
  
  constructor(private service:SharedService) { }

  ToDoList:any=[];
  TagsList:any = [];
  CurrentData:any = [];

  ShowModal:boolean=false;

  ngOnInit(): void {
    this.refreshToDo();
  }
 
  refreshToDo(){
    this.service.getToDos().subscribe(data=>{
      this.ToDoList=data;
    });
    this.service.getTags().subscribe(data=>{
      this.TagsList = data;
    });
  }

  taskCompleted(event:any,item:any){
    this.service.completeToDo(item).subscribe();
    this.refreshToDo();
  }
  taskDelete(event:any,item:any){
    this.service.deleteToDo(item.id).subscribe();
    this.refreshToDo();
  }

  taskUpdate(event:any,item:any){
    this.CurrentData=item;
    this.refreshToDo();
    this.refreshToDo();
  }
  add(){
    var data = this.accountForm.value;
    var tag = parseInt(data['tag']);
    data['status']="pending";
    this.service.addToDo(data).subscribe(data1=>{
    });
    this.accountForm.reset();
    this.refreshToDo();
    this.refreshToDo();
  }
  addTag(){
    this.service.addTag(this.accountForm1.value).subscribe();
    this.accountForm1.reset();
    this.refreshToDo();
    this.refreshToDo();
  }
  updateTodo(){
    const data:any = this.CurrentData
    for (const [key, value] of Object.entries(this.accountForm2.value)) {
      if(value!=null){
        data[key]=value;
      }
    }
    this.service.updateToDoById(data).subscribe();
    this.refreshToDo();
    const modals = document.getElementsByClassName('modal');
    for(let i=0; i<modals.length; i++) {
      modals[i].classList.remove('show');
      modals[i].setAttribute('aria-hidden', 'true');
      modals[i].setAttribute('style', 'display: none');
    }
     const modalsBackdrops = document.getElementsByClassName('modal-backdrop');

     for(let i=0; i<modalsBackdrops.length; i++) {
       document.body.removeChild(modalsBackdrops[i]);
     }
     this.refreshToDo();
  }
  days(dateSent:any){
    let currentDate = new Date();
    dateSent = new Date(dateSent);

    var days =  Math.floor((Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) -  Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate())) /(1000 * 60 * 60 * 24));
    if(days>0){
      return days+" days to go";
    }
    else{
      return "Deadline Completed";

    }
  }
}
