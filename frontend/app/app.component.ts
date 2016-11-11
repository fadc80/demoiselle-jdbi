import { Component, OnInit } from '@angular/core';
import { Contact }   from './contact';

import { ContactListService } from './contact-list.service';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})

export class AppComponent implements OnInit { 
  title = 'Simple Contact List';
  subtitle = 'Contacts';
  contacts: Contact[];
  selectedContact: Contact;

  constructor(private contactListService: ContactListService) { }

  ngOnInit(): void {
    this.contactListService.findAll().then(
      (contacts)=>this.contacts=contacts);
  }

  onSelect(contact: Contact): void {
    this.selectedContact = contact;
  }
};
