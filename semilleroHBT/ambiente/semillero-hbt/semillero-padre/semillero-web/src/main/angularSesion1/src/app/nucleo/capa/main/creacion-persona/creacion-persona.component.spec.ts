import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreacionPersonaComponent } from './creacion-persona.component';

describe('CreacionPersonaComponent', () => {
  let component: CreacionPersonaComponent;
  let fixture: ComponentFixture<CreacionPersonaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreacionPersonaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreacionPersonaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
