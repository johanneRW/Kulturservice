package com.kulturservice.service;

import com.kulturservice.Repository.BandRepository;
import com.kulturservice.model.Band;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BandService implements IBandService {

    private BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public Set<Band> findAll() {
        Set<Band> set = new HashSet<>();
        bandRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Band save(Band band) {
        bandRepository.save(band);
        return band;
    }

    @Override
    public void delete(Band band) {
        bandRepository.delete(band);

    }

    @Override
    public void deleteById(Long aLong) {
        bandRepository.deleteById(aLong);

    }

    @Override
    public Optional<Band> findById(Long aLong) {
        return bandRepository.findById(aLong);
    }
}
