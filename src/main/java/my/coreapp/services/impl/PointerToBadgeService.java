package my.coreapp.services.impl;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.dao.api.IPointerToBadgeDao;
import my.coreapp.services.api.IPointerToBadgeService;
import my.coreapp.model.Badge;
import my.coreapp.model.Pointer;
import my.coreapp.model.QBadge;
import my.coreapp.model.PointerToBadgeLink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//IMPORT

@Component
public class PointerToBadgeService implements IPointerToBadgeService {

    @Inject
    private IPointerToBadgeDao dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createLink(final Pointer roleA, final Badge roleB, final Container container) {
        dao.createLink(roleA, roleB, container);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLink(final Pointer roleA, final Badge roleB, final Container container) {
        dao.deleteLink(roleA, roleB, container);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void moveFromRoleA(final Pointer roleA, final Pointer targetRoleA, final Badge roleB, final Container container) {
        deleteLink(roleA, roleB, container);
        createLink(targetRoleA, roleB, container);
    }

    @Override
    public Optional<PointerToBadgeLink> getLink(final Pointer roleA, final Badge roleB, final Container container) {
        return dao.getLink(roleA, roleB, container);
    }

    @Override
    public void removeAllRoleBs(final Pointer roleA, final Container container) {
        dao.removeAllRoleBs(roleA, container);
    }

    @Override
    public List<Badge> getAllRoleBs(final Pointer roleA, final Container container) {
        return dao.getAllRoleBs(roleA, container);
    }

    @Override
    public Map<Badge, PointerToBadgeLink> getAllRoleBsWithLink(final Pointer roleA, final Container container) {
        return dao.getAllRoleBsWithLink(roleA, container);
    }

    @Override
    public PageResult searchRoleBsNotLinkedToRoleA(final Pointer roleA, final String searchTerm, final PageRequest pageRequest, final Container container) {
        return dao.searchRoleBsNotLinkedToRoleA(roleA, searchTerm, pageRequest, container);
    }

    @Override
    public List<Pointer> getAllPointerInverseRoleBsManyToManyLinkToOfBadge(final Badge roleB, final Container container) {
        return dao.getAllPointerInverseRoleBsManyToManyLinkToOfBadge(roleB, container);
    }
}
