package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating extends BasicEntity{

    @Column(name = "rating_enum", nullable = false)
    private RatingEnum ratingEnum;

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
