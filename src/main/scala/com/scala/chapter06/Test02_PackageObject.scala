package chapter06 {

  object Test02_PackageObject {
    def main(args: Array[String]): Unit = {
      commonMehtod()  //I am 18 years old
      println(commonValue)  //18
    }
  }
}

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