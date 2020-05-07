import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Company } from '../models/Company';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AdministratorService {

  public compagny: Company;
  public allCompany : Company[];
  public allUsersCompany : User;  
  public oneUser : User ;
  public compagnyId : number; 


  
  constructor(private http : HttpClient , private router : Router) {
    this.compagny = new Company();
    this.oneUser = new User ();
     
   }

    
   public getUsers (token : string , usersId : String) : void {
    let observable = this.http.get<User>("http://localhost:8080/user/"+ usersId +"?token=" + token);{
    observable.subscribe(res => { 
      this.oneUser = res ; 
    },
      err => {
         console.log(err);
        }
        )
        
        }
        }

   
   public getAllCompany(token : string): void {
    let observable = this.http.get<Company[]>("http://localhost:8080/company?token="+token);{
    observable.subscribe(UserObjReturned => {
      this.allCompany = UserObjReturned;
    }, err => {
      alert("Register failed, Status: " + err.status + ", Message: " + err.message);

    });

  }

   }
    
   public getAllUserByCompany(token : string , idCompagny : number): void {
    let observable = this.http.get<User>("http://localhost:8080/company/"+idCompagny+"?token="+token);{
    observable.subscribe(UserObjReturned => {
      this.allUsersCompany = UserObjReturned;
    }, err => {
      alert("Register failed, Status: " + err.status + ", Message: " + err.message);

    });

  }

}
  public registerCompany(token : string , compagny : Company ): void {
    let observable = this.http.post<Company>("http://localhost:8080/company?token="+ token, compagny);{
    observable.subscribe(UserObjReturned => {
      alert("Creat Compagny works !!!");

    }, err => {
      alert("Register failed, Status: " + err.status + ", Message: " + err.message);

    });

  }

   }
   public updateUsers(token : string , user : User ) : void {
          let observable = this.http.put<User>("http://localhost:8080/user?token="+ token, user );{
          observable.subscribe(res => { 
            alert("update users works !!!");
      
          },
            err => {
               console.log(err);
              }
              )
              }
              }
              

          public creatUser(user : User , token : string ): void {
    
            let observable = this.http.post<User>("http://localhost:8080/user?token="+ token,  user);
            observable.subscribe(UserObjReturned => {
            
              alert("user creat ");
        
            }, err => {
              alert("Register failed, Status: " + err.status + ", Message: " + err.message);
        
            });
        
          }
          


  }