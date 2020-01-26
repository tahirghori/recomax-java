package com.ms.base.workspace.service;

import com.ms.base.beam.Event;
import com.ms.base.beam.Role;
import org.springframework.stereotype.Service;

@Service("eventService")
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService {
}
