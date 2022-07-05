package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PostDeleteResourceAccess extends VLServiceEvent {

    public PostDeleteResourceAccess() {
        super();
    }

    public PostDeleteResourceAccess(Persistable subject) {
        super(subject);
    }
}
