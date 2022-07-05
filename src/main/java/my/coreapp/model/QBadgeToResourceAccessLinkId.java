package my.coreapp.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BeanPath;
import com.mysema.query.types.path.NumberPath;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import my.coreapp.model.ResourceAccess;
import my.coreapp.model.QResourceAccess;
//IMPORT

public class QBadgeToResourceAccessLinkId extends BeanPath<BadgeToResourceAccessLinkId> {

    private static final long serialVersionUID = 1L;

    public static final QBadgeToResourceAccessLinkId badgeId = new QBadgeToResourceAccessLinkId("badgeId");

    public final NumberPath<Long> roleAId = createNumber("roleAId", Long.class);

    public final NumberPath<Long> roleBId = createNumber("roleBId", Long.class);

    public QBadgeToResourceAccessLinkId(String variable) {
        super(BadgeToResourceAccessLinkId.class, forVariable(variable));
    }

    public QBadgeToResourceAccessLinkId(Path<? extends BadgeToResourceAccessLinkId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBadgeToResourceAccessLinkId(PathMetadata<?> metadata) {
        super(BadgeToResourceAccessLinkId.class, metadata);
    }

}
