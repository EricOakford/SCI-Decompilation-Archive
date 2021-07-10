;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgCaves)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use Track)
(use Smooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	cavesRg 0
)

(local
	local0 =  4
	local1 =  2
	local2 = [0 0 0 0 0 0 0 264 0 0 61 0 63 65 64 243 54 0 67 363 166 68 53 0 0 360 359 162 0 0 0 57 56 55 53 54]
	local39 = [46 7 44 9 26 13 -43 7 -38 8 -21 9 18 9 25 8 32 6 -14 8 -24 8 -32 7 21 -20 33 -17 44 -16 -19 -21 -31 -16 -41 -14 42 -19 34 -23 28 -24 -44 -19 -33 -26 -24 -23]
	lightIsOn
	local88
	[aDebris 6]
	local95
	local96
	local97
	local98 =  15
)
(procedure (localproc_038e param1 param2 &tmp i)
	(return
		(if (>= (= i (+ (* 6 param1) param2)) 0)
			(return (mod [local2 i] 100))
		else
			(return 0)
		)
	)
)

(procedure (localproc_03b1 &tmp temp0)
	(return (/ [local2 (+ (* 6 local0) local1)] 100))
)

(procedure (localproc_06a2 param1 param2)
	(return [local39 (localproc_06be param1 param2)])
)

(procedure (localproc_06ae param1 param2)
	(return [local39 (+ (localproc_06be param1 param2) 1)])
)

(procedure (localproc_06be param1 param2)
	(return (+ (* param1 6) (& param2 $000e)))
)

(instance cavesRg of Region
	
	(method (init)
		(if (== prevRoomNum 53)
			(= local0 3)
			(= local1 3)
			(= local98 0)
		)
		(curRoom
			north: (localproc_038e (- local0 1) local1)
			south: (localproc_038e (+ 1 local0) local1)
			east: (localproc_038e local0 (+ 1 local1))
			west: (localproc_038e local0 (- local1 1))
		)
		(air init: ignoreActors: TRUE setPri: 15)
		(if (<= (/ theQueuedSound 800) 0) (air hide:))
		(gauge init: ignoreActors: TRUE setPri: 15 addToPic:)
		(lightBeam init: setPri: 2 hide:)
		(bubbles init:)
		(if (not local96)
			(= local96 1)
			(globalSound
				number: 56
				priority: 1
				loop: -1
				play: egosBubbleScript
			)
		)
		(if (ego has: iDiver)
			(= local97 1)
			(ego view: 54 cycleSpeed: 2 setLoop: caveLooper)
			(caveLooper vNormal: 54 vChangeDir: 55)
		else
			(= local97 5)
			(ego view: 154 cycleSpeed: 2 setLoop: caveLooper)
			(caveLooper vNormal: 154 vChangeDir: 254)
		)
		(super init:)
		(HandsOn)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((< 0 theQueuedSound)
				(-= theQueuedSound local97)
				(if (< 0 (/ theQueuedSound 800))
					(air setCel: (- 10 (/ theQueuedSound 800)))
				else
					(air hide:)
				)
			)
			((not (ego script?))
				(ego setScript: dyingEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around,at][/room,cave,wall]')
				(Print 304 0)
			)
			((and (ego has: iDiver) (Said 'turn<on/light'))
				(= lightIsOn TRUE)
			)
			((and (ego has: iDiver) (Said 'turn<off/light'))
				(= lightIsOn FALSE)
				)
			(
			(and (ego has: iDiver) (Said 'press/lever,switch<left'))
				(if lightIsOn
					(Print 304 1)
					(= lightIsOn FALSE)
				else
					(Print 304 2)
					(= lightIsOn TRUE)
				)
			)
			((Said 'light/flare')
				(if (and (not local88) (ego has: iFlare))
					(if (not (-- numFlares))
						(ego put: iFlare)
					)
					(ego setScript: lightFlare)
				else
					(Print 304 3)
				)
			)
			((and (ego has: iDiver) (Said 'press/lever,switch<right'))
				(Print 304 4)
			)
			(
				(Said
					'examine,look[<at]/gear,scuba,coat,wetsuit,equipment'
				)
				(Print 304 5)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep
			(OneOf roomNum 59 60 63 64 65 66 67 68 62 43)
		)
		(= initialized FALSE)
		(switch ((User alterEgo?) edgeHit?)
			(NORTH (-- local0))
			(SOUTH (++ local0))
			(EAST (++ local1))
			(WEST (-- local1))
		)
		(if (== roomNum 53)
			(theGame changeScore: local98)
		)
		(if (< 0 local98)
			(-- local98)
		)
		(super newRoom: roomNum)
	)
)

(instance lightBeam of Actor
	(properties
		view 64
	)
	
	(method (init)
		(super init:)
		(self ignoreActors:)
		(ego ignoreActors:)
		(lightShine init: hide:)
	)
)

(instance lightShine of Actor
	(properties
		view 63
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 1
			ignoreActors:
			loop: (ego loop?)
			setMotion: Track lightBeam 0 0
		)
	)
)

(instance caveLooper of SmoothLooper
	(properties
		vNormal 54
		vChangeDir 55
	)
	
	(method (init)
		(super init: &rest)
		(self doit: ego (ego heading?) inProgress: FALSE)
	)
	
	(method (doit param1 param2)
		(super doit: param1 param2)
		(lightBeam
			setCycle: lightBeamCycler
			setMotion: lightMover
		)
		(lightShine setCycle: lightShineCycler)
	)
)

(instance lightBeamCycler of Cycle
	
	(method (doit)
		(if lightIsOn
			(lightBeam show:)
			(cond 
				((== (ego view?) 55)
					(lightBeam
						view: 65
						setLoop: (ego loop?)
						setCel: (/ (ego cel?) 2)
					)
				)
				((== (ego view?) 54) (lightBeam view: 64 setLoop: (ego loop?)))
			)
		else
			(lightBeam hide:)
		)
	)
)

(instance lightShineCycler of Cycle
	
	(method (doit)
		(if lightIsOn
			(lightShine show:)
			(cond 
				((== (ego view?) 55)
					(lightShine
						view: 63
						loop: (ego loop?)
						setCel: (/ (ego cel?) 2)
					)
				)
				((== (ego view?) 54)
					(lightShine view: 62 loop: (ego loop?))
				)
			)
		else
			(lightShine hide:)
		)
	)
)

(instance lightMover of Motion
	
	(method (doit &tmp temp0 temp1 egoLoop egoCel)
		(= egoLoop (ego loop?))
		(= egoCel (ego cel?))
		(cond 
			((== (ego view?) 55)
				(lightBeam
					x: (+ (ego x?) (localproc_06a2 egoLoop egoCel))
					y: (+ (ego y?) (localproc_06ae egoLoop egoCel))
				)
			)
			((== (ego view?) 54)
				(switch egoLoop
					(3 (= temp0 0) (= temp1 -24))
					(0 (= temp0 43) (= temp1 3))
					(2 (= temp0 0) (= temp1 8))
					(1 (= temp0 -44) (= temp1 3))
				)
				(lightBeam setMotion: Track ego temp0 temp1)
			)
		)
	)
)

(instance lightFlare of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 3) (ego y?) self
				)
			)
			(1
				(ego
					view: 155
					viewer: 0
					setLoop: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
				(if (ego has: iDiver)
					(theGame changeScore: 1)
					(lightShine setCycle: 0 setMotion: 0)
					(lightBeam setCycle: 0 setMotion: 0)
					(DV-3X
						init:
						view: 155
						setLoop: 8
						setCel: 1
						setPri: (ego priority?)
						posn: (- (ego x?) 28) (- (ego y?) 3)
					)
				)
				(= local88 1)
				(Flare init: hide:)
			)
			(2
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(if (< (Random 0 19) 16)
					(soundFlare init: play: self)
					(Flare
						show:
						illegalBits: 0
						ignoreActors:
						view: 155
						setLoop: 2
						setPri: (ego priority?)
						posn: (- (ego x?) 22) (- (ego y?) 23)
						setScript: debrisFlow
						setCycle: EndLoop self
					)
				else
					(Print 304 6)
					(self changeState: 7)
				)
			)
			(4
				(ego setLoop: 4 cycleSpeed: 6 setCycle: EndLoop)
				(Flare setLoop: 3 setCycle: Forward)
				(= cycles 20)
			)
			(5
				(ego setLoop: 4 cycleSpeed: 6 setCycle: BegLoop)
				(= cycles 150)
			)
			(6
				(= local88 0)
				(Flare setLoop: 2 setCel: 3 setCycle: BegLoop self)
				(soundFlare dispose:)
			)
			(7
				(= local88 0)
				(Flare view: 155 setLoop: 8 setCel: 0 hide:)
				(ego setLoop: 1 setCel: 2 setCycle: BegLoop)
				(lightShineCycler dispose:)
				(= cycles 30)
			)
			(8
				(ego
					setLoop: 0
					setCel: 3
					setMotion: MoveTo (+ (ego x?) 3) (ego y?)
					setCycle: BegLoop self
				)
				(Flare
					posn: (- (ego x?) 19) (+ (ego y?) 4)
					show:
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 18) 200
				)
			)
			(9
				(if (ego has: 6)
					(lightShine setCycle: lightShineCycler)
					(lightShine setMotion: Track lightBeam 0 0)
					(lightBeam
						setCycle: lightBeamCycler
						setMotion: lightMover
					)
					(DV-3X dispose:)
					(ego view: 54)
				else
					(ego view: 154)
				)
				(ego
					loop: 1
					setLoop: -1
					cel: 5
					setCycle: Walk
					cycleSpeed: 2
					heading: 270
					setLoop: caveLooper
				)
				(= cycles 30)
			)
			(10
				(ego illegalBits: cWHITE setScript: 0)
				(HandsOn)
			)
		)
	)
	
	(method (cue param1)
		(if
			(and
				(== param1 soundFlare)
				(== (soundFlare signal?) 10)
			)
			(egosBubbleScript cue:)
		else
			(super cue: &rest)
		)
	)
)

(instance bubbles of Actor
	(properties
		yStep 3
		view 54
		loop 4
	)
	
	(method (init)
		(self
			setCycle: Walk
			setLoop: 4
			ignoreActors:
			illegalBits: 0
			setScript: egosBubbleScript
		)
		(super init:)
		(self hide:)
	)
)

(instance egosBubbleScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(bubbles
					x:
						(switch (ego loop?)
							(0 (+ (ego x?) 8))
							(1 (- (ego x?) 8))
							(else  (ego x?))
						)
					y: (- (ego y?) 5)
					show:
					setPri: (ego priority?)
					setMotion: MoveTo (ego x?) 5 self
				)
			)
			(2
				(bubbles hide:)
				(= cycles 2)
			)
			(3 (self init:))
		)
	)
)

(instance Flare of Actor)

(instance DV-3X of View)

(instance debris of Actor
	(properties
		view 155
		loop 5
	)
)

(instance debrisFlow of Script
	
	(method (doit)
		(super doit:)
		(if (and (!= state 10) (not local88))
			(self changeState: 10)
		)
	)
	
	(method (dispose &tmp i)
		(for ((= i 0)) (< i 6) ((++ i))
			([aDebris i] hide: forceUpd:)
		)
		(for ((= i 0)) (< i 6) ((++ i))
			([aDebris i] dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(for ((= i 0)) (< i 6) ((++ i))
					((= [aDebris i] (Clone debris))
						init:
						setLoop: (Random 5 7)
						ignoreActors:
						ignoreHorizon:
						illegalBits: 0
						hide:
					)
				)
				(= cycles 2)
			)
			(1
				([aDebris local95]
					setCycle: Forward
					posn: (Flare x?) (Flare y?)
					setPri: (if (ego priority?) (ego priority?) else 0)
					show:
					setMotion: MoveTo
						(switch (localproc_03b1)
							(0 330)
							(1 -10)
							(else 
								(+ (Random 0 50) (Flare x?) -25)
							)
						)
						(switch (localproc_03b1)
							(2 200)
							(3 -10)
							(else 
								(+ (Random 0 50) (Flare y?) -25)
							)
						)
				)
				(= local95 (mod (++ local95) 4))
				(= cycles 6)
			)
			(2
				([aDebris local95] setCycle: 0 hide:)
				(= cycles 6)
			)
			(3 (self changeState: 1))
			(10 (= cycles 60))
			(11 (client dispose:))
		)
	)
)

(instance gauge of Prop
	(properties
		y 11
		x 22
		z 1
		view 54
		loop 5
		priority 15
	)
)

(instance air of Prop
	(properties
		y 8
		x 5
		z 2
		view 54
		loop 6
		cel 9
	)
)

(instance soundFlare of Sound
	(properties
		number 57
		priority 2
	)
)

(instance soundDying of Sound
	(properties
		number 59
		priority 2
	)
)

(instance dyingEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 3) (ego y?)
				)
				(globalSound stop:)
				(soundDying init: play: self)
				(= seconds 4)
			)
			(1
				(if (ego has: 6)
					(lightShine setMotion: 0 setCycle: 0)
					(lightBeam setCycle: 0 setMotion: 0)
					(DV-3X
						init:
						view: 155
						setLoop: 8
						setCel: 1
						setPri: (ego priority?)
						ignoreActors:
						posn: (- (ego x?) 28) (- (ego y?) 3)
					)
				)
				(ego
					view: 155
					viewer: 0
					setLoop: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2 (= seconds 1))
			(3
				(ego
					view: 255
					setLoop: 0
					setCel: 0
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 3)
			)
			(4
				(ego
					view: 255
					setLoop: 0
					setCel: 0
					cycleSpeed: 0
					setCycle: Forward
				)
				(= seconds 5)
			)
			(5
				(ego
					view: 255
					setLoop: 0
					setCel: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(= seconds 3)
			)
			(6
				(ego
					view: 255
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: Forward
				)
			)
			(7
				(ego
					view: 255
					setLoop: 1
					setCel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
				(= cycles 60)
			)
			(8
				(ego
					illegalBits: cWHITE
					moveSpeed: 1
					setCycle: 0
					setLoop: 1
					setCel: 4
					ignoreActors:
					looper: 0
					setMotion: MoveTo (ego x?) 0
				)
				(= cycles 40)
			)
			(9
				(EgoDead 951 0 0 304 7)
			)
		)
	)
	
	(method (cue param1)
		(cond 
			((and (< 0 argc) (== (param1 signal?) 20))
				(super cue: param1 &rest)
			)
			((== argc 0)
				(super cue:)
			)
		)
	)
)
