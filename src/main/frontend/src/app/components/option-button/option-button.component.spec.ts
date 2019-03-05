import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionButtonComponent } from './option-button.component';

describe('OptionButtonComponent', () => {
  let component: OptionButtonComponent;
  let fixture: ComponentFixture<OptionButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OptionButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OptionButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
