package com.whv.common.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.whv.common.utils.string.StringPad;

public class StringTest {

	@Test
	public void testLpad() {
		System.out.println(StringPad.lpad("12", "≤‚ ‘", 1, false));
		System.out.println(StringPad.lpad("12", "≤‚ ‘", 1, true));
		System.out.println(StringPad.lpad("12", '\0', 5));
	}
	@Test
	public void testRpad() {
		System.out.println(StringPad.rpad("12", "≤‚ ‘", 6, false));
		System.out.println(StringPad.rpad("12", "≤‚ ‘", 1, true));
	}
	@Test
	public void testBothpad() {
		System.out.println(StringPad.bothpad("12", '*', 100));
		System.out.println(StringPad.bothpad("12", "≤‚ ‘", 1));
		System.out.println(StringPad.bothpad("12", "≤‚ ‘", 1, true));
	}
}
