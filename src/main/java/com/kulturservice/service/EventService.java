package com.kulturservice.service;

import com.kulturservice.Repository.EventRepository;
import com.kulturservice.model.Event;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService implements IEventService {

    private EventRepository eventRepository;

    public EventService(EventRepository repository) {
        this.eventRepository = repository;
    }

    @Override
    public Set<Event> findAll() {
        Set<Event> set = new HashSet<>();
        eventRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public void deleteById(Long aLong) {
        eventRepository.deleteById(aLong);
    }

    @Override
    public Optional<Event> findById(Long aLong) {
        return eventRepository.findById(aLong);
    }

    @Override
    public List<Event> findAllByOrderByEventDateAsc() {
        return eventRepository.findAllByOrderByEventDateAsc();
    }
}