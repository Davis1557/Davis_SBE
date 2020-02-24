package tw.com.aitc.SBE.Java8Feature;

public class DoDouble implements Calculator {
	@Override
	public void doCalc(int i) {
		System.out.println(2 * i);
	}
}
