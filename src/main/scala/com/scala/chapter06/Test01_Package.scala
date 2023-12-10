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