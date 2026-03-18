package br.com.fiap.techchallenge.auth.domain.model;


import br.com.fiap.techchallenge.auth.domain.enums.Role;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    // construtor vazio (obrigatório)
    public User() {
    }

    // construtor completo
    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}