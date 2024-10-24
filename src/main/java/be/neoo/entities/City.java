package be.neoo.entities;


import jakarta.persistence.*;
import java.util.Objects;


@NamedQueries( value = {
        @NamedQuery(name = "city.findAll" , query= "SELECT c FROM City c"),
})

@Entity
@Table(name = "cities")
public class City {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "city_name", nullable = false, length = 60)
    private String cityName;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @ManyToOne
    @JoinColumn( name = "country", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country country;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName( String cityName ) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode( String zipCode ) {
        this.zipCode = zipCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry( Country country ) {
        this.country = country;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        City city = (City) o;
        return id == city.id && Objects.equals( cityName, city.cityName ) && Objects.equals( zipCode,
                city.zipCode ) && Objects.equals( country, city.country );
    }

    @Override public int hashCode() {
        return Objects.hash( id, cityName, zipCode, country );
    }
}
