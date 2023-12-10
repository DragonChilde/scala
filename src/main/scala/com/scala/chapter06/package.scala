package object chapter06 {
  // 定义当前包共享的属性和方法
  val commonValue = "18"

  def commonMehtod() = {
    println(s"I am ${commonValue} years old")
  }
}