package my.coreapp.services.api;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.model.Badge;
import my.coreapp.model.Pointer;
import my.coreapp.model.PointerToBadgeLink;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//IMPORT

public interface IPointerToBadgeService {

    void createLink(Pointer roleA, Badge roleB, Container container);

    void deleteLink(Pointer roleA, Badge roleB, Container container);

    void moveFromRoleA(Pointer roleA, Pointer targetRoleA, Badge roleB, Container container);

    Optional<PointerToBadgeLink> getLink(Pointer roleA, Badge roleB, Container container);

    void removeAllRoleBs(Pointer roleA, Container container);

    List<Badge> getAllRoleBs(Pointer roleA, Container container);

    Map<Badge, PointerToBadgeLink> getAllRoleBsWithLink(Pointer roleA, Container container);

    PageResult searchRoleBsNotLinkedToRoleA(Pointer roleA, String searchTerm, PageRequest pageRequest, Container container);

    List<Pointer> getAllPointerInverseRoleBsManyToManyLinkToOfBadge(Badge roleB, Container container);
}
