# AnyBar4j

A Java application/API to control [AnyBar](https://github.com/tonsky/AnyBar)
instances.

## From the Command Line

### Options

| Flag | Type | Description | Default |
| ---- | ---- | ----------- | ------- |
| `-?`, `--help` || Show the available command line flags with descriptions. ||
| `-c`, `-i`, `--color`, `--image` | String | The name of the image to display in the AnyBar instance. | `"white"` |
| `-h`, `--host`, `--ip` | String | The hostname or IP address of the machine running the AnyBar instance. | `"localhost"` |
| `-p`, `--port` | Integer | The port number that the AnyBar instance is running on. | `1738` |

### Examples

Change AnyBar to `green`:

```bash
java -jar AnyBar4j.jar -c green
```

Change AnyBar to `exclamation` on port 1800 of another local machine:

```bash
java -jar AnyBar4j.jar -c exclamation -h 10.0.0.42 -p 1800
```

## From the API

1. Create an instance of AnyBar4j, passing in the hostname (string) and port (int) of the AnyBar instance.
2. Call `setImage()` on the AnyBar4j instance, using one of the `AnyBarImage` enum values or a custom name, to change the displayed image/color.
3. Call `close()` to close the client socket.

### Default values

| Name | Value |
| ---- | ----- |
| `DEFAULT_HOST` | `"localhost"` |
| `DEFAULT_PORT` | `1738` |

### `AnyBarImage` Enum Values (AnyBar Defaults)

| Name |
| ---- |
| WHITE |
| RED |
| ORANGE |
| YELLOW |
| GREEN |
| CYAN |
| BLUE |
| PURPLE |
| BLACK |
| QUESTION |
| EXCLAMATION |

### Example

Change AnyBar to `purple`:

```java
AnyBar4j anybar = null;

try {
	anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, AnyBar4j.DEFAULT_PORT);

	anybar.setImage(AnyBarImage.PURPLE);
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (anybar != null) {
		anybar.close();
	}
}
```

In the above instance, you may substitute the AnyBar4j constructor with an empty constructor to connect to the default host and port:

```java
anybar = new AnyBar4j(AnyBar4j.DEFAULT_HOST, AnyBar4j.DEFAULT_PORT);
// Same as
anybar = new AnyBar4j();
```

