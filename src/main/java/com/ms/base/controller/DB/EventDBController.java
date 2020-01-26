package com.ms.base.controller.DB;

import com.fasterxml.jackson.databind.JsonNode;
import com.ms.base.beam.Event;
import com.ms.base.throwable.validator.EventValidator;
import com.ms.base.workspace.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.ms.base.controller.route.EVENT_CONTROLLER;

@RestController
@RequestMapping(value = EVENT_CONTROLLER)
public class EventDBController extends BaseDBController<EventService, Event, EventValidator> {

    @Autowired
    EventValidator eventValidator;

    @Override
    Optional<Event> requestCast(JsonNode jsonNode) {
        return Optional.of(jackson.requestCast(Event.class, jsonNode));
    }

    @Override
    BindingResult applyValidator(String rule, Event item, BindingResult errors) {
        return eventValidator.applyValidate(rule, item, errors);
    }


}
