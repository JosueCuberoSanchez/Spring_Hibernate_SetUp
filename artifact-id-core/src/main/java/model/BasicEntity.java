package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.proxy.HibernateProxyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BasicEntity implements Serializable, Cloneable, Comparable {

    /**
     * Logger object to use in model classes.
     */
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * UUID identifier formatted as a String, used as primary key.
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid", parameters = {@Parameter(name = "separator", value = "-")})
    protected String id;

    /**
     * Timestamp that marks the moment when the entity got created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "entity_creation_timestamp", nullable = false, updatable = false)
    protected Date entityCreationTimestamp;

    /**
     * Timestamp that marks the moment of the last update made to the entity.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_timestamp", nullable = false)
    private Date lastUpdatedTimestamp;

    /**
     * Current version (incremental value) of the entity, used for optimistic.
     * locking
     */
    @Column(name = "entity_version", nullable = false)
    private long entityVersion;

    public Date getEntityCreationTimestamp() {
        return entityCreationTimestamp;
    }

    public void setEntityCreationTimestamp(Date entityCreationTimestamp) {
        this.entityCreationTimestamp = entityCreationTimestamp;
    }

    public long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(long entityVersion) {
        this.entityVersion = entityVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    /**
     * Basic wrapper for the {@link BasicEntity#onEquals(Object)} method that
     * must be implemented by all <tt>BasicEntity</tt> subclasses.
     *
     * @param o the reference object with which to compare.
     * @return <tt>true</tt> if this object is considered to be equal to the
     * argument; <tt>false</tt> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BasicEntity) {
            BasicEntity e = (BasicEntity) o;
            String thisId = getId();
            String otherId = e.getId();
            Class otherClass = HibernateProxyHelper.getClassWithoutInitializingProxy(o);
            if (thisId != null && otherId != null) {
                return thisId.equals(otherId) && getClass().equals(otherClass);
            } else {
                return onEquals(e);
            }
        }

        return false;
    }

    /**
     * Equality testing method for all persistent classes.
     * <p/>
     * This method's implementation must be designed and coded carefully.
     * Since we are using Hibernate 3 as a persistence framework and entity
     * identifiers are being generated by the DBMS, we cannot rely on Hibernate
     * object identifiers (i.e., the <tt>BasicEntity.id</tt> field) for the
     * <tt>equals()</tt> and <tt>hashCode()</tt> methods since it may not
     * always have been set before these methods are called. Instead, the
     * <tt>equals()</tt> and <tt>hashCode()</tt> methods must be based on a
     * business key. The business key property value(s) must not change during
     * the scope of a Hibernate session or while the persistent object belongs
     * to a java.util.Collection (such as java.util.HashMap or java.util.HashSet).
     * <p/>
     * Additionally, the <tt>onEquals()</tt> and <tt>onHashCode()</tt>
     * implementations must respect the <tt>equals()</tt> and <tt>hashCode()</tt>
     * contract; please refer to the corresponding documentation for
     * {@link Object#equals(Object)} and {@link Object#hashCode()}.
     *
     * @param o the reference object with which to compare.
     * @return <tt>true</tt> if this object is considered to be equal to the
     * argument; <tt>false</tt> otherwise.
     * @see BasicEntity#equals(Object)
     * @see Object#equals(Object)
     * @see Object#hashCode()
     */
    protected abstract boolean onEquals(Object o);

    /**
     * Basic wrapper for the {@link BasicEntity#onHashCode(int)} method that
     * must be implemented by all <tt>BasicEntity</tt> subclasses.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 6;
        return onHashCode(result);
    }

    /**
     * Hashcode method for all persistent classes.
     * <p/>
     * The properties that a subclass may use for calculating a hashcode must
     * be the same properties that are used to implement the
     * {@link BasicEntity#onEquals(Object)} method.
     *
     * @param result the result of the hashCode function from the parent class.
     * @return a hash code value for this object.
     * @see BasicEntity#onEquals(Object)
     * @see Object#hashCode()
     */
    protected abstract int onHashCode(int result);

    @Override
    @SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException"})
    protected Object clone() throws CloneNotSupportedException {
        try {
            BasicEntity cloneEntity = (BasicEntity) super.clone();
            cloneEntity.setId(null);
            cloneEntity.setEntityCreationTimestamp(null);
            cloneEntity.setEntityVersion(-1);
            cloneEntity.setLastUpdatedTimestamp(null);
            return cloneEntity;
        } catch (CloneNotSupportedException e) {
            // shouldn't ever happen since this class is Cloneable and
            // a direct subclass of Object
            throw new InternalError("Unable to clone object of type [" + getClass().getName() + "]");
        }
    }

    /**
     * Returns a StringBuilder representing the toString function of the class
     * implementation. This should be overridden by all children classes using
     * <tt>super.toStringBuilder()</tt> as the base, adding properties to the
     * returned StringBuilder.
     *
     * @return a <tt>StringBuilder</tt> representing the <tt>toString</tt>
     * value of this object.
     */
    protected StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("class name = ").append(getClass().getName()).append(", id = ").append(id);
        return sb;
    }

    /**
     * Returns toStringBuilder().toString(). Declared as 'final' to require
     * subclasses to override the {@link #toStringBuilder()} method, a cleaner
     * and better performing mechanism for toString();
     *
     * @return toStringBuilder().toString()
     */
    public final String toString() {
        StringBuilder stringBuilder = toStringBuilder();
        if (stringBuilder != null)
            return toStringBuilder().insert(0, '[').append(']').toString();

        return "";
    }

    @Override
    public int compareTo(Object o) {
        return this.getEntityCreationTimestamp().compareTo(((BasicEntity) o).getEntityCreationTimestamp());
    }

}