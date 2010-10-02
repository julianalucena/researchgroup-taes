package br.ufpe.cin.in980.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProperties {

	private ResourceBundle captions;

	public MessageProperties() {
		// #ifdef lang_pt
		this.captions = ResourceBundle.getBundle("Messages", new Locale("pt",
				"BR"));
		// #else
		// @ this.captions = ResourceBundle
		// @ .getBundle("Messages", new
		// @ Locale("en",
		// @ "US"));
		// #endif
	}

	public ResourceBundle getCaptions() {
		return captions;
	}
}
