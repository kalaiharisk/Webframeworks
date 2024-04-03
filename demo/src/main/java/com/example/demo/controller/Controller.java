package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
@RestController
public class Controller {
    @Autowired
MenuService us;
@PostMapping("/adddata")
public ResponseEntity<Menu>add(@RequestBody Menu u){
    Menu newmenu = us.create(u);
   return new ResponseEntity<>(newmenu,HttpStatus.CREATED); 
}
@GetMapping("/showdata")
public ResponseEntity <List<Menu>>showinfo(){
    List<Menu>obj = us.getAlldetails();
    return new ResponseEntity<>(obj,HttpStatus.OK);
}
@PutMapping("/api/menu/{menuId}")
    public ResponseEntity<Menu> putMethodName(@PathVariable("menuId") int id, @RequestBody Menu employee) {
        if(us.updateDetails(id,employee) == true)
        {
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/menu/{menuId}")
    public ResponseEntity<Boolean> delete(@PathVariable("menuId") int id)
    {
        if(us.deleteMenu(id) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
    
@GetMapping("/api/menu/{offset}/{pagesize}/{field}")
public List<Menu> getsorting(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
{
    return us.getsort(offset,pagesize,field);
}
}