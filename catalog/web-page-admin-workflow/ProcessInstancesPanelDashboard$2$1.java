package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.web.page.admin.server.dto.ApprovalOutcomeIcon;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.2;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

class ProcessInstancesPanelDashboard$2$1 extends AbstractReadOnlyModel<String> {
	ProcessInstancesPanelDashboard$2$1(2 this$1, IModel var2) {
      this.this$1 = this$1;
      this.val$rowModel = var2;
   }

	public String getObject() {
      return 2.access$200(this.this$1, this.val$rowModel, (String)null, ApprovalOutcomeIcon.IN_PROGRESS.getIcon(), ApprovalOutcomeIcon.APPROVED.getIcon(), ApprovalOutcomeIcon.REJECTED.getIcon());
   }
}