import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { CustomerComponent } from './customer/customer.component';
import { CompanyComponent } from './company/company.component';
import { AdministratorComponent } from './administrator/administrator.component';
import { RegisterComponent } from './register/register.component';
import { InfoPageComponent } from './info-page/info-page.component';
import { AddCouponComponent } from './add-coupon/add-coupon.component';
import { InfoCompanyComponent } from './info-company/info-company.component';


const routes: Routes = [
  { path: "login", component: LoginComponent }, 
  { path: "register", component: RegisterComponent },   
  { path: "customer", component: CustomerComponent },  
  { path: "administrator", component: AdministratorComponent}, 
  { path: "InfoPage", component: InfoPageComponent}, 
  { path: "company", component: CompanyComponent}, 
  { path: "add-coupon", component: AddCouponComponent}, 
  { path: "info-company", component: InfoCompanyComponent}, 


  
  

  { path: "", redirectTo: "login", pathMatch: "full" }, // pathMatch = התאמת המחרוזת הריקה לכלל הנתיב    
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CustomerComponent,
    CompanyComponent,
    AdministratorComponent,
    RegisterComponent,
    InfoPageComponent,
    AddCouponComponent,
    InfoCompanyComponent,    
    
    
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
