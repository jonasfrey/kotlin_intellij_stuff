package exam

/** Programming with Kotlin,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.stage.Stage
import tornadofx.*

class MainApp : App(MainView::class) {
  override fun start(stage: Stage) {
    stage.width = 500.0
    stage.height = 300.0
    super.start(stage)
  }
}

class MainView : View("The Title") {
  //TODO: Enhance the TornadoFX App, see README
  override val root =
    label("   If you see this, you have successfully cloned the gitlab project.")
}

fun main(args: Array<String>) {
  launch<MainApp>(args)
}