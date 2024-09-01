package com.whale.shopquanao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIZES", schema = "dbo")
public class Size extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "size_name", nullable = false, length = 50)
    private String sizeName;

    @JsonIgnore
    @OneToMany(mappedBy = "size")
    private List<ProductDetail> listProductDetail;
}