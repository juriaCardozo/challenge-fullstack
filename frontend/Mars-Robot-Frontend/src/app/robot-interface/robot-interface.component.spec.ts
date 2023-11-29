import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RobotInterfaceComponent } from './robot-interface.component';

describe('RobotInterfaceComponent', () => {
  let component: RobotInterfaceComponent;
  let fixture: ComponentFixture<RobotInterfaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RobotInterfaceComponent]
    });
    fixture = TestBed.createComponent(RobotInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
