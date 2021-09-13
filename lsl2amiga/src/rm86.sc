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
		(addToPics add: aThought aMoon doit:)
		(aBigEgo
			setLoop: 0
			setPri: 14
			init:
		)
		(aBigEgoEyes
			setLoop: 2
			setPri: 15
			init:
		)
		(aBigEgoMouth
			setPri: 15
			init:
		)
		(aReflection
			setLoop: 1
			setCycle: Forward
			cycleSpeed: 5
			isExtra: 1
			init:
		)
		(aVolcano
			setLoop: 0
			setPri: 2
			cel: 0
			cycleSpeed: 1
			init:
			hide:
		)
		(aSmoke
			setLoop: 2
			setPri: 1
			cycleSpeed: 3
			init:
			hide:
		)
		(aLava
			setLoop: 3
			setPri: 3
			cycleSpeed: 4
			init:
			hide:
		)
		(aKalalau
			setLoop: 2
			setPri: 9
			setCycle: Walk
			illegalBits: 0
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

(instance aThought of PicView
	(properties
		y 179
		x 213
		view 718
		loop 3
		priority 14
		signal ignrAct
	)
)

(instance aMoon of PicView
	(properties
		y 47
		x 257
		view 825
	)
)

(instance aBigEgo of View
	(properties
		y 1095
		x 204
		view 116
		signal ignrAct
	)
)

(instance aBigEgoEyes of Prop
	(properties
		y 1085
		x 202
		view 116
		signal ignrAct
	)
)

(instance aBigEgoMouth of Prop
	(properties
		y 1107
		x 202
		view 116
		loop 1
		signal ignrAct
	)
)

(instance aReflection of Prop
	(properties
		y 134
		x 262
		view 825
		signal ignrAct
	)
)

(instance aVolcano of Prop
	(properties
		y 29
		x 147
		view 827
		signal ignrAct
	)
)

(instance aSmoke of Prop
	(properties
		y 29
		x 147
		view 827
		signal ignrAct
	)
)

(instance aLava of Prop
	(properties
		y 29
		x 147
		view 827
		signal ignrAct
	)
)

(instance aKalalau of Actor
	(properties
		y 133
		x 174
		view 718
		signal ignrAct
	)
)
