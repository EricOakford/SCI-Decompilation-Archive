;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include sci.sh)
(use Main)
(use PolyPath)
(use System)


(class ExitFeature of Obj
	(properties
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		cursor 999
		lastCursor -1
		exitDir 0
		noun 0
	)
	
	(method (init)
		(exitHandler add: self)
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
		(if (IsObject cursor)
			(= cursor ((= theCursor_2 cursor) view?))
			(theCursor_2 dispose:)
		)
		(exitHandler delete: self)
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
				0
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
						(== (event type?) evKEYBOARD)
						(!= (event message?) KEY_RETURN)
					)
					(and
						(== (event type?) evMOUSEBUTTON)
						(event modifiers?)
					)
					(not (OneOf (event type?) 1 4))
				)
				(= lastCursor -1)
			)
			((== theCursor ((theIconBar at: 1) cursor?)) (event claimed: 1) (messager say: noun 1))
			((!= theCursor cursor))
			(else
				(event claimed: 1)
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
