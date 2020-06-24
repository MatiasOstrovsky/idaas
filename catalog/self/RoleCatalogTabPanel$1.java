package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.util.ModelServiceLocator;
import com.evolveum.midpoint.web.component.menu.cog.InlineMenuItem;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.page.admin.orgs.OrgTreePanel;
import com.evolveum.midpoint.web.session.OrgTreeStateStorage;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class RoleCatalogTabPanel$1 extends OrgTreePanel {
	private static final long serialVersionUID = 1L;

	RoleCatalogTabPanel$1(RoleCatalogTabPanel this$0, String id, IModel rootOid, boolean selectable,
			ModelServiceLocator serviceLocator, String treeTitleKey) {
		super(id, rootOid, selectable, serviceLocator, treeTitleKey);
		this.this$0 = this$0;
	}

	protected void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
		RoleCatalogTabPanel.access$000(this.this$0, selected, target);
	}

	protected List<InlineMenuItem> createTreeMenu() {
		return new ArrayList();
	}

	protected List<InlineMenuItem> createTreeChildrenMenu(OrgType org) {
		return new ArrayList();
	}

	protected OrgTreeStateStorage getOrgTreeStateStorage() {
		return this.this$0.getRoleCatalogStorage();
	}
}