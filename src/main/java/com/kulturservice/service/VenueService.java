package com.kulturservice.service;

import com.kulturservice.Repository.VenueRepository;
import com.kulturservice.model.Venue;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class VenueService implements IVenueService {

    private VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Set<Venue> findAll() {
        Set<Venue> set = new HashSet<>();
        venueRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    @Override
    public void delete(Venue venue) {
        venueRepository.delete(venue);

    }

    @Override
    public void deleteById(Long aLong) {
        venueRepository.deleteById(aLong);
    }

    @Override
    public Optional<Venue> findById(Long aLong) {
        return venueRepository.findById(aLong);
    }
}
