package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.assignment.RoleCatalogItemButton;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.4;
import javax.xml.namespace.QName;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class RoleCatalogTabPanel$4$2 extends RoleCatalogItemButton {
	private static final long serialVersionUID = 1L;

	RoleCatalogTabPanel$4$2(4 this$1, String x0, IModel x1) {
      super(x0, x1);
      this.this$1 = this$1;
   }

	protected void assignmentAddedToShoppingCartPerformed(AjaxRequestTarget target) {
		this.this$1.this$0.assignmentAddedToShoppingCartPerformed(target);
	}

	protected QName getNewAssignmentRelation() {
		return this.this$1.this$0.getNewAssignmentRelation();
	}
}