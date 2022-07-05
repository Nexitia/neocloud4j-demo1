package my.coreapp.services.api;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.utils.UIAttributes;
import java.util.List;
import java.util.Map;
import my.coreapp.model.*;
import my.coreapp.model.CardRequest;
import com.nt.neocloud4j.core.model.account.UserAccount;
// IMPORT

public interface IBadgeService {

    Badge create(UIAttributes uiAttributes, Container container) ;

    Badge update(UIAttributes uiAttributes, Container container) ;

    void delete(Badge entity, Container container);

    PageResult list(PageRequest pageRequest, Container container, Map<String, String> params);

    PageResult searchByNameLike(String searchTerm, PageRequest pageRequest, Container container);

    void batchCreateFromImport(Badge entity, Container container);

    void batchUpdateFromImport(Badge transientEntity);

    
    void addCardRequest(Badge entity, CardRequest roleB, Container container);

    List<CardRequest> getAllCardRequest(Badge entity, Container container);

    void removeCardRequest(Badge entity, CardRequest roleB, Container container);

    void removeAllCardRequest(Badge entity, Container container);

    PageResult navigateCardRequest(Badge entity, PageRequest pageRequest, Container container);

    Badge getOneToManyCardRequestInverse(CardRequest entity, Container container);

    PageResult searchUserAccountEntity(final Badge roleA, final String searchTerm, PageRequest pageRequest, Container container);

// SERVICES
}
