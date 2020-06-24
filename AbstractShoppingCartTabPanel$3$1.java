package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.assignment.AssignmentsUtil;
import com.evolveum.midpoint.web.component.assignment.RoleCatalogItemButton;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.3;
import javax.xml.namespace.QName;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class AbstractShoppingCartTabPanel$3$1 extends RoleCatalogItemButton {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$3$1(3 this$1, String id, IModel model) {
      super(id, model);
      this.this$1 = this$1;
   }

	protected void assignmentAddedToShoppingCartPerformed(AjaxRequestTarget target) {
		int assignmentsLimit = this.this$1.this$0.getRoleCatalogStorage().getAssignmentRequestLimit();
		if (AssignmentsUtil.isShoppingCartAssignmentsLimitReached(assignmentsLimit, this.this$1.this$0.getPageBase())) {
			target.add(new Component[]{this.this$1.this$0});
		}

		this.this$1.this$0.assignmentAddedToShoppingCartPerformed(target);
	}

	protected QName getNewAssignmentRelation() {
		return this.this$1.this$0.getNewAssignmentRelation();
	}
}