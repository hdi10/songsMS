import cats.effect._
import dev.profunktor.redis4cats._
import dev.profunktor.redis4cats.effect.Log.Stdout._
import scala.collection.mutable
import io.circe.parser._
import io.circe.syntax._

object Main extends IOApp {

  val happyWords = Set("happy", "joy", "love", "wonderful", "great", "amazing")

  def analyzeLyrics(lyrics: String): Map[String, Int] = {
    val words = lyrics.split("\\s+").map(_.toLowerCase)
    val wordCount = mutable.Map[String, Int]()

    for (word <- words) {
      wordCount(word) = wordCount.getOrElse(word, 0) + 1
    }

    wordCount.toMap
  }

  def isHappySong(lyrics: String): Boolean = {
    val wordCount = analyzeLyrics(lyrics)
    val happyWordCount = happyWords.map(word => wordCount.getOrElse(word, 0)).sum
    happyWordCount > 3
  }

  def run(args: List[String]): IO[ExitCode] = {
    val redisURI = "redis://localhost:6380" // Ändern Sie dies entsprechend Ihrer Konfiguration

    Redis[IO].utf8(redisURI).use { cmd =>
      val testLyrics = "After balling I go clubbing\nThen I’m hugging\nThen I’m hungry and I’m walking on the street\nand I’m getting getting getting getting grumpy grumpy\n\nI see Chow, by my right\nI smell food in the air\nIt’s Chinese Food, my favorite\nSo I’m getting getting getting getting hungry\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein\n\nRead the menu\nThey got broccoli\nEven chicken wings\nMake it spicy\nAnd you like it\nCause it’s beautiful\nAnd it tastes so so so good\n\nI like the egg rolls\nAnd the wonton soup\nThis makes me feel so so good\nFortune cookies, tell my future\nChinese Chinese Food\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein\n\n[Patrice Wilson:]\nYo!\nI like Chinese food\nAnd some Wonton soup\nGet me broccoli\nWhile I play Monopoly\nDon’t be a busy bee\nCause it’s your fantasy\nTo eat Chinese food\nEgg roll and Chop Suey\nI use the chopsticks\nTo eat pot sticks\nPut some hot sauce and sweet and sour make it sweet\nBecause Chinese food takes away my stress\nNow I’m going to go eat Panda Express\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein\n\nI love Chinese food (Yeah)\nyou know that it’s true (Yeah)\nI love fried rice (Yeah)\nI love noodles (Yeah)\nI love Chow mein\nChow Mo-Mo-Mo-Mo Mein " // (Ihr Liedtext hier)

      // Überprüfen Sie, ob die Analyse bereits in Redis gespeichert ist
      for {
        cached <- cmd.get("lyrics_analysis")
        _ <- cached match {
          case Some(value) =>
            IO(println(s"Found cached analysis: $value"))
          case None =>
            // Führen Sie die Analyse durch und speichern Sie sie in Redis
            val analysis = analyzeLyrics(testLyrics).asJson.noSpaces
            for {
              _ <- cmd.set("lyrics_analysis", analysis)
              _ <- IO(println(s"Analysis result saved to Redis: $analysis"))
            } yield ()
        }
        isHappy = isHappySong(testLyrics)
        _ <- IO(println(s"Is this a happy song? $isHappy"))
      } yield ()
    }.as(ExitCode.Success)
  }
}
