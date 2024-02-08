import { Component } from '@angular/core';
import { AdministrarServicesService } from '../../services/administrar-services.service';
import { Chart } from 'chart.js';
import { Provider } from '../../models/provider';
import { ProviderService } from '../../services/provider.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  amountArticles: number = 0; amountProviders: number = 0; amountPurchaseOrders: number = 0;
  providersDeleted: number = 0; articlesDeleted: number = 0; purchaseOrderDeleted: number = 0;
  avgDelete: number = 0; provider!: Provider;

  constructor(private adminService: AdministrarServicesService, private providerService: ProviderService) { }

  ngOnInit(): void {    
    this.adminService.get('article').subscribe(data => {
      this.articlesDeleted = data.filter((aux:any) => aux.isDeleted == true).length;
      this.amountArticles = data.length;
      this.adminService.get('provider').subscribe(data => {
        this.providersDeleted = data.filter((aux:any) => aux.isDeleted == true).length;
        this.amountProviders = data.length;
        this.adminService.get('purchase-order').subscribe(data => {
          this.purchaseOrderDeleted = data.filter((aux:any) => aux.state == 'C').length;
          this.amountPurchaseOrders = data.length;
          this.providerService.getWithMostOrders().subscribe((data: Provider) => {
            this.provider = data;
            this.avgDelete = ((this.articlesDeleted + this.purchaseOrderDeleted + this.providersDeleted) * 100)/
                              (this.amountArticles + this.amountPurchaseOrders + this.amountProviders)  
          });
        });
      });
    });  
  }
}