import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AllUsersComponent } from './all-users/all-users.component';
import { SearchComponent } from './search/search.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AddCompanyComponent } from './add-company/add-company.component';
import { AddIpoComponent } from './add-ipo/add-ipo.component';
import { UserOperationsComponent } from './user-operations/user-operations.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ViewIPOComponent } from './view-ipo/view-ipo.component';
import { CompareCompanyComponent } from './compare-company/compare-company.component';
import { UploadExcelComponent } from './upload-excel/upload-excel.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompareSectorComponent } from './compare-sector/compare-sector.component';




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    
    AllUsersComponent,
    SearchComponent,
    FooterComponent,
    LoginComponent,
    AdminComponent,
    AddCompanyComponent,
    AddIpoComponent,
    UserOperationsComponent,
    UpdateProfileComponent,
    ViewIPOComponent,
    CompareCompanyComponent,
    UploadExcelComponent,
    CompanyListComponent,
    CompareSectorComponent,
    
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
