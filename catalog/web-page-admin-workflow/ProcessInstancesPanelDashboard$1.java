package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.web.component.data.column.LinkColumn;
import com.evolveum.midpoint.web.page.admin.workflow.dto.ProcessInstanceDto;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class ProcessInstancesPanelDashboard$1 extends LinkColumn<ProcessInstanceDto> {
	ProcessInstancesPanelDashboard$1(ProcessInstancesPanelDashboard this$0, IModel x0, String x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	protected IModel createLinkModel(IModel<ProcessInstanceDto> rowModel) {
		return ProcessInstancesPanelDashboard.access$000(this.this$0, rowModel);
	}

	public void onClick(AjaxRequestTarget target, IModel<ProcessInstanceDto> rowModel) {
		ProcessInstanceDto piDto = (ProcessInstanceDto) rowModel.getObject();
		ProcessInstancesPanelDashboard.access$100(this.this$0, target, piDto.getTaskOid());
	}
}