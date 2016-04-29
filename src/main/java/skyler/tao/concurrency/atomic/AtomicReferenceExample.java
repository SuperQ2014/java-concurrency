package skyler.tao.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

	private static String message;
	private static Person person;
	private static AtomicReference<String> aRmessage;
	private static AtomicReference<Person> aRperson;
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MyRun1());
		Thread t2 = new Thread(new MyRun2());
		message = "origin";
		person = new Person("origin-person", 0);
		aRmessage = new AtomicReference<String>(message);
		aRperson = new AtomicReference<Person>(person);
		System.out.println("Message is: " + message
				+ "\nPerson is " + person.toString());
		System.out.println("Atomic Reference of Message is: " + aRmessage.get()
				+ "\nAtomic Reference of Person is " + aRperson.get().toString());
		t1.start();
		t2.start();
		t1.join();
		t2.join();	
		System.out.println("\nNow Message is: " + message 
				+ "\nPerson is " + person.toString());
		System.out.println("Atomic Reference of Message is: " + aRmessage.get()
				+ "\nAtomic Reference of Person is " + aRperson.get().toString());
	}
		
	static class MyRun1 implements Runnable {

		public void run() {
			aRmessage.compareAndSet(message, "thread0-message");
			message = message.concat("-concat-something-thread0");
			person.setAge(person.getAge()+1);
			person.setName("person-thread0");
			aRperson.getAndSet(new Person("thread0", 100));
			System.out.println("\n" + Thread.currentThread().getName() +" Values: " 
					+ message + " - " + person.toString());
			System.out.println("\n" + Thread.currentThread().getName() +" Atomic References " 
					+ aRmessage.get() + " - " + aRperson.get().toString());
		}		
	}
	
	static class MyRun2 implements Runnable {

		public void run() {
			message = message.concat("-concat-something-thread1");
			person.setAge(person.getAge()+2);
			person.setName("person-thread1");
			aRmessage.lazySet("thread1-message");
			aRperson.set(new Person("thread1", 101));
			System.out.println("\n" + Thread.currentThread().getName() +" Values: " 
					+ message + " - " + person.toString());
			System.out.println("\n" + Thread.currentThread().getName() +" Atomic References: " 
					+ aRmessage.get() + " - " + aRperson.get().toString());
		}		
	}
	
	static class Person {
		
		private String name;
		private int age;
		
		public Person(String name, int age) {
			this.name = name;
			this.age= age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}
		
		public void setAge(int age) {
			this.age = age;
		}	
		
		@Override
		public String toString() {
			return "[name " + this.name + ", age " + this.age + "]";
		}
	}
}
