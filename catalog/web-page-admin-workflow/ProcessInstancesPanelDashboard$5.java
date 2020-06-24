package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.schema.constants.ObjectTypes;
import com.evolveum.midpoint.web.component.data.column.IconColumn;
import com.evolveum.midpoint.web.page.admin.workflow.dto.ProcessInstanceDto;
import com.evolveum.midpoint.web.util.ObjectTypeGuiDescriptor;
import com.evolveum.midpoint.web.util.TooltipBehavior;
import javax.xml.namespace.QName;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class ProcessInstancesPanelDashboard$5 extends IconColumn<ProcessInstanceDto> {
	ProcessInstancesPanelDashboard$5(ProcessInstancesPanelDashboard this$0, IModel x0, boolean var3) {
		super(x0);
		this.this$0 = this$0;
		this.val$object = var3;
	}

	protected IModel<String> createIconModel(IModel<ProcessInstanceDto> rowModel) {
		if (this.getObjectType(rowModel) == null) {
			return null;
		} else {
			ObjectTypeGuiDescriptor guiDescriptor = this.getObjectTypeDescriptor(rowModel);
			String icon = guiDescriptor != null ? guiDescriptor.getBlackIcon() : "silk-error";
			return new Model(icon);
		}
	}

	private ObjectTypeGuiDescriptor getObjectTypeDescriptor(IModel<ProcessInstanceDto> rowModel) {
		QName type = this.getObjectType(rowModel);
		return ObjectTypeGuiDescriptor.getDescriptor(ObjectTypes.getObjectTypeFromTypeQName(type));
	}

	private QName getObjectType(IModel<ProcessInstanceDto> rowModel) {
		return this.val$object
				? ((ProcessInstanceDto) rowModel.getObject()).getObjectType()
				: ((ProcessInstanceDto) rowModel.getObject()).getTargetType();
	}

	public void populateItem(Item<ICellPopulator<ProcessInstanceDto>> item, String componentId,
			IModel<ProcessInstanceDto> rowModel) {
		super.populateItem(item, componentId, rowModel);
		ObjectTypeGuiDescriptor guiDescriptor = this.getObjectTypeDescriptor(rowModel);
		if (guiDescriptor != null) {
			item.add(new Behavior[]{AttributeModifier.replace("title",
					this.this$0.createStringResource(guiDescriptor.getLocalizationKey(), new Object[0]))});
			item.add(new Behavior[]{new TooltipBehavior()});
		}

	}
}