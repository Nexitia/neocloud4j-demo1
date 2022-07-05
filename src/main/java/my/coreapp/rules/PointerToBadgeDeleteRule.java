package my.coreapp.rules;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.rules.api.IVetoableBusinessRule;
import my.coreapp.model.Pointer;
import my.coreapp.model.Badge;
import my.coreapp.model.event.PreDeletePointer;
import my.coreapp.services.api.IPointerToBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//IMPORT

@Component("PointerToBadgeDeleteRule")
public class PointerToBadgeDeleteRule implements IVetoableBusinessRule {

    @Autowired
    private IPointerToBadgeService service;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void apply(VLServiceEvent event) {
        PreDeletePointer preDeleteRoleAEvent = (PreDeletePointer) event;
        Pointer roleA = (Pointer) preDeleteRoleAEvent.getSubject();
        service.removeAllRoleBs(roleA, event.getContainer());
    }

}