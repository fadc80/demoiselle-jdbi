import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Contact } from './contact';

export interface IContactService {
  findAll(): Promise<Contact[]>;
}

@Injectable()
export class ContactService implements IContactService {

  constructor(private http: Http) { }

  public findAll(): Promise<Contact[]> {
    return this.http.get('/rest/contacts')
      .toPromise().then(response => response.json() as Contact[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
