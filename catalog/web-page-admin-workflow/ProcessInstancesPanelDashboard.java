package com.evolveum.midpoint.web.page.admin.workflow;

import com.evolveum.midpoint.gui.api.component.BasePanel;
import com.evolveum.midpoint.gui.api.model.ReadOnlyModel;
import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.web.component.data.BoxedTablePanel;
import com.evolveum.midpoint.web.component.data.column.GenericColumn;
import com.evolveum.midpoint.web.component.data.column.IconColumn;
import com.evolveum.midpoint.web.component.util.SerializableFunction;
import com.evolveum.midpoint.web.component.util.SerializableSupplier;
import com.evolveum.midpoint.web.page.admin.server.PageTaskEdit;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.1;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.2;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.3;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.4;
import com.evolveum.midpoint.web.page.admin.workflow.ProcessInstancesPanelDashboard.5;
import com.evolveum.midpoint.web.page.admin.workflow.dto.ProcessInstanceDto;
import com.evolveum.midpoint.web.session.UserProfileStorage.TableId;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jetbrains.annotations.NotNull;

public class ProcessInstancesPanelDashboard extends BasePanel {
	private static final String ID_REQUESTS_TABLE = "requestsTable";
	private ISortableDataProvider<ProcessInstanceDto, String> provider;

	public ProcessInstancesPanelDashboard(String id, ISortableDataProvider<ProcessInstanceDto, String> provider,
			TableId tableId, int pageSize) {
		super(id);
		this.provider = provider;
		this.initLayout(tableId, pageSize);
	}

	private void initLayout(TableId tableId, int pageSize) {
		BoxedTablePanel<ProcessInstanceDto> table = new BoxedTablePanel("requestsTable", this.provider,
				this.initColumns(), tableId, pageSize);
		table.setOutputMarkupId(true);
		table.setAdditionalBoxCssClasses("without-box-header-top-border");
		this.add(new Component[]{table});
	}

	public BoxedTablePanel<ProcessInstanceDto> getTablePanel() {
		return (BoxedTablePanel) this.get("requestsTable");
	}

	private List<IColumn<ProcessInstanceDto, String>> initColumns() {
		List<IColumn<ProcessInstanceDto, String>> columns = new ArrayList();
		columns.add(this.createCreatedColumn());
		columns.add(this.createTypeIconColumn(true));
		columns.add(this.createObjectNameColumn("pageProcessInstances.item.object"));
		columns.add(this.createTypeIconColumn(false));
		columns.add(this.createTargetNameColumn("pageProcessInstances.item.target"));
		columns.add(this.createStageColumn());
		columns.add(this.createOutcomeColumn());
		columns.add(this.createFinishedColumn());
		return columns;
	}

	@NotNull
	private PropertyColumn<ProcessInstanceDto, String> createFinishedColumn() {
		return new PropertyColumn(this.createStringResource("pageProcessInstances.item.finished", new Object[0]),
				"endFormatted");
	}

	@NotNull
	private PropertyColumn<ProcessInstanceDto, String> createStageColumn() {
		return new PropertyColumn(this.createStringResource("pageProcessInstances.item.stage", new Object[0]), "stage");
	}

	@NotNull
   private IColumn<ProcessInstanceDto, String> createCreatedColumn() {
      return (IColumn)(WebComponentUtil.isAuthorized(new String[]{"http://midpoint.evolveum.com/xml/ns/public/security/authorization-ui-3#tasksAll", "http://midpoint.evolveum.com/xml/ns/public/security/authorization-ui-3#task"}) ? new 1(this, this.createStringResource("WorkItemsPanel.created", new Object[0]), "name") : new GenericColumn(this.createStringResource("WorkItemsPanel.created", new Object[0]), (rowModel) -> {
         return this.createProcessNameModel(rowModel);
      }));
   }

	private IModel<String> createProcessNameModel(IModel<ProcessInstanceDto> processInstanceDtoModel) {
		return new ReadOnlyModel(() -> {
			ProcessInstanceDto processInstanceDto = (ProcessInstanceDto) processInstanceDtoModel.getObject();
			return processInstanceDto.getStartFormatted();
		});
	}

	@NotNull
   private IconColumn<ProcessInstanceDto> createOutcomeColumn() {
      return new 2(this, this.createStringResource("pageProcessInstances.item.result", new Object[0]));
   }

	private void itemDetailsPerformed(AjaxRequestTarget target, String pid) {
		PageParameters parameters = new PageParameters();
		parameters.add("pathParameter", pid);
		this.getPageBase().navigateToNext(PageTaskEdit.class, parameters);
	}

	IColumn<ProcessInstanceDto, String> createObjectNameColumn(String headerKey) {
      return new 3(this, this.createStringResource(headerKey, new Object[0]), "objectName");
   }

	IColumn<ProcessInstanceDto, String> createTargetNameColumn(String headerKey) {
      return new 4(this, this.createStringResource(headerKey, new Object[0]), "targetName");
   }

	public IColumn<ProcessInstanceDto, String> createTypeIconColumn(boolean object) {
      return new 5(this, this.createStringResource("", new Object[0]), object);
   }
}