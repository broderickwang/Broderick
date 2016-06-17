package com.ttb.broderick.bean;

import android.app.AlarmManager;
import android.os.Parcel;
import android.os.Parcelable;

/*********************************************************
 * 说 明 ：  Parcelable接口的使用，使类可以在activity之间传递
 *          类似于Serializable接口，但是Serializable接口序列化
 *          和反序列化过程中需要大量I/O操作，从而导致开销大效率低。
 *          Parcelable接口可以避免这种情况
 * Created by Kevin on 16/6/17.
 *********************************************************/
public class Person implements Parcelable {
	private String name;
	private int age;
	private boolean isCheck;

	public Person(String name, int age, boolean isCheck) {
		this.name = name;
		this.age = age;
		this.isCheck = isCheck;
	}

	public Person() {
	}

	//实现Parcelable接口的内容
	@Override
	public int describeContents() {
		return 0;
	}
	//入这个类的变量  序列化
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeInt(age);
		//由于没有直接对boolean序列化的方法，所以采用byte
		parcel.writeByte((byte)(isCheck?1:0));
	}

	//在实现上面的接口方法后，接下来还需要执行反序列化，定义一个变量，并重新定义其中的部分方法
	//如果不实现这个会报错
	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
		//在这个方法中反序列化上面的序列化内容，最后根据反序列化得到的各个属性，得到之前试图传递的对象
		//反序列化的属性的顺序必须和之前写入的顺序一致
		@Override
		public Person createFromParcel(Parcel parcel) {
			Person p = new Person();
			p.name = parcel.readString();
			p.age = parcel.readInt();
			p.isCheck = parcel.readByte() != 0 ;
			return p;
		}

		@Override
		public Person[] newArray(int i) {
			//一般返回一个数量为i的传递的类的数组就可以了
			return new Person[i];
		}
	};

	@Override
	public String toString() {
		return name+":"+age+"  \nischeck="+isCheck;
	}
}
