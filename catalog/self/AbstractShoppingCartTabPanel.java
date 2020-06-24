package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.component.BasePanel;
import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.prism.query.ObjectFilter;
import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.prism.query.TypeFilter;
import com.evolveum.midpoint.schema.constants.ObjectTypes;
import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.schema.util.ObjectQueryUtil;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.util.logging.Trace;
import com.evolveum.midpoint.util.logging.TraceManager;
import com.evolveum.midpoint.web.component.AjaxButton;
import com.evolveum.midpoint.web.component.assignment.AssignmentEditorDto;
import com.evolveum.midpoint.web.component.assignment.AssignmentsUtil;
import com.evolveum.midpoint.web.component.assignment.GridViewComponent;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.web.component.search.Search;
import com.evolveum.midpoint.web.component.search.SearchFactory;
import com.evolveum.midpoint.web.component.search.SearchPanel;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.1;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.2;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.3;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.4;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.5;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.6;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.7;
import com.evolveum.midpoint.web.session.RoleCatalogStorage;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AssignmentType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.RoleManagementConfigurationType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.UserType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

public abstract class AbstractShoppingCartTabPanel<R extends AbstractRoleType> extends BasePanel {
	private static final long serialVersionUID = 1L;
	private static final String ID_SHOPPING_CART_CONTAINER = "shoppingCartContainer";
	private static final String ID_SHOPPING_CART_ITEMS_PANEL = "shoppingCartItemsPanel";
	private static final String ID_SEARCH_FORM = "searchForm";
	private static final String ID_SEARCH = "search";
	private static final String ID_ADD_ALL_BUTTON = "addAllButton";
	private static final String DOT_CLASS = AbstractShoppingCartTabPanel.class.getName() + ".";
	private static final String OPERATION_LOAD_ASSIGNABLE_ROLES;
	private static final String OPERATION_LOAD_ASSIGNMENTS_LIMIT;
	private static final Trace LOGGER;
	private RoleManagementConfigurationType roleManagementConfig;

	public AbstractShoppingCartTabPanel(String id, RoleManagementConfigurationType roleManagementConfig) {
		super(id);
		this.roleManagementConfig = roleManagementConfig;
	}

	protected void onInitialize() {
		super.onInitialize();
		this.initLayout();
	}

	private void initLayout() {
		this.setOutputMarkupId(true);
		this.initLeftSidePanel();
		WebMarkupContainer shoppingCartContainer = new WebMarkupContainer("shoppingCartContainer");
		shoppingCartContainer.setOutputMarkupId(true);
		this.appendItemsPanelStyle(shoppingCartContainer);
		this.add(new Component[]{shoppingCartContainer});
		this.initSearchPanel(shoppingCartContainer);
		this.initShoppingCartItemsPanel(shoppingCartContainer);
		this.initAddAllButton(shoppingCartContainer);
	}

	protected void initLeftSidePanel() {
	}

	private void initSearchPanel(WebMarkupContainer shoppingCartContainer) {
      Form searchForm = new com.evolveum.midpoint.web.component.form.Form("searchForm");
      searchForm.setOutputMarkupId(true);
      SearchPanel search = new 1(this, "search", Model.of(this.getRoleCatalogStorage().getSearch() != null ? this.getRoleCatalogStorage().getSearch() : SearchFactory.createSearch(WebComponentUtil.qnameToClass(this.getPageBase().getPrismContext(), this.getQueryType()), this.getPageBase())), false);
      searchForm.add(new Component[]{search});
      shoppingCartContainer.add(new Component[]{searchForm});
   }

	protected void searchPerformed(ObjectQuery query, AjaxRequestTarget target) {
		this.getRoleCatalogStorage().setSearch((Search) this.getSearchPanel().getModelObject());
		target.add(new Component[]{this});
	}

	private void initShoppingCartItemsPanel(WebMarkupContainer shoppingCartContainer) {
      GridViewComponent<ObjectDataProvider<AssignmentEditorDto, AbstractRoleType>> catalogItemsGrid = new 3(this, "shoppingCartItemsPanel", new 2(this));
      catalogItemsGrid.add(new Behavior[]{new 4(this)});
      catalogItemsGrid.setOutputMarkupId(true);
      shoppingCartContainer.add(new Component[]{catalogItemsGrid});
   }

	private void initAddAllButton(WebMarkupContainer shoppingCartContainer) {
      AjaxButton addAllButton = new 5(this, "addAllButton", this.createStringResource("AbstractShoppingCartTabPanel.addAllButton", new Object[0]));
      addAllButton.add(new Behavior[]{new 6(this)});
      addAllButton.add(new Behavior[]{AttributeAppender.append("title", AssignmentsUtil.getShoppingCartAssignmentsLimitReachedTitleModel(this.getPageBase()))});
      shoppingCartContainer.add(new Component[]{addAllButton});
   }

	private void addAllAssignmentsPerformed(AjaxRequestTarget target) {
		List<AssignmentEditorDto> availableProviderData = this.getGridViewComponent().getProvider().getAvailableData();
		if (availableProviderData != null) {
			int assignmentsLimit = AssignmentsUtil
					.loadAssignmentsLimit(new OperationResult(OPERATION_LOAD_ASSIGNMENTS_LIMIT), this.getPageBase());
			int addedAssignmentsCount = availableProviderData.size()
					+ this.getRoleCatalogStorage().getAssignmentShoppingCart().size();
			if (assignmentsLimit >= 0 && addedAssignmentsCount > assignmentsLimit) {
				this.warn(this.createStringResource("AssignmentPanel.assignmentsLimitReachedWarning",
						new Object[]{assignmentsLimit}).getString());
				target.add(new Component[]{this.getPageBase().getFeedbackPanel()});
				return;
			}

			availableProviderData.forEach((newAssignment) -> {
				AssignmentEditorDto assignmentToAdd = newAssignment.clone();
				assignmentToAdd.getTargetRef().setRelation(this.getNewAssignmentRelation());
				this.getRoleCatalogStorage().getAssignmentShoppingCart().add(assignmentToAdd);
			});
			target.add(new Component[]{this});
			this.assignmentAddedToShoppingCartPerformed(target);
		}

	}

	private ObjectDataProvider<AssignmentEditorDto, AbstractRoleType> getTabPanelProvider() {
      ObjectDataProvider provider = new 7(this, this, AbstractRoleType.class);
      return provider;
   }

	private boolean isAlreadyAssigned(PrismObject<AbstractRoleType> obj, AssignmentEditorDto assignmentDto) {
		UserType user = this.getTargetUser();
		if (user != null && user.getAssignment() != null) {
			boolean isAssigned = false;
			List<QName> assignedRelationsList = new ArrayList();
			Iterator var6 = user.getAssignment().iterator();

			while (var6.hasNext()) {
				AssignmentType assignment = (AssignmentType) var6.next();
				if (assignment.getTargetRef() != null && assignment.getTargetRef().getOid().equals(obj.getOid())) {
					isAssigned = true;
					assignedRelationsList.add(assignment.getTargetRef().getRelation());
				}
			}

			assignmentDto.setAssignedRelationsList(assignedRelationsList);
			return isAssigned;
		} else {
			return false;
		}
	}

	protected boolean isShoppingCartItemsPanelVisible() {
		return true;
	}

	protected void appendItemsPanelStyle(WebMarkupContainer container) {
		container.add(new Behavior[]{AttributeAppender.append("class", "col-md-12")});
	}

	protected ObjectQuery createContentQuery() {
		ObjectQuery memberQuery = new ObjectQuery();
		memberQuery.addFilter(this.getAssignableRolesFilter());
		if (this.getQueryType() != null && !AbstractRoleType.COMPLEX_TYPE.equals(this.getQueryType())) {
			ObjectFilter typeFilter = ObjectQueryUtil.filterAnd(
					TypeFilter.createType(this.getQueryType(), (ObjectFilter) null), memberQuery.getFilter());
			memberQuery.addFilter(typeFilter);
		}

		SearchPanel searchPanel = this.getSearchPanel();
		ObjectQuery searchQuery = ((Search) searchPanel.getModelObject())
				.createObjectQuery(this.getPageBase().getPrismContext());
		if (searchQuery != null && searchQuery.getFilter() != null) {
			memberQuery.addFilter(searchQuery.getFilter());
		}

		return memberQuery;
	}

	private SearchPanel getSearchPanel() {
		return (SearchPanel) this
				.get(this.createComponentPath(new String[]{"shoppingCartContainer", "searchForm", "search"}));
	}

	private ObjectFilter getAssignableRolesFilter() {
		Task task = this.getPageBase().createSimpleTask(OPERATION_LOAD_ASSIGNABLE_ROLES);
		OperationResult result = task.getResult();
		return WebComponentUtil.getAssignableRolesFilter(this.getTargetUser().asPrismObject(),
				ObjectTypes.getObjectTypeClass(this.getQueryType()), result, task, this.getPageBase());
	}

	protected abstract QName getQueryType();

	private UserType getTargetUser() {
		return this.getRoleCatalogStorage().isSelfRequest()
				? (UserType) this.getPageBase().loadUserSelf().asObjectable()
				: (UserType) this.getRoleCatalogStorage().getTargetUserList().get(0);
	}

	protected void assignmentAddedToShoppingCartPerformed(AjaxRequestTarget target) {
	}

	protected QName getNewAssignmentRelation() {
		return WebComponentUtil.getDefaultRelationOrFail();
	}

	protected RoleCatalogStorage getRoleCatalogStorage() {
		return this.getPageBase().getSessionStorage().getRoleCatalog();
	}

	protected GridViewComponent getGridViewComponent() {
		return (GridViewComponent) this
				.get(this.createComponentPath(new String[]{"shoppingCartContainer", "shoppingCartItemsPanel"}));
	}

	static {
		OPERATION_LOAD_ASSIGNABLE_ROLES = DOT_CLASS + "loadAssignableRoles";
		OPERATION_LOAD_ASSIGNMENTS_LIMIT = DOT_CLASS + "loadAssignmentsLimit";
		LOGGER = TraceManager.getTrace(AbstractShoppingCartTabPanel.class);
	}
}