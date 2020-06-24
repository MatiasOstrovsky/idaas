package com.evolveum.midpoint.web.page.self;

import javax.xml.namespace.QName;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

class UserViewTabPanel$4 extends ListView<QName> {
	private static final long serialVersionUID = 1L;

	UserViewTabPanel$4(UserViewTabPanel this$0, String x0, IModel x1) {
		super(x0, x1);
		this.this$0 = this$0;
	}

	protected void populateItem(ListItem<QName> item) {
		item.add(new Component[]{UserViewTabPanel.access$200(this.this$0, "relationLink", item.getModel())});
	}
}