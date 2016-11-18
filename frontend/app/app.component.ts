import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <h2>{{subtitle}}</h2>
    <contacts></contacts>  
  `
})

export class AppComponent { 
  title = 'Simple Contact List';
  subtitle = 'Contacts';
};
