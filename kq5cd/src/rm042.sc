;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm042 0
)

(local
	local0
	[local1 9] = [1028 180 62 3 9 26 24 32 32]
)
(instance rm042 of KQ5Room
	(properties
		picture 42
		south 43
	)
	
	(method (init)
		(super init:)
		(theMusic number: 824 loop: -1 vol: 127 playBed:)
		(locket setCycle: Forward cycleSpeed: 3 init:)
		(self setFeatures: egg nest)
		(User canControl: 0 canInput: 1)
		(theGame setCursor: crownCursor)
		(theIconBar enable:)
		(ego normal: 0 view: 604 loop: 0 posn: 195 125 init:)
		((ego head?) hide:)
		(self setScript: hatch)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance hatch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 45))
			(1
				(babyRoc1
					cycleSpeed: (if (== howFast 0) 10 else 20)
					setCycle: EndLoop self
					init:
				)
				(babyRoc2
					cycleSpeed: (if (== howFast 0) 10 else 20)
					setCycle: EndLoop self
					init:
				)
			)
			(2
				(theMusic3 number: 789 loop: -1 vol: 127 play:)
			)
			(3
				(babyRoc1 loop: 2 cel: 0 setCycle: EndLoop self)
				(babyRoc2 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4)
			(5
				(theMusic3 stop:)
				(proc762_1 @local1 7025 self)
			)
			(6
				(cls)
				(babyRoc1 dispose: stopUpd:)
				(ego hide:)
				(if (== ((inventory at: 19) owner?) 34)
					(babyRoc2
						loop: 4
						posn: 126 78
						cel: 0
						cycleSpeed: 10
						setCycle: CycleTo 1 1 self
					)
				else
					(HandsOff)
					(babyRoc2
						loop: 4
						posn: 126 78
						cel: 0
						cycleSpeed: 10
						setCycle: EndLoop self
					)
					(++ state)
					(theMusic number: 825 loop: -1 vol: 127 playBed:)
				)
			)
			(7 (curRoom newRoom: 43))
			(8
				(babyRoc2 loop: 5 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(9
				(babyRoc2 loop: 6 cel: 0 setCycle: Forward cycleSpeed: 1)
				(= seconds 3)
			)
			(10
				(babyRoc2 cycleSpeed: 2 setCycle: RandCycle)
				(SpeakAudio 9116 self)
			)
			(11
				(cls)
				(= deathMessage 494)
				(EgoDead)
			)
		)
	)
)

(instance getLocket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1 (locket hide:) (= cycles 2))
			(2
				(SpeakAudio 501)
				(locket dispose:)
				(ego loop: 0)
			)
			(3
				(HandsOn)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance egg of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if (>= (hatch state?) 4)
						(SpeakAudio 495)
					else
						(SpeakAudio 496)
					)
					(event claimed: 1)
				)
				(verbDo
					(if (>= (hatch state?) 4)
						(SpeakAudio 502)
					else
						(SpeakAudio 503)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance locket of Prop
	(properties
		x 185
		y 125
		view 604
		loop 2
		signal $4010
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
					(SpeakAudio 497)
					(event claimed: 1)
				)
				(verbDo
					(ego get: 25)
					(SolvePuzzle 2)
					(HandsOff)
					(ego setScript: getLocket)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance babyRoc1 of Actor
	(properties
		x 147
		y 107
		view 606
		priority 2
		signal $4010
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
					(if (>= (hatch state?) 4)
						(SpeakAudio 498)
						(event claimed: 1)
					)
				)
				(verbDo
					(if (not local0)
						(++ local0)
						(SpeakAudio 504)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance babyRoc2 of Actor
	(properties
		x 153
		y 90
		view 606
		loop 1
		priority 1
		signal $4010
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
					(if (>= (hatch state?) 4)
						(SpeakAudio 498)
						(event claimed: 1)
					)
				)
				(verbDo
					(if (not local0)
						(++ local0)
						(SpeakAudio 504)
						(event claimed: 1)
					)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 505)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance nest of RFeature
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
					(if (>= (hatch state?) 4)
						(SpeakAudio 499)
					else
						(SpeakAudio 500)
					)
					(event claimed: 1)
				)
			)
		)
	)
)
