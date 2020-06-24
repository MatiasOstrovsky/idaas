package com.evolveum.midpoint.web.page.self;

import com.evolveum.midpoint.gui.api.util.WebComponentUtil;
import com.evolveum.midpoint.xml.ns._public.common.common_3.DisplayType;
import com.evolveum.midpoint.xml.ns._public.common.common_3.RelationDefinitionType;
import javax.xml.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class UserViewTabPanel$5 extends AjaxLink<QName> {
	UserViewTabPanel$5(UserViewTabPanel this$0, String x0, IModel x1, IModel var4) {
		super(x0, x1);
		this.this$0 = this$0;
		this.val$model = var4;
	}

	public IModel<String> getBody() {
		QName relation = (QName) this.val$model.getObject();
		if (relation == null) {
			return this.this$0.createStringResource("RelationTypes.ANY", new Object[0]);
		} else {
			RelationDefinitionType def = WebComponentUtil.getRelationDefinition((QName) this.val$model.getObject());
			if (def != null) {
				DisplayType display = def.getDisplay();
				if (display != null) {
					String label = display.getLabel();
					if (StringUtils.isNotEmpty(label)) {
						return this.this$0.getPageBase().createStringResource(label, new Object[0]);
					}
				}
			}

			return Model.of(((QName) this.val$model.getObject()).getLocalPart());
		}
	}

	public void onClick(AjaxRequestTarget target) {
		UserViewTabPanel.access$302(this.this$0, (QName) this.val$model.getObject());
		target.add(new Component[]{this.this$0});
	}

	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		QName relation = (QName) this.val$model.getObject();
		if ((relation != null || UserViewTabPanel.access$300(this.this$0) != null)
				&& (relation == null || !relation.equals(UserViewTabPanel.access$300(this.this$0)))) {
			tag.put("class", "list-group-item");
		} else {
			tag.put("class", "list-group-item active");
		}

	}
}