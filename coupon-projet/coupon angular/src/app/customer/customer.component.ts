import { Component, OnInit } from '@angular/core';
import { User } from '../shared/models/User';
import { CouponService } from '../shared/service/coupon.service';
import { CustomerService } from '../shared/service/customer.service';
import { Coupon } from '../shared/models/coupon';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  private token : string ;
  private id : string ;
  public amounts : number ; 
  public coupon : Coupon ; 
  public user: User;
  public selected : string ; 

  
  constructor( private myCouponService : CouponService, private myCustomerService : CustomerService) {
    this.user = new User();
    this.coupon = new Coupon();
 

    this.token = sessionStorage.getItem("token"); 
    this.id= sessionStorage.getItem("id");
      
 
    }

    onChangeOption(event){

      let option = event;
      if(option == 'All')
        this.showAllCoupon();
      else
        this.showAllCouponByCategory(option)
    }
   
      public showAllCoupon () : void {
       this.myCouponService.showAllCoupon(this.token); 
      }
      public oneCustomer() : void {
        this.myCustomerService.getOneCustomer( this.token , this.id);
      }
      public byCoupon( coupon : Coupon) : void{
        this.myCustomerService.byCoupon( this.id , this.amounts , this.token , coupon);
      }
      public amountCoupon (amount : number) : void{
         this.amounts = amount ; 
      }

      public showAllCouponByCategory (category) : void {
        this.myCouponService.showAllCouponByCategory( category , this.token ); 
       }
     
      ngOnInit() {
    
        this.oneCustomer();
        this.onChangeOption('All');
       
      }
    
    }
    