package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PreUpdateBadge extends VLServiceEvent {

    private UIAttributes uiAttributes;

    public PreUpdateBadge() {
        super();
    }

    public PreUpdateBadge(Persistable subject) {
        super(subject);
    }

     public PreUpdateBadge(Persistable subject, UIAttributes uiAttributes) {
            super(subject);
            this.uiAttributes = uiAttributes;
            setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
        }
}
