package com.evolveum.midpoint.web.component.assignment;

import com.evolveum.midpoint.gui.api.component.BasePanel;
import com.evolveum.midpoint.gui.api.page.PageBase;
import com.evolveum.midpoint.model.api.ModelService;
import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.schema.result.OperationResult;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.web.component.assignment.CatalogBreadcrumbPanel.1;
import com.evolveum.midpoint.web.component.util.SelectableBean;
import com.evolveum.midpoint.web.session.RoleCatalogStorage;
import com.evolveum.midpoint.xml.ns._public.common.common_3.ObjectReferenceType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.OrgType;
import java.util.Collection;
import java.util.Iterator;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;

public class CatalogBreadcrumbPanel extends BasePanel {
	private static final String ID_BREADCRUMB_LIST = "breadcrumbList";
	private static final String ID_BREADCRUMB_ITEM = "breadcrumbItem";
	private static final String ID_BREADCRUMB_DESCRIPTION = "breadcrumbDescription";
	private static final String DOT_CLASS = CatalogBreadcrumbPanel.class.getName() + ".";
	private static final String OPERATION_LOAD_ROLE;
	private PageBase pageBase;
	private RoleCatalogStorage roleCatalogStorage;

	public CatalogBreadcrumbPanel(String id, PageBase pageBase, RoleCatalogStorage roleCatalogStorage) {
		super(id);
		this.pageBase = pageBase;
		this.roleCatalogStorage = roleCatalogStorage;
	}

	protected void onInitialize() {
		super.onInitialize();
		this.initLayout();
	}

	private void initLayout() {
		RepeatingView breadcrumbList = new RepeatingView("breadcrumbList");

		try {
			ModelService modelService = this.pageBase.getModelService();
			Task task = this.pageBase.createSimpleTask(OPERATION_LOAD_ROLE);
			OperationResult result = new OperationResult(OPERATION_LOAD_ROLE);
			String selectedOid = this.roleCatalogStorage.getSelectedOid();
			PrismObject<OrgType> object = modelService.getObject(OrgType.class, selectedOid, (Collection) null, task,
					result);
			OrgType org = (OrgType) object.asObjectable();
			this.addOrgToList(breadcrumbList, org, true);
		} catch (Exception var8) {
			;
		}

		this.add(new Component[]{breadcrumbList});
	}

	private void addOrgToList(RepeatingView breadcrumbList, OrgType org, Boolean first) {
      Iterator var4 = org.getParentOrgRef().iterator();

      while(var4.hasNext()) {
         ObjectReferenceType parentRef = (ObjectReferenceType)var4.next();

         try {
            ModelService modelService = this.pageBase.getModelService();
            Task task = this.pageBase.createSimpleTask(OPERATION_LOAD_ROLE);
            OperationResult result = new OperationResult(OPERATION_LOAD_ROLE);
            PrismObject<OrgType> object = modelService.getObject(OrgType.class, parentRef.getOid(), (Collection)null, task, result);
            OrgType parent = (OrgType)object.asObjectable();
            if (parent.getSubtype().contains("catalog")) {
               this.addOrgToList(breadcrumbList, parent, false);
               break;
            }
         } catch (Exception var11) {
            ;
         }
      }

      WebMarkupContainer breadcrumbContainer = new WebMarkupContainer(breadcrumbList.newChildId());
      breadcrumbList.add(new Component[]{breadcrumbContainer});
      AjaxLink breadcrumbLink = new 1(this, "breadcrumbItem", org);
      breadcrumbContainer.add(new Component[]{breadcrumbLink});
      Label breadcrumbItemContainer = new Label("breadcrumbDescription", org.getName());
      if (first) {
         breadcrumbItemContainer.add(new Behavior[]{new AttributeAppender("class", "active")});
      }

      breadcrumbLink.add(new Component[]{breadcrumbItemContainer});
   }

	protected void selectTreeItemPerformed(SelectableBean<OrgType> selected, AjaxRequestTarget target) {
	}

	static {
		OPERATION_LOAD_ROLE = DOT_CLASS + "loadOrg";
	}
}