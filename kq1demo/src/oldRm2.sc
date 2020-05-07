;;; Sierra Script 1.0 - (do not remove this comment)
(script# 502)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	oldRm2 0
)

(local
	local0
	local1
	local2
	local3
)
(instance oldRm2 of Room
	(properties
		picture 502
		style WIPELEFT
		east 501
		west 2
	)
	
	(method (init)
		(LoadMany VIEW
			3 597 607 509 511 186 107 108
			0 265 15 512 80 81 500 267
		)
		(Load SOUND 1)
		(Load PICTURE 1)
		(super init:)
		(= local0 3)
		(castleDoor init:)
		(flags
			init:
			setCycle: (if (!= howFast 0) Forward else 0)
		)
		(alligator1 init: setScript: moveAlligator1)
		(alligator2 init: setScript: moveAlligator2)
		(ego init: view: 500 x: 313 y: 171 loop: 1 setCycle: Walk)
		(HandsOff)
		(curRoom setScript: walkIntoCastle)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((!= (menace x?) (ego x?))
				(menace
					view: 80
					loop: (ego loop?)
					x: (ego x?)
					cel: (ego cel?)
					y: (+ (ego y?) 20)
				)
			)
			((== (menace x?) (ego x?))
				(menace
					view: 81
					loop: (ego loop?)
					x: (ego x?)
					cel: 0
					y: (+ (ego y?) 20)
				)
			)
		)
	)
)

(instance walkIntoCastle of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== state 1)
					(== (ego y?) 136)
					(== (castleDoor cel?) 1)
				)
				(ego hide:)
				(= cycles 1)
			)
			((and (< (ego y?) 150) (> (ego y?) 138)) (ego priority: 10 signal: 16))
			((> (ego y?) 150) (ego priority: -1 signal: 0))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(Print 502 0 #at 25 20 #width 260 #mode 1 #dispose)
				(DisplayOldGraphics 39 165)
				(castleDoor ignoreActors: TRUE)
				(ego illegalBits: 0 setMotion: DPath 229 171 229 136)
			)
			(2 (= cycles 1))
			(3
				(Print 502 1 #at 25 20 #width 260 #mode 1 #dispose)
				(= seconds 6)
			)
			(4
				(cls)
				(curRoom setScript: changeEgo)
				(self dispose:)
			)
		)
	)
)

(instance changeEgo of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== state 0) (== (glow cel?) 3)) (glowDoor show:))
			((and (== state 0) (== (glow cel?) 4)) (glowDoor hide:))
			((and (== state 3) (== (castleDoor cel?) 1)) (ego show: view: 0 loop: 2 setCycle: StopWalk 2))
			((and (== state 12) (< (ego x?) 60)) (self changeState: 13))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glow init:)
				(glowDoor init: hide:)
				(if local0
					(glow setCycle: EndLoop self)
				else
					(self changeState: 2)
				)
			)
			(1
				(if local0
					(= local0 (- local0 1))
					(self changeState: 0)
				)
			)
			(2
				(glow hide: setCycle: 0)
				(glowDoor hide:)
				(= seconds 1)
			)
			(3
				(castleDoor setCycle: EndLoop self)
			)
			(4
				(Print 502 2 #at 25 20 #width 260 #mode 1 #dispose)
				(ego setMotion: MoveTo 229 145)
				(= seconds 2)
			)
			(5
				(ego view: 3 loop: 0 cycleSpeed: 4 setCycle: EndLoop)
				(= seconds 4)
			)
			(6
				(cls)
				(ego
					view: 15
					illegalBits: 0
					priority: 15
					signal: 16
					cycleSpeed: 1
					setCycle: CycleTo 5 1 self
				)
			)
			(7
				(ego cel: 6 setMotion: JumpTo 160 108 self)
				(= cycles 3)
			)
			(8
				(curRoom picture: 1 style: 7 drawPic: 1)
				(alligator1 dispose: delete:)
				(alligator2 dispose: delete:)
				(castleDoor dispose: delete:)
				(flags dispose: delete:)
				(gate init: stopUpd:)
				(g1 init: stopUpd:)
				(g2 init: stopUpd:)
				(urn1 init: stopUpd:)
				(urn2 init: stopUpd:)
				(if (!= howFast 0)
					(monsterHead1
						init:
						hide:
						setPri: 4
						setScript: monsterLeftRight
					)
					(monsterTail1 init: setPri: 4 hide:)
				)
				(monsterPeek
					init:
					hide:
					setPri: 4
					setScript: monsterLookScript
				)
			)
			(9
				(DisplayNewGraphics)
				(ego setCycle: BegLoop self)
			)
			(10
				(Print 502 3 #at 25 20 #width 260 #mode 1 #dispose)
				(ego view: 3 cycleSpeed: 2 setCycle: EndLoop self)
				(if (!= howFast 0)
					(menace
						view: 80
						setPri: 0
						loop: (ego loop?)
						x: (ego x?)
						cel: (ego cel?)
						y: (+ (ego y?) 20)
						init:
					)
				)
			)
			(11
				(ego
					view: 0
					setLoop: -1
					loop: 2
					setCycle: StopWalk 2
					cycleSpeed: 0
				)
				(= seconds 5)
			)
			(12
				(ego setMotion: MoveTo 30 106)
			)
			(13
				(cls)
				(ego setMotion: 0)
				(if (!= howFast 0) (poofReflect init: setPri: 1))
				(poof
					init:
					x: (ego x?)
					y: (ego y?)
					priority: (+ (ego priority?) 1)
					signal: 16
					setCycle: EndLoop self
				)
			)
			(14
				(if (!= howFast 0) (menace dispose:))
				(ego hide:)
				(poof setCycle: BegLoop self)
			)
			(15
				((ScriptID 0 6) loop: 1 fade:)
				(curRoom newRoom: 551)
				(self dispose:)
			)
		)
	)
)

(instance moveAlligator1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(alligator1
					setCycle: 0
					cel: 0
					setMotion: MoveTo (Random 82 296) 184 self
				)
				(= cycles (Random (Random 8 20) (Random 25 30)))
			)
			(1
				(= cycles 0)
				(alligator1 setCycle: Forward setMotion: 0)
				(= cycles (Random (Random 4 9) (Random 12 15)))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance moveAlligator2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(alligator2
					setCycle: 0
					cel: 0
					setMotion: MoveTo (Random 82 296) 186 self
				)
				(= cycles (Random (Random 8 20) (Random 25 30)))
			)
			(1
				(= cycles 0)
				(alligator2 setCycle: Forward setMotion: 0)
				(= cycles (Random (Random 4 9) (Random 12 15)))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance monsterLeftRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 (Random 0 1))
				(monsterHead1 x: (Random 60 260) y: (Random 150 166))
				(= cycles 1)
			)
			(1
				(monsterHead1
					show:
					setLoop: local2
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(monsterTail1
					x: (monsterHead1 x?)
					y: (monsterHead1 y?)
					cycleSpeed: 1
				)
				(monsterHead1
					setLoop: (+ local2 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(monsterHead1
					x: (+ (monsterHead1 x?) (- (* local2 120) 60))
					setLoop: local2
					setCel: 0
					setCycle: EndLoop
				)
				(monsterTail1
					show:
					setLoop: (+ local2 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(monsterHead1
					setLoop: (+ local2 2)
					setCel: 0
					setCycle: Forward
				)
				(monsterTail1
					setLoop: (+ local2 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(monsterHead1 hide:)
				(monsterTail1
					x: (monsterHead1 x?)
					setLoop: (+ local2 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(monsterTail1
					setLoop: (+ local2 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(monsterTail1 setCel: 0 hide:)
				(self cue:)
			)
			(8 (self init:))
		)
	)
)

(instance monsterLookScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 25 150)))
			(1
				(monsterPeek
					posn: (Random 50 270) (Random 145 155)
					show:
					setLoop: 2
					setCel: 0
					cycleSpeed: (Random 1 2)
					setCycle: EndLoop self
				)
			)
			(2
				(= local1 (Random 0 1))
				(monsterPeek setLoop: local1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(if (= local3 (Random 0 1))
					(monsterPeek
						setLoop: (+ local1 3)
						setCel: 0
						setCycle: EndLoop
					)
					(= cycles (Random 40 60))
				else
					(self cue:)
				)
			)
			(4
				(monsterPeek setCycle: BegLoop self)
			)
			(5
				(if local3
					(monsterPeek
						setLoop: local1
						setCel: (monsterPeek lastCel:)
						setCycle: BegLoop self
					)
				else
					(self cue:)
				)
			)
			(6
				(monsterPeek
					setLoop: 2
					setCel: (monsterPeek lastCel:)
					setCycle: BegLoop self
				)
			)
			(7
				(monsterPeek hide:)
				(self init:)
			)
		)
	)
)

(instance flags of Actor
	(properties
		x 271
		y 18
		view 597
		cel 2
		moveSpeed 3
	)
)

(instance castleDoor of Prop
	(properties
		x 229
		y 136
		view 509
		priority 10
		signal fixPriOn
	)
	
	(method (doit)
		(super doit:)
		(if cycler
			(castleDoor forceUpd:)
		else
			(castleDoor stopUpd:)
		)
		(cond 
			(
				(and
					(< (ego y?) 150)
					(> (ego y?) 138)
					(== (ego loop?) 3)
					(== (castleDoor cel?) 0)
					(!= (curRoom script?) changeEgo)
				)
				(cond 
					((!= (curRoom script?) walkIntoCastle) (self setCycle: 0))
					((!= (castleDoor cel?) 0) (self setCycle: 0))
					(else (self setCycle: EndLoop))
				)
			)
			(
				(or
					(and
						(< (ego y?) 138)
						(== (castleDoor cel?) 2)
						(== (curRoom script?) walkIntoCastle)
					)
					(and
						(> (ego y?) 138)
						(== (ego loop?) 2)
						(== (castleDoor cel?) 2)
					)
				)
				(self setCycle: BegLoop)
			)
		)
	)
)

(instance alligator1 of Actor
	(properties
		x 198
		y 187
		view 607
		cel 2
	)
)

(instance alligator2 of Actor
	(properties
		x 107
		y 185
		view 607
		loop 2
		cel 2
	)
)

(instance glow of Prop
	(properties
		x 173
		y 86
		view 511
		priority 15
		signal fixPriOn
		cycleSpeed 1
	)
)

(instance glowDoor of Prop
	(properties
		x 229
		y 136
		view 512
		priority 15
		signal fixPriOn
	)
)

(instance g1 of View
	(properties
		x 203
		y 87
		view 186
	)
)

(instance g2 of View
	(properties
		x 116
		y 87
		view 186
		cel 1
	)
)

(instance gate of Actor
	(properties
		x 159
		y 78
		yStep 1
		view 201
		priority 3
		signal fixPriOn
		illegalBits $0000
	)
)

(instance monsterPeek of Prop
	(properties
		view 108
	)
)

(instance monsterHead1 of Prop
	(properties
		x 45
		y 145
		view 107
		loop 1
	)
)

(instance monsterTail1 of Prop
	(properties
		x 45
		y 145
		view 107
		loop 5
	)
)

(instance poof of Prop
	(properties
		z 26
		view 265
	)
	
	(method (doit)
		(super doit:)
		(poofReflect
			loop: 1
			cel: (poof cel?)
			x: (ego x?)
			y: (+ (ego y?) 26)
		)
	)
)

(instance poofReflect of Prop
	(properties
		view 265
		loop 1
	)
)

(instance menace of Actor
	(properties)
)

(instance urn1 of View
	(properties
		x 35
		y 103
		view 267
	)
)

(instance urn2 of View
	(properties
		x 292
		y 103
		view 267
	)
)
