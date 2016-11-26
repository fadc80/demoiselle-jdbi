import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Contact } from './contact';
import { IContactsService, ContactsService } from './contacts.service';

const CONTACTS: Contact[] = [
  {id: 1, firstName: "Ronald", lastName: "Reagan",  phoneNumberList:[]},
  {id: 1, firstName: "Gerald", lastName: "Ford",    phoneNumberList:[]},
  {id: 1, firstName: "Lyndon", lastName: "Jonhson", phoneNumberList:[]} 
]; 

@Injectable()
export class ContactsServiceMock implements IContactsService {

  public findAll(): Promise<Contact[]> {
    return new Promise<Contact[]>((resolve, reject)=> { resolve(CONTACTS) });
  }

}
