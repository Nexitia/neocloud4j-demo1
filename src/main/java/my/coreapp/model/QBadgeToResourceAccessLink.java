package my.coreapp.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;

import java.util.*;
import my.coreapp.model.ResourceAccess;
import my.coreapp.model.QResourceAccess;
import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

//IMPORT

@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBadgeToResourceAccessLink extends EntityPathBase<BadgeToResourceAccessLink> {

    private static final long serialVersionUID = -1888300761L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBadgeToResourceAccessLink badgeLink = new QBadgeToResourceAccessLink("badgeLink");

    public final com.nt.neocloud4j.core.model.persistable.QPersistable _super;

    protected com.nt.neocloud4j.core.model.composite.QContainerInfo containerInfo;

    protected QBadgeToResourceAccessLinkId id;

    // inherited
    protected com.nt.neocloud4j.core.model.persistable.QPersistenceInfo persistenceInfo;

    protected QBadge roleA;

    protected QResourceAccess roleB;

    //inherited
    public final NumberPath<Long> version;

    public QBadgeToResourceAccessLink(String variable) {
        this(BadgeToResourceAccessLink.class, forVariable(variable), INITS);
    }

    public QBadgeToResourceAccessLink(Path<? extends BadgeToResourceAccessLink> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBadgeToResourceAccessLink(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBadgeToResourceAccessLink(PathMetadata<?> metadata, PathInits inits) {
        this(BadgeToResourceAccessLink.class, metadata, inits);
    }

    public QBadgeToResourceAccessLink(Class<? extends BadgeToResourceAccessLink> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.nt.neocloud4j.core.model.persistable.QPersistable(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QBadgeToResourceAccessLinkId(forProperty("id")) : null;
        this.roleA = inits.isInitialized("roleA") ? new QBadge(forProperty("roleA"), inits.get("roleA")) : null;
        this.roleB = inits.isInitialized("roleB") ? new QResourceAccess(forProperty("roleB"), inits.get("roleB")) : null;
        this.version = _super.version;
    }

    public QBadgeToResourceAccessLinkId id() {
        if (id == null) {
            id = new QBadgeToResourceAccessLinkId(forProperty("id"));
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

    public QBadge roleA() {
        if (roleA == null) {
            roleA = new QBadge(forProperty("roleA"));
        }
        return roleA;
    }

    public QResourceAccess roleB() {
        if (roleB == null) {
            roleB = new QResourceAccess(forProperty("roleB"));
        }
        return roleB;
    }
}
