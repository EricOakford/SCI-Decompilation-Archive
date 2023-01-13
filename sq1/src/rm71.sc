;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm71 0
)

(local
	local0
	local1 =  3
	saveBits
)
(instance rm71 of Room
	(properties
		picture 16
	)
	
	(method (init)
		(if (Btst fStartedSelfDestruct)
			(Load VIEW 215)
			(LoadMany SOUND 411 312)
		else
			(LoadMany VIEW 115 217)
		)
		(theMusic fade:)
		(super init:)
		(ship init:)
		(if (Btst fDeltaurSelfDestructs)
			(self setScript: blowUp)
		else
			(self setScript: flyOffDeltaur)
		)
	)
)

(instance flyOffDeltaur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(escapePod
					loop: 5
					init:
					setPri: 0
					ignoreHorizon: 1
					cel: 0
					posn: 117 72
					setMotion: MoveTo 0 -5 self
				)
			)
			(1
				(if (Btst fStartedSelfDestruct)
					(curRoom setScript: blowUp)
				else
					(curRoom setScript: endGame)
				)
			)
		)
	)
)

(instance endGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Print 71 0)
				(curRoom setScript: flyingPod)
			)
		)
	)
)

(instance blowUp of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== local0 1) (Palette PALCycle 208 255 -1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(head init: hide:)
				(tail init: hide:)
				(side1 init: hide:)
				(side2 init: hide:)
				(= cycles 4)
			)
			(1
				(soundFx number: 805 loop: 1 play: 90)
				(explosion init: setCycle: CycleTo 2 1 self)
				(= local0 1)
				(= cycles 4)
			)
			(2
				(curRoom overlay: 15)
				(theMusic2 number: 805 loop: 1 play:)
				(= cycles 4)
			)
			(3
				(explosion setCycle: CycleTo 4 1 self)
			)
			(4
				(ship dispose:)
				(explosion setCycle: EndLoop self)
				(head
					show:
					moveSpeed: 2
					setCycle: Forward
					cycleSpeed: 10
					setStep: 5 5
					setMotion: MoveTo -50 50
				)
				(tail
					show:
					moveSpeed: 2
					setCycle: Forward
					cycleSpeed: 8
					setStep: 6 6
					setMotion: MoveTo 477 327
				)
				(side1
					show:
					moveSpeed: 2
					setCycle: Forward
					cycleSpeed: 10
					setStep: 8 8
					setMotion: MoveTo 493 7
				)
				(side2
					show:
					moveSpeed: 2
					setCycle: Forward
					cycleSpeed: 8
					setStep: 6 6
					setMotion: MoveTo -108 224
				)
			)
			(5
				(explosion hide:)
				(= cycles 75)
			)
			(6
				(= local0 0)
				(if (or (Btst fLeftDeltaurPodBay) (Btst fPodGone) (Btst fDeltaurSelfDestructs))
					(EgoDead 949 0 0 71 1)
				else
					(self setScript: flyingPod)
				)
			)
		)
	)
)

(instance flyingPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 17)
				(theMusic number: 701 loop: -1 flags: mNOPAUSE play:)
				(ship dispose:)
				(if (Btst fStartedSelfDestruct)
					(explosion dispose:)
					(head dispose:)
					(tail dispose:)
					(side1 dispose:)
					(side2 dispose:)
				)
				(UnLoad VIEW 215)
				(UnLoad VIEW 115)
				(= cycles 3)
			)
			(1
				(planet
					loop: (if (not (Btst fStartedSelfDestruct)) 6 else 0)
					init:
					posn: 355 76
					setPri: 4
					stopUpd:
				)
				(star setCycle: Forward init: setPri: 0)
				(star2 setCycle: Forward init: setPri: 0)
				(star3 setCycle: Forward init: setPri: 0)
				(escapePod
					setPri: -1
					setLoop: 3
					cel: 0
					posn: 0 145
					setPri: 4
					setMotion: MoveTo 120 117 self
				)
				(exhaust init: setCycle: Forward)
			)
			(2
				(if (> (planet x?) 253)
					(-- state)
					(planet x: (- (planet x?) 1) y: 76 forceUpd:)
					(if (== (-- local1) 0)
						(= local1 3)
						(escapePod x: (+ (escapePod x?) 1))
					)
				)
				(= cycles 1)
			)
			(3
				(if (not (Btst fStartedSelfDestruct))
					(curRoom setScript: destroyXenon)
				else
					(escapePod setMotion: MoveTo 452 78 self)
				)
			)
			(4
				(exhaust dispose:)
				(= ticks 60)
			)
			(5
				(escapePod
					setLoop: 5
					setPri: 2
					posn: 319 46
					setMotion: MoveTo 267 42 self
				)
			)
			(6 (curRoom newRoom: 68))
		)
	)
)

(instance destroyXenon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(exhaust dispose:)
				(escapePod setMotion: 0)
				(planet stopUpd:)
				(= cycles 1)
			)
			(1 (Print 71 2) (= cycles 1))
			(2 (= seconds 2))
			(3
				(= saveBits (Graph GSaveBits 74 0 78 245 1))
				(Graph GDrawLine 76 0 76 244 colLGreen 1 0 0)
				(Graph GDrawLine 75 0 75 122 colLGreen 1 0 0)
				(Graph GDrawLine 77 0 77 122 colLGreen 1 0 0)
				(Graph GShowBits 74 0 78 245 1)
				(soundFx number: 312 loop: 1 play:)
				(= ticks 80)
			)
			(4
				(Graph GRestoreBits saveBits)
				(Graph GShowBits 74 0 78 245 1)
				(soundFx number: 411 loop: 1 play:)
				(planet cycleSpeed: 5 setCycle: EndLoop self)
			)
			(5
				(planet dispose:)
				(= seconds 4)
			)
			(6 (EgoDead 950 0 0 71 3))
		)
	)
)

(instance ship of Prop
	(properties
		x 162
		y 82
		view 215
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance explosion of Prop
	(properties
		x 162
		y 82
		view 215
		cel 1
		priority 2
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance head of Actor
	(properties
		x 106
		y 51
		view 115
		signal (| ignrAct fixedLoop)
		cycleSpeed 2
	)
)

(instance tail of Actor
	(properties
		x 199
		y 105
		view 115
		loop 3
		signal (| ignrAct fixedLoop)
		cycleSpeed 2
	)
)

(instance side1 of Actor
	(properties
		x 207
		y 73
		view 115
		loop 1
		signal (| ignrAct fixedLoop)
		cycleSpeed 2
	)
)

(instance side2 of Actor
	(properties
		x 142
		y 109
		view 115
		loop 2
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 2
	)
)

(instance planet of Prop
	(properties
		x 272
		y 89
		view 217
	)
)

(instance star of Prop
	(properties
		x 97
		y 68
		view 217
		loop 1
		cycleSpeed 2
	)
)

(instance star2 of Prop
	(properties
		x 279
		y 148
		view 217
		loop 2
		cycleSpeed 2
	)
)

(instance star3 of Prop
	(properties
		x 250
		y 177
		view 217
		loop 2
		cycleSpeed 2
	)
)

(instance escapePod of Actor
	(properties
		y 145
		view 217
		loop 3
		signal (| ignrAct fixedLoop)
		cycleSpeed 2
		moveSpeed 2
	)
)

(instance exhaust of Actor
	(properties
		x 74
		y 103
		view 217
		loop 4
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
		moveSpeed 2
	)
	
	(method (doit)
		(self
			x: (- (escapePod x?) 79)
			y: (+ (escapePod y?) 27)
		)
		(super doit: &rest)
	)
)
