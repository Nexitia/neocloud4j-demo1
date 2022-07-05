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

import com.nt.neocloud4j.core.model.Boolean01Converter;
import java.lang.String;
import my.coreapp.model.CardRequest;
// IMPORT


@javax.persistence.Entity
@Table(name = "gen_badge")
@Access(AccessType.PROPERTY)
// ANNOTATIONS
public class Badge extends BusinessObject implements Serializable {// KNOER

    private static final long serialVersionUID = 1L;

    private String identification;
    private Date expirationDate;
    private Boolean isActive;
    	private List<CardRequest> cardRequest;
    private com.nt.neocloud4j.core.model.account.UserAccount userAccount;

// ATTRIBUTES


    @Override
    public void updateFrom(Persistable entity) {
        super.updateFrom(entity);
        setIdentification(((Badge)entity).getIdentification());
        setIdentification(((Badge)entity).getIdentification());
        setExpirationDate(((Badge)entity).getExpirationDate());
        setIdentification(((Badge)entity).getIdentification());
        setExpirationDate(((Badge)entity).getExpirationDate());
        setIsActive(((Badge)entity).getIsActive());
        // UPDATE_ATTRIBUTES
    }

    @Override
    @Transient
    public Class<?> getDomainClass() {
        return Badge.class;
    }


    @Id
    @Override
    @SequenceGenerator(name="gen_badge_oid_seq", sequenceName="gen_badge_oid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_badge_oid_seq")
    @Column(columnDefinition = "serial", updatable = false)
    public Long getOid() {
        return super._getOid();
    }

    
		@TransferIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="badge")
    public List<CardRequest> getCardRequest() {
        return cardRequest;
    }

    public void setCardRequest(final List<CardRequest> cardRequest) {
        this.cardRequest = cardRequest;
    }

    public void addCardRequest(CardRequest entity){
      if(cardRequest == null){
        cardRequest = new ArrayList();
      }

      cardRequest.add(entity);
      entity.setBadge(this);
    }

    @TransferIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "manytoone_useraccount_fk_oid", nullable = true)
    public com.nt.neocloud4j.core.model.account.UserAccount getUserAccount() {
        return userAccount;
    }


    public void setUserAccount(final com.nt.neocloud4j.core.model.account.UserAccount userAccount) {
        this.userAccount = userAccount;
    }

// GETTERS AND SETTERS
    @UIAttribute(fieldName = "identification", required = false, blankAllowed = false, fieldEditor = UIFieldEditor.TEXT_FIELD)
    @Column(name = "identification")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @UIAttribute(fieldName = "expirationDate", required = false, blankAllowed = false, fieldEditor = UIFieldEditor.TEXT_FIELD)
    @Column(name = "expirationdate")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Convert(converter = Boolean01Converter.class)
    @UIAttribute(fieldName = "isActive", required = false, blankAllowed = true, fieldEditor = UIFieldEditor.TEXT_FIELD)
    @Column(name = "isactive")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


}

