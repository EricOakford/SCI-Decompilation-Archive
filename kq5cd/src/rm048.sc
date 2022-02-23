;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm048 0
)

(local
	[local0 6] = [0 3073 1 7027]
	[local6 9] = [1000 75 123 4 11 24 19 23 30]
	[local15 9] = [1003 125 123 4 11 25 23 31 31]
)
(instance rm048 of KQ5Room
	(properties
		picture 48
		north 49
		south 47
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 620 651)
		(if (> (theGame detailLevel:) 0)
			(wave0 setCycle: Forward init:)
			(wave1 setCycle: Forward init:)
			(wave2 setCycle: Forward init:)
		)
		(self setFeatures: island)
		(HandsOff)
		(= inCartoon 0)
		(switch prevRoomNum
			(49
				(if (== (ego view?) 21)
					(LoadMany 128 128 130)
					(self setScript: swimOutScript)
				else
					(self setScript: sailOutScript)
				)
			)
			(else 
				(theMusic number: 814 loop: -1 vol: 127 playBed:)
				(self setScript: sailInScript)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance sailInScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(boat
					init:
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo (boat x?) (if (Btst 54) 170 else 190) self
				)
			)
			(2
				(if (not (Btst 54))
					(SolvePuzzle 3)
					(proc762_0 @local6 @local15 @local0 self)
				else
					(= cycles 1)
				)
			)
			(3
				(if (not (Btst 54))
					(boat
						init:
						setLoop: 0
						setCycle: Walk
						setMotion: MoveTo (boat x?) 170 self
					)
				else
					(= cycles 1)
				)
			)
			(4 (curRoom newRoom: 49))
		)
	)
)

(instance sailOutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boat
					init:
					view: 620
					posn: (boat x?) 170
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo (boat x?) 219 self
				)
			)
			(1 (curRoom newRoom: 47))
		)
	)
)

(instance swimOutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boat
					init:
					view: 128
					posn: (boat x?) 155
					setLoop: 4
					setCycle: Walk
					setStep: 1 1
					setMotion: MoveTo (boat x?) 175 self
				)
			)
			(1
				(boat view: 130 setLoop: 1 cycleSpeed: 2 setCycle: Forward)
				(= seconds 4)
			)
			(2
				(= deathMessage 524)
				(EgoDead)
			)
		)
	)
)

(instance boat of Actor
	(properties
		x 140
		y 219
		yStep 1
		view 620
		xStep 1
		moveSpeed 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if (== view 620)
						(SpeakAudio 533)
					else
						(SpeakAudio 534)
					)
					(event claimed: 1)
				)
				(verbDo (event claimed: 1))
			)
		)
	)
)

(instance island of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 535)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wave0 of Prop
	(properties
		x 47
		y 150
		view 651
		priority 1
		signal $4010
		cycleSpeed 3
		detailLevel 3
	)
)

(instance wave1 of Prop
	(properties
		x 147
		y 170
		view 651
		loop 1
		priority 1
		signal $4010
		cycleSpeed 3
		detailLevel 3
	)
)

(instance wave2 of Prop
	(properties
		x 259
		y 151
		view 651
		loop 2
		priority 1
		signal $4010
		cycleSpeed 3
		detailLevel 3
	)
)
