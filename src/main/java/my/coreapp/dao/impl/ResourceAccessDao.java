package my.coreapp.dao.impl;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;
import com.nt.neocloud4j.core.model.composite.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import my.coreapp.dao.api.IResourceAccessDao;
import my.coreapp.model.QResourceAccess;
import java.util.Map;
import com.nt.neocloud4j.core.utils.StringUtils;
import java.util.Objects;
import my.coreapp.model.*;
// IMPORT

@Component
public class ResourceAccessDao implements  IResourceAccessDao{

    @Autowired
    private IPersistableRepository dao;

    @Override
    public PageResult list(PageRequest pageRequest, Container container, Map<String, String> params) {
        QResourceAccess qResourceAccess = new QResourceAccess("entity");
        BooleanExpression whereClause =  qResourceAccess.containerInfo().container().eq(container);

        // WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qResourceAccess).where(whereClause);
        return dao.readPage(jpqlQuery, qResourceAccess, pageRequest);
    }

    @Override
    public boolean existWithName(String name, Container container) {
        QResourceAccess qResourceAccess = new QResourceAccess("entity");
        return false;
    }

    @Override
    public PageResult searchByNamelike(String searchTerm, PageRequest pageRequest, Container container) {
        QResourceAccess qResourceAccess = new QResourceAccess("entity");

        BooleanExpression whereClause = qResourceAccess.name.likeIgnoreCase("%" + searchTerm + "%");
whereClause = whereClause.or(qResourceAccess.location.likeIgnoreCase("%" + searchTerm + "%"));
whereClause = whereClause.or(qResourceAccess.description.likeIgnoreCase("%" + searchTerm + "%"));
// SEARCH ENTITY WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qResourceAccess)
                .where(whereClause);

        return dao.readPage(jpqlQuery, qResourceAccess, pageRequest);
    }

    // DAO
}
