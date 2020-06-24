package com.evolveum.midpoint.web.component.assignment;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

class OrgCatalogItemButton$1 extends AjaxLink {
	private static final long serialVersionUID = 1L;

	OrgCatalogItemButton$1(OrgCatalogItemButton this$0, String x0) {
		super(x0);
		this.this$0 = this$0;
	}

	public void onClick(AjaxRequestTarget ajaxRequestTarget) {
		OrgCatalogItemButton.access$000(this.this$0, (AssignmentEditorDto) this.this$0.getModelObject(),
				ajaxRequestTarget);
	}
}