;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use PolyPath)
(use User)
(use System)


(class ExitFeature of Object
	(properties
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		cursor 999
		lastCursor -1
		exitDir 0
		modNum -1
		noun 0
	)
	
	(method (init)
		(exitHandler add: self)
		(= cursor ((Cursor new:) view: cursor))
	)
	
	(method (doit)
		(cond 
			((self onMe: mouseX (- mouseY 10))
				(if (and (User canControl:) (== lastCursor -1))
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
			(
				(or
					(and
						(== ((ScriptID 883 7) cel?) 1)
						(not (== theIconBar (ScriptID 883 2)))
					)
					(and
						(== ((ScriptID 883 7) cel?) 1)
						(== theIconBar (ScriptID 883 2))
					)
				)
				(self doVerb: V_LOOK)
				(event claimed: TRUE)
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
	
	(method (doVerb theVerb &tmp theCode temp1)
		(= theCode (if doVerbCode else dftDoVerb))
		(if (== modNum -1)
			(= modNum curRoomNum)
		)
		(if (and msgType (Message MsgGet modNum noun theVerb NULL 1))
			(messager say: noun theVerb NULL 0 0 modNum)
		else
			(theCode doit: theVerb self)
		)
	)
)

(instance dftDoVerb of Code
	
	(method (doit)
		(return TRUE)
	)
)
