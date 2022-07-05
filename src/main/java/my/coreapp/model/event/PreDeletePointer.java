package my.coreapp.model.event;

import com.nt.neocloud4j.core.model.VLServiceEvent;
import com.nt.neocloud4j.core.model.persistable.Persistable;

public class PreDeletePointer extends VLServiceEvent {

    public PreDeletePointer() {
            super();
    }

    public PreDeletePointer(Persistable subject) {
        super(subject);
    }
}
