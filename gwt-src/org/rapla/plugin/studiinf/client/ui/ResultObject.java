package org.rapla.plugin.studiinf.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface ResultObject {

	List<Widget> getCellObjects();
	public void setNumber(int number);
	public Widget getFooterButton();
}
