package com.beefyboys.menuman.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Section {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotEmpty
    @NotNull
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

}
