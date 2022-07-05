package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;
import com.nt.neocloud4j.core.utils.UIAttributes;

public class PostUpdatePointer extends VLServiceEvent {

   private UIAttributes uiAttributes;

    public PostUpdatePointer() {
        super();
    }

    public PostUpdatePointer(Persistable subject) {
        super(subject);
    }

    public PostUpdatePointer(Persistable subject, UIAttributes uiAttributes) {
        super(subject);
        this.uiAttributes = uiAttributes;
        setAdditionnalAtributes(uiAttributes.getAllAttributesFromUI());
    }
}
