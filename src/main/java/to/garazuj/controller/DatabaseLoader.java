package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import to.garazuj.model.Role;
import to.garazuj.model.RoleName;
import to.garazuj.repository.RoleRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RoleRepository repository;

    @Autowired
    public DatabaseLoader(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        if(!this.repository.findByName(RoleName.ROLE_ADMIN).isPresent())
            this.repository.save(new Role(RoleName.ROLE_ADMIN));
        if(!this.repository.findByName(RoleName.ROLE_USER).isPresent())
            this.repository.save(new Role(RoleName.ROLE_USER));
    }
}