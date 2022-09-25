package com.kulturservice.Repository;

import com.kulturservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findAllByOrderByEventDateAsc();

    public List<Event> findAllByEventDateAfterOrderByEventDateAsc(Date date);
}
