package br.com.atividade.sistemabancario.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false, updatable = false)
    private UUID id;

    @NotNull
    @DecimalMin(value = "0.00", message = "The balance cant'not be negative")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", unique = true, nullable = false)
    private User userOwner;
}
