import { Component, OnInit } from '@angular/core';
import { AdministratorService } from '../shared/service/administrator.service';
import { Company } from '../shared/models/Company';
import { User } from '../shared/models/User';
import { CompanyService } from '../shared/service/company.service';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit {
  public company: Company;
  public company2: Company;
  public user: User;
  private token : string ;
  public id: string;

  
  constructor( private myAdminService : AdministratorService , private myCompanyService : CompanyService) { 
this.company = new Company() ; 
this.company2 = new Company() ; 
this.user = new User();
this.company.user = new User();
this.token = sessionStorage.getItem("token"); 
this.id= sessionStorage.getItem("id");
  }

  
  public UsersInfo() : void {
    this.myAdminService.getUsers( this.token , this.id);

  }

public allCompagnie(): void {
this.myAdminService.getAllCompany(this.token); 
}


public allUserCompany(company): void {
  this.company2 = company;
  this.myAdminService.getAllUserByCompany(this.token , company.id); 
  }

  public deleteThisCompany (compagnyId : String) : void {
    this.myCompanyService.deleteCompany(this.token  , compagnyId);
  }

  
  public registerCompany( compagny : Company): void {
    this.myAdminService.registerCompany(this.token , compagny );
   
 }
 
 public updateCompany(compagny : Company) : void {
  this.myCompanyService.updateCompany( this.token , compagny);
  }
onChangeOption(event){
    this.user.type = event;
  }
  public updateUsers(user : User) : void {
   this.myAdminService.updateUsers( this.token , user);
   }

  ngOnInit() {
    this.UsersInfo();
    this.allCompagnie();
  }
}