package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.web.component.assignment.AssignmentsUtil;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.web.component.util.VisibleEnableBehaviour;

class AbstractShoppingCartTabPanel$6 extends VisibleEnableBehaviour {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$6(AbstractShoppingCartTabPanel this$0) {
		this.this$0 = this$0;
	}

	public boolean isVisible() {
		ObjectDataProvider provider = this.this$0.getGridViewComponent().getProvider();
		return provider != null && provider.size() > 0L;
	}

	public boolean isEnabled() {
		int assignmentsLimit = AssignmentsUtil.loadAssignmentsLimit(
				new OperationResult(AbstractShoppingCartTabPanel.access$200()), this.this$0.getPageBase());
		return !AssignmentsUtil.isShoppingCartAssignmentsLimitReached(assignmentsLimit, this.this$0.getPageBase());
	}
}