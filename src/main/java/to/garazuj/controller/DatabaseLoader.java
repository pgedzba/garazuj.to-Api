package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import to.garazuj.model.Role;
import to.garazuj.model.RoleName;
import to.garazuj.repository.RoleRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... strings) {
        for(RoleName role : RoleName.values() ) {
            if (!this.roleRepository.findByName(role).isPresent())
                this.roleRepository.save(new Role(role));
        }
    }
}