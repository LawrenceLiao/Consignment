package com.freightmate.consignment.models;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrier {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false)
    private Long id;

    @Column( name = "name", updatable = false, nullable = false)
    private String name;

    @Column( name = "prefix", nullable = false)
    private String prefix;

    @Column( name = "created_date_time", updatable = false, nullable = false )
    private OffsetDateTime createdDateTime;

    @Column( name = "updated_date_time", nullable = false )
    private OffsetDateTime updatedDateTime;

}
