package my.coreapp.model;

import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.model.composite.ContainerInfoFactory;
import com.nt.neocloud4j.core.model.persistable.BusinessObject;

import java.util.*;

import javax.persistence.*;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import my.coreapp.model.ResourceAccess;
//IMPORT

@Entity
@Table(name = "MANY_TO_MANY_BADGE_RESOURCEACCESS_LINK")
@Cacheable(true)
@Access(AccessType.FIELD)
public class BadgeToResourceAccessLink extends BusinessObject {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private BadgeToResourceAccessLinkId id;

    @ManyToOne
    @JoinColumn(name = "roleAid", insertable = false, updatable = false)
    private Badge roleA;

    @ManyToOne
    @JoinColumn(name = "roleBid", insertable = false, updatable = false)
    private ResourceAccess roleB;

    private Date fromDate;
    private Date toDate;
    // ATTRIBUTES

    public BadgeToResourceAccessLink(){
    }

    public BadgeToResourceAccessLink(Badge roleA, ResourceAccess roleB, Container container) {
        setRoleA(roleA);
        setRoleB(roleB);
        setContainerInfo(ContainerInfoFactory.createContainerInfo(container));
        id = new BadgeToResourceAccessLinkId(roleA, roleB, container);
    }

    public Badge getRoleA() {
        return roleA;
    }

    public Class<Badge> getRoleADomainClass() {
        return Badge.class;
    }

    public void setRoleA(final Badge Badge) {
        this.roleA = roleA;
    }

    public ResourceAccess getRoleB() {
        return roleB;
    }

    public Class<ResourceAccess> getRoleBDomainClass() {
        return ResourceAccess.class;
    }

    public void setRoleB(final ResourceAccess ResourceAccess) {
        this.roleB = roleB;
    }

    public Class getDomainClass() {
        return BadgeToResourceAccessLink.class;
    }

    public BadgeToResourceAccessLinkId getId() {
        return id;
    }

    public void setId(final BadgeToResourceAccessLinkId id) {
        this.id = id;
    }

    // GETTERS AND SETTERS

    @Column(name = "fromdate")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name = "todate")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }


}

