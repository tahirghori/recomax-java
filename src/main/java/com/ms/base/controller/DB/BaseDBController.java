package com.ms.base.controller.DB;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.base.application.Component.UtilEntity;
import com.ms.base.application.Component.UtilJackson;
import com.ms.base.beam.BaseModel;
import com.ms.base.throwable.ResponseBuilder;
import com.ms.base.throwable.validator.BaseValidator;
import com.ms.base.workspace.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Validated
public abstract class BaseDBController<S extends BaseService<T>, T extends BaseModel, V extends BaseValidator> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    S service;

    @Autowired
    UtilJackson<T> jackson;

    @Autowired
    UtilEntity<T> entity;

    @Autowired
    ResponseBuilder<T> responseBuilder;

    abstract Optional<T> requestCast(JsonNode item);

    abstract BindingResult applyValidator(String rule, T item, BindingResult errors);

    /**
     * Non Validate Request Endpoints
     */

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity getItems(Pageable pageable) {
        return responseBuilder.buildResponse(HttpStatus.OK, service.findAllActive(pageable));
    }

    @GetMapping("/pageable")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity getItemsPageable(Pageable pageable) {
        return responseBuilder.buildResponse(HttpStatus.OK, service.findAll());
    }

    @PostMapping("/insertBulk")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity insertBulk(@RequestBody Iterable<T> list) {
        return responseBuilder.buildResponse(HttpStatus.CREATED, service.saveAll(list));
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity findById(@PathVariable("uuid") UUID id) {
        return responseBuilder.buildResponse(HttpStatus.CREATED, service.findById(id).orElseThrow(() -> responseBuilder.setDetails("Resource Not Found :" + id).buildThrow(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity delete(@PathVariable UUID id) {
        return responseBuilder
                .buildResponse(HttpStatus.OK, service.update(
                        service.findById(id)
                                .map(x -> {
                                    x.setDeleted(true);
                                    return service.update(x);
                                })
                                .orElseThrow(() -> responseBuilder
                                        .setDetails("Resource Not Found :" + id)
                                        .buildThrow(HttpStatus.NOT_FOUND))
                ), "Record Deleted  Successfully!");
    }

    @DeleteMapping("/permanent/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity deletePermanent(@PathVariable UUID id) {
        return responseBuilder
                .buildResponse(HttpStatus.OK, service.update(
                        service.findById(id)
                                .map(x -> {
                                    x.setDeleted(true);
                                    return service.delete(x.getId());
                                })
                                .orElseThrow(() -> responseBuilder
                                        .setDetails("Resource Not Found :" + id)
                                        .buildThrow(HttpStatus.NOT_FOUND))
                ), "Record Deleted  Successfully!");
    }


    /**
     * Insert
     */

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity insert(@RequestBody JsonNode item) {
        return responseBuilder.buildResponse(HttpStatus.CREATED,
                service.insert(
                        requestCast(item)
                                .orElseThrow(() -> responseBuilder
                                        .setDetails("Request Parse Exception")
                                        .buildThrow(HttpStatus.BAD_REQUEST))
                ));
    }


    /**
     * Update
     */
    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity update(@RequestBody JsonNode jsonItem) {
        return responseBuilder
                .buildResponse(HttpStatus.OK, requestCast(jsonItem)
                        .map(itemNew -> {
                            return service.findById(itemNew.getId())
                                    .map(x -> {
                                        // then use Spring BeanUtils to copy and ignore null
                                        BeanUtils.copyProperties(itemNew, x, entity.getNullPropertyNames(itemNew));
                                        return service.update(x);
                                    })
                                    .orElseThrow(() -> responseBuilder.setDetails("Resource Not Found :" + itemNew.getId()).buildThrow(HttpStatus.NOT_FOUND));

                        }).orElseThrow(() -> responseBuilder.setDetails("Request Parse Exception").buildThrow(HttpStatus.BAD_REQUEST))
                );
    }


    /**
     * Save or Update with validate Request
     */

    @PutMapping("/saveOrUpdate")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity saveOrUpdate(@RequestBody JsonNode jsonItem) {
        return responseBuilder
                .buildResponse(HttpStatus.OK, requestCast(jsonItem)
                        .map(itemNew -> {
                            return service.findById(itemNew.getId())
                                    .map(x -> {
                                        // then use Spring BeanUtils to copy and ignore null
                                        BeanUtils.copyProperties(itemNew, x, entity.getNullPropertyNames(itemNew));
                                        return service.update(x);
                                    })
                                    .orElseGet(() -> service.insert(itemNew));
                        }).orElseThrow(() -> responseBuilder.setDetails("Request Parse Exception").buildThrow(HttpStatus.BAD_REQUEST)));
    }


    /**
     * Update Validate Request
     */
    @PutMapping("/validate/{action}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity updateValidate(@RequestBody JsonNode jsonItem, @PathVariable("action") String action, BindingResult errors) {

        return responseBuilder
                .buildResponse(HttpStatus.OK, requestCast(jsonItem).map(itemNew -> {
                            responseBuilder.buildvalidate(
                                    applyValidator(action, itemNew, errors)
                            );

                            return service.findById(itemNew.getId())
                                    .map(x -> {
                                        // then use Spring BeanUtils to copy and ignore null
                                        BeanUtils.copyProperties(itemNew, x, entity.getNullPropertyNames(itemNew));
                                        return service.update(x);
                                    })
                                    .orElseThrow(() -> responseBuilder.setDetails("Resource Not Found :" + itemNew.getId()).buildThrow(HttpStatus.NOT_FOUND));

                        }).orElseThrow(() -> responseBuilder.setDetails("Request Parse Exception").buildThrow(HttpStatus.BAD_REQUEST))
                );

    }


    /**
     * Create Item with Validate Request
     */

    @PostMapping(value = "/validate/{action}")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity insertValidate(@RequestBody JsonNode item, @PathVariable("action") String action, BindingResult errors) {
        return responseBuilder.buildResponse(HttpStatus.CREATED, service.insert(requestCast(item).map(x -> {
                    responseBuilder.buildvalidate(
                            applyValidator(action, x, errors)
                    );
                    return x;
                }).orElseThrow(() -> responseBuilder.setDetails("Request Parse Exception").buildThrow(HttpStatus.BAD_REQUEST))
        ));
    }


    /**
     * Save or Update with validate Request
     */

    @PutMapping("/validate/saveOrUpdate/{action}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity saveOrUpdateValidate(@RequestBody JsonNode jsonItem, @PathVariable("action") String action, BindingResult errors) {
        return responseBuilder
                .buildResponse(HttpStatus.OK, requestCast(jsonItem)
                        .map(itemNew -> {
                            responseBuilder.buildvalidate(
                                    applyValidator(action, itemNew, errors)
                            );
                            return service.findById(itemNew.getId())
                                    .map(x -> {
                                        // then use Spring BeanUtils to copy and ignore null
                                        BeanUtils.copyProperties(itemNew, x, entity.getNullPropertyNames(itemNew));
                                        return service.update(x);
                                    })
                                    .orElseGet(() -> service.insert(itemNew));
                        }).orElseThrow(() -> responseBuilder.setDetails("Request Parse Exception").buildThrow(HttpStatus.BAD_REQUEST)));
    }

}
