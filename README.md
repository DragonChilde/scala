# 第一章 入门

------

Scala是一门以Java虚拟机（JVM）为运行环境并将面向对象和函数式编程的最佳特性结合在一起的 静态类型编程语言（静态语言需要提前编译的如：Java、c、c++等，动态语言如：js）。

1. Scala是一门多范式的编程语言，Scala支持面向对象和函数式编程。（多范式，就是多种编程方 法的意思。有面向过程、面向对象、泛型、函数式四种程序设计方法。）
2. Scala源代码（.scala）会被编译成Java字节码（.class），然后运行于JVM之上，并可以调用现有 的Java类库，实现两种语言的无缝对接。
3. Scala单作为一门语言来看，非常的简洁高效。
4. Scala在设计时，马丁·奥德斯基是参考了Java的设计思想，可以说Scala是源于Java，同时马丁·奥 德斯基也加入了自己的思想，将函数式编程语言的特点融合到JAVA中, 因此，对于学习过Java的同学， 只要在学习Scala的过程中，搞清楚Scala和Java相同点和不同点，就可以快速的掌握Scala这门语言。

特点：

- 同样运行在JVM上，可以与现存程序同时运行。
- 可直接使用Java类库。
- 同Java一样静态类型。
- 语法和Java类似，比Java更加简洁（简洁而并不是简单），表达性更强。
- 同时支持面向对象、函数式编程。
- 比Java更面向对象。

关注点：

- 类型推断、不变量、函数式编程、高级程序构造。
- 并发：actor模型。
- 和现有Java代码交互、相比Java异同和优缺。

和Java关系：

```
        javac               java
.java --------> .class ----------> run on JVM
.scala -------> .class ----------> run on JVM
        scalac              scala
```

- [Scala官网语法速查](https://docs.scala-lang.org/zh-cn/cheatsheets/index.html)
- [Scala官方文档 Tour Of Scala](https://docs.scala-lang.org/zh-cn/tour/tour-of-scala.html)



## 环境配置

Scala需要依赖Java，访问[这里](https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html)查看特定Scala版本依赖的Java版本。这里选择，最新的JDK17配合Scala2.13.12。

Windows中下载安装配置环境变量：

- 类似于java配置`SCALA_HOME`为安装目录。
- 添加`%SCALA_HOME%\bin`到path环境变量。

Linux中类似，可以使用包管理器，但如果依赖版本不严格一致的话，需要官网下载对应版本安装即可。

也有交互式执行环境：

```
scala
```

交互式执行环境中的传统艺能：

```
println("hello,world!")
```

暂时不管项目配置，还是单文件编译执行为主，项目开发肯定要以包的形式组织可以使用IntelliJ IDEA开发，使用maven或者sbt进行项目配置。



## class和object说明

```scala
package com.scala.chapter01

/**
 * main 方法名
 * 小括号表示参数列表
 * 参数声明方式: java -> 类型 参数名
 * scala -> 参数名: 类型
 * public修饰符:scala 中没有public关键字,如果不声明访问权限,那就是公共的
 * static修饰符: scala 中没有静态语法,所以没有static关键字
 * void关键字: 表示返回值,但是不遵循面向对象语法,所以scala中没有,但是有Unit类型,表示没有返回值
 * scala中: 方法名(参数列表):返回值此类型
 * scala中声明方法必须采用关键字def声晚
 * scala中方法实现赋值给方法声明,所以中间需要等号连接
 *
 * Scala是一个完全面向对象的语言,所以没有静态语法,为了能调用静态语法(模仿静态语法),采用伴生对象单例的方式调用方法
 */

/**
 *  object: 关键字，声明一个单例对象（伴生对象）
 *  可以理解为替代Java的static关键字的方式，将静态方法用单例对象的实例方法做了替代，做到了更纯粹的面向对象。
 */
object Hello {

  def main(args: Array[String]): Unit = {
    println("hello scala")
  }
}

```

Scala完全面向对象，故Scala去掉了Java中非面向对象的元素，如static关键字，void类型

1. static

   - Scala无static关键字，由object实现类似静态方法的功能（类名.方法名）。
   - class关键字和Java中的class关键字作用相同，用来定义一个类；

2. void

   对于无返回值的函数，Scala定义其返回值类型为Unit类

## Scala程序反编译

1. 在项目的 target 目录 Hello 文件上点击右键->Show in Explorer->看到 object 底层生成 `Hello$.class` 和 `Hello.class` 两个文件

   > java源代码编译而成的字节码是可以通过`scala`命令运行的
   >
   > 如编译异常,原因是没有引入Scala的库，添加`classpath`就可以通过java执行scala编译成的字节码了：
   >
   > ```
   > java -cp %SCALA_HOME%/lib/scala-library.jar; HelloScala
   > ```
   >
   > 使用[Java Decompiler](http://java-decompiler.github.io/)反编译字节码到java源文件可以看到引入Scala库的逻辑。并且：
   >
   > - scala源文件中的`Hello`对象编译后成为了一个类，但对象本身编译后就是生成的另一个类`Hello$`类的单例对象`Hello$.MODULE$`，称之为伴生对象。
   > - `Hello$`有一个`main`实例方法，`Hello`类的静态方法通过这个单例对象转调这个实例方法。完成打印。
   > - Scala比Java更面向对象。

2. 采用 Java 反编译工具 jd-gui.exe 反编译代码，将 Hello.class 拖到 jd-gui.exe 页面

   1. Hello源码如上

      > Object编译后生成`Hello$.class`和`Hello.class`两个文件

   2. `Hello.class`类

      ```java
      package com.scala.chapter01;
      
      import scala.reflect.ScalaSignature;
      
      public final class Hello
      {
        public static void main(String[] args)
        {
          Hello$.MODULE$.main(args);
        }
      }
      ```

      > Hello中有个main函数,调用Hello$类的一个静态对象MODULE$

   3. `Hello$.class`类

      ```java
      package com.scala.chapter01;
      
      import scala.Predef.;
      
      public final class Hello$
      {
        public static final  MODULE$ = new ();
      
        public void main(String[] args) {
          Predef..MODULE$.println("hello scala");
        }
      }
      ```

      > Hello$.MODULE$.对象是静态的,通过该对象调用Hello$的main函数



## IDEA环境配置

- 创建Maven项目，JDK版本17。
- 安装插件：Scala。一般默认都已经装了。
- Maven项目默认用Java写，在`main/`目录下新建目录`scala/`，然后将目录标记为`Source Root`。
- 这样甚至可以在同一个项目中混用Scala和Java源文件，并互相调用。
- 需要能够添加scala源文件，右键项目，添加框架支持，配置Scala SDK，选择，然后就可以右键添加Scala源文件了。
- 添加包，添加Scala类，选择对象，编辑源码。

> 官网下载源码包`scala-sources-2.13.12.tar.gz`到`scala-2.13.12\lib`文件夹下进行解压，在源文件选择源码包路径即可



## java与Scala等价类对比

- `java`

  ```java
  package com.scala.entity;
  
  public class Student {
  
      private String name;
      private Integer age;
      private static String school = "scala";
  
      public Student(String name, Integer age) {
          this.name = name;
          this.age = age;
      }
  
      public void printInfo() {
          System.out.println(this.name + " " + this.age + " " + Student.school);
      }
  
  
      public static void main(String[] args) {
          Student stu = new Student("cang", 19);
          stu.printInfo();
  
  
      }
  }
  
  ```

- `scala`

  ```scala
  package com.scala.chapter01
  
  class Student(name: String, age: Int) {
  
    def printInfo(): Unit = {
      println(name + " " + age + " " + Student.school)
  
    }
  }
  
  object Student {
    val school: String = "scala"
  
    def main(args: Array[String]): Unit = {
      val stu = new Student("sanshan", 20)
      stu.printInfo()
    }
  }
  
  ```

scala生成的字节码反编译java如下

```java
package com.scala.chapter01;

import scala.Predef.;
import scala.reflect.ScalaSignature;

public class Student
{
  private final String name;
  private final int age;

  public static void main(String[] args)
  {
    Student..MODULE$.main(args);
  }

  public static String school()
  {
    return Student..MODULE$.school();
  }

  public void printInfo()
  {
    Predef..MODULE$.println(2 + this.name + " " + this.age + " " + Student..MODULE$.school());
  }

  public Student(String name, int age)
  {
  }
}
```

```java
package com.scala.chapter01;

public final class Student$
{
  public static final  MODULE$ = new ();
  private static final String school = "scala";

  public String school() { return school; }

  public void main(String[] args) {
    Student stu = new Student("sanshan", 20);
    stu.printInfo();
  }
}
```

> 其伴生类生成的是最终类

------

# 第二章 变量和数据类型

## 注释

> **Scala注释使用和Java完全一样**

1. 基本语法

   ```
   单行注释	//
   多行注释	/* */
   文档注释	/**
   			*
   			*/
   ```

   

2. 实例

   ```scala
   package com.scala.chapter02
   
   /*
     这是多行注释
    */
   object Test01_Commment {
   
     /**
      * 文档注释
      * 程序的入口
      *
      * @param args
      */
     def main(args: Array[String]): Unit = {
   
       // 单行注释
       println("hello")
     }
   }
   
   ```

   

3. 代码规范

   1. 使用一次 tab 操作，实现缩进，默认整体向右边移动，用 shift+tab 整体向左移
   2. 或者使用 **ctrl + alt + L** 来进行格式化
   3. 运算符两边习惯性各加一个空格。比如：2 + 4 * 5
   4. 一行最长不超过 80 个字符，超过的请使用换行展示，尽量保持格式优雅



## 变量和常量

常量：在程序执行的过程中，其值不会被改变的变量

0. 回顾：Java 变量和常量语法

   ```java
   变量类型 变量名称 = 初始值 int a = 10
   final 常量类型 常量名称 = 初始值 final int b = 20
   ```

1. 基本语法

   ```sca
   var 变量名 [: 变量类型] = 初始值 var i:Int = 10
   val 常量名 [: 常量类型] = 初始值 val j:Int = 20
   ```

   > 注意：能用常量的地方不用变量

2. 案例实操

   1. 声明变量时，类型可以省略，编译器自动推导，即类型推导
   2. 类型确定后，就不能修改，说明 Scala 是强数据类型语言
   3. 变量声明时，必须要有初始值
   4. 在声明/定义一个变量时，可以使用 var 或者 val 来修饰，var 修饰的变量可改变， val 修饰的变量不可改
   5. var 修饰的对象引用可以改变，val 修饰的对象则不可改变，但对象的状态（值） 却是可以改变的。（比如：自定义对象、数组、集合等等）

   ```scala
   package com.scala.chapter02
   
   import com.scala.chapter01.Student
   
   object Test02_Variable {
   
     def main(args: Array[String]): Unit = {
   
       // 声明一个变量的通用语法
       var a: Int = 10
   
       // 1.声明变量时,类型可以省略,编译器自动推导,即类型推导
       var a1 = 10
       val b1 = 20
   
       // 2. 类型确定后,就不能修改,说明 Scala是强数据类型语言
       var a2 = 15 //a2类型是Int
       //a2 = "hello"	//error
   
   
       // 3. 变量声明时,必须要有初始值
       //var a3: Int	//error
   
   
       // 4. 在声明定义一个变量时,可以使用var或者val来修饰,var修饰的变量可改变,val修饰的变量不可改
       a1 = 22
       //b1 = 30	//error
   
   
       var tom = new Student("tom", 30)
       tom = new Student("jack", 22)
   
       tom = null
   
         
       // val修饰bob,bob本身就不可变,(即bob的内存地址不能变),但是其属性是可以变,因为属性并没有用val修饰
       val bob = new Student("bob", 11)
   
       bob.age = 24 //注意 这里当初使用的构造方法的传参方式把参数传到类里,实际类里是没有该属性的,必须把该类的参数定义成var,如下
       bob.printInfo()
   
   
     }
   
   }
   
   ```

   Student

   ```scala
   class Student(name: String, var age: Int) {
   
     def printInfo(): Unit = {
       println(name + " " + age + " " + Student.school)
   
     }
   }
   
   object Student {
     val school: String = "scala"
   
     def main(args: Array[String]): Unit = {
       val stu = new Student("sanshan", 20)
       stu.printInfo()
     }
   }
   ```





标识符的命名规范

Scala 对各种变量、方法、函数等命名时使用的字符序列称为标识符。即：凡是自己可 以起名字的地方都叫标识符

1. 命名规则

   Scala 中的标识符声明，基本和 Java 是一致的，但是细节上会有所变化，有以下三种规 则

   1. 以字母或者下划线开头，后接字母、数字、下划线

   2. 以操作符开头，且只包含操作符（+ - * / # !等）

   3. 用反引号\`....\`包括的任意字符串，即使是 Scala 关键字（39 个）也可以

      ```
      • package, import, class, object, trait, extends, with, type, for
      • private, protected, abstract, sealed, final, implicit, lazy, override
      • try, catch, finally, throw
      • if, else, match, case, do, while, for, return, yield
      • def, val, var
      • this, super
      • new
      • true, false, null
      ```

2. 案例实操

   ```scala
   package com.scala.chapter02
   
   object Test03_Identifier {
   
     def main(args: Array[String]): Unit = {
   
   
       // 1. 以字母或者下划线开头,后接字母,数字,下划线
       val hello: String = ""
       var Hello123 = ""
   
       val _abc = 123
   
       //val h - b = ""  //error 不能用-
       //val 123 abc = 234   //error 数字不能开头
   
   
       //2. 以操作符开头，且只包含操作符（+ - * / # !等）
       val -+/% = "hello"
       println(-+/%)
   
       //3. 用反引号`....`包括的任意字符串，即使是Scala关键字（39个）也可以
       val `if` = "if"
       println(`if`)
     }
   }
   
   ```



## 字符串输出

1. 基本语法

   1. 字符串
   2. printf用法:字符串,通过%传值
   3. 字符串模板(插值字符串):通过$获取变量值

2. 实例操作

   ```scala
   package com.scala.chapter02
   
   object Test04_String {
     def main(args: Array[String]): Unit = {
   
       //1. 字符串,通过+号连接
       val name: String = "jack"
       val age: Int = 18
   
       println(age + " old " + name + " learning in school")
   
       //用于将一个字符串复制多次并拼接
       println(name * 3) //jackjackjack
   
       //2. printf用法：字符串，通过%传值
       printf("%d old %s learning in school", age, name)
   
       println()
       // 3.字符串模板（插值字符串）：通过$获取变量值
       println(s"${age} old ${name} learning in school")
   
   
       val num: Double = 1.2345
       println(f"The num is ${num}%2.2f") // 格式化模板字符串 The num is 1.23
       println(raw"The num is${num}%2.2f") // 不作格式化直接输出  The num is1.2345%2.2f
   
       // 三引号表示字符串，保持多行字符串的原格式输出
       val sql =
         s"""|select *
             |from
             | student
             |where
             | name = ${name}
             |and
             | age > ${age}
             |""".stripMargin
             /*
             select *
             from
              student
             where
              name = jack
             and
              age > 18
              */
       println(sql)  
     }
   
   }
   ```





## 键盘输入

在编程中，需要接收用户输入的数据，就可以使用键盘输入语句来获取

1. 基本语法

   ```scala
   StdIn.readLine()
   StdIn.readShort()
   StdIn.readDouble()
   ```

   

2. 实例

   ```scala
   package com.scala.chapter02
   
   import scala.io.StdIn
   
   object Test05_StdIn {
   
     def main(args: Array[String]): Unit = {
       // 输入信息
       println("please enter your name:")
       val name = StdIn.readLine()
   
       println("please enter your age:")
       val age = StdIn.readInt()
   
       // 控制台打印输出
       println(s"welcome ${age} old ${name} come here!")
     }
   }
   
   ```

   ```
   please enter your name:
   jack
   please enter your age:
   19
   welcome 19 old jack come here!
   ```

3. 文件读写

   ```scala
   package com.scala.chapter02
   
   import java.io.{File, PrintWriter}
   import scala.io.Source
   
   object Test06_FileIO {
   
     def main(args: Array[String]): Unit = {
       // 1. 从文件中读取数据
       Source.fromFile("src/main/resources/test.txt").foreach(print)
   
   
       // 2. 将数据写入文件
       val writer = new PrintWriter(new File("src/main/resources/output.txt"))
       writer.write("hello scala from java code")
       writer.close()
   
     }
   }
   
   ```



## 数据类型（重点）

### java数据类型

Java基本类型：char、byte、short、int、long、float、double、boolean 

Java引用类型：（对象类型）

**由于Java有基本类型，而且基本类型不是真正意义的对象**，即使后面产生了基本类型的包装类，但是仍然存在基本数据类型，所以Java语言并不是真正意思的面向对象。 

Java基本类型的包装类：Character、Byte、Short、Integer、Long、Float、Double、Boolean

> 注意：Java中基本类型和引用类型没有共同的祖先

### Scala数据类型

![](http://www.dxb02.top/photos/scala/01.jpg)

1. Scala中一切数据都是对象，**都是Any的子类**
2. Scala中数据类型分为两大类：数值类型（AnyVal）、 引用类型（AnyRef），**不管是值类型还是引用类型都是对象**
3. Scala数据类型仍然遵守，**低精度的值类型向高精度值类型，自动转换**（隐式转换）
4. Scala中的StringOps是对Java中的String增强
5. Unit：对应Java中的void，用于方法返回值的位置，表示方法没有返回值。**Unit是一个数据类型**，只有一个对象就是()。Void不是数据类型，只是一个关键字
6. **Null是一个类型**，只有一个对象就 是null。**它是所有引用类型（AnyRef）的子类**
7. **Nothing，是所有数据类型的子类**，主要用在一个函数没有明确返回值时使 用，因为这样我们可以把抛出的返回值，返回给任何的变量或者函数

```
Scala中一切数据都是对象,都是Any的子类
10   和  new User()意义一样
```

### 整数类型(Byte,Short,Int,Long)

Scala的整数类型就是用于存放整数值的,比如12,30,3456等

1. 整形分类

   | 数据类型 | 描述                                                         |
   | -------- | ------------------------------------------------------------ |
   | Byte[1]  | 8 位有符号补码整数。数值区间为 -128 到 127                   |
   | Short[2] | 16 位有符号补码整数。数值区间为 -32768 到 32767              |
   | Int[4]   | 32 位有符号补码整数。数值区间为 -2147483648 到 2147483647    |
   | Long[8]  | 64 位有符号补码整数。数值区间为 -9223372036854775808 到 9223372036854775807 = 2 的(64-1)次方-1 |

2. 实例操作

   1. Scala 各整数类型有固定的表示范围和字段长度，不受具体操作的影响，以保证 Scala 程序的可移植性
   2. Scala 的整型，默认为 Int 型，声明 Long 型，须后加‘l’或‘L’
   3. Scala 程序中**变量常声明为 Int 型**，除非不足以表示大数，才使用 Long
   
   ```scala
   package com.scala.chapter02
   
   object Test07_DataType {
   
     def main(args: Array[String]): Unit = {
   
       // 1. 整数类型
       val a1: Byte = 127
       val a2: Byte = -128
   
       //val a2: Byte = 128 // error
   
       val a3 = 12 // 整数默认类型为Int
       val a4: Long = 123456789L // 长整型数值定义
   
   
       val b1: Byte = 10
       val b2: Byte = 10 + 20 //这种方式默认是允许的,编辑器会提示是Int类型
       println(b2) //30
   
   
       //val b3: Byte = b1 + 20 //注意:运行时直接报异常,要强转类型
       val b3: Byte = (b1 + 20).toByte
       println(b3) //30
   
   
     }
   
   }
   
   ```

### 浮点类型(Float,Double)

Scala 的浮点类型可以表示一个小数，比如 123.4f，7.8，0.12 等等。

1. 浮点型分类

   | 数据类型   | 描述                               |
   | ---------- | ---------------------------------- |
   | Float [4]  | 32 位, IEEE 754 标准的单精度浮点数 |
   | Double [8] | 64 位 IEEE 754 标准的双精度浮点数  |

2. 实例操作

   Scala 的浮点型常量默认为 Double 型，声明 Float 型常量，须后加‘f’或‘F’。

   ```scala
   package com.scala.chapter02
   
   object Test07_DataType {
   
     def main(args: Array[String]): Unit = {
   	// 2. 浮点类型
       // 建议，在开发中需要高精度小数时，请选择 Double
       val f1: Float = 1.2345f
       val d1 = 34.2245
   
     }
   
   }
   
   ```

### 字符类型(Char)

1. 基本说明

   字符类型可以表示单个字符，字符类型是 Char

2. 实例操作

   1. 字符常量是用单引号 ' ' 括起来的单个字符
   2. \t ：一个制表位，实现对齐的功能
   3. \n ：换行符
   4. \\ ：表示\
   5. \" ：表示"

   ```scala
   package com.scala.chapter02
   
   object Test07_DataType {
   
     def main(args: Array[String]): Unit = {
       // 3. 字符类型
       val c1: Char = 'a'
       println(c1)
   
       val c2: Char = '9'
       println(c2)
   
   
       // 控制字符
       val c3: Char = '\t' //制表符
       val c4: Char = '\n' //换行符
       println("abc" + c3 + "def") //abc	def
   
       /*
       abc
       def
        */
       println("abc" + c4 + "def")
   
   
       //转义字符
       val c5 = '\\' // 表示\自身
       val c6 = '\"' // 表示"
       println("abc" + c5 + "def") //abc\def
       println("abc" + c6 + "def") //abc"def
   
   
       // 字符变量底层保存ASCII码
       val i1: Int = c1
       println("i1:" + i1) //i1:97
   
       val i2: Int = c2
       println("i2:" + i2) //i2:57
       //注意：这里涉及自动类型提升，其实编译器可以自定判断是否超出范围，
    	//不过 idea 提示报错
       val c7: Char = (i1 + 1).toChar
       println(c7) //b
   
       val c8: Char = (i2 - 1).toChar
       println(c8) //8
   
     }
   
   }
   ```

   

### 布尔类型:Boolean

1. 基本说明

   1. 布尔类型也叫 Boolean 类型，**Booolean 类型数据只允许取值 true 和 false**
   2. boolean 类型占 1 个字节

2. 实例操作

   ```scala
   package com.scala.chapter02
   
   object Test07_DataType {
   
     def main(args: Array[String]): Unit = {
   	// 4. 布尔类型
       val isTrue: Boolean = true
       println(isTrue) //true
   
     }
   
   }
   ```



### Unit类型,Null类型和Nothing类型(重点)

1. 基本说明

   | 数据类型 | 描述                                                         |
   | -------- | ------------------------------------------------------------ |
   | Unit     | 表示无值，和其他语言中 void 等同。用作不返回任何结果的方法的结果 类型。Unit 只有一个实例值，写成()。 |
   | Null     | null , Null 类型只有一个实例值 null                          |
   | Nothing  | Nothing 类型在 Scala 的类层级最低端；它是任何其他类型的子类型。 当一个函数，我们确定没有正常的返回值，可以用 Nothing 来指定返回类 型，这样有一个好处，就是我们可以把返回的值（异常）赋给其它的函数 或者变量（兼容性） |

2. 实例操作

   1. Unit 类型用来标识过程，也就是没有明确返回值的函数

      由此可见，Unit 类似于 Java 里的 void。Unit 只有一个实例——( )，这个实例也没有实 质意义

      ```scala
          //5.1 空值Unit
          def m1(): Unit = {	// unit 表示没有返回值，即 void
            println("m1被调用执行")
          }
          val a: Unit = m1()
          println("a" + a) //a()
      ```

   2. Null 类只有一个实例对象，Null 类似于 Java 中的 null 引用。Null 可以赋值给任 意引用类型（AnyRef），但是不能赋值给值类型（AnyVal）

      ```scala
          //val n: Int = null //error   运行时报异常
      
          var student: Student = new Student("tom", 20)
          student = null
          println(student)
      ```

   3. Nothing，可以作为没有正常返回值的方法的返回类型，非常直观的告诉你这个方 法不会正常返回，而且由于 Nothing 是其他任意类型的子类，他还能跟要求返回值的方法兼 容

      ```scala
          /*
              5.3 Nothing
              因为Nothing是所有类型的子类,所以下面返回Int类型已经包含有Nothing
           */
          def m2(n: Int): Int = {
            if (n == 0) {
              throw new NullPointerException()
            } else {
              n
            }
          }
      
          val b: Int = m2(2)
          println("b:" + b)
      ```

### Unit源码分析

```scala
// 可看到Unit是一抽像类,其实现在下面
final abstract class Unit private extends AnyVal {
  // Provide a more specific return type for Scaladoc
  override def getClass(): Class[Unit] = ???
}

@scala.annotation.compileTimeOnly("`Unit` companion object is not allowed in source; instead, use `()` for the unit value")
object Unit extends AnyValCompanion {

  // 这里是一个包装方法,返回一个指定的包装类,点击进这里,如下
  def box(x: Unit): scala.runtime.BoxedUnit = scala.runtime.BoxedUnit.UNIT

  def unbox(x: java.lang.Object): Unit = x.asInstanceOf[scala.runtime.BoxedUnit]

  /** The String representation of the scala.Unit companion object. */
  override def toString = "object scala.Unit"
}


```

```scala
// 这个类是一个工厂类,可看下面构造方法
public final class BoxedUnit implements java.io.Serializable {
    private static final long serialVersionUID = 8405543498931817370L;

    // 静态全局方法,实例化自身
    public final static BoxedUnit UNIT = new BoxedUnit();

    // 定义其类型就是java的void类型
    public final static Class<Void> TYPE = java.lang.Void.TYPE;
    
    private Object readResolve() { return UNIT; }

    // 构造方法私有化
    private BoxedUnit() { }

    public boolean equals(java.lang.Object other) {
	return this == other;
    }

    public int hashCode() {
	return 0;
    }

    // 返回(),就是这里
    public String toString() {
	return "()";
    }
}
```

### 类型转换

扩展Java面试题(隐式类型转换)

```java
package com.scala;

public class TestDataTypeConversion {

    public static void main(String[] args) {
        byte b = 10;
        test(b);        //aaaa
        /*
            当test(byte b)注释后,输出的是bbbb
            当test(short s)注释后,输出的是dddd
            虽然char也是2个字节,但与其它几个类型是不同类型,所以只会提升到相同类型
         */
        char c = 'a';
        short c2 = (short) c;
        test(c2);       //bbbb
        test(c);        //cccc

    }
/*

    public static void test(byte b) {
        System.out.println("aaaa");
    }
*/

/*    public static void test(short s) {
        System.out.println("bbbb");
    }*/

    public static void test(char c) {
        System.out.println("cccc");
    }

    public static void test(int i) {
        System.out.println("dddd");
    }
}

```



#### 数据类型自动转换

当 Scala 程序在进行赋值或者运算时，精度小的类型自动转换为精度大的数值类型，这 个就是自动类型转换（隐式转换）。数据类型按精度（容量）大小排序为

![](http://www.dxb02.top/photos/scala/02.jpg)

1.  基本说明

   1. 自动提升原则：有多种类型的数据混合运算时，系统首先**自动将所有数据转换成精度大的那种数据类型**，然后再进行计算
   2. **把精度大的数值类型赋值给精度小的数值类型时，就会报错**，反之就会进行自动类型转换
   3. （byte，short）和 char 之间不会相互自动转换
   4. byte，short，char 他们三者可以计算，在计算时首先转换为 int 类型

2. 实例操作

   ```scala
   package com.scala.chapter02
   
   object Test08_DataTypeConversion {
   
     def main(args: Array[String]): Unit = {
       // 1. 自动类型转换
       //    （1）自动提升原则：有多种类型的数据混合运算时，系统首先自动将所有数据转换成精度大的那种数据类型，然后再进行计算。
       var n = 1 + 2.0
       println(n) //n 就是Double 3.0
   
       //    （2）把精度大的数值类型赋值给精度小的数值类型时，就会报错，反之就会进行自动类型转换。
       val a2: Byte = 10
       val b2: Int = a2
       //val c2: Byte = b2   //  error
   
       //    （3）（byte，short）和char之间不会相互自动转换。
       val a3: Byte = 10
       val b3: Char = 'b'
       //val c3: Byte = b3   // 执行时报异常 error
       val c3: Int = b3
       println(c3) //98
   
       //    （4）byte，short，char他们三者可以计算，在计算时首先转换为int类型。
       val a4: Byte = 12
       val b4: Short = 25
       val c4: Char = 'c'
       val result4: Int = a4 + b4
       val result44: Int = a4 + b4 + c4
       println(result44) //136
   
     }
   
   }
   
   ```

   > 注意：Scala 还提供了非常强大的隐式转换机制（隐式函数，隐式类等），我们放在高 级部分专门用一个章节来讲解。

#### 强制类型转换

1. 基本说明

   自动类型转换的逆过程，将精度大的数值类型转换为精度小的数值类型。使用时要加上 强制转函数，但可能造成精度降低或溢出，格外要注意

   ```
   Java : int num = (int)2.5
   Scala : var num : Int = 2.7.toInt
   ```

2. 实例操作

   1. 将数据由高精度转换为低精度，就需要使用到强制转换
   2. 强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级

   ```scala
   package com.scala.chapter02
   
   object Test08_DataTypeConversion {
   
     def main(args: Array[String]): Unit = {
   
       // 2. 强制类型转换
       //    （1）将数据由高精度转换为低精度，就需要使用到强制转换
       val n1: Int = -2.9.toInt
       println("n1:" + n1) //n1:-2		存在精度 损失
   
       //    （2）强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级
       val n2: Int = 2.6.toInt + 3.7.toInt
       val n3: Int = (2.6 + 3.7).toInt
       println("n2 : " + n2) //n2 : 5
       println("n3 : " + n3) //n3 : 6
   
     }
   
   }
   
   ```



数值类型和String类型间转换

1. 基本说明

   在程序开发中，我们经常需要将基本数值类型转成 String 类型。或者将 String 类型转成 基本数值类型

2. 实例操作

   1. 基本类型转 String 类型（语法：将基本类型的值+"" 即可）
   2. String 类型转基本数值类型（语法：s1.toInt、s1.toFloat、s1.toDouble、s1.toByte、s1.toLong、s1.toShort）

   ```scala
   package com.scala.chapter02
   
   object Test08_DataTypeConversion {
   
     def main(args: Array[String]): Unit = {
   
        // 3. 数值类型和String类型的转换
       // (1) 数值转String
       val n4: Int = 27
       val s: String = n4 + ""
       println(s) //27
   
   
       // (2) String转数值
       val m: Int = "12".toInt
       val f: Float = "12.3".toFloat
       val f2: Int = "12.3".toDouble.toInt
       println(f2) //12
   
     }
   
   }
   
   ```

   > 注意
   >
   > 在将 String 类型转成基本数值类型时，要确保 String 类型能够转成有效的数据，比如我 们可以把"123"，转成一个整数，但是不能把"hello"转成一个整数。 
   >
   > var n5:Int = "12.6".toInt 会出现 NumberFormatException 异常。


#### 扩展面试题

```scala
package com.scala.chapter02

/*
128: Int类型，占据4个字节，32位
原码 0000 0000 0000 0000 0000 0000 1000 0000
补码 0000 0000 0000 0000 0000 0000 1000 0000

截取最后一个字节，Byte
得到补码 1000 0000
表示最大负数 -128

130: Int类型，占据4个字节，32位
原码 0000 0000 0000 0000 0000 0000 1000 0010
补码 0000 0000 0000 0000 0000 0000 1000 0010

截取最后一个字节，Byte
得到补码 1000 0010
对应原码 1111 1110
-126
 */
object Test09_Problem_DataTypeConversion {

  def main(args: Array[String]): Unit = {
    var n: Int = 130
    val b: Byte = n.toByte
    println(b) //-126
  }

}

```

------



# 第三章 运算符

> Scala 运算符的使用和 Java 运算符的使用基本相同，只有个别细节上不同

## 算术运算符

1. 基本语法

   | 运算符 | 运算       | 范例       | 结果    |
   | ------ | ---------- | ---------- | ------- |
   | +      | 正号       | +3         | 3       |
   | -      | 负号       | b=4;-b     | -4      |
   | +      | 加         | 5+5        | 10      |
   | -      | 减         | 6-4        | 2       |
   | *      | 乘         | 3*4        | 12      |
   | /      | 除         | 5/5        | 1       |
   | %      | 取模(取余) | 7%5        | 2       |
   | +      | 字符串相加 | "He"+"llo" | "Hello" |

   1. 对于除号“/”，它的整数除和小数除是有区别的：整数之间做除法时，只保留整 数部分而舍弃小数部分
   2. 对一个数取模 a%b，和 Java 的取模规则一样

2. 实例操作

   ```scala
   package com.scala.chapter03
   
   object Test01_TestOperator {
   
     def main(args: Array[String]): Unit = {
   
       // 1. 算术运算符
       //对于除号“/”，它的整数除和小数除是有区别的：整数之间做除法时，只保留整数部分而舍弃小数部分
       val result: Int = 10 / 3
       println(result) //3
   
       val result2: Double = 10 / 3
       println(result2) //3.0
   
       val result3: Double = 10.0 / 3
       // 含义:保留小数点2位,使用四舍五入
   
       //println(result3.formatted("%5.2f")) //3.33 此方法在JDK15后已弃用,使用以后方式代替
       println("%5.2f".format(result3)) //3.33
   
       //对一个数取模 a%b，和 Java 的取模规则一样
       val result4: Int = 10 % 3
       println(result4) //1
   
     }
   
   }
   ```



## 关系运算符(比较运算符)

1. 基本语法

   | 运算符 | 运算     | 范例 | 结果  |
   | ------ | -------- | ---- | ----- |
   | ==     | 相等于   | 4==3 | false |
   | !=     | 不等于   | 4!=3 | true  |
   | <      | 小于     | 4<3  | false |
   | >      | 大于     | 4>3  | true  |
   | <=     | 小于等于 | 4<=3 | false |
   | >=     | 大于等于 | 4>=3 | true  |

   

2. 实例操作

   Java和Scalal中关于==的区别

   > ==比较两个变量本身的值，即两个对象在内存中的首地址
   >
   > equals 比较字符串中所包含的内容是否相同。

   ```java
   package com.scala;
   
   public class TestOperator {
   
       public static void main(String[] args) {
           // 比较运算符
           String s1 = "Hello";
           String s2 = new String("Hello");
   
   
           Boolean isEqual = s1 == s2;
           System.out.println(isEqual);    // false
           System.out.println(s1.equals(s2));  //true
   
       }
   }
   
   ```

   > Scala：==更加类似于 Java 中的 equals，参照 jd 工具

   ```scala
   package com.scala.chapter03
   
   object Test01_TestOperator {
   
     def main(args: Array[String]): Unit = {
   
       // 2. 比较运算符
       val s1: String = "hello"
       val s2: String = new String("hello")
   
       println(s1 == s2) //true
       println(s1.equals(s2)) //true
       println(s1.eq(s2)) //false
   
     }
   
   }
   
   ```

## 逻辑运算符

1. 基本语法

   用于连接多个条件（一般来讲就是关系表达式），最终的结果也是一个 Boolean 值

   假定：变量 A 为 true，B 为 false

   | 运算符 | 描述   | 实例                      |
   | ------ | ------ | ------------------------- |
   | &&     | 逻辑与 | (A && B) 运算结果为 false |
   | \|\|   | 逻辑或 | (A \|\| B)运算结果为true  |
   | !      | 逻辑非 | !(A && B)运算结果为true   |

   

2. 实例操作

   ```scala
   package com.scala.chapter03
   
   object Test01_TestOperator {
   
     def main(args: Array[String]): Unit = {
   
       //3. 逻辑运算符
       def m(n: Int): Int = {
         println("m被调用")
         return n
       }
   
       val n = 1
       println((4 > 5) && m(n) > 0) //false
   
       // 判断一个字符串是否为空
       //扩展避免逻辑与空指针异常
       def isNotEmpty(str: String): Boolean = {
         return str != null && !("".equals(str.trim))
       }
   
       println(isNotEmpty(null)) //false
     }
   
   }
   
   ```

## 赋值运算符

1. 基本语法

   赋值运算符就是将某个运算后的值，赋给指定的变量

   | 运算符 | 描述                                            | 实例                                   |
   | ------ | ----------------------------------------------- | -------------------------------------- |
   | =      | 简单的赋值运算符，将一个表 达式的值赋给一个左值 | C = A + B 将 A + B 表达式结果赋 值给 C |
   | +=     | 相加后再赋值                                    | C += A 等于 C = C + A                  |
   | -=     | 相减后再赋值                                    | C -= A 等于 C = C - A                  |
   | *=     | 相乘后再赋值                                    | C *= A 等于 C = C * A                  |
   | /=     | 相除后再赋值                                    | C /= A 等于 C = C / A                  |
   | %=     | 求余后赋值                                      | C %= A 等于 C = C % A                  |
   | <<=    | 左移后赋值                                      | C <<= 2 等于 C = C << 2                |
   | >>=    | 右移后赋值                                      | C >>= 2 等于 C = C >> 2                |
   | &=     | 按位与后赋值                                    | C &= 2 等于 C = C & 2                  |
   | ^=     | 按位异或后赋值                                  | C ^= 2 等于 C = C ^ 2                  |
   | \|=    | 按位或后赋值                                    | C \|=2 等于 C = C \| 2                 |

   > 注意：Scala 中没有++、--操作符，可以通过+=、-=来实现同样的效果

2. 实例操作

   ```scala
   package com.scala.chapter03
   
   object Test01_TestOperator {
   
     def main(args: Array[String]): Unit = {
       // 4. 赋值运算符
       /*
         var b: Byte = 10
         b += 1
         println(b)  //error 运行时报异常,会自动升格为int类型
         */
   
       var i: Int = 12
       i += 1
       println(i)  //13
     }
   
   }
   
   ```



## 位运算符

1. 基本语法

   下表中变量 a 为 60，b 为 13

   | 运算符 | 描述           | 实例                                                         |
   | ------ | -------------- | ------------------------------------------------------------ |
   | &      | 按位与运算符   | (a & b) 输出结果 12 ，二进制解释： 0000 1100                 |
   | \|     | 按位或运算符   | (a \| b) 输出结果 61 ，二进制解释： 0011 1101                |
   | ^      | 按位异或运算符 | (a ^ b) 输出结果 49 ，二进制解释： 0011 0001                 |
   | ~      | 按位取反运算符 | (~a ) 输出结果 -61 ，二进制解释： 1100 0011， 在 一个有符号二进制数的补码形式。 |
   | <<     | 左移动运算符   | a << 2 输出结果 240 ，二进制解释： 0011 0000                 |
   | >>     | 右移动运算符   | a >> 2 输出结果 15 ，二进制解释： 0000 1111                  |
   | >>>    | 无符号右移     | a >>>2 输出结果 15, 二进制解释: 0000 1111                    |

   

2. 实例操作

   ```scala
   package com.scala.chapter03
   
   object Test01_TestOperator {
   
     def main(args: Array[String]): Unit = {
       // 5. 位运算符
       val a: Byte = 60
       println(a << 3) //0011 1100 => 1 1110 0000 =>480
       println(a >> 2) //0011 1100 => 0000 1111 =>15
   
       val b: Short = -13
       println(b << 2) //1000 1101 => 1111 0010 => 1111 0011 => 1100 1100 => 1100 1011 =>1011 0100 =>-52
       println(b >> 2) //1000 1101 => 1111 0010 => 1111 0011 => 0011 1100 => 0011 1011 =>1000 0100 =>-4
       println(b >>> 2) //1073741820
     }
   
   }
   
   ```

## Scala 运算符本质

在 Scala 中其实是没有运算符的，所有运算符都是方法。 

1. 当调用对象的方法时，点.可以省略 
2. 如果函数参数只有一个，或者没有参数，()可以省略

```scala
package com.scala.chapter03

object Test01_TestOperator {

  def main(args: Array[String]): Unit = {
    // 6. 运算符的本质
    val n1: Int = 12
    val n2: Int = 37
    //1. 当调用对象的方法时，.可以省略
    println(n1.+(n2)) //49
    println(n1 + n2) //49

    println(1.34.*(25)) //33.5
    println(1.34 * 25) //33.5

    //2. 如果函数参数只有一个，或者没有参数，()可以省略
    //println(1.toString())
    //println(1 toString)
    //println(1.1 toInt toString)
  }

}

```





------

# 第四章 流程控制

## 分支控制 if-else

让程序有选择的的执行，分支控制有三种：单分支、双分支、多分支

### 单分支

1. 基本语法

   ```
   if (条件表达式) {
   	执行代码块
   }
   ```

   说明：当条件表达式为 ture 时，就会执行{ }的代码。

2. 实例操作

   需求：输入人的年龄，如果该同志的年龄小于 18 岁，则输出“童年”

   ```scala
     def main(args: Array[String]): Unit = {
       println("请输入你的年龄:")
       val age: Int = StdIn.readInt()
   
       // 1. 单分支
       if (age >= 18) {
         println("成年")
       }
   
       println("===================")
     }
   }
   ```
   

### 双分支

1. 基本语法

   ```
   if (条件表达式) {
   	执行代码块 1
   } else {
   	执行代码块 2
   }
   ```

   

2. 实例操作

   需求：输入年龄，如果年龄小于 18 岁，则输出“童年”。否则，输出“成年

   ```scala
     def main(args: Array[String]): Unit = {
       println("请输入你的年龄:")
       val age: Int = StdIn.readInt()
   
       // 2. 双分支
       if (age >= 18) {
         println("成年")
       } else {
         println("未成年")
       }
     }
   }
   
   ```

### 多分支

1. 基本语法

   ```
   if (条件表达式 1) {
   	执行代码块 1
   }
   else if (条件表达式 2) {
   	执行代码块 2
   }
    ……
   else {
   	执行代码块 n
   }
   ```

   

2. 实例操作

   1. 需求 1：需求：输入年龄，如果年龄小于 18 岁，则输出“童年”。如果年龄大于 等于 18 且小于等于 30，则输出“中年”，否则，输出“老年”

      ```scala
        def main(args: Array[String]): Unit = {
          println("请输入你的年龄:")
          val age: Int = StdIn.readInt()
          // 3. 多分支
          if (age <= 6) {
            println("童年")
          } else if (age < 18) {
            println("青少年")
          } else if (age < 35) {
            println("青年")
          } else if (age < 60) {
            println("中年")
          } else {
            println("老年")
          }
        }
      }
      
      ```
   
   2. 需求 2：Scala 中 if else 表达式其实是有返回值的，具体返回值取决于满足条件的 代码体的最后一行内容
   
      ```scala
        def main(args: Array[String]): Unit = {
          println("请输入你的年龄:")
          val age: Int = StdIn.readInt()
          // 4. 分支语句的返回值
          val result: String = if (age <= 6) {
            println("童年")
            "童年"
          } else if (age < 18) {
            println("青少年")
            "青少年"
          } else if (age < 35) {
            println("青年")
            "青年"
          } else if (age < 60) {
            println("中年")
            "中年"
          } else {
            println("老年")
            "老年"
          }
          println(result)
        }
      }
      
      ```
   
   3. 需求 3：Scala 中返回值类型不一致，取它们共同的祖先类型
   
      ```scala
        def main(args: Array[String]): Unit = {
          println("请输入你的年龄:")
          val age: Int = StdIn.readInt()
          // 4. 分支语句的返回值
          val result: Any = if (age <= 6) {
            println("童年")
            "童年"
          } else if (age < 18) {
            println("青少年")
            "青少年"
          } else if (age < 35) {
            println("青年")
            age
          } else if (age < 60) {
            println("中年")
            age
          } else {
            println("老年")
            age
          }
          println(result)
        }
      }
      
      ```
   
   4. 需求 4：Java 中的三元运算符可以用 if else 实现
   
      > 如果大括号{}内的逻辑代码只有一行，大括号可以省略。如果省略大括号，if 只对最近 的一行逻辑代码起作用
   
      ```scala
        def main(args: Array[String]): Unit = {
          println("请输入你的年龄:")
          val age: Int = StdIn.readInt()
          // java中三元运算符 String res = (age >= 18)?"成年":"未成年"
          val res: String = if (age >= 18) {
            "成年"
          } else {
            "未成年"
          }
      
          val res2 = if (age >= 18) "成年" else "未成年"
        }
      }
      
      ```
      
      

## 嵌套分支

在一个分支结构中又完整的嵌套了另一个完整的分支结构，里面的分支的结构称为内层。 分支外面的分支结构称为外层分支。嵌套分支不要超过 3 层

1. 基本语法

   ```
   if(){
       if(){
       }else{
       }
   }
   ```

   

2. 实例操作

   需求：如果输入的年龄小于 18，返回“童年”。如果输入的年龄大于等于 18，需要再判 断：如果年龄大于等于 18 且小于 30，返回“中年”；如果其他，返回“老年”

   ```scala
     def main(args: Array[String]): Unit = {
       println("请输入你的年龄:")
       val age: Int = StdIn.readInt()
       // 5. 嵌套分支
       if (age >= 18) {
         println("成年")
         if (age >= 35) {
           if (age >= 60) {
             println("老年")
           } else {
             println("中年")
           }
         }
       } else {
         println("未成年")
         if (age <= 6) {
           println("童年")
         } else {
           println("青少年")
         }
       }
     }
   }
   
   ```

## Switch 分支结构

> 在 Scala 中没有 Switch，而是使用**模式匹配来处理**。 
>
> 模式匹配涉及到的知识点较为综合，因此我们放在后面讲解

## For 循环控制

Scala 也为 for 循环这一常见的控制结构提供了非常多的特性，这些 for 循环的特性被称 为 for 推导式或 for 表达式。

### 范围数据循环（To）

1. 基本语法

   ```scala
   for(i <- 1 to 3){
    	print(i + " ")
   }
   println()
   ```

   1. i 表示循环的变量，<- 规定 to
   2. i 将会从 1-3 循环，前后闭合

2. 实例

   需求：输出 5 句 "宋宋，告别海狗人参丸吧"

   ```scala
   object Test02_ForLoop {
   
     def main(args: Array[String]): Unit = {
       // java for语法： for(int i = 0; i < 10; i++){ System.out.println(i + ". hello world") }
       // 1. 范围遍历
       for (i <- 1 to 3) {
         println(i + " hello world")
       }
       // 与上面等价
       for (i: Int <- 1.to(3)) {
         println(i + " hello world")
       }
         
     }
   
   }
   ```

### 范围数据循环（Until）

1. 基本语法

   ```scala
   for(i <- 1 until 3) {
    	print(i + " ")
   }
   println()
   ```

   1. 这种方式和前面的区别在于 i 是从 1 到 3-1
   2. 即使前闭合后开的范围

2. 实例

   需求：输出 5 句 "宋宋，告别海狗人参丸吧"

   ```scala
   	object Test02_ForLoop {
   
     def main(args: Array[String]): Unit = {
      /* for (i <- Range(1, 3)) {
         println(i + " hello world")
       }*/
       // 与上面等价
       for (i <- 1 until (3)) {
         println(i + " hello world")
       }
         
     }
   
   }
   ```
   
   ```scala
       // 2.集合
       for (i <- Array(11, 22, 33)) {
         println(i)
       }
   
       for (i <- List(22, 33, 44)) {
         println(i)
       }
       for (i <- Set(33, 44, 55)) {
         println(i)
       }
   ```
   

### 循环守卫

1. 基本语法

   ```scala
   for(i <- 1 to 3 if i != 2) {
    	print(i + " ")
   }
   println()
   ```

   1. 循环守卫，即循环保护式（也称条件判断式，守卫）。保护式为 true 则进入循环 体内部，为 false 则跳过，类似于 continue

   2. 上面的代码等价

      ```scala
      for (i <- 1 to 3){
      	if (i != 2) {
          	print(i + " ")
          }
      }
      ```

2. 实例操作

   需求：输出 1 到 10 中，不等于 5 的值

   ```scala
       // 3. 循环守卫
       for (i <- 1 to 10) {
         if (i != 5) {
           println(i)
         }
       }
   
       for (i <- 1 to 10 if i != 5) {
         println(i)
       }
   ```

   


### 循环步长

1. 基本语法

   ```scala
   for (i <- 1 to 10 by 2) {
    	println("i=" + i)
   }
   ```

   说明：by 表示步长

2. 实例操作

   需求：输出 1 到 10 以内的所有奇数

   ```scala
       // 4. 循环步长
       for (i <- 1 to 10 by 2) {
         println(i)
       }
   ```
   
   输出结果
   
   ```
   1
   3
   5
   7
   9
   ```
   
   > 注意:error，step不能为0
   >
   > ```scala
   > /*    
   > for (i <- 30 to 13 by 0) {
   >       println(i)
   >     }
   > */
   > ```
   
   > 可以小数的方式进行递增
   >
   > ```scala
   >     for (i <- 1.0 to 10.0 by 0.3) {
   >       println(i)
   >     
   > ```

### 嵌套循环

1. 基本语法

   ```scala
       for (i <- 1 to 3; j <- 1 to 3) {
         println("i = " + i + " j = " + j)
       }
   ```

   说明：没有关键字，所以范围后一定要加；来隔断逻辑

2. 基本语法

   上面的代码等价

   ```scala
       for (i <- 1 to 3) {
         for (j <- 1 to 3) {
           println("i = " + i + " j = " + j)
         }
       }
   ```



### 引入变量

1. 基本语法

   ```scala
       for (i <- 1 to 10; j = 10 - i) {
         println("i = " + i + " j = " + j)
       }
   ```

   说明：

   1. for 推导式一行中有多个表达式时，所以要加 ; 来隔断逻辑 
   2. for 推导式有一个不成文的约定：当 for 推导式仅包含单一表达式时使用圆括号， 当包含多个表达式时，一般每行一个表达式，并用花括号代替圆括号，如下

   ```scala
       for {i <- 1 to 10
            j = 10 - i
       } {
         println("i = " + i + " j = " + j)
       }
   ```

2. 实例操作

   上面的代码等价于

   ```scala
       for (i <- 1 to 10) {
         val j = 10 - i
         println("i = " + i + " j = " + j)
       }
   ```

### 循环返回值

基本语法

```scala
    val b = for (i <- 1 to 10) yield i * i
    println(b)//Vector(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
```

> **说明：将遍历过程中处理的结果返回到一个新 Vector 集合中，使用 yield 关键字。** 
>
> **注意：开发中很少使用。**

```scala
    val a = for (i <- 1 to 10) {
      i
    }
    println("a = " + a)	//a = ()
```

> 如有多个返回时默认只为空的,必须使用其yield关键字才可把遍历结果放到集合中
>

### 倒序打印

1. 说明：如果想倒序打印一组数据，可以用 reverse

2. 实例操作

   需求：倒序打印 10 到 1

   ```scala
       for (i <- 1 to 10 reverse) {
         println(i)
       }
   ```

   ```
   10
   9
   8
   7
   6
   5
   4
   3
   2
   1
   ```

## While 和 do..While 循环控制

> While 和 do..While 的使用和 Java 语言中用法相同

### While 循环控制

1. 基本语法

   ```
   循环变量初始化
   while (循环条件) {
        循环体(语句)
        循环变量迭代
   }
   ```

   说明：

   1. 循环条件是返回一个布尔值的表达式
   2. while 循环是先判断再执行语句
   3. 与 for 语句不同，**while 语句没有返回值**，即整个 while 语句的**结果是 Unit 类型()**
   4. **因为 while 中没有返回值**，所以当要用该语句来计算并返回结果时，就不可避免的使用变量，而变量需要声明在 while 循环的外部，那么就等同于循环的内部对外部的变量造成了影响，所以不推荐使用，**而是推荐使用 for 循环**

2. 实例操作

   需求：输出 5 句 "this is a while loop"

   ```scala
     def main(args: Array[String]): Unit = {
       var a: Int = 5
       while (a >= 1) {
         println("this is a while loop:" + a)
         a -= 1
       }
     }
   
   }
   ```
   

### do..while 循环控制

1. 基本语法

   ```
    循环变量初始化;
    do{
        循环体(语句)
        循环变量迭代
    } while(循环条件)
   ```

   说明

   1. 循环条件是返回一个布尔值的表达式
   2. do..while 循环是先执行，再判断

2. 实例操作

   需求：输出 1 句 "this is a do-while loop:"

   ```scala
     def main(args: Array[String]): Unit = {
       var b: Int = 0
       do {
         println("this is a do-while loop:" + b)
         b -= 1
       } while (b > 0)
     }
   
   }
   ```

### 循环中断

1. 基本主明

   Scala 内置控制结构特地去掉了 `break `和 `continue`，是为了更好的适应函数式编程，推荐使用函数式的风格解决`break`和`continue`的功能，而不是一个关键字。Scala中使用`breakable `控制结构来实现 `break `和 `continue `功能

2. 实例操作

   java代码退出循环

   ```java
   public class TestBreak {
       public static void main(String[] args) {
           try {
   
               for (int i = 0; i < 5; i++) {
                   if (i == 3)
                       // break
                       throw new RuntimeException();
                   System.out.println(i);
               }
           } catch (Exception e) {
               // 什么都不做，只是退出循环
           }
           System.out.println("这是循环外的代码");
       }
   }
   
   ```

   需求 1：采用异常的方式退出循环

   ```scala
   object Test06_Break {
   
     def main(args: Array[String]): Unit = {
       // 1. 采用抛出异常的方式，退出循环
       try {
         for (i <- 0 until 5) {
           if (i == 3)
             throw new RuntimeException
           println(i)
         }
       } catch {
         case e: Exception => // 什么都不做，只是退出循环
       }
     }
   }
   ```

   需求 2：采用 Scala 自带的函数，退出循环

   ```scala
       // 2. 使用Scala中的Breaks类的break方法，实现异常的抛出和捕捉
       Breaks.breakable(
         for (i <- 0 until 5) {
           if (i == 3)
             Breaks.break()
           println(i)
         }
   
       )
   ```

   需求 3：对 break 进行省略
   
   ```scala
       Breaks.breakable(
         for (i <- 0 until 5) {
           if (i == 3)
             break()
           println(i)
         }
       )
   ```
   
   需求 4：循环遍历 10 以内的所有数据，奇数打印，偶数跳过（continue）
   
   ```scala
   object TestBreak {
        def main(args: Array[String]): Unit = {
            for (elem <- 1 to 10) {
                if (elem % 2 == 1) {
                	println(elem)
                } else {
                	println("continue")
                }
            }
        }
   }
   ```

## 多重循环

1. 基本说明

   1. 将一个循环放在另一个循环体内，就形成了嵌套循环。其中，for，while，do…while 均可以作为外层循环和内层循环。【建议一般使用两层，最多不要超过 3 层】
   2. 设外层循环次数为 m 次，内层为 n 次，则内层循环体实际上需要执行 m*n 次。

2. 实例操作

   需求：打印出九九乘法表

   ```scala
   object Test03_Practice_MulTable {
   
     def main(args: Array[String]): Unit = {
       for (i <- 1 to 9) {
         for (j <- 1 to i) {
           print(s"$j * $i = ${i * j}\t")
         }
         println()
       }
   
       // 简写
       for (i <- 1 to 9; j <- 1 to i) {
         print(s"$j * $i = ${i * j}\t")
         if (j == i) println()
       }
     }
   
   }
   
   ```
   
   需求,打印输出一个九层妖塔
   
   ```scala
   object Test04_Practice_Pyramid {
   
     def main(args: Array[String]): Unit = {
       for (i <- 1 to 9) {
         val stars = 2 * i - 1
         val spaces = 9 - i
         println(" " * spaces + "*" * stars)
       }
   
       // 简写,其效果与上一样
       for (i <- 1 to 9; stars = 2 * i - 1; spaces = 9 - i) {
         println(" " * spaces + "*" * stars)
       }
   
       // 继续优化,与上效果一样
       for (stars <- 1 to 17 by 2; spaces = (17 - stars) / 2) {
         println(" " * spaces + "*" * stars)
       }
   
     }
   
   }
   ```
   
   
   
   ------
   
   

# 第五章 函数式编程

1. 面向对象编程

   解决问题，分解对象，行为，属性，然后通过对象的关系以及行为的调用来解决问题

   - 对象：用户
   - 行为：登录、连接 JDBC、读取数据库
   - 属性：用户名、密码

2. 函数式编程

   解决问题时，将问题分解成一个一个的步骤，将每个步骤进行封装（函数），通过调用这些封装好的步骤，解决问题

   例如：请求->用户名、密码->连接 JDBC->读取数据库

   > Scala 语言是一个完全面向对象编程语言。万物皆对象
   >
   > 对象的本质：对数据和行为的一个封装

3. 在 Scala 中函数式编程和面向对象编程完美融合在一起了



## 函数基础

### 函数基本语法

1. 基本语法

   ![](http://www.dxb02.top/photos/scala/03.jpg)

2. 实例操作

   需求：定义一个函数，实现将传入的名称打印出来

   ```scala
     def main(args: Array[String]): Unit = {
       // 定义函数
       def sayHi(name: String): Unit = {
         println("hi, " + name)
       }
       // 调用函数
       sayHi("tom")
   
       // 调用对象方法
       Test01_FunctionAndMethod.sayHi("jack")
   
       // 获取方法返回值
       val result = Test01_FunctionAndMethod.sayHello("marry")
       println(result)
   
     }
   
   
     // 定义对象的方法
     def sayHi(name: String): Unit = {
       println("Hi , " + name)
     }
   
     def sayHello(name: String): String = {
       println("Hello , " + name)
       return "Hello"
     }
   }
   ```

### 函数和方法的区别

1. 核心概念

   1. 为完成某一功能的程序语句的集合，称为函数
   2. 类中的函数称之方法

2. 案例实操

   1. Scala 语言可以在任何的语法结构中声明任何的语法
   2. 函数没有重载和重写的概念；方法可以进行重载和重写
   3. Scala 中函数可以嵌套定义

   ```scala
   object Test00_FunctionAndMethod {
   
     //2. 方法可以进行重载和重写，程序可以执行
     def main(): Unit = {
   
     }
   
     def main(args: Array[String]): Unit = {
       // 1. Scala 语言可以在任何的语法结构中声明任何的语法
       import java.util.Date
       new Date()
   
   
       // 2. 函数没有重载和重写的概念，程序报错
       def test(): Unit = {
         println("无参,无返回值")
       }
   
       test()
   
       /*    def test(name: String): Unit = {
             println()
           }*/
   
       //3. Scala 中函数可以嵌套定义
       def test2(): Unit = {
         def test3(name: String): Unit = {
           println("函数可以嵌套定义")
         }
       }
   
   
     }
   
   }
   ```

### 函数定义

1. 函数定义

   1. 函数 1：无参，无返回值
   2. 函数 2：无参，有返回值
   3. 函数 3：有参，无返回值
   4. 函数 4：有参，有返回值
   5. 函数 5：多参，无返回值
   6. 函数 6：多参，有返回值

2. 实例操作

   ```scala
   package com.scala.chapter05
   
   object Test02_FunctionDefine {
   
     def main(args: Array[String]): Unit = {
       //1. 函数1：无参，无返回值
       def f1(): Unit = {
         println("1. 无参,无返回值")
       }
   
       f1() //1. 无参,无返回值
   
       /*
       1. 无参,无返回值
       ()
        */
       println(f1())
   
       println("====================")
   
   
       // 2. 函数2：无参，有返回值
       def f2(): Int = {
         println("2.无参,有返回值")
         return 11
       }
   
       /*
       2.无参,有返回值
       11
        */
       println(f2())
   
   
       println("====================")
   
       // 3. 函数3：有参，无返回值
       def f3(name: String): Unit = {
         println("3：有参，无返回值 " + name)
       }
   
       /*
       3：有参，无返回值 jack
       ()
        */
       println(f3("jack"))
   
   
       println("====================")
   
       // 4. 函数4：有参，有返回值
       def f4(name: String): Unit = {
         println("4：有参，有返回值 " + name)
         return "hi, " + name
       }
   
       /*
       4：有参，有返回值 tom
       ()
        */
       println(f4("tom"))
       println("====================")
   
       // 5. 函数5：多参，无返回值
       def f5(name1: String, name2: String): Unit = {
         println("5：多参，无返回值")
         println(s"${name1}和${name2}都是我的好朋友")
       }
   
       /*
       5：多参，无返回值
       lee和chen都是我的好朋友
        */
       f5("lee", "chen")
       println("====================")
   
   
       // 6. 函数6：多参，有返回值
       def f6(a: Int, b: Int): Int = {
         println("6：多参，有返回值")
         return a + b
       }
   
       /*
       6：多参，有返回值
       33
        */
       println(f6(11, 22))
     }
   
   }
   ```
   
   

### 函数参数

1. 案例实操

   1. 可变参数
   2. 如果参数列表中存在多个参数，那么可变参数一般放置在最后
   3. 参数默认值，一般将有默认值的参数放置在参数列表的后面
   4. 带名参数

   ```scala
   package com.scala.chapter05
   
   object Test03_FunctionParameter {
   
     def main(args: Array[String]): Unit = {
       // 1. 可变参数
       def f1(str: String*): Unit = {
         println(str)
       }
   
       f1("jack") //ArraySeq(jack)
       f1("aaa", "bbb", "ccc") //ArraySeq(aaa, bbb, ccc)
       println("==============================")
   
   
       // 2. 如果参数列表中存在多个参数，那么可变参数一般放置在最后
       def f2(str1: String, str2: String*): Unit = {
         println("str1:" + str1 + " str2:" + str2)
       }
   
       f2("tom") //str1:tom str2:List()
       f2("aaa", "bbb", "ccc") //str1:aaa str2:ArraySeq(bbb, ccc)
   
       println("==============================")
   
   
       //3 . 数默认值，一般将有默认值的参数放置在参数列表的后面
       def f3(name: String = "sanshang"): Unit = {
         println("My name is " + name)
       }
   
       f3("julia") //My name is julia
       f3() //My name is sanshang
   
       println("==============================")
   
       //4. 带名参数
       def f4(name: String = "sanshang", age: Int): Unit = {
         println(s"${age} old ${name} learning at school")
       }
   
       f4("marry", 18) //18 old marry learning at school
       f4(age = 11, name = "karry") //11 old karry learning at school
       f4(age = 19) //19 old sanshang learning at school
   
     }
   
   }
   ```
   
   

### 函数至简原则（重点）

函数至简原则：能省则省

1. 至简原则细节

   1. return 可以省略，Scala 会使用函数体的最后一行代码作为返回值
   2. 如果函数体只有一行代码，可以省略花括号
   3. 返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）
   4. 如果有 return，则不能省略返回值类型，必须指定
   5. 如果函数明确声明 unit，那么即使函数体中使用 return 关键字也不起作用
   6. Scala 如果期望是无返回值类型，可以省略等号
   7. 如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
   8. 如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
   9. 如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略

2. 实例操作

   ```scala
   package com.scala.chapter05
   
   
   object Test04_Simplify {
   
     def main(args: Array[String]): Unit = {
   
       // 0.函数标准写法
       def f0(name: String): Unit = {
         return name
       }
   
       println(f0("jack")) //()
   
       println("==========================")
   
       // 函数至简原则
       // 2. return可以省略，Scala会使用函数体的最后一行代码作为返回值
       def f1(name: String): Unit = {
         name
       }
   
       println(f0("jack")) //()
       println("==========================")
   
       //2. 如果函数体只有一行代码，可以省略花括号
       def f2(name: String): String = name
   
       println(f2("jack")) //jack
       println("==========================")
   
       //3. 返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）
       def f3(name: String) = name
   
       println(f3("jack")) //jack
       println("==========================")
   
       // 4. 如果有return，则不能省略返回值类型，必须指定
       // 下面是error的必须有返回类型
       /*    def f4(name: String) = {
             return name
           }
   
           println(f4("jack"))*/
   
       println("==========================")
   
       // 5. 如果函数明确声明unit，那么即使函数体中使用return关键字也不起作用
       def f5(name: String): Unit = {
         return name
       }
   
       println(f5("jack")) //()
       println("==========================")
   
       // 6. Scala如果期望是无返回值类型，可以省略等号
       def f6(name: String) {
         println(name)
       }
   
       /*
       jack
       ()
        */
       println(f6("jack"))
       println("==========================")
   
       // 7. 如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
       def f7(): Unit = {
         println("hello world")
       }
   
       f7() //hello world
       f7 //hello world
   
       println("==========================")
   
       // 8. 如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
       def f8: Unit = {
         println("hello ")
       }
   
       //f8()
       f8 //hello
       println("==========================")
   
   
       //9. 如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略
       def f9(name: String): Unit = {
         println(name)
       }
   
       def f10 = (name: String) => println(name)
   
       f10("tom") //tom
   
   
     }
   
   }
   
   ```

## 函数高级

### 高阶函数

在 Scala 中，函数是一等公民。怎么体现的呢？

对于一个函数我们可以：**定义函数、调用函数**

```scala
package com.scala.chapter05

object Test06_HighOrderFunction {

  def main(args: Array[String]): Unit = {
    // 定义函数
    def f(n: Int): Int = {
      println("f 调用")
      n + 1
    }

    // 调用函数
    val result: Int = f(123) //f 调用
    println(result) //124
  }
}
```

但是其实函数还有更高阶的用法

1. 函数可以作为值进行传递

   ```scala
   package com.scala.chapter05
   
   object Test06_HighOrderFunction {
   
     def main(args: Array[String]): Unit = {
       // 定义函数
       def f(n: Int): Int = {
         println("f 调用")
         n + 1
       }
   
       // 调用函数
       val result: Int = f(123) //f 调用
       println(result) //124
   
       println("=============================")
   
       // 1. 函数作为值进行传递
       val f1: Int => Int = f
       val result2 = f1(234) //f 调用
       println(result2) //235
   
       println("----------------------")
   
       //2. 在被调用函数 foo 后面加上 _，相当于把函数 foo 当成一个整体， 传递给变量 f1
       val f2 = f _
       val result3 = f2(345) //f 调用
       println(result3) //346
   
       //3. 如果明确变量类型，那么不使用下划线也可以将函数作为整体传递给 变量
       def fun(): Int = {
         println("fun 调用")
         2
       }
   
       val f3: () => Int = fun
       println(f3()) //2
       println("----------------------")
   
       val result4 = fun _
       println(result4) //com.scala.chapter05.Test06_HighOrderFunction$$$Lambda$21/0x0000000801096390@39ba5a14
   
     }
   
   }
   
   ```

2. 函数可以作为参数进行传递

   ```scala
   package com.scala.chapter05
   
   object Test06_HighOrderFunction {
   
     def main(args: Array[String]): Unit = {
       // 2. 函数作为参数进行传递
       // 定义二元计算函数
       //定义一个函数，函数参数还是一个函数签名；op 表示函数名称;(Int,Int) 表示输入两个 Int 参数；Int 表示函数返回值
       def dualEval(op: (Int, Int) => Int, a: Int, b: Int): Int = {
         op(a, b)
       }
   
       //定义一个函数，参数和返回值类型和 dualEval 的输入参数一致
       def add(a: Int, b: Int): Int = {
         a + b
       }
   
       //将 add 函数作为参数传递给 f1 函数，如果能够推断出来不是调用，_ 可以省略
       println(dualEval(add, 11, 22)) //33
       println(dualEval((a, b) => a + b, 11, 33)) //44
       println(dualEval(_ + _, 22, 44)) //66
   
       // 可以传递匿名函数
   
     }
   
   }
   ```

3. 函数可以作为函数返回值返回

   ```scala
   package com.scala.chapter05
   
   object Test06_HighOrderFunction {
   
     def main(args: Array[String]): Unit = {
       // 3. 函数作为函数的返回值返回
       def f5(): Int => Unit = {
         def f6(a: Int): Unit = {
           println("f6调用 : " + a)
         }
   
         f6 // 将函数直接返回
       }
   
       // 因为 f5 函数的返回值依然为函数，所以可以变量 f6 可以作为函数继续调用
       val f6 = f5()
       println(f6) //com.scala.chapter05.Test06_HighOrderFunction$$$Lambda$24/0x00000008010965e0@17c68925
       f6(66) //f6调用 : 66
       println("----------------------")
   
       // 上面的代码可以简化为
       /*
       f6调用 : 77
       ()
        */
       println(f5()(77))
   
     }
   
   }
   ```

### 匿名函数

1. 说明

   没有名字的函数就是匿名函数。

   ```
   (x:Int)=>{函数体}
   x：表示输入参数类型；Int：表示输入参数类型；函数体：表示具体代码逻辑
   ```

2. 实例操作

   需求 1：传递的函数有一个参数

   传递匿名函数至简原则：

   1. **参数的类型可以省略，会根据形参进行自动的推导**
   2. **类型省略之后，发现只有一个参数，则圆括号可以省略；其他情况：没有参数和参 数超过 1 的永远不能省略圆括号**
   3. **匿名函数如果只有一行，则大括号也可以省略**
   4. **如果参数只出现一次，则参数省略且后面参数可以用_代替**

   ```scala
   package com.scala.chapter05
   
   object Test05_Lambda {
   
     def main(args: Array[String]): Unit = {
   
   
       val fun = (name: String) => {
         println(name)
       }
   
       fun("hello") //hello
   
       println("========================")
   
       // 定义一个函数，以函数作为参数输入
       def f(func: String => Unit): Unit = {
         println("hello world")
       }
   
       f(fun) //hello world
   
       // 下面输出结果与上相同,把函数作为参数直接传入
       f((name: String) => {
         println(name) //hello world
       })
   
       println("========================")
   
       // 匿名函数的简化原则
       // 1. 参数的类型可以省略，会根据形参进行自动的推导
       f((name) => {
         println(name) //hello world
       })
       println("========================")
   
       //2. 类型省略之后，发现只有一个参数，则圆括号可以省略；其他情况：没有参数和参数超过1的永远不能省略圆括号。
       f(name => {
         println(name)
       })
   
       println("========================")
       // 3. 匿名函数如果只有一行，则大括号也可以省略
       f(name => println(name)) //hello world
   
       println("========================")
   
       // 4. 如果参数只出现一次，则参数省略且后面参数可以用_代替
       f(println(_)) //hello world
   
       println("========================")
       // 5. 如果可以推断出，当前传入的println是一个函数体，而不是调用语句，可以直接省略下划线
       f(println) //hello world
   
     }
   
   }
   
   ```

   需求 2：传递的函数有两个参数

   ```scala
   package com.scala.chapter05
   
   object Test05_Lambda {
   
     def main(args: Array[String]): Unit = {
   
       // 实际示例，定义一个”二元运算“函数，只操作1和2两个数，但是具体运算通过参数传入
       def dualFunctionOneAndTwo(function: (Int, Int) => Int): Int = {
         function(1, 2)
       }
   
       val add = (a: Int, b: Int) => a + b
       val minus = (a: Int, b: Int) => a - b
   
   
       println(dualFunctionOneAndTwo(add)) //3
       println(dualFunctionOneAndTwo(minus)) //-1
   
       // 匿名函数简化
       println("---------------------")
       println(dualFunctionOneAndTwo((a: Int, b: Int) => a + b))
       println(dualFunctionOneAndTwo((a: Int, b: Int) => a - b))
   
       println("---------------------")
       //如下继续简化
       println(dualFunctionOneAndTwo((a, b) => a + b)) //3
   
       println("---------------------")
       // 继续优化
       println(dualFunctionOneAndTwo(_ + _)) //3
       println(dualFunctionOneAndTwo(_ - _)) //-1
   
       println("---------------------")
   
       // 当第二参数减第一参数时,如下
       println(dualFunctionOneAndTwo((a, b) => b - a)) //1
       println(dualFunctionOneAndTwo(-_ + _)) //1
     }
   
   }
   
   ```

3. 扩展练习

   练习 1：定义一个匿名函数，并将它作为值赋给变量 fun。函数有三个参数，类型分别为 Int，String，Char，返回值类型为 Boolean

   要求调用函数 fun(0, “”, ‘0’)得到返回值为 false，其它情况均返回 true。

   ```scala
       val fun = (i: Int, s: String, c: Char) => {
         if (i == 0 && s == "" && c == '0') false else true
       }
   
       println(fun(0, "", 0)) //true
       println(fun(0, "", '1')) //true
       println(fun(23, "", '0')) //true
       println(fun(0, "hello", '0')) //true
       println(fun(0, "", '0')) //false
   ```
   
   练习 2： 定义一个函数 func，它接收一个 Int 类型的参数，返回一个函数（记作 f1）。 它返回的函数 f1，接收一个 String 类型的参数，同样返回一个函数（记作 f2）。函数 f2 接 收一个 Char 类型的参数，返回一个 Boolean 的值。 
   
   要求调用函数 func(0) (“”) (‘0’)得到返回值为 false，其它情况均返回 true
   
   ```scala
       def func(i: Int): String => (Char => Boolean) = {
         def f1(s: String): Char => Boolean = {
           def f2(c: Char): Boolean = {
             if (i == 0 && s == "" && c == '0') false else true
           }
   
           f2
         }
   
         f1
       }
   
       println(func(0)("")('0')) //false
       println(func(0)("")('1')) //true
       println(func(23)("")('0')) //true
       println(func(0)("hello")('0')) //true
   
       println("------------------------------")
   
       // 匿名函数简写
       def func1(i: Int): String => (Char => Boolean) = {
         s => c => if (i == 0 && s == "" && c == '0') false else true
       }
   
       println(func1(0)("")('0')) //false
       println(func1(0)("")('1')) //true
       println(func1(23)("")('0')) //true
       println(func1(0)("hello")('0')) //true
   
       println("------------------------------")
   
       // 柯里化
       def func2(i: Int)(s: String)(c: Char): Boolean = {
         if (i == 0 && s == "" && c == '0') false else true
       }
   
       println(func2(0)("")('0')) //false
       println(func2(0)("")('1')) //true
       println(func2(23)("")('0')) //true
       println(func2(0)("hello")('0')) //true
   
   ```

### 高阶函数案例

需求：模拟 Map 映射、Filter 过滤、Reduce 聚合

```scala
package com.scala.chapter05

import scala.collection.mutable.ArrayBuffer


object Test07_Practice_CollectionOperation {

  def main(args: Array[String]): Unit = {

    val arr: Array[Int] = Array(11, 22, 33, 44, 55, 66)

    // 对数组进行处理，将操作抽象出来，处理完毕之后的结果返回一个新的数组
    def arrayOperation(array: Array[Int], op: Int => Int): Array[Int] = {
      for (elem <- array) yield op(elem)
    }

    // 定义一个加一操作
    def addOne(elem: Int): Int = {
      elem + 1
    }

    //调用函数
    val newArray: Array[Int] = arrayOperation(arr, addOne)
    println(newArray.mkString(",")) //12,23,34,45,56,67

    println("---------------------")

    // 传入匿名函数，实现元素翻倍
    val newArray2 = arrayOperation(arr, _ * 2)
    println(newArray2.mkString(",")) //22,44,66,88,110,132

    println("===================")

    //1 .Map映射
    def map(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }

    val mapArr = map(Array(1, 2, 3, 4, 5), (x: Int) => {
      x * x
    })

    println(mapArr.mkString(","))
    println("===================")

    // 2. filter 过滤。有参数，且参数再后面只使用一次，则参数省略且 后面参数用_表示
    def filter(arr: Array[Int], op: Int => Boolean) = {
      var arr1: ArrayBuffer[Int] = ArrayBuffer[Int]()
      for (elem <- arr if op(elem)) {
        arr1.append(elem)
      }
      arr1.toArray
    }

    var arr1 = filter(Array(1, 2, 3, 4), _ % 2 == 1)
    println(arr1.mkString(",")) //1,3

    println("======================")

    //3. reduce 聚合。有多个参数，且每个参数再后面只使用一次，则参数省略且后面参数用_表示，第 n 个_代表第 n 个参数
    def reduce(arr: Array[Int], op: (Int, Int) => Int) = {
      var init: Int = arr(0)
      for (elem <- 1 to arr.length) {
        init = op(init, elem)
      }
      init
    }

    val arr2 = reduce(Array(1, 2, 3, 4), (x, y) => x * y)
    println(arr2) //24
    val arr3 = reduce(Array(1, 2, 3, 4), _ * _)
    println(arr3) //24
  }

}
```

### 函数柯里化&闭包

闭包：函数式编程的标配

1. 说明

   - 闭包：如果一个函数，访问到了它的外部（局部）变量的值，那么这个函数和他所处的 环境，称为闭包
   - 函数柯里化：把一个参数列表的多个参数，变成多个参数列表。

2. 实例

   闭包

   ```scala
   package com.scala.chapter05
   
   object Test09_ClosureAndCurrying {
   
     def main(args: Array[String]): Unit = {
       def add(a: Int, b: Int): Int = {
         a + b
       }
   
   
       // 1. 考虑固定一个加数的场景
       def addByFour(b: Int): Int = {
         4 + b
       }
   
       // 2. 扩展固定加数改变的情况
       def addByFive(b: Int): Int = {
         5 + b
       }
   
       // 3. 将固定加数作为另一个参数传入，但是是作为”第一层参数“传入
       def addByFour1(): Int => Int = {
         val a = 4
   
         def addB(b: Int): Int = {
           a + b
         }
   
         addB
       }
   
       // 下面修改为参数a为也可作为传参
       def addByA(a: Int): Int => Int = {
         def addB(b: Int): Int = {
           a + b
         }
   
         addB
       }
   
       println(addByA(11)(22)) //33
   
      // 在调用时，f1 函数执行完毕后，局部变量 a 应该随着栈空间释放掉
       val addByFour2 = addByA(11)
       val addByFive2 = addByA(22)
   
       println(addByFour2(22)) //33
       println(addByFive2(33)) //55
   
     }
   
   
   }
   
   ```

   柯里化

   ```scala
   package com.scala.chapter05
   
   object Test09_ClosureAndCurrying {
   
     def main(args: Array[String]): Unit = {
       // 4. lambda表达式简写
       def addByA1(a: Int): Int => Int = {
         (b: Int) => {
           a + b
         }
       }
   
       // 下面继续优化,可以简写为
       def addByA2(a: Int): Int => Int = {
         b => a + b
       }
   
       // 续优化,可以简写为
       def addByA3(a: Int): Int => Int = a + _
   
       val addByFour3 = addByA3(11)
       val addByFive3 = addByA3(22)
   
       println(addByFour3(22)) //33
       println(addByFive3(33)) //55
   
       println("============================")
   
       // 5. 柯里化
       def addCurrying(a: Int)(b: Int): Int = {
         a + b
       }
   
       println(addCurrying(11)(22)) //33
   
     }
   }
   ```

### 递归

1. 说明

   一个函数/方法在函数/方法体内又调用了本身，我们称之为递归调用

2. 实例操作

   ```scala
   package com.scala.chapter05
   
   import scala.annotation.tailrec
   
   object Test10_Recursion {
   
     def main(args: Array[String]): Unit = {
       println(fact(5)) //120
   
       println(tailFact(5)) //120
     }
   
     // 阶乘
     // 递归算法
     // 1) 方法调用自身
     // 2) 方法必须要有跳出的逻辑
     // 3) 方法调用自身时，传递的参数应该有规律
     // 4) scala 中的递归必须声明函数返回值类型
     
     // 递归实现计算阶乘
     def fact(n: Int): Int = {
       if (n == 0) return 1
       fact(n - 1) * n
     }
   
     /*
     递归缺点就是不断在栈空间里创建函数,造成内存资源浪费,直到return才会进行销毁
     尾递归解决了内存资源浪费的问题,把每次的计算结果直接存在变量里,计算完可直接销毁方法线空间的内存
      */
     // 尾递归实现
     def tailFact(n: Int): Int = {
       //尾递归特有的注解,只在尾递归函数里才会有作用
       @tailrec
       def loop(n: Int, currRes: Int): Int = {
         if (n == 0) return currRes
         loop(n - 1, currRes * n)
       }
   
       loop(n, 1)
     }
   }
   
   ```
   

### 控制抽象

1. 值调用：把计算后的值传递过去

   ```scala
   package com.scala.chapter05
   
   object Test11_ControlAbstraction {
   
     def main(args: Array[String]): Unit = {
       //1. 传值参数
       def f0(a: Int): Unit = {
   
         println("a: " + a)
         println("b: " + a)
       }
   
       /*
       a: 11
       b: 11
        */
       f0(11)
       println("------------------------")
   
       def f1(): Int = {
         println("f1调用")
         22
       }
   
       /*
       f1调用
       a: 22
       b: 22
        */
       f0(f1())
   
     }
   
   }
   
   ```

2. 名调用：把代码传递过去

   ```scala
   
   package com.scala.chapter05
   
   object Test11_ControlAbstraction {
   
     def main(args: Array[String]): Unit = {
       // 2. 传名参数，传递的不再是具体的值，而是代码块
       def f2(a: => Int): Unit = { //注意这里变量 a 没有小括号了
         println("a: " + a)
         println("a: " + a)
       }
   
       /*
       a: 23
       a: 23
        */
       f2(23)
       println("----------------------------")
   
       /*
       f1调用
       a: 22
       f1调用
       a: 22
        */
       f2(f1())
     }
   
   }
   
   ```

   > **注意：Java 只有值调用；Scala 既有值调用，又有名调用**

3. 实例操作

   ```scala
   package com.scala.chapter05
   
   object Test11_ControlAbstraction {
   
     def main(args: Array[String]): Unit = {
       // 1.传递代码块
       //上面也可以简化如下
       /*
       this is code block
       a: 33
       this is code block
       a: 33
        */
       f2({
         println("this is code block")
         33
       })
   
       println("----------------------------")
       //2. 小括号可以省略
       /*
       this is code block
       a: 44
       this is code block
       a: 44
        */
       f2 {
         println("this is code block")
         44
       }
   
     }
   
   }
   
   ```
   
   自定义一个 While 循环
   
   ```scala
   package com.scala.chapter05
   
   object Test12_MyWhile {
     def main(args: Array[String]): Unit = {
       var n = 10
       // 1. 常规的while循环
       while (n >= 1) {
         println(n)
         n -= 1
       }
   
       // 2. 用闭包实现一个函数，将代码块作为参数传入，递归调用
       def myWhile(condition: => Boolean): (=> Unit) => Unit = {
   
         def doLoop(op: => Unit): Unit = {
           if (condition) {
             op
             myWhile(condition)(op)
           }
   
         }
   
         doLoop _
       }
   
   
       println("=================")
       n = 10
       myWhile(n >= 1) {
         println(n)
         n -= 1
       }
   
       println("=================")
   
       // 3. 用匿名函数实现
       def myWhile2(condition: => Boolean): (=> Unit) => Unit = {
         // 内层函数需要递归调用，参数就是循环体
         op => {
           if (condition) {
             op
             myWhile2(condition)(op)
           }
         }
       }
   
       n = 10
       myWhile2(n >= 1) {
         println(n)
         n -= 1
       }
   
       println("=================")
   
       // 4. 用柯里化实现
       def myWhile3(condition: => Boolean)(op: => Unit): Unit = {
         if (condition) {
           op
           myWhile3(condition)(op)
         }
   
       }
   
       n = 10
       myWhile3(n >= 1) {
         println(n)
         n -= 1
       }
   
     }
   }
   ```

### 惰性加载

1. 说明

   当函数返回值被声明为 lazy 时，函数的执行将被推迟，直到我们首次对此取值，该函数才会执行。这种函数我们称之为惰性函数

2. 实例操作

   ```scala
   package com.scala.chapter05
   
   object Test13_Lazy {
   
     def main(args: Array[String]): Unit = {
   
       val result: Int = sum(11, 22)
   
       /*
       3. sum调用
       1. 函数调用
       2. result = 33
       4. result = 33
        */
       println("1. 函数调用")
       println("2. result = " + result)
       println("4. result = " + result)
   
       println("======================")
   
       lazy val result2: Int = sum(11, 22)
   
       /*
       1. 函数调用
       3. sum调用
       2. result = 33
       4. result = 33
        */
       println("1. 函数调用")
       println("2. result = " + result2)
       println("4. result = " + result2)
     }
   
     def sum(a: Int, b: Int): Int = {
       println("3. sum调用")
       a + b
     }
   }
   ```
   
   > **注意：lazy 不能修饰 var 类型的变量**



------

# 第六章 面向对象

Scala 的面向对象思想和 Java 的面向对象思想和概念是一致的。 

Scala 中语法和 Java 不同，补充了更多的功能

## Scala 包

1. 基本语法

   ```
   package 包名
   ```

2. Scala 包的三大作用（和 Java 一样）

   1. 区分相同名字的类
   2. 当类很多时，可以很好的管理类
   3. 控制访问范围

### 包的命名

1. 命名规则

   只能包含数字、字母、下划线、小圆点.，但不能用数字开头，也不要使用关键字

2. 实例操作

   ```
   demo.class.exec1 //错误，因为 class 关键字
   demo.12a //错误，数字开头
   ```

3. 命名规范

   一般是小写字母+小圆点

   ```
   com.公司名.项目名.业务模块名
   ```

4. 实例操作

   ```scala
   
   ```



###  包说明（包语句）

1. 说明

   Scala 有两种包的管理风格，一种方式和 Java 的包管理风格相同，每个源文件一个包（包 名和源文件所在路径不要求必须一致），包名用“.”进行分隔以表示包的层级关系，如 com.atguigu.scala。另一种风格，通过嵌套的风格表示层级关系，如下

   ```scala
   
   ```

   第二种风格有以下特点

   1. 一个源文件中可以声明多个 package
   2. 子包中的类可以直接访问父包中的内容，而无需导包

2. 实例操作

   ```scala
   
   ```

   

### 包对象

在 Scala 中可以为每个包定义一个同名的包对象，定义在包对象中的成员，作为其对 应包下所有 class 和 object 的共享变量，可以被直接访问。

1. 定义

   ```scala
   
   ```

   

2. 说明

   1. 若使用 Java 的包管理风格，则包对象一般定义在其对应包下的 package.scala 文件中，包对象名与包名保持一致

      ![](http://www.dxb02.top/photos/scala/04.jpg)

   2. 如采用嵌套方式管理包，则包对象可与包定义在同一文件中，但是要保证包对象 与包声明在同一作用域中

      ```scala
      
      ```





### 导包说明

1. 和 Java 一样，可以在顶部使用 import 导入，在这个文件中的所有类都可以使用

2. 局部导入：什么时候使用，什么时候导入。**在其作用范围内都可以使用**

3. 通配符导入：`import java.util._`

4. 给类起名：`import java.util.{ArrayList=>JL}`

5. 导入相同包的多个类：`import java.util.{HashSet, ArrayList}`

6. 屏蔽类：`import java.util.{ArrayList =>_,_}`

7. 导入包的绝对路径：`new _root_.java.util.HashMap`

   ```scala
   
   ```

说明



| `import com.atguigu.Fruit`              | 引入 com.atguigu 包下 Fruit（class 和 object）              |
| --------------------------------------- | ----------------------------------------------------------- |
| `import com.atguigu._ `                 | 引入 com.atguigu 下的所有成员                               |
| `import com.atguigu.Fruit._ `           | 引入 Fruit(object)的所有成员                                |
| `import com.atguigu.{Fruit,Vegetable}`  | 引入 com.atguigu 下的 Fruit 和 Vegetable                    |
| `import com.atguigu.{Fruit=>Shuiguo}`   | 引入 com.atguigu 包下的 Fruit 并更名为 Shuiguo              |
| `import com.atguigu.{Fruit=>Shuiguo,_}` | 引入 com.atguigu 包下的所有成员，并将 Fruit 更名 为 Shuiguo |
| `import com.atguigu.{Fruit=>_,_} `      | 引入 com.atguigu 包下屏蔽 Fruit 类                          |
| `new _root_.java.util.HashMap`          | 引入的 Java 的绝对路径                                      |

> 注意
>
> Scala 中的三个默认导入分别是
>
> ```scala
> import java.lang._
> import scala._
> import scala.Predef._
> ```





## 类和对象

- 类: 可以看成一个模板
- 对象: ：表示具体的事物

### 定义类

0. 回顾：Java 中的类

   如果类是 public 的，则必须和文件名一致。

   一般，一个.java 有一个 public 类

   > **注意：Scala 中没有 public，一个.scala 中可以写多个类。**

1. 基本语法

   ```
   [修饰符] class 类名 {
    	类体
   } 
   ```

   说明

   1. Scala 语法中，类并不声明为 public，所有这些类都具有公有可见性（即默认就是 public）
   2. 一个 Scala 源文件可以包含多个类

2. 实例操作

   ```scala
   
   ```

### 属性

属性是类的一个组成部分

1. 基本语法

   ```
   [修饰符] var|val 属性名称 [：类型] = 属性值
   ```

   > 注：Bean 属性（@BeanPropetry），可以自动生成规范的 setXxx/getXxx 方法

2. 实例操作

   ```scala
   
   ```




## 封装

封装就是把抽象出的数据和对数据的操作封装在一起，数据被保护在内部，程序的其它 部分只有通过被授权的操作（成员方法），才能对数据进行操作。Java 封装操作如下，

1. 将属性进行私有化
2. 提供一个公共的 set 方法，用于对属性赋值
3. 提供一个公共的 get 方法，用于获取属性的值

Scala 中的 public 属性，底层实际为 private，并通过 get 方法（obj.field()）和 set 方法 （obj.field_=(value)）对其进行操作。所以 Scala 并不推荐将属性设为 private，再为其设置 public 的 get 和 set 方法的做法。但由于很多 Java 框架都利用反射调用 getXXX 和 setXXX 方 法，有时候为了和这些框架兼容，也会为 Scala 的属性设置 getXXX 和 setXXX 方法（通过 @BeanProperty 注解实现）。

### 访问权限

1. 说明

2. 实例操作

   ```scala
   
   ```

   
