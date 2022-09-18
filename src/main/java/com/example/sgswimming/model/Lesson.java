package com.example.sgswimming.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "swimmer_id"))
    private List<Swimmer> swimmers = new ArrayList<>();

    @ManyToMany(mappedBy = "swimmers", fetch = FetchType.EAGER)
    private Set<ClientData> clientDataSet = new HashSet<>();

    private String description;

    private LocalDateTime localDateTime;

    @Builder
    public Lesson(Long id, Instructor instructor, String description, LocalDateTime localDateTime) {
        this.id = id;
        this.instructor = instructor;
        this.description = description;
        this.localDateTime = localDateTime;
    }

    public void addSwimmer(Swimmer swimmer) {
        swimmers.add(swimmer);
    }

    public void addClientData(ClientData clientData) {
        this.clientDataSet.add(clientData);
    }
}
