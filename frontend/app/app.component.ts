import { Component } from '@angular/core';

export class Contact {
  id: number;
  firstName: string;
  lastName: String;
};

const CONTACTS: Contact[] = [
  { id: 1, firstName: 'John',    lastName: 'Kennedy' },
  { id: 2, firstName: 'Richard', lastName: 'Nixon' },
  { id: 3, firstName: 'Jorge',   lastName: 'Bush' },
  { id: 4, firstName: 'Jimmy',   lastName: 'Carter' },
  { id: 5, firstName: 'Harry',   lastName: 'Thruman' }
];

@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <h2>All Contacts</h2>
    <ul class="contacts">
      <li *ngFor="let contact of contacts"
        [class.selected]="contact === selectedContact"
        (click)="onSelect(contact)">
        <span class="badge">{{contact.id}}</span> {{contact.firstName}} {{contact.lastName}}
      </li>
    </ul>
  `,
  styles: [`
    .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .contacts {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .contacts li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .4em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
    }
    .contacts li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .contacts li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .heroes .text {
      position: relative;
      top: -3px;
    }
    .contacts .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `]
})

export class AppComponent { 
  title = 'Contact List';
  contacts = CONTACTS;
  selectedContact: Contact;

  onSelect(contact: Contact): void {
    this.selectedContact = contact;
  }
};
