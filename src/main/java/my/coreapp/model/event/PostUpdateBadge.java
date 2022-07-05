package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PostUpdateBadge extends VLServiceEvent {

   private UIAttributes uiAttributes;

    public PostUpdateBadge() {
        super();
    }

    public PostUpdateBadge(Persistable subject) {
        super(subject);
    }

    public PostUpdateBadge(Persistable subject, UIAttributes uiAttributes) {
        super(subject);
        this.uiAttributes = uiAttributes;
        setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
    }
}
