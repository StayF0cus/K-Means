object Test{
  def main(args: Array[String]): Unit = {
    val a :Donnees = new Donnees(Array(1,1,1,1), "iris bleu")  // test calcule distance
    var b :Donnees = new Donnees(Array(2,2,2,2), "iris jaune")
    var e :Donnees = new Donnees(Array(4,4,4,4), "iris setosa")
    var d :Donnees = new Donnees(Array(5,5,5,5), "iris-setosa")

    var c :MatriceDonnee = new MatriceDonnee(Array(a,b))
    println(c)

    var k :KMeans = new KMeans()   // test afficher une ligne
    k.initialisationMatriceDonnees("iris.data")
    println("Ligne 1 " + k.getMatriceDonnees(0)) // affiche ligne 1
    println("")
    println("Ligne 2 " + k.getMatriceDonnees(1)) // affiche ligne 2
    println("")
    println("Premiere valeur de la premiere ligne: " + k.getMatriceDonnees(0).getPoint(0)) // affiche la premiere valeur premiere ligne

    val x1 = k.getMatriceDonnees(0) // recuper la premiere ligne
    val x2 = k.getMatriceDonnees(1) // recuperer la deuxieme ligne
    println("")
    println("Distance entre la premiere ligne et la deuxieme ligne: " + x1.distance(x2)) // calcule la distance entre x1 et x2
    println("")
    println("La covariance entre colonne 1 et colonne 2 est : " + k.covariance(0,1)) //calcule de l'ecart type de la premiere colonne
    println("La covariance entre colonne 1 et colonne 3 est : " + k.covariance(0,2))
    println("La covariance entre colonne 1 et colonne 4 est : " + k.covariance(0,3))
    println("La covariance entre colonne 2 et colonne 3 est : " + k.covariance(1,2))
    println("La covariance entre colonne 2 et colonne 4 est : " + k.covariance(1,3))
    println("La covariance entre colonne 3 et colonne 4 est : " + k.covariance(2,3))
    println("")
    k.commentaireResultat(0)
    var oui = k.calculerKMeans(3)
    k.affichage(oui)


  }
}
