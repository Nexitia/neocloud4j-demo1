package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PostUpdateResourceAccess extends VLServiceEvent {

   private UIAttributes uiAttributes;

    public PostUpdateResourceAccess() {
        super();
    }

    public PostUpdateResourceAccess(Persistable subject) {
        super(subject);
    }

    public PostUpdateResourceAccess(Persistable subject, UIAttributes uiAttributes) {
        super(subject);
        this.uiAttributes = uiAttributes;
        setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
    }
}
