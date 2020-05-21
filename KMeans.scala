import scala.io.Source
import scala.math.pow
import scala.math.sqrt
import scala.util.Random

class KMeans(){
    private var matriceDonnees: MatriceDonnees = null

    def initialisationMatriceDonnees(file: String): Unit= {
        var m : Array[Donnees] = Array()
        for (line <- Source.fromFile(file).getLines) {  // Pour lire un fichier
            val arr = line.split(",")
            m = m :+ new Donnees(arr.dropRight(1).map(_.toDouble), arr(arr.length-1)) // matrice tableau generale avec toute les données dedans, :+ c'est comme un append new donnees, arr.dropRight(1) eneleve le dernier element a droite du tableau, map appliqu ela commande paranthese a tous les element du tableau, toFloat transforme tout en float, arr tableau on veut mettre le nom de la classe dans le dernier element du tableau tab[n]
        }
        return this.matriceDonnees = new MatriceDonnees(m)
    }

    def calculerKMeans(k: Int): Array[Int] = {
      var tabClusters: Array[Cluster] = Array()
      tabClusters = this.tableauDeClusters(k, tabClusters)
      this.conditionSortie(k, tabClusters)
      var res = resultat(tabClusters)
      println("[" + res.mkString(", ") + "]")
      return res
  }

    def tableauDeClusters(k: Int, tabClusters: Array[Cluster]): Array[Cluster] = {
      var tabCluster = tabClusters
      var r = new scala.util.Random // creer un random
      for (i <- 0 until k){  // de i a k
        var centroide: Array[Double] = Array() // creer un tableau de centroide
        for (j <- 0 until this.matriceDonnees.getMatrice(0).getLongueur()){ // on parcour la matrice
          centroide = centroide :+ this.matriceDonnees.getMatrice(r.nextInt(this.matriceDonnees.getLongueur())).getPoint(j) // on met nos centroide dans le tableau
          println("[" + centroide.mkString(", ") + "]") // affciher les centroide choisit
        }
          tabCluster = tabCluster :+ new Cluster(new Donnees(centroide, "")) // creer un object cluster a chaque iteration de k et l'ajoute au tableau de cluster
      }
      return tabCluster
    }

    def conditionSortie(k: Int, tabClusters: Array[Cluster]): Unit = {
      var d = 0
      while (d<300) {
          for (cluster <- tabClusters){
            cluster.resetIndiceDonnees
         }
          for (i <- 0 until this.matriceDonnees.getLongueur()) {
              var distances: Array[Double] = Array()
              for (j <- 0 until k) {
                  distances = distances :+ this.matriceDonnees.getMatrice(i).distance(tabClusters(j).getCentroide)
              }
              tabClusters(min(distances)).addIndice(i)
          }
          //update centroides
          this.updateCentroide(k, tabClusters)
          d = d + 1
      }
    }

    def updateCentroide(k: Int, tabClusters: Array[Cluster]): Unit = {
      for (i <- 0 until k) {
          var centroide: Array[Double] = Array()
          var clusterData = tabClusters(i).getId
          if (clusterData.length > 0) {
              for (j <- 0 until this.matriceDonnees.getMatrice(0).getLongueur()) {
                  var moyenne: Double = 0
                  for (indice <- clusterData){
                    moyenne = moyenne + this.matriceDonnees.getMatrice(indice).getPoint(j)
                  }
                  centroide = centroide :+ moyenne/clusterData.length
              }
              tabClusters(i).setCentroide(new Donnees(centroide, ""))
          }
      }
    }

    def resultat(tabClusters: Array[Cluster]): Array[Int] = {
      var res: Int = 0
      var resultat: Array[Int] = Array()
      for (i <- 0 until this.matriceDonnees.getLongueur()) {
          for(j <- 0 to tabClusters.length-1){
            if(tabClusters(j).getId.contains(i)){
              res = j
            }
          }
          resultat :+= res
      }
      return resultat
    }

    def affichage(resultat: Array[Int]): Unit = {
      for (i <- 0 until this.matriceDonnees.getLongueur()){
        println("Ligne " + i + " est dans le cluster " + resultat(i))
      }
    }

    def min(tab: Array[Double]): Int = {
        var mi = tab(0)
        var m = 0
        for (i <- 1 until tab.length){
            if (tab(i) < mi){
              m = i
              mi = tab(i)
            }
        }
        m
    }

    def getMatriceDonnees(i: Int): Donnees = {  // recuperer une ligne de la matrice
        return this.matriceDonnees.getMatrice(i)
    }

    def moyenne(i: Int): Float = {
      var longueurMatriceDonnees = this.matriceDonnees.getLongueur
      var moy :Float = 0
      for (x <- 0 until longueurMatriceDonnees){
        moy += (this.matriceDonnees.getMatrice(x).getPoint(i)).toFloat
      }
      return moy/longueurMatriceDonnees
    }

    def variance(i: Int): Float = {
      var longueurMatriceDonnees = this.matriceDonnees.getLongueur
      var varian :Float = 0
      for (x <- 0 until longueurMatriceDonnees){
        varian += pow(this.matriceDonnees.getMatrice(x).getPoint(i)-moyenne(i),2).toFloat
      }
      return varian/longueurMatriceDonnees
    }

    def ecartType(i: Int): Float = {
       return sqrt(variance(i)).toFloat
    }

    def commentaireResultat(i: Int): Unit = {
      println("Resultats :\n")
      val ligne = this.matriceDonnees.getMatrice(0).getLongueur()
      for (x <- 0 until ligne){
        println("Colonne numero "+(x+1).toString()+" :")
        println("Moyenne est de = " + moyenne(x))
        println("Variance est de = " + variance(x))
        println("EcartType est de = " + ecartType(x) + "\n")
      }
    }

    def covariance(colonne1: Int, colonne2: Int): Double ={
      val moy1 = moyenne(colonne1)
      val moy2 = moyenne(colonne2)
      val ligne = this.matriceDonnees.getLongueur()
      var resultat: Double = 0.0
      for(i <- 0 until ligne){
        resultat = resultat + (this.getMatriceDonnees(i).getPoint(colonne1) - moy1) * (this.getMatriceDonnees(i).getPoint(colonne2) - moy2)
      }
      resultat = (resultat / ligne).toFloat
      resultat = resultat / (ecartType(colonne1)*ecartType(colonne2))
      return resultat
    }
}
