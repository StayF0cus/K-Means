import scala.math.pow
import scala.math.sqrt

class Donnees(private val point:Array[Double],  private val typ: String){

  override def toString(): String = { // toString de Donnees
    var str = "Coordonées: "
    for (value <- this.point){
      str += value.toString + " | "
    }
    return str + "\nType: " + this.typ
  }

  def distance(d:Donnees): Double= { // calculer la distance
    var resultat :Double = 0
    for(i<-0 until this.point.length){
      resultat += pow(this.point(i)-d.getPoint(i),2)
    }
    return sqrt(resultat)
  }

  def getPoint(i: Int): Double = { // recupere la valeur dans point
    return point(i)
  }

  def getPoint(): Array[Double] = {
    return this.point
  }

  def getLongueur(): Int = {
    return this.point.length
  }

  def getTyp(): String = {
    return this.typ
  }
}
