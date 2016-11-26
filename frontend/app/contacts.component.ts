import { Component, OnInit } from '@angular/core';
import { Contact }   from './contact';

import { ContactsService } from './contacts.service';

@Component({
  moduleId: module.id,
  selector: 'contacts',
  templateUrl: 'contacts.component.html',
  styleUrls: ['contacts.component.css']
})

export class ContactsComponent implements OnInit { 
  contacts: Contact[];
  selectedContact: Contact;

  constructor(private contactService: ContactsService) { }

  ngOnInit(): void {
    this.contactService.findAll().then(
      contacts => this.contacts = contacts
    );
  }

  onSelect(contact: Contact): void {
    this.selectedContact = contact;
  }
};
