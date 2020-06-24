package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.web.component.data.column.LinkColumn;
import com.evolveum.midpoint.web.page.admin.workflow.dto.ProcessInstanceDto;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class ProcessInstancesPanelDashboard$3 extends LinkColumn<ProcessInstanceDto> {
	ProcessInstancesPanelDashboard$3(ProcessInstancesPanelDashboard this$0, IModel x0, String x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	public void onClick(AjaxRequestTarget target, IModel<ProcessInstanceDto> rowModel) {
		ProcessInstanceDto dto = (ProcessInstanceDto) rowModel.getObject();
		WebComponentUtil.dispatchToObjectDetailsPage(dto.getObjectRef(), this.this$0.getPageBase(), false);
	}
}