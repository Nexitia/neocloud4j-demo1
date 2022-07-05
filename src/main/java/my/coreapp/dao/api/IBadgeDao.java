package my.coreapp.dao.api;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.model.Badge;
import java.util.Map;
import my.coreapp.model.*;
import com.nt.neocloud4j.core.model.account.UserAccount;
// IMPORT

public interface IBadgeDao {

    PageResult list(PageRequest pageRequest, Container container, Map<String, String> params);

    boolean existWithName(String name, Container container);

    PageResult searchByNamelike(String searchTerm, PageRequest pageRequest, Container container);


    PageResult navigateCardRequest(Badge entity, PageRequest pageRequest, Container container);

    Badge getOneToManyCardRequestInverse(CardRequest entity, Container container);

    PageResult searchUserAccountEntity(final Badge roleA, final String searchTerm, PageRequest pageRequest, Container container);

// DAO
}
