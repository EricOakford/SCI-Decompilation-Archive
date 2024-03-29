;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm86 0
)

(local
	local0
	aVolcano
	aSmoke
	aLava
	aReflection
	aBigEgo
	aBigEgoEyes
	aBigEgoMouth
	aKalalau
)
(instance theSound of Sound
	(properties
		number 116
	)
)

(instance rm86 of Room
	(properties
		picture 86
		style (| BLACKOUT IRISOUT)
		horizon 1
	)
	
	(method (init)
		(Load VIEW 116)
		(Load VIEW 718)
		(Load VIEW 824)
		(Load VIEW 825)
		(Load VIEW 827)
		(Load SOUND 116)
		(super init:)
		(theSound init:)
		((View new:)
			view: 718
			ignoreActors:
			loop: 3
			posn: 213 179
			setPri: 14
			addToPic:
		)
		((View new:) view: 825 posn: 257 47 addToPic:)
		((= aBigEgo (View new:))
			view: 116
			ignoreActors:
			setLoop: 0
			setPri: 14
			posn: 204 1095
			init:
		)
		((= aBigEgoEyes (Prop new:))
			view: 116
			setLoop: 2
			ignoreActors:
			setPri: 15
			posn: 202 1085
			init:
		)
		((= aBigEgoMouth (Prop new:))
			view: 116
			setLoop: 1
			ignoreActors:
			setPri: 15
			posn: 202 1107
			init:
		)
		((= aReflection (Prop new:))
			view: 825
			ignoreActors:
			setLoop: 1
			posn: 262 134
			setCycle: Forward
			cycleSpeed: 5
			isExtra: 1
			init:
		)
		((= aVolcano (Prop new:))
			view: 827
			ignoreActors:
			setLoop: 0
			setPri: 2
			cel: 0
			posn: 147 29
			cycleSpeed: 1
			init:
			hide:
		)
		((= aSmoke (Prop new:))
			view: 827
			ignoreActors:
			setLoop: 2
			setPri: 1
			posn: 147 29
			cycleSpeed: 3
			init:
			hide:
		)
		((= aLava (Prop new:))
			view: 827
			ignoreActors:
			setLoop: 3
			setPri: 3
			cel: 0
			posn: 147 29
			cycleSpeed: 4
			init:
			hide:
		)
		((= aKalalau (Actor new:))
			view: 718
			ignoreActors:
			illegalBits: 0
			setLoop: 2
			posn: 174 133
			setPri: 9
			setCycle: Walk
			setStep: 4 3
			cycleSpeed: 2
			moveSpeed: 2
			init:
			hide:
		)
		(ego
			view: 718
			illegalBits: 0
			ignoreActors:
			loop: 0
			cel: 0
			posn: 176 108
			setMotion: 0
			setCycle: Walk
			setStep: 4 3
			cycleSpeed: 2
			moveSpeed: 2
			init:
		)
		(HandsOff)
		(self setScript: rm86Script)
	)
)

(instance rm86Script of Script
	(method (doit)
		(super doit:)
		(if (and (> state 3) (< state 7) (> 50 (Random 0 75)))
			(ShakeScreen 1 (Random 1 3))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				(ego setCycle: EndLoop self)
			)
			(2
				(aKalalau show: setMotion: MoveTo 210 161 self)
				(ego
					setLoop: 1
					cel: 0
					posn: 156 135
					setCycle: Walk
					setMotion: MoveTo 200 169
				)
			)
			(3
				(theSound play:)
				(ego hide:)
				(aKalalau
					view: 824
					setLoop: 0
					cel: 0
					posn: 204 166
					setCycle: EndLoop self
				)
			)
			(4
				(aVolcano show: setCycle: EndLoop self)
			)
			(5
				(aVolcano setLoop: 1 cycleSpeed: 3 setCycle: Forward)
				(aSmoke show: setCycle: Forward)
				(= cycles 10)
			)
			(6
				(aLava show: setCycle: EndLoop self)
			)
			(7
				(aLava setLoop: 4 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(Print 86 0 #draw)
				(Print 86 1)
				(Print 86 2)
				(Print 86 3)
				(Print 86 4)
				(= seconds 3)
			)
			(9
				(aKalalau setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(10
				(aBigEgo posn: 204 94 stopUpd:)
				(aBigEgoEyes posn: 202 84)
				(aBigEgoMouth posn: 202 106)
				(= seconds 3)
			)
			(11
				(aBigEgoEyes setCycle: EndLoop self)
				(aBigEgoMouth setCycle: EndLoop)
			)
			(12
				(aKalalau setLoop: 2 setCycle: Forward)
			)
		)
	)
)
