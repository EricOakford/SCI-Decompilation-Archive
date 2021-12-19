;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;; 	CURSOR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	General purpose 'system' classes, presumably not adventure-game
;;;;	specific.  Defines the base class, Object, and all Collection
;;;;	classes.  Probably should be broken up into several modules.
;;;;
;;;;	Classes:
;;;;		Cursor
;;;;


(script# CURSOR)
(include game.sh)
(use Main)
(use Actor)


(class Cursor kindof View
	;;; The Cursor class is an object used to control the user's cursor.
	;;;	(Written by Robert W. Lindsley, July, 1991)

	(properties
		view			NULL
		loop			NULL
		cel			NULL
		x				0
		y				0
		hidden		FALSE		;private
	)
;;;	(methods
;;;		posn				;move cursor to a set of screen coordinates
;;;		setView			;set cursor's view
;;;		setLoop			;set cursor's loop
;;;		setCel			;set cursor's cel
;;;		show
;;;		hide
;;;	)

	(method (init)
		;
		; Set the game cursor to us, setting the hot spot as well (if indicated)

		(SetCursor view loop cel)
		(return self)
	)

	(method (posn theX theY)
		;
		; Move the cursor to a specific set of coordinates

 		(SetCursor theX theY)
		(= x (= mouseX theX))
		(= y (= mouseY theY))
	)

	(method (setView whichView)
		;
		; Set the view of the cursor

		(= view whichView)
		(self init:)
	)

	(method (setLoop whichLoop)
		;
		; Set the loop of the cursor

		(= loop whichLoop)
		(self init:)
	)

	(method (setCel whichCel)
		;
		; Set the cel of the cursor

		(= cel whichCel)
		(self init:)
	)

	(method (show)
		;
		; Show the cursor

		(if hidden
			(SetCursor TRUE)
			(= hidden FALSE)
		)
	)

	(method (hide)
		;
		; Hide the cursor

		(if (not hidden)
			(SetCursor FALSE)
			(= hidden TRUE)
		)
	)
)
