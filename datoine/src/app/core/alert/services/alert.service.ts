import { Injectable, signal } from '@angular/core';
import { Alert } from '../models/alert.model';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  alertMessage = signal<Alert | null>(null);
  constructor() { 
  }
}
