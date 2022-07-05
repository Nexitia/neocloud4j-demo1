package my.coreapp.rules;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.rules.api.IVetoableBusinessRule;
import my.coreapp.model.Badge;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.event.PreDeleteBadge;
import my.coreapp.services.api.IBadgeToResourceAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//IMPORT

@Component("BadgeToResourceAccessDeleteRule")
public class BadgeToResourceAccessDeleteRule implements IVetoableBusinessRule {

    @Autowired
    private IBadgeToResourceAccessService service;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void apply(VLServiceEvent event) {
        PreDeleteBadge preDeleteRoleAEvent = (PreDeleteBadge) event;
        Badge roleA = (Badge) preDeleteRoleAEvent.getSubject();
        service.removeAllRoleBs(roleA, event.getContainer());
    }

}