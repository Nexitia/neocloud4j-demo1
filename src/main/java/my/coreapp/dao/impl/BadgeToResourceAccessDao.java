package my.coreapp.dao.impl;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.nt.neocloud4j.core.utils.StringUtils;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import my.coreapp.model.*;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.QResourceAccess;
import my.coreapp.dao.api.IBadgeToResourceAccessDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//IMPORT

@Component
public class BadgeToResourceAccessDao implements IBadgeToResourceAccessDao {


    @Inject
    private IPersistableRepository persistableRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void createLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        if(!existLinkBetween(roleA, roleB, container)) {
            BadgeToResourceAccessLink link = new BadgeToResourceAccessLink(roleA, roleB, container);
            persistableRepository.saveWithoutEvent(link);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        getLink(roleA, roleB, container).ifPresent(link -> {
            persistableRepository.delete(link);
        });
    }

    @Override
    public Optional<BadgeToResourceAccessLink> getLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        QBadgeToResourceAccessLink qLink = QBadgeToResourceAccessLink.badgeLink;
        BadgeToResourceAccessLink link = persistableRepository
                .from(qLink)
                .where(qLink.roleA().eq(roleA)
                        .and(qLink.roleB().eq(roleB))
                        .and(qLink.containerInfo().container().eq(container))
                ).singleResult(qLink);
        return Optional.ofNullable(link);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeAllRoleBs(final Badge roleA, final Container container) {
        QBadgeToResourceAccessLink qLink = QBadgeToResourceAccessLink.badgeLink;
        persistableRepository
                .from(qLink)
                .where(qLink.roleA().eq(roleA).and(qLink.containerInfo().container().eq(container)))
                .list()
                .stream()
                .forEach(link -> persistableRepository.delete((Persistable) link));
    }

    @Override
    public List<ResourceAccess> getAllRoleBs(final Badge roleA, final Container container) {
        QBadgeToResourceAccessLink qLink = QBadgeToResourceAccessLink.badgeLink;
        JPASubQuery linkedMembers = new JPASubQuery()
                .from(qLink)
                .where(qLink.id().roleAId.eq(roleA._getOid())
                        .and(qLink.containerInfo().container().eq(container)));

        QResourceAccess qRoleB = new QResourceAccess("roleB");
        BooleanExpression whereClause = qRoleB.oid.in(linkedMembers.list(qLink.id().roleBId));

        List<ResourceAccess> results = persistableRepository
                .from(qRoleB)
                .where(whereClause)
                .list(qRoleB);

        return results;
    }

    @Override
    public Map<ResourceAccess, BadgeToResourceAccessLink> getAllRoleBsWithLink(final Badge roleA, final Container container) {
        Map<ResourceAccess, BadgeToResourceAccessLink> map = new HashMap<>();
        List<ResourceAccess> roleBs = getAllRoleBs(roleA, container);
        roleBs.stream().forEach(roleB -> {
            getLink(roleA, roleB, container).ifPresent(link -> map.put(roleB, link));
        });

        return map;
    }

    private boolean existLinkBetween(Badge roleA, ResourceAccess roleB, Container container){
        QBadgeToResourceAccessLink qLink = QBadgeToResourceAccessLink.badgeLink;
        return persistableRepository
                .from(qLink)
                .where(qLink.roleA().eq(roleA)
                        .and(qLink.roleB().eq(roleB))
                        .and(qLink.containerInfo().container().eq(container))
                ).exists();
    }

    @Override
    public PageResult searchRoleBsNotLinkedToRoleA(final Badge roleA, final String searchTerm, final PageRequest pageRequest, final Container container) {

        // not linked
        QBadgeToResourceAccessLink link = QBadgeToResourceAccessLink.badgeLink;
        JPASubQuery linkedMembers = new JPASubQuery()
                .from(link)
                .where(link.id().roleAId.eq(roleA._getOid())
                        .and(link.containerInfo().container().eq(container)));

        QResourceAccess qRoleB = new QResourceAccess("entity");
        BooleanExpression whereClause = qRoleB.oid.notIn(linkedMembers.list(link.id().roleBId));

        if (StringUtils.isNotBlank(searchTerm)) {
            //whereClause = whereClause.and(searchTerm.);
        }

        //searchRoleBsNotLinkedToRoleA

        JPAQuery query = persistableRepository
                .from(qRoleB)
                .where(whereClause);

        return persistableRepository.readPage(query, qRoleB, pageRequest);
    }

    @Override
    public List<Badge> getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(final ResourceAccess roleB, final Container container) {
        // not linked
        QBadgeToResourceAccessLink link = QBadgeToResourceAccessLink.badgeLink;
        JPASubQuery linkedMembers = new JPASubQuery()
                .from(link)
                .where(link.id().roleBId.eq(roleB._getOid())
                        .and(link.containerInfo().container().eq(container)));

        QBadge qRoleA = new QBadge("entity");
        BooleanExpression whereClause = qRoleA.oid.in(linkedMembers.list(link.id().roleAId));

        JPAQuery query = persistableRepository
                .from(qRoleA)
                .where(whereClause);

        return query.list(qRoleA);
    }
}
