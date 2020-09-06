package com.myhomemadeshop.api.user.domain;

import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role extends BaseEntity {

    public Role(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;

}
