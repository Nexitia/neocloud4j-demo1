package my.coreapp.rest;

import com.nt.neocloud4j.core.bridge.operation.IOperationResult;
import com.nt.neocloud4j.core.bridge.result.MultipleResult;
import com.nt.neocloud4j.core.bridge.result.OperationData;
import com.nt.neocloud4j.core.utils.pagination.PageRequest;
import com.nt.neocloud4j.core.utils.pagination.PageResult;
import com.nt.neocloud4j.core.model.composite.Container;
import com.nt.neocloud4j.core.utils.RestObjectFullId;
import com.nt.neocloud4j.core.utils.common.TransferUtils;
import com.nt.neocloud4j.core.utils.common.ValidationErrorTranslator;
import my.coreapp.services.api.IBadgeToResourceAccessService;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.Badge;
import my.coreapp.model.BadgeToResourceAccessLink;
import my.coreapp.rest.dto.BadgeToResourceAccessLinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//IMPORT

@RestController
@RequestMapping("/neocloud4j/online/v1/secured/api/badge/manytomany/ResourceAccess")
public class BadgeToResourceAccessServiceFacade {

    @Autowired
    @Qualifier("ValidationErrorTranslator")
    private ValidationErrorTranslator et;

    @Autowired
    private IBadgeToResourceAccessService service;

    @PostMapping(path = "/createLink", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable createLink(@RequestParam("roleAId") RestObjectFullId roleAId,
                                   @RequestParam("roleBId") RestObjectFullId roleBId,
                                   @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.basicSuccess();
        service.createLink((Badge) roleAId.getPersistable(), (ResourceAccess) roleBId.getPersistable(), container);
        return result;
    }

    @DeleteMapping(path = "/deleteLink", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable deleteLink(@RequestParam("roleAId") RestObjectFullId roleAId,
                                   @RequestParam("roleBId") RestObjectFullId roleBId,
                                   @RequestParam("containerId") Container container){

        IOperationResult result = IOperationResult.basicSuccess();

        Badge roleA = (Badge) roleAId.getPersistable();
        ResourceAccess roleB = (ResourceAccess) roleBId.getPersistable();
        service.deleteLink(roleA, roleB, container);

        return result;
    }

    @PutMapping(path = "/moveFromRoleA", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable moveFromRoleA(@RequestParam("roleAId") RestObjectFullId roleAId,
                                      @RequestParam("targetRoleAId") RestObjectFullId targetRoleAId,
                                      @RequestParam("roleBId") RestObjectFullId roleBId,
                                      @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.basicSuccess();

        Badge roleA = (Badge) roleAId.getPersistable();
        Badge targetRoleA = (Badge) targetRoleAId.getPersistable();
        ResourceAccess roleB = (ResourceAccess) roleBId.getPersistable();
        service.moveFromRoleA(roleA, targetRoleA, roleB, container);

        return result;
    }

    @GetMapping(path = "/getLink", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable getLink(@RequestParam("roleAId") RestObjectFullId roleAId,
                                @RequestParam("roleBId") RestObjectFullId roleBId,
                                @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.basicSuccess();

        Badge roleA = (Badge) roleAId.getPersistable();
        ResourceAccess roleB = (ResourceAccess) roleBId.getPersistable();
        Optional<BadgeToResourceAccessLink> linkAsOptional = service.getLink(roleA, roleB, container);

        linkAsOptional.ifPresent(link -> {
            OperationData operationData = TransferUtils.toOperationData(link);
            result.setData(operationData);
        });

        return result;
    }

    @DeleteMapping(path = "/removeAllRoleBs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable removeAllRoleBs(@RequestParam("roleAId") RestObjectFullId roleAId,
                                        @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.basicSuccess();

        Badge roleA = (Badge) roleAId.getPersistable();
        service.removeAllRoleBs(roleA, container);
        return result;
    }

    @GetMapping(path = "/getAllRoleBs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable getAllRoleBs(@RequestParam("roleAId") RestObjectFullId roleAId,
                                     @RequestParam("containerId") Container container){

        Badge roleA = (Badge) roleAId.getPersistable();
        List<ResourceAccess> roleBs = service.getAllRoleBs(roleA, container);

        return TransferUtils.toUniquePageResult(roleBs);
    }

    @GetMapping(path = "/getAllRoleBs/inverse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(
                                     @RequestParam("roleBId") RestObjectFullId roleBId,
                                     @RequestParam("containerId") Container container){

        ResourceAccess roleB = (ResourceAccess) roleBId.getPersistable();
        List<Badge> roleAs = service.getAllBadgeInverseRoleBsManyToManyLinkToOfResourceAccess(roleB, container);

        return TransferUtils.toUniquePageResult(roleAs);
    }

    @GetMapping(path = "/getAllRoleBsWithLink", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable getAllRoleBsWithLink(@RequestParam("roleAId") RestObjectFullId roleAId,
                                             @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.listSuccess();

        Badge roleA = (Badge) roleAId.getPersistable();
        Map<ResourceAccess, BadgeToResourceAccessLink> links = service.getAllRoleBsWithLink(roleA, container);
        List dtos = links.keySet().stream().map(key -> BadgeToResourceAccessLinkDTO.from(key, links.get(key))).collect(Collectors.toList());
        result.setData(dtos);
        return result;
    }

    @GetMapping(path = "/searchRoleBsNotLinkedToRoleA", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Serializable searchRoleBsNotLinkedToRoleA(@RequestParam("roleAId") RestObjectFullId roleAId,
                                                     @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                     @RequestParam("containerId") Container container){
        IOperationResult result = IOperationResult.listSuccess();
        Badge roleA = (Badge) roleAId.getPersistable();
        PageRequest pageRequest = new PageRequest(page, pageSize);
        PageResult pageResult = service.searchRoleBsNotLinkedToRoleA(roleA, searchTerm, pageRequest, container);
        TransferUtils.toOperationData(pageResult, (MultipleResult) result);
        return result;
    }
}
