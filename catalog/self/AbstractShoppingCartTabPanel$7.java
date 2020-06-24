package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.web.component.assignment.AssignmentEditorDto;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.web.page.admin.users.dto.UserDtoStatus;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectType;
import org.apache.wicket.Component;

class AbstractShoppingCartTabPanel$7 extends ObjectDataProvider<AssignmentEditorDto, AbstractRoleType> {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$7(AbstractShoppingCartTabPanel this$0, Component x0, Class x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	public AssignmentEditorDto createDataObjectWrapper(PrismObject<AbstractRoleType> obj) {
		AssignmentEditorDto dto = AssignmentEditorDto.createDtoFromObject((ObjectType) obj.asObjectable(),
				UserDtoStatus.ADD, this.this$0.getPageBase());
		if (!this.this$0.getRoleCatalogStorage().isMultiUserRequest()) {
			dto.setAlreadyAssigned(AbstractShoppingCartTabPanel.access$200(this.this$0, obj, dto));
			dto.setDefualtAssignmentConstraints(AbstractShoppingCartTabPanel.access$300(this.this$0) == null
					? null
					: AbstractShoppingCartTabPanel.access$300(this.this$0).getDefaultAssignmentConstraints());
		}

		return dto;
	}

	public ObjectQuery getQuery() {
		return this.this$0.createContentQuery();
	}
}