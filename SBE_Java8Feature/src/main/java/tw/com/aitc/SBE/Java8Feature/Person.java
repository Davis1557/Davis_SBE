package tw.com.aitc.SBE.Java8Feature;

public class Person {
	private String name;
	private String words;

	public Person(String name, String words) {
		this.name = name;
		this.words = words;
	}

	public String getName() {
		return name;
	}

	public String getWords() {
		return words;
	}

	public String greeting() {
		return name + ": " + words;
	}
}
