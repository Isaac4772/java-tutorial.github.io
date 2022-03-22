package Day10.Interface;

public class Button implements OnClickListener {
	private String name;

	public Button(String name) {
		this.name = name;
	}

	public void display() {
		System.out.println("Font size: " + FONT_SIZE);
		System.out.println("Font family: " + FONT_FAMILY);
	}

	@Override
	public void onDoubleClick() {
		System.out.println(name + " double click");

	}

	@Override
	public void onClick() {
		System.out.println(name + " click");

	}
}
