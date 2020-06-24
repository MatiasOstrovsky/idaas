package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.model.LoadableModel;
import java.util.List;
import javax.xml.namespace.QName;

class UserViewTabPanel$3 extends LoadableModel<List<QName>> {
	UserViewTabPanel$3(UserViewTabPanel this$0, boolean alwaysReload) {
		super(alwaysReload);
		this.this$0 = this$0;
	}

	protected List<QName> load() {
		return UserViewTabPanel.access$100(this.this$0);
	}
}