package com.appparking.parking.services.impl;

import com.appparking.parking.dao.ParkingAPIDAO;
import com.appparking.parking.dao.entity.RecordEnity;
import com.appparking.parking.dao.entity.ReponseParkingEntity;
import com.appparking.parking.models.Parking;
import com.appparking.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl  implements ParkingService {

    @Autowired
    public ParkingAPIDAO parkingAPIDAO;

    @Override
    public List<Parking> getListeParkings() {
      ReponseParkingEntity reponse = parkingAPIDAO.getListeParkings();
        return transformEntityToModel(reponse);
    }

    private List<Parking> transformEntityToModel(ReponseParkingEntity reponse) {
        List<Parking> resultat = new ArrayList<Parking>();
        for (RecordEnity record : reponse.getRecords()) {
            Parking parking = new Parking();
            parking.setIdentifiant(Integer.parseInt(record.getFields().getIdObj()));
            parking.setNom(record.getFields().getGrpNom());
            parking.setStatut(getLibelleStatut(record));
            parking.setNbPlacesDispo(record.getFields().getGrpDisponible());
            parking.setNbPlacesTotal(record.getFields().getGrpDisponible());
            parking.setHeureMaj(getHeureMaj(record));

            resultat.add(parking);
        }
        return resultat;
    }

    //méthode pour convertir et afficher l'heure
    private String getHeureMaj(RecordEnity record) {
        OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
        OffsetDateTime dateWithOffsetPlus2 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+02:00"));//+2  car heure Paris

        return dateWithOffsetPlus2.getHour() + "h" + dateWithOffsetPlus2.getMinute();
    }

    private String getLibelleStatut(RecordEnity record) {
        switch (record.getFields().getGrpstatut()) {
            case "1": {
                return  "FERME";
            }

            case "2": {
                return  "ABONNES";
            }

            case "5": {
                return  "OUVERT";
            }
        }

        return "Donnnées non dispoible";
    }
}
