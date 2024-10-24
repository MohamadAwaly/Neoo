package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "document_type" )
public class DocumentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "type", nullable = false, length = 60 )
    private String type;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        DocumentType that = (DocumentType) o;
        return id == that.id && Objects.equals( type, that.type );
    }

    @Override public int hashCode() {
        return Objects.hash( id, type );
    }
}
