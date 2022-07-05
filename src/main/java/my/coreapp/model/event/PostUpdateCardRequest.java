package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PostUpdateCardRequest extends VLServiceEvent {

   private UIAttributes uiAttributes;

    public PostUpdateCardRequest() {
        super();
    }

    public PostUpdateCardRequest(Persistable subject) {
        super(subject);
    }

    public PostUpdateCardRequest(Persistable subject, UIAttributes uiAttributes) {
        super(subject);
        this.uiAttributes = uiAttributes;
        setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
    }
}
