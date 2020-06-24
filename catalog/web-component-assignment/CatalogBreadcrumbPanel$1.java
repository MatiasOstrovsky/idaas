package com.evolveum.midpoint.web.component.assignment;

import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

class CatalogBreadcrumbPanel$1 extends AjaxLink {
	private static final long serialVersionUID = 1L;

	CatalogBreadcrumbPanel$1(CatalogBreadcrumbPanel this$0, String x0, OrgType var3) {
		super(x0);
		this.this$0 = this$0;
		this.val$org = var3;
	}

	public void onClick(AjaxRequestTarget ajaxRequestTarget) {
		CatalogBreadcrumbPanel.access$000(this.this$0).getSessionStorage().getRoleCatalog()
				.setSelectedOid(this.val$org.getOid());

		try {
			SelectableBean<OrgType> orgSB = new SelectableBean(this.val$org);
			CatalogBreadcrumbPanel.access$000(this.this$0).getSessionStorage().getRoleCatalog().setSelectedItem(orgSB);
			this.this$0.selectTreeItemPerformed(orgSB, ajaxRequestTarget);
		} catch (Exception var3) {
			;
		}

	}
}