package com.base.designpattern.listener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;


public class ListenerPattern2 {

	public static void main(String[] args) {
		Kid kid = new Kid("xiaoming ");
		kid.addListener(new WashListener());
		kid.eat();

	}

}

class Kid{
	private String name ;
	private List<Listener> listenerlist;
	
	public Kid(String name) {
		super();
		this.name = name;
		this.listenerlist = new ArrayList<Listener>();
	}
	
	public void eat() {
		for(Listener listener : listenerlist) {
			if(listener instanceof WashListener) {
				WashListener washListener  = (WashListener) listener;
				washListener.invoke(new WashingEvent(this,"wash hands"));
			}
		}
		System.out.println(name + "wash");
	}
	
	public void  addListener(Listener l) {
		listenerlist.add(l);
	}
	
}


class Event extends EventObject{

	private static final long serialVersionUID = 1L;

	public Event(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
}


class WashingEvent extends Event{

	private static final long serialVersionUID = 1L;

	public WashingEvent(Object source) {
		super(source);
	}

	public WashingEvent(Object source, String eventName) {
		super(source);
		this.eventName = eventName;
	}

	private String eventName;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}


interface Listener extends EventListener{
	public void invoke(Event event);
}

class WashListener implements Listener{

	@Override
	public void invoke(Event event) {
		WashingEvent event2  = (WashingEvent) event;
		System.out.println(event2.getEventName());
		
	}
	
}
