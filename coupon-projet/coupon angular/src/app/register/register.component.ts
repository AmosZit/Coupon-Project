import { Component, OnInit } from '@angular/core';
import { LoginService } from '../shared/service/login.service';
import { User } from '../shared/models/User';
import { Customer } from '../shared/models/Customer';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public users : User ; 
  public customer : Customer ; 



  constructor( private myRegisterService : LoginService) { 
    this.users = new User(); 
    this.customer = new Customer(); 
    this.customer.user = new Customer();
}

  
  public register(customer : Customer): void {
    this.myRegisterService.register(customer);
   
 }
 
 
 ngOnInit() {
}

}

  