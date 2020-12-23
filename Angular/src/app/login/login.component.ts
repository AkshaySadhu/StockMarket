import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserRegisterService } from '../user-register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  [x: string]: any;

  user : User  = new User("","","",0,"");
  message : any;

  constructor(private service : UserRegisterService,private router: Router) { }

  ngOnInit() {
  }

  public Login(){

    
       
    if(this.username=="admin" && this.password=="password")
    {
      this.router.navigate(['/admin']);
    }
    else
    {
      let reponse = this.service.dologin(this.user);
      reponse.subscribe(data => {
        this.message = data;
      
           this.router.navigate(['/useroperations']);
        
      });
    }
  
   
   
  }
  
  onSignup() {
    this.router.navigate(['/register']);
  }
}




