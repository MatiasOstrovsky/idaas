package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.AjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes.EventPropagation;
import org.apache.wicket.model.IModel;

class AbstractShoppingCartTabPanel$5 extends AjaxButton {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$5(AbstractShoppingCartTabPanel this$0, String x0, IModel x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	public void onClick(AjaxRequestTarget ajaxRequestTarget) {
		AbstractShoppingCartTabPanel.access$000(this.this$0, ajaxRequestTarget);
	}

	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		super.updateAjaxAttributes(attributes);
		attributes.setEventPropagation(EventPropagation.BUBBLE);
	}
}