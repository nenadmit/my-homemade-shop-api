package com.myhomemadeshop.api.infrastructure.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GenericGenerator(
            name = "sequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @Parameter(name = "optimizer", value = "pooled"),
                    @Parameter(name = "initial_value", value = "100"),
                    @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;


}
