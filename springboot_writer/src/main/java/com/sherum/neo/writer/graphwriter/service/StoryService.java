package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Story;
import com.sherum.neo.writer.graphwriter.repo.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepo storyRepo;

    public Story get(Long id){
        return storyRepo.findById(id).get();
    }

    public List<Story> getAll(){
        return (List<Story>)storyRepo.findAll();
    }

    public Story create(Story story){
        return storyRepo.save(story);
    }

    public void update(Story story){
        storyRepo.save(story);
    }

    public Story save(Story story){
        return storyRepo.save(story);
    }

    public void delete(Long id){
        Story a = get(id);
        storyRepo.delete(a);
    }

    public void connectPlotpoint(Long id, Long otherId){
        storyRepo.addPlotpoint(id,otherId);
    }
}
