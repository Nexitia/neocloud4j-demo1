package my.coreapp.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;

import java.util.*;
import my.coreapp.model.Badge;
import my.coreapp.model.QBadge;
import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

//IMPORT

@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPointerToBadgeLink extends EntityPathBase<PointerToBadgeLink> {

    private static final long serialVersionUID = -1888300761L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointerToBadgeLink pointerLink = new QPointerToBadgeLink("pointerLink");

    public final com.nt.neocloud4j.core.model.persistable.QPersistable _super;

    protected com.nt.neocloud4j.core.model.composite.QContainerInfo containerInfo;

    protected QPointerToBadgeLinkId id;

    // inherited
    protected com.nt.neocloud4j.core.model.persistable.QPersistenceInfo persistenceInfo;

    protected QPointer roleA;

    protected QBadge roleB;

    //inherited
    public final NumberPath<Long> version;

    public QPointerToBadgeLink(String variable) {
        this(PointerToBadgeLink.class, forVariable(variable), INITS);
    }

    public QPointerToBadgeLink(Path<? extends PointerToBadgeLink> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPointerToBadgeLink(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPointerToBadgeLink(PathMetadata<?> metadata, PathInits inits) {
        this(PointerToBadgeLink.class, metadata, inits);
    }

    public QPointerToBadgeLink(Class<? extends PointerToBadgeLink> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.nt.neocloud4j.core.model.persistable.QPersistable(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QPointerToBadgeLinkId(forProperty("id")) : null;
        this.roleA = inits.isInitialized("roleA") ? new QPointer(forProperty("roleA"), inits.get("roleA")) : null;
        this.roleB = inits.isInitialized("roleB") ? new QBadge(forProperty("roleB"), inits.get("roleB")) : null;
        this.version = _super.version;
    }

    public QPointerToBadgeLinkId id() {
        if (id == null) {
            id = new QPointerToBadgeLinkId(forProperty("id"));
        }
        return id;
    }

    public com.nt.neocloud4j.core.model.composite.QContainerInfo containerInfo() {
        if (containerInfo == null) {
            containerInfo = new com.nt.neocloud4j.core.model.composite.QContainerInfo(forProperty("containerInfo"));
        }
        return containerInfo;
    }

    public com.nt.neocloud4j.core.model.persistable.QPersistenceInfo persistenceInfo() {
        if (persistenceInfo == null) {
            persistenceInfo = new com.nt.neocloud4j.core.model.persistable.QPersistenceInfo(forProperty("persistenceInfo"));
        }
        return persistenceInfo;
    }

    public QPointer roleA() {
        if (roleA == null) {
            roleA = new QPointer(forProperty("roleA"));
        }
        return roleA;
    }

    public QBadge roleB() {
        if (roleB == null) {
            roleB = new QBadge(forProperty("roleB"));
        }
        return roleB;
    }
}
