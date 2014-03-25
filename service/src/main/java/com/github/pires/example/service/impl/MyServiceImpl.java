package com.github.pires.example.service.impl;

import com.github.pires.example.aspect.Trace;
import com.github.pires.example.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link MyService}.
 */
public class MyServiceImpl implements MyService {

  private static final Logger log = LoggerFactory.getLogger(MyService.class);


  //@Trace
  public void doSomething() {
    log.info("METHOD EXEC: public void doSomething()");
    test1();
    //test2();
  }

  @Trace
  private void test1()
  {
    log.info("METHOD EXEC: private void test1()");
  }

//  @Trace
//  public void test2()
//  {
//    log.info("METHOD EXEC: public void test2()");
//  }
}
