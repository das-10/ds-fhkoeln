package de.thkoeln.pattern.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.thkoeln.pattern.observer.Invoice;
import de.thkoeln.pattern.observer.WebDisplayTest;

@RunWith(Suite.class)
@SuiteClasses({ ShoppindCArdRemoveTest.class, ShoppingCardAddTest.class,
	Invoice.class,WebDisplayTest.class })
public class AllTests {

}
