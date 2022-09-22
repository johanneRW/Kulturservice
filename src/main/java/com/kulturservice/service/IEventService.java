package com.kulturservice.service;

import com.kulturservice.model.Event;

import java.util.List;

public interface IEventService extends ICrudService<Event, Long> {
    public List<Event> findAllByOrderByEventDateAsc();
}
