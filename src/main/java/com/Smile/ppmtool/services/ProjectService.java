package com.Smile.ppmtool.services;

import com.Smile.ppmtool.domain.Project;
import com.Smile.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        //lots of Logic here
        return projectRepository.save(project);
    }

}
