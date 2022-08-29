;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use PolyPath)
(use System)


(class ExitFeature of Object
	(properties
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		cursor ARROW_CURSOR
		lastCursor -1
		exitDir 0
		noun 0
	)
	
	(method (init)
		(gGk1Exits add: self)
		(= cursor ((Cursor new:) view: cursor))
	)
	
	(method (doit)
		(cond 
			((self onMe: mouseX (- mouseY 10))
				(if (== lastCursor -1)
					(= lastCursor theCursor)
					(theGame setCursor: cursor)
				)
			)
			((!= lastCursor -1)
				(if (== theCursor cursor)
					(theGame
						setCursor: ((theIconBar walkIconItem?) cursor?)
					)
				)
				(= lastCursor -1)
			)
		)
	)
	
	(method (dispose &tmp theCursor_2)
		(if (== theCursor cursor)
			(theGame
				setCursor: ((theIconBar walkIconItem?) cursor?)
			)
		)
		(if (IsObject cursor)
			(= cursor ((= theCursor_2 cursor) view?))
			(theCursor_2 dispose:)
		)
		(gGk1Exits delete: self)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(<= nsLeft theObjOrX)
					(<= theObjOrX nsRight)
					(<= nsTop theY)
				)
				(<= theY nsBottom)
			else
				FALSE
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((not (user input?)))
			((not (self onMe: mouseX (- mouseY 10))))
			(
				(or
					(and
						(== (event type?) keyDown)
						(!= (event message?) ENTER)
					)
					(and
						(== (event type?) mouseDown)
						(event modifiers?)
					)
					(not (OneOf (event type?) mouseDown keyDown))
				)
				(= lastCursor -1)
			)
			((== theCursor ((theIconBar at: ICON_LOOK) cursor?))
				(event claimed: TRUE)
				(messager say: noun V_LOOK)
			)
			((!= theCursor cursor))
			(else
				(event claimed: TRUE)
				(switch exitDir
					(1
						(ego setMotion: PolyPath mouseX 0)
					)
					(3
						(ego setMotion: PolyPath mouseX 190)
					)
					(2
						(ego setMotion: PolyPath 320 (- mouseY 10))
					)
					(4
						(ego setMotion: PolyPath 0 (- mouseY 10))
					)
				)
			)
		)
	)
)
