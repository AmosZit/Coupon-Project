import { Component, OnInit } from '@angular/core';
import { LoginService } from '../shared/service/login.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor( private myLoginService : LoginService) { 
    
}

  
  public login(): void {
     this.myLoginService.login();
    
  
 }

 
  ngOnInit() {

  }

}
