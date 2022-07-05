package my.coreapp.dao.api;

import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import my.coreapp.model.ResourceAccess;
import java.util.Map;
import my.coreapp.model.*;
// IMPORT

public interface IResourceAccessDao {

    PageResult list(PageRequest pageRequest, Container container, Map<String, String> params);

    boolean existWithName(String name, Container container);

    PageResult searchByNamelike(String searchTerm, PageRequest pageRequest, Container container);

// DAO
}
