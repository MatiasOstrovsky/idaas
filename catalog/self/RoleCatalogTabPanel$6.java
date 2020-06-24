package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.page.PageBase;
import com.evolveum.midpoint.web.component.assignment.CatalogBreadcrumbPanel;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.session.RoleCatalogStorage;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import org.apache.wicket.ajax.AjaxRequestTarget;

class RoleCatalogTabPanel$6 extends CatalogBreadcrumbPanel {
	RoleCatalogTabPanel$6(RoleCatalogTabPanel this$0, String id, PageBase pageBase,
			RoleCatalogStorage roleCatalogStorage) {
		super(id, pageBase, roleCatalogStorage);
		this.this$0 = this$0;
	}

	protected void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
		RoleCatalogTabPanel.access$000(this.this$0, selected, target);
	}
}