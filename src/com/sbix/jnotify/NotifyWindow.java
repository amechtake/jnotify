package com.sbix.jnotify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.Timer;

/**
 * Represents a notification window that can be displayed on the screen.
 * This window shows a message with a specific style (type) and can be positioned
 * at various locations on the screen. It automatically disappears after a configurable delay.
 * The window also responds to mouse events to pause the disappearance timer when hovered.
 */
public class NotifyWindow extends JDialog implements ActionListener,
		MouseListener, ScreenDimension {

	private static final long serialVersionUID = 1L;
	private NotifyType panneau_principal = null;
	private Timer timerPrint = null;
	private Timer timerExit = null;

	// heigth and width of notifaction
	private int width = 360;
	private int height = 80;
	// default position of the notification
	private NPosition position = NPosition.CENTER;
	// default Type of the notification
	private Color type = NotifyType.DEFAULT_NOTIFICATION;

	// display time of notification in milliseconds
	/** Short delay for notification visibility (1500 milliseconds). */
	public static final int SHORT_DELAY = 1500;
	/** Normal delay for notification visibility (2500 milliseconds). */
	public static final int NORMAL_DELAY = 2500;
	/** Long delay for notification visibility (4000 milliseconds). */
	public static final int LONG_DELAY = 4000;

	/**
	 * Constructs a new notification window with specified type, message, delay, and position.
	 *
	 * @param notification The color representing the type of notification (e.g., {@link NotifyType#SUCCESS_NOTIFICATION}).
	 * @param message      The message to be displayed in the notification.
	 * @param delay        The duration in milliseconds for which the notification will be visible (e.g., {@link #NORMAL_DELAY}).
	 * @param position     The position on the screen where the notification will appear (e.g., {@link NPosition#TOP_RIGHT}).
	 */
	public NotifyWindow(Color notification, String message, int delay,
			NPosition position) {
		super();
		this.position = position;
		this.type = notification;
		init(this.type, message, delay, this.position);
	}

	/**
	 * Constructs a new notification window with specified type, message, and delay, using the default position (CENTER).
	 *
	 * @param notification The color representing the type of notification.
	 * @param message      The message to be displayed.
	 * @param delay        The duration in milliseconds for visibility.
	 */
	public NotifyWindow(Color notification, String message, int delay) {
		super();
		this.type = notification;
		init(this.type, message, delay, this.position);
	}

	/**
	 * Constructs a new notification window with specified type and message, using default delay and position.
	 *
	 * @param notification The color representing the type of notification.
	 * @param message      The message to be displayed.
	 */
	public NotifyWindow(Color notification, String message) {
		super();
		this.type = notification;
		init(this.type, message, NORMAL_DELAY, this.position);
	}

	/**
	 * Constructs a new notification window with a specified message, using default type, delay, and position.
	 *
	 * @param message The message to be displayed.
	 */
	public NotifyWindow(String message) {
		super();
		init(this.type, message, NORMAL_DELAY, this.position);
	}

	/**
	 * Constructs a new notification window with a specified message and position, using default type and delay.
	 *
	 * @param message  The message to be displayed.
	 * @param position The position on the screen.
	 */
	public NotifyWindow(String message, NPosition position) {
		super();
		this.position = position;
		init(this.type, message, NORMAL_DELAY, this.position);
	}

	/**
	 * Initializes the notification window's components and appearance.
	 * Sets up the size, opacity, location, content pane, and timers.
	 *
	 * @param notiColor The color for the notification type.
	 * @param message   The message text.
	 * @param delay     The display duration.
	 * @param position  The screen position.
	 */
	private void init(Color notiColor, String message, int delay,
			NPosition position) {
		panneau_principal = new NotifyType(notiColor, message);
		this.setSize(new Dimension(width, height));
		this.setUndecorated(true);
		this.setResizable(false);
		this.setOpacity((float) 0.9);
		this.setLocation(position.getPosition(this));
		this.setContentPane(panneau_principal);
		this.setVisible(true);
		this.addMouseListener(this);
		/*
		 * instantiation of timers and the launch of the first timer that
		 * displays the notification according to the time given in parameter
		 */
		this.timerPrint = new Timer(delay, this);
		this.timerExit = new Timer(300, this);
		this.timerPrint.setRepeats(false);
		this.timerPrint.start();
	}
	
	/**
	 * Gets the current screen position of the notification window.
	 *
	 * @return The {@link NPosition} enum constant representing the current position.
	 */
	public NPosition getPosition() {
		return position;
	}

	/**
	 * Sets the screen position for the notification window.
	 * Note: This method might not dynamically update the position if called after the window is visible.
	 * It's primarily used for internal state management before initialization.
	 *
	 * @param position The {@link NPosition} enum constant to set as the new position.
	 */
	public void setPosition(NPosition position) {
		this.position = position;
	}
	
	/**
	 * Adjusts the static inset values when a notification at a specific corner is closed.
	 * This is intended to allow subsequent notifications in the same corner to stack correctly.
	 *
	 * @param position The {@link NPosition} of the window that was closed.
	 */
	public void clear(NPosition position) {
		if (position == NPosition.TOP_LEFT) {
			topLeftInsets.y = topLeftInsets.y - this.getHeight() - blocSpacing; 
		} else if (position == NPosition.TOP_RIGHT) {
			topRightInsets.y = topRightInsets.y - this.getHeight() - blocSpacing ;			
		}else if(position == NPosition.BOTTOM_LEFT){
			bottomLeftInsets.y = bottomLeftInsets.y -  this.getHeight() - blocSpacing;
		}else if(position == NPosition.BOTTOM_RIGHT){
			bottomRightInsets.y = bottomRightInsets.y - this.getHeight() - blocSpacing;
		}
	}

	/**
	 * Handles action events, primarily from the internal timers.
	 * When {@code timerPrint} fires, it starts {@code timerExit}.
	 * When {@code timerExit} fires, it gradually fades out the notification window
	 * and then disposes of it, clearing its position from the insets.
	 *
	 * @param e The ActionEvent object.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// launched timers exit
		if (e.getSource() == timerPrint) {
			timerExit.start();
		}

		if (e.getSource() == timerExit) {
			if (this.getOpacity() > 0.1) {
				this.setOpacity((float) ((float) this.getOpacity() - 0.1));
			} else {
				if (timerExit.isRunning()) {
					timerExit.stop();
				}
				this.clear(this.getPosition());
				this.dispose();
			}
		}
	}


	/**
	 * Invoked when the mouse button has been clicked (pressed and released) on the notification window.
	 * Currently, this method has no specific action implemented.
	 *
	 * @param e The MouseEvent object.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Invoked when the mouse enters the notification window.
	 * If the exit timer ({@code timerExit}) is running (meaning the window is fading out),
	 * this method stops the exit timer, restarts the print timer ({@code timerPrint})
	 * to keep the notification visible, and resets the opacity to full (0.9).
	 * This allows the user to hover over the notification to prevent it from disappearing.
	 *
	 * @param e The MouseEvent object.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// check that the cursor is on the notification at the exit timer is
		// started
		if (timerExit.isRunning()) {
			timerExit.stop();
			timerPrint.restart();
			if (timerPrint.isRunning()) {
				this.setOpacity((float) 0.9);
			}
		}

	}

	/**
	 * Invoked when the mouse exits the notification window.
	 * Currently, this method has no specific action implemented.
	 *
	 * @param e The MouseEvent object.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Invoked when a mouse button has been pressed on the notification window.
	 * Currently, this method has no specific action implemented.
	 *
	 * @param e The MouseEvent object.
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * Invoked when a mouse button has been released on the notification window.
	 * Currently, this method has no specific action implemented.
	 *
	 * @param e The MouseEvent object.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Gets the current location of this notification window.
	 * This method is part of the {@link ScreenDimension} interface implementation,
	 * though it directly returns the window's current location rather than calculating
	 * a new position based on the input dialog (which is itself in this case).
	 *
	 * @param window The JDialog window (expected to be this instance).
	 * @return A Point object representing the current (x, y) screen coordinates of this notification window.
	 */
	@Override
	public Point getPosition(JDialog window) {
		return this.getLocation();
	}

}
