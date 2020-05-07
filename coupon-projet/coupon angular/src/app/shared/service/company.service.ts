import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { Coupon } from '../models/coupon';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Company } from '../models/Company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  public token : string ;
  public coupon : Coupon;  
  public Company : Company; 
  public allCouponByCompany : Coupon[];
  public allCouponByCompanyAndCategory  : Coupon[];
  public users: User;
  public companyInfo : Company ; 
 public  usersCompany: User;



  constructor(private http : HttpClient , private router : Router) {
    this.users = new User();
    this.coupon = new Coupon();
    this.Company = new Company; 
  }

  public getOneCompany(token : string , companyId : String) : void {
    let observable = this.http.get<Company>("http://localhost:8080/company/id?id="+ companyId +"&token=" + token);{
    observable.subscribe(res => { 
      this.companyInfo = res ; 
    
    },
      err => {
         console.log(err);
        }
        )
        
        }
        }
        public getAllUserByCompany(token : string , idCompany : String): void {
          let observable = this.http.get<User>("http://localhost:8080/company/"+idCompany+"?token="+token);{
          observable.subscribe(UserObjReturned => {
            this.usersCompany = UserObjReturned;
          }, err => {
            alert("Register failed, Status: " + err.status + ", Message: " + err.message);
      
          });
      
        }
      
      }

    

  public updateCompany(token : string , company : Company ) : void {
    let observable = this.http.put<Company>("http://localhost:8080/company?token="+ token, company );{
    observable.subscribe(res => { 
      alert("update Compagny works !!!");

    },
      err => {
         console.log(err);
        }
        )
        }
        }
  
        public deleteCompany (token : String , companyId : String) : void {
          let observable = this.http.delete<Company>("http://localhost:8080/company/"+ companyId +"?token=" + token);{
          observable.subscribe(res => { 
            alert("the compagny is delete")
          
          },
            err => {
               console.log(err);
              }
              )
              }
              }

              
   public showAllCouponByCompany (token : string , idCompany : String ) : void {
    let observable = this.http.get<Coupon[]>("http://localhost:8080/coupons/couponByCompany?companyId="+ idCompany +"&token=" + token);{
    observable.subscribe(res => { 
    this.allCouponByCompany = res ;
    },
      err => {
         console.log(err);
         }
    )
    }
    }

    public deleteCoupon (token : String , couponId : String) : void {
      let observable = this.http.delete<Coupon>("http://localhost:8080/coupons/"+ couponId +"?token=" + token);{
      observable.subscribe(res => { 
      alert("coupon is delete");
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

   public showAllCouponByCategoryAndCompany(  category : string ,  token : string  ,  idCompany : String ) : void {
    let observable = this.http.get<Coupon[]>("http://localhost:8080/coupons/categoryAndCompanyId?type="+ category + "&companyId="+ idCompany +"&token=" + token);{
    observable.subscribe(res => { 
       this.allCouponByCompany = res;
    
    },
      err => {
         console.log(err);
         }
    )
    }
    }


}