package com.kulturservice.service;

import com.kulturservice.Repository.RoleRepository;
import com.kulturservice.model.Review;
import com.kulturservice.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements IRoleService{
    private RoleRepository roleRepository;

    @Override
    public Set<Role> findAll() {
        Set<Role> set = new HashSet<>();
        roleRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return roleRepository.findById(aLong);
    }
}
