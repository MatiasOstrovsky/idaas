package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.assignment.UserSelectionButton;
import com.evolveum.midpoint.xml.ns._public.common.common_3.UserType;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

class UserViewTabPanel$2 extends UserSelectionButton {
	private static final long serialVersionUID = 1L;

	UserViewTabPanel$2(UserViewTabPanel this$0, String id, IModel selectedUsersListModel, boolean isMultiSelection,
			StringResourceModel titleModel) {
		super(id, selectedUsersListModel, isMultiSelection, titleModel);
		this.this$0 = this$0;
	}

	protected void singleUserSelectionPerformed(AjaxRequestTarget target, UserType user) {
		super.singleUserSelectionPerformed(target, user);
		this.this$0.getRoleCatalogStorage().setAssignmentsUserOwner(user);
		target.add(new Component[]{this.this$0});
	}

	protected String getUserButtonLabel() {
		return UserViewTabPanel.access$000(this.this$0);
	}

	protected boolean isDeleteButtonVisible() {
		return false;
	}
}