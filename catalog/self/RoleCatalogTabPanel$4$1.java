package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.assignment.OrgCatalogItemButton;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.4;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class RoleCatalogTabPanel$4$1 extends OrgCatalogItemButton {
	private static final long serialVersionUID = 1L;

	RoleCatalogTabPanel$4$1(4 this$1, String id, IModel model) {
      super(id, model);
      this.this$1 = this$1;
   }

	protected void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
		RoleCatalogTabPanel.access$000(this.this$1.this$0, selected, target);
	}
}