;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO) ;707
(include game.sh)
(use Main)
(use Sq4Dialog)
(use Game)
(use System)

(public
	intro 0
)

(local
	local0
)

(enum	;intro buttons
	skipIt
	watchIt
	restoreGame
)

(class intro of Region
	(properties
		iconBar 0
	)
	
	(method (init)
		(theIconBar disable:)
		(super init:)
		(= theCursor (= waitCursor INVIS_CURSOR))
		(theGame setCursor: waitCursor)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (handleEvent event &tmp oldCur)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(event claimed: TRUE)
			(sounds pause: TRUE)
			(theGame setCursor: ARROW_CURSOR)
			(SetCursor 127 99)
			(switch
				(PrintD
					{Would you like to skip\nthe introduction or\nwatch the whole thing?}
					#at 100 60
					#new
					#button {Skip it} skipIt
					#new
					#button {Watch it} watchIt
					#new
					#button {Restore a Game} restoreGame
				)
				(skipIt
					(music fade: 0 1 2 1)
					(Palette PALIntensity 8 15 100)
					(= theCursor (= waitCursor (ScriptID 0 19)))
					(curRoom newRoom: 59)
				)
				(watchIt
					(sounds pause: FALSE)
					(theGame setCursor: waitCursor)
				)
				(restoreGame
					(Palette PALIntensity 8 10 100)
					(sounds pause: FALSE)
					(= oldCur normalCursor)
					(= normalCursor ARROW_CURSOR)
					(theGame restore:)
					(= normalCursor oldCur)
					(theGame setCursor: waitCursor)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= keep (OneOf newRoomNumber 1 6 9 10 15 16 17 19 20 21))
		(= initialized FALSE)
		(if (not keep)
			(theIconBar enable:)
			(= waitCursor (ScriptID 0 19))
		)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
)
