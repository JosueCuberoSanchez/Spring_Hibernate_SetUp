package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "book")
public class Book extends BasicEntity {

    @Column(name = "published", nullable = false)
    private Date published;

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "is_old", nullable = false)
    private boolean isOld;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="book")
    private Set<Copy> copies;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> genres;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
