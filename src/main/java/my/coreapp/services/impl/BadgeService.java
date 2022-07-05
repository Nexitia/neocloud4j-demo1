package my.coreapp.services.impl;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.exceptions.BusinessRuleException;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.service.api.IPersistableService;
import com.nt.neocloud4j.core.service.api.foldered.IContainedService;
import com.nt.neocloud4j.core.utils.UIAttributes;
import org.apache.commons.lang.StringUtils;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.nt.neocloud4j.core.model.typed.ITypeManaged;
import com.nt.neocloud4j.core.service.api.typed.ITypeManagedService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import java.util.Map;
import my.coreapp.model.*;
import my.coreapp.services.api.IBadgeService;
import my.coreapp.dao.api.IBadgeDao;
import my.coreapp.model.event.PreCreateBadge;
import my.coreapp.model.event.PreDeleteBadge;
import my.coreapp.model.event.PreUpdateBadge;
import my.coreapp.model.event.PostCreateBadge;
import my.coreapp.model.event.PostDeleteBadge;
import my.coreapp.model.event.PostUpdateBadge;
import java.util.List;
import my.coreapp.model.CardRequest;
import com.nt.neocloud4j.core.model.account.UserAccount;
import com.nt.neocloud4j.core.model.account.QUserAccount;
// IMPORT

@Component
public class BadgeService implements  IBadgeService {

    @Autowired
    private IBadgeDao dao;

    @Autowired
    private IPersistableService persistableService;

    @Autowired
    private IContainedService containedService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ITypeManagedService typeManagedService;

    @Autowired
    private IPersistableRepository persistableDao;

    @Override
    @Transactional
    public Badge  create(UIAttributes uiAttributes, Container container) {
        Badge entity = (Badge) uiAttributes.getTarget();
        containedService.setContainer(entity, container);

        String businessType = uiAttributes.getAllAttributesFromUI().optString("businessType");
        if(StringUtils.isNotBlank(businessType) && entity instanceof ITypeManaged){
            typeManagedService.setType((ITypeManaged) entity, businessType);
        }

        applicationContext.publishEvent(new PreCreateBadge(entity, uiAttributes));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateBadge(entity, uiAttributes));
        return entity;
    }

    @Override
    @Transactional
    public void batchCreateFromImport(final Badge entity, final Container container) {
        containedService.setContainer(entity, container);

        applicationContext.publishEvent(new PreCreateBadge(entity));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateBadge(entity));
    }

    @Override
    @Transactional
    public void batchUpdateFromImport(final Badge transientEntity) {
        Badge persistent = (Badge) persistableService.findById(transientEntity.getOid(), Badge.class);

        applicationContext.publishEvent(new PreUpdateBadge(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateBadge(persistent));
    }


    @Override
    @Transactional
    public Badge update(UIAttributes uiAttributes, Container container) {
        Badge transientEntity = (Badge) uiAttributes.getTarget();
        Badge persistent = (Badge) persistableService.findById(transientEntity.getOid(), Badge.class);

        applicationContext.publishEvent(new PreUpdateBadge(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateBadge(persistent));

        return persistent;
    }

    @Override
    @Transactional
    public void delete(Badge entity, Container container) {
        Badge persistent  = persistableService.refresh(entity);
        applicationContext.publishEvent(new PreDeleteBadge(persistent));
        persistableService.deleteWithoutEvent(persistent);
        applicationContext.publishEvent(new PostDeleteBadge(persistent));
    }

    @Override
    public PageResult list(PageRequest pageRequest, Container container, Map<String, String> params) {
        return dao.list(pageRequest, container, params);
    }

    @Override
    public PageResult searchByNameLike(String searchTerm, PageRequest pageRequest, Container container) {
        return dao.searchByNamelike(searchTerm, pageRequest, container);
    }

    


    @Override
    public List<CardRequest> getAllCardRequest(final Badge entity, final Container container) {
        QCardRequest qCardRequest = new QCardRequest("roleB");

        return persistableDao.from(qCardRequest)
                .where(qCardRequest.badge().eq(entity))
                .list(qCardRequest);
    }


    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public void addCardRequest(final Badge entity, final CardRequest roleb, final Container container) {
        Badge refreshed = persistableService.refresh(entity);
        CardRequest refreshedToOne = persistableService.refresh(roleb);
        refreshed.addCardRequest(refreshedToOne);

        persistableService.mergeWithoutEvent(refreshed);
    }

    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public void removeAllCardRequest(final Badge entity, final Container container) {
        Badge refreshed = persistableService.refresh(entity);

        for(CardRequest  roleb: refreshed.getCardRequest()){
            roleb.setBadge(null);
        }

        refreshed.getCardRequest().clear();
        persistableService.mergeWithoutEvent(refreshed);
    }

    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public void removeCardRequest(final Badge entity, final CardRequest roleB, final Container container) {
        Badge refreshed = persistableService.refresh(entity);
        refreshed.getCardRequest().remove(roleB);
        roleB.setBadge(null);
        persistableService.mergeWithoutEvent(refreshed);
    }

    @Override
    public PageResult navigateCardRequest(final Badge entity, final PageRequest pageRequest, final Container container) {
       PageResult pageResult = dao.navigateCardRequest(entity, pageRequest, container);
       return pageResult;
    }

    @Override
    public Badge getOneToManyCardRequestInverse(final CardRequest entity, final Container container) {
        return dao.getOneToManyCardRequestInverse(entity, container);
    }



    @Override
    public PageResult searchUserAccountEntity(final Badge roleA, final String searchTerm, final PageRequest pageRequest, final Container container) {
        return dao.searchUserAccountEntity(roleA, searchTerm, pageRequest, container);
    }

// SERVICES
}
