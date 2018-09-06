package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Thing;
import com.sherum.neo.writer.graphwriter.domain.Thing;
import com.sherum.neo.writer.graphwriter.repo.ThingRepo;
import com.sherum.neo.writer.graphwriter.repo.ThingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingService {

    @Autowired
    private ThingRepo thingRepo;
    
    public Thing get(Long id){
        return thingRepo.findById(id).get();
    }

    public List<Thing> getAll(){
        return (List<Thing>)thingRepo.findAll();
    }

    public Thing create(Thing thing){
        return thingRepo.save(thing);
    }

    public void update(Long id, Thing thing){
        Thing currentAction = get(id);
        currentAction = thing;
        thingRepo.save(currentAction);
    }

    public Thing save(Thing thing){
        return thingRepo.save(thing);
    }

    public void delete(Long id){
        Thing a = get(id);
        thingRepo.delete(a);
    }
}
