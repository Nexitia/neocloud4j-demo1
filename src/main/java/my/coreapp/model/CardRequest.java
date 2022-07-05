package my.coreapp.model;

import com.nt.neocloud4j.core.model.persistable.BusinessObject;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttribute;
import com.nt.neocloud4j.core.utils.UIFieldEditor;
import com.nt.neocloud4j.core.utils.common.TransferIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.*;
import java.io.Serializable;

import java.lang.String;
import com.nt.neocloud4j.core.model.typed.ITypeManaged;
import com.nt.neocloud4j.core.model.typed.TypeInfo;
import com.nt.neocloud4j.core.model.typed.TypeManaged;

import com.nt.neocloud4j.core.model.lifecyclemanaged.ILifecycleManaged;
import com.nt.neocloud4j.core.model.lifecyclemanaged.LifecycleInfo;
import my.coreapp.model.Badge;
// IMPORT


@javax.persistence.Entity
@Table(name = "gen_cardrequest")
@Access(AccessType.PROPERTY)
@TypeManaged(rootType = "com.nt.neocloud4j.online.types.CardRequestType")
// ANNOTATIONS
public class CardRequest extends BusinessObject implements Serializable , ITypeManaged, ILifecycleManaged{// KNOER

    private static final long serialVersionUID = 1L;

    private String description;
    private Date requestExpirationDate;
    	private TypeInfo typeInfo;
private LifecycleInfo lifecycleInfo;
	private Badge badge;
// ATTRIBUTES


    @Override
    public void updateFrom(Persistable entity) {
        super.updateFrom(entity);
        setDescription(((CardRequest)entity).getDescription());
        setDescription(((CardRequest)entity).getDescription());
        setRequestExpirationDate(((CardRequest)entity).getRequestExpirationDate());
        // UPDATE_ATTRIBUTES
    }

    @Override
    @Transient
    public Class<?> getDomainClass() {
        return CardRequest.class;
    }


    @Id
    @Override
    @SequenceGenerator(name="gen_cardrequest_oid_seq", sequenceName="gen_cardrequest_oid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_cardrequest_oid_seq")
    @Column(columnDefinition = "serial", updatable = false)
    public Long getOid() {
        return super._getOid();
    }

    
	@Embedded
    @Override
    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    @Override
    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

	@Embedded
    @Override
    public LifecycleInfo getLifecycleInfo() {
        return lifecycleInfo;
    }

    @Override
    public void setLifecycleInfo(LifecycleInfo lifecycleInfo) {
        this.lifecycleInfo = lifecycleInfo;
    }

		@TransferIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "one_tomany_badge_fk_oid", nullable = true)
    public Badge getBadge() {
        return badge;
    }

    public void setBadge(final Badge badge) {
        this.badge = badge;
    }

// GETTERS AND SETTERS
    @UIAttribute(fieldName = "description", required = false, blankAllowed = false, fieldEditor = UIFieldEditor.TEXT_FIELD)
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @UIAttribute(fieldName = "requestExpirationDate", required = false, blankAllowed = false, fieldEditor = UIFieldEditor.TEXT_FIELD)
    @Column(name = "requestexpirationdate")
    public Date getRequestExpirationDate() {
        return requestExpirationDate;
    }

    public void setRequestExpirationDate(Date requestExpirationDate) {
        this.requestExpirationDate = requestExpirationDate;
    }


}

