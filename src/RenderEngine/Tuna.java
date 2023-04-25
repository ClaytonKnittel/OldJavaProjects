package RenderEngine;

public enum Tuna {
	bucky("Nice","22"),
	kelsey("Ugly","10"),
	julia("Big","12");
	
	private final String desc;
	private final String year;
	
	Tuna(String description, String y) {
		desc=description;
		year=y;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public String getYear() {
		return year;
	}
	
}
