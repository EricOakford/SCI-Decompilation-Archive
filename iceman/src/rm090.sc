;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm090 0
)

(instance rm090 of Room
	(properties
		picture 99
	)
	
	(method (init)
		(Load VIEW 90)
		(Load SOUND 93)
		(super init:)
		(aFace init:)
		(aGeneral init:)
		(self setScript: RoomScript)
		(HandsOff)
	)
)

(instance RoomScript of Script
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(cls)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSong number: (SoundFX 93) loop: -1 play:)
				(Print 90 0 #at -1 140 #dispose)
				(= seconds 9)
			)
			(1
				(aFace setCycle: Forward)
				(Print 90 1
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(2
				(aFace setCel: 0)
				(= cycles 3)
			)
			(3
				(aFace setCycle: Forward)
				(Print 90 2
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(4
				(aFace setCel: 0)
				(= cycles 3)
			)
			(5
				(aFace setCycle: Forward)
				(Print 90 3
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(6
				(aFace setCel: 0)
				(= cycles 3)
			)
			(7
				(aFace setCycle: Forward)
				(Print 90 4
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(8
				(aFace setCel: 0)
				(= cycles 3)
			)
			(9
				(aFace setCycle: Forward)
				(Print 90 5
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(10
				(aFace setCel: 0)
				(= cycles 3)
			)
			(11
				(aFace setCycle: Forward)
				(Print 90 6
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(12
				(aFace setCel: 0)
				(= cycles 3)
			)
			(13
				(aFace setCycle: Forward)
				(Print 90 7
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(14
				(aFace setCel: 0)
				(= cycles 3)
			)
			(15
				(aFace setCycle: Forward)
				(Print 90 8
					#title {Captain}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 9)
			)
			(16
				(aFace setCel: 0)
				(theSong fade:)
				(= seconds 3)
			)
			(17
				(cls)
				(curRoom newRoom: 89)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) keyDown) (event claimed?))
			(return)
		)
		(if modelessDialog
			(cls)
			(self cue:)
		)
		(event claimed: TRUE)
	)
)

(instance aGeneral of View
	(properties
		y 144
		x 158
		view 90
	)
	
	(method (init)
		(super init:)
		(self ignoreActors:)
	)
)

(instance aFace of Prop
	(properties
		y 79
		x 157
		view 90
		loop 1
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
	)
)

(instance theSong of Sound
	(properties
		priority 15
	)
)
