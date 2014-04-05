package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

import com.google.gwt.user.client.Timer;

public class ResetTimer extends Timer{

	@Override
	public void run() {
		System.out.println("Reset Page");
        Navigation.goToPage(Navigation.homePage);
		
	}

}
