package com.example.sgswimming.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "swimmer-read-dto",
        attributeNodes = {
                @NamedAttributeNode(value = "lessons", subgraph = "lessons-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "lessons-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("instructor"),
                                @NamedAttributeNode("swimmers")
                        })
        })

@Data
@NoArgsConstructor
@ToString(exclude = {"lessons", "clientDataSet"})
@EqualsAndHashCode(exclude = {"lessons", "clientDataSet"})
@Entity
public class Swimmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "swimmers", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany(mappedBy = "swimmers", fetch = FetchType.EAGER)
    private Set<ClientData> clientDataSet = new HashSet<>();

    @Builder
    public Swimmer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addClientData(ClientData clientData) {
        clientDataSet.add(clientData);
    }

    public void removeClientData(ClientData clientData) {
        clientDataSet.remove(clientData);
    }
}
