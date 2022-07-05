package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PreDeleteCardRequest extends VLServiceEvent {

    public PreDeleteCardRequest() {
            super();
    }

    public PreDeleteCardRequest(Persistable subject) {
        super(subject);
    }
}
