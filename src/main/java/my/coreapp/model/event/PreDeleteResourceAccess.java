package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PreDeleteResourceAccess extends VLServiceEvent {

    public PreDeleteResourceAccess() {
            super();
    }

    public PreDeleteResourceAccess(Persistable subject) {
        super(subject);
    }
}
