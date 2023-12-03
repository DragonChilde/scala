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

    // 在调用时，addByA 函数执行完毕后，局部变量 a 应该随着栈空间释放掉
    val addByFour2 = addByA(11)
    val addByFive2 = addByA(22)

    // 但是在此处，变量 a 其实并没有释放，而是包含在了 f2 函数的内部，形成了闭合的效果
    println(addByFour2(22)) //33
    println(addByFive2(33)) //55

    println("============================")

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
