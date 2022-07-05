package my.coreapp.rest.dto;

import my.coreapp.model.Badge;
import my.coreapp.model.PointerToBadgeLink;
import java.util.*;

//IMPORT

public class PointerToBadgeLinkDTO {

    private Long roleAId;
    private Long roleBId;
    private String roleAFullId;
    private String roleBFullId;

    private Date pointageDate;
    // ATTRIBUTES


    public PointerToBadgeLinkDTO(){

    }

    public PointerToBadgeLinkDTO(final Badge roleB, final PointerToBadgeLink link){
        setPointageDate(link.getPointageDate());
        // UPDATE_ATTRIBUTES
    }

    public static Object from(final Badge roleB, final PointerToBadgeLink link) {
        PointerToBadgeLinkDTO dto = new PointerToBadgeLinkDTO(roleB, link);
        return dto;
    }

    public Long getRoleAId() {
        return roleAId;
    }

    public void setRoleAId(final Long roleAId) {
        this.roleAId = roleAId;
    }

    public Long getRoleBId() {
        return roleBId;
    }

    public void setRoleBId(final Long roleBId) {
        this.roleBId = roleBId;
    }

    public String getRoleAFullId() {
        return roleAFullId;
    }

    public void setRoleAFullId(final String roleAFullId) {
        this.roleAFullId = roleAFullId;
    }

    public String getRoleBFullId() {
        return roleBFullId;
    }

    public void setRoleBFullId(final String roleBFullId) {
        this.roleBFullId = roleBFullId;
    }

        public Date getPointageDate() {
        return pointageDate;
    }

    public void setPointageDate(Date pointageDate) {
        this.pointageDate = pointageDate;
    }

// GETTERS AND SETTERS
}
