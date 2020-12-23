import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { AllUsersComponent } from './all-users/all-users.component';
import { SearchComponent } from './search/search.component';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from './admin/admin.component';
import {AddCompanyComponent}from './add-company/add-company.component';
import {AddIpoComponent}from './add-ipo/add-ipo.component';
import { UserOperationsComponent } from './user-operations/user-operations.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ViewIPOComponent } from './view-ipo/view-ipo.component';
import { CompareCompanyComponent } from './compare-company/compare-company.component';
import { UploadExcelComponent } from './upload-excel/upload-excel.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompareSectorComponent } from './compare-sector/compare-sector.component';









const routes: Routes = [
  {
    path : "", redirectTo: "/login" , pathMatch : "full"
  },
  {
    path : "register" , component : RegisterComponent
  },
  {
    path : "search" , component : SearchComponent
  },
  {
    path : "all-users", component : AllUsersComponent
  },
  {
    path : "login", component : LoginComponent
  },
  {
    path : "admin", component : AdminComponent
  },
  {
     path: "addcompany", component:AddCompanyComponent
  },
  {
    path: "addIpo", component:AddIpoComponent
  },
  {
    path:"useroperations", component:UserOperationsComponent
  },
  {
    path: "updateprofile", component:UpdateProfileComponent
  },
  {
     path: "viewipo", component:ViewIPOComponent
  },
  {
    path: "comparecompany", component:CompareCompanyComponent
  },
  {
    path:"uploadexcel", component:UploadExcelComponent
  },
  {
    path:"companylist", component:CompanyListComponent
  },
  {
    path:"comparesector",component:CompareSectorComponent
  },
 
  
  
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
