package com.sbix.jnotify;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * Interface defining constants and methods related to screen dimensions and notification positioning.
 * This interface provides a centralized way to access screen properties and calculate
 * notification positions.
 */
public interface ScreenDimension {

	/** The overall dimensions (width and height) of the primary screen. */
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/** The height of the primary screen in pixels. */
	public int heightScreen = screenSize.height;
	/** The width of the primary screen in pixels. */
	public int widthScreen = screenSize.width;

	/**
	 * The spacing in pixels between multiple notifications when they are
	 * displayed simultaneously in the same corner of the screen.
	 */
	public int blocSpacing = 4;

	/**
	 * Insets of the screen, representing the space occupied by elements like the taskbar.
	 * This is retrieved using {@link NotifyType#getScreenInsets()}.
	 */
	public Insets screenInsets = NotifyType.getScreenInsets();

	/** Point representing the insets for the top-right corner of the screen (right inset, top inset). */
	public Point topRightInsets = new Point(screenInsets.right,
			screenInsets.top);
	/** Point representing the insets for the top-left corner of the screen (left inset, top inset). */
	public Point topLeftInsets = new Point(screenInsets.left, screenInsets.top);
	/** Point representing the insets for the bottom-right corner of the screen (right inset, bottom inset). */
	public Point bottomRightInsets = new Point(screenInsets.right,
			screenInsets.bottom);
	/** Point representing the insets for the bottom-left corner of the screen (left inset, bottom inset). */
	public Point bottomLeftInsets = new Point(screenInsets.left,
			screenInsets.bottom);

	/**
	 * Calculates the position (x, y coordinates) for a notification window.
	 * Implementations of this method in enums like {@link NPosition} will define
	 * the specific logic for positioning the window (e.g., top-left, center).
	 *
	 * @param window The notification window (JDialog) for which to calculate the position.
	 * @return A Point object representing the calculated (x, y) coordinates for the window.
	 */
	public Point getPosition(JDialog window);

}
