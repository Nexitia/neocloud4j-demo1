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
import my.coreapp.services.api.IResourceAccessService;
import my.coreapp.dao.api.IResourceAccessDao;
import my.coreapp.model.event.PreCreateResourceAccess;
import my.coreapp.model.event.PreDeleteResourceAccess;
import my.coreapp.model.event.PreUpdateResourceAccess;
import my.coreapp.model.event.PostCreateResourceAccess;
import my.coreapp.model.event.PostDeleteResourceAccess;
import my.coreapp.model.event.PostUpdateResourceAccess;
import java.util.List;
// IMPORT

@Component
public class ResourceAccessService implements  IResourceAccessService {

    @Autowired
    private IResourceAccessDao dao;

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
    public ResourceAccess  create(UIAttributes uiAttributes, Container container) {
        ResourceAccess entity = (ResourceAccess) uiAttributes.getTarget();
        containedService.setContainer(entity, container);

        String businessType = uiAttributes.getAllAttributesFromUI().optString("businessType");
        if(StringUtils.isNotBlank(businessType) && entity instanceof ITypeManaged){
            typeManagedService.setType((ITypeManaged) entity, businessType);
        }

        applicationContext.publishEvent(new PreCreateResourceAccess(entity, uiAttributes));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateResourceAccess(entity, uiAttributes));
        return entity;
    }

    @Override
    @Transactional
    public void batchCreateFromImport(final ResourceAccess entity, final Container container) {
        containedService.setContainer(entity, container);

        applicationContext.publishEvent(new PreCreateResourceAccess(entity));
        persistableService.saveWithoutEvent(entity);
        applicationContext.publishEvent(new PostCreateResourceAccess(entity));
    }

    @Override
    @Transactional
    public void batchUpdateFromImport(final ResourceAccess transientEntity) {
        ResourceAccess persistent = (ResourceAccess) persistableService.findById(transientEntity.getOid(), ResourceAccess.class);

        applicationContext.publishEvent(new PreUpdateResourceAccess(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateResourceAccess(persistent));
    }


    @Override
    @Transactional
    public ResourceAccess update(UIAttributes uiAttributes, Container container) {
        ResourceAccess transientEntity = (ResourceAccess) uiAttributes.getTarget();
        ResourceAccess persistent = (ResourceAccess) persistableService.findById(transientEntity.getOid(), ResourceAccess.class);

        applicationContext.publishEvent(new PreUpdateResourceAccess(persistent));
        persistent.updateFrom(transientEntity);
        persistableService.mergeWithoutEvent(persistent);
        applicationContext.publishEvent(new PostUpdateResourceAccess(persistent));

        return persistent;
    }

    @Override
    @Transactional
    public void delete(ResourceAccess entity, Container container) {
        ResourceAccess persistent  = persistableService.refresh(entity);
        applicationContext.publishEvent(new PreDeleteResourceAccess(persistent));
        persistableService.deleteWithoutEvent(persistent);
        applicationContext.publishEvent(new PostDeleteResourceAccess(persistent));
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
