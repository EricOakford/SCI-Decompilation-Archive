;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh)
(use Main)
(use Intrface)
(use rgClouds)
(use mwAvoider)
(use NewFeature)
(use Block)
(use LoadMany)
(use Wander)
(use Chase)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm58 0
)

(local
	giantTriedToKill
	local1
	local2
	local3
	local4
	egoInvisible
	egoHalo
	local7
	local8
)
(procedure (localproc_15d2)
	(= local7 0)
	(if (or (GiantSleeping) (GiantDead)) (return))
	(if (ego blocks?)
		(ego ignoreBlocks: sleepindeadGiant)
	)
	(if (or (ego has: iMagicShield) egoHalo egoInvisible)
		(GiantCantKill)
		(giant
			ignoreActors: 0
			setMotion: Wander 50
			setAvoider: mwAvoider
		)
		(= local7 0)
	else
		(giant
			setAvoider: mwAvoider
			setCycle: SyncWalk
			setMotion: Chase ego 15
		)
		(= local7 1)
		((ScriptID 0 21) number: 39 loop: -1 init: play:)
	)
)

(procedure (GiantCantKill)
	(if (not giantTriedToKill)
		(cond 
			(egoInvisible
				(Print 58 40)
			)
			((ego has: iMagicShield)
				(Print 58 41)
			)
			(egoHalo
				(Print 58 42)
			)
		)
		(= giantTriedToKill TRUE)
	)
)

(procedure (localproc_16bb &tmp giantX giantY)
	(= giantX (giant x?))
	(= giantY (giant y?))
	(= local2
		((Block new:)
			left: (- giantX 5)
			top: (- giantY 38)
			right: (+ giantX 5)
			bottom: (- giantY 32)
			init:
			yourself:
		)
	)
	(= local3
		((Block new:)
			left: (- giantX 11)
			top: (- giantY 31)
			right: (+ giantX 11)
			bottom: (- giantY 12)
			init:
			yourself:
		)
	)
	(= local4
		((Block new:)
			left: (- giantX 16)
			top: (- giantY 11)
			right: (+ giantX 16)
			bottom: (+ giantY 1)
			init:
			yourself:
		)
	)
	(ego observeBlocks: local2 local3 local4)
)

(procedure (AttackGiant)
	(cond 
		((GiantDead)
			(Print 58 43)
		)
		((Btst fInvisible)
			(Print 58 44)
		)
		((GiantSleeping)
			(Print 58 45)
		)
		((curRoom script?)
			(Print 58 46)
		)
		(else
			(return FALSE)
		)
	)
	(return TRUE)
)

(procedure (localproc_17e3 param1 param2)
	(return
		(==
			((inventory at: param1) owner?)
			(if (== argc 1) curRoomNum else param2)
		)
	)
)

(procedure (GiantHasChest)
	(return
		(==
			(giant view?)
			(+ 140 (if (localproc_17e3 1) 0 else 300))
		)
	)
)

(procedure (GiantSleeping)
	(return (== (giant view?) 142))
)

(procedure (GiantDead)
	(return (!= deadGiantX -1))
)

(instance rm58 of cloudRoom
	(properties
		picture 58
		east 59
		south 61
		west 57
		picAngle 60
	)
	
	(method (init)
		(LoadMany VIEW
			140 141 142 143 144
			(+ 140 (if (localproc_17e3 1) 0 else 300))
			(+ 144 (if (localproc_17e3 1) 0 else 300))
			141
			(+ 141 (if (localproc_17e3 1) 0 else 300))
			67 40 53 440 441 444
		)
		(LoadMany SOUND 76 77 59 19 18 75 39 66)
		(super init:)
		(StartCloudRoom
			0
			0
			(proc0_18 53 (ego x?) 0)
			188
			315
			(proc0_18 174 (proc0_17 189 (ego y?) 79) 143)
			11
			(proc0_17 188 (ego y?) 77)
			297
		)
		(= egoInvisible (Btst fInvisible))
		(= egoHalo (!= 0 haloTimer))
		(= giantTriedToKill 0)
		(ego init:)
		(NormalEgo)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(tree6 init:)
		(tree7 init:)
		(tree8 init:)
		(tree9 init:)
		(cloud init:)
		(giant init:)
		(chest init:)
		(localproc_15d2)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
			((== (giant script?) catchEgo) ((giant script?) doit:))
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			((giant script?) ((giant script?) doit:))
		)
		(if (not script)
			(if (!= egoInvisible (Btst fInvisible))
				(= egoInvisible (Btst fInvisible))
				(localproc_15d2)
			)
			(if (!= egoHalo (!= 0 haloTimer))
				(= egoHalo (> 0 haloTimer))
				(localproc_15d2)
			)
		)
	)
)

(instance giant of Actor
	(properties
		yStep 3
		cycleSpeed 2
		illegalBits $8060
		xStep 4
		moveSpeed 2
	)
	
	(method (init)
		(super init:)
		(cond 
			((GiantDead)
				(self
					view: 143
					loop: 1
					cel: 5
					posn: deadGiantX deadGiantY
					stopUpd:
				)
				(localproc_16bb)
			)
			((Btst fGiantSleeps)
				(giant
					view: 142
					loop: 0
					cel: 0
					posn: 112 158
					setCycle: 0
					setMotion: 0
					setAvoider: 0
					stopUpd:
				)
				(giantHead init:)
			)
			(else
				(= local1
					(cond 
						((not (>= howFast 1)) 250)
						((localproc_17e3 1) 1070)
						(else -1)
					)
				)
				(self
					view: (+ 140 (if (localproc_17e3 1) 0 else 300))
					ignoreActors:
					posn: 134 118
					setCycle: SyncWalk
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (> local1 0) (== (-- local1) 20))
			((ScriptID 0 21) fade:)
		)
		(cond 
			(
				(and
					(GiantHasChest)
					(not local1)
					(not (curRoom script?))
				)
				(-- local1)
				(giant setScript: sleepGiant)
			)
			(
				(and
					(!= (giant script?) catchEgo)
					(!= (curRoom script?) useSling)
					(GiantHasChest)
				)
				(if
					(and
						(OneOf
							(ego view?)
							0
							4
							7
							2
							(if (Btst fLittleEgo) 23 else 16)
							(if (Btst fLittleEgo) 17 else 15)
						)
						(or
							(OneOf (giant script?) 0 sleepGiant)
							(OneOf (ego view?) 32 23 17)
						)
						(!= (ego view?) 38)
						(!= (ego view?) 39)
						(< (self distanceTo: ego) 20)
					)
					(if local7
						(giant setScript: catchEgo)
					else
						(GiantCantKill)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/giant') (MousedOn self event))
				(event claimed: TRUE)
				(cond 
					((localproc_17e3 1)
						(cond 
							((GiantDead)
								(Print 58 0)
							)
							((GiantSleeping)
								(Print 58 1)
							)
							(else
								(Print 58 2)
							)
						)
					)
					((GiantDead)
						(Print 58 3)
					)
					((GiantSleeping)
						(Print 58 4)
					)
					(else
						(Print 58 5)
					)
				)
			)
			(
			(or (Said 'talk,speak,say[/*][/*]') (Said 'hello'))
				(cond 
					((GiantDead)
						(Print 58 6)
					)
					((GiantSleeping)
						(Print 58 7)
					)
					(else
						(Print 58 8)
					)
				)
			)
			((Said 'wake/giant')
				(cond 
					((GiantDead)
						(Print 58 9)
					)
					((GiantSleeping)
						(Print 58 7)
					)
					(else
						(Print 58 10)
					)
				)
			)
			((Said 'tickle/giant')
				(if (GiantDead)
					(Print 58 11)
				else
					(Print 58 12)
				)
			)
			((Said 'stab/giant')
				(if (ego has: iDagger)
					(Print 58 13)
				else
					(Print 58 14)
				)
			)
			(
				(or
					(Said 'use/shot')
					(Said 'kill/giant/shot')
					(Said 'drop/pebble/shot')
					(Said 'shoot/giant[/shot]')
				)
				(cond 
					((AttackGiant))
					((not (ego has: iSlingshot))
						(Print 58 15)
					)
					((not (ego has: iPebbles))
						(Print 58 16)
					)
					((== (giant script?) sleepGiant)
						(Print 58 17)
					)
					(else
						(curRoom setScript: useSling)
					)
				)
			)
			((Said 'kill/giant>')
				(cond 
					((AttackGiant)
						(event claimed: TRUE)
					)
					((Said '/dagger')
						(if (ego has: iDagger)
							(self setScript: (ScriptID 780 0))
						else
							(Print 58 18)
						)
					)
					(else
						(Print 58 19)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'throw,throw/boulder,pebble[/giant]')
				(if (GiantSleeping)
					(Print 58 20)
				)
			)
			((Said 'give/*[/giant,man]')
				(cond 
					((== (giant view?) 143)
						(Print 58 21)
					)
					((ego has: iChest)
						(Print 58 22)
					)
					(else
						(Print 58 23)
					)
				)
			)
			((Said 'kiss/giant')
				(if (not (localproc_17e3 1))
					(Print 58 24)
				else
					(Print 58 25)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance giantHead of Prop
	(properties
		z 22
		description {giant}
		view 142
		loop 1
		cycleSpeed 4
	)
	
	(method (init)
		(super init:)
		(self posn: (giant x?) (giant y?) setScript: snorer)
	)
)

(instance snorer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(giantHead cel: 0 setCycle: CycleTo 3 1)
				((ScriptID 0 21) number: 76 loop: 1 play: self)
			)
			(1 (= cycles 10))
			(2
				(giantHead setCycle: EndLoop)
				((ScriptID 0 21) number: 77 play: self)
			)
			(3 (= cycles 30))
			(4 (self changeState: 0))
		)
	)
)

(instance chest of View
	(properties
		view 142
		cel 1
	)
	
	(method (init)
		(if (not (localproc_17e3 1)) (return))
		(super init:)
		(if (GiantHasChest)
			(self hide:)
		else
			(self posn: (+ (giant x?) 1) (- (giant y?) 2) show:)
		)
		(self stopUpd:)
		(cast delete: self addToFront: self)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((super handleEvent: event)
					(return TRUE)
				)
				((Said 'look,check/chest')
					(self doVerb: verbLook)
				)
				((Said 'look,check/grass')
					(cond 
						((GiantHasChest)
							(Print 58 26)
						)
						((> (ego distanceTo: self) 20)
							(Print 58 27)
						)
						(else
							(Print 58 28)
						)
					)
				)
				((Said 'get,take/chest')
					(self doVerb: verbGet)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((GiantHasChest)
						(Print 58 29)
					)
					((and (GiantDead) (> (ego distanceTo: self) 20))
						(Print 58 30)
					)
					((and (GiantSleeping) (> (ego distanceTo: self) 20))
						(Print 58 27)
					)
					(else
						(Print 58 31)
					)
				)
			)
			(verbGet
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((GiantHasChest)
						(Print 58 32)
					)
					((> (ego distanceTo: self) 20)
						(CantReach)
					)
					(else
						(curRoom setScript: getChest)
					)
				)
			)
		)
	)
)

(instance catchEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face giant ego)
				(self cue:)
			)
			(1
				((ScriptID 0 21) fade:)
				(giant ignoreControl: 64)
				(if (== (giant loop?) 2)
					(giant setMotion: MoveTo (ego x?) (- (ego y?) 9) self)
				else
					(ego hide:)
					(giant
						cycleSpeed: 1
						view: (+ 144 (if (localproc_17e3 1) 0 else 300))
						loop: (if (== (giant loop?) 1) 1 else 0)
						cel: 0
						setCycle: EndLoop self
					)
					(if (== (giant loop?) 3) (- (giant y?) 9))
				)
			)
			(2
				(if (!= (ego view?) 63)
					(ego hide:)
					(giant
						view: (+ 141 (if (localproc_17e3 1) 0 else 300))
						cel: (if (== (giant loop?) 2) 3 else 0)
						y: (+ (giant y?) 6)
						loop: 0
						cycleSpeed: 1
						setMotion: 0
						setAvoider: 0
						setCycle: CycleTo 10 1 self
					)
				else
					(self dispose:)
				)
			)
			(3
				((ScriptID 0 21) number: 59 loop: 1 play:)
				(= cycles 1)
			)
			(4
				(ShakeScreen 6)
				(giant setCycle: EndLoop self)
			)
			(5
				(ego
					view: 141
					loop: 1
					cel: 0
					setPri: 0
					posn: (giant x?) (giant y?)
					stopUpd:
					show:
				)
				(giant
					view: (+ 140 (if (localproc_17e3 1) 0 else 300))
					loop: 0
					cel: 0
					setCycle: SyncWalk
					cycleSpeed: 2
					setMotion: Wander
					setAvoider: mwAvoider
				)
				(= seconds 3)
			)
			(6
				(HandsOn)
				(EgoDead 58 33)
				(self dispose:)
			)
		)
	)
)

(instance getChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: mwAvoider
					setMotion:
						MoveTo
						(+
							(chest x?)
							(* 10 (if (< (ego x?) (giant x?)) -1 else 1))
						)
						(+ (chest y?) 7)
						self
				)
			)
			(1
				(ego
					view: 67
					loop: (+
						(if egoInvisible 2 else 0)
						(if (< (ego x?) (giant x?)) 0 else 1)
					)
					cel: 0
					setMotion: 0
					setAvoider: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 7)
			)
			(2
				(getSound init: play: self)
				(if (not (GiantDead))
					(Print 58 34
						#at -1 20
						#width 280
					)
				)
				(chest dispose:)
			)
			(3
				(ego setCycle: BegLoop self get: iChest)
				(SolvePuzzle fGotChest 8)
			)
			(4
				(if (GiantDead)
					(Print 58 35
						#at -1 20
						#width 280
					)
				else
					(giantHead setScript: snorer)
				)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance useSling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) fade:)
				(giant
					view: (+ 140 (if (localproc_17e3 1) 0 else 300))
					loop: 0
					setCycle: SyncWalk
				)
				(Face ego giant)
				(cond 
					((> (giant y?) 176) (giant setMotion: MoveTo (giant x?) 176 self))
					((& (giant onControl: origin) cYELLOW)
						(giant
							setMotion: MoveTo (giant x?) (- (giant y?) 20) self
						)
					)
					(else
						(self cue:)
					)
				)
			)
			(1
				(Face giant ego)
				(ego
					view: 40
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo (if (< (ego loop?) 2) 7 else 5) 1 self
				)
			)
			(2
				((ScriptID 0 21) number: 19 loop: 1 play:)
				(ego
					cel: (if (< (ego loop?) 2) 8 else 6)
					setCycle: EndLoop
				)
				(= cycles
					(proc0_17 25 (/ (ego distanceTo: giant) 5) 5)
				)
			)
			(3
				((ScriptID 0 21) number: 18 loop: 1 play:)
				(giant
					view: 143
					loop: (if (localproc_17e3 1) 0 else 2)
					cel: 0
					cycleSpeed: 4
					setCycle: CycleTo 1 1
					setAvoider: 0
					setMotion: 0
					ignoreActors:
					ignoreControl: 64
				)
				(= cycles 7)
			)
			(4
				(self setScript: sitGiant self)
			)
			(5
				(localproc_16bb)
				(if (not (ego canBeHere:))
					(NormalEgo)
					(ego view: 141 loop: 1 cel: 0 setPri: 0)
				else
					(NormalEgo)
				)
				(giant view: 143 loop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(6
				((ScriptID 0 21) number: 59 loop: 1 play:)
				(self cue:)
			)
			(7
				(ShakeScreen 6)
				(giant setCycle: EndLoop self)
			)
			(8
				(if (== (ego view?) 141)
					(EgoDead 58 36)
				)
				(SolvePuzzle fGiantDead 3)
				(PebbleCount)
				(= deadGiantX (giant x?))
				(= deadGiantY (giant y?))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sitGiant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(giant
					view: 143
					loop: (if (localproc_17e3 1) 0 else 2)
					cel: 1
					cycleSpeed: 6
					ignoreActors:
					setAvoider: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				((ScriptID 0 21) number: 59 loop: 1 play:)
				(giant cycleSpeed: 4 loop: 0 cel: 3)
				(chest init:)
				(= cycles 4)
			)
			(2
				(ShakeScreen 6)
				(giant setCycle: EndLoop cycleSpeed: 2)
				(= cycles 7)
			)
			(3 (self dispose:))
		)
	)
)

(instance sleepGiant of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== state 1)
				(giant inRect: 50 130 174 173)
				(ego inRect: 93 135 131 157)
				(not local8)
			)
			(Print 58 37)
			(HandsOff)
			(ego setMotion: MoveTo 112 175)
			(= local8 1)
		)
	)
	
	(method (dispose)
		(giant setAvoider: 0 setMotion: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 21) number: 75 loop: 1 play:)
				(Print 58 38)
				(= local8 0)
				(giant
					view: (+ 140 (if (localproc_17e3 1) 0 else 300))
					setCycle: SyncWalk
					setAvoider: mwAvoider
				)
				(if (< (giant y?) 158)
					(if (< (giant x?) 111)
						(giant setMotion: MoveTo 50 150 self)
					else
						(giant setMotion: MoveTo 180 150 self)
					)
				else
					(self cue:)
				)
			)
			(1
				(giant setMotion: MoveTo 112 158 self)
			)
			(2
				(sleepindeadGiant init:)
				(HandsOff)
				(ego setMotion: 0)
				(self setScript: sitGiant self)
			)
			(3
				(giant
					view: 142
					loop: 0
					cel: 0
					setCycle: 0
					setMotion: 0
					setAvoider: 0
					stopUpd:
				)
				(Bset fGiantSleeps)
				(giantHead init:)
				(= seconds 4)
			)
			(4
				(HandsOn)
				(SolvePuzzle fGiantTired 7)
				(Print 58 39)
				(self dispose:)
			)
		)
	)
)

(instance sleepindeadGiant of Block
	(properties
		top 135
		left 93
		bottom 157
		right 131
	)
	
	(method (init)
		(super init:)
		(ego observeBlocks: self)
	)
)

(instance tree1 of NewFeature
	(properties
		x 163
		y 16
		noun '/ceder'
		nsLeft 8
		nsBottom 33
		nsRight 318
		description {tree1}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree2 of NewFeature
	(properties
		x 197
		y 50
		noun '/ceder'
		nsTop 34
		nsLeft 75
		nsBottom 67
		nsRight 319
		description {tree2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree3 of NewFeature
	(properties
		x 116
		y 105
		noun '/ceder'
		nsTop 68
		nsLeft 104
		nsBottom 143
		nsRight 128
		description {tree3}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree4 of NewFeature
	(properties
		x 282
		y 125
		noun '/ceder'
		nsTop 149
		nsLeft 268
		nsBottom 101
		nsRight 297
		description {tree4}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree5 of NewFeature
	(properties
		x 271
		y 84
		noun '/ceder'
		nsTop 69
		nsLeft 253
		nsBottom 99
		nsRight 289
		description {tree5}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree6 of NewFeature
	(properties
		x 215
		y 76
		noun '/ceder'
		nsTop 68
		nsLeft 204
		nsBottom 84
		nsRight 227
		description {tree6}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree7 of NewFeature
	(properties
		x 6
		y 119
		noun '/ceder'
		nsTop 49
		nsBottom 189
		nsRight 8
		description {tree7}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree8 of NewFeature
	(properties
		x 298
		y 144
		noun '/ceder'
		nsTop 124
		nsLeft 288
		nsBottom 165
		nsRight 309
		description {tree8}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance tree9 of NewFeature
	(properties
		x 279
		y 132
		noun '/ceder'
		nsTop 102
		nsLeft 265
		nsBottom 163
		nsRight 293
		description {tree9}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The trees here are astonishingly tall; they loom over the land above the clouds.__You can barely see the tops!}
	)
)

(instance cloud of NewFeature
	(properties
		x 159
		y 49
		noun '/cloud'
		nsTop 21
		nsBottom 77
		nsRight 319
		description {cloud}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Thick, cottony clouds surround the land up here, obscuring your view of Daventry far below.}
	)
)

(instance getSound of Sound
	(properties
		number 66
		priority 10
	)
)
