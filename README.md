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
   com.scala.oa.model
   com.scala.oa.controller
   com.sohu.bank.order
   ```

###  包说明（包语句）

1. 说明

   Scala 有两种包的管理风格，一种方式和 Java 的包管理风格相同，每个源文件一个包（包名和源文件所在路径不要求必须一致），包名用“.”进行分隔以表示包的层级关系，如 `com.company.scala`。另一种风格，通过嵌套的风格表示层级关系，如下

   ```scala
   package com{
       package company{
           package scala
       }
   }
   ```

   第二种风格有以下特点

   1. 一个源文件中可以声明多个 package
   2. 子包中的类可以直接访问父包中的内容，而无需导包

2. 实例操作

   ```scala
   package com {
   
     import com.company.scala.Inner //父包访问子包需要导包
   
     // 在外层包中定义单例对象
     object Outer {
       var out: String = "out"
   
       def main(args: Array[String]): Unit = {
         println(Inner.in) //in
       }
     }
   
     package company {
       package scala {
         // 内层包中定义单例对象
         object Inner {
           var in: String = "in"
   
           def main(args: Array[String]): Unit = {
             println(Outer.out) //out
             Outer.out = "outter"
   
             //子包访问父包无需导包
             println(Outer.out) //outter
           }
         }
       }
   
     }
   
   }
   
   // 在同一文件中定义不同的包
   package aaa {
     package bbb {
       object Test01_Package {
         def main(args: Array[String]): Unit = {
   
           import com.company.scala.Inner
           println(Inner.in) //in
         }
       }
     }
   
   }
   ```
   

### 包对象

在 Scala 中可以为每个包定义一个同名的包对象，定义在包对象中的成员，作为其对应包下所有 class 和 object 的共享变量，可以被直接访问。

1. 定义

   ```scala
   //package.scala
   package object chapter06 {
     // 定义当前包共享的属性和方法
     val commonValue = "18"
   
     def commonMehtod() = {
       println(s"I am ${commonValue} years old")
     }
   }
   
   //Test02_PackageObject.scala
   package chapter06 {
   
     object Test02_PackageObject {
       def main(args: Array[String]): Unit = {
         commonMehtod()  //I am 18 years old
         println(commonValue)  //18
       }
     }
   }
   ```

2. 说明

   1. 若使用 Java 的包管理风格，则包对象一般定义在其对应包下的 package.scala 文件中，包对象名与包名保持一致

      ![](http://www.dxb02.top/photos/scala/04.jpg)

   2. 如采用嵌套方式管理包，则包对象可与包定义在同一文件中，但是要保证包对象 与包声明在同一作用域中

      ```scala
      package ccc {
        package ddd {
          object Test02_PackageObject {
            def main(args: Array[String]): Unit = {
              println(school) //dxb02
            }
          }
        }
      
      }
      
      package object ccc {
        val school: String = "dxb02"
      }
      ```

### 导包说明

1. 和 Java 一样，可以在顶部使用 import 导入，在这个文件中的所有类都可以使用

2. 局部导入：什么时候使用，什么时候导入。**在其作用范围内都可以使用**

3. 通配符导入：`import java.util._`

4. 给类起名：`import java.util.{ArrayList=>JL}`

5. 导入相同包的多个类：`import java.util.{HashSet, ArrayList}`

6. 屏蔽类：`import java.util.{ArrayList =>_,_}`,`ArrayList`是要屏蔽的类,`_,_`前面是要屏蔽的参数,后面的要导入的全部类

7. 导入包的绝对路径：`new _root_.java.util.HashMap`,一般开发中比较少用

   ```scala
   package java {
       package util {
           class HashMap{
               
           }
       }
   }
   ```

说明



| `import com.dxb02.Fruit`              | 引入 com.dxb02包下 Fruit（class 和 object）              |
| ------------------------------------- | -------------------------------------------------------- |
| `import com.dxb02._ `                 | 引入 com.dxb02下的所有成员                               |
| `import com.dxb02.Fruit._ `           | 引入 Fruit(object)的所有成员                             |
| `import com.dxb02.{Fruit,Vegetable}`  | 引入 com.dxb02下的 Fruit 和 Vegetable                    |
| `import com.dxb02.{Fruit=>Shuiguo}`   | 引入 com.dxb02包下的 Fruit 并更名为 Shuiguo              |
| `import com.dxb02.{Fruit=>Shuiguo,_}` | 引入 com.dxb02包下的所有成员，并将 Fruit 更名 为 Shuiguo |
| `import com.dxb02.{Fruit=>_,_} `      | 引入 com.dxb02包下屏蔽 Fruit 类                          |
| `new _root_.java.util.HashMap`        | 引入的 Java 的绝对路径                                   |

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
   // 1. Scala 语法中，类并不声明为 public，所有这些类都具有公有可见性（即默 认就是 public）
   class Person {
   
   }
   
   // 定义一个类
   // 2. 一个 Scala 源文件可以包含多个类
   class Bird {
   
   }
   ```

### 属性

属性是类的一个组成部分

1. 基本语法

   ```
   [修饰符] var|val 属性名称 [：类型] = 属性值
   ```

   > 注：Bean 属性（`@BeanPropetry`），可以自动生成规范的 `setXxx/getXxx` 方法

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   import scala.beans.BeanProperty
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-05 09:32
    * */
   object Test03_Class {
     def main(args: Array[String]): Unit = {
       // 创建一个对象
       val animal = new Bird
       println(animal.age) //0
       println(animal.sex) //null
   
       animal.sex = "female"
       println(animal.sex) //female
   
     }
   
   }
   
   //1. Scala 语法中，类并不声明为 public，所有这些类都具有公有可见性（即默 认就是 public）
   class Animal {
   
   }
   
   // 定义一个类
   // 2. 一个 Scala 源文件可以包含多个类
   class Bird {
   
     // 定义属性
     private var name: String = "angryBird"
   
     //Bean 属性（@BeanProperty）
     @BeanProperty
     var age: Int = _   //_表示给属性一个默认值
     //val 修饰的属性不能赋默认值，必须显示指定
   
     var sex: String = _
   
   }
   ```


## 封装

封装就是把抽象出的**数据和对数据的操作**封装在一起，数据被保护在内部，程序的其它 部分只有通过被授权的操作（成员方法），才能对数据进行操作。Java 封装操作如下，

1. 将属性进行私有化
2. 提供一个公共的 set 方法，用于对属性赋值
3. 提供一个公共的 get 方法，用于获取属性的值

Scala 中的 `public `属性，底层实际为 `private`，并通过 get 方法（`obj.field()`）和 set 方法 （`obj.field_=(value)`）对其进行操作。所以 Scala 并不推荐将属性设为 `private`，再为其设置 `public `的 `get `和 `set `方法的做法。但由于很多 Java 框架都利用反射调用 getXXX 和 setXXX 方法，有时候为了和这些框架兼容，也会为 Scala 的属性设置 getXXX 和 setXXX 方法（通过 `@BeanProperty` 注解实现）。

### 访问权限

1. 说明

   在 Java 中，访问权限分为：public，private，protected 和默认。在 Scala 中，你可以通过类似的修饰符达到同样的效果。但是使用上有区别

   1. Scala 中属性和方法的默认访问权限为 `public`，但 Scala 中无 `public `关键字
   2. `private `为私有权限，只在类的内部和伴生对象中可用
   3. `protected `为受保护权限，Scala 中受保护权限比 Java 中更严格，同类、子类可以访问，同包无法访问
   4. `private[包名]`增加包访问权限，包名下的其他类也可以使用

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-05 09:52
    * */
   object Test04_ClassForAccess {
   
   }
   
   class Person {
     private var idCard: String = "12345567"
   
     protected var name: String = "terry"
   
     var sex: String = "female"
   
     private[chapter06] var age: Int = 20
   
     def printInfo(): Unit = {
       println(s"Person: $idCard $name $sex $age")
     }
   }
   ```

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-05 09:51
    * */
   object Test04_Access {
   
     def main(args: Array[String]): Unit = {
   
       // 创建对象
       val person: Person = new Person
       //person.idCard //error
       //person.name //error
       println(person.age) //20
       println(person.sex) //female
   
       person.printInfo() //Person: 12345567 terry female 20
   
       println("=================")
   
       var worker: Worker = new Worker
   
       worker.age = 11
       worker.printInfo() //Worker: bob 22 male
     }
   
   }
   
   // 定义一个子类
   class Worker extends Person {
     override def printInfo(): Unit = {
   
       //println(idCard) //error
       name = "bob"
       age = 22
       sex = "male"
       println(s"Worker: $name $age $sex")
   
     }
   }
   
   ```

   

### 方法

1. 基本语法

   ```
   def 方法名(参数列表) [：返回值类型] = {
   	方法体
   }
   ```

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-05 09:52
    * */
   object Test04_ClassForAccess {
   
     def main(args: Array[String]): Unit = {
       val person = new Person
       println(person.sum(10, 12))	//22
     }
   }
   
   
   class Person {
     private var idCard: String = "12345567"
   
     protected var name: String = "terry"
   
     var sex: String = "female"
   
     private[chapter06] var age: Int = 20
   
     def printInfo(): Unit = {
       println(s"Person: $idCard $name $sex $age")
     }
   
     def sum(n1: Int, n2: Int): Int = {
       n1 + n2
     }
   }
   ```
   
   

### 创建对象

1. 基本语法

   ```
   val | var 对象名 [：类型] = new 类型()
   ```

2. 实例操作

   1. val 修饰对象，不能改变对象的引用（即：内存地址），可以改变对象属性的值。
   2. var 修饰对象，可以修改对象的引用和修改对象的属性值
   3. 自动推导变量类型不能多态，所以多态需要显示声明

   ```scala
   object Test04_ClassForAccess {
   
     def main(args: Array[String]): Unit = {
       //val 修饰对象，不能改变对象的引用（即：内存地址），可以改变对象属 性的值
       val person = new Person
       person.sex = "male"
       //person = new Person //error
       println(person.sum(10, 12)) //22
   
     }
   }
   ```

### 构造器

和 Java 一样，Scala 构造对象也需要调用构造方法，并且可以有任意多个构造方法。

Scala 类的构造器包括：**主构造器和辅助构造器**

1. 基本语法

   ```
   class 类名(形参列表) { // 主构造器
        // 类体
        def this(形参列表) { // 辅助构造器
        }
        def this(形参列表) { //辅助构造器可以有多个...
        }
   }
   
   ```

   说明

   1. 辅助构造器，函数的名称 `this`，可以有多个，编译器通过参数的个数及类型来区分。
   2. 辅助构造方法不能直接构建对象，必须直接或者间接调用主构造方法
   3. **构造器调用其他另外的构造器，要求被调用构造器必须提前声明**

2. 实例操作

   如果主构造器无参数，小括号可省略，构建对象时调用的构造方法的小括号也可 以省略

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 09:47
    * */
   object Test05_Constructor {
     def main(args: Array[String]): Unit = {
       val stu = new Student1
       stu.Student1()
       /*
       1. 主构造方法被调用
       一般方法调用
        */
       println("===================")
   
       var stu2 = new Student1("marry")
       /*
       1. 主构造方法被调用
       2. 辅助构造方法一被调用
       name: marry age: 0
        */
   
       println("===================")
       var stu3 = new Student1("lucy", 18)
       /*
       1. 主构造方法被调用
       2. 辅助构造方法一被调用
       name: lucy age: 0
       3. 辅助构造方法二被调用
       name: lucy age: 18
        */
     }
   }
   
   // 定义一个类
   //（1）如果主构造器无参数，小括号可省略
   //class Student1() {
   class Student1 {
     //定义属性
     var name: String = _
     var age: Int = _
   
     println("1. 主构造方法被调用")
   
     // 声明辅助构造方法
     def this(name: String) {
       // 辅助构造必须调用主构造器,否则无法使用
       this() // 直接调用主构造器,
       println("2. 辅助构造方法一被调用")
       this.name = name
       println(s"name: $name age: $age")
     }
   
     def this(name: String, age: Int) {
   
       this(name)
       println("3. 辅助构造方法二被调用")
       this.age = age
       println(s"name: $name age: $age")
   
     }
   
     def Student1(): Unit = {
       println("一般方法调用")
     }
   
   }
   ```

### 构造器参数

1. 说明

   Scala 类的主构造器函数的形参包括三种类型：未用任何修饰、var 修饰、val 修饰

   1. 未用任何修饰符修饰，这个参数就是一个局部变量
   2. var 修饰参数，作为类的成员属性使用，可以修改
   3. val 修饰参数，作为类只读属性使用，不能修改

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 14:13
    * */
   object Test06_ConstructorParams {
   
     def main(args: Array[String]): Unit = {
   
       var student2 = new Student2
       student2.name = "jack"
       student2.age = 20
   
       //student2: name = jack ,age = 20
       println(s"student2: name = ${student2.name} ,age = ${student2.age}")
   
       println("================================")
   
       var student3 = new Student3("bob", 18)
       //student3: name = bob ,age = 18
       println(s"student3: name = ${student3.name} ,age = ${student3.age}")
       println("================================")
   
       var student4 = new Student4("david", 11)
       // student4: name = david, age = 11
       student4.printinfo()
       // error, 局部变量无法打印对象属性,但如果使用局部变量赋值给属性则没问题
       //println(s"student4: name = ${student4.name}, age = ${student4.age}")
   
       println("================================")
       val student5 = new Student5("king", 35)
       //student5: name = king ,age = 35
       println(s"student5: name = ${student5.name} ,age = ${student5.age}")
   
       // 构造器初始化赋值后无法再改变该对象的属性值
       // student5.age = 44  //error
   
       println("================================")
       val student6 = new Student6("lucy", 10, "scala")
       //student6: name = lucy ,age = 10 , school = scala
       println(s"student6: name = ${student6.name} ,age = ${student6.age} , school = ${student6.school}")
       //student6: name = lucy, age = 10, school = scala
       student6.printInfo()
   
     }
   
   }
   
   // 定义类
   // 无参构造器
   class Student2 {
     // 单独定义属性
     var name: String = _
     var age: Int = _
   }
   
   // 上面定义等价于
   // var 修饰参数，作为类的成员属性使用，可以修改
   class Student3(var name: String, var age: Int)
   
   // 主构造器参数无修饰
   // 未用任何修饰符修饰，这个参数就是一个局部变量
   class Student4(name: String, age: Int) {
     def printinfo(): Unit = {
       println(s"student4: name = $name, age = $age")
     }
   }
   
   // 下面这种局部变量的传入可以赋值给属性
   /*
   class Student4(_name: String, _age: Int) {
     var name: String = _name
     var age: Int = _age
   }*/
   
   // val 修饰参数，作为类的只读属性使用，不能修改
   class Student5(val name: String, val age: Int)
   
   class Student6(var name: String, var age: Int) {
   
     var school: String = _
   
     def this(name: String, age: Int, school: String) {
       this(name, age)
       this.school = school
     }
   
     def printInfo() {
       println(s"student6: name = ${name}, age = $age, school = $school")
     }
   
   }
   ```

## 继承和多态

1. 基本语法

   ```
   class 子类名 extends 父类名 { 类体 }
   ```

   1. 子类继承父类的属性和方法
   2. scala 是单继承

2. 实例操作

   1. 子类继承父类的属性和方法
   2. 继承的调用顺序：父类构造器->子类构造器

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 15:14
    * */
   object Test07_Inherit {
   
     def main(args: Array[String]): Unit = {
       val student1: Student7 = new Student7("marry", 11)
       student1.printInfo()
       /*
       1. 父类的主构造器调用
       2. 父类的辅助构造器调用
       3. 子类的主构造器调用
       Student: marry 11 null
        */
       println("======================================")
   
       val student2 = new Student7("jack", 12, "scala")
       student2.printInfo()
       /*
       1. 父类的主构造器调用
       2. 父类的辅助构造器调用
       3. 子类的主构造器调用
       4. 子类的辅助构造器调用
       Student: jack 12 scala
        */
   
       println("======================================")
   
       val teacher = new Teacher
       teacher.printInfo()
       /*
       1. 父类的主构造器调用
       Teachter
        */
   
       println("======================================")
       printInfo(student1) //调用了Student类的重写方法
       /*
       Student: marry 11 null
        */
       println("--------------------------------------")
       // Teachter
       printInfo(teacher) //调用了Teacher类的重写方法
       println("--------------------------------------")
   
       val person = new Person7
       /*
       1. 父类的主构造器调用
       Person : name: null  age : 0
        */
       printInfo(person) //调用了父类的打印方法
     }
   
     def printInfo(person: Person7): Unit = {
       person.printInfo()
     }
   }
   
   // 定义一个父类
   class Person7 {
     var name: String = _
     var age: Int = _
   
     println("1. 父类的主构造器调用")
   
     def this(name: String, age: Int) {
       this
       println("2. 父类的辅助构造器调用")
       this.name = name
       this.age = age
   
     }
   
     def printInfo(): Unit = {
       println(s"Person : name: $name  age : $age")
     }
   }
   
   // 定义子类
   class Student7(name: String, age: Int) extends Person7(name, age) {
   
     var stdNo: String = _
     println("3. 子类的主构造器调用")
   
     def this(name: String, age: Int, stdNo: String) {
       this(name, age)
       println("4. 子类的辅助构造器调用")
       this.stdNo = stdNo
     }
   
     override def printInfo(): Unit = {
   
       println(s"Student: $name $age $stdNo")
     }
   }
   
   class Teacher extends Person7 {
     override def printInfo(): Unit = {
       println(s"Teacher")
     }
   }
   ```

3. 动态绑定

   > **Scala 中属性和方法都是动态绑定，而 Java 中只有方法为动态绑定**

   对比 Java 与 Scala 的重写

   - Scala

     ```scala
     package com.scala.chapter06
     
     /**
      * @program: scala
      * @description: ${description}
      * @author: JunWen
      * @create: 2023-12-06 15:56
      * */
     object Test08_DynamicBind {
     
       def main(args: Array[String]): Unit = {
     
         val student: Student8 = new Student8
         println(student.name) // student
         student.hello() // hello student
     
         println("==========================")
         val person: Person8 = new Student8
         println(person.name) //student
         person.hello() //hello student
       }
     
     }
     
     
     class Person8 {
       val name: String = "person"
     
       def hello(): Unit = {
         println(s"hello $name")
       }
     }
     
     class Student8 extends Person8 {
       override val name: String = "student"
     
       override def hello(): Unit = {
     
         println(s"hello $name")
       }
     }
     ```

   - Java

     ```java
     package com.scala;
     
     /**
      * @program: scala
      * @description: dynamic bind
      * @author: JunWen
      * @create: 2023-12-06 16:00
      **/
     public class TestDynamicBind {
     
         public static void main(String[] args) {
     
             Worker worker = new Worker();
             System.out.println(worker.name);    //worker
             worker.hello(); //hello worker
             worker.hi();    //hi worker
     
             System.out.println("==============================");
     
             // 多态
             Person person = new Worker();
             System.out.println(person.name);    //person    // 静态绑定属性
             person.hello(); //hello worker  // 动态绑定方法
             ///person.hi();     //error
         }
     }
     
     class Person {
         String name = "person";
     
         public void hello() {
             System.out.println("hello " + this.name);
         }
     }
     
     class Worker extends Person {
         String name = "worker";
     
         public void hello() {
             System.out.println("hello " + this.name);
         }
     
         public void hi() {
             System.out.println("hi " + this.name);
         }
     }
     
     ```

## 抽象类

### 抽像属性和抽像方法

1. 基本语法

   1. 定义抽象类：`abstract class Person{}` //通过 abstract 关键字标记抽象类
   2. 定义抽象属性：`val|var name:String` //一个属性没有初始化，就是抽象属性
   3. 定义抽象方法：`def hello():String` //只声明而没有实现的方法，就是抽象方法

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 16:08
    * */
   object Test09_AbstractClass {
   
     def main(args: Array[String]): Unit = {
   
       val student = new Student9
   
       /*
       person eat
       student eat
        */
       student.eat()
       student.sleep() //student sleep
     }
   
   }
   
   
   // 定义抽象类
   abstract class Person9 {
     // 非抽象属性
     var name: String = "person"
   
     // 抽象属性
     var age: Int
   
     // 非抽象方法
     def eat(): Unit = {
       println("person eat")
     }
   
     // 抽象方法
     def sleep(): Unit
   }
   
   // 定义具体的实现子类
   class Student9 extends Person9 {
     // 实现抽象属性和方法
     var age: Int = 11
   
     override def sleep(): Unit = {
       println("student sleep")
     }
   
   
     // 父类已定义是可变变量,子类重写无法改变其类型
     //override val name: String = "student"
     // 重写非抽象属性和方法
     name = "student"
   
     override def eat(): Unit = {
       super.eat()
       println(s"$name eat")
     }
   }
   ```

   

2. 继承&重写

   1. 如果父类为抽象类，那么子类需要将抽象的属性和方法实现，否则子类也需声明 为抽象类

   2. 重写非抽象方法需要用 `override `修饰，重写抽象方法则可以不加 `override`

   3. 子类中调用父类的方法使用 `super `关键字

   4. 子类对抽象属性进行实现，父类抽象属性可以用 var 修饰

      子类对非抽象属性重写，父类非抽象属性只支持 val 类型，而不支持 var

      > **因为 var 修饰的为可变变量，子类继承之后就可以直接使用，没有必要重写**



### 匿名子类

1. 说明

   和 Java 一样，可以通过包含带有定义或重写的代码块的方式创建一个匿名的子类

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 16:36
    * */
   object Test10_AnnoymousClass {
   
     def main(args: Array[String]): Unit = {
   
       val person: Person10 = new Person10 {
         override var name: String = "jack"
   
         override def eat(): Unit = println(s"$name eat")
       }
   
       println(person.name)  //jack
       person.eat()  //jack eat
     }
   
   }
   
   // 定义抽象类
   abstract class Person10 {
     var name: String
   
     def eat(): Unit
   }
   
   ```

## 单例对象（伴生对象）

Scala语言是**完全面向对象**的语言，所以并没有静态的操作（即在Scala中没有静态的概念）。但是为了能够和Java语言交互（因为Java中有静态概念），就产生了一种特殊的对象来模拟类对象，该对象为**单例对象**。若单例对象名与类名一致，则称该单例对象这个类的**伴生对象**，这个类的所有“静态”内容都可以**放置在它的伴生对象**中声明。

### 单例对象语法

1. 基本语法

   ```scala
   object Person{
   	val country:String="China"
   }
   ```

2. 说明

   1. 单例对象采用 `object `关键字声明
   2. 单例对象对应的类称之为**伴生类**，伴生对象的名称应该和伴生类名一致
   3. 单例对象中的属性和方法都可以通过伴生对象名（类名）直接调用访问

3. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 16:38
    * */
   object Test11_Object {
   
     def main(args: Array[String]): Unit = {
   
       val student1 = new Student11("alice", 18)
       student1.printInfo() //student: name = alice, age = 18, school = scala
   
       println("========================")
       // 3. 伴生对象中的属性和方法都可以通过伴生对象名（类名）直接调用访问。
       var student2 = Student11.newStudent("jack", 20)
       student2.printInfo() //student: name = jack, age = 20, school = scala
     }
   
   }
   
   
   // 2. 伴生对象对应的类称之为伴生类，伴生对象的名称应该和伴生类名一致。
   class Student11(val name: String, val age: Int) {
   
     def printInfo() {
       println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
     }
   }
   
   // 1.伴生对象采用 object 关键字声明
   object Student11 {
     val school: String = "scala"
   
     // 定义一个类的对象实例的创建方法
     def newStudent(name: String, age: Int): Student11 = new Student11(name, age)
   
   
   }
   ```

   

### apply 方法

1. 说明

   1. 通过伴生对象的 `apply `方法，实现不使用 new 方法创建对象。
   2. 如果想让主构造器变成私有的，可以在()之前加上 `private`
   3. apply 方法可以重载
   4. Scala 中 `obj(arg)`的语句实际是在调用该对象的 `apply `方法，即 `obj.apply(arg)`。用 以统一面向对象编程和函数式编程的风格。
   5. 当使用 new 关键字构建对象时，调用的其实是类的构造方法，当直接使用类名构 建对象时，调用的其实时伴生对象的 `apply `方法

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-06 16:38
    * */
   object Test11_Object {
   
     def main(args: Array[String]): Unit = {
   
       //val student1 = new Student11("alice", 18)
       //student1.printInfo() //student: name = alice, age = 18, school = scala
   
       println("========================")
       // 3. 伴生对象中的属性和方法都可以通过伴生对象名（类名）直接调用访问。
       var student2 = Student11.newStudent("jack", 20)
       student2.printInfo() //student: name = jack, age = 20, school = scala
   
       println("========================")
   
   
       var student3 = Student11.apply("lucy", 33)
       student3.printInfo() //student: name = lucy, age = 33, school = scala
   
       println("========================")
   
       // 1. 通过伴生对象的 apply 方法，实现不使用 new 关键字创建对象
       var student4 = Student11("david", 44)
       student4.printInfo() //student: name = david, age = 44, school = scala
   
     }
   
   }
   
   
   // 2. 伴生对象对应的类称之为伴生类，伴生对象的名称应该和伴生类名一致。
   // 如果想让主构造器变成私有的，可以在()之前加上 private ,当为私有后,只有伴生对象才可进行调用
   class Student11 private(val name: String, val age: Int) {
   
     def printInfo() {
       println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
     }
   }
   
   // 1.伴生对象采用 object 关键字声明
   object Student11 {
     val school: String = "scala"
   
     // 定义一个类的对象实例的创建方法
     def newStudent(name: String, age: Int): Student11 = new Student11(name, age)
   
   
     def apply(name: String, age: Int): Student11 = new Student11(name, age)
   
   }
   
   ```

   > **//注意：也可以创建其它类型对象，并不一定是伴生类对象**

### 扩展：在 Scala 中实现单例模式

```scala
package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 17:35
 * */
object Test12_Singleton {


  def main(args: Array[String]): Unit = {

    val student1 = Student12.getInstance()
    student1.printInfo() //student: name = alice, age = 20, school = scala

    val student2 = Student12.getInstance()

    println(student1) //com.scala.chapter06.Student12@3ac3fd8b
    println(student2) //com.scala.chapter06.Student12@3ac3fd8b
  }

}

class Student12 private(val name: String, val age: Int) {
  def printInfo() {
    println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
  }
}

/**
 * 饿汉式
 */
/*object Student12 {
  private val student: Student12 = new Student12("alice", 20)

  def getInstance(): Student12 = student
}*/

/**
 * 懒汉式
 */
object Student12 {
  private var student: Student12 = _

  def getInstance(): Student12 = {
    if (student == null) {
      // 如果没有对象实例的话，就创建一个
      student = new Student12("alice", 20)
    }
    student
  }
}
```

## 特质（Trait）

**Scala 语言中，采用特质 `trait`（特征）来代替接口的概念**，也就是说，多个类具有相同的特质（特征）时，就可以将这个特质（特征）独立出来，采用关键字 `trait `声明

Scala 中的 `trait `中即**可以有抽象属性和方法，也可以有具体的属性和方法，一个类可以混入（mixin）多个特质**。这种感觉**类似于 Java 中的抽象类**

Scala 引入 `trait `特征，第一可以替代 Java 的接口，第二个也是对单继承机制的一种补充

### 特质声明

1. 基本语法

   ```
   trait 特质名 {
   	trait 主体
   }
   ```

2. 实例操作

   ```scala
   trait PersonTrait {
    // 声明属性
    var name:String = _
    // 声明方法
    def eat():Unit={
    }
    // 抽象属性
    var age:Int
   
    // 抽象方法
    def say():Unit
   }
   通过查看字节码，可以看到特质=抽象类+接口
   ```

### 特质基本语法

一个类具有某种特质（特征），就意味着这个类满足了这个特质（特征）的所有要素， 所以在使用时，也采用了`extends `关键字，如果有多个特质或存在父类，那么需要采用 `with `关键字连接

1. 基本语法

   - 没有父类:class 类名 extends 特质 1 with 特质 2 with 特质 3 …
   - 有父类 ：class 类名 extends 父类 with 特质 1 with 特质 2 with 特质 3…

2. 说明

   1. 类和特质的关系：使用继承的关系。
   2. 当一个类去继承特质时，第一个连接词是 `extends`，后面是 `with`。
   3. 如果一个类在同时继承特质和父类时，应当把父类写在 `extends `后

3. 实例操作

   1. 特质可以同时拥有抽象方法和具体方法

      ```scala
      package com.scala.chapter06
      
      /**
       * @program: scala
       * @description: ${description}
       * @author: JunWen
       * @create: 2023-12-06 17:58
       * */
      object Test13_Trait {
      
        def main(args: Array[String]): Unit = {
          val student: Student13 = new Student13
          student.sayHello()
          student.study()
          student.dating()
          student.play()
      
          /*
          hello from: student
          hello from : student student
          student student is studying
          student student is dating
          young people student is playing
           */
        }
      }
      
      // 定义一个父类
      class Person13 {
        val name: String = "person"
        var age: Int = 18
      
        def sayHello(): Unit = {
          println(s"hello from: $name")
        }
      
        def increase() = {
          println("person increase")
        }
      
      }
      
      //1. 特质可以同时拥有抽象方法和具体方法
      trait Young {
      
        // 抽象属性
        var age: Int
      
        // 声明属性
        val name: String = "young"
      
        // 声明方法
        def play() = {
          println(s"young people $name is playing")
      
        }
      
        // 抽象方法
        def dating(): Unit
      }
      
      //（2）一个类可以实现/继承多个特质
      //（3）所有的 Java 接口都可以当做 Scala 特质使用
      class Student13 extends Person13 with Young with java.io.Serializable{
        // 重写冲突的属性,必须重写,否则当前类不清楚使用哪个name
        override val name: String = "student"
      
        // 实现抽象方法
        override def dating(): Unit = println(s"student $name is dating")
      
        // 重写父类方法
        override def sayHello(): Unit = {
          super.sayHello()
          println(s"hello from : student $name")
        }
      
        def study() = println(s"student $name is studying")
      }
      ```

   2. 一个类可以混入（mixin）多个特质
   
   3. 所有的 Java 接口都可以当做 Scala 特质使用
   
   4. 动态混入：可灵活的扩展类的功能
   
      1. 动态混入：创建对象时混入 trait，而无需使类混入该 trait
      2. 如果混入的 trait 中有未实现的方法，则需要实现
      
      ```scala
      package com.scala.chapter06
      
      /**
       * @program: scala
       * @description: ${description}
       * @author: JunWen
       * @create: 2023-12-07 21:58
       * */
      object Test14_TraitMixin {
      
        def main(args: Array[String]): Unit = {
      
          val student = new Student14
          student.study()
          student.increase()
          student.play()
          student.increase()
      
          /*
          student student is studying
          student student knowledge increased: 1
          young people student is playing
          student student knowledge increased: 2
           */
      
          println("===========================")
      
          //（4）动态混入：可灵活的扩展类的功能
          val studentWithTalent = new Student14 with Talent {
            override def singing(): Unit = println("student is good at singign")
      
            override def dancing(): Unit = println("student is good at dancing")
          }
      
          studentWithTalent.sayHello()
          studentWithTalent.play()
          studentWithTalent.study()
          studentWithTalent.dating()
          studentWithTalent.dating()
          studentWithTalent.singing()
      
          /*
          hello from: student
          hello from : student student
          young people student is playing
          student student is studying
          student student is dating
          student student is dating
          student is good at singign
           */
      
        }
      
      }
      
      // 再定义一个特质
      trait Knowledge {
        var amount: Int = 0
      
        def increase(): Unit
      }
      
      trait Talent {
        def singing(): Unit
      
        def dancing(): Unit
      }
      
      class Student14 extends Person13 with Young with Knowledge {
      
        // 重写冲突的属性
        override val name: String = "student"
      
        // 实现抽象方法
        override def dating(): Unit = println(s"student $name is dating")
      
        def study(): Unit = println(s"student $name is studying")
      
        // 重写父类方法
        override def sayHello(): Unit = {
          super.sayHello()
          println(s"hello from : student $name")
      
        }
      
        // 实现特质中的抽象方法
        override def increase(): Unit = {
          amount += 1
          println(s"student $name knowledge increased: $amount")
        }
      }
      ```
      

### 特质叠加

由于一个类可以混入（mixin）多个 `trait`，且 `trait `中可以有具体的属性和方法，若混入的特质中具有相同的方法（方法名，参数列表，返回值均相同），必然会出现继承冲突问题。 

冲突分为以下两种

- 第一种，一个类（Sub）混入的两个 trait（TraitA，TraitB）中具有相同的具体方法，且 两个 trait 之间没有任何关系，解决这类冲突问题，直接在类（Sub）中重写冲突方法

  ![](http://www.dxb02.top/photos/scala/05.jpg)

- 第二种，一个类（Sub）混入的两个 trait（TraitA，TraitB）中具有相同的具体方法，且两个 trait 继承自相同的 trait（TraitC），及所谓的“钻石问题”，解决这类冲突问题，Scala 采用了**特质叠加**的策略

  ![](http://www.dxb02.top/photos/scala/06.jpg)

所谓的**特质叠加**，就是将混入的多个 trait 中的冲突方法叠加起来，案例如下

```scala
package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-08 08:58
 * */
object Test15_TraitOverlying {

  def main(args: Array[String]): Unit = {

    val student = new Student15
    student.increase() //person increase

    println("=================================")

    val myFootBall = new MyFootBall
    println(myFootBall.describe()) //my ball is a red - foot - ball

  }

}

// 定义球类特征
trait Ball {
  def describe(): String = "ball"
}

// 定义颜色特征
trait ColorBall extends Ball {
  var color: String = "red"

  override def describe(): String = color + " - " + super.describe()
}

// 定义一个自定义球的类
trait CategoryBall extends Ball {
  var category: String = "foot"

  override def describe(): String = category + " - " + super.describe()
}

// 定义一个自定义球的类
class MyFootBall extends CategoryBall with ColorBall {
  override def describe(): String = "my ball is a " + super.describe()
}

trait Knowledge15 {
  var amount: Int = 0

  def increase(): Unit = {
    println("knowledge increased")
  }
}

trait Talent15 {
  def singing(): Unit

  def dancing(): Unit

  def increase(): Unit = {
    println("talent increased")
  }
}

class Student15 extends Person13 with Talent15 with Knowledge15 {
  override def singing(): Unit = println("singing")

  override def dancing(): Unit = println("dancing")

  // 指定调用父类Person13.increase()方法
  override def increase(): Unit = super[Person13].increase()
}

```

### 特质叠加执行顺序

思考：上述案例中的 super.describe()调用的是父 trait 中的方法吗？

当一个类混入多个特质的时候，scala 会对所有的特质及其父特质按照一定的顺序进行 排序，而此案例中的 super.describe()调用的实际上是排好序后的下一个特质中的 describe() 方法。，排序规则如下

![](http://www.dxb02.top/photos/scala/07.jpg)



结论：

1. 案例中的 super，不是表示其父特质对象，而是表示上述叠加顺序中的下一个特质， 即，**MyClass 中的 super 指代 Color，Color 中的 super 指代 Category，Category 中的 super 指代 Ball**

2. 如果想要调用某个指定的混入特质中的方法，可以增加约束：`super[]`，

   例如`super[Category].describe()`



### 特质自身类型

1. 说明

   自身类型可实现依赖注入的功能

2. 实例操作

   ```scala
   package com.scala.chapter06
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-08 10:02
    * */
   object Test16_TraitSelfType {
   
     def main(args: Array[String]): Unit = {
   
       val user = new RegisterUser("alice", "123456")
       user.insert() //insert into db:alice
     }
   
   }
   
   class User(val name: String, val password: String)
   
   trait UserDao {
   
     _: User =>
   
     def insert(): Unit = {
       println(s"insert into db:${this.name}")
     }
   
   }
   
   class RegisterUser(name: String, password: String) extends User(name, password) with UserDao
   ```

### 特质和抽象类的区别

1. 优先使用特质。一个类扩展多个特质是很方便的，但却只能扩展一个抽象类
2. 如果你需要构造函数参数，使用抽象类。因为抽象类可以定义**带参数**的构造函数， 而特质不行（有无参构造）



## 扩展

### 类型检查和转换

1. 说明

   1. `obj.isInstanceOf[T]`：判断 obj 是不是 T 类型
   2. `obj.asInstanceOf[T]`：将 obj 强转成 T 类型。
   3. `classOf `获取对象的类名

2. 实例操作

   ```scala
   object Test17_Extends {
   
     def main(args: Array[String]): Unit = {
       // 1. 类型的检测和转换
       val student: Student17 = new Student17("alice", 18)
       student.study() //student study
       student.sayHi() //hi from student alice
   
       val person: Person17 = new Student17("jack", 20)
       person.sayHi() //hi from student jack
   
       println("======================")
       // 类型判断
       println("student is Student17 : " + student.isInstanceOf[Student17]) //student is Student17 : true
       println("student is Person17 : " + student.isInstanceOf[Person17]) //student is Person17 : true
       println("person is Person17 : " + person.isInstanceOf[Person17]) //person is Person17 : true
       println("person is Student17 : " + person.isInstanceOf[Student17]) //person is Student17 : true
       println("======================")
   
       val person2: Person17 = new Person17("carry", 11)
       println("person2 is Student17: " + person2.isInstanceOf[Student17]) //person2 is Student17: false
       println("======================")
   
       // 类型转换
       if (person.isInstanceOf[Student17]) {
         val newStudent = person.asInstanceOf[Student17]
         newStudent.study() //student study
       }
       println("======================")
       println(classOf[Student17]) //class com.scala.chapter06.Student17
   
     }
   
   }
   
   
   class Person17(val name: String, val age: Int) {
     def sayHi(): Unit = {
       println("hi from person" + name)
     }
   }
   
   
   class Student17(name: String, age: Int) extends Person17(name, age) {
     override def sayHi(): Unit = {
       println("hi from student " + name)
     }
   
     def study(): Unit = {
       println("student study")
     }
   }
   ```





### 枚举类和应用类

1. 说明

   - 枚举类：需要继承 `Enumeration`
   - 应用类：需要继承 App(可以直接运行不需写main方法)

2. 实例操作

   - 枚举类

     ```scala
     object Test17_Extends {
     
       def main(args: Array[String]): Unit = {
         // 2. 测试枚举类
         println(WorkDay.MONDAY) //Monday
       }
     
     }
     
     object WorkDay extends Enumeration {
       val MONDAY = Value(1, "Monday")
       val TUESDAY = Value(2, "TuesDay")
     }
     ```

   - 应用类

     ```scala
     object TestApp extends App {
       println("app start") //app start
     
       type MyString = String
       val a: MyString = "abc"
       println(a) //abc
     }
     ```

### Type 定义新类型

1. 说明

   使用 `type `关键字可以定义新的数据数据类型名称，本质上就是类型的一个别名

2. 案例操作

   ```scala
   object Test {
        def main(args: Array[String]): Unit = {
            type S=String
            var v:S="abc"
            def test():S="xyz"
        }
   }
   ```

------



# 第七章 集合

## 集合简介

1. Scala 的集合有三大类：序列 `Seq`、集 `Set`、映射 `Map`，所有的集合都扩展自 `Iterable `特质。
2. 对于几乎所有的集合类，Scala 都同时提供了可变和不可变的版本，分别位于以下两 个包
   - 不可变集合：`scala.collection.immutable`
   - 可变集合： `scala.collection.mutable`
3. Scala 不可变集合，就是指该集合对象不可修改，每次修改就会返回一个新对象，而 不会对原对象进行修改。类似于 java 中的 `String `对象
4. 可变集合，就是这个集合可以直接对原对象进行修改，而不会返回新的对象。类似 于 java 中 `StringBuilder `对象

> **建议：在操作集合的时候，不可变用符号，可变用方法**

### 不可变集合继承图

![](http://www.dxb02.top/photos/scala/08.jpg)

1. `Set`、`Map` 是 Java 中也有的集合
2. Seq 是 Java 没有的，我们发现 List 归属到 Seq 了，因此这里的 List 就和 Java 不是同一个 概念了
3. 我们前面的 ``for ``循环有一个 1 to 3，就是 `IndexedSeq `下的 `Range`
4. `String `也是属于 `IndexedSeq`
5. 我们发现经典的数据结构比如 `Queue `和 `Stack `被归属到 LinearSeq(线性序列)
6. 大家注意 Scala 中的 Map 体系有一个 `SortedMap`，说明 Scala 的 Map 可以支持排序
7. `IndexedSeq `和 `LinearSeq `的区别
   1. `IndexedSeq `是通过索引来查找和定位，因此速度快，比如 String 就是一个索引集合，通过索引即可定位
   2. `LinearSeq `是线型的，即有头尾的概念，这种数据结构一般是通过遍历来查找

### 可变集合继承图

![](http://www.dxb02.top/photos/scala/09.jpg)

## 数组

### 不可变数组

1. 第一种方式定义数组

   ```scala
   val arr1 = new Array[Int](10)
   ```

   1. `new `是关键字
   2. `[Int]`是指定可以存放的数据类型，如果希望存放任意数据类型，则指定 Any
   3. (10)，表示数组的大小，确定后就不可以变化

2. 案例实操

   ```scala
   package com.scala.chapater07
   
   object Test01_ImmutableArray {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建数组
       val arr: Array[Int] = new Array[Int](5)
       println(arr.length) //5
       println("====================")
       // 2. 访问元素
       println(arr(0)) //0
       //println(arr(5)) // error 越界
   
       println("====================")
   
       //（2）数组赋值
       //（2.1）修改某个元素的值
       arr(0) = 12
       arr(4) = 23
       println(arr(0)) //12
       println(arr(4)) //23
   
       //（2.2）采用方法的形式给数组赋值
       arr.update(0, 234)
       println(arr(0)) //234
   
       println("====================")
   
       // 3. 数组的遍历
       //（3.1）查看数组
       println(arr) //[I@3ac3fd8b
       println(arr.mkString(",")) //234,0,0,0,23
   
       println("====================")
   
       // 1) 普通for循环
       for (i <- 0 until arr.length) {
         /*
         12
         0
         0
         0
         23
          */
         println(arr(i))
       }
       println("====================")
       // 上面的便利可以简化如下
       /*
       12
       0
       0
       0
       23
        */
       for (i <- arr.indices) println(arr(i))
       println("====================")
       // 2) 直接遍历所有元素，增强for循环
       /*
       12
       0
       0
       0
       23
        */
       for (elem <- arr) println(elem)
       println("====================")
       // 3) 迭代器
       val iter = arr.iterator
       while (iter.hasNext) {
   
         /*
         234
         0
         0
         0
         23
          */
         println(iter.next())
       }
   
       println("====================")
   
       // 4) 调用foreach方法
       /*
       234
       0
       0
       0
       23
        */
       arr.foreach((elem: Int) => println(elem))
   
       println("====================")
   
       // 上面也可简写为
       arr.foreach(println)
   
       println("====================")
       //（4）增加元素（由于创建的是不可变数组，增加元素，其实是产生新的数组
       val newArr = arr.:+(11)
       println(newArr.mkString(",")) //234,0,0,0,23,11
       println(newArr.mkString("--")) //234--0--0--0--23--11
   
       // 上面添加元素也可简写如下
       val newArr2 = newArr.+:(22)
       println(newArr2.mkString(",")) //22,234,0,0,0,23,11
   
       // 上面的方法也可以继续简写如下(注意+的位置,是把要紧跟着新添加的元素,在前面代表添加在前面,在后面代表添加在后面)
       val newArr3 = newArr2 :+ 33
       val newArr4 = 44 +: 55 +: newArr3 :+ 66 :+ 77
   
       println(newArr3.mkString(",")) //22,234,0,0,0,23,11,33
       println(newArr4.mkString(",")) //44,55,22,234,0,0,0,23,11,33,66,77
   
   
       // 另一种创建方式
       val arr2 = Array(11, 22, 33, 44, 55)
   
     }
   
   }
   
   ```

3. 第二种方式定义数组

   ```scala
   val arr1 = Array(1, 2)
   ```

   1. 在定义数组时，直接赋初始值

   2. 使用 `apply `方法创建数组对象

      ```scala
      //底层原码可以看到也是根据传参new一个数组对象返回 
      def apply(x: Int, xs: Int*): Array[Int] = {
          val array = new Array[Int](xs.length + 1)
          array(0) = x
          val iterator = xs.iterator
          var i = 1
          while (iterator.hasNext) {
            array(i) = iterator.next(); i += 1
          }
          array
        }
      ```

4. 实例操作

   ```scala
       // 另一种创建方式
       val arr2 = Array(11, 22, 33, 44, 55)
       arr2.foreach(println)
       /*
       11
       22
       33
       44
       55
        */
   
       val arr3 = Array.apply(1, 2, 3, 4, 5)
       arr3.foreach(println)
       /*
       1
       2
       3
       4
       5
        */
   ```



### 可变数组

1. 定义变长数组

   ```scala
   val arr01 = ArrayBuffer[Any](3, 2, 5)
   ```

   1. `[Any]`存放任意数据类型
   2. (3, 2, 5)初始化好的三个元素
   3. `ArrayBuffer `需要引入` scala.collection.mutable.ArrayBuffer`

2. 实例操作

   1. `ArrayBuffer `是有序的集合
   2. 增加元素使用的是 `append `方法()，支持可变参数

   ```scala
   package com.scala.chapater07
   
   import scala.collection.mutable
   import scala.collection.mutable.ArrayBuffer
   
   object Test02_ArrayBuffer {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建可变数组
       val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
   
       // 创建并初始赋值可变数组
       val arr2 = ArrayBuffer(11, 22, 33)
   
       println(arr1) //ArrayBuffer()
       println(arr2) //ArrayBuffer(11, 22, 33)
   
       println("==================================")
       // 2. 访问元素
       println(arr2(0)) //11
       arr2(0) = 44
       println(arr2(0)) //44
   
       println("==================================")
   
       // 3. 添加元素
       // 尾部添加元素
       val newArr1 = arr2 :+ 15
       println(arr2) //ArrayBuffer(44, 22, 33)
       println(newArr1) //ArrayBuffer(44, 22, 33, 15)
       println(arr2 == newArr1) //false
   
       val newArr2 = arr2 += 19
       println(arr2) //ArrayBuffer(44, 22, 33, 19)
       println(newArr2) //ArrayBuffer(44, 22, 33, 19)
       println(arr2 == newArr2) //true
   
       newArr2 += 13
       println(arr2) //ArrayBuffer(44, 22, 33, 19, 13)
   
       println("==================================")
   
       // 头部添加元素
       77 +=: arr2
       println(arr2) //ArrayBuffer(77, 44, 22, 33, 19, 13)
       println(newArr2) //ArrayBuffer(77, 44, 22, 33, 19, 13)
   
       arr2.append(36)
       // 此方法存在冲突,建议使用appendAll,如下
       //arr1.prepend(11, 76)
       arr2.appendAll(ArrayBuffer(11, 22, 33)) //ArrayBuffer(77, 44, 22, 33, 19, 13, 36, 11, 22, 33)
       println(arr2)
   
       // 向指定的位置插入数据,在0位插入值为1的元素
       arr2.insert(0, 1)
       println(arr2) //ArrayBuffer(1, 77, 44, 22, 33, 19, 13, 36, 11, 22, 33)
       // 指定的位置插入数组
       arr2.insertAll(1, ArrayBuffer(2, 3))
       println(arr2) //ArrayBuffer(1, 2, 3, 77, 44, 22, 33, 19, 13, 36, 11, 22, 33)
   
       println("==================================")
   
       // 4. 删除元素
       arr2.remove(3)
       println(arr2) //ArrayBuffer(1, 2, 3, 44, 22, 33, 19, 13, 36, 11, 22, 33)
   
       // 参数一,起始位,参数二,删除个数
       arr2.remove(0, 5)
       println(arr2) //ArrayBuffer(33, 19, 13, 36, 11, 22, 33)
   
       // 删除值为33的元素(重复元素只能删除一个)
       arr2 -= 33
       println(arr2) //ArrayBuffer(19, 13, 36, 11, 22, 33)
   
       println("==================================")
   
   
     }
   
   }
   
   ```

### 不可变数组与可变数组的转换

1. 说明

   ```scala
   arr1.toBuffer //不可变数组转可变数组
   arr2.toArray //可变数组转不可变数组
   ```

   1. `arr2.toArray` 返回结果才是一个不可变数组，arr2 本身没有变化
   2. `arr1.toBuffer` 返回结果才是一个可变数组，arr1 本身没有变化

2. 实例操作

   ```scala
       // 5. 可变数组转换为不可变数组
       val arr: ArrayBuffer[Int] = ArrayBuffer(22, 33, 44)
       val newArr: Array[Int] = arr.toArray
       println(newArr.mkString(",")) //22,33,44
   
       println("==================================")
   
       // 6. 不可变数组转换为可变数组
       val buffer: mutable.Buffer[Int] = newArr.toBuffer
       println(buffer) //ArrayBuffer(22, 33, 44)
       println(newArr.mkString(",")) //22,33,44
   ```

### 多维数组

1. 多维数组定义

   ```scala
   val arr = Array.ofDim[Double](3,4)
   ```

   说明：二维数组中有三个一维数组，每个一维数组中有四个元素

2. 案例实操

   ```scala
   package com.scala.chapater07
   
   object Test03_MulArray {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建二维数组(第一个参数是有几维,第二个参数是有几个元素)
       val array: Array[Array[Int]] = Array.ofDim[Int](2, 3)
   
       // 2. 访问元素
       array(0)(2) = 11
       array(1)(0) = 22
   
   
       println(array.mkString(",")) //[I@2c6a3f77,[I@246ae04d
       //遍历二维数组
       for (i <- 0 until array.length; j <- 0 until array(i).length) { //i 就是一维数组
         /*
         0
         0
         11
         22
         0
         0
          */
         println(array(i)(j))
       }
   
       println("=====================")
   
       for (i <- array.indices; j <- array(i).indices) {
         /*
         0	0	11
         22	0	0
          */
         print(array(i)(j) + "\t")
         if (j == array(i).length - 1) println()
       }
   
   
       println("=====================")
   
       //使用foreach遍历
       array.foreach(line => line.foreach(println))
       /*
         0
         0
         11
         22
         0
         0
          */
       println("=====================")
   
       // 上面方法也可简写如下
       array.foreach(_.foreach(println))
       /*
       0
       0
       11
       22
       0
       0
        */
   
     }
   
   }
   
   ```

## 列表 List

### 不可变 List

1. 说明

   1. List 默认为不可变集合
   2. 创建一个 List（数据有顺序，可重复）
   3. 遍历 List
   4. List 增加数据
   5. 集合间合并：将一个整体拆成一个一个的个体，称为扁平化
   6. 取指定数据
   7. 空集合 Nil

2. 案例实操

   ```scala
   package com.scala.chapter07
   
   object Test04_List {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建一个List
       val list = List(11, 22, 33)
       println(list) //List(11, 22, 33)
   
       println("======================")
   
       // 2. 访问和遍历元素
       println(list(1)) //22
       list.foreach(println)
       /*
       22
       11
       22
       33
        */
       println("======================")
       // 3. 添加元素
       // 向前添加元素
       val list2 = 10 +: list
       // 向后添加元素
       val list3 = list :+ 23
   
       println(list) //List(11, 22, 33)
       println(list2) //List(10, 11, 22, 33)
       println(list3) //List(11, 22, 33, 23)
   
       println("======================")
   
       // 向前添加元素
       val list4 = list2.::(51)
       println(list4) //List(51, 10, 11, 22, 33)
   
       // 添加一个新List,元素只有一个(空集合 Nil)
       val list5 = Nil.::(13)
       println(list5) //List(13)
   
       val list6 = 73 :: 32 :: Nil
       println(list6) //List(73, 32)
   
       val list7 = 17 :: 28 :: 59 :: 16 :: Nil
       println(list7) //List(17, 28, 59, 16)
   
       println("======================")
   
       val list8 = 31 :: list2
       println(list8) //List(31, 10, 11, 22, 33)
   
       println("=================")
   
   
       // 4. 合并列表
       // 合并后List会在List列表里
       val list9 = list6 :: list7
       println(list9) //List(List(73, 32), 17, 28, 59, 16)
   
       // 合并两个list为一个List,将一个整体拆成一个一个的个体，称为扁平化
       val list10 = list6 ::: list7
       println(list10) // List(73, 32, 17, 28, 59, 16)
   
       // 合并同上
       val list11 = list6 ++ list7
       println(list11) //List(73, 32, 17, 28, 59, 16)
   
     }
   
   }
   
   ```

### 可变 ListBuffer

1. 说明

   1. 创建一个可变集合 ListBuffer
   2. 向集合中添加数据
   3. 打印集合数据

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   import scala.collection.mutable.ListBuffer
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-12 10:04
    * */
   object Test05_ListBuffer {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建可变列表
       val list1: ListBuffer[Int] = new ListBuffer[Int]()
       list1.append(11, 22, 33, 44)
   
   
       val list2 = ListBuffer(11, 22, 33, 44)
   
       println(list1) //ListBuffer(11, 22, 33, 44)
       println(list2) //ListBuffer(11, 22, 33, 44)
   
       println("==============================")
       // 2. 添加元素
       // 尾部追加一元素
       list1.append(55)
       // 头部追加一元素
       list2.prepend(55)
   
       println(list1) //ListBuffer(11, 22, 33, 44, 55)
       println(list2) //ListBuffer(55, 11, 22, 33, 44)
   
       println("==============================")
   
       31 +=: 96 +=: list1 += 25 += 83
       println(list1) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83)
   
       println("==============================")
       // 3. 合并list
       val list3 = list1 ++ list2
       println(list3) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)
       println("==============================")
   
       // 前面的列表合到后面的列表
       list1 ++=: list2
       println(list1) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83)
       println(list2) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)
   
       println("==============================")
       // 4. 修改元素
       list2(0) = 10
       list2.update(1, 9)
       println(list2) //ListBuffer(10, 9, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)
   
   
       // 5. 删除元素
       // 删除下标2的元素
       list2.remove(2)
       // 删除元素为5,只能删除一个,首次出现
       list2 -= 55
       println(list2) //ListBuffer(10, 9, 22, 33, 44, 25, 83, 55, 11, 22, 33, 44)
   
   
     }
   
   }
   ```

##  Set 集合

默认情况下，Scala 使用的是不可变集合，如果你想使用可变集合，需要引用 `scala.collection.mutable.Set` 包



### 不可变 Set

1. 说明

   1. Set 默认是不可变集合，数据无序
   2. 数据不可重复
   3. 遍历集合

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-12 10:42
    * */
   object Test06_ImmutableSet {
   
   
     def main(args: Array[String]): Unit = {
       // 1. 创建set(Set 默认是不可变集合，数据无序)(数据不可重复)
       val set1 = Set(11, 22, 33, 44, 55, 11, 33)
       println(set1) //HashSet(33, 11, 55, 22, 44)
   
       println("===========================")
   
       // 2. 添加元素,顺序是无序的
       val set2 = set1 + 66
       println(set1) //HashSet(33, 11, 55, 22, 44)
       println(set2) //HashSet(33, 66, 11, 55, 22, 44)
   
       println("===========================")
   
       // 3. 合并set
       val set3 = Set(12, 13, 14, 15, 16, 17)
       val set4 = set2 ++ set3
       println(set2) //HashSet(33, 66, 11, 55, 22, 44)
       println(set3) //HashSet(14, 13, 17, 12, 16, 15)
       println(set4) //HashSet(14, 33, 13, 17, 12, 66, 16, 11, 55, 15, 22, 44)
   
       println("===========================")
   
       // 4. 删除元素
       val set5 = set3 - 13
       println(set3) //HashSet(14, 13, 17, 12, 16, 15)
       println(set5) //HashSet(14, 17, 12, 16, 15)
   
       println("===========================")
       
       // 遍历集合
       for (x <- set5) {
         println(x)
         /*
         14
         17
         12
         16
         15
          */
       }
     }
   }
   
   ```



### 可变 `mutable.Set`

1. 说明

   1. 创建可变集合 `mutable.Set`
   2. 打印集合
   3. 集合添加元素
   4. 向集合中添加元素，返回一个新的 Set
   5. 删除数据

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   import scala.collection.mutable
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-13 09:07
    * */
   object Test07_MutableSet {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建可变集合
       val mutableSet: mutable.Set[Int] = mutable.Set(11, 22, 33, 44, 55, 66, 11, 22, 33)
       println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44)
   
       println("========================")
   
       // 2. 添加元素
       val mutableSet2 = mutableSet + 77
       println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44)
       println(mutableSet2) //HashSet(33, 66, 22, 55, 11, 44, 77)
   
       mutableSet += 77
       println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44, 77)
   
       println("========================")
   
       val flag = mutableSet.add(10)
       println(flag) //true
       println(mutableSet) //HashSet(33, 66, 22, 55, 10, 11, 44, 77)
   
       val flag2 = mutableSet.add(10)
       println(flag2) //false
       println(mutableSet) //HashSet(33, 66, 22, 55, 10, 11, 44, 77)
   
   
       println("========================")
   
       // 3. 删除元素
       mutableSet -= 11
       println(mutableSet) //HashSet(33, 66, 22, 55, 10, 44, 77)
   
       val flag3 = mutableSet.remove(10)
       println(flag3) //true
       println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
   
       val flag4 = mutableSet.remove(10)
       println(flag4) //false
       println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
   
       println("========================")
   
       // 4. 合并两个Set
       val mutableSet3 = mutable.Set(10, 20, 30, 40)
       val mutableSet4 = mutableSet ++ mutableSet3
       println(mutableSet4) //HashSet(33, 66, 20, 22, 55, 40, 10, 44, 77, 30)
       println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
       println(mutableSet3) //HashSet(33, 66, 22, 55, 11, 44, 77)
   
       println("========================")
       mutableSet3 ++= mutableSet
       println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
       println(mutableSet3) //HashSet(33, 66, 20, 22, 55, 40, 10, 44, 77, 30)
   
       println("========================")
       // 打印集合
       mutableSet.foreach(println)
       /*
       33
       66
       22
       55
       44
       77
        */
   
     }
   
   }
   ```

## Map 集合

Scala 中的 Map 和 Java 类似，也是一个散列表，它存储的内容也是键值对（key-value） 映射

### 不可变 Map

1. 说明

   1. 创建不可变集合 Map
   2. 循环打印
   3. 访问数据
   4. 如果 key 不存在，返回 0

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-13 09:46
    * */
   object Test08_ImmutableMap {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建不可变集合 Map
       val map: Map[String, Int] = Map("a" -> 11, "b" -> 22, "c" -> 33)
       println(map) //Map(a -> 11, b -> 22, c -> 33)
       println(map.getClass) //class scala.collection.immutable.Map$Map3
   
       println("===============================")
       // 2. 遍历元素
   
       for (key <- map.keys) {
         // 使用 get 访问 map 集合的数据，会返回特殊类型 Option(选项): 有值 （Some ），无值(None)
         println(key + "=" + map.get(key).get)
         /*
         a=11
         b=22
         c=33
          */
       }
   
   
       println("---------------------")
   
   
       map.foreach(println)
       /*
       (a,11)
       (b,22)
       (c,33)
        */
       println("---------------------")
   
       map.foreach((kv: (String, Int)) => println(kv))
       /*
       (a,11)
       (b,22)
       (c,33)
        */
   
       println("===============================")
       // 3. 取map中所有的key 或者 value
       for (key <- map.keys) {
         println(s"$key---> ${map.get(key)}")
         // 注意这里的Some类型是Option的子类
         /*
         a---> Some(11)
         b---> Some(22)
         c---> Some(33)
          */
       }
   
       println("===============================")
   
       // 4. 访问某一个key的value
       println("a : " + map.get("a").get) //a : 11
       println("c : " + map.get("c")) //c : Some(33)
       //如果 key 不存在，返回 0
       println("b : " + map.getOrElse("b", 0)) //b : 22
   
   
       println("--------------------------------")
       println(map("a")) //1
   
     }
   
   }
   ```

### 可变 Map

1. 说明

   1. 创建可变集合
   2. 打印集合
   3. 向集合增加数据
   4. 删除数据
   5. 修改数据

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   import scala.collection.mutable
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-13 10:28
    * */
   object Test09_MutableMap {
   
     def main(args: Array[String]): Unit = {
       // 1. 创建可变集合
       val mutableMap: mutable.Map[String, Int] = mutable.Map("a" -> 11, "b" -> 22, "c" -> 33)
       println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 33)
       println(mutableMap.getClass) //class scala.collection.mutable.HashMap
   
   
       println("===================================")
       // 2. 添加元素
       mutableMap.put("d", 44)
       mutableMap.put("e", 55)
       println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 33, d -> 44, e -> 55)
   
       mutableMap += (("f", 66))
       mutableMap += ("g" -> 77)
       println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 50, e -> 10, f -> 66, g -> 77)
   
       println("===================================")
   
       // 3. 删除元素
       println(mutableMap.get("c")) //Some(33)
       mutableMap.remove("c")
       println(mutableMap.getOrElse("c", 0)) //0
   
       mutableMap -= "d"
       mutableMap -= ("g", "c")
       println(mutableMap) //HashMap(a -> 11, b -> 22, e -> 55, f -> 66)
       println("===================================")
   
       // 4. 修改元素
       mutableMap.update("c", 50)
       mutableMap("e") = 10
       println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 50, e -> 10, f -> 66)
   
       println("===================================")
   
       // 5. 合并两个Map
       val mutableMap2: mutable.Map[String, Int] = mutable.Map("aaa" -> 11, "b" -> 20, "hello" -> 44)
       mutableMap ++= mutableMap2
       println(mutableMap) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)
       println(mutableMap2) //HashMap(aaa -> 11, b -> 20, hello -> 44)
   
       println("---------------------------")
   
       val mutableMap3: mutable.Map[String, Int] = mutableMap ++ mutableMap2
       println(mutableMap) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)
       println(mutableMap2) //HashMap(aaa -> 11, b -> 20, hello -> 44)
       println(mutableMap3) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)
   
       println("==============================")
   
       // 打印集合
       mutableMap.foreach(kv => println(kv))
       /*
       (aaa,11)
       (a,11)
       (b,20)
       (c,50)
       (e,10)
       (f,66)
       (hello,44)
        */
   
     }
   
   }
   ```
   
   

## 元组

1. 说明

   元组也是可以理解为一个容器，可以存放各种相同或不同类型的数据。说的简单点，就是将多个无关的数据封装为一个整体，称为**元组**

   > 注意：元组中最大只能有 22 个元素。

2. 案例实操

   1. 声明元组的方式：(元素 1，元素 2，元素 3)
   2. 访问元组
   3. Map 中的键值对其实就是元组,只不过元组的元素个数为 2，称之为对偶

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-13 12:25
    * */
   object Test10_Tuple {
   
     def main(args: Array[String]): Unit = {
       // 1. 声明元组的方式：(元素 1，元素 2，元素 3)
       val tuple: (String, Int, Char, Boolean) = ("hello", 100, 'a', true)
       println(tuple) //(hello,100,a,true)
   
       println("=====================================")
   
       // 2. 访问数据
       // 通过元素的顺序进行访问，调用方式：_顺序号
       println(tuple._1) //hello
       println(tuple._2) //100
       println(tuple._3) //a
       println(tuple._4) //true
   
       println("=====================================")
   
       //通过索引访问数据
       println(tuple.productElement(1)) //100
       println("=====================================")
       // 3. 遍历元组数据
       for (elem <- tuple.productIterator) {
         println(elem)
         /*
         hello
         100
         a
         true
          */
       }
       println("=====================================")
       // 4. 嵌套元组
       val mulTuple = (11, 0.1, "hello", (10, "scala"))
       println(mulTuple._4._2) //scala
       println("=====================================")
   
       //Map 中的键值对其实就是元组,只不过元组的元素个数为 2，称之为对偶
       val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
       val map2 = Map(("a", 1), ("b", 2), ("c", 3))
   
       map.foreach(tuple => println(tuple._1 + "=" + tuple._2))
   
     }
   
   }
   ```

## 集合常用函数

### 基本属性和常用操作

1. 说明

   1. 获取集合长度
   2. 获取集合大小
   3. 循环遍历
   4. 迭代器
   5. 生成字符串
   6. 是否包含

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 08:21
    * */
   object Test11_CommonOp {
   
     def main(args: Array[String]): Unit = {
   
       val list = List(11, 22, 33, 44, 55, 66)
       val set = Set(10, 20, 30, 40, 50)
   
       // 1. 获取集合长度
       println(list.length) //6
   
       // 2. 获取集合大小
       println(set.size) //5
   
       println("===============================")
   
       // 2. 循环遍历
       for (elem <- list) {
         println(elem)
         /*
         11
         22
         33
         44
         55
         66
          */
       }
       println("===============================")
   
   
       set.foreach(println)
       /*
       10
       20
       50
       40
       30
        */
   
       println("===============================")
       //2. 迭代器
       for (elem <- list.iterator) println(elem)
       /*
       11
       22
       33
       44
       55
       66
        */
   
       println("===============================")
       //5. 生成字符串
       println(list) //List(11, 22, 33, 44, 55, 66)
       println(set) //HashSet(10, 20, 50, 40, 30)
       println(list.mkString("--")) //11--22--33--44--55--66
   
       println("===============================")
   
       //6. 是否包含
       println(list.contains(22)) //true
       println(set.contains(40)) //true
     }
   
   }
   
   ```

### 衍生集合

1. 说明

   1. 获取集合的头
   2. 获取集合的尾（不是头的就是尾）
   3. 集合最后一个数据
   4. 集合初始数据（不包含最后一个）
   5. 反转
   6. 取前（后）n 个元素
   7. 去掉前（后）n 个元素
   8. 并集
   9. 交集
   10. 差集
   11. 拉链
   12. 滑窗

2. 案例实操

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 08:37
    * */
   object Test12_DerivedCollection {
   
     def main(args: Array[String]): Unit = {
   
       val list = List(11, 22, 33, 44, 55, 66)
       val list2 = List(10, 20, 30, 40, 50, 60)
   
       // 1. 获取集合的头
       println(list.head) //11
   
       //2. 获取集合的尾（不是头的就是尾）
       println(list.tail) //List(22, 33, 44, 55, 66)
   
   
       // 3. 集合最后一个数据
       println(list2.last) //60
   
       println("==============================")
   
       // 4. 集合初始数据（不包含最后一个）
       println(list2.init) //List(10, 20, 30, 40, 50)
   
       //5. 反转
       println(list.reverse) //List(66, 55, 44, 33, 22, 11)
   
       // 6. 取前（后）n个元素
       println(list.take(3)) //List(11, 22, 33)
       //从后取4个元素
       println(list.takeRight(4)) //List(33, 44, 55, 66)
   
       println("==============================")
   
       // 7. 去掉前（后）n个元素
       println(list.drop(3)) //List(44, 55, 66)
       // 从右去掉4个元素
       println(list.dropRight(4)) //List(11, 22)
   
   
       println("==============================")
   
       // 8. 并集
       val concat = list.concat(list2)
       println("concat: " + concat) //concat: List(11, 22, 33, 44, 55, 66, 10, 20, 30, 40, 50, 60)
       println(list ::: list2) //List(11, 22, 33, 44, 55, 66, 10, 20, 30, 40, 50, 60)
   
   
       println("==============================")
   
       // 如果是set做并集，会去重
       val set1 = Set(11, 22, 33, 44, 55)
       val set2 = Set(10, 20, 30, 40, 11, 22)
   
       val concatSet = set1.concat(set2)
       println("concat set: " + concatSet) //concat set: HashSet(10, 20, 33, 11, 30, 22, 44, 40, 55)
       println(set1 ++ set2) //HashSet(10, 20, 33, 11, 30, 22, 44, 40, 55)
   
       println("==============================")
   
       // 9.交集
       val intersect = set1.intersect(set2)
       println("intersect: " + intersect) //intersect: HashSet(22, 11)
   
   
       println("==============================")
   
       // 10. 差集
       val diff1 = set1.diff(set2)
       val diff2 = set2.diff(set1)
       println("diff1: " + diff1) //diff1: HashSet(33, 44, 55)
       println("diff2: " + diff2) //diff2: HashSet(10, 20, 40, 30)
   
       println("==============================")
   
       // 11. 拉链(注:如果两个集合的元素个数不相等，那么会将同等数量的数据进 行拉链，多余的数据省略不用)
       println("zip: " + list.zip(list2)) //zip: List((11,10), (22,20), (33,30), (44,40), (55,50), (66,60))
       println("zip: " + list2.zip(list)) //zip: List((10,11), (20,22), (30,33), (40,44), (50,55), (60,66))
   
       println("==============================")
   
       //  12. 滑窗
       for (elem <- list.sliding(3))
         println(elem)
       /*
       List(11, 22, 33)
       List(22, 33, 44)
       List(33, 44, 55)
       List(44, 55, 66)
        */
   
   
       println("------------------------------------")
   
       for (elem <- list2.sliding(4, 2))
         println(elem)
   
       /*
       List(10, 20, 30, 40)
       List(30, 40, 50, 60)
        */
   
   
       println("------------------------------------")
   
       for (elem <- list2.sliding(3, 3))
         println(elem)
   
       /*
       List(10, 20, 30)
       List(40, 50, 60)
        */
   
     }
   
   }
   
   ```

### 集合计算简单函数

1. 说明

   1. 求和
   2. 求乘积
   3. 最大值
   4. 最小值
   5. 排序

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 10:05
    * */
   object Test13_SimpleFunction {
   
     def main(args: Array[String]): Unit = {
   
   
       val list = List(1, 2, 3, 4, 5, 6)
       val list2 = List(("a", 10), ("b", 20), ("c", 30), ("d", 40), ("e", 50), ("f", -1))
   
       // 1. 求和
       var sum = 0
       for (elem <- list)
         sum += elem
   
       println(sum) //21
   
   
       //2. 求乘积
       println(list.product) //720
   
       println("===================================")
   
       // 2. 最大值
       println(list.max) //6
       println(list2.maxBy((tuple: (String, Int)) => tuple._2)) //(e,50)
       // 上面的方法可以简写如下
       println(list2.maxBy(_._2)) //(e,50)
   
       println("===================================")
   
       //4. 最小值
       println(list.min) //1
       println(list2.minBy(_._2)) //(f,-1)
   
       println("===================================")
   
   
       //5. 排序
       // 5.1 sorted
       val sortedList = list.sorted
       println(sortedList) //List(1, 2, 3, 4, 5, 6)
   
       // 从大到小逆序排序
       println(list.sorted.reverse) //List(6, 5, 4, 3, 2, 1)
   
       // 传入隐式参数
       println(list.sorted(Ordering[Int].reverse)) //List(6, 5, 4, 3, 2, 1)
   
       // sorted对有内部多个List是不起作用的
       println(list2.sorted) //List((a,10), (b,20), (c,30), (d,40), (e,50), (f,-1))
   
       println("-----------------------------------------")
   
       // 5.2 sortBy
       println(list2.sortBy(_._2)) //List((f,-1), (a,10), (b,20), (c,30), (d,40), (e,50))
       println(list2.sortBy(_._2)(Ordering[Int].reverse)) //List((e,50), (d,40), (c,30), (b,20), (a,10), (f,-1))
   
       println("-----------------------------------------")
       // 5.3 sortWith
       println(list.sortWith((a: Int, b: Int) => {
         a < b
       })) //List(1, 2, 3, 4, 5, 6)
   
       // 上面的方式可以简写如下
       println(list.sortWith(_ < _)) //List(1, 2, 3, 4, 5, 6)
   
       println(list.sortWith(_ > _)) //List(6, 5, 4, 3, 2, 1)
     }
   
   }
   ```

 1.  `sorted`

     对一个集合进行自然排序，通过传递隐式的 `Ordering`

 2.  `sortBy`

     对一个属性或多个属性进行排序，通过它的类型。

 3.  `sortWith`

     基于函数的排序，通过一个 `comparator `函数，实现自定义排序的逻辑

### 集合计算高级函数

1. 说明

   1. 过滤

   2. 转化/映射（map）

   3. 扁平化

   4. 扁平化+映射 注：`flatMap `相当于先进行 map 操作，在进行 flatten 操作

      集合中的每个元素的子元素映射到某个函数并返回新集合

   5. 分组(group)

      按照指定的规则对集合的元素进行分组

   6. 简化（归约）

   7. 折叠

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 11:35
    * */
   object Test14_HighLevelFunction_Map {
   
     def main(args: Array[String]): Unit = {
       val list = List(11, 22, 33, 44, 55, 66, 77)
   
       // 1. 过滤
       // 选取偶数
       val evenList = list.filter((elem: Int) => {
         elem % 2 == 0
       })
       println(evenList) //List(22, 44, 66)
   
       // 选取奇数
       println(list.filter(_ % 2 == 1)) //List(11, 33, 55, 77)
   
       println("======================================")
   
       // 2. 映射map
       // 把集合中每个数乘2
       println(list.map(_ * 2)) //List(22, 44, 66, 88, 110, 132, 154)
       println(list.map(x => x * x)) //List(121, 484, 1089, 1936, 3025, 4356, 5929)
   
       println("======================================")
   
   
       // 3. 扁平化
       val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5), List(6, 7, 8, 9))
       val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
       println(flatList) //List(1, 2, 3, 4, 5, 6, 7, 8, 9)
   
       val flatList2 = nestedList.flatten
       println(flatList2) //List(1, 2, 3, 4, 5, 6, 7, 8, 9)
   
       println("======================================")
   
       // 4. 扁平映射
       // 将一组字符串进行分词，并保存成单词的列表
       val strings: List[String] = List("hello world", "hello scala", "hello java", "we study")
       val splitList: List[Array[String]] = strings.map(_.split(" ")) // 分词
       val flattenList = splitList.flatten // 打散扁平化
   
       println(flattenList) //List(hello, world, hello, scala, hello, java, we, study)
   
       // 上面的方式也可以简写如下
       // 扁平化+映射 注：flatMap 相当于先进行 map 操作，在进行 flatten 操作
       val flatmapList = strings.flatMap(_.split(" "))
       println(flatmapList) //List(hello, world, hello, scala, hello, java, we, study)
   
   
       println("======================================")
   
       // 5. 分组groupBy
       // 分成奇偶两组
       val groupMap: Map[Int, List[Int]] = list.groupBy(_ % 2)
       val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "偶数" else "奇数")
   
       println(groupMap) //HashMap(0 -> List(22, 44, 66), 1 -> List(11, 33, 55, 77))
       println(groupMap2) //HashMap(偶数 -> List(22, 44, 66), 奇数 -> List(11, 33, 55, 77))
   
       println("======================================")
   
       // 给定一组词汇，按照单词的首字母进行分组
       val worldList = List("china", "america", "alice", "canada", "cary", "bob", "japan")
       println(worldList.groupBy(_.charAt(0))) //HashMap(j -> List(japan), a -> List(america, alice), b -> List(bob), c -> List(china, canada, cary))
   
     }
   
   }
   ```

3. `Reduce `方法 

   `Reduce `简化（归约） ：通过指定的逻辑将集合中的数据进行聚合，从而减少数据，最 终获取结果

   ```scala
       val list = List(11, 22, 33, 44, 55, 66)
   
       // 1. reduce
       println(list.reduce(_ + _)) //231
       println(list.reduceLeft(_ + _)) //231
       println(list.reduceRight(_ + _)) //231
   
       println("===============================")
   
       val list2 = List(10, 20, 30, 40, 50)
       println(list2.reduce(_ - _)) //-130
       println(list2.reduceLeft(_ - _)) //-130
   
       // (10-(20 - (30 - (40 - 50))
       println(list2.reduceRight(_ - _)) //30
   ```

4. Fold 方法

   Fold 折叠：化简的一种特殊情况。

   1. 案例实操：fold 基本使用

      ```scala
          // 2. fold
          // fold 方法使用了函数柯里化，存在两个参数列表
          // 第一个参数列表为 ： 零值（初始值）
          // 第二个参数列表为： 简化规则
          println(list.fold(10)(_ + _)) //241
      
          // fold 底层其实为 foldLeft
          // 10 - 11 - 22- 33 - 44 - 55 - 66
          println(list.foldLeft(10)(_ - _)) //-221
      
          // 11 - (10-(20 - (30 - (40 - 50))
          println(list2.foldRight(11)(_ - _)) //19
      ```
   
   2. 案例实操：两个集合合并
   
      ```scala
      package com.scala.chapter07
      
      import scala.collection.mutable
      
      /**
       * @program: scala
       * @description: ${description}
       * @author: JunWen
       * @create: 2023-12-14 18:58
       * */
      object Test16_MergeMap {
      
        def main(args: Array[String]): Unit = {
          val map = Map("a" -> 1, "b" -> 3, "c" -> 7)
          val mutableMap = mutable.Map("a" -> 22, "b" -> 33, "c" -> 44, "d" -> 55)
      
          println(map ++ mutableMap) //Map(a -> 22, b -> 33, c -> 44, d -> 55)
      
      
          val mergeMap = map.foldLeft(mutableMap)((result, kv) => {
            val key = kv._1
            val value = kv._2
            result(key) = result.getOrElse(key, 0) + value
            result
      
          })
      
          println(mergeMap) //HashMap(a -> 23, b -> 36, c -> 51, d -> 55)
      
      
        }
      
      }
      ```



### 普通 WordCount 案例

1. 需求

   单词计数：将集合中出现的相同的单词，进行计数，取计数排名前三的结果

2. 需求分析

   ![](http://www.dxb02.top/photos/scala/10.jpg)

3. 实例操作

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 19:04
    * */
   object Test17_CommonWordCount {
   
     def main(args: Array[String]): Unit = {
   
       // 单词计数：将集合中出现的相同的单词，进行计数，取计数排名前三的结果
       val stringList: List[String] = List(
         "hello",
         "hello world",
         "hello scala",
         "hello spark from scala",
         "hello flink from scala",
         "hello haddop"
       )
   
       // 1. 对字符串进行切分，得到一个打散所有单词的列表
       /*
       val wordList: List[Array[String]] = stringList.map(_.split(" "))
       val wordList2: List[String] = wordList.flatten
       println(wordList2) //List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala, hello, haddop)
    */
   
       // 上面的方式也可以简写为如下
       val wordList = stringList.flatMap(_.split(" "))
       println(wordList) //List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala, hello, haddop)
   
   
       // 2. 相同的单词进行分组,
       val groupMap: Map[String, List[String]] = wordList.groupBy(word => word)
       println(groupMap) //HashMap(world -> List(world), flink -> List(flink), haddop -> List(haddop), spark -> List(spark), hello -> List(hello, hello, hello, hello, hello, hello), scala -> List(scala, scala, scala), from -> List(from, from))
   
   
       // 3. 对分组之后的list取长度，得到每个单词的个数
       // (word, list) => (word, count)
       val countMap: Map[String, Int] = groupMap.map(kv => (kv._1, kv._2.size))
       println(countMap) //HashMap(world -> 1, flink -> 1, haddop -> 1, spark -> 1, hello -> 6, scala -> 3, from -> 2)
   
       //4.  对计数完成后的结果进行排序（降序）, 对排序后的结果取前 3 名
       val sortList: List[(String, Int)] = countMap.toList.sortWith(_._2 > _._2).take(3)
   
       println(sortList) //List((hello,6), (scala,3), (from,2))
   
     }
   
   }
   
   ```

### 复杂 WordCount 案例

1. 方式一

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 19:04
    * */
   object Test18_ComplexWordCount {
   
     def main(args: Array[String]): Unit = {
   
       val tupleList: List[(String, Int)] = List(
         ("hello", 1),
         ("hello world", 2),
         ("hello scala", 3),
         ("hello spark from scala", 1),
         ("hello flink from scala", 2)
       )
   
       // 思路一：直接展开为普通版本
         // 第一种方式（不通用）
       val newStringList: List[String] = tupleList.map(
         kv => {
           (kv._1.trim + " ") * kv._2
         }
       )
   
       println(newStringList) //List(hello , hello world hello world , hello scala hello scala hello scala , hello spark from scala , hello flink from scala hello flink from scala )
   
   
       // 接下来操作与普通版本完全一致
       val wordCountList: List[(String, Int)] = newStringList
         .flatMap(_.split(" ")) // 空格分词
         .groupBy(word => word) // 按照单词分组
         //在 map 中，如果传进来什么就返回什么，不要用_省略
         .map(kv => (kv._1, kv._2.size)) // 统计出每个单词的个数
         .toList
         .sortBy(_._2)(Ordering[Int].reverse)
         .take(3)
   
   
       println(wordCountList) //List((hello,9), (scala,6), (from,3))
     }
   
   }
   
   ```

   

2. 方式二

   ```scala
   package com.scala.chapter07
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 19:04
    * */
   object Test18_ComplexWordCount {
   
     def main(args: Array[String]): Unit = {
   
       val tupleList: List[(String, Int)] = List(
         ("hello", 1),
         ("hello world", 2),
         ("hello scala", 3),
         ("hello spark from scala", 1),
         ("hello flink from scala", 2)
       )
   
       // 思路二：直接基于预统计的结果进行转换
       // 1. 将字符串打散为单词，并结合对应的个数包装成二元组
       val preCountList: List[(String, Int)] = tupleList.flatMap(
         tuple => {
           val strings: Array[String] = tuple._1.split(" ")
           strings.map(word => (word, tuple._2))
         }
       )
   
       println(preCountList) //List((hello,1), (hello,2), (world,2), (hello,3), (scala,3), (hello,1), (spark,1), (from,1), (scala,1), (hello,2), (flink,2), (from,2), (scala,2))
   
   
       // 2. 对二元组按照单词进行分组
       val preCountMap: Map[String, List[(String, Int)]] = preCountList.groupBy(_._1)
       println(preCountMap) //HashMap(world -> List((world,2)), flink -> List((flink,2)), spark -> List((spark,1)), hello -> List((hello,1), (hello,2), (hello,3), (hello,1), (hello,2)), scala -> List((scala,3), (scala,1), (scala,2)), from -> List((from,1), (from,2)))
   
   
       // 3. 叠加每个单词预统计的个数值
       val countMap: Map[String, Int] = preCountMap.view.mapValues(tupleList => tupleList.map(_._2).sum).toMap
   
       println(countMap) //HashMap(world -> 2, flink -> 2, spark -> 1, hello -> 9, scala -> 6, from -> 3)
   
       // 4. 转换成list，排序取前3
       val countList = countMap.toList
         .sortWith(_._2 > _._2)
         .take(3)
   
       println(countList) //List((hello,9), (scala,6), (from,3))
   
     }
   
   }
   
   ```



## 队列

1. 说明

   Scala 也提供了队列（`Queue`）的数据结构，队列的特点就是先进先出。进队和出队的方法分别为 `enqueue `和 `dequeue`

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   import scala.collection.immutable.Queue
   import scala.collection.mutable
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 19:05
    * */
   object Test19_Queue {
   
     def main(args: Array[String]): Unit = {
       // 创建一个可变队列
       val queue: mutable.Queue[String] = new mutable.Queue[String]()
   
       queue.enqueue("a", "b", "c")
   
       println(queue) //Queue(a, b, c)
       println(queue.dequeue()) //a
   
       println(queue) //Queue(b, c)
       println(queue.dequeue()) //b
       println(queue) //Queue(c)
   
       println("-----------------------------------")
   
       queue.enqueue("d", "e")
       println(queue) //Queue(c, d, e)
       println(queue.dequeue()) //c
       println(queue) //Queue(d, e)
   
       println("=========================================")
   
       // 不可变队列
       val queue2: Queue[String] = Queue("a", "b", "c")
       val queue3 = queue2.enqueue("d")
       println(queue2) //Queue(a, b, c)
       println(queue3) //Queue(a, b, c, d)
   
     }
   
   }
   ```
   
   

## 并行集合

1. 说明

   Scala 为了充分使用多核 CPU，提供了并行集合（有别于前面的串行集合），用于多核 环境的并行计算

2. 实例操作

   ```scala
   package com.scala.chapter07
   
   import scala.collection.parallel.CollectionConverters.seqIsParallelizable
   import scala.collection.parallel.ParSeq
   
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-14 19:17
    * */
   object Test20_Parallel {
   
     def main(args: Array[String]): Unit = {
   
   
       val result: IndexedSeq[Long] = (1 to 100).map(x => Thread.currentThread().getId)
   
       // 可以看到主要在main线程处理
       println(result)
       //Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
   
   
       val result2: ParSeq[Long] = (1 to 100).par.map(x => Thread.currentThread().getId)
   
       // 可看到线程ID发生了变化
       println(result2)
       //ParVector(16, 16, 16, 23, 23, 23, 21, 21, 21, 21, 21, 21, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 18, 18, 18, 27, 27, 27, 22, 22, 22, 22, 22, 22, 20, 20, 20, 25, 18, 18, 25, 25, 25, 16, 16, 23, 23, 17, 17, 17, 19, 27, 27, 16, 27, 27, 17, 19, 19, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 21, 27, 27, 23, 19, 19, 24, 21, 21, 27, 27, 23, 23)
   
   
     }
   
   }
   
   ```
   
   > 注:Scala2.13已把parallel移出,需导入后方能使用
   >
   > ```xml
   > <dependency>
   >     <groupId>org.scala-lang.modules</groupId>
   >     <artifactId>scala-parallel-collections_2.13</artifactId>
   >     <version>0.2.0</version>
   > </dependency>
   > ```

------

# 第八章 模式匹配

Scala 中的模式匹配类**似于 Java 中的 switch 语法**

```java
int i = 10
switch (i) {
case 10 :
	System.out.println("10");
	break;
case 20 :
	System.out.println("20");
	break;
default :
	System.out.println("other number");
	break;
}
```

但是 scala 从语法中补充了更多的功能，所以更加强大

## 基本语法

模式匹配语法中，采用 `match `关键字声明，每个分支采用 `case `关键字进行声明，当需 要匹配时，会从第一个 `case `分支开始，如果匹配成功，那么执行对应的逻辑代码，如果匹配不成功，继续执行下一个分支进行判断。如果所有 `case `都不匹配，那么会执行 `case _`分支， 类似于 Java 中 `default `语句

```scala
package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:33
 * */
object Test01_PatternMatchBase {

  def main(args: Array[String]): Unit = {
    // 1. 基本定义语法
    val x: Int = 5
    val y: String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "other"
    }

    println(y) //other


    // 2. 示例：用模式匹配实现简单二元运算
    val a = 11
    val b = 22

    def matchDualOp(op: Char): Int = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case '%' => a % b
      case _ => -1
    }

    println(matchDualOp('+')) //33
    println(matchDualOp('/')) //0
    println(matchDualOp('\\')) //-1


    println("=========================")

  }

}

```

1. 如果所有 `case `都不匹配，那么会执行 `case _` 分支，类似于 Java 中 default 语句， 若此时没有 `case _` 分支，那么会抛出 `MatchError`
2. 每个 `case `中，不需要使用 `break `语句，自动中断 `case`。
3. `match case` 语句可以匹配任何类型，而不只是字面量。
4. => 后面的代码块，直到下一个 `case `语句之前的代码是**作为一个整体执行**，可以使用{}括起来，也可以不括

## 模式守卫

1. 说明

   如果想要表达匹配某个范围的数据，就需要在模式匹配中增加条件守卫

2. 实例操作

   ```scala
       // 3. 模式守卫
       // 求一个整数的绝对值
       def abs(num: Int): Int = {
         num match {
           case i if i >= 0 => i
           case i if i < 0 => -i
         }
       }
   
   
       println(abs(10)) //10
       println(abs(0)) //0
       println(abs(-20)) //20
   
   
   ```

## 模式匹配类型

### 匹配常量

1. 说明

   Scala 中，模式匹配可以匹配所有的字面量，包括字符串，字符，数字，布尔值等等

2. 实例操作

   ```scala
   package com.scala.chapter08
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-15 11:34
    * */
   object Test02_MatchTypes {
   
     def main(args: Array[String]): Unit = {
       // 1. 匹配常量
       def describeConst(x: Any): String = x match {
         case 1 => "Int one"
         case "hello" => "String hello"
         case true => "Boolean true"
         case '+' => "Char +"
         case _ => ""
       }
   
       println(describeConst("hello")) //String hello
       println(describeConst('+')) //Char +
       println(describeConst(0.3))
   
     }
   
   }
   
   ```
   
   

### 匹配类型

1. 说明

   需要进行类型判断时，可以使用前文所学的 `isInstanceOf[T]`和 `asInstanceOf[T]`，也可使用模式匹配实现同样的功能

2. 实例操作

   ```scala
       // 2. 匹配类型
       def describeType(x: Any): String = x match {
         case i: Int => "Int " + i
         case s: String => "String " + s
         case list: List[String] => "List " + list
         case array: Array[Int] => "Array[Int] " + array.mkString(",")
         case a => "Something else: " + a
       }
   
       println(describeType(22)) //Int 22
       println(describeType("hello")) //String hello
       println(describeType(List("hi", "hello"))) //List List(hi, hello)
       // List 泛型擦除
       println(describeType(List(10, 20))) //List List(10, 20)
       ////数组例外，可保留泛型
       println(describeType(Array("java", "scala"))) //Something else: [Ljava.lang.String;@2ef9b8bc
       println(describeType(Array(11, 22))) //Array[Int] 11,22
   ```
   
   

### 匹配数组

1. 说明

   scala 模式匹配可以对集合进行精确的匹配，例如匹配只有两个元素的、且第一个元素 为 0 的数组

1. 实例操作

   ```scala
       // 3. 匹配数组
       for (arr <- List(
         Array(0),
         Array(1, 0),
         Array(0, 1, 0),
         Array(1, 1, 0),
         Array(2, 3, 7, 15),
         Array("hello", 1, 30),
       )) {
         val result = arr match {
           case Array(0) => "0"  //匹配 Array(0) 这个数组
           case Array(1, 0) => "Array(1,0)"
           case Array(x, y) => "Array: " + x + " , " + y //匹配有两个元素的数组，然后将将元素值赋给对应的 x, y
           case Array(0, _*) => "以0开头的数组"
           case Array(x, 1, z) => "中间为1的三元素数组"
           case _ => "something else"
   
         }
   
         /*
         0
         Array(1,0)
         以0开头的数组
         中间为1的三元素数组
         something else
         中间为1的三元素数组
          */
         println(result)
       }
   ```

### 匹配列表

1. 方式一

   ```scala
       for (list <- List(
         List(0),
         List(1, 0),
         List(0, 0, 0),
         List(1, 1, 0),
         List(11),
         List("hello")
       )) {
         val result = list match {
           case List(0) => "0" //匹配 List(0)
           case List(x, y) => "List(x,y): " + x + " , " + y //匹配有两个元素的 List
           case List(0, _*) => "List(0,....)"
           case List(a) => "List(a): " + a
           case _ => "something else"
         }
   
         /*
         0
         List(x,y): 1 , 0
         List(0,....)
         something else
         List(a): 11
         List(a): hello
          */
         println(result)
       }
   ```

2. 方式二

   ```scala
       val list = List(11, 22, 33, 44, 55) //first : 11 , second: 22, rest : List(33, 44, 55)
       val list2 = List(10)  //something else
   
       list match {
         case first :: second :: rest => println(s"first : $first , second: $second, rest : $rest")
         case _ => println("something else")
       }
   ```
   

### 匹配元组

```scala
    //对一个元组集合进行遍历
    for (tuple <- List(
      (0, 1),
      (0, 0),
      (0, 1, 0),
      (0, 1, 1),
      (1, 23, 56),
      ("hello", true, 0.5)
    )) {
      val result = tuple match {
        case (a, b) => "" + a + " , " + b
        case (0, _) => "(0,_)"   //是第一个元素是 0 的元组
        case (a, 1, _) => "(a,1,_) " + a
        case (x, y, z) => "(x,y,z) " + x + " " + y + " " + z
        case _ => "something else"  //默认
      }

      /*
      0 , 1
      0 , 0
      (a,1,_) 0
      (a,1,_) 0
      (x,y,z) 1 23 56
      (x,y,z) hello true 0.5
       */
      println(result)
    }
```

### 匹配对象及样例类

基本语法

```scala
package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:35
 * */
object Test04_MatchObject {

  def main(args: Array[String]): Unit = {
    val student = Student("zhangsan", 11)

    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("zhangsan", 11) => "zhangsan , 11"
      case _ => "Else"
    }

    println(result)	//zhangsan , 11
  }

}

// 定义类
class Student(val name: String, val age: Int)

// 定义伴生对象
object Student {
  def apply(name: String, age: Int): Student = new Student(name, age)

  // 必须实现一个unapply方法，用来对对象属性进行拆解
  def unapply(student: Student): Option[(String, Int)] = {
    if (student == null) {
      None
    } else {
      Some((student.name, student.age))
    }
  }
}
```

小结

- `val student = Student("zhangsan",11)`，该语句在执行时，实际调用的是 `Student`伴生对象中的 `apply `方法，因此不用 `new `关键字就能构造出相应的对象
- 当将 `Student("zhangsan", 11)`写在 `case `后时`[case Student("zhangsan", 11) => ""zhangsan , 11"]`，会默认调用 `unapply `方法(对象提取器)，`student`作为 `unapply `方法的参数，`unapply `方法 将 `student`对象的 `name `和 `age `属性提取出来，与 `Student("zhangsan", 11)`中的属性值进行匹配
- `case `中对象的 `unapply `方法(提取器)返回 `Some`，且所有属性均一致，才算匹配成功, 属性不一致，或返回 `None`，则匹配失败
- 若只提取对象的一个属性，则提取器为 `unapply(obj:Obj):Option[T]`
- 若提取对象的多个属性，则提取器为 `unapply(obj:Obj):Option[(T1,T2,T3…)]`
- 若提取对象的可变个属性，则提取器为` unapplySeq(obj:Obj):Option[Seq[T]]`



样例类

1. 语法

   ```scala
   case class Person (name: String, age: Int)
   ```

2. 说明

   1. 样例类仍然是类，和普通类相比，只是其自动生成了伴生对象，并且伴生对象中自动提供了一些常用的方法，如 `apply`、`unapply`、`toString`、`equals`、`hashCode `和 `copy`
   2. 样例类是为模式匹配而优化的类，因为其默认提供了 `unapply `方法，因此，样例类可以直接使用模式匹配，而无需自己实现 `unapply `方法
   3. 构造器中的每一个参数都成为 `val`，除非它被显式地声明为 `var`（不建议这样做）

3. 实例操作

   上述匹配对象的案例使用样例类会节省大量代码

   ```scala
   package com.scala.chapter08
   
   /**
    * @program: scala
    * @description: ${description}
    * @author: JunWen
    * @create: 2023-12-15 11:35
    * */
   object Test05_MatchCaseClass {
   
     def main(args: Array[String]): Unit = {
   
   
       val user = User("jack", 20)
   
       // 针对对象实例的内容进行匹配
   
       val result = user match {
         case User("jack", 20) => "Jack , 20"
         case _ => "Else"
       }
   
       println(result) //Jack , 20
     }
   
   }
   
   // 定义样例类
   case class User(name: String, age: Int)
   ```

##  变量声明中的模式匹配

```scala
package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:34
 * */
object Test03_MatchTupleExtend {

  def main(args: Array[String]): Unit = {

    // 1. 在变量声明时匹配
    val (x, y) = (10, "hello")
    println(s"x: $x , y: $y") //x: 10 , y: hello

    val List(first, second, _*) = List(11, 22, 33, 44)
    println(s"first : $first , second: $second") //first : 11 , second: 22

    val fir :: sec :: rest = List(10, 20, 30.50)
    println(s"first : $fir , second: $sec, rest: $rest") //first : 10.0 , second: 20.0, rest: List(30.5)

    println("===========================================")
  }

}

```

## for 表达式中的模式匹配

```scala
package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:34
 * */
object Test03_MatchTupleExtend {

  def main(args: Array[String]): Unit = {

    // 2. for推导式中进行模式匹配
    val list: List[(String, Int)] = List(("a", 11), ("b", 20), ("c", 33), ("d", 40))
    // 2.1 原本的遍历方式
    for (elem <- list) {
      println(elem._1 + " " + elem._2)
      /*
      a 11
      b 20
      c 33
      d 40
       */
    }
    println("--------------------------")
    // 2.2 将List的元素直接定义为元组，对变量赋值
    for ((word, count) <- list) {
      println(word + ": " + count)
      /*
      a: 11
      b: 20
      c: 33
      d: 40
       */
    }

    println("--------------------------")

    // 2.3 可以不考虑某个位置的变量，只遍历key或者value
    for ((word, _) <- list)
      println(word)
    /*
    a
    b
    c
    d
     */

    println("--------------------------")

    // 2.4 可以指定某个位置的值必须是多少
    for (("a", count) <- list)
      println(count) //11

  }

}

```

## 偏函数中的模式匹配(了解)

偏函数也是函数的一种，通过偏函数我们可以方便的对输入参数做更精确的检查。例如 该偏函数的输入类型为 `List[Int]`，而我们需要的是第一个元素是 0 的集合，这就是通过模式匹配实现的

1. 偏函数定义

   ```scala
   val second: PartialFunction[List[Int], Option[Int]] = {
    	case x :: y :: _ => Some(y)
   }
   ```

   > 注：该偏函数的功能是返回输入的 List 集合的第二个元素

2. 偏函数原理

   上述代码会被 scala 编译器翻译成以下代码，与普通函数相比，只是多了一个用于参数 检查的函数——`isDefinedAt`，其返回值类型为 `Boolean`

   ```scala
   val second = new PartialFunction[List[Int], Option[Int]] {
   //检查输入参数是否合格
   override def isDefinedAt(list: List[Int]): Boolean = list match
   {
    	case x :: y :: _ => true
    	case _ => false
   }
    //执行函数逻辑
   override def apply(list: List[Int]): Option[Int] = list match
   {
    	case x :: y :: _ => Some(y)
    	}
   }
   
   ```

3. 偏函数使用

   偏函数不能像` second(List(1,2,3))`这样直接使用，因为这样会直接调用 `apply `方法，而应该调用 `applyOrElse `方法，如下

   ```scala
   second.applyOrElse(List(1,2,3), (_: List[Int]) => None)
   ```

   `applyOrElse `方法的逻辑为 `if (ifDefinedAt(list)) apply(list) else default`。如果输入参数满 足条件，即 `isDefinedAt `返回 `true`，则执行 `apply `方法，否则执行 `defalut `方法，`default `方法 为参数不满足要求的处理逻辑。

4. 案例操作

   1. 需求

      ```scala
      package com.scala.chapter08
      
      /**
       * @program: scala
       * @description: ${description}
       * @author: JunWen
       * @create: 2023-12-15 11:35
       * */
      object Test06_PartialFunction {
      
        def main(args: Array[String]): Unit = {
          val list: List[(String, Int)] = List(("a", 11), ("b", 22), ("c", 33), ("d", 10))
      
          // 1. map转换，实现key不变，value2倍
          val newList = list.map(tuple => (tuple._1, tuple._2 * 2))
      
          // 2. 用模式匹配对元组元素赋值，实现功能
          val newList2 = list.map(
            tuple => {
              tuple match {
                case (word, count) => (word, count * 2)
              }
            }
          )
      
          // 3. 省略lambda表达式的写法，进行简化
          val newList3 = list.map {
            case (word, count) => (word, count * 2)
          }
      
          println(newList) //List((a,22), (b,44), (c,66), (d,20))
          println(newList2) //List((a,22), (b,44), (c,66), (d,20))
          println(newList3) //List((a,22), (b,44), (c,66), (d,20))
      
          println("====================================")
      
          // 偏函数的应用，求绝对值
          // 对输入数据分为不同的情形：正、负、0
          val positiveAbs: PartialFunction[Int, Int] = {
            case x if x > 0 => x
          }
          val negativeAbs: PartialFunction[Int, Int] = {
            case x if x < 0 => -x
          }
      
          val zeroAbs: PartialFunction[Int, Int] = {
            case 0 => 0
          }
      
          def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs)(x)
      
          println(abs(-67)) // 67
          println(abs(11)) //11
          println(abs(0)) //0
      
        }
      
      }
      ```
   
   2. 实例操作
   
      将该 List(1,2,3,4,5,6,"test")中的 Int 类型的元素加一，并去掉字符串。
   
      - 方法一
   
        ```scala
        List(1,2,3,4,5,6,"test").filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int] + 1).foreach(println)
        ```
   
      - 方法二
   
        ```scala
        List(1, 2, 3, 4, 5, 6, "test").collect { case x: Int => x + 1 }.foreach(println)
        ```

------

# 第九章 异常

语法处理上和 Java 类似，但是又不尽相同。

## Java 异常处理

```java
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            int c = a / b;
        }catch (ArithmeticException e){
            // catch 时，需要将范围小的写到前面
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
    }
}

```

> 注意事项
>
> 1. Java 语言按照 try—catch—finally 的方式来处理异常
> 2. 不管有没有异常捕获，都会执行 `finally`，因此通常可以在 `finally `代码块中释放资 源
> 3. 可以有多个 `catch`，分别捕获对应的异常，这时需要把范围小的异常类写在前面， 把范围大的异常类写在后面，否则编译错误



## Scala 异常处理

```scala
package com.scala.chapter09

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:54
 * */
object Test01_Exception {

  def main(args: Array[String]): Unit = {

    try {
      val n = 10 / 0
    } catch {
      case e: ArithmeticException => {
        println("ArithmeticException")
      }
      case e: Exception => {
        println("Exception")
      }
    } finally {
      println("hand ending")
    }
    /*
    ArithmeticException
    hand ending
     */
  }

}
```

1. 我们将可疑代码封装在 try 块中。在 try 块之后使用了一个 catch 处理程序来捕获异 常。如果发生任何异常，catch 处理程序将处理它，程序将不会异常终止

2. Scala 的异常的工作机制和 Java 一样，但是 Scala 没有“checked（编译期）”异常， 即 Scala 没有编译异常这个概念，异常都是在运行的时候捕获处理

3. 异常捕捉的机制与其他语言中一样，如果有异常发生，catch 子句是按次序捕捉的。 因此，在 catch 子句中，越具体的异常越要靠前，越普遍的异常越靠后，如果把越普遍的异 常写在前，把具体的异常写在后，在 Scala 中也不会报错，但这样是非常不好的编程风格

4. finally 子句用于执行不管是正常处理还是有异常发生时都需要执行的步骤，一般用 于对象的清理工作，这点和 Java 一样

5. 用 throw 关键字，抛出一个异常对象。所有异常都是 Throwable 的子类型。throw 表 达式是有类型的，就是 Nothing，因为 Nothing 是所有类型的子类型，所以 throw 表达式可以用在需要类型的地方

   ```scala
   def test():Nothing = {
       throw new Exception("不对")
   }
   ```

6. java 提供了 throws 关键字来声明异常。可以使用方法定义声明异常。它向调用者函 数提供了此方法可能引发此异常的信息。它有助于调用函数处理并将该代码包含在 try-catch 块中，以避免程序异常终止。在 Scala 中，可以使用 throws 注解来声明异常

   ```scala
   def main(args: Array[String]): Unit = {
       f11()
   }
   @throws(classOf[NumberFormatException])
   def f11()={
       "abc".toInt
   }
   ```

------

# 第十章 隐式转换



> **当编译器第一次编译失败的时候，会在当前的环境中查找能让代码编译通过的方法，用 于将类型进行转换，实现二次编译**

## 隐式函数

1. 说明

   隐式转换可以在不需改任何代码的情况下，扩展某个类的功能。

2. 实例操作

   需求：通过隐式转化为 Int 类型增加方法

   ```scala
   
   ```





## 隐式参数

普通方法或者函数中的参数可以通过 `implicit `关键字声明为隐式参数，调用该方法时， 就可以传入该参数，编译器会在相应的作用域寻找符合条件的隐式值。

1. 说有

   1. 同一个作用域中，相同类型的隐式值只能有一个
   2. 编译器按照隐式参数的类型去寻找对应类型的隐式值，与隐式值的名称无关
   3. 隐式参数优先于默认参数

2. 实例操作

   ```scala
   
   ```

   

## 隐式类

在 Scala2.10 后提供了隐式类，可以使用 implicit 声明类，隐式类的非常强大，同样可 以扩展类的功能，在集合中隐式类会发挥重要的作用

1. 隐式类说明

   1. 其所带的构造参数有且只能有一个
   2. 

2. 实例操作

   ```scala
   
   ```

   

## 隐式解析机制

1. 说明

   1. 首先会在当前代码作用域下查找隐式实体（隐式方法、隐式类、隐式对象）。（一 般是这种情况）
   2. 如果第一条规则查找隐式实体失败，会继续在隐式参数的类型的作用域里查找。 类型的作用域是指与该类型相关联的全部伴生对象以及该类型所在包的包对象

2. 实例操作

   ```scala
   
   ```



------

# 第十一章 泛型

## 协变和逆变

1. 语法

   1. ```scala
      class MyList[+T]{ //协变
      }
      class MyList[-T]{ //逆变
      }
      class MyList[T] //不变
      
      ```

      

2. 说明

   - 协变：Son 是 Father 的子类，则 MyList[Son] 也作为 MyList[Father]的“子类”。
   - 逆变：Son 是 Father 的子类，则 MyList[Son]作为 MyList[Father]的“父类”
   - 不变：Son 是 Father 的子类，则 MyList[Father]与 MyList[Son]“无父子关系”

3. 实操

   ```scala
   
   ```

## 泛型上下限

1. 语法

   ```
   Class PersonList[T <: Person]{ //泛型上限
   }
   Class PersonList[T >: Person]{ //泛型下限
   }
   ```

2. 说明

   泛型的上下限的作用是对传入的泛型进行限定

3. 实例操作

   ```scala
   
   ```





## 上下文限定

1. 语法

   ```scala
   def f[A : B](a: A) = println(a) //等同于 def f[A](a:A)(implicit arg:B[A])=println(a)
   ```

2. 说明

   上下文限定是将泛型和隐式转换的结合产物，以下两者功能相同，使用上下文限定[A : Ordering]之后，方法内无法使用隐式参数名调用隐式参数，需要通过 `implicitly[Ordering[A]]` 获取隐式变量，如果此时无法查找到对应类型的隐式变量，会发生出错误。

   ```scala
   
   ```

3. 实例操作

   ```scala
   
   ```



