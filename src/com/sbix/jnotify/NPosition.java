package com.sbix.jnotify;

import java.awt.Point;

import javax.swing.JDialog;

/**
 * Defines the possible positions for the notification window on the screen.
 * It also provides methods to calculate the exact coordinates for each position.
 */
public enum NPosition implements ScreenDimension {
	/**
	 * Represents the top-left corner of the screen.
	 */
	TOP_LEFT {
		/**
		 * Calculates the position for the notification window at the top-left corner.
		 *
		 * @param window The notification window (JDialog) for which to calculate the position.
		 * @return A Point object representing the (x, y) coordinates for the window.
		 */
		public Point getPosition(JDialog window) {
			final int x = topLeftInsets.x;
			final int y = topLeftInsets.y;
			topLeftInsets.y += window.getHeight() + blocSpacing;  
			return new Point(x, y);
		}
	},
	/**
	 * Represents the top-right corner of the screen.
	 */
	TOP_RIGHT {
		/**
		 * Calculates the position for the notification window at the top-right corner.
		 *
		 * @param window The notification window (JDialog) for which to calculate the position.
		 * @return A Point object representing the (x, y) coordinates for the window.
		 */
		public Point getPosition(JDialog window) {
			final int x = widthScreen - topRightInsets.x - window.getWidth();
			final int y = topRightInsets.y;
			topRightInsets.y += window.getHeight() + blocSpacing;
			return new Point(x, y);
		}
	},
	/**
	 * Represents the bottom-left corner of the screen.
	 */
	BOTTOM_LEFT{
		/**
		 * Calculates the position for the notification window at the bottom-left corner.
		 *
		 * @param window The notification window (JDialog) for which to calculate the position.
		 * @return A Point object representing the (x, y) coordinates for the window.
		 */
		public Point getPosition(JDialog window) {
			final int x = bottomLeftInsets.x;
			final int y = heightScreen - bottomLeftInsets.y - window.getHeight();
			bottomLeftInsets.y +=  window.getHeight() + blocSpacing;
			return new Point(x, y);
		}
	},
	/**
	 * Represents the bottom-right corner of the screen.
	 */
	BOTTOM_RIGHT{
		/**
		 * Calculates the position for the notification window at the bottom-right corner.
		 *
		 * @param window The notification window (JDialog) for which to calculate the position.
		 * @return A Point object representing the (x, y) coordinates for the window.
		 */
		public Point getPosition(JDialog window) {
			final int x = widthScreen - window.getWidth()-bottomRightInsets.x;
			final int y = heightScreen - bottomRightInsets.y - window.getHeight();
			bottomRightInsets.y +=  window.getHeight() + blocSpacing;
			return new Point(x, y);
		}
	},
	/**
	 * Represents the center of the screen.
	 */
	CENTER{
		/**
		 * Calculates the position for the notification window at the center of the screen.
		 *
		 * @param window The notification window (JDialog) for which to calculate the position.
		 * @return A Point object representing the (x, y) coordinates for the window.
		 */
		public Point getPosition(JDialog window) {
			final int x = widthScreen/2 - window.getWidth()/2;
			final int y = heightScreen/2 - window.getHeight()/2;
			return new Point(x, y);
		}
	}

}
