package my.coreapp.batch;

import com.nt.neocloud4j.core.utils.ObjectIdentifierUtils;
import com.nt.neocloud4j.core.helpers.Neoclou4JCoreServicesHelper;
import com.nt.neocloud4j.core.batch.bean.AbstractLineBean;
import com.nt.neocloud4j.core.batch.exception.VLBatchSkippableException;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.batch.bean.ImportLineBean;
import com.nt.neocloud4j.core.model.typed.ITypeManaged;
import com.nt.neocloud4j.core.model.typed.TypeManaged;
import com.nt.neocloud4j.core.service.api.typed.ITypeManagedService;

import my.coreapp.model.ResourceAccess;
import my.coreapp.services.api.IResourceAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("ResourceAccessBatchWriter")
@Scope("step")
public class ResourceAccessBatchWriter implements ItemWriter<AbstractLineBean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceAccessBatchWriter.class);

    @Autowired
    private Neoclou4JCoreServicesHelper services;

    @Autowired
    private IResourceAccessService service;

    @Autowired
    private ITypeManagedService typeManagedService;

    @Override
    public void write(final List<? extends AbstractLineBean> items) throws Exception {

        Iterator entityIterator = items.iterator();

        while(entityIterator.hasNext()) {
          ImportLineBean line = (ImportLineBean) entityIterator.next();
          String containerPath = line.getString(1);

          String command = line.getCommand();
          if(command.startsWith("ADD_")){
              Container container = services.containerService().getContainerByPath(containerPath);
              if (container == null) {
                  throw new VLBatchSkippableException("Container not found with path: " + containerPath);
              }

              ResourceAccess entity = (ResourceAccess) line.getRootEntity();

              // TODO handle set type from csv file
              if(entity instanceof ITypeManaged){
                    TypeManaged annotation = AnnotationUtils.findAnnotation(entity.getClass(), TypeManaged.class);
                    typeManagedService.setType((ITypeManaged) entity, annotation.rootType());
              }

              service.batchCreateFromImport(entity, container);
          }

          if(command.startsWith("UPDATE_")){
              ResourceAccess transientEntity = (ResourceAccess) line.getRootEntity();
              String fullId  = ObjectIdentifierUtils.decode((String) line.getExternalEntity("fullId"));
              Long id = ObjectIdentifierUtils.getId(fullId);
              transientEntity.setOid(id);

              service.batchUpdateFromImport(transientEntity);
          }
        }
    }
}
