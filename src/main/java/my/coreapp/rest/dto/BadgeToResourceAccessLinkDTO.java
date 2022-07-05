package my.coreapp.rest.dto;

import my.coreapp.model.ResourceAccess;
import my.coreapp.model.BadgeToResourceAccessLink;
import java.util.*;

//IMPORT

public class BadgeToResourceAccessLinkDTO {

    private Long roleAId;
    private Long roleBId;
    private String roleAFullId;
    private String roleBFullId;

    private Date fromDate;
    private Date toDate;
    // ATTRIBUTES


    public BadgeToResourceAccessLinkDTO(){

    }

    public BadgeToResourceAccessLinkDTO(final ResourceAccess roleB, final BadgeToResourceAccessLink link){
        setFromDate(link.getFromDate());
        setToDate(link.getToDate());
        // UPDATE_ATTRIBUTES
    }

    public static Object from(final ResourceAccess roleB, final BadgeToResourceAccessLink link) {
        BadgeToResourceAccessLinkDTO dto = new BadgeToResourceAccessLinkDTO(roleB, link);
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

        public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

// GETTERS AND SETTERS
}
