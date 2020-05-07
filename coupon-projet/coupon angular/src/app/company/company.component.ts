import { Component, OnInit } from '@angular/core';
import { Company } from '../shared/models/Company';
import { CompanyService } from '../shared/service/company.service';
import { AdministratorService } from '../shared/service/administrator.service';
import { User } from '../shared/models/User';
import { Coupon } from '../shared/models/coupon';
import { CouponService } from '../shared/service/coupon.service';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  public company: Company;
  public user : User; 
  private token : string ;
  private idCompany : String ;
  public id: string;
  public coupon : Coupon; 
  




  constructor( private myCompanyService : CompanyService , public myAdminService : AdministratorService , public myCouponService : CouponService ) {    
    this.company = new Company();
    this.user = new User() ; 
    this.coupon = new Coupon()
    this.token = sessionStorage.getItem("token"); 
    this.id= sessionStorage.getItem("id");
    this.idCompany = sessionStorage.getItem("company");

  }

  public usersInfo() : void {
    this.myAdminService.getUsers( this.token , this.id);

  }
  public getCouponCompany():void {
     this.myCompanyService.showAllCouponByCompany(this.token , this.idCompany); 
  }
  public deleteThisCoupon(couponId : String) : void {
    this.myCompanyService.deleteCoupon(this.token  , couponId);
  }
  public getOneCompany() : void {
this.myCompanyService.getOneCompany(this.token , this.idCompany) 
}
public updateCoupon( coupon : Coupon) : void{
  coupon.compagnyId= this.idCompany ;
  this.myCouponService.updateCoupon( this.token , coupon);
}

onChangeOption(event){
  this.coupon.category = event;
}
public showAllCouponByCategoryAndCompany (category) : void {
  this.myCompanyService.showAllCouponByCategoryAndCompany(category , this.token , this.idCompany ); 
}


onChangeOptionCoupon(event){
  let option = event;
  if(option == 'All')
    this.getCouponCompany();
  else
    this.showAllCouponByCategoryAndCompany(option)
}
  

  ngOnInit() {
    this.usersInfo();
    this.onChangeOptionCoupon('All');
    this.getOneCompany();

  }
}