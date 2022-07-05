package exam

import java.util.*


fun main() {
  //TODO: Replace the following line with the appropriate code, see README
  println("there was nothing in the readme...")

  val o_thread_interval = Thread {
    var n_ts_ms = System.currentTimeMillis()
    var n_ms_interval = 1000;
    while(true){
      val n_ts_ms_new = System.currentTimeMillis();
      val n_ts_ms_delta = n_ts_ms_new - n_ts_ms;
      if(n_ts_ms_delta > n_ms_interval){
        n_ts_ms = n_ts_ms_new
      }
      println(n_ts_ms_delta)
    }
    println("${Thread.currentThread()} has run.")
  }
//  o_thread_interval.start()


  val o_thread_interval_sleep = Thread {
    while(true){
      Thread.sleep(1_000)
      println("now")
    }
  }
//  o_thread_interval_sleep.start()



  var n_number = 0;
  val o_thread_interval_sleep_change_external_var = Thread {
    while(true){
      n_number += 10;
      Thread.sleep(1_000)
      println("now")
    }
  }
  o_thread_interval_sleep_change_external_var.start()
  while(true){
    Thread.sleep(1_000)
    println(n_number)
  }
}