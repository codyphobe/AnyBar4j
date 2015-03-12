package com.gmail.cs475x.anybar4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class AnyBar4j {

	/**
	 * The default host that AnyBar4j connects to.
	 */
	public static final String DEFAULT_HOST = "localhost";

	/**
	 * The default port that AnyBar is bound to.
	 */
	public static final int DEFAULT_PORT = 1738;

	/**
	 * The hostname or IP address of the target machine.
	 */
	public final InetAddress host;

	/**
	 * The port to connect to on the target machine.
	 */
	public final int port;

	/**
	 * The client socket.
	 */
	private DatagramSocket socket;

	public static void main(String[] args) throws IOException {
		OptionParser parser = new OptionParser();

		OptionSpec<Void> optionHelp = parser.acceptsAll(Arrays.asList("?", "help"), "Show the available command line flags with descriptions.");
		OptionSpec<String> optionImage = parser.acceptsAll(Arrays.asList("c", "color", "i", "image"), "The name of the image to display in the AnyBar instance.").withRequiredArg().ofType(String.class).defaultsTo(AnyBarImage.WHITE.toString());
		OptionSpec<String> optionHost = parser.acceptsAll(Arrays.asList("h", "host", "ip"), "The hostname or IP address of the machine running the AnyBar instance.").withRequiredArg().ofType(String.class).defaultsTo(DEFAULT_HOST);
		OptionSpec<Integer> optionPort = parser.acceptsAll(Arrays.asList("p", "port"), "The port number that the AnyBar instance is running on.").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

		OptionSet options = parser.parse(args);

		if (options.has(optionHelp)) {
			parser.printHelpOn(System.out);

			return;
		}

		AnyBar4j anybar = null;
		String image = options.valueOf(optionImage);
		String host = options.valueOf(optionHost);
		Integer port = options.valueOf(optionPort);

		try {
			anybar = new AnyBar4j(host, port);

			anybar.setImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (anybar != null) {
				anybar.close();
			}
		}
	}

	/**
	 * Constructs an AnyBar4j instance that will connect to the default host and port.
	 *
	 * @throws SocketException if a new socket could not be created.
	 * @throws UnknownHostException if the supplied hostname is invalid.
	 */
	public AnyBar4j()
	throws SocketException, UnknownHostException {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}

	/**
	 * Constructs an AnyBar4j instance that will connect to the specified host and port.
	 *
	 * @param host The hostname or IP that AnyBar is running on.
	 * @param port The port that AnyBar is running on.
	 * @throws SocketException if a new socket could not be created.
	 * @throws UnknownHostException if the supplied hostname is invalid.
	 */
	public AnyBar4j(String host, int port)
	throws SocketException, UnknownHostException {
		this.host = InetAddress.getByName(host);
		this.port = port > 0 ? port : 1738;
		this.socket = new DatagramSocket();
	}

	/**
	 * Sets the displayed image on the AnyBar instance to one of the defaults.
	 *
	 * @param image The image to display.
	 * @throws IOException
	 */
	public void setImage(AnyBarImage image)
	throws IOException {
		this.setImage(image.toString());
	}

	/**
	 * Sets the displayed image on the AnyBar instance.
	 *
	 * @param imageName The name of the image to display.
	 * @throws IOException if the packet could not be sent.
	 */
	public void setImage(String imageName)
	throws IOException {
		if (this.socket != null) {
			byte[] data = imageName.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, this.host, this.port);

			this.socket.send(packet);
		}
	}

	/**
	 * Closes the client socket.
	 */
	public void close() {
		if (this.socket != null) {
			this.socket.close();
		}
	}

	/**
	 * The default images/colors in AnyBar.
	 */
	public enum AnyBarImage {
		WHITE,
		RED,
		ORANGE,
		YELLOW,
		GREEN,
		CYAN,
		BLUE,
		PURPLE,
		BLACK,
		QUESTION,
		EXCLAMATION;

		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	}

}
