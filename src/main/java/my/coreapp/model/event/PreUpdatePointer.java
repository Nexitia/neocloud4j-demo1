package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PreUpdatePointer extends VLServiceEvent {

    private UIAttributes uiAttributes;

    public PreUpdatePointer() {
        super();
    }

    public PreUpdatePointer(Persistable subject) {
        super(subject);
    }

     public PreUpdatePointer(Persistable subject, UIAttributes uiAttributes) {
            super(subject);
            this.uiAttributes = uiAttributes;
            setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
        }
}
