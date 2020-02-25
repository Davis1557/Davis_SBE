package tw.com.aitc.SBE.HTTP_INVOKER;

public class HelloWorld implements HelloService {
	@Override
	public String sayHello() {
		String hello = "Hello World";
		System.out.println(hello);
		return hello;
	}
}
