package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PostDeleteBadge extends VLServiceEvent {

    public PostDeleteBadge() {
        super();
    }

    public PostDeleteBadge(Persistable subject) {
        super(subject);
    }
}
