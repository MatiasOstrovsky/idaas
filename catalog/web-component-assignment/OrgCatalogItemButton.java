package com.evolveum.midpoint.web.component.assignment;

import com.evolveum.midpoint.gui.api.component.BasePanel;
import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.model.api.ModelService;
import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.util.logging.Trace;
import com.evolveum.midpoint.util.logging.TraceManager;
import com.evolveum.midpoint.web.component.assignment.OrgCatalogItemButton.1;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import java.util.Collection;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class OrgCatalogItemButton extends BasePanel<AssignmentEditorDto> {
	private static final long serialVersionUID = 1L;
	private static final String ID_ITEM_BUTTON_CONTAINER = "itemButtonContainer";
	private static final String ID_INNER = "inner";
	private static final String ID_INNER_LABEL = "innerLabel";
	private static final String ID_INNER_DESCRIPTION = "innerDescription";
	private static final String ID_TYPE_ICON = "typeIcon";
	private static final String DOT_CLASS = RoleCatalogItemButton.class.getName() + ".";
	private static final String OPERATION_LOAD_ROLE;
	private static final Trace LOGGER;

	public OrgCatalogItemButton(String id, IModel<AssignmentEditorDto> model) {
		super(id, model);
	}

	protected void onInitialize() {
		super.onInitialize();
		this.initLayout();
	}

	private void initLayout() {
      WebMarkupContainer itemButtonContainer = new WebMarkupContainer("itemButtonContainer");
      itemButtonContainer.setOutputMarkupId(true);
      itemButtonContainer.add(new Behavior[]{new AttributeAppender("class", this.getBackgroundClass((AssignmentEditorDto)this.getModelObject()))});
      this.add(new Component[]{itemButtonContainer});
      AjaxLink inner = new 1(this, "inner");
      inner.add(new Behavior[]{new AttributeAppender("title", ((AssignmentEditorDto)this.getModelObject()).getName())});
      itemButtonContainer.add(new Component[]{inner});
      Label nameLabel = new Label("innerLabel", ((AssignmentEditorDto)this.getModelObject()).getName());
      inner.add(new Component[]{nameLabel});
      Label descriptionLabel = new Label("innerDescription", ((AssignmentEditorDto)this.getModelObject()).getTargetRef() != null ? ((AssignmentEditorDto)this.getModelObject()).getTargetRef().getDescription() : "");
      descriptionLabel.setOutputMarkupId(true);
      inner.add(new Component[]{descriptionLabel});
      WebMarkupContainer icon = new WebMarkupContainer("typeIcon");
      icon.add(new Behavior[]{new AttributeAppender("class", WebComponentUtil.createDefaultBlackIcon(((AssignmentEditorDto)this.getModelObject()).getType().getQname()))});
      inner.add(new Component[]{icon});
   }

	private String getBackgroundClass(AssignmentEditorDto dto) {
		if (AssignmentEditorDtoType.ROLE.equals(dto.getType())) {
			return "object-role-bg";
		} else if (AssignmentEditorDtoType.SERVICE.equals(dto.getType())) {
			return "object-service-bg";
		} else {
			return AssignmentEditorDtoType.ORG_UNIT.equals(dto.getType()) ? "object-org-bg" : "";
		}
	}

	private void goToOrg(AssignmentEditorDto assignment, AjaxRequestTarget target) {
		this.getPageBase().getSessionStorage().getRoleCatalog().setSelectedOid(assignment.getTargetRef().getOid());

		try {
			ModelService modelService = this.getPageBase().getModelService();
			Task task = this.getPageBase().createSimpleTask(OPERATION_LOAD_ROLE);
			OperationResult result = new OperationResult(OPERATION_LOAD_ROLE);
			PrismObject<OrgType> object = modelService.getObject(OrgType.class, assignment.getTargetRef().getOid(),
					(Collection) null, task, result);
			OrgType org = (OrgType) object.asObjectable();
			SelectableBean<OrgType> orgSB = new SelectableBean(org);
			this.getPageBase().getSessionStorage().getRoleCatalog().setSelectedItem(orgSB);
			this.selectTreeItemPerformed(orgSB, target);
		} catch (Exception var9) {
			;
		}

	}

	protected void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
	}

	static {
		OPERATION_LOAD_ROLE = DOT_CLASS + "loadRole";
		LOGGER = TraceManager.getTrace(RoleCatalogItemButton.class);
	}
}