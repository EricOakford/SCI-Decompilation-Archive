;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use Intrface)
(use Block)
(use ForCount)
(use LoadMany)
(use Follow)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm51 0
)

(local
	dragonTriedToKill
	local1
	i
	[aPuff 10]
	[puffX 10] = [102 126 100 184 43 195 34 173 33 31]
	[puffY 10] = [113 87 53 67 78 102 95 129 122 67]
	[puffPri 20] = [10 8 12 10 8 3 12 11 10 14 14 14 14 14 14 14 14 14 3 3]
)
(procedure (CantWhileInvisible)
	(Print 51 66)
)

(instance rockBlock of Block
	(properties
		top 108
		left 34
		bottom 131
		right 105
	)
)

(instance rm51 of Room
	(properties
		picture 51
		east 52
		west 50
		picAngle 60
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(west WIPERIGHT)
				(east WIPELEFT)
			)
		)
		(LoadMany SOUND 37 66)
		(if (not (Btst fDragonDoused))
			(LoadMany VIEW 22 146 145)
			(LoadMany SOUND 64 63)
			(if (ego has: iSlingshot)
				(Load SCRIPT 784)
				(Load VIEW 41)
			)
			(if (and (Btst fWaterInBucket) (ego has: iWaterBucket))
				(LoadMany VIEW 50 148)
				(Load SOUND 63)
			)
			(if (ego has: iDagger)
				(LoadMany VIEW 51 147)
				(LoadMany SOUND 65 18)
			)
		)
		(super init:)
		(SolvePuzzle fEnteredDragonDen 1)
		(switch prevRoomNum
			(east
				(ego posn: 315 140)
			)
			(west
				(ego posn: 13 130)
			)
			(else
				(ego posn: 13 130)
			)
		)
		(ego init:)
		(NormalEgo)
		(if (not (Btst fGotMirror))
			(mirror setPri: 2 init: stopUpd:)
			(if (>= howFast 1) (mirror setScript: flashMirror))
		)
		(if (Btst fDragonDoused)
			(addToPics add: rock eachElementDo: #init doit:)
			(ego observeBlocks: rockBlock)
			(if (not (Btst fGotMirror))
				(mirror posn: 134 90)
			)
		else
			(dragBod
				priority: 1
				signal: (| (dragBod signal?) (| ignrAct fixPriOn))
				init:
			)
			(if (Btst fDragonDead)
				(dragHead view: 147 loop: 2 cel: 1 posn: 102 145 init:)
				(addToPics
					add: dragHead dragKneck
					eachElementDo: #init
					doit:
				)
			else
				(dragHead init: stopUpd:)
				(smoke init: setCycle: Forward)
			)
			(rock x: 1 y: 134 init:)
			(addToPics doit:)
			(ego illegalBits: cYELLOW)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(if (== nRoom west)
					((ScriptID 0 23) loop: 1 fade:)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'throw,use/water,bucket')
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((not (ego has: iWaterBucket))
						(DontHave)
					)
					((not (Btst fWaterInBucket))
						(Print 51 0)
					)
					((Btst fDragonDead)
						(Print 51 1)
					)
					((Btst fDragonDoused)
						(Print 51 2)
					)
					((curRoom script?)
						(CantDo)
					)
					((< (ego x?) 130)
						(Print 51 3)
					)
					(else
						(curRoom setScript: throwWater)
					)
				)
			)
			((or (Said 'look,check/boulder,boulder') (MousedOn rock event shiftDown))
				(Print 51 4)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(cond 
							((and (Btst fDragonDead) (Btst fGotMirror))
								(Print 51 5)
							)
							((Btst fDragonDead)
								(Print 51 6)
							)
							((and (Btst fDragonDoused) (Btst fGotMirror))
								(Print 51 7)
							)
							((Btst fDragonDoused)
								(Print 51 8)
							)
							((Btst fGotMirror)
								(Print 51 9)
							)
							(else
								(Print 51 10)
							)
						)
					)
					((Said '/stalactite,stalactite')
						(Print 51 11)
					)
					((Said '/doorway')
						(cond 
							((and (< (ego x?) 160) (== (ego loop?) 1))
								(Print 51 12)
							)
							((and (>= (ego x?) 160) (== (ego loop?) 0))
								(Print 51 13)
							)
							(else
								(Print 51 14)
							)
						)
					)
					((Said '/dragon')
						(if (Btst fDragonDead)
							(Print 51 15)
						else
							(Print 51 16)
						)
					)
					((Said '/tongue')
						(cond 
							((Btst fDragonDead)
								(Print 51 17)
							)
							((Btst fDragonDoused)
								(Print 51 18)
							)
							(else
								(Print 51 19)
							)
						)
					)
				)
			)
			((Said 'get,take/mirror')
				(cond 
					((Btst fGotMirror)
						(Print 51 20)
					)
					((curRoom script?)
						(CantDo)
					)
					(
						(not
							(ego
								inRect:
									(- (mirror x?) 12)
									(- (mirror y?) 1)
									(+ (mirror x?) 12)
									(+ (mirror y?) 15)
							)
						)
						(CantDo)
					)
					(else
						((ScriptID 0 21) number: 66 init: play:)
						(Print 51 21)
						(ego get: iMagicMirror)
						(SolvePuzzle fGotMirror 8)
						(mirror dispose:)
					)
				)
			)
			((Said 'get,take/tongue')
				(cond 
					((Btst fDragonDead)
						(Print 51 22)
					)
					((Btst fDragonDoused)
						(Print 51 23)
					)
					(else
						(Print 51 19)
					)
				)
			)
			((Said 'move,pull/boulder,boulder')
				(Print 51 24)
			)
			((Said '/ring>')
				(cond 
					((or (Said 'remove') (Said 'take<off'))
						(cond 
							((== (ego view?) (if (Btst fLittleEgo) 23 else 16))
								(CantDo)
							)
							((not (Btst fWearingRing))
								(Print 51 25)
							)
							(else
								(Print 51 26)
								(if (and (== curRoomNum 51) (< (ego x?) 130))
									(Print 51 27)
								)
								(if (and (cast contains: theGoat) (ego has: iCarrot))
									(theGoat setMotion: Follow ego 60)
									(Bset fGoatFollows)
								)
								(Bclr fWearingRing)
								(Bclr fInvisible)
								(if (== (ego illegalBits?) cYELLOW)
									(NormalEgo)
									(ego illegalBits: cYELLOW)
								else
									(NormalEgo)
								)
								(event claimed: TRUE)
							)
						)
					)
					((Said 'rub')
						(cond 
							((Btst fInvisible)
								(Print 51 28)
							)
							((not invisibleRingTimer)
								(Print 51 29)
							)
							((Btst fWearingRing)
								(Print 51 30)
								(Bset fInvisible)
								(if (== (ego illegalBits?) cYELLOW)
									(NormalEgo)
									(ego illegalBits: cYELLOW)
								else
									(NormalEgo)
								)
							)
							(else
								(Print 51 31)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
			((Said 'give/[/dragon]')
				(cond 
					((Btst fDragonDoused)
						(Print 51 32)
					)
					((Btst fDragonDead)
						(Print 51 33)
					)
					((ego has: iMagicMirror)
						(Print 51 34)
					)
					(else
						(Print 51 35)
					)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(== (OnControl CMAP (event x?) (event y?)) cWHITE)
				)
				(if (> (event y?) 115)
					(Print 51 36)
				else
					(Print 51 37)
				)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 126
		y 135
		view 146
		loop 1
		cycleSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 38)
			)
		)
	)
)

(instance fireHead of Prop
	(properties
		view 145
		loop 1
		cycleSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 39)
			)
		)
	)
)

(instance dragBod of PicView
	(properties
		x 117
		y 126
		description {dragon}
		view 251
		loop 3
	)
	
	(method (dispose)
		(features delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 40)
			)
		)
	)
)

(instance dragTail of Actor
	(properties
		x 51
		y 131
		description {dragon}
		yStep 1
		view 146
		signal (| ignrAct fixedLoop stopUpdOn) ;$5801
		illegalBits $0000
		xStep 6
		moveSpeed 1
	)
)

(instance fireBall of Actor
	(properties
		description {fire ball}
		yStep 6
		view 146
		xStep 8
	)
)

(instance projectile of Actor
	(properties
		description {dagger}
		yStep 8
		view 51
		loop 2
		xStep 10
	)
)

(instance puff of Prop
	(properties
		description {steam}
		view 251
		loop 1
	)
)

(instance rock of PicView
	(properties
		x 69
		y 127
		view 251
	)
	
	(method (dispose)
		(features delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent)
	)
)

(instance mirror of Prop
	(properties
		x 149
		y 104
		view 251
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'look,check<in/mirror')
				(if (< (ego distanceTo: self) 10)
					(Face ego mirror)
					(event claimed: FALSE)
				else
					(Print 51 41)
				)
			)
			((or (Said 'look,check/mirror') (MousedOn self event shiftDown))
				(Print 51 42)
				(event claimed: TRUE)
			)
			((super handleEvent: event)
				(return)
			)
		)
	)
)

(instance dragKneck of View
	(properties
		x 41
		y 90
		view 147
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 43)
			)
		)
	)
)

(instance dragHead of Prop
	(properties
		x 41
		y 90
		description {dragon's head}
		view 145
		signal stopUpdOn
		cycleSpeed 1
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(or
					script
					(Btst fDragonDead)
					(Btst fDragonDoused)
					(== (curRoom script?) daggerDrag)
				)
				0
			)
			(
				(and
					(> (ego x?) 266)
					(== (dragHead cel?) (- (NumCels dragHead) 1))
					(not (Btst fInvisible))
				)
				(self setScript: lowerHead)
			)
			(
				(and
					(< (ego x?) 266)
					(<= (dragHead cel?) 1)
					(not (Btst fInvisible))
				)
				(if (not local1)
					(= local1 1)
					(smoke hide:)
				)
				(self setScript: raiseHead)
			)
			(
				(and
					(< 130 (ego x?))
					(< (ego x?) 208)
					(not (Btst fInvisible))
					(not dragonTriedToKill)
				)
				(if (!= (curRoom script?) daggerDrag)
					(self setScript: flameEgo)
				)
			)
			((and dragonTriedToKill (> (ego x?) 208))
				(= dragonTriedToKill 0)
			)
			((not haloTimer)
				(= dragonTriedToKill 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(Said 'look,check/dragon')
					(Said 'look,check/head[<dragon]')
					(Said 'look,check/body[<dragon]')
					(MousedOn self event shiftDown)
					(MousedOn dragBod event shiftDown)
				)
				(cond 
					((Btst fDragonDead)
						(Print 51 44)
					)
					((and (Btst fDragonDoused) (Btst fGotMirror))
						(Print 51 7)
					)
					((Btst fDragonDoused)
						(Print 51 8)
					)
					((Btst fGotMirror)
						(if (<= (dragHead cel?) 1)
							(Print 51 45)
						else
							(Print 51 46)
						)
					)
					(else
						(Print 51 40)
						(Print 51 10)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'play/fiddle')
				(if (ego has: iFiddle)
					(Print 51 47)
				else
					(event claimed: FALSE)
				)
			)
			(
			(or (Said 'throw,use/dagger') (Said 'kill,stab/dragon'))
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((Btst fDragonDead)
						(Print 51 48)
					)
					((not (ego has: iDagger))
						(Print 51 49)
					)
					((curRoom script?)
						(CantDo)
					)
					((not (dragHead script?))
						(if (< (ego x?) 130)
							(Print 51 3)
						else
							(curRoom setScript: daggerDrag)
						)
					)
					(else
						(Print 51 50)
						(ego setMotion: 0)
						(curRoom setScript: 0)
					)
				)
			)
			((Said 'shoot/dragon')
				(cond 
					((or (not (ego has: iSlingshot)) (not (ego has: iPebbles)))
						(Print 51 51)
					)
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((Btst fDragonDead)
						(Print 51 52)
					)
					(else
						(Print 51 53)
					)
				)
			)
			((or (Said 'talk,speak[/dragon]') (Said '/hello') (Said 'hello,say'))
				(if (Btst fDragonDead)
					(Print 51 54)
				else
					(Print 51 55)
				)
			)
			((Said 'get,take/dagger')
				(if (Btst fDragonDead)
					(Print 51 56)
				else
					(Print 51 57)
				)
			)
			((Said '/dragon')
				(if (Btst fDragonDead)
					(Print 51 58)
				else
					(Print 51 59)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance flameEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(smoke hide: stopUpd:)
				(ego stopUpd:)
				(dragHead stopUpd:)
				(if (== (dragHead cel?) (- (NumCels dragHead) 1))
					(fireHead posn: 97 111)
				else
					(fireHead posn: 104 149)
				)
				(fireHead
					init:
					ignoreActors:
					cycleSpeed: 2
					cel: 0
					setPri: 12
					setCycle: EndLoop self
				)
				(mirror cel: 0 setScript: 0 stopUpd:)
			)
			(1
				(fireHead dispose:)
				(fireBall
					init:
					setLoop: (if (< (ego y?) 142) 3 else 2)
					ignoreActors:
					ignoreHorizon:
					setPri: 4
					illegalBits: 0
					setCycle: Forward
				)
				(if (== (dragHead cel?) (- (NumCels dragHead) 1))
					(fireBall posn: 126 102)
				else
					(fireBall posn: 136 144)
				)
				(fireBall
					setMotion: MoveTo (- (ego x?) 12) (- (ego y?) 20) self
				)
			)
			(2
				(if (not haloTimer)
					((ScriptID 0 21) number: 37 init: play:)
					(ego
						setLoop: 2
						cel: 0
						view: 22
						cycleSpeed: 1
						setCycle: Forward
					)
					(fireBall dispose:)
					(= cycles 22)
				else
					(fireBall dispose:)
					(self changeState: 6)
				)
			)
			(3
				(ego setLoop: 0 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 1 cel: 0 setCycle: Forward)
				(= seconds 6)
			)
			(5
				(if (ego has: iMagicShield)
					(EgoDead
						{Your shield melted from the intensity of the dragon's flames!__Looks like the dragon made an ash out of you.}
					)
				else
					(EgoDead
						{By venturing too close to the dragon's flame, you made an ash out of yourself.}
					)
				)
				(self changeState: 7)
			)
			(6
				(Print 51 60)
				(= dragonTriedToKill TRUE)
				(HandsOn)
				(self cue:)
			)
			(7
				(self dispose:)
			)
		)
	)
)

(instance raiseHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dragHead setCycle: EndLoop self)
				(smoke hide: stopUpd:)
			)
			(1
				(dragHead stopUpd:)
				(smoke posn: 119 98 show: startUpd:)
				(self dispose:)
			)
		)
	)
)

(instance lowerHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dragHead setCycle: CycleTo 1 -1 self)
				(smoke hide: stopUpd:)
			)
			(1
				(dragHead stopUpd:)
				(smoke posn: 126 135 show: startUpd:)
				(self dispose:)
			)
		)
	)
)

(instance daggerDrag of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego put: 0)
				(if (<= (dragHead cel?) 1)
					(dragHead setCycle: EndLoop self)
					(smoke hide: stopUpd:)
				else
					(= cycles 1)
				)
			)
			(1
				(dragHead stopUpd:)
				(ego
					view: 51
					cel: 0
					loop: (if (< (ego x?) 130) 0 else 1)
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				((ScriptID 0 21) number: 65 loop: 1 init: play:)
				(ego setCycle: EndLoop)
				(projectile
					init:
					setLoop: 2
					cel: 2
					setCycle: Forward
					illegalBits: 0
					ignoreActors:
				)
				(if (ego loop?)
					(projectile
						posn: (- (ego x?) 19) (- (ego y?) 41)
						setMotion: MoveTo (+ (dragHead x?) 40) (+ (dragHead y?) 23) self
					)
				else
					(projectile
						posn: (+ (ego x?) 20) (- (ego y?) 36)
						setMotion: MoveTo 330 118 self
					)
				)
			)
			(3
				(ego stopUpd:)
				(projectile dispose:)
				(if (ego loop?)
					(SolvePuzzle fDragonDead 3)
					(smoke hide:)
					((ScriptID 0 21) number: 18 loop: 1 play:)
					(dragHead
						view: 147
						cel: 0
						loop: 0
						cycleSpeed: (if (>= howFast 1) 2 else 0)
						setCycle: ForwardCounter 2 self
					)
				else
					(Print 51 61)
					(= local1 1)
					(smoke hide:)
					(NormalEgo)
					(ego illegalBits: cYELLOW)
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(dragHead setCycle: BegLoop)
				(= cycles 4)
			)
			(5
				((ScriptID 0 21) number: 59 init: play:)
				(dragHead loop: 2 cel: 1 posn: 102 145)
				(dragKneck init: stopUpd:)
				(smoke view: 147 loop: 1 setCel: 0 posn: 41 90 show:)
				(= cycles 3)
			)
			(6
				(dragHead posn: 102 144)
				(smoke cel: 1)
				(= cycles 3)
			)
			(7
				(dragHead posn: 102 145)
				(smoke cycleSpeed: 3 setCycle: EndLoop)
				(= cycles 3)
			)
			(8
				(ShakeScreen 3 1)
				(= cycles 1)
			)
			(9
				(dragHead stopUpd:)
				(smoke hide: stopUpd:)
				(= cycles 2)
			)
			(10
				(HandsOn)
				(Print 51 62)
				(NormalEgo)
				(ego illegalBits: cYELLOW)
				(self dispose:)
			)
		)
	)
)

(instance throwWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle fDragonDoused 5)
				(BucketState 0)
				(ego stopUpd:)
				(if (<= (dragHead cel?) 1)
					(dragHead setCycle: EndLoop)
					(smoke hide: stopUpd:)
				)
				(mirror cel: 0 setScript: 0 stopUpd:)
				(ego view: 50 cel: 0 loop: 0 setMotion: 0 setCycle: EndLoop)
				(= cycles 4)
			)
			(1
				(projectile
					init:
					view: 50
					setLoop: 2
					setCel: 0
					setCycle: 0
					illegalBits: 0
					setPri: (ego priority?)
					ignoreActors:
					xStep: (if (>= howFast 1) 10 else 15)
					posn: (- (ego x?) 30) (- (ego y?) 25)
					setMotion: MoveTo 108 103 self
				)
			)
			(2
				(ego stopUpd:)
				(projectile dispose:)
				(smoke dispose:)
				((ScriptID 0 21) number: 63 init: play:)
				(dragHead view: 148 loop: 0 cel: 0 setCycle: Forward)
				(= cycles 6)
			)
			(3
				(Print 51 63)
				(self cue:)
			)
			(4
				((= [aPuff i] (Clone puff))
					init:
					setPri: [puffPri i]
					x: [puffX i]
					y: [puffY i]
				)
				(if howFast
					([aPuff i] setCycle: EndLoop self)
				else
					([aPuff i] setCel: (puff lastCel:))
					(= cycles 1)
				)
			)
			(5
				(= cycles (+ 1 (* howFast 5)))
			)
			(6
				([aPuff i] addToPic:)
				(switch i
					(9
						(mirror posn: 134 90)
						(= cycles 10)
					)
					(2
						(dragHead stopUpd:)
						(++ i)
						(self changeState: 4)
					)
					(else 
						(++ i)
						(self changeState: 4)
					)
				)
			)
			(7
				(curRoom drawPic: 51 DISSOLVE)
				(rock x: 69 y: 127 init:)
				(addToPics doit:)
				(ego observeBlocks: rockBlock)
				(dragHead dispose:)
				((ScriptID 0 23) loop: 1 fade:)
				(dragTail init: setMotion: MoveTo -45 131 self)
			)
			(8
				(if (not (ego has: iMagicMirror))
					(Print 51 64)
				else
					(Print 51 65)
				)
				(NormalEgo)
				(ego observeBlocks: rockBlock loop: 1)
				(if (and (not (ego has: iMagicMirror)) (>= howFast 1))
					(mirror setScript: flashMirror)
				)
				(dragTail dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance flashMirror of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (mirror setCycle: EndLoop self))
			(1
				(mirror stopUpd:)
				(= cycles (Random 40 60))
			)
			(2 (self changeState: 0))
		)
	)
)
