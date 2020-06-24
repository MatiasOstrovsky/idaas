package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.web.component.data.column.IconColumn;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.2.1;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.2.2;
import com.evolveum.midpoint.web.page.admin.workflow.dto.ProcessInstanceDto;
import com.evolveum.midpoint.wf.util.ApprovalUtils;
import org.apache.wicket.model.IModel;

class ProcessInstancesPanelDashboard$2 extends IconColumn<ProcessInstanceDto> {
	ProcessInstancesPanelDashboard$2(ProcessInstancesPanelDashboard this$0, IModel x0) {
		super(x0);
		this.this$0 = this$0;
	}

	protected IModel<String> createIconModel(IModel<ProcessInstanceDto> rowModel) {
      return new 1(this, rowModel);
   }

	protected IModel<String> createTitleModel(IModel<ProcessInstanceDto> rowModel) {
      return new 2(this, rowModel);
   }

	public String getCssClass() {
		return "shrink";
	}

	private String choose(IModel<ProcessInstanceDto> rowModel, String noReply, String inProgress, String approved,
			String rejected) {
		ProcessInstanceDto dto = (ProcessInstanceDto) rowModel.getObject();
		Boolean result = ApprovalUtils.approvalBooleanValueFromUri(dto.getOutcome());
		if (result == null) {
			return dto.getEndTimestamp() != null ? noReply : inProgress;
		} else {
			return result ? approved : rejected;
		}
	}
}