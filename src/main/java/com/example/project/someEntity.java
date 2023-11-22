package com.example.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "some_entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class someEntity {
    @Id
    private String id;

}
