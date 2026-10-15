package com.eip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table( name = "part" )
public class Part
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "part_number", nullable = false, unique = true )
    private String partNumber;

    @Column( nullable = false )
    private String name;

    private String description;

    @Column( name = "weight_grams" )
    private BigDecimal weightGrams;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false )
    private PartStatus status;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
}
