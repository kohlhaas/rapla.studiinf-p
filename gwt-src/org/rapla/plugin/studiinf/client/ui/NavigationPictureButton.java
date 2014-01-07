package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

public class NavigationPictureButton extends PictureButton implements
		ClickHandler {

	private AbstractPage targetPage;
	private String targetId;
	
	public NavigationPictureButton(int number, Image img, AbstractPage targetPage) {
		super(number, img);

		this.targetPage = targetPage;
		this.addClickHandler(this);
	}
	public NavigationPictureButton(int number, Image img, AbstractPage targetPage,String targetId) {
		this(number,img,targetPage);
		this.targetId = targetId;
	}

	@Override
	public void onClick(ClickEvent event) {
		if(targetId == null){
			Navigation.goToPage(targetPage);
		}else{
			Navigation.goToPage(targetPage, targetId);
		}
	}

}
