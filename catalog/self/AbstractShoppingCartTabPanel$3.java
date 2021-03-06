package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.assignment.AssignmentEditorDto;
import com.evolveum.midpoint.web.component.assignment.GridViewComponent;
import com.evolveum.midpoint.web.component.data.ObjectDataProvider;
import com.evolveum.midpoint.web.page.self.AbstractShoppingCartTabPanel.3.1;
import com.evolveum.midpoint.xml.ns._public.common.common_3.AbstractRoleType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

class AbstractShoppingCartTabPanel$3
		extends
			GridViewComponent<ObjectDataProvider<AssignmentEditorDto, AbstractRoleType>> {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$3(AbstractShoppingCartTabPanel this$0, String x0, IModel x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	protected void populateItem(Item item) {
      item.add(new Component[]{new 1(this, getCellItemId(), item.getModel())});
   }
}