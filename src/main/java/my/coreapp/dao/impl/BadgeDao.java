package my.coreapp.dao.impl;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;
import com.nt.neocloud4j.core.model.composite.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import my.coreapp.dao.api.IBadgeDao;
import my.coreapp.model.QBadge;
import java.util.Map;
import com.nt.neocloud4j.core.utils.StringUtils;
import java.util.Objects;
import my.coreapp.model.*;
import my.coreapp.model.CardRequest;
import my.coreapp.model.QCardRequest;
import my.coreapp.model.QBadge;
import com.nt.neocloud4j.core.model.account.UserAccount;
import com.nt.neocloud4j.core.model.account.QUserAccount;
// IMPORT

@Component
public class BadgeDao implements  IBadgeDao{

    @Autowired
    private IPersistableRepository dao;

    @Override
    public PageResult list(PageRequest pageRequest, Container container, Map<String, String> params) {
        QBadge qBadge = new QBadge("entity");
        BooleanExpression whereClause =  qBadge.containerInfo().container().eq(container);

        // WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qBadge).where(whereClause);
        return dao.readPage(jpqlQuery, qBadge, pageRequest);
    }

    @Override
    public boolean existWithName(String name, Container container) {
        QBadge qBadge = new QBadge("entity");
        return false;
    }

    @Override
    public PageResult searchByNamelike(String searchTerm, PageRequest pageRequest, Container container) {
        QBadge qBadge = new QBadge("entity");

        BooleanExpression whereClause = qBadge.identification.likeIgnoreCase("%" + searchTerm + "%");
// SEARCH ENTITY WHERE CLAUSE

        JPQLQuery jpqlQuery = dao.from(qBadge)
                .where(whereClause);

        return dao.readPage(jpqlQuery, qBadge, pageRequest);
    }

    

    @Override
    public PageResult navigateCardRequest(final Badge entity, final PageRequest pageRequest, final Container container) {
         QCardRequest qCardRequest = new QCardRequest("entity");
         JPQLQuery jpqlQuery = dao.from(qCardRequest)
                .where(qCardRequest.badge().eq(entity));
         return dao.readPage(jpqlQuery, qCardRequest, pageRequest);
    }

    @Override
    public Badge getOneToManyCardRequestInverse(final CardRequest entity, final Container container) {
        QCardRequest qCardRequest = new QCardRequest("entity");
        QBadge qBadge =  new QBadge("badge");

        Badge roleb = dao.from(qCardRequest, qBadge)
                .where(qCardRequest.oid.eq(entity.getOid()).and(qCardRequest.badge().eq(qBadge)))
                .singleResult(qBadge);

        return roleb;
    }



    @Override
    public PageResult searchUserAccountEntity(final Badge roleA, final String searchTerm, final PageRequest pageRequest, final Container container) {

        QUserAccount qRoleB = new QUserAccount("entity");
        BooleanExpression whereClause = qRoleB.containerInfo().container().isNotNull();
        if (StringUtils.isNotBlank(searchTerm)) {
            //whereClause = whereClause.and(searchTerm.);
        }

        whereClause = whereClause.and(qRoleB.nickName.notIn("epanon", "epadmin"));
//searchRoleBsNotLinkedToRoleA

        JPQLQuery query = dao.from(qRoleB)
                .where(whereClause);

        return dao.readPage(query, qRoleB, pageRequest);
    }

// DAO
}
