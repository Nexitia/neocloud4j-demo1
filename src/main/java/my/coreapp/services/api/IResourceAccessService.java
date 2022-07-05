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
// IMPORT

public interface IResourceAccessService {

    ResourceAccess create(UIAttributes uiAttributes, Container container) ;

    ResourceAccess update(UIAttributes uiAttributes, Container container) ;

    void delete(ResourceAccess entity, Container container);

    PageResult list(PageRequest pageRequest, Container container, Map<String, String> params);

    PageResult searchByNameLike(String searchTerm, PageRequest pageRequest, Container container);

    void batchCreateFromImport(ResourceAccess entity, Container container);

    void batchUpdateFromImport(ResourceAccess transientEntity);

    // SERVICES
}
