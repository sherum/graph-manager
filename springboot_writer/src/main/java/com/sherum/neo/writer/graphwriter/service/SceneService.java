package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Scene;
import com.sherum.neo.writer.graphwriter.repo.SceneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneService {

    @Autowired
    private SceneRepo sceneRepo;

    public Scene get(Long id){
        return sceneRepo.findById(id).get();
    }

    public List<Scene> getAll(Long id){
        return (List<Scene>)sceneRepo.findScenesInStory(id);
    }

    public Scene create(Scene scene){
        return sceneRepo.save(scene);
    }

    public void update(Long id, Scene scene){
        Scene currentScene = get(id);
        currentScene = scene;
        sceneRepo.save(currentScene);
    }

    public Scene save(Scene scene){
        return sceneRepo.save(scene);
    }

    public void delete(Long id){
        Scene a = get(id);
        sceneRepo.delete(a);
    }
}
