import { Component, OnInit } from '@angular/core';
import { ProviderService } from '../../../services/provider.service';

@Component({
  selector: 'provider-list',
  templateUrl: './provider-list.component.html',
  styleUrl: './provider-list.component.css'
})
export class ProviderListComponent implements OnInit {
  providers!: any;

  constructor(public providerService: ProviderService){}
  
  ngOnInit(): void {
    this.providers = this.providerService.get();
  }
}
