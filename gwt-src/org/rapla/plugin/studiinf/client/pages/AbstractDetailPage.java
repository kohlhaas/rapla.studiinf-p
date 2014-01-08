package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwtjsonrpc.common.AsyncCallback;

public abstract class AbstractDetailPage extends AbstractPage {

	public String id ="";
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	abstract public boolean hasDefaultQrBox();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.remove(qrBox);
		if (this.hasDefaultQrBox() == true){
			qrBox = new QRBox(getHistoryKey()+"/"+getId());
			this.add(qrBox);
		}
		handleId(id);
	}

	@Override
	public void init() {
		super.init();
		qrBox.setStyleName("qrBox");
		if (this.hasDefaultQrBox() == true){
			this.add(qrBox);
		}
		

		
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		
	}

	protected void handleId(final String id){
		ServiceProvider.getResource(id, new AsyncCallback<ResourceDetail>() {
				
				@Override
				public void onSuccess(ResourceDetail arg0) {
					handleRessource(id, arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
					
				}
		});
	};
	
	abstract protected void handleRessource(String id, ResourceDetail resource);

}
