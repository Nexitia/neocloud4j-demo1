package my.coreapp.services.impl;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.dao.api.IBadgeToResourceAccessDao;
import my.coreapp.services.api.IBadgeToResourceAccessService;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.Badge;
import my.coreapp.model.QResourceAccess;
import my.coreapp.model.BadgeToResourceAccessLink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//IMPORT

@Component
public class BadgeToResourceAccessService implements IBadgeToResourceAccessService {

    @Inject
    private IBadgeToResourceAccessDao dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        dao.createLink(roleA, roleB, container);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        dao.deleteLink(roleA, roleB, container);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void moveFromRoleA(final Badge roleA, final Badge targetRoleA, final ResourceAccess roleB, final Container container) {
        deleteLink(roleA, roleB, container);
        createLink(targetRoleA, roleB, container);
    }

    @Override
    public Optional<BadgeToResourceAccessLink> getLink(final Badge roleA, final ResourceAccess roleB, final Container container) {
        return dao.getLink(roleA, roleB, container);
    }

    @Override
    public void removeAllRoleBs(final Badge roleA, final Container container) {
        dao.removeAllRoleBs(roleA, container);
    }

    @Override
    public List<ResourceAccess> getAllRoleBs(final Badge roleA, final Container container) {
        return dao.getAllRoleBs(roleA, container);
    }

    @Override
    public Map<ResourceAccess, BadgeToResourceAccessLink> getAllRoleBsWithLink(final Badge roleA, final Container container) {
        return dao.getAllRoleBsWithLink(roleA, container);
    }

    @Override
    public PageResult searchRoleBsNotLinkedToRoleA(final Badge roleA, final String searchTerm, final PageRequest pageRequest, final Container container) {
        return dao.searchRoleBsNotLinkedToRoleA(roleA, searchTerm, pageRequest, container);
    }

    @Override
    public List<Badge> getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(final ResourceAccess roleB, final Container container) {
        return dao.getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(roleB, container);
    }
}
