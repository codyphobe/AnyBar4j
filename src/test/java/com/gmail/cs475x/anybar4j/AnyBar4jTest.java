package com.gmail.cs475x.anybar4j;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gmail.cs475x.anybar4j.AnyBar4j.AnyBarImage;

public class AnyBar4jTest {

	@Test
	public void shouldWorkWithDefaultHostAndPort() {
		AnyBar4j anybar = null;
		Exception exception = null;

		try {
			anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, AnyBar4j.DEFAULT_PORT);

			anybar.setImage(AnyBarImage.GREEN);
		} catch (Exception e) {
			exception = e;
		} finally {
			if (anybar != null) {
				anybar.close();
			}
		}

		assertEquals(null, exception);
	}

	@Test
	public void shouldUseDefaultPortIfSuppliedPortIsLessThanOrEqualToZero() {
		AnyBar4j anybar = null;
		Exception exception = null;

		try {
			anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, -1);
		} catch (Exception e) {
			exception = e;
		} finally {
			if (anybar != null) {
				anybar.close();
			}
		}

		assertEquals(null, exception);
		assertEquals(AnyBar4j.DEFAULT_PORT, anybar.port);
	}

}

