package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.xml.ns._public.common.common_3.UserType;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.model.AbstractReadOnlyModel;

class UserViewTabPanel$1 extends AbstractReadOnlyModel<List<UserType>> {
	UserViewTabPanel$1(UserViewTabPanel this$0) {
		this.this$0 = this$0;
	}

	public List<UserType> getObject() {
		List<UserType> usersList = new ArrayList();
		if (this.this$0.getRoleCatalogStorage().getAssignmentsUserOwner() != null) {
			usersList.add(this.this$0.getRoleCatalogStorage().getAssignmentsUserOwner());
		}

		return usersList;
	}
}