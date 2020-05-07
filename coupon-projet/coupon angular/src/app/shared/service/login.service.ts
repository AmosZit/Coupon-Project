import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { User } from '../models/User';
import { UserDataClient } from '../models/UserDataClient';
import { Customer } from '../models/Customer';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private clientData: UserDataClient;
  private user : User ; 
  public token : string ; 


  constructor(private http : HttpClient , private router : Router) {
    this.user = new User();




    
   }
   public login(): void {
    let observable = this.http.post<UserDataClient>("http://localhost:8080/user/login", this.user);

    observable.subscribe(res => {
      this.clientData = res;
   
      if (res.clientType === 'Compagny') {
        this.router.navigate(["company"]);
        sessionStorage.setItem("company", this.clientData.companyId + "");


        
      }
      if (res.clientType === 'Customer') {
        this.router.navigate(["customer"]);

      } 
      if (res.clientType === 'Administator') {
        this.router.navigate(["administrator"]);
      }

      sessionStorage.setItem("token", this.clientData.token + "");
      sessionStorage.setItem("id", this.clientData.id + ""); 


    }, err => {
      alert("Oh crap !.... Error! Status: " + err.status + ", Message: " + err.message);
    });

  }
  
  public register(customer : Customer): void {
    
    let observable = this.http.post<Customer>("http://localhost:8080/customer", customer);
    observable.subscribe(UserObjReturned => {
      customer = UserObjReturned ;
    
      alert("It works !!!");
      this.router.navigate(["/login"]);

    }, err => {
      alert("Register failed, Status: " + err.status + ", Message: " + err.message);

    });

  }

  
  
  ngOnInit() {
  }

}