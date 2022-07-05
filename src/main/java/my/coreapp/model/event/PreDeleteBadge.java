package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PreDeleteBadge extends VLServiceEvent {

    public PreDeleteBadge() {
            super();
    }

    public PreDeleteBadge(Persistable subject) {
        super(subject);
    }
}
