package my.coreapp.model;

import com.nt.neocloud4j.core.model.composite.Container;

import javax.persistence.Embeddable;
import java.io.Serializable;

import my.coreapp.model.ResourceAccess;
//IMPORT

@Embeddable
public class BadgeToResourceAccessLinkId implements Serializable {

    private Long roleAId;
    private Long roleBId;

    public BadgeToResourceAccessLinkId(){

    }

    public BadgeToResourceAccessLinkId(Badge roleA, ResourceAccess roleB, Container container) {
        setRoleAId(roleA.getOid());
        setRoleBId(roleB.getOid());
    }

    public Long getRoleAId() {
        return roleAId;
    }

    public void setRoleAId(Long roleAId) {
        this.roleAId = roleAId;
    }

    public Long getRoleBId() {
        return roleBId;
    }

    public void setRoleBId(Long roleBId) {
        this.roleBId = roleBId;
    }
}
