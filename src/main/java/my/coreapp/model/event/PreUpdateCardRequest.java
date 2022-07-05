package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PreUpdateCardRequest extends VLServiceEvent {

    private UIAttributes uiAttributes;

    public PreUpdateCardRequest() {
        super();
    }

    public PreUpdateCardRequest(Persistable subject) {
        super(subject);
    }

     public PreUpdateCardRequest(Persistable subject, UIAttributes uiAttributes) {
            super(subject);
            this.uiAttributes = uiAttributes;
            setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
        }
}
