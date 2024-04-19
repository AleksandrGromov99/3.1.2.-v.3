package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public Role findById(long id) {
        return roleRepository.getById((long) id);
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
