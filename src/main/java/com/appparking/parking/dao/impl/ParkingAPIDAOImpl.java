package com.appparking.parking.dao.impl;

import com.appparking.parking.dao.ParkingAPIDAO;
import com.appparking.parking.dao.entity.ReponseParkingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ParkingAPIDAOImpl implements ParkingAPIDAO {

    private static final String URL_API_OPEN_DATA = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&rows=-1&facet=grp_nom&facet=grp_statut";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ReponseParkingEntity getListeParkings() {
        return restTemplate.getForEntity(URL_API_OPEN_DATA, ReponseParkingEntity.class).getBody();
    }
}
