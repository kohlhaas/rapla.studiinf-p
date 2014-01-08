package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;

import com.google.gwt.user.client.Window;

public class PoiSearch extends AbstractSearch {

	public PoiSearch(String searchTerm, PoiSearchPage page) {
		super(searchTerm, page);
	}


	@Override
	protected NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources) {
		NoDuplicatesList<ResourceDescriptor> pOIMatched = new NoDuplicatesList<ResourceDescriptor>();
			pOIMatched.addAll(resources);
			Window.alert(pOIMatched.toString());
		return pOIMatched;
	}


	@Override
	protected String getResourceType() {
		return "sonstiges";
	}

}
