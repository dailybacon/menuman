package com.beefyboys.menuman.models;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Menu {

    @NotEmpty
    List<Section> sections;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
