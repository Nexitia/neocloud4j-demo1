package my.coreapp.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;

// IMPORT
import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QCardRequest is a Querydsl query type for CardRequest
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCardRequest extends EntityPathBase<CardRequest> {

    private static final long serialVersionUID = -118259594L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardRequest cardRequest = new QCardRequest("cardRequest");

    public final com.nt.neocloud4j.core.model.persistable.QBusinessObject _super;

    // inherited
    protected com.nt.neocloud4j.core.model.composite.QContainerInfo containerInfo;

    public final NumberPath<Long> oid = createNumber("oid", Long.class);

    // inherited
    protected com.nt.neocloud4j.core.model.persistable.QPersistenceInfo persistenceInfo;


    //inherited
    public final NumberPath<Long> version;

     public final com.mysema.query.types.path.StringPath description = createString("description");
 public final com.mysema.query.types.path.DatePath<java.util.Date> requestExpirationDate = createDate("requestExpirationDate", java.util.Date.class);
protected com.nt.neocloud4j.core.model.typed.QTypeInfo typeInfo;
	protected com.nt.neocloud4j.core.model.lifecyclemanaged.QLifecycleInfo lifecycleInfo;
protected QBadge badge;
// ATTRIBUTES

    public QCardRequest(String variable) {
        this(CardRequest.class, forVariable(variable), INITS);

        this.typeInfo = INITS.isInitialized("typeInfo") ? new com.nt.neocloud4j.core.model.typed.QTypeInfo(forProperty("typeInfo"), INITS.get("typeInfo")) : null;
	this.lifecycleInfo = INITS.isInitialized("lifecycleInfo") ? new com.nt.neocloud4j.core.model.lifecyclemanaged.QLifecycleInfo(forProperty("lifecycleInfo"), INITS.get("lifecycleInfo")) : null;
// CONSTRUCTOR
    }

    public QCardRequest(Path<? extends CardRequest> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCardRequest(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCardRequest(PathMetadata<?> metadata, PathInits inits) {
        this(CardRequest.class, metadata, inits);
    }

    public QCardRequest(Class<? extends CardRequest> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.nt.neocloud4j.core.model.persistable.QBusinessObject(type, metadata, inits);
        this.version = _super.version;
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

    
public com.nt.neocloud4j.core.model.typed.QTypeInfo typeInfo() {
        if (typeInfo == null) {
            typeInfo = new com.nt.neocloud4j.core.model.typed.QTypeInfo(forProperty("typeInfo"));
        }
        return typeInfo;
    }

	public com.nt.neocloud4j.core.model.lifecyclemanaged.QLifecycleInfo lifecycleInfo() {
        if (lifecycleInfo == null) {
            lifecycleInfo = new com.nt.neocloud4j.core.model.lifecyclemanaged.QLifecycleInfo(forProperty("lifecycleInfo"));
        }
        return lifecycleInfo;
    }

 public QBadge badge() {
        if (badge == null) {
            badge = new QBadge(forProperty("badge"));
        }
        return badge;
    }

// METHODS
}

