package com.sherum.neo.writer.graphwriter.service;

import com.sherum.neo.writer.graphwriter.domain.Plotpoint;
import com.sherum.neo.writer.graphwriter.domain.Plotpoint;
import com.sherum.neo.writer.graphwriter.repo.PlotpointRepo;
import com.sherum.neo.writer.graphwriter.repo.PlotpointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotpointService {
    @Autowired
    private PlotpointRepo plotPointRepo;

    public Plotpoint get(Long id){
        return plotPointRepo.findById(id).get();
    }

    public List<Plotpoint> getAll(){
        return (List<Plotpoint>)plotPointRepo.findAll();
    }

    public Plotpoint create(Plotpoint plotpoint){
        return plotPointRepo.save(plotpoint);
    }

    public void update(Long id, Plotpoint plotpoint){
        Plotpoint currentPlotpoint = get(id);
        currentPlotpoint = plotpoint;
        plotPointRepo.save(currentPlotpoint);
    }

    public Plotpoint save(Plotpoint plotpoint){
        return plotPointRepo.save(plotpoint);
    }

    public void delete(Long id){
        Plotpoint a = get(id);
        plotPointRepo.delete(a);
    }
}
