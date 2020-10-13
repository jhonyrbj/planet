package com.planet.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

import static com.planet.api.constant.Field.*;
import static com.planet.api.constant.Table.TABLE_PLANET;

@Entity
@Table(name = TABLE_PLANET, uniqueConstraints = {@UniqueConstraint(columnNames = {FIELD_NAME})})
public class Planet implements Serializable {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("climate")
    @NotNull
    private String climate;

    @JsonProperty("terrain")
    @NotNull
    private String terrain;

    @JsonProperty("qttMovies")
    private Integer qttMovies;

    public Planet() {
        this.id = UUID.randomUUID().toString();
    }

    @Basic
    @Column(name = FIELD_ID, nullable = false, updatable = false, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = FIELD_NAME, nullable = false, updatable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = FIELD_CLIMATE, nullable = false)
    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @Basic
    @Column(name = FIELD_TERRAIN, nullable = false)
    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Basic
    @Column(name = FIELD_QTT_MOVIES, nullable = false)
    public Integer getQttMovies() {
        return qttMovies;
    }

    public void setQttMovies(Integer qttMovies) {
        this.qttMovies = qttMovies;
    }
}
