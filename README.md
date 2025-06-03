# jnotify

jnotify is an API for creating notifications in Java applications, designed to provide informative messages.

## Table of Contents
- [Usage](#usage)
- [Components](#components)
- [API Documentation](#api-documentation)
  - [NPosition](#nposition)
  - [NotifyType](#notifytype)
  - [ScreenDimension](#screendimension)
  - [NotifyWindow](#notifywindow)
- [Demo](#demo)
- [Author](#author)

## Usage

**Step 1:**
To use jnotify, include the library from [https://sourceforge.net/projects/jnotify-api/](https://sourceforge.net/projects/jnotify-api/) in your project.

**Step 2:**
You can use any constructor according to your needs. For example:

```java
new NotifyWindow(NotifyType.DEFAULT_NOTIFICATION, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", NotifyWindow.NORMAL_DELAY, NPosition.CENTER);
```

**Constructor Parameters:**

*   `NotifyType`: Specifies the type of notification. Available types include:
    *   `NotifyType.DEFAULT_NOTIFICATION`
    *   `NotifyType.SUCCESS_NOTIFICATION`
    *   `NotifyType.ERROR_NOTIFICATION`
    *   `NotifyType.WARNING_NOTIFICATION`
*   `String message`: The message to be displayed in the notification.
*   `int delay`: The duration for which the notification will be displayed. Available options:
    *   `NotifyWindow.NORMAL_DELAY`
    *   `NotifyWindow.SHORT_DELAY`
    *   `NotifyWindow.LONG_DELAY`
*   `NPosition`: The position on the screen where the notification will appear. Available positions:
    *   `NPosition.CENTER`
    *   `NPosition.TOP_LEFT`
    *   `NPosition.TOP_RIGHT`
    *   `NPosition.BOTTOM_LEFT`
    *   `NPosition.BOTTOM_RIGHT`

## Components

*   **`NotifyWindow`:** The main class for creating and displaying notifications.
*   **`NotifyType`:** A class defining the visual style and content of a notification.
*   **`NPosition`:** An enum defining the possible positions for the notification window on the screen.
*   **`ScreenDimension`:** An interface defining constants and methods related to screen dimensions.

## API Documentation

### NPosition
`src/com/sbix/jnotify/NPosition.java`

Defines the possible positions for the notification window on the screen. It also provides methods to calculate the exact coordinates for each position.

**Enum Constants:**

*   `TOP_LEFT`: Represents the top-left corner of the screen.
    *   `getPosition(JDialog window)`: Calculates the position for the notification window at the top-left corner.
*   `TOP_RIGHT`: Represents the top-right corner of the screen.
    *   `getPosition(JDialog window)`: Calculates the position for the notification window at the top-right corner.
*   `BOTTOM_LEFT`: Represents the bottom-left corner of the screen.
    *   `getPosition(JDialog window)`: Calculates the position for the notification window at the bottom-left corner.
*   `BOTTOM_RIGHT`: Represents the bottom-right corner of the screen.
    *   `getPosition(JDialog window)`: Calculates the position for the notification window at the bottom-right corner.
*   `CENTER`: Represents the center of the screen.
    *   `getPosition(JDialog window)`: Calculates the position for the notification window at the center of the screen.

### NotifyType
`src/com/sbix/jnotify/NotifyType.java`

Represents the visual style and content of a notification. This class is responsible for displaying the notification message with a specific background color based on the notification type.

**Static Color Constants:**

*   `DEFAULT_NOTIFICATION`: `new Color(0x7f8c8d)`
    *   Represents a default notification type with a neutral color.
*   `SUCCESS_NOTIFICATION`: `new Color(0x2ecc71)`
    *   Represents a success notification type, typically green.
*   `ERROR_NOTIFICATION`: `new Color(0xc0392b)`
    *   Represents an error notification type, typically red.
*   `WARNING_NOTIFICATION`: `new Color(0xf39c12)`
    *   Represents a warning notification type, typically orange or yellow.

**Constructor:**

*   `NotifyType(Color notficationType, String message)`
    *   Constructs a new `NotifyType` panel.
    *   Parameters:
        *   `notficationType`: The background `Color` for the notification, typically one of the predefined static `Color` constants.
        *   `message`: The message text to be displayed in the notification. HTML formatting can be used.

**Public Methods:**

*   `static Insets getScreenInsets()`
    *   Calculates the insets of the screen, which can be used to determine the usable area of the screen, excluding elements like the taskbar.
    *   Returns: An `Insets` object representing the top, left, bottom, and right insets of the screen.

### ScreenDimension
`src/com/sbix/jnotify/ScreenDimension.java`

Interface defining constants and methods related to screen dimensions and notification positioning. This interface provides a centralized way to access screen properties and calculate notification positions.

**Constants:**

*   `Dimension screenSize`: The overall dimensions (width and height) of the primary screen.
*   `int heightScreen`: The height of the primary screen in pixels.
*   `int widthScreen`: The width of the primary screen in pixels.
*   `int blocSpacing`: The spacing in pixels (default: 4) between multiple notifications when they are displayed simultaneously in the same corner of the screen.
*   `Insets screenInsets`: Insets of the screen, representing the space occupied by elements like the taskbar. Retrieved using `NotifyType.getScreenInsets()`.
*   `Point topRightInsets`: `Point` representing the insets for the top-right corner (right inset, top inset).
*   `Point topLeftInsets`: `Point` representing the insets for the top-left corner (left inset, top inset).
*   `Point bottomRightInsets`: `Point` representing the insets for the bottom-right corner (right inset, bottom inset).
*   `Point bottomLeftInsets`: `Point` representing the insets for the bottom-left corner (left inset, bottom inset).

**Methods:**

*   `Point getPosition(JDialog window)`
    *   Calculates the position (x, y coordinates) for a notification window. Implementations of this method (e.g., in `NPosition`) define the specific logic for positioning.
    *   Parameters:
        *   `window`: The notification window (`JDialog`) for which to calculate the position.
    *   Returns: A `Point` object representing the calculated (x, y) coordinates.

### NotifyWindow
`src/com/sbix/jnotify/NotifyWindow.java`

Represents a notification window that can be displayed on the screen. This window shows a message with a specific style (type) and can be positioned at various locations on the screen. It automatically disappears after a configurable delay. The window also responds to mouse events to pause the disappearance timer when hovered.

**Constructors:**

*   `NotifyWindow(Color notification, String message, int delay, NPosition position)`
    *   Constructs a new notification window with specified type, message, delay, and position.
    *   Parameters:
        *   `notification`: The `Color` representing the type of notification (e.g., `NotifyType.SUCCESS_NOTIFICATION`).
        *   `message`: The message to be displayed.
        *   `delay`: The duration in milliseconds for visibility (e.g., `NotifyWindow.NORMAL_DELAY`).
        *   `position`: The `NPosition` on the screen.
*   `NotifyWindow(Color notification, String message, int delay)`
    *   Constructs a new notification window with specified type, message, and delay, using the default position (`NPosition.CENTER`).
*   `NotifyWindow(Color notification, String message)`
    *   Constructs a new notification window with specified type and message, using default delay (`NotifyWindow.NORMAL_DELAY`) and position (`NPosition.CENTER`).
*   `NotifyWindow(String message)`
    *   Constructs a new notification window with a specified message, using default type (`NotifyType.DEFAULT_NOTIFICATION`), delay (`NotifyWindow.NORMAL_DELAY`), and position (`NPosition.CENTER`).
*   `NotifyWindow(String message, NPosition position)`
    *   Constructs a new notification window with a specified message and position, using default type (`NotifyType.DEFAULT_NOTIFICATION`) and delay (`NotifyWindow.NORMAL_DELAY`).

**Public Static Final Delay Constants:**

*   `int SHORT_DELAY = 1500`: Short delay for notification visibility (1500 milliseconds).
*   `int NORMAL_DELAY = 2500`: Normal delay for notification visibility (2500 milliseconds).
*   `int LONG_DELAY = 4000`: Long delay for notification visibility (4000 milliseconds).

**Public Methods:**

*   `NPosition getPosition()`
    *   Gets the current screen position of the notification window.
    *   Returns: The `NPosition` enum constant representing the current position.
*   `void setPosition(NPosition position)`
    *   Sets the screen position for the notification window. (Note: Best used before window is visible).
    *   Parameters: `position` - The `NPosition` enum constant.
*   `void clear(NPosition position)`
    *   Adjusts static inset values when a notification at a specific corner is closed, to allow correct stacking of subsequent notifications.
    *   Parameters: `position` - The `NPosition` of the closed window.
*   `void actionPerformed(ActionEvent e)`
    *   Handles timer events for showing and fading out the notification.
    *   Parameters: `e` - The `ActionEvent`.
*   `void mouseClicked(MouseEvent e)`
    *   Invoked when the mouse is clicked on the notification. (No specific action implemented).
    *   Parameters: `e` - The `MouseEvent`.
*   `void mouseEntered(MouseEvent e)`
    *   Invoked when the mouse enters the notification area; pauses the fade-out timer.
    *   Parameters: `e` - The `MouseEvent`.
*   `void mouseExited(MouseEvent e)`
    *   Invoked when the mouse exits the notification area. (No specific action implemented).
    *   Parameters: `e` - The `MouseEvent`.
*   `void mousePressed(MouseEvent e)`
    *   Invoked when a mouse button is pressed on the notification. (No specific action implemented).
    *   Parameters: `e` - The `MouseEvent`.
*   `void mouseReleased(MouseEvent e)`
    *   Invoked when a mouse button is released on the notification. (No specific action implemented).
    *   Parameters: `e` - The `MouseEvent`.
*   `Point getPosition(JDialog window)`
    *   Gets the current location of this notification window (implements `ScreenDimension`).
    *   Parameters: `window` - The `JDialog` window (expected to be this instance).
    *   Returns: A `Point` object with current (x,y) coordinates.

## Demo

<img width="100%"  src="http://zupimages.net/up/13/49/kn2z.png" alt="Demo jNotify" />

## Author

Mohsine AMECHTAK
