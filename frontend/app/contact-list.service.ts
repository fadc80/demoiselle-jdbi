import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Contact } from './contact';

@Injectable()
export class ContactListService {

  constructor(private http: Http) { }

  public findAll(): Promise<Contact[]> {
    return this.http.get('http://localhost:8080/simple-contact-list/rest/contacts')
      .toPromise().then((response)=>response.json().data as Contact[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
