;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm002 0
)

(local
	local0
	[local1 2]
	egoX
	egoY
	local5
)
(instance rm002 of Room
	(properties
		picture 2
		east 27
	)
	
	(method (init &tmp [temp0 50])
		(= showStyle HSHUTTER)
		(Load VIEW 0)
		(Load VIEW 11)
		(Load VIEW 12)
		(Load SOUND 4)
		(Load SOUND 5)
		(Load SCRIPT JUMP)
		(ego
			view: 11
			loop: 0
			cel: 0
			setPri: 9
			ignoreActors: TRUE
			illegalBits: 0
			posn: 141 111
			viewer: 0
		)
		(HandsOff)
		(ego init:)
		(super init:)
		(self setScript: doorScript)
		(door init:)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
)

(instance doorSound of Sound
	(properties
		number 4
	)
)

(instance doorScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(= seconds 3)
			)
			(1
				(doorSound play:)
				(= cycles 20)
			)
			(2 (door setCycle: EndLoop self))
			(3
				(door stopUpd:)
				(= cycles 8)
			)
			(4
				(ego setPri: 10 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(5 (= cycles 7))
			(6
				(ego loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(7 (= cycles 7))
			(8 (ego setCycle: EndLoop self))
			(9 (= cycles 10))
			(10
				(ego loop: 2 cel: 0 setCycle: 0)
				(= cycles 10)
			)
			(11
				(ego loop: 2 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(12
				(ego
					cycleSpeed: 0
					setStep: -1 10
					setMotion: JumpTo 142 142 self
				)
			)
			(13 (ego setCycle: EndLoop self))
			(14 (= cycles 7))
			(15
				(ego setCel: (- (ego cel?) 1))
				(= cycles 5)
			)
			(16
				(ego
					view: 0
					loop: 2
					cel: 0
					illegalBits: cWHITE
					setStep: -1 2
					setCycle: Walk
					setPri: -1
				)
				(ego viewer: local0)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(self changeState: (+ state 3))
				(= cycles 10)
			)
			(17 (= cycles 2))
			(18 (= cycles 2))
			(19
				(door setPri: 9 setCycle: BegLoop self)
				(doorSound number: 5 play:)
			)
			(20
				(door stopUpd:)
				(= seconds 1)
			)
			(21
				(door setLoop: 1 forceUpd:)
				(ego setScript: demoScript)
			)
		)
	)
)

(instance demoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 11 play:)
				(ego loop: 1)
				(= seconds 4)
			)
			(1 (ego loop: 0) (= seconds 3))
			(2
				(ego setMotion: MoveTo 181 144 self)
			)
			(3
				(ego setMotion: MoveTo 325 144)
			)
		)
	)
)

(instance door of Prop
	(properties
		view 12
	)
	
	(method (init)
		(super init:)
		(self
			cycleSpeed: 1
			setLoop:
				(if
					(or
						(== prevRoomNum 155)
						(== prevRoomNum 1)
						(== prevRoomNum 900)
						(== (GameIsRestarting) TRUE)
					)
					0
				else
					1
				)
			setPri: (if (or (== prevRoomNum 2) (== prevRoomNum 3))
				9
			else
				10
			)
			ignoreActors: TRUE
			posn: 138 115
			stopUpd:
		)
	)
)
