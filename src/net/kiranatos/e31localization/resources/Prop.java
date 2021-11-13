package net.kiranatos.e31localization.resources;


import java.util.ListResourceBundle;

public class Prop extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] { { "main.name", "Class.Default: Name" }, 
			{ "main.message", "Class.Default: Message" },
			{ "main.missing", "Class.Default: Missing key" }};
	}

}
