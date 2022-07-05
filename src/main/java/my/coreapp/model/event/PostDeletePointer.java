package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PostDeletePointer extends VLServiceEvent {

    public PostDeletePointer() {
        super();
    }

    public PostDeletePointer(Persistable subject) {
        super(subject);
    }
}
