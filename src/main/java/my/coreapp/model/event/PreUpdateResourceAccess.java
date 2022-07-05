package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PreUpdateResourceAccess extends VLServiceEvent {

    private UIAttributes uiAttributes;

    public PreUpdateResourceAccess() {
        super();
    }

    public PreUpdateResourceAccess(Persistable subject) {
        super(subject);
    }

     public PreUpdateResourceAccess(Persistable subject, UIAttributes uiAttributes) {
            super(subject);
            this.uiAttributes = uiAttributes;
            setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
        }
}
