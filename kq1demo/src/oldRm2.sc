;;; Sierra Script 1.0 - (do not remove this comment)
(script# rOldRm2)
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
	glowCycles
	monsterPeekLoop
	monsterLoop
	peekABoo
)
(instance oldRm2 of Room
	(properties
		picture rOldRm2
		style WIPELEFT
		east rOldRm2
		west 2
	)
	
	(method (init)
		(LoadMany VIEW
			vEgoLookAround
			vFlagsAGI
			vAlligator
			vDoorAGI
			vGlowAGI
			vCastleGuards
			vMoatMonster
			vMoatMonsterPeek
			vEgo
			vPoof
			vEgoJumping
			vGlowDoorAGI
			vEgoReflection
			vEgoReflectionStand
			vEgoAGI
			vUrn
		)
		(Load SOUND 1)
		(Load PICTURE rCastleGate)
		(super init:)
		(= glowCycles 3)
		(castleDoor init:)
		(flags
			init:
			setCycle: (if (!= howFast slow) Forward else 0)
		)
		(alligator1 init: setScript: moveAlligator1)
		(alligator2 init: setScript: moveAlligator2)
		(ego
			init:
			view: vEgoAGI
			x: 313
			y: 171
			loop: loopW
			setCycle: Walk
		)
		(HandsOff)
		(curRoom setScript: walkIntoCastle)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((!= (menace x?) (ego x?))
				(menace
					view: vEgoReflection
					loop: (ego loop?)
					x: (ego x?)
					cel: (ego cel?)
					y: (+ (ego y?) 20)
				)
			)
			((== (menace x?) (ego x?))
				(menace
					view: vEgoReflectionStand
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
			((and (< (ego y?) 150) (> (ego y?) 138))
				(ego
					priority: 10
					signal: fixPriOn
				)
			)
			((> (ego y?) 150)
				(ego priority: -1 signal: 0)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(Print 502 0
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
				(DisplayOldGraphics 39 165)
				(castleDoor ignoreActors: TRUE)
				(ego
					illegalBits: 0
					setMotion: DPath
						229 171
						229 136
				)
			)
			(2
				(= cycles 1)
			)
			(3
				(Print 502 1
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
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

	(method (doit)
		(super doit:)
		(cond 
			((and (== state 0) (== (glow cel?) 3))
				(glowDoor show:)
			)
			((and (== state 0) (== (glow cel?) 4))
				(glowDoor hide:)
			)
			((and (== state 3) (== (castleDoor cel?) 1))
				(ego
					show:
					view: vEgo
					loop: loopS
					setCycle: StopWalk vEgoStand
				)
			)
			((and (== state 12) (< (ego x?) 60))
				(self changeState: 13)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glow init:)
				(glowDoor init: hide:)
				(if glowCycles
					(glow setCycle: EndLoop self)
				else
					(self changeState: 2)
				)
			)
			(1
				(if glowCycles
					(-= glowCycles 1)
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
				(Print 502 2
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
				(ego setMotion: MoveTo 229 145)
				(= seconds 2)
			)
			(5
				(ego
					view: vEgoLookAround
					loop: 0
					cycleSpeed: 4
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(6
				(cls)
				(ego
					view: vEgoJumping
					illegalBits: 0
					priority: 15
					signal: fixPriOn
					cycleSpeed: 1
					setCycle: CycleTo 5 1 self
				)
			)
			(7
				(ego cel: 6 setMotion: JumpTo 160 108 self)
				(= cycles 3)
			)
			(8
				(curRoom
					picture: rCastleGate
					style: IRISOUT
					drawPic: rCastleGate
				)
				(alligator1 dispose: delete:)
				(alligator2 dispose: delete:)
				(castleDoor dispose: delete:)
				(flags dispose: delete:)
				(gate init: stopUpd:)
				(g1 init: stopUpd:)
				(g2 init: stopUpd:)
				(urn1 init: stopUpd:)
				(urn2 init: stopUpd:)
				(if (!= howFast slow)
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
				(Print 502 3
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
				(ego
					view: vEgoLookAround
					cycleSpeed: 2
					setCycle: EndLoop
					self
				)
				(if (!= howFast slow)
					(menace
						view: vEgoReflection
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
					view: vEgo
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
				(if (!= howFast slow)
					(poofReflect init: setPri: 1)
				)
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
				(if (!= howFast slow) (menace dispose:))
				(ego hide:)
				(poof setCycle: BegLoop self)
			)
			(15
				((ScriptID 0 6) loop: 1 fade:)
				(curRoom newRoom: dmDragon)
				(self dispose:)
			)
		)
	)
)

(instance moveAlligator1 of Script

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
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance moveAlligator2 of Script

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
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance monsterLeftRight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= monsterLoop (Random 0 1))
				(monsterHead1 x: (Random 60 260) y: (Random 150 166))
				(= cycles 1)
			)
			(1
				(monsterHead1
					show:
					setLoop: monsterLoop
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
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(monsterHead1
					x: (+ (monsterHead1 x?) (- (* monsterLoop 120) 60))
					setLoop: monsterLoop
					setCel: 0
					setCycle: EndLoop
				)
				(monsterTail1
					show:
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(monsterHead1
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: Forward
				)
				(monsterTail1
					setLoop: (+ monsterLoop 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(monsterHead1 hide:)
				(monsterTail1
					x: (monsterHead1 x?)
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(monsterTail1
					setLoop: (+ monsterLoop 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(monsterTail1 setCel: 0 hide:)
				(self cue:)
			)
			(8
				(self init:)
			)
		)
	)
)

(instance monsterLookScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 25 150))
			)
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
				(= monsterPeekLoop (Random 0 1))
				(monsterPeek setLoop: monsterPeekLoop setCel: 0 setCycle: EndLoop self)
			)
			(3
				(if (= peekABoo (Random 0 1))
					(monsterPeek
						setLoop: (+ monsterPeekLoop 3)
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
				(if peekABoo
					(monsterPeek
						setLoop: monsterPeekLoop
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
		view vFlagsAGI
		cel 2
		moveSpeed 3
	)
)

(instance castleDoor of Prop
	(properties
		x 229
		y 136
		view vDoorAGI
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
					((!= (curRoom script?) walkIntoCastle)
						(self setCycle: 0)
					)
					((!= (castleDoor cel?) 0)
						(self setCycle: 0)
					)
					(else
						(self setCycle: EndLoop)
					)
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
		view vAlligator
		cel 2
	)
)

(instance alligator2 of Actor
	(properties
		x 107
		y 185
		view vAlligator
		loop 2
		cel 2
	)
)

(instance glow of Prop
	(properties
		x 173
		y 86
		view vGlowAGI
		priority 15
		signal fixPriOn
		cycleSpeed 1
	)
)

(instance glowDoor of Prop
	(properties
		x 229
		y 136
		view vGlowDoorAGI
		priority 15
		signal fixPriOn
	)
)

(instance g1 of View
	(properties
		x 203
		y 87
		view vCastleGuards
	)
)

(instance g2 of View
	(properties
		x 116
		y 87
		view vCastleGuards
		cel 1
	)
)

(instance gate of Actor
	(properties
		x 159
		y 78
		yStep 1
		view vCastleGate
		priority 3
		signal fixPriOn
		illegalBits 0
	)
)

(instance monsterPeek of Prop
	(properties
		view vMoatMonsterPeek
	)
)

(instance monsterHead1 of Prop
	(properties
		x 45
		y 145
		view vMoatMonster
		loop 1
	)
)

(instance monsterTail1 of Prop
	(properties
		x 45
		y 145
		view vMoatMonster
		loop 5
	)
)

(instance poof of Prop
	(properties
		z 26
		view vPoof
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
		view vPoof
		loop 1
	)
)

(instance menace of Actor)

(instance urn1 of View
	(properties
		x 35
		y 103
		view vUrn
	)
)

(instance urn2 of View
	(properties
		x 292
		y 103
		view vUrn
	)
)
