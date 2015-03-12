package com.gmail.cs475x.anybar4j;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gmail.cs475x.anybar4j.AnyBar4j.AnyBarImage;

public class AnyBar4jTest {

	@Test
	public void shouldWorkWithDefaultHostAndPort() {
		Exception exception = null;

		try {
			AnyBar4j anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, AnyBar4j.DEFAULT_PORT);

			anybar.setImage(AnyBarImage.GREEN);
			anybar.close();
		} catch (Exception e) {
			exception = e;
		}

		assertEquals(null, exception);
	}

	@Test
	public void shouldUseDefaultPortIfSuppliedPortIsLessThanOrEqualToZero() {
		AnyBar4j anybar = null;
		Exception exception = null;

		try {
			anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, -1);

			anybar.close();
		} catch (Exception e) {
			exception = e;
		}

		assertEquals(null, exception);
		assertEquals(AnyBar4j.DEFAULT_PORT, anybar.port);
	}

}
