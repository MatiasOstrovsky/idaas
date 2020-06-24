package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.model.LoadableModel;
import com.evolveum.midpoint.web.component.assignment.AssignmentEditorDto;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;

class AbstractShoppingCartTabPanel$2 extends LoadableModel<ObjectDataProvider<AssignmentEditorDto, AbstractRoleType>> {
	AbstractShoppingCartTabPanel$2(AbstractShoppingCartTabPanel this$0) {
		this.this$0 = this$0;
	}

	protected ObjectDataProvider<AssignmentEditorDto, AbstractRoleType> load() {
		return AbstractShoppingCartTabPanel.access$000(this.this$0);
	}
}