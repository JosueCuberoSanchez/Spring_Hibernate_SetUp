package model;

import javax.persistence.*;

@Entity
@Table(name = "copy")
public class Copy extends BasicEntity{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

}
