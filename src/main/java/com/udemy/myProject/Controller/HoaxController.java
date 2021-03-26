package com.udemy.myProject.Controller;

import com.udemy.myProject.Managers.HoaxServiceManager;


import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Shared.CurrentUser;
import com.udemy.myProject.Shared.GenericResponse;
import com.udemy.myProject.vm.HoaxVM;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/1.0")
public class HoaxController {
    @Autowired
    HoaxServiceManager manager;

    @GetMapping("/hoaxes")
    public Page<HoaxVM> getHoaxes(@PageableDefault(sort = "id" , direction = Sort.Direction.DESC) Pageable page){

        return  manager.getHoaxes(page).map(HoaxVM::new);
    }



    @PostMapping("/hoaxes")
    GenericResponse saveHoax(@Valid @RequestBody Hoax hoax, @CurrentUser User user){
        manager.save(hoax,user);
        return new GenericResponse("Hoax is saved");
    }

    @GetMapping("/users/{username}/hoaxes")
    public Page<HoaxVM> getUserHoaxes(@PathVariable String username,@PageableDefault(sort = "id" , direction = Sort.Direction.DESC) Pageable page){
        return manager.getHoaxesOfUser(username,page).map(HoaxVM::new);
    }
    @GetMapping({"/hoaxes/{id:[0-9]+}","/users/{username}/hoaxes/{id:[0-9]+}" })
    public ResponseEntity<?> getHoaxesRelative(@PageableDefault(sort = "id" , direction = Sort.Direction.DESC) Pageable page,
                                               @PathVariable long id,
                                               @PathVariable(required = false) String username,
                                               @RequestParam(name = "count",required = false ,defaultValue = "false") boolean count,
                                               @RequestParam(name = "direction", defaultValue = "before") String direction){
        if(count){
            long newHoaxCount =  manager.getNewHoaxesCount(id,username);
            Map<String, Long> response = new HashMap<>();
            response.put("count",newHoaxCount);
            return ResponseEntity.ok(response);
        }
        if(direction.equals("after")){
            List<HoaxVM> newHoaxes = manager.getNewHoaxes(id,username, page.getSort()).stream().map(HoaxVM::new).collect(Collectors.toList());
            return ResponseEntity.ok(newHoaxes);
        }
        return  ResponseEntity.ok().body(manager.getOldHoaxes(id, username, page).map(HoaxVM::new));
    }




}
