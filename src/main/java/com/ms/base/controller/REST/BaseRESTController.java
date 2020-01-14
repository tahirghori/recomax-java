package com.ms.base.controller.REST;

import com.ms.base.beam.Base;
import com.ms.base.workspace.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public class BaseRESTController<S extends BaseService<T>, T extends Base> {

    @Autowired
    S service;

    @GetMapping
    Page<T> getAllActive(Pageable pageable){
        return service.findAllActive(pageable);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<T> getAllPageable(Pageable pageable) {
        return service.findAll();
    }

//    @GetMapping
//    public List<T> getAll() {
//        return service.getAll();
//    }

    //    @Secured({"GRUSER", "ROLE_GRUSER"})
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public T insert(@Valid @RequestBody T item) {
        return service.insert(item);
    }

    @PostMapping("/saveall")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<T> saveAll(@Valid @RequestBody Iterable<T> list) {
        return service.saveAll(list);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public T delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public T update(@RequestBody T item) {
        return service.update(item);
    }


    @GetMapping("/search/{name}")
    @ResponseBody
    @ResponseStatus(value= HttpStatus.OK)
    public T findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    @GetMapping("/find/{keyword}")
    public List<T> findByNameContainingIgnoreCase(@PathVariable String keyword){
        return  service.findByNameContainingIgnoreCase(keyword);
    }



}
