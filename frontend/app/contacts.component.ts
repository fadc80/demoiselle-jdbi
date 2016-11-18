import { Component, OnInit } from '@angular/core';
import { Contact }   from './contact';

import { ContactService } from './contact.service';

@Component({
  moduleId: module.id,
  selector: 'contacts',
  templateUrl: 'contacts.component.html',
  styleUrls: ['contacts.component.css']
})

export class ContactsComponent implements OnInit { 
  contacts: Contact[];
  selectedContact: Contact;

  constructor(private contactService: ContactService) { }

  ngOnInit(): void {
    this.contactService.findAll().then(
      contacts => this.contacts = contacts
    );
  }

  onSelect(contact: Contact): void {
    this.selectedContact = contact;
  }
};
