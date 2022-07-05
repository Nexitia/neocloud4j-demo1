package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PostDeleteCardRequest extends VLServiceEvent {

    public PostDeleteCardRequest() {
        super();
    }

    public PostDeleteCardRequest(Persistable subject) {
        super(subject);
    }
}
