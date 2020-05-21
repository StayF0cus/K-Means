class Cluster(private var centroide: Donnees){
  private var idLigneMatrice: Array[Int] = Array()

  def getId(i: Int): Int = {
    return this.idLigneMatrice(i)
  }

  def getId(): Array[Int] = {
    return this.idLigneMatrice
  }

  def getCentroide(): Donnees={
    return this.centroide
  }

  def setCentroide(centroide: Donnees): Unit = {
    this.centroide = centroide
  }

  def addIndice(i: Int): Unit = {
    this.idLigneMatrice = this.idLigneMatrice :+ i
  }

  def resetIndiceDonnees(): Unit = {
    this.idLigneMatrice = Array()
  }
}
