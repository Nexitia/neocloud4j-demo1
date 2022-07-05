package my.coreapp.model;

import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.model.composite.ContainerInfoFactory;
import com.nt.neocloud4j.core.model.persistable.BusinessObject;

import java.util.*;

import javax.persistence.*;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import my.coreapp.model.Badge;
//IMPORT

@Entity
@Table(name = "MANY_TO_MANY_POINTER_BADGE_LINK")
@Cacheable(true)
@Access(AccessType.FIELD)
public class PointerToBadgeLink extends BusinessObject {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PointerToBadgeLinkId id;

    @ManyToOne
    @JoinColumn(name = "roleAid", insertable = false, updatable = false)
    private Pointer roleA;

    @ManyToOne
    @JoinColumn(name = "roleBid", insertable = false, updatable = false)
    private Badge roleB;

    private Date pointageDate;
    // ATTRIBUTES

    public PointerToBadgeLink(){
    }

    public PointerToBadgeLink(Pointer roleA, Badge roleB, Container container) {
        setRoleA(roleA);
        setRoleB(roleB);
        setContainerInfo(ContainerInfoFactory.createContainerInfo(container));
        id = new PointerToBadgeLinkId(roleA, roleB, container);
    }

    public Pointer getRoleA() {
        return roleA;
    }

    public Class<Pointer> getRoleADomainClass() {
        return Pointer.class;
    }

    public void setRoleA(final Pointer Pointer) {
        this.roleA = roleA;
    }

    public Badge getRoleB() {
        return roleB;
    }

    public Class<Badge> getRoleBDomainClass() {
        return Badge.class;
    }

    public void setRoleB(final Badge Badge) {
        this.roleB = roleB;
    }

    public Class getDomainClass() {
        return PointerToBadgeLink.class;
    }

    public PointerToBadgeLinkId getId() {
        return id;
    }

    public void setId(final PointerToBadgeLinkId id) {
        this.id = id;
    }

    // GETTERS AND SETTERS

    @Column(name = "pointagedate")
    public Date getPointageDate() {
        return pointageDate;
    }

    public void setPointageDate(Date pointageDate) {
        this.pointageDate = pointageDate;
    }


}

