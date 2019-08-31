package com.Smile.ppmtool.web;

import com.Smile.ppmtool.domain.Project;
import com.Smile.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        //加了@Valid注解之后，validation不在出现500（HTTP-Internal Server Error）的错误，而出现400（bad request），much better
        //BindingResult是一个用来得到错误信息接口，

        if(result.hasErrors()){
            //将Project 换成? 才能返回字符串
            //return new ResponseEntity<String>("Invalid project object",HttpStatus.BAD_REQUEST);

            //返回FieldErrors List
            //return new ResponseEntity<List<FieldError>>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);

            //获取具体的error field with message details eg. {"field":"error message","field":"error message"}
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);

        }

        Project newProject=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

}
