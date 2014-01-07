package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.Tile;
import org.rapla.plugin.studiinf.client.ui.TileContainer;

import com.google.gwt.user.client.ui.Image;


public class HomePage extends AbstractPage {
	
	
	@Override
	public void init() {
		super.init();
		Image logo = new Image(IconProvider.LOGO);
		logo.setStyleName("logo");
		
		TileContainer tileContainer = new TileContainer();
		
		Tile courseBtn = new Tile(Studiinf.i18n.courses(),Navigation.course);
		Tile personBtn = new Tile(Studiinf.i18n.people(),Navigation.person);
		Tile roomBtn = new Tile(Studiinf.i18n.rooms(),Navigation.room);
		Tile poiBtn = new Tile(Studiinf.i18n.pointsOfInterest(),Navigation.poi);
		
		QRBox qrBox = new QRBox(getHistoryKey());
		
		tileContainer.add(courseBtn);
		tileContainer.add(personBtn);
		tileContainer.add(roomBtn);
		tileContainer.add(poiBtn);
		
		this.add(tileContainer);
		this.add(qrBox);
		this.add(logo);
		
		
	};
	
	@Override
	public String getTitle() {
		//return Studiinf.i18n.homeScreenTitle();
		return "";
	}

	@Override
	public String getHistoryKey() {
		return "";
	}
}
