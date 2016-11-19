
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { DebugElement } from '@angular/core';
import { Http } from '@angular/http';
import { By } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ContactService } from './contact.service';
import { ContactServiceMock } from './contact.service.mock'
import { ContactsComponent } from './contacts.component'

let fixture: ComponentFixture<AppComponent>;
let component: AppComponent;
let debugElement: DebugElement;

describe('AppComponent', () => {

  beforeEach(async(() => {
    return TestBed.configureTestingModule({
      declarations: [ AppComponent, ContactsComponent ],
      providers: [{provide: ContactService, useValue: new ContactServiceMock()},]
    }).compileComponents().then(()=>{
        fixture = TestBed.createComponent(AppComponent);
        component = fixture.componentInstance;
    }); 
  }));
  
  it('Should display original title', () => {
    fixture.detectChanges();
    debugElement = fixture.debugElement.query(By.css('h1'));
    expect(debugElement.nativeElement.textContent).toContain(component.title);
  });
});