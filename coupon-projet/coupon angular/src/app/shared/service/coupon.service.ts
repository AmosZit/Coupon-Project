import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Coupon } from '../models/coupon';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  public coupon : Coupon; 
  public allCoupon : Coupon[];
  public allCouponCategory : Coupon[];




  constructor(private http : HttpClient) {
    this.coupon = new Coupon ();      
   }


   public showAllCoupon (token : string) : void {
  let observable = this.http.get<Coupon[]>("http://localhost:8080/coupons?token="+token);{
  observable.subscribe(res => { 
     this.allCoupon = res;
  
  },
    err => {
       console.log(err);
       }
  )
  }
  }
         

  public updateCoupon(token : string , coupon : Coupon ) : void {
    let observable = this.http.put<Coupon>("http://localhost:8080/coupons?token="+ token, coupon );{
    observable.subscribe(res => { 
      alert("update Compagny works !!!");

    },
      err => {
         console.log(err);
        }
        )
        }
        }
               

  public creatCoupon(token : string , coupon : Coupon ) : void {
    let observable = this.http.post<Coupon>("http://localhost:8080/coupons?token="+ token, coupon );{
    observable.subscribe(res => { 
      alert("creat Compagny works !!!");

    },
      err => {
         console.log(err);
        }
        )
        }
        }


   public showAllCouponByCategory (  category : string ,  token : string  ) : void {
    let observable = this.http.get<Coupon[]>("http://localhost:8080/coupons/category?type="+ category + "&token="+ token);{
    observable.subscribe(res => { 
       this.allCoupon = res;
    
    },
      err => {
         console.log(err);
         }
    )
    }
    }

    
           
}