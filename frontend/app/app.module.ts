import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';

import { AppComponent }       from './app.component';
import { ContactService } from './contact.service';

@NgModule({
  imports:      [ BrowserModule, HttpModule],
  declarations: [ AppComponent ],
  providers:    [ ContactService ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }

