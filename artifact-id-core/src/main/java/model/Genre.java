package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre extends BasicEntity {

    @Column(name = "number", nullable = false)
    private Float number;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
    private Set<Book> books;

    @OneToOne(cascade = CascadeType.ALL)
    private Rating rating;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
