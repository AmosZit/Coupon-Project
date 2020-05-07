import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/User';
import { Coupon } from '../models/coupon';
import { Customer } from '../models/Customer';
import { Router } from '@angular/router';
import { Purchase } from '../models/Purchase';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  public customer: Customer;
  public oneCustomer : Customer ;
  public infoCustomer : Customer;  
  public customerId : String ; 
  public token : string ; 
  public allCoupon : Coupon[];
  public buyCoupon : Coupon;
  public allPurchase : Purchase[];
  public purchase : Purchase; 


  constructor(private http : HttpClient  , private router : Router) {
    this.customer = new Customer() ;
    this.customer.user = new User ();
    
     
   }

   
   public getOneCustomer (token : string , customerId : String) : void {
    let observable = this.http.get<Customer>("http://localhost:8080/customer/"+ customerId +"?token=" + token);{
    observable.subscribe(res => { 
       this.oneCustomer = res;
    
    },
      err => {
         console.log(err);
        }
        )
        }
        }

        
   public getCustomerInfo (token : string , customerId : String) : void {
    let observable = this.http.get<Customer>("http://localhost:8080/customer/detailOfCustomer?id="+ customerId +"&token=" + token);{
    observable.subscribe(res => { 
       this.infoCustomer = res;
    
    },
      err => {
         console.log(err);
        }
        )
        }
        }
              

        public getCustomerPurchase (token : string , customerId : String) : void {
          let observable = this.http.get<Purchase[]>("http://localhost:8080/customer/allPurchaseByCustomer?id="+ customerId +"&token=" + token);{
          observable.subscribe(res => { 
             this.allPurchase = res;
          
          },
            err => {
               console.log(err);
              }
              )
              }
              }
      
        public byCoupon( customerId : String , amout : number , token : string  , coupon  : Coupon): void {
    
          let observable = this.http.put<Coupon>("http://localhost:8080/coupons/byCoupon?customer="+customerId+"&amount="+ amout+ "&token=" + token , coupon);
          observable.subscribe(UserObjReturned => {
            alert("you have buy "+amout +" "+coupon.title)
          }, err => {
            alert("Register failed, Status: " + err.status + ", Message: " + err.message);
      
          });
      
        }
      
        public updateCustomer (token : string , customer : Customer ) : void {
          let observable = this.http.put<Customer>("http://localhost:8080/customer?token="+ token, customer );{
          observable.subscribe(res => { 
            this.router.navigate(["/customer"]);
            this.oneCustomer = res;
          
          },
            err => {
               console.log(err);
              }
              )
              }
              }

              public deleteCustomer (token : String , customerId : String) : void {
                let observable = this.http.delete<Customer>("http://localhost:8080/customer/"+ customerId +"?token=" + token);{
                observable.subscribe(res => { 
                  this.router.navigate(["/login"]);
                
                },
                  err => {
                     console.log(err);
                    }
                    )
                    }
                    }
        
      }
      
      