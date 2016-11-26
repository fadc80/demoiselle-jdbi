import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';

import { AppComponent }      from './app.component';
import { ContactsComponent } from './contacts.component';
import { ContactsService }    from './contacts.service';

@NgModule({
  imports: [ 
    BrowserModule, 
    HttpModule
  ],
  declarations: [ 
    AppComponent, 
    ContactsComponent 
  ],
  providers: [ ContactsService ],
  bootstrap: [ AppComponent ]
})

export class AppModule { }

