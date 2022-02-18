;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
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
	rm047 0
)

(local
	local0
	local1
	gEgoEdgeHit
	[local3 18] = [1000 100 42 4 11 24 19 23 30 1003 10 120 42 11 25 23 31 31]
)
(procedure (localproc_0c6c)
	(ego setMotion: 0)
	(cond 
		((not gGEgoX)
			(switch gGEgoY
				(1
					(theMusic fade:)
					(curRoom newRoom: 45)
				)
				(2 (curRoom newRoom: 44))
				(else  (curRoom newRoom: 46))
			)
		)
		((and (== gGEgoX 0) (== gGEgoY 3)) (curRoom newRoom: 46))
		((and (== gGEgoX 4) (== gGEgoY 2)) (curRoom newRoom: 48))
		(else
			(if (and (== gGEgoX 4) (== gGEgoY 3))
				(harpies init:)
			else
				(harpies dispose:)
			)
			(switch gEgoEdgeHit
				(1 (ego posn: 160 186))
				(3 (ego posn: 160 89))
				(2 (ego posn: 5 (ego y?)))
				(else 
					(ego posn: 315 (ego y?))
				)
			)
			(islets
				posn: (- 200 (* gGEgoX 30)) (islets y?)
				forceUpd:
			)
			(islets2
				posn: (- 350 (* gGEgoX 30)) (islets2 y?)
				forceUpd:
			)
			(sailBoat posn: (+ (ego x?) 10) (ego y?))
			(curRoom drawPic: 47)
			(if (cast contains: cedric)
				(cond 
					((== (sailBoat loop?) 0)
						(cedric
							posn: (+ (sailBoat x?) 20) (+ (sailBoat y?) 4)
							cel: (if (Btst 54) 2 else 1)
						)
					)
					((== (sailBoat loop?) 1)
						(cedric
							posn: (- (sailBoat x?) 23) (+ (sailBoat y?) 4)
							cel: (if (Btst 54) 3 else 0)
						)
					)
					((== (sailBoat loop?) 5)
						(cedric
							posn: (+ (sailBoat x?) 15) (- (sailBoat y?) 4)
							cel: (if (Btst 54) 2 else 1)
						)
					)
					((== (sailBoat loop?) 6)
						(cedric
							posn: (- (sailBoat x?) 15) (- (sailBoat y?) 4)
							cel: (if (Btst 54) 3 else 0)
						)
					)
					((== (sailBoat loop?) 7)
						(cedric
							posn: (- (sailBoat x?) 15) (+ (sailBoat y?) 9)
							cel: (if (Btst 54) 3 else 0)
						)
					)
					((== (sailBoat loop?) 8)
						(cedric
							posn: (+ (sailBoat x?) 15) (+ (sailBoat y?) 10)
							cel: (if (Btst 54) 3 else 0)
						)
					)
					((== (sailBoat loop?) 9)
						(cedric
							posn: (sailBoat x?) (+ (sailBoat y?) 10)
							cel: (if (Btst 54) 3 else 0)
						)
					)
					(else (cedric posn: 500 500))
				)
				(RedrawCast)
			)
			(= gEgoEdgeHit 0)
			(ego edgeHit: 0)
			(= local1 0)
		)
	)
	(if (or (not gGEgoY) (== gGEgoY 4) (== gGEgoX 6))
		(curRoom setScript: killEgo)
	)
)

(instance rm047 of KQ5Room
	(properties
		picture 47
		horizon 80
	)
	
	(method (init)
		(self setFeatures: water)
		(if (not (Btst 107))
			(theMusic number: 895 loop: -1 vol: 127 playBed:)
			(theAudio number: 7057 doNotStop: 1 loop: -1 play:)
		)
		(LoadMany 128 259)
		(switch prevRoomNum
			(45
				(= gGEgoX 1)
				(= gGEgoY 1)
				(ego posn: 10 147)
			)
			(44
				(= gGEgoX 1)
				(= gGEgoY 2)
				(ego posn: 10 147)
			)
			(46
				(= gGEgoX 1)
				(= gGEgoY 3)
				(ego posn: 10 127)
			)
			(48
				(= gGEgoX 4)
				(= gGEgoY 3)
				(harpies init:)
				(ego posn: 160 89)
			)
			(else 
				(= gGEgoX 1)
				(= gGEgoY 3)
				(ego posn: 10 120)
			)
		)
		(wave1 init: setScript: waves)
		(islets
			init:
			posn: (- 200 (* gGEgoX 30)) (islets y?)
			stopUpd:
		)
		(islets2
			init:
			posn: (- 350 (* gGEgoX 30)) (islets2 y?)
			stopUpd:
		)
		(if (and (ego has: 18) (not (Btst 107)))
			(ego posn: 80 130)
			(sailBoat
				init:
				view: 259
				setCel: -1
				posn: 80 130
				loop: 0
				cycleSpeed: 2
			)
			(if (and (not (Btst 54)) (not (Btst 55)))
				(cedric
					init:
					signal: 16400
					setPri: 15
					posn: (+ (sailBoat x?) 20) (+ (sailBoat y?) 1)
				)
			)
		)
		(super init:)
		(cond 
			((Btst 107) (curRoom setScript: drownHim))
			((ego has: 18) (curRoom setScript: sink))
			(else (curRoom setScript: sailIn))
		)
	)
	
	(method (doit)
		(super doit:)
		(sailBoat x: (+ (ego x?) 10) y: (ego y?))
		(cond 
			(script (script doit:))
			(
			(and (not local1) (= gEgoEdgeHit (ego edgeHit?)))
				(= local1 1)
				(switch gEgoEdgeHit
					(3 (++ gGEgoY))
					(2 (++ gGEgoX))
					(4 (-- gGEgoX))
					(1 (-- gGEgoY))
				)
				(theGame setCursor: waitCursor 1)
				(HandsOff)
				(localproc_0c6c)
				(Wait 0)
				(Animate (cast elements?) 0)
				(theGame setCursor: gameCursor (HaveMouse))
				(HandsOn)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 970)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic fade:)
	)
)

(instance waves of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(wave1
					init:
					show:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave2
					init:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave3
					init:
					setCycle: EndLoop
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
				(wave4
					init:
					setCycle: EndLoop self
					setPri: 2
					ignoreActors:
					cycleSpeed: (Random 5 10)
				)
			)
			(1 (= seconds (Random 3 8)))
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance startBoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sailBoat setCycle: Forward)
				(ego moveSpeed: 2)
				(= seconds 1)
			)
			(1
				(ego moveSpeed: 1)
				(= seconds 1)
			)
			(2
				(ego moveSpeed: 0)
				(= seconds 2)
			)
			(3 (client setScript: 0))
		)
	)
)

(instance stopBoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sailBoat setCycle: 0)
				(= cycles 1)
			)
			(1 (client setScript: 0))
		)
	)
)

(instance drownHim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SpeakAudio 524 0 1)
				(ego
					view: 128
					normal: 0
					posn: 0 130
					setPri: -1
					setCycle: Forward
					moveSpeed: 1
					cycleSpeed: 1
					init:
					show:
					setMotion: MoveTo 160 130 self
				)
				((ego head?) hide:)
			)
			(1 (= cycles 1))
			(2
				(theAudio number: 7056 loop: 1 play: self)
				(theMusic2 number: 893 loop: 1 vol: 127 play:)
				(ego view: 130 cycleSpeed: 1 setCycle: Forward)
			)
			(3 (ego setCycle: EndLoop self))
			(4 (ego hide:) (= cycles 5))
			(5
				(= deathMessage 525)
				(EgoDead)
			)
		)
	)
)

(instance sailIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sailBoat
					init:
					setCel: -1
					ignoreActors:
					illegalBits: 0
					setCycle: Forward
					cycleSpeed: 2
				)
				(ego
					view: 0
					setPri: 15
					setLoop: -1
					setCycle: KQ5SyncWalk
					ignoreActors:
					setStep: 3 2
					init:
					hide:
				)
				((ego head?) hide:)
				(if (not (Btst 55)) (cedric init: ignoreActors: z: 8))
				(sailBoat posn: (+ (ego x?) 10) (ego y?))
				(sail posn: (sailBoat x?) (sailBoat y?))
				(RedrawCast)
				(= cycles 1)
			)
			(1
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance sink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(if (and (not (Btst 54)) (not (Btst 55)))
					(proc762_1 @local3 3071 self)
				)
			)
			(2
				(theAudio number: 7056 loop: 1 play: self)
				(sailBoat
					cycleSpeed: (if (== howFast 2) 16 else 8)
					setCycle: EndLoop self
				)
				(if (and (not (Btst 54)) (not (Btst 55)))
					(cedric dispose:)
					(staticCedric
						init:
						view: 259
						posn: (cedric x?) (cedric y?) (cedric z?)
						loop: 1
						cycleSpeed: 5
						setCycle: EndLoop
					)
				)
			)
			(3)
			(4 (EgoDead))
		)
	)
)

(instance killEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego heading?) 180) (self register: 1))
				(staticBoat
					init:
					view: 648
					cel: 0
					x: (sailBoat x?)
					y: (sailBoat y?)
					setLoop: register
				)
				(sail
					init:
					view: 649
					cel: 0
					x: (sailBoat x?)
					y: (sailBoat y?)
					setLoop: register
				)
				(sailBoat dispose:)
				(if (not (Btst 55))
					(staticCedric
						init:
						cel: register
						x: (if register
							(- (staticBoat x?) 19)
						else
							(+ (staticBoat x?) 19)
						)
						y: (- (staticBoat y?) 2)
						setPri: 15
					)
					(if (Btst 54)
						(staticCedric cel: (+ (staticCedric cel?) 2))
					)
				)
				(cedric hide:)
				(= cycles 25)
			)
			(1
				(DoAudio 1 7069)
				(theMusic number: 894 loop: 1 vol: 127 play:)
				(seaMonster
					init:
					loop: register
					x:
						(cond 
							(register
								(if (< (- (staticBoat x?) 80) 30)
									30
								else
									(- (staticBoat x?) 80)
								)
							)
							((> (+ (staticBoat x?) 80) 290) 290)
							(else (+ (staticBoat x?) 80))
						)
					y: (if (< (- (staticBoat y?) 20) 60)
						60
					else
						(- (staticBoat y?) 20)
					)
					setCycle: EndLoop self
				)
			)
			(2
				(HandsOff)
				(theAudio number: 7069 loop: 1 play:)
				(seaMonsterHead
					loop: (+ 4 register)
					cel: 0
					x: (seaMonster x?)
					y: (seaMonster y?)
					init:
				)
				(seaMonster
					setLoop: (+ 2 register)
					setCycle: Forward
					setMotion:
						MoveTo
						(if register
							(- (staticBoat x?) 65)
						else
							(+ (staticBoat x?) 65)
						)
						(- (staticBoat y?) 10)
						self
				)
			)
			(3
				(seaMonsterHead dispose:)
				(seaMonster
					setLoop: (+ 4 register)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(staticBoat hide:)
				(sail hide:)
				(seaMonster setLoop: (+ 6 register) cel: 0)
				(= seconds 2)
			)
			(5
				(if (and (not (Btst 55)) (not (Btst 54)))
					(proc762_1 @local3 3072 self)
				else
					(= cycles 1)
				)
			)
			(6
				(staticCedric dispose:)
				(theAudio number: 7070 loop: 1 play:)
				(seaMonster setCycle: EndLoop)
				(staticBoat dispose:)
				(sail dispose:)
				(= cycles 1)
			)
			(7 (= seconds 4))
			(8
				(= deathMessage 9060)
				(EgoDead 261)
			)
		)
	)
)

(instance sailBoat of Actor
	(properties
		x -35
		y 182
		yStep 1
		view 647
		xStep 1
	)
	
	(method (doit &tmp egoHeading)
		(super doit:)
		(curRoom doit:)
		(if
		(and (== (sailBoat view?) 647) (not (ego has: 18)))
			(cond 
				(
					(and
						(< 134 (= egoHeading (ego heading?)))
						(< egoHeading 160)
						(ego mover?)
						(!= (sailBoat loop?) 8)
					)
					(sailBoat loop: 8)
				)
				(
					(and
						(< 200 egoHeading)
						(< egoHeading 226)
						(!= (sailBoat loop?) 7)
					)
					(sailBoat loop: 7)
				)
				(
					(and
						(< 314 egoHeading)
						(< egoHeading 341)
						(ego mover?)
						(!= (sailBoat loop?) 6)
					)
					(sailBoat loop: 6)
				)
				(
					(and
						(< 20 egoHeading)
						(< egoHeading 46)
						(ego mover?)
						(!= (sailBoat loop?) 5)
					)
					(sailBoat loop: 5)
				)
				(
					(and
						(< -1 egoHeading)
						(< egoHeading 20)
						(ego mover?)
						(!= (sailBoat loop?) 3)
					)
					(sailBoat loop: 3)
				)
				(
					(and
						(< 340 egoHeading)
						(< egoHeading 361)
						(ego mover?)
						(!= (sailBoat loop?) 3)
					)
					(sailBoat loop: 3)
				)
				(
					(and
						(< 45 egoHeading)
						(< egoHeading 135)
						(ego mover?)
						(!= (sailBoat loop?) 0)
					)
					(sailBoat loop: 0)
				)
				(
					(and
						(< 225 egoHeading)
						(< egoHeading 315)
						(ego mover?)
						(!= (sailBoat loop?) 1)
					)
					(sailBoat loop: 1)
				)
				(
					(and
						(< 160 egoHeading)
						(< egoHeading 200)
						(ego mover?)
						(!= (sailBoat loop?) 9)
					)
					(sailBoat loop: 9)
				)
			)
		)
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
					(if (cast contains: cedric)
						(SpeakAudio 527)
					else
						(SpeakAudio 9061)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance water of RFeature
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
					(SpeakAudio 528)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance islets of Prop
	(properties
		y 33
		view 630
		signal $7900
		detailLevel 3
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
					(SpeakAudio 529)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance islets2 of Prop
	(properties
		y 33
		view 630
		cel 3
		signal $7900
		detailLevel 3
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
					(SpeakAudio 529)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sail of Prop
	(properties
		x 146
		y 133
		view 649
		signal $7900
	)
)

(instance cedric of Actor
	(properties
		z 13
		view 647
		loop 2
		cel 1
		signal $7800
	)
	
	(method (doit)
		(if (not (curRoom script?))
			(switch (sailBoat loop?)
				(0
					(cedric
						x: (+ (sailBoat x?) 20)
						y: (+ (sailBoat y?) 4)
						cel: (if (Btst 54) 2 else 1)
					)
				)
				(1
					(cedric
						x: (- (sailBoat x?) 23)
						y: (+ (sailBoat y?) 4)
						cel: (if (Btst 54) 3 else 0)
					)
				)
				(5
					(cedric
						x: (+ (sailBoat x?) 15)
						y: (- (sailBoat y?) 4)
						cel: (if (Btst 54) 2 else 1)
					)
				)
				(6
					(cedric
						x: (- (sailBoat x?) 15)
						y: (- (sailBoat y?) 4)
						cel: (if (Btst 54) 3 else 0)
					)
				)
				(7
					(cedric
						x: (- (sailBoat x?) 15)
						y: (+ (sailBoat y?) 9)
						cel: (if (Btst 54) 3 else 0)
					)
				)
				(8
					(cedric
						x: (+ (sailBoat x?) 15)
						y: (+ (sailBoat y?) 10)
						cel: (if (Btst 54) 3 else 0)
					)
				)
				(9
					(cedric
						x: (sailBoat x?)
						y: (+ (sailBoat y?) 10)
						cel: (if (Btst 54) 3 else 0)
					)
				)
				(else  (cedric x: 500 y: 500))
			)
			(super doit:)
		)
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
				(verbTalk
					(SpeakAudio 532)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rock1 of View
	(properties
		x 258
		y 101
		view 630
		loop 3
		cel 2
		signal $7900
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
					(SpeakAudio 530)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rock2 of View
	(properties
		x 54
		y 163
		view 630
		loop 3
		signal $7900
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
					(SpeakAudio 530)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance seaMonster of Actor
	(properties
		yStep 5
		view 621
		signal $6000
		illegalBits $0000
		xStep 5
	)
)

(instance seaMonsterHead of Actor
	(properties
		yStep 5
		view 621
		priority 15
		signal $7810
		illegalBits $0000
		xStep 5
	)
	
	(method (doit)
		(super doit:)
		(= x (seaMonster x?))
		(= y (seaMonster y?))
	)
)

(instance staticBoat of Actor
	(properties
		view 648
	)
)

(instance staticCedric of Actor
	(properties
		view 647
		loop 2
		cel 1
		signal $6000
	)
)

(instance harpies of View
	(properties
		x 160
		y 38
		view 630
		loop 1
		cel 1
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
					(if (cast contains: cedric)
						(SpeakAudio 531)
					else
						(SpeakAudio 9062)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wave1 of Prop
	(properties
		x 199
		y 120
		view 631
		detailLevel 3
	)
)

(instance wave2 of Prop
	(properties
		x 250
		y 126
		view 631
		detailLevel 3
	)
)

(instance wave3 of Prop
	(properties
		x 33
		y 131
		view 631
		loop 1
		detailLevel 3
	)
)

(instance wave4 of Prop
	(properties
		x 272
		y 176
		view 631
		loop 2
		detailLevel 3
	)
)

(instance rwave1 of Prop
	(properties
		x 43
		y 189
		view 631
		loop 3
		detailLevel 3
	)
)

(instance rwave2 of Prop
	(properties
		x 89
		y 174
		view 631
		detailLevel 3
	)
)

(instance rwave3 of Prop
	(properties
		x 245
		y 106
		view 631
		loop 4
		detailLevel 3
	)
)

(instance rwave4 of Prop
	(properties
		x 116
		y 158
		view 631
		loop 1
		detailLevel 3
	)
)

(instance gull1 of Actor
	(properties
		yStep 1
		view 631
		loop 5
		signal $2800
		cycleSpeed 2
		detailLevel 3
		illegalBits $0002
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
					(SpeakAudio 9063)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance gull2 of Actor
	(properties
		yStep 1
		view 631
		loop 5
		signal $2800
		cycleSpeed 2
		detailLevel 3
		illegalBits $0002
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
					(SpeakAudio 9063)
					(event claimed: 1)
				)
			)
		)
	)
)
