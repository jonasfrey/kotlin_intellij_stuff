package exam

/** Programming with Kotlin,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.Stage
import tornadofx.*
import kotlin.math.pow
import kotlin.random.Random


class MainApp : App(
  MainView::class,
  O_stylesheet::class
) {

  init {
    reloadStylesheetsOnFocus()
  }
  override fun start(stage: Stage) {
    stage.width = 500.0
    stage.height = 300.0
    super.start(stage)
  }
}

class MainView : View("The Title") {

  var b_clock_running = SimpleBooleanProperty()
  var b_toggle = SimpleBooleanProperty();
  var s_input_string = SimpleStringProperty("test");



  val a_s_button_text = arrayOf(
    SimpleStringProperty("hit me"),
    SimpleStringProperty("click me"),
    SimpleStringProperty("flick me"),
    SimpleStringProperty("touch me"),
    SimpleStringProperty("push me"),
    SimpleStringProperty("smash me"),
    SimpleStringProperty("smack me"),
    SimpleStringProperty("poke me")
  )
  var s_button_random_color = SimpleStringProperty("click random color: default");

  val o_clock_controller: O_clock_controller by inject()

  //TODO: Enhance the TornadoFX App, see README
  override val root =
    vbox {
      button("hit me"){
        action(){
          val s_random = a_s_button_text[Random.nextInt(0, a_s_button_text.size )]
          println(s_random)
//          s_input_string = SimpleStringProperty(s_random) // this wont worko
//          s_input_string = s_random // even this wont work if s_random is simplestringprop
            s_input_string.set(s_random.get())
         }
        style{
          fontSize = 20.px
        }
      }
      button(s_input_string)
      textfield(s_input_string)
      button("test"){
        addClass(O_stylesheet.o_button)
      }
      button("click me to toggle color"){
//        b_toggle = !b_toggle; // so schlecht i schwoere

        action(){
          b_toggle.set(!b_toggle.get())
          if(b_toggle.get()){
            removeClass(O_stylesheet.o_button_true)
            addClass(O_stylesheet.o_button_false)
          }else{
            removeClass(O_stylesheet.o_button_false)
            addClass(O_stylesheet.o_button_true)
          }
        }
      }

      button(s_button_random_color){
        action(){
          val n_random_r = Random.nextInt(0, (2.0.pow(8)).toInt())
          val n_random_g = Random.nextInt(0, (2.0.pow(8)).toInt())
          val n_random_b = Random.nextInt(0, (2.0.pow(8)).toInt())

          val s_random_hex_color = "#${n_random_r.toString(16).padStart(2, '0')}${n_random_g.toString(16).padStart(2, '0')}${n_random_b.toString(16).padStart(2, '0')};"
          s_button_random_color.set("click random color: ${s_random_hex_color}")
          this.setStyle("-fx-background-color: ${s_random_hex_color}");
        }
      }

      button(o_clock_controller.s_clock_string){
        action(){
          b_clock_running.set(!b_clock_running.get())
          if(!b_clock_running.get() ){
            o_clock_controller.f_start_async()
//            o_thread_clock_counter.stop()
          }else{
//            o_thread_clock_counter.start()
          }
        }
      }
    }

}

fun main(args: Array<String>) {
  launch<MainApp>(args)
}

class O_stylesheet: Stylesheet(){
  companion object{
    val a_o_button = ArrayList<CssRule>();
    val n_max = 10;

    val o_button by cssclass()
    val o_button_false by cssclass()
    val o_button_true by cssclass()
    val o_hover_color = c("#ff00ff");
    val o_default_color = c("#aa00aa");

  }

  init{

      o_button{
        backgroundColor+= o_default_color
        and(hover){
          backgroundColor+= o_hover_color
        }
      }
      o_button_true{
        backgroundColor+= c("#0efa00");
      }
      o_button_false{
        backgroundColor+=c("#af0fa0");
      }
  }
}

class O_clock_controller: Controller(){
  val n_interval_ms = 1000;
  var s_clock_string = SimpleStringProperty("click clock:")
  fun f_start_async(){
    var n_clock_ms = 0;

    runAsync{
      while(true){
        println("n_interval_ms: ${n_interval_ms}")
        //      s_clock_string.set("click clock: ${n_clock_ms}")
        n_clock_ms += n_interval_ms;
        Thread.sleep(n_interval_ms.toLong())
        println(n_clock_ms)
        s_clock_string.set(n_clock_ms.toString())
      }
    }
  }
  fun f_start_thread(){
    var n_clock_ms = 0;
    val n_interval_ms = n_interval_ms
    val o_thread_clock_counter = Thread {
      while(true){
        println("n_interval_ms: ${n_interval_ms}")
//      s_clock_string.set("click clock: ${n_clock_ms}")
        n_clock_ms += n_interval_ms;
        Thread.sleep(n_interval_ms.toLong())
        println(n_clock_ms)
        s_clock_string.set(n_clock_ms.toString())
      }
    }
    o_thread_clock_counter.start()
  }
}