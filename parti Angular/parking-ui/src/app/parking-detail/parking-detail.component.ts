import { Component, OnInit } from '@angular/core';
import { ParkingInfo } from '../parkinginfo';
import { ActivatedRoute } from '@angular/router';
import { ParkingService } from '../parking.service';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  isLoaded: boolean = false;
  parking!: ParkingInfo;

  constructor(private route: ActivatedRoute, private parkingService: ParkingService) { }

  ngOnInit(): void {
    const parkingId = parseInt(<string>this.route.snapshot.paramMap.get('id'));
    this.parkingService.getParking(parkingId).subscribe(
      response => {
        this.parking = response;
        this.isLoaded = true;
        }
     );
    }

  }

