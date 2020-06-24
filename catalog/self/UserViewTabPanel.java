package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.prism.query.InOidFilter;
import com.evolveum.midpoint.prism.query.ObjectFilter;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.util.logging.Trace;
import com.evolveum.midpoint.util.logging.TraceManager;
import com.evolveum.midpoint.web.component.assignment.UserSelectionButton;
import com.evolveum.midpoint.web.page.self.UserViewTabPanel.1;
import com.evolveum.midpoint.web.page.self.UserViewTabPanel.2;
import com.evolveum.midpoint.web.page.self.UserViewTabPanel.3;
import com.evolveum.midpoint.web.page.self.UserViewTabPanel.4;
import com.evolveum.midpoint.web.page.self.UserViewTabPanel.5;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AreaCategoryType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AssignmentType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.RoleManagementConfigurationType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.UserType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

public class UserViewTabPanel extends AbstractShoppingCartTabPanel<AbstractRoleType> {
	private static final long serialVersionUID = 1L;
	private static final String DOT_CLASS = UserViewTabPanel.class.getName() + ".";
	private static final String OPERATION_LOAD_RELATION_DEFINITIONS;
	private static final Trace LOGGER;
	private static final String ID_SOURCE_USER_PANEL = "sourceUserPanel";
	private static final String ID_SOURCE_USER_BUTTON = "sourceUserButton";
	private static final String ID_SOURCE_USER_RELATIONS = "sourceUserRelations";
	private static final String ID_RELATION_LINK = "relationLink";
	private QName selectedRelation = null;

	public UserViewTabPanel(String id, RoleManagementConfigurationType roleManagementConfig) {
		super(id, roleManagementConfig);
	}

	protected void initLeftSidePanel() {
		if (this.getRoleCatalogStorage().getAssignmentsUserOwner() == null) {
			this.getRoleCatalogStorage()
					.setAssignmentsUserOwner((UserType) this.getPageBase().loadUserSelf().asObjectable());
		}

		WebMarkupContainer sourceUserPanel = new WebMarkupContainer("sourceUserPanel");
		sourceUserPanel.setOutputMarkupId(true);
		this.add(new Component[]{sourceUserPanel});
		this.initSourceUserSelectionPanel(sourceUserPanel);
		this.initRelationsPanel(sourceUserPanel);
	}

	private void initSourceUserSelectionPanel(WebMarkupContainer sourceUserPanel) {
      UserSelectionButton sourceUserButton = new 2(this, "sourceUserButton", new 1(this), false, this.createStringResource("AssignmentCatalogPanel.selectSourceUser", new Object[0]));
      sourceUserButton.setOutputMarkupId(true);
      sourceUserPanel.add(new Component[]{sourceUserButton});
   }

	private String getSourceUserSelectionButtonLabel() {
		UserType user = this.getRoleCatalogStorage().getAssignmentsUserOwner();
		return user.getOid().equals(this.getPageBase().loadUserSelf().getOid())
				? this.createStringResource("UserSelectionButton.myAssignmentsLabel", new Object[0]).getString()
				: this.createStringResource("UserSelectionButton.userAssignmentsLabel",
						new Object[]{user.getName().getOrig()}).getString();
	}

	private void initRelationsPanel(WebMarkupContainer sourceUserPanel) {
      ListView relationsPanel = new 4(this, "sourceUserRelations", new 3(this, false));
      relationsPanel.setOutputMarkupId(true);
      sourceUserPanel.add(new Component[]{relationsPanel});
   }

	private List<QName> getRelationsList() {
		List<QName> relationsList = new ArrayList();
		relationsList.add((Object) null);
		relationsList
				.addAll(WebComponentUtil.getCategoryRelationChoices(AreaCategoryType.SELF_SERVICE, this.getPageBase()));
		return relationsList;
	}

	private Component createRelationLink(String id, IModel<QName> model) {
      AjaxLink<QName> button = new 5(this, id, model, model);
      button.setOutputMarkupId(true);
      return button;
   }

	protected void appendItemsPanelStyle(WebMarkupContainer container) {
		container.add(new Behavior[]{AttributeAppender.append("class", "col-md-12")});
	}

	protected ObjectQuery createContentQuery() {
		ObjectQuery query = super.createContentQuery();
		if (this.getRoleCatalogStorage().getAssignmentsUserOwner() != null) {
			UserType assignmentsOwner = this.getRoleCatalogStorage().getAssignmentsUserOwner();
			List<String> assignmentTargetObjectOidsList = this
					.collectTargetObjectOids(assignmentsOwner.getAssignment());
			ObjectFilter oidsFilter = InOidFilter.createInOid(assignmentTargetObjectOidsList);
			query.addFilter(oidsFilter);
		}

		return query;
	}

	private List<String> collectTargetObjectOids(List<AssignmentType> assignments) {
		List<String> oidsList = new ArrayList();
		if (assignments == null) {
			return oidsList;
		} else {
			QName relation = this.getSelectedRelation();
			assignments.forEach((assignment) -> {
				if (assignment.getTargetRef() != null && assignment.getTargetRef().getOid() != null) {
					if (relation == null || relation.equals(assignment.getTargetRef().getRelation())) {
						oidsList.add(assignment.getTargetRef().getOid());
					}
				}
			});
			return oidsList;
		}
	}

	private QName getSelectedRelation() {
		return this.selectedRelation;
	}

	protected QName getQueryType() {
		return AbstractRoleType.COMPLEX_TYPE;
	}

	static {
		OPERATION_LOAD_RELATION_DEFINITIONS = DOT_CLASS + "loadRelationDefinitions";
		LOGGER = TraceManager.getTrace(UserViewTabPanel.class);
	}
}