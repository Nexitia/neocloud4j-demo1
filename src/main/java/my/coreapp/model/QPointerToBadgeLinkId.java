package my.coreapp.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BeanPath;
import com.mysema.query.types.path.NumberPath;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import my.coreapp.model.Badge;
import my.coreapp.model.QBadge;
//IMPORT

public class QPointerToBadgeLinkId extends BeanPath<PointerToBadgeLinkId> {

    private static final long serialVersionUID = 1L;

    public static final QPointerToBadgeLinkId pointerId = new QPointerToBadgeLinkId("pointerId");

    public final NumberPath<Long> roleAId = createNumber("roleAId", Long.class);

    public final NumberPath<Long> roleBId = createNumber("roleBId", Long.class);

    public QPointerToBadgeLinkId(String variable) {
        super(PointerToBadgeLinkId.class, forVariable(variable));
    }

    public QPointerToBadgeLinkId(Path<? extends PointerToBadgeLinkId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointerToBadgeLinkId(PathMetadata<?> metadata) {
        super(PointerToBadgeLinkId.class, metadata);
    }

}
