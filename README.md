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

   ```
   
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
      
      ```

   2. 需求 2：Scala 中 if else 表达式其实是有返回值的，具体返回值取决于满足条件的 代码体的最后一行内容

      ```scala
      
      ```

   3. 需求 3：Scala 中返回值类型不一致，取它们共同的祖先类型

      ```scala
      
      ```

   4. 需求 4：Java 中的三元运算符可以用 if else 实现

      > 如果大括号{}内的逻辑代码只有一行，大括号可以省略。如果省略大括号，if 只对最近 的一行逻辑代码起作用

      ```scala
      
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
   
   ```



## Switch 分支结构

> 在 Scala 中没有 Switch，而是使用**模式匹配来处理**。 
>
> 模式匹配涉及到的知识点较为综合，因此我们放在后面讲解





## For 循环控制

Scala 也为 for 循环这一常见的控制结构提供了非常多的特性，这些 for 循环的特性被称 为 for 推导式或 for 表达式。

### 范围数据循环（To）

1. 基本语法

   ```
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
   
   ```

### 范围数据循环（Until）

1. 基本语法

   ```
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

      

# scala
