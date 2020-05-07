import { Component, OnInit } from '@angular/core';
import { Coupon } from '../shared/models/coupon';
import { Company } from '../shared/models/Company';
import { CompanyService } from '../shared/service/company.service';
import { AdministratorService } from '../shared/service/administrator.service';
import { CouponService } from '../shared/service/coupon.service';
import { User } from '../shared/models/User';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {
  public company: Company;
  public user : User; 
  private token : string ;
  private idCompagny : String ;
  public id: string;
  public coupon : Coupon; 
  




  constructor( public myAdminService : AdministratorService , public myCouponService : CouponService ) {    
    this.company = new Company();
    this.coupon = new Coupon()
    this.user = new User();
    this.token = sessionStorage.getItem("token"); 
    this.id= sessionStorage.getItem("id");
    this.idCompagny = sessionStorage.getItem("company");

  }
  
onChangeOption(event){
  this.coupon.category = event;
}
  public creatCoupon( coupon : Coupon) : void{
    coupon.compagnyId= this.idCompagny ;
    alert(coupon.startDate)
    alert(coupon.endDate)

    this.myCouponService.creatCoupon( this.token , coupon);
  }
 
    
  ngOnInit() {
  }

}