package com.Smile.ppmtool.services;

import com.Smile.ppmtool.domain.Project;
import com.Smile.ppmtool.exceptions.ProjectIdException;
import com.Smile.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        //lots of Logic here

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception ex){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+ "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        //return projectRepository.findByProjectIdentifier(projectId.toUpperCase());//转换成uppercase应该放在service

        //能找到就返回，没找到的时候状态完成但是没返回数据，用户体验不好，所以进行没有找到数据时处理
        Project project= projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null ){
            throw new ProjectIdException("Project ID '"+ projectId + "' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }
}
