package edu.eci.ieti.proyecto.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany(mappedBy = "plantations", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plantation> plantations;

    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private Long numberOfHarvest;
    private Double harvestPercentage;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User(String name,
            String email,
            Long phoneNumber,
            Long numberOfHarvest,
            Double harvestPercentage) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.plantations = new ArrayList<Plantation>();
        this.numberOfHarvest = numberOfHarvest;
        this.harvestPercentage = harvestPercentage;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {
        return true;
    }
}
