package platform.codingnomads.co.springdata.lab_complete.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "points_of_interest")
public class PointOfInterest implements Serializable {

    private static final long serialVersionUID = -5832297257746489251L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "poi_generator")
    private Long id;

    private String type;
    private String name;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToMany
    @JoinTable(name="poi_route", joinColumns = @JoinColumn(name = "poi_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private List<Route> routes;

    public PointOfInterest(String type, String name, Area area) {
        this.type = type;
        this.name = name;
        this.area = area;
    }

    public PointOfInterest(String type, String name, Route route) {
        this.type = type;
        this.name = name;
        this.routes = List.of(route);
    }

    public void addRoutes(List<Route> routes) {
        if (this.routes == null) {
            this.routes = routes;
        } else {
            this.routes.addAll(routes);
        }
    }

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
