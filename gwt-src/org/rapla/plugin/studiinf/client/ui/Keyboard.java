package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Keyboard extends FlowPanel implements ClickHandler {
	private final TextBox inputField;
	
	public Keyboard(TextBox inputField) {
		super();
		this.inputField = inputField;
					
		addStringAsRow("1|2|3|4|5|6|7|8|9|0|return");
		addStringAsRow("Q|W|E|R|T|Z|U|I|O|P|Ü");
		addStringAsRow("A|S|D|F|G|H|J|K|L|Ö|Ä");
		addStringAsRow("Y|X|C|V|B|N|M|.|-|search");
		addStringAsRow(" ");
		
	}

	@Override
	public void onClick(ClickEvent event) {
		try {
			Button key = (Button) event.getSource();
			inputField.setText(inputField.getText() + key.getText());
			char keyChar =  key.getText().charAt(0);
			
			DomEvent.fireNativeEvent(
					Document.get().createKeyUpEvent(
							false, false, false, false,keyChar)
							, inputField);

					
		} catch (Exception e) {
			
		}
			
	}
	
	public void addStringAsRow(String keys){
		FlowPanel row = new FlowPanel();
		Button button;
		row.addStyleName("keyboardRow");
		String[] keysArray = keys.split("\\|");
		for(String key : keysArray){
			if(key.length() == 1){
			button = new Button(key);
			
			button.addClickHandler(this);
			}else{
				button = handleSpecialKey(key);
				
			}
			
			button.removeStyleName("gwt-Button");
			button.addStyleName("keyboardButton");
			
			if (key.contentEquals(" ")){
				button.removeStyleName("keyboardButton");
				button.addStyleName("keyboardSpace");
			}
			
			if (key.contentEquals("return")){
				button.removeStyleName("keyboardButton");
				button.addStyleName("keyboardReturn");
			}
			
//			button.getElement().getStyle().setWidth(100/keysArray.length, Unit.PCT);
//			button.getElement().getStyle().setProperty("height", "2.6h");
//			button.getElement().getStyle().setProperty("fontSize", "1.5vh");
//			button.getElement().getStyle().setProperty("padding", "0px");
			row.add(button);
		}
		this.add(row);
		
		if(this.getChildren().size() == 1){
			row.addStyleName("keyboardRowOne");
		}
		if(this.getChildren().size() == 2){
			row.addStyleName("keyboardRowTwo");
		}
		if(this.getChildren().size() == 3){
			row.addStyleName("keyboardRowThree");
		}
		if(this.getChildren().size() == 4){
			row.addStyleName("keyboardRowFour");
		}
		if(this.getChildren().size() == 5){
			row.addStyleName("keyboardRowFive");
		}
//		else if(this.getChildren().size() <= 3){
//			row.getElement().getStyle().setMarginRight(50/keysArray.length, Unit.PCT);
//		}
		row.addStyleName("keyboardRow");
	}

	private Button handleSpecialKey(String key) {
		Button button = null;
		if(key.equals("return")){
			button = new Button("<i class='fa fa-arrow-left'></i>");
			button.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					inputField.setText(inputField.getText().substring(0, inputField.getText().length() - 1));
					DomEvent.fireNativeEvent(
							Document.get().createKeyUpEvent(
									false, false, false, false,0)
									, inputField);
				}
			});
		}else if(key.equals("search")){
			button = new Button("<i class='fa fa-search'></i>");
			button.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					DomEvent.fireNativeEvent(
							Document.get().createKeyUpEvent(
									false, false, false, false,0)
									, inputField);
				}
			});
		}
		return button;
	}

	
}
