package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.prism.query.ObjectQuery;
import com.evolveum.midpoint.web.component.search.SearchPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

class AbstractShoppingCartTabPanel$1 extends SearchPanel {
	private static final long serialVersionUID = 1L;

	AbstractShoppingCartTabPanel$1(AbstractShoppingCartTabPanel this$0, String id, IModel model,
			boolean advancedSearch) {
		super(id, model, advancedSearch);
		this.this$0 = this$0;
	}

	public void searchPerformed(ObjectQuery query, AjaxRequestTarget target) {
		this.this$0.searchPerformed(query, target);
	}
}