import { Component, OnInit } from '@angular/core';
import { Company } from '../shared/models/Company';
import { User } from '../shared/models/User';
import { AdministratorService } from '../shared/service/administrator.service';
import { CompanyService } from '../shared/service/company.service';

@Component({
  selector: 'app-info-company',
  templateUrl: './info-company.component.html',
  styleUrls: ['./info-company.component.css']
})
export class InfoCompanyComponent implements OnInit {
  public company: Company;
  public user: User;
  private token : string ;
  public id: string;
  public idCompany : string; 


  
  constructor( private myAdminService : AdministratorService , private myCompanyService : CompanyService) { 
this.company = new Company() ; 
this.user = new User();
this.company.user = new User();
this.token = sessionStorage.getItem("token"); 
this.id= sessionStorage.getItem("id");
this.idCompany = sessionStorage.getItem("company");

  }

  public UsersInfo() : void {
    this.myAdminService.getUsers( this.token , this.id);

  }


  public creatUser(user : User): void {
    this.user.type = "Compagny";
    this.user.companyId = this.idCompany;
    this.myAdminService.creatUser(user , this.token);
   
 }
 
  public updateCompany(compagny : Company) : void {
    this.myCompanyService.updateCompany( this.token , compagny);
    }

public allUserCompany(): void {
  this.myCompanyService.getAllUserByCompany(this.token , this.idCompany); 
  }
  ngOnInit() {
    this.UsersInfo();
    this.allUserCompany();
  }

}