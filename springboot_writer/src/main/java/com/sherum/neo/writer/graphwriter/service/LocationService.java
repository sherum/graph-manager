package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Location;
import com.sherum.neo.writer.graphwriter.repo.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location get(Long id){
        return locationRepo.findById(id).get();
    }
    public List<Location> getAll(){
        return (List<Location>)locationRepo.findAll();
    }

    public Location create(Location action){
        return locationRepo.save(action);
    }

    public void update(Long id, Location location){
        Location currentLocation = get(id);
        currentLocation = location;
        locationRepo.save(currentLocation);
    }

    public Location save(Location action){
        return locationRepo.save(action);
    }

    public void delete(Long id){
        Location a = get(id);
        locationRepo.delete(a);
    }
}
