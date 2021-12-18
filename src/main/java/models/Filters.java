package models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "filters")
@XmlAccessorType(XmlAccessType.FIELD)
public class Filters {
    @XmlElement(name = "filter")
    private List<Filter> filters;

    public Filters(){

    }

    public Filters(List<Filter> filters){
        this.filters = List.copyOf(filters);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = List.copyOf(filters);
    }
}
