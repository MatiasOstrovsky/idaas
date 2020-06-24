package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.web.component.util.VisibleEnableBehaviour;

class RoleCatalogTabPanel$5 extends VisibleEnableBehaviour {
	private static final long serialVersionUID = 1L;

	RoleCatalogTabPanel$5(RoleCatalogTabPanel this$0) {
		this.this$0 = this$0;
	}

	public boolean isVisible() {
		return this.this$0.isShoppingCartItemsPanelVisible();
	}
}