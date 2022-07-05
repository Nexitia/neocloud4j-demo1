package my.coreapp.model;

import com.nt.neocloud4j.core.model.composite.Container;

import javax.persistence.Embeddable;
import java.io.Serializable;

import my.coreapp.model.Badge;
//IMPORT

@Embeddable
public class PointerToBadgeLinkId implements Serializable {

    private Long roleAId;
    private Long roleBId;

    public PointerToBadgeLinkId(){

    }

    public PointerToBadgeLinkId(Pointer roleA, Badge roleB, Container container) {
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
