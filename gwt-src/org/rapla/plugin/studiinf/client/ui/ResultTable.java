package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class ResultTable extends FlexTable {

	private double size = 0.5;
	private List<ResultObject> results = new LinkedList<ResultObject>();
	private int columns;
	private int maxRows;
	private final AccessibilityRow accessibilityRow;
	private int page;
	private NavButton backButton = new NavButton(IconProvider.Up,FontIcon.Position.BOTH,Studiinf.i18n.previous(),null,null);
	private NavButton nextButton = new NavButton(IconProvider.Down,FontIcon.Position.BOTH,Studiinf.i18n.next(),null,null);
	private NavButton backButtonBottom = new NavButton(IconProvider.Previous,null,null,null);
	private NavButton nextButtonBottom = new NavButton(IconProvider.Next,null,null,null);

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page > 0 && page < getMaxPages()){
			this.page = page;
		
		}else if(page > 0){
			this.page = getMaxPages();
			
		}else{
			this.page = 0;
		}
		refresh();
			
	}

	public void nextPage(){
		setPage(getPage() + 1); 
	}

	public void previousPage(){
		setPage(getPage() - 1); 
	}

	/**
	 * 
	 */
	public int getMaxPages() {
		return (int) Math.ceil(results.size() / (columns*maxRows));
	}

	public boolean hasPages(){
		return getMaxPages() > 1;
	}
	public boolean hasNextPage(){
		return hasPages() && getPage() < getMaxPages();
	}
	public boolean hasPreviousPage(){
		return hasPages() && getPage() > 0;
	}

	public ResultTable(AccessibilityRow accessibilityRow,int columns,int maxRows) {
	this.columns = columns;	
	this.maxRows = maxRows;
	this.accessibilityRow = accessibilityRow;
	this.page = 0;
	
	this.backButton.setSize(size);
	/*this.backButton.getElement().getStyle().setPosition(Position.ABSOLUTE);
	this.backButton.getElement().getStyle().setBottom(-5.0, Unit.EM);
	this.backButton.getElement().getStyle().setLeft(0.0, Unit.EM);
	this.backButton.getElement().getStyle().setMarginRight(0.5,Unit.EM);*/
	this.backButton.getElement().getStyle().setWidth(100, Unit.PCT);
	this.nextButton.setSize(size);
	/*this.nextButton.getElement().getStyle().setPosition(Position.ABSOLUTE);
	this.nextButton.getElement().getStyle().setBottom(-5.0, Unit.EM);
	this.nextButton.getElement().getStyle().setLeft(50, Unit.PCT);
	this.nextButton.getElement().getStyle().setMarginLeft(0.5,Unit.EM);*/
	this.nextButton.getElement().getStyle().setWidth(100, Unit.PCT);
	this.backButton.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
		}
	});
	this.nextButton.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
		}
	});
	
	this.backButtonBottom.addStyleName("backButtonBottom");
	this.nextButtonBottom.addStyleName("nextButtonBottom");
	this.backButtonBottom.setSize(size);
	this.nextButtonBottom.setSize(size);
	this.backButtonBottom.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
		}
	});
	this.nextButtonBottom.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
		}
	});
	}
	
	public void addResult(ResultObject result){
		results.add(result);
		result.setNumber(results.indexOf(result) + 1);
	}
	
	public boolean removeResult(ResultObject result) {
		boolean removed = results.remove(result);
		return removed;
	}
	
	public void clearResults() {
		results.clear();
		refresh();
	}
	
	public void refresh(){
		clear();
		accessibilityRow.clear();
		int count = 0;
		backButton.setEnabled(false);
		
		getFlexCellFormatter().setColSpan(0, 0, 2);
		setWidget(0, 0, backButton);
		
		for (ResultObject result : results){
			for(Widget cell : result.getCellObjects()){
				if(Math.floor((count / columns))-(page*maxRows)>= maxRows){
					break;
				}
				if(count >= page*columns*maxRows){
					try {
						NavButton btn = (NavButton) cell;
						btn.setSize(size);
					} catch (Exception e) {
					}
					getFlexCellFormatter().setColSpan((int)(count / columns)+1-(page*maxRows), (int) count % columns, 1);
					setWidget((int)(count / columns)+1-(page*maxRows), (int) count % columns, cell);
				}
				count++;
			}
			if(count >= page*columns*maxRows+1){
				NavButton fbut = result.getFooterButton();
				fbut.setSize(0.5);
				accessibilityRow.add(fbut);
			}
			if(Math.floor((count / columns))-(page*maxRows)>= maxRows){
				break;
			}
		}
		
		while(count % columns != 0){
			
			setWidget((int)(count / columns)+1-(page*maxRows), (int) count % columns, new FlowPanel());
			count++;
		}
		getFlexCellFormatter().setColSpan((int)((count / columns))+1-(page*maxRows), 0, 2);
		setWidget((int)((count / columns))+1-(page*maxRows), 0, nextButton);
			backButton.setEnabled(hasPreviousPage());
			nextButton.setEnabled(hasNextPage());
			backButtonBottom.setEnabled(hasPreviousPage());
			nextButtonBottom.setEnabled(hasNextPage());
	}
	
	public List<NavButton> getButtonsBottom(){
		List<NavButton> buttons = new LinkedList<NavButton>();
		buttons.add(backButtonBottom);
		buttons.add(nextButtonBottom);
		return buttons;
	}
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		setPage(0);
	}
}
