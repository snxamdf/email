package test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.reflect.MethodUtils;

public class TestMain {

	public static void main(String[] args) {
		TestMain tm = new TestMain();
		Parent param = new Parent();
		try {
			Object obj=MethodUtils.invokeExactMethod(tm, "getString", param);
			System.out.println(obj);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public String getString(Parent param) {
		param.meme();
		return "asdf";
	}
}

class Parent {
	public void meme() {
		System.out.println("meme1");
	}
}

class Child {
	public void meme() {
		System.out.println("meme2");
	}
}
