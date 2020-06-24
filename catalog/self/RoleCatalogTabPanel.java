package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.prism.query.ObjectFilter;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.prism.query.OrFilter;
import com.evolveum.midpoint.prism.query.OrgFilter;
import com.evolveum.midpoint.prism.query.TypeFilter;
import com.evolveum.midpoint.prism.query.OrgFilter.Scope;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.page.admin.orgs.OrgTreePanel;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.1;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.2;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.RoleManagementConfigurationType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.RoleType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ServiceType;
import javax.xml.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

public class RoleCatalogTabPanel extends AbstractShoppingCartTabPanel<AbstractRoleType> {
	private static final long serialVersionUID = 1L;
	private static final String ID_TREE_PANEL_CONTAINER = "treePanelContainer";
	private static final String ID_TREE_PANEL = "treePanel";
	private String roleCatalogOid;

	public RoleCatalogTabPanel(String id, RoleManagementConfigurationType roleManagementConfig, String roleCatalogOid) {
		super(id, roleManagementConfig);
		this.roleCatalogOid = roleCatalogOid;
	}

	protected void initLeftSidePanel() {
      this.getRoleCatalogStorage().setSelectedOid(this.roleCatalogOid);
      WebMarkupContainer treePanelContainer = new WebMarkupContainer("treePanelContainer");
      treePanelContainer.setOutputMarkupId(true);
      this.add(new Component[]{treePanelContainer});
      OrgTreePanel treePanel = new 1(this, "treePanel", Model.of(this.roleCatalogOid), false, this.getPageBase(), "AssignmentShoppingCartPanel.treeTitle");
      treePanel.add(new Behavior[]{new 2(this)});
      treePanel.setOutputMarkupId(true);
      treePanelContainer.add(new Component[]{treePanel});
   }

	protected boolean isShoppingCartItemsPanelVisible() {
		return this.isRootOrgExists();
	}

	protected void appendItemsPanelStyle(WebMarkupContainer container) {
		container.add(new Behavior[]{AttributeAppender.append("class", "col-md-9")});
	}

	private boolean isRootOrgExists() {
		OrgTreePanel treePanel = this.getOrgTreePanel();
		return treePanel.getSelected() != null && treePanel.getSelected().getValue() != null;
	}

	private OrgTreePanel getOrgTreePanel() {
		return (OrgTreePanel) this.get("treePanelContainer").get("treePanel");
	}

	private void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
		OrgType selectedOrg = (OrgType) selected.getValue();
		if (selectedOrg != null) {
			this.getRoleCatalogStorage().setSelectedOid(selectedOrg.getOid());
			target.add(new Component[]{this.getGridViewComponent()});
		}
	}

	protected ObjectQuery createContentQuery() {
		ObjectQuery query = super.createContentQuery();
		String oid = this.getRoleCatalogStorage().getSelectedOid();
		if (StringUtils.isEmpty(oid)) {
			return query;
		} else {
			ObjectFilter filter = OrgFilter.createOrg(oid, Scope.ONE_LEVEL);
			TypeFilter roleTypeFilter = TypeFilter.createType(RoleType.COMPLEX_TYPE, filter);
			TypeFilter serviceTypeFilter = TypeFilter.createType(ServiceType.COMPLEX_TYPE, filter);
			query.addFilter(OrFilter.createOr(new ObjectFilter[]{roleTypeFilter, serviceTypeFilter}));
			return query;
		}
	}

	protected QName getQueryType() {
		return AbstractRoleType.COMPLEX_TYPE;
	}
}