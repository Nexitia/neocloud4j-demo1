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
import my.coreapp.services.api.ICardRequestService;
import my.coreapp.dao.api.ICardRequestDao;
import my.coreapp.model.event.PreCreateCardRequest;
import my.coreapp.model.event.PreDeleteCardRequest;
import my.coreapp.model.event.PreUpdateCardRequest;
import my.coreapp.model.event.PostCreateCardRequest;
import my.coreapp.model.event.PostDeleteCardRequest;
import my.coreapp.model.event.PostUpdateCardRequest;
import java.util.List;
// IMPORT

@Component
public class CardRequestService implements  ICardRequestService {

    @Autowired
    private ICardRequestDao dao;

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
    public CardRequest  create(UIAttributes uiAttributes, Container container) {
        CardRequest entity = (CardRequest) uiAttributes.getTarget();
        containedService.setContainer(entity, container);

        String businessType = uiAttributes.getAllAttributesFromUI().optString("businessType");
        if(StringUtils.isNotBlank(businessType) && entity instanceof ITypeManaged){
            typeManagedService.setType((ITypeManaged) entity, businessType);
        }

        applicationContext.publishEvent(new PreCreateCardRequest(entity, uiAttributes));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateCardRequest(entity, uiAttributes));
        return entity;
    }

    @Override
    @Transactional
    public void batchCreateFromImport(final CardRequest entity, final Container container) {
        containedService.setContainer(entity, container);

        applicationContext.publishEvent(new PreCreateCardRequest(entity));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateCardRequest(entity));
    }

    @Override
    @Transactional
    public void batchUpdateFromImport(final CardRequest transientEntity) {
        CardRequest persistent = (CardRequest) persistableService.findById(transientEntity.getOid(), CardRequest.class);

        applicationContext.publishEvent(new PreUpdateCardRequest(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateCardRequest(persistent));
    }


    @Override
    @Transactional
    public CardRequest update(UIAttributes uiAttributes, Container container) {
        CardRequest transientEntity = (CardRequest) uiAttributes.getTarget();
        CardRequest persistent = (CardRequest) persistableService.findById(transientEntity.getOid(), CardRequest.class);

        applicationContext.publishEvent(new PreUpdateCardRequest(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateCardRequest(persistent));

        return persistent;
    }

    @Override
    @Transactional
    public void delete(CardRequest entity, Container container) {
        CardRequest persistent  = persistableService.refresh(entity);
        applicationContext.publishEvent(new PreDeleteCardRequest(persistent));
        persistableService.deleteWithoutEvent(persistent);
        applicationContext.publishEvent(new PostDeleteCardRequest(persistent));
    }

    @Override
    public PageResult list(PageRequest pageRequest, Container container, Map<String, String> params) {
        return dao.list(pageRequest, container, params);
    }

    @Override
    public PageResult searchByNameLike(String searchTerm, PageRequest pageRequest, Container container) {
        return dao.searchByNamelike(searchTerm, pageRequest, container);
    }

    // SERVICES
}
