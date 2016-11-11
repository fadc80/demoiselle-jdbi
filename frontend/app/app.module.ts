import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';

import { AppComponent }       from './app.component';
import { ContactListService } from './contact-list.service';

@NgModule({
  imports:      [ BrowserModule, HttpModule],
  declarations: [ AppComponent ],
  providers:    [ ContactListService ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }

