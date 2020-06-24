package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.2;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

class ProcessInstancesPanelDashboard$2$2 extends AbstractReadOnlyModel<String> {
	ProcessInstancesPanelDashboard$2$2(2 this$1, IModel var2) {
      this.this$1 = this$1;
      this.val$rowModel = var2;
   }

	public String getObject() {
      return 2.access$200(this.this$1, this.val$rowModel, (String)null, this.this$1.this$0.createStringResource("MyRequestsPanel.inProgress", new Object[0]).getString(), this.this$1.this$0.createStringResource("MyRequestsPanel.approved", new Object[0]).getString(), this.this$1.this$0.createStringResource("MyRequestsPanel.rejected", new Object[0]).getString());
   }
}