package tw.com.aitc.SBE.Java8Feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8Example {

	public void lambdaExample() {
		// 當我們需要一個 Calculator Interface 的物件時
		// 要先有一個實作它的 Class (例如 Double)
		// 並透過 new 呼叫 Constructor
		Calculator calc = new DoDouble();

		// 偷懶 1 : 匿名類
		// 但當我們需要不同實作方式的物件時
		// 又需要再有一個實作它的 Class
		// 但這 Class 可能就只被用這麼一次，且實作內容又不多
		// 再新建一個檔案的必要性就不是太高
		// 所以就有了 Anonymous Class 的作法出現
		// 實作 Interface、繼承 Class 都能使用
		Calculator calc2 = new Calculator() {
			@Override
			public void doCalc(int input) {
				System.out.println(input * 3);
			}
		};

		// 偷懶 2 : Lambda
		// 改用 Anonymous Class 後發現
		// 在實作只有一個 Method 的 Interface 時
		// 代碼顯得冗長且笨拙
		// 後來就將這種 Interface 稱作 Functional Interface
		// 並在宣告 Anonymous Class 時
		// 減少一些編譯器能夠自行推斷、補上的代碼
		// 這種只留下參數和運算式的語法又稱 lambda
		Calculator calc3 = i -> System.out.println(i * 3);

		// 偷懶 3 : 方法參考
		// lambda 使用時常常會碰到只是單純將參數委派給另一個物件或類別的 Method
		// 或是單純執行參數的一個 method
		// 對此，再將 lambda 精簡後的語法便是 Method Reference
		Calculator calc4 = System.out::println;

	}

	public void streamExample() {
		//============================================
		//
		//  Collection<T>  =>  Stream<T>  =>  Stream<U>  =>  Collection<U>
		//
		//  Collection<T>  =>  Stream<T>  =>  ...  =>  Object
		//
		//============================================
		// 例 1
		// Person 有 String greeting()
		Collection<Person> people = Arrays.asList(
				new Person("Davis", "Hello ~"),
				new Person("Rex", "Wow ~"),
				new Person("Charlie", "Hi !!")
		);

		// 1. 從 people 取得 List of greeting (排除 Rex)
		// 2. 依次 print List 中每一個 greeting

		//// java7
		System.out.println("\n=== greeting7 ===");
		List<String> greeting7 = new ArrayList<>();
		for (Person person : people) {
			if (!person.getName().equals("Rex")) {
				greeting7.add(person.greeting());
			}
		}

		for (String g : greeting7) {
			System.out.println(g);
		}

		//// java8
		System.out.println("\n=== greeting8 ===");
		List<String> greeting8 = people
				.stream()
				.filter(p -> !p.getName().equals("Rex"))
				.map(Person::greeting) //Stream<String>
				.collect(Collectors.toList());

		greeting8.forEach(System.out::println);


		// 例 2
		// 一組數字資料
		System.out.println("\n=== Number case ===");
		double average = IntStream.of(1, 2, 3, 4, 5, 1, 2, 3, 7, 8, 9) //{1, 2, 3, 4, 5, 1, 2, 3, 7, 8, 9}
				.distinct() //{1, 2, 3, 4, 5, 7, 8, 9}
				.skip(2) //{3, 4, 5, 7, 8, 9}
				.peek(i -> System.out.print(i + ", "))
				.average()
				.getAsDouble(); // 6.0

		System.out.println("\n" + average);
	}
}
