package my.coreapp.rest.rest;

import com.nt.neocloud4j.core.bridge.operation.IOperationResult;
import com.nt.neocloud4j.core.bridge.result.MultipleResult;
import com.nt.neocloud4j.core.bridge.result.OperationData;
import com.nt.neocloud4j.core.bridge.result.SingleResult;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.service.api.IPersistableService;
import org.hibernate.Hibernate;
import com.nt.neocloud4j.core.utils.RestObjectFullId;
import com.nt.neocloud4j.core.utils.common.TransferUtils;
import com.nt.neocloud4j.core.utils.common.ValidationErrorTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.hibernate.Hibernate;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.nt.neocloud4j.core.utils.UIAttributes;
import javax.inject.Provider;
import com.nt.neocloud4j.core.dao.api.IPersistableRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import my.coreapp.services.api.IBadgeService;
import my.coreapp.model.*;
import my.coreapp.model.CardRequest;
import com.nt.neocloud4j.core.model.account.UserAccount;
// IMPORT

@RestController
@RequestMapping("/neocloud4j/online/v1/secured/api/badge")
public class BadgeServiceFacade {

  @Autowired
  @Qualifier("ValidationErrorTranslator")
  private ValidationErrorTranslator et;

  @Autowired
  private IBadgeService service;

  @Autowired
  private IPersistableService persistableService;

  @Autowired
  private IPersistableRepository persistableRepository;

  @Autowired
  private Provider<UIAttributes> uiAttributesProvider;


  @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable create(@RequestBody String request) {
    IOperationResult result = new SingleResult();

    UIAttributes uiAttributes = uiAttributesProvider.get();
    uiAttributes.from(request, Badge.class);
    uiAttributes.validateAttributes(result);

    Container container = uiAttributes.validateContainer(result);
    service.create(uiAttributes, container);

    OperationData operationData = TransferUtils.toOperationData(uiAttributes.getTarget());
    result.setData(operationData);
    return result;
  }

  @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @ResponseBody
  public Serializable details(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                            @RequestParam("containerId") Container container)  {

    IOperationResult result = new SingleResult();
    Badge entity = (Badge) entityId.getPersistable();

    OperationData operationData = TransferUtils.toOperationData(entity);
    result.setData(operationData);
    return result;
  }

  @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable delete(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                           @RequestParam("containerId") Container container)  {

    IOperationResult result = new SingleResult();
    Badge entity = (Badge) entityId.getPersistable();
    service.delete(entity, container);
    return result;
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable update(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                           @RequestBody String request,
                           @RequestParam("containerId") Container container) {

    IOperationResult result = new SingleResult();

    UIAttributes uiAttributes = uiAttributesProvider.get();
    uiAttributes.from(request, Badge.class);
    uiAttributes.validateAttributes(result);

    Badge entity = (Badge) entityId.getPersistable();
    uiAttributes.getTarget().setOid(entity.getOid());

    service.update(uiAttributes, container);
    return result;
  }

  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @ResponseBody
  public Serializable list(@RequestParam("containerId") Container container,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "status", defaultValue = "") String status,
                         @RequestParam(name = "pageSize", defaultValue = "10") int pagesize,
                         @RequestParam(name = "sort", required = false) String sort){

    IOperationResult result = new MultipleResult();
    PageRequest pageRequest = new PageRequest.Builder().page(page).size(pagesize).build();

    Map params = new HashMap();
    params.put("status", status);

    PageResult pageResult = service.list(pageRequest, container, params);
    TransferUtils.toOperationData(pageResult, (MultipleResult)result);

    return result;
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @ResponseBody
  public Serializable search(@RequestParam("containerId") Container container,
                           @RequestParam(name = "searchTerm") String searchTerm,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "pageSize", defaultValue = "10") int pagesize,
                           @RequestParam(name = "sort", required = false) String sort){

    IOperationResult result = new MultipleResult();
    PageRequest pageRequest = new PageRequest.Builder().page(page).size(pagesize).build();

    PageResult pageResult = service.searchByNameLike(searchTerm, pageRequest, container);
    TransferUtils.toOperationData(pageResult, (MultipleResult)result);

    return result;
  }

  

  @PostMapping(value = "/oneToManyCardRequest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable addOneToManyCardRequest(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                        @RequestParam(name = "rolebId") final RestObjectFullId rolebId,
                                        @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.basicSuccess();
    Badge entity = (Badge) entityId.getPersistable();
    CardRequest roleb = (CardRequest) rolebId.getPersistable();

    service.addCardRequest(entity, roleb, container);

    OperationData operationData = TransferUtils.toOperationData(entity);
    result.setData(operationData);
    return result;
  }

  @DeleteMapping(value = "/oneToManyCardRequest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @ResponseBody
  public Serializable removeOneToManyCardRequest(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                            @RequestParam(name = "rolebId") final RestObjectFullId rolebId,
                                            @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.basicSuccess();
    Badge entity = (Badge) entityId.getPersistable();
    CardRequest roleb = (CardRequest) rolebId.getPersistable();

    service.removeCardRequest(entity, roleb, container);

    OperationData operationData = TransferUtils.toOperationData(entity);
    result.setData(operationData);
    return result;
  }

  @GetMapping(value = "/oneToManyCardRequest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Serializable getAllOneToManyCardRequest(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                        @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.listSuccess();
    Badge entity = (Badge) entityId.getPersistable();

    List<CardRequest> roleb = service.getAllCardRequest(entity, container);
    return TransferUtils.toUniquePageResult(roleb);
  }

  @GetMapping(value = "/oneToManyCardRequestNavigate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Serializable navigateOneToManyCardRequest(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("pageSize") int pageSize,
                                                  @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.listSuccess();
    Badge entity = (Badge) entityId.getPersistable();

    PageRequest pageRequest = new PageRequest(page, pageSize);
    PageResult pageResult = service.navigateCardRequest(entity, pageRequest, container);
    TransferUtils.toOperationData(pageResult, (MultipleResult)result);
    return result;
  }

  @GetMapping(value = "/oneToManyCardRequestInverse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Serializable oneToManyCardRequestInverse(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                                    @RequestParam("containerId") Container container)  {

      IOperationResult result = IOperationResult.basicSuccess();
      CardRequest entity = (CardRequest) entityId.getPersistable();

      Badge rolea = service.getOneToManyCardRequestInverse(entity, container);
      result.setData(TransferUtils.toOperationData((Persistable) Hibernate.unproxy(rolea)));
      return result;
  }


  @PostMapping(value = "/setManyToOneUserAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable setManyToOneUserAccount(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                        @RequestParam(name = "rolebId") final RestObjectFullId rolebId,
                                        @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.basicSuccess();
    Badge entity = (Badge) persistableService.refresh(entityId.getPersistable());
    UserAccount roleb = (UserAccount) rolebId.getPersistable();

    entity.setUserAccount(persistableService.refresh(roleb));
    persistableService.merge(entity);

    OperationData operationData = TransferUtils.toOperationData(entity);
    result.setData(operationData);
    return result;
  }

  @DeleteMapping(value = "/removeManyToOneUserAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @ResponseBody
  public Serializable removeManyToOneUserAccount(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                            @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.basicSuccess();
    Badge entity = (Badge) persistableService.refresh(entityId.getPersistable());
    entity.setUserAccount(null);
    persistableService.merge(entity);

    OperationData operationData = TransferUtils.toOperationData(entity);
    result.setData(operationData);
    return result;
  }

  @GetMapping(value = "/getManyToOneUserAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=true)
  @ResponseBody
  public Serializable getManyToOneUserAccount(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                        @RequestParam("containerId") Container container)  {

    IOperationResult result = IOperationResult.basicSuccess();
    Badge entity = (Badge) persistableService.refresh(entityId.getPersistable());

    UserAccount roleb = entity.getUserAccount();
    if(roleb != null){
      OperationData operationData = TransferUtils.toOperationData(roleb);
      result.setData(operationData);
    }

    return result;
  }


  @GetMapping(value = "/searchUserAccountEntity", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @ResponseBody
  public Serializable searchUserAccountEntity(@RequestParam(name = "entityId") final RestObjectFullId entityId,
                                                       @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                       @RequestParam("containerId") Container container)  {

       IOperationResult result = IOperationResult.listSuccess();
       Badge roleA = (Badge) entityId.getPersistable();
       PageRequest pageRequest = new PageRequest(page, pageSize);
       PageResult pageResult = service.searchUserAccountEntity(roleA, searchTerm, pageRequest, container);
       TransferUtils.toOperationData(pageResult, (MultipleResult) result);
       return result;
  }
// SERVICES
}
