package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.prism.PrismContainerDefinition;
import com.evolveum.midpoint.prism.path.ItemPath;
import com.evolveum.midpoint.prism.query.EqualFilter;
import com.evolveum.midpoint.prism.query.ObjectFilter;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.prism.query.OrFilter;
import com.evolveum.midpoint.prism.query.OrgFilter;
import com.evolveum.midpoint.prism.query.TypeFilter;
import com.evolveum.midpoint.prism.query.OrgFilter.Scope;
import com.evolveum.midpoint.web.component.assignment.AssignmentEditorDto;
import com.evolveum.midpoint.web.component.assignment.CatalogBreadcrumbPanel;
import com.evolveum.midpoint.web.component.assignment.GridViewComponent;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.page.admin.orgs.OrgTreePanel;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.1;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.2;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.3;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.4;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.5;
import com.evolveum.midpoint.web.page.self.RoleCatalogTabPanel.6;
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
	private static final String ID_BREADCRUMB_PANEL = "breadcrumbPanel";
	private String roleCatalogOid;

	public RoleCatalogTabPanel(String id, RoleManagementConfigurationType roleManagementConfig, String roleCatalogOid) {
		super(id, roleManagementConfig);
		this.roleCatalogOid = roleCatalogOid;
	}

	protected void initLeftSidePanel() {
      this.getRoleCatalogStorage().setSelectedOid(this.roleCatalogOid);
      this.add(new Component[]{this.initBreadcrumbPanel()});
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
		container.add(new Behavior[]{AttributeAppender.append("class", "col-md-12")});
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
			CatalogBreadcrumbPanel breadcrumbPanel = this.initBreadcrumbPanel();
			this.addOrReplace(new Component[]{breadcrumbPanel});
			target.add(new Component[]{this.getGridViewComponent()});
			target.add(new Component[]{breadcrumbPanel});
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
			query.addFilter(filter);
			query.addFilter(
					OrFilter.createOr(new ObjectFilter[]{this.getRequestableFilter(), this.getCatalogTypeFilter()}));
			return query;
		}
	}

	protected QName getQueryType() {
		return AbstractRoleType.COMPLEX_TYPE;
	}

	protected void initShoppingCartItemsPanel(WebMarkupContainer shoppingCartContainer) {
      GridViewComponent<ObjectDataProvider<AssignmentEditorDto, AbstractRoleType>> catalogItemsGrid = new 4(this, "shoppingCartItemsPanel", new 3(this));
      catalogItemsGrid.add(new Behavior[]{new 5(this)});
      catalogItemsGrid.setOutputMarkupId(true);
      shoppingCartContainer.add(new Component[]{catalogItemsGrid});
   }

	public CatalogBreadcrumbPanel initBreadcrumbPanel() {
      CatalogBreadcrumbPanel breadcrumbPanel = new 6(this, "breadcrumbPanel", this.getPageBase(), this.getRoleCatalogStorage());
      breadcrumbPanel.setOutputMarkupId(true);
      return breadcrumbPanel;
   }

	private EqualFilter getRequestableFilter() {
		PrismContainerDefinition userPcd = this.getPageBase().getPrismContext().getSchemaRegistry()
				.findContainerDefinitionByCompileTimeClass(RoleType.class);
		EqualFilter requestableFilter = EqualFilter.createEqual(new ItemPath(new QName[]{RoleType.F_REQUESTABLE}),
				userPcd.findPropertyDefinition(RoleType.F_REQUESTABLE), (QName) null,
				this.getPageBase().getPrismContext(), new Object[]{true});
		return requestableFilter;
	}

	private EqualFilter getCatalogTypeFilter() {
		PrismContainerDefinition userPcd = this.getPageBase().getPrismContext().getSchemaRegistry()
				.findContainerDefinitionByCompileTimeClass(OrgType.class);
		EqualFilter catalogTypeFilter = EqualFilter.createEqual(new ItemPath(new QName[]{OrgType.F_SUBTYPE}),
				userPcd.findPropertyDefinition(OrgType.F_SUBTYPE), (QName) null, this.getPageBase().getPrismContext(),
				new Object[]{"catalog"});
		return catalogTypeFilter;
	}
}