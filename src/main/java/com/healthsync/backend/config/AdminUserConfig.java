package com.healthsync.backend.config;

import com.healthsync.backend.models.Role;
import com.healthsync.backend.models.Funcionario;
import com.healthsync.backend.repositories.FuncionarioRepository;
import com.healthsync.backend.repositories.PacienteRepository;
import com.healthsync.backend.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private FuncionarioRepository funcionarioRepository;

    public AdminUserConfig(RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           FuncionarioRepository funcionarioRepository, PacienteRepository pacienteRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = funcionarioRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                funcionario -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var funcionario = new Funcionario();
                    funcionario.setNome("admin");
                    funcionario.setPassword(passwordEncoder.encode("123"));
                    funcionario.setRoles(Set.of(roleAdmin));
                    funcionarioRepository.save(funcionario);
                }
        );
    }
}
