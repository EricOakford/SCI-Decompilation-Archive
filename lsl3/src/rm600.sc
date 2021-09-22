;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	rm600 0
)

(instance rm600 of Room
	(properties
		picture 99
	)
	
	(method (init)
		(HandsOff)
		(Bset fSaveDisabled)
		(Bset fCursorHidden)
		(Bset fAutoSaveDisabled)
		(music number: 600 loop: -1 play:)
		(super init:)
		(= saveSpeed (theGame setSpeed: 3))
		(ego view: 601 setLoop: 1 setStep: 22 12 init:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ego
			posn: (Random 10 310) -40
			setMotion: MoveTo (Random 10 310) 224 RoomScript
		)
		(switch (= state newState)
			(2
				(Printf 600 0 expletive)
			)
			(4
				(Print 600 1)
			)
			(5
				(ego setLoop: 0)
			)
			(8
				(Print 600 2)
			)
			(9
				(Print 600 3)
			)
			(10
				(theGame setSpeed: saveSpeed)
				(music fade:)
				(curRoom newRoom: 610)
			)
		)
	)
)
