import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurhcaseOrderListComponent } from './purhcase-order-list.component';

describe('PurhcaseOrderListComponent', () => {
  let component: PurhcaseOrderListComponent;
  let fixture: ComponentFixture<PurhcaseOrderListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PurhcaseOrderListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PurhcaseOrderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
