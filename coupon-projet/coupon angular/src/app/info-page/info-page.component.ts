import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../shared/service/customer.service';
import { User } from '../shared/models/User';
import { Customer } from '../shared/models/Customer';
import { Coupon } from '../shared/models/coupon';
import { Purchase } from '../shared/models/Purchase';

@Component({
  selector: 'app-info-page',
  templateUrl: './info-page.component.html',
  styleUrls: ['./info-page.component.css']
})

export class InfoPageComponent implements OnInit {
  public users: User;
  private token : string ;
  public id: string;
 public customerId : String; 
 public customer : Customer;  
 public coupon : Coupon; 
 public amounts : number; 
 public purchase : Purchase ;

  
 constructor( private myCustomerService : CustomerService) {
this.customer = new Customer();
this.coupon = new Coupon();
this.token = sessionStorage.getItem("token"); 
this.id= sessionStorage.getItem("id");
this.customerId = this.id ; 
this.purchase = new Purchase();

    
 }

 public showAllCoupon () : void {
  this.myCustomerService.getOneCustomer(this.token , this.customerId ); 
 }
 public getCustomerInfo () : void {
  this.myCustomerService.getCustomerInfo(this.token , this.customerId ); 
 }
 
 public getCustomerPurchase () : void {
  this.myCustomerService.getCustomerPurchase(this.token , this.customerId ); 
 }
 public deleteThisCustomer () : void {
   this.myCustomerService.deleteCustomer(this.token  , this.customerId);
 }
 public updateThisCustomer (customer : Customer) : void {
   customer.id = this.id;
   if(customer.firstName == null){
     customer.firstName = this.myCustomerService.oneCustomer.firstName; 
   }
   if(customer.lastName == null){
    customer.lastName = this.myCustomerService.oneCustomer.lastName; 
  }
  this.myCustomerService.updateCustomer(this.token  , customer);
}
public infoAmount () :void {
  
for (  let purchase of this.myCustomerService.allPurchase){
  alert(purchase.amounts)
  this.amounts = purchase.amounts;
  
}
}
 
 ngOnInit() {
  this.showAllCoupon();
  this.getCustomerInfo();
 this.getCustomerPurchase(); 
 this.infoAmount();
}
}