package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Action;
import com.sherum.neo.writer.graphwriter.repo.ActionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActionService {
    @Autowired
    private ActionRepo actionRepo;

    public Action get(Long id){
        return actionRepo.findById(id).get();
    }

    public List<Action>getAll(){
        return (List<Action>)actionRepo.findAll();
    }

    public Action create(Action action){
        return actionRepo.save(action);
    }

    public void update(Long id, Action action){
        Action currentAction = actionRepo.findById(id).get();
        currentAction = action;
        actionRepo.save(currentAction);
    }

    public Action save(Action action){
        return actionRepo.save(action);
    }

    public void delete(Long id){
        Action a = get(id);
        actionRepo.delete(a);
    }
}
