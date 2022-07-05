package my.coreapp.dao.api;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.Badge;
import my.coreapp.model.BadgeToResourceAccessLink;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//IMPORT

public interface IBadgeToResourceAccessDao {

    void createLink(Badge roleA, ResourceAccess roleB, Container container);

    void deleteLink(Badge roleA, ResourceAccess roleB, Container container);

    Optional<BadgeToResourceAccessLink> getLink(Badge roleA, ResourceAccess roleB, Container container);

    void removeAllRoleBs(Badge roleA, Container container);

    List<ResourceAccess> getAllRoleBs(Badge roleA, Container container);

    Map<ResourceAccess, BadgeToResourceAccessLink> getAllRoleBsWithLink(Badge roleA, Container container);

    PageResult searchRoleBsNotLinkedToRoleA(Badge roleA, String searchTerm, PageRequest pageRequest, Container container);

    List<Badge> getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(ResourceAccess roleB, Container container);
}
