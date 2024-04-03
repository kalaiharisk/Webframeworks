package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepo;

@Service
public class MenuService {
    @Autowired
    private  MenuRepo ur;
    public Menu create(Menu us)
    {
        return ur.save(us);
    }
    public Menu getMenuById(int id)
    {
        return ur.findById(id).orElse(null);
    }
    public List <Menu> getAlldetails()
    {
        return ur.findAll();
    }
    public boolean updateDetails(int id,Menu u)
    {
        if(this.getMenuById(id)==null)
        {
            return false;
        }
        try{
            ur.save(u);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public boolean deleteMenu(int id)
    {
        if(this.getMenuById(id) == null)
        {
            return false;
        }
        ur.deleteById(id);
        return true;
    }
    public List<Menu> getsort(int pageNumber,int pageSize,String field)
    {          
        return ur.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))).getContent();
    }
}
