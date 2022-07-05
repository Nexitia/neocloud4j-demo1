package my.coreapp.dao.impl;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;
import com.nt.neocloud4j.core.model.composite.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import my.coreapp.dao.api.IPointerDao;
import my.coreapp.model.QPointer;
import java.util.Map;
import com.nt.neocloud4j.core.utils.StringUtils;
import java.util.Objects;
import my.coreapp.model.*;
// IMPORT

@Component
public class PointerDao implements  IPointerDao{

    @Autowired
    private IPersistableRepository dao;

    @Override
    public PageResult list(PageRequest pageRequest, Container container, Map<String, String> params) {
        QPointer qPointer = new QPointer("entity");
        BooleanExpression whereClause =  qPointer.containerInfo().container().eq(container);

        if(Objects.nonNull(params) && params.size() > 0){
            String status = params.get("status");
            if(StringUtils.isNotBlank(status)){
                whereClause = whereClause.and(
                        qPointer.lifecycleInfo().currentState.eq(status)
                );
            }
        }
// WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qPointer).where(whereClause);
        return dao.readPage(jpqlQuery, qPointer, pageRequest);
    }

    @Override
    public boolean existWithName(String name, Container container) {
        QPointer qPointer = new QPointer("entity");
        return false;
    }

    @Override
    public PageResult searchByNamelike(String searchTerm, PageRequest pageRequest, Container container) {
        QPointer qPointer = new QPointer("entity");

        BooleanExpression whereClause = qPointer.name.likeIgnoreCase("%" + searchTerm + "%");
whereClause = whereClause.or(qPointer.description.likeIgnoreCase("%" + searchTerm + "%"));
whereClause = whereClause.or(qPointer.identifier.likeIgnoreCase("%" + searchTerm + "%"));
whereClause = whereClause.or(qPointer.location.likeIgnoreCase("%" + searchTerm + "%"));
// SEARCH ENTITY WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qPointer)
                .where(whereClause);

        return dao.readPage(jpqlQuery, qPointer, pageRequest);
    }

    // DAO
}
