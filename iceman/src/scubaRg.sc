;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgScuba)
(include game.sh)
(use Main)
(use Intrface)
(use RegionPath)
(use EgoDead)
(use Smooper)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scubaRg 0
	AddUnderwaterObj 1
	AddWavingPlant 2
)

(local
	rayPts1 = [
		NODIRECTION 57 249 110 340 110
		NODIRECTION 56 -20 133 150 150
		PATHEND
		]
	rayPts2 = [
		NODIRECTION 56 150 150 340 167
		NODIRECTION 55 -20 150 160 150 340 134
		NODIRECTION 53 -20 134 340 134
		NODIRECTION 54 -20 134 208 148
		PATHEND
		]
	rayPts3 = [
		NODIRECTION 54 208 148 340 155
		NODIRECTION 61 -20 157 340 140
		NODIRECTION 58 -20 140 31 140
		PATHEND
		]
	rayPts4 = [
		NODIRECTION 58 31 140 -20 105
		NODIRECTION 61 340 97 167 110 -20 118
		NODIRECTION 54 340 120 -20 88
		NODIRECTION 53 340 83 -20 81
		NODIRECTION 55 340 105 158 108 -20 125
		NODIRECTION 56 340 129 -20 131
		NODIRECTION 57 340 107 273 107
		PATHEND
		]
	local106
	local107
	deathReason
)
(procedure (AddUnderwaterObj theLoop theCel theX theY thePri &tmp obj)
	((= obj (PicView new:))
		view: 56
		loop: theLoop
		cel: theCel
		x: theX
		y: theY
	)
	(if (> argc 4)
		(obj priority: thePri)
	)
	(addToPics add: obj)
)

(procedure (AddWavingPlant theLoop theX theY thePri)
	((Clone wavingPlant)
		loop: theLoop
		x: theX
		y: theY
		setPri: thePri
		init:
	)
)

(instance scubaRg of Region
	
	(method (init)
		(Load SOUND 58)
		(Load SOUND 258)
		(fish1 init:)
		(fish2 init:)
		(schoolOfFish init:)
		(super init:)
		(bubbles init:)
		(if (not local106)
			(= local106 1)
			(globalSound
				number: 60
				priority: 1
				loop: -1
				play: egosBubbleScript
			)
		)
		(ego cycleSpeed: 2 setLoop: -1 setLoop: scubaLooper)
		(if (ego has: iDiver)
			(= local107 1)
			(ego view: 54)
			(scubaLooper vNormal: 54 vChangeDir: 55)
		else
			(= local107 5)
			(ego view: 154)
			(scubaLooper vNormal: 154 vChangeDir: 254)
		)
		(torpedoRay init: setScript: rayScript setCycle: Walk)
		(air init: ignoreActors: TRUE setPri: 15)
		(if (<= (/ theQueuedSound 800) 0) (air hide:))
		(gauge init: ignoreActors: TRUE setPri: 15 addToPic:)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (scubaLooper vNormal?) 54)
				(not (ego has: iDiver))
			)
			(scubaLooper vNormal: 154 vChangeDir: 254)
		)
		(if (< 0 theQueuedSound)
			(-= theQueuedSound local107)
			(if (< 0 (/ theQueuedSound 800))
				(air setCel: (- 10 (/ theQueuedSound 800)))
			else
				(air hide:)
			)
		else
			(EgoDead 157 0 1 305 0)
		)
		(switch deathReason
			(1 (EgoDead 157 0 1 305 1))
			(2 (EgoDead 157 0 0 305 2))
			(3 (EgoDead 157 0 1 305 3))
			(4 (EgoDead 157 0 1 305 3))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'surface')
				(cond 
					((ego has: iDiver)
						(Print 305 4)
					)
					(((inventory at: iRumBottle) ownedBy: 55)
						(Print 305 5)
					)
					(else
						(Print 305 6)
					)
				)
			)
			(
				(Said
					'examine,look[<at]/gear,scuba,coat,wetsuit,equipment'
				)
				(Print 305 7)
			)
			((Said 'look>')
				(cond 
					((Said '[<at,around][/room]')
						(switch (Random 0 3)
							(0 (Print 305 8))
							(1 (Print 305 9))
							(2 (Print 305 10))
							(3 (Print 305 11))
						)
					)
					((Said '/cave')
						(Print 305 12)
					)
					((Said '/rock')
						(switch (Random 0 1)
							(0 (Print 305 13))
							(1 (Print 305 14))
						)
					)
					((Said '/plant')
						(Print 305 15)
					)
					(else
						(Print 305 16)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'get')
				(Print 305 17)
			)
			((Said 'move>')
				(if (Said '/rock')
					(Print 305 18)
				else
					(Print 305 19)
				)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep
			(OneOf roomNum 53 54 55 56 57 58 61 45 46 47)
		)
		(= initialized FALSE)
		(if (!= ((inventory at: iExplosive) owner?) 51)
			(= curRoomNum roomNum)
			(= deathReason 1)
		)
		(if (OneOf roomNum 999 70 71 72)
			(cond 
				((ego has: iDiver)
					(= curRoomNum roomNum)
					(= deathReason 2)
				)
				((== roomNum 999)
					(= curRoomNum roomNum)
					(= deathReason 3)
				)
				((not ((inventory at: iRumBottle) ownedBy: 55))
					(= curRoomNum roomNum)
					(= deathReason 3)
				)
				(else
					(globalSound fade:)
					(theGame changeScore: 2)
				)
			)
		)
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
			(3
				(self init:)
			)
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
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/bubbles]')
				(Print 305 20)
			)
		)
	)
)

(instance wavingPlant of Prop
	(properties
		view 56
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self isExtra: 1 setCycle: Forward)
	)
)

(instance fish1 of Actor
	(properties
		view 57
	)
	
	(method (init)
		(super init:)
		(self
			xStep: 1
			yStep: 1
			posn: (Random 20 300) (Random 100 150)
			cycleSpeed: 2
			setCycle: Walk
			setMotion: Wander 100
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/fish]')
				(Print 305 21)
			)
			((Said 'catch/fish')
				(Print 305 22)
			)
			((Said 'kill/fish')
				(Print 305 23)
			)
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:)
			(self inRect: -20 -20 340 210)
		)
	)
)

(instance fish2 of Actor
	(properties
		view 67
	)
	
	(method (init)
		(super init:)
		(self
			xStep: 1
			yStep: 1
			cycleSpeed: 2
			setCycle: Walk
			posn: (Random 20 300) (Random 70 150)
			setMotion: Wander 100
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/fish]')
				(Print 305 21)
			)
			((Said 'catch/fish')
				(Print 305 22)
			)
			((Said 'kill/fish')
				(Print 305 23)
			)
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:)
			(self inRect: -20 -20 340 210)
		)
	)
)

(instance scubaLooper of SmoothLooper
	(properties
		vNormal 54
		vChangeDir 55
	)
)

(instance schoolOfFish of Actor
	(properties
		view 267
	)
	
	(method (init)
		(super init:)
		(self
			posn: (Random 0 320) (Random 0 200)
			setMotion: Wander 100
			setCycle: Forward
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/fish]')
				(Print 305 24)
			)
			((Said 'catch/fish')
				(Print 305 25)
			)
			((Said 'kill/fish')
				(Print 305 23)
			)
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:)
			(self inRect: -20 -20 340 210)
		)
	)
)

(instance torpedoRay of Actor
	(properties
		y 110
		x 259
		view 60
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreControl: cWHITE ignoreActors: TRUE)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				mover
				(== (mover currentRoom?) curRoomNum)
				(< (ego distanceTo: self) 20)
			)
			(ego setMotion: MoveTo (ego x?) (ego y?))
			(HandsOff)
			(script register: 0)
			(self setMotion: 0 setScript: electricuteScript)
		)
	)
	
	(method (dispose)
		(= script 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/ray]')
				(Print 305 26)
			)
			((Said 'catch/ray')
				(NotClose)
			)
		)
	)
)

(instance rayPath1 of RegionPath
	(properties
		endType 0
		theRegion 305
	)
	
	(method (at n)
		(return [rayPts1 n])
	)
)

(instance rayPath2 of RegionPath
	(properties
		endType 0
		theRegion 305
	)
	
	(method (at n)
		(return [rayPts2 n])
	)
)

(instance rayPath3 of RegionPath
	(properties
		endType 0
		theRegion 305
	)
	
	(method (at n)
		(return [rayPts3 n])
	)
)

(instance rayPath4 of RegionPath
	(properties
		endType 0
		theRegion 305
	)
	
	(method (at n)
		(return [rayPts4 n])
	)
)

(instance rayScript of Script

	(method (init)
		(if register
			(client script: self setMotion: register)
		else
			(super init: &rest)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 3
					setCycle: Walk
					setMotion: rayPath1 self
				)
				(= register rayPath1)
			)
			(1
				(= register 0)
				(client setCel: 0 setLoop: 5 setCycle: EndLoop self)
			)
			(2
				(client setLoop: 4 setCycle: EndLoop self)
			)
			(3
				(client
					setLoop: 3
					setCycle: Walk
					setMotion: rayPath2 self
				)
				(= register rayPath2)
			)
			(4
				(= register 0)
				(client
					setLoop: 1
					setCel: 16
					cycleSpeed: 2
					setCycle: BegLoop self
				)
			)
			(5
				(client stopUpd:)
				(= seconds 10)
			)
			(6
				(client setLoop: 1 setCycle: EndLoop self)
			)
			(7
				(client
					setLoop: 3
					cycleSpeed: 0
					setCycle: Walk
					setMotion: rayPath3 self
				)
				(= register rayPath3)
			)
			(8
				(= register 0)
				(client setLoop: 1 cycleSpeed: 2 setCycle: BegLoop self)
			)
			(9
				(client stopUpd:)
				(= seconds 10)
			)
			(10
				(client setLoop: 1 setCycle: EndLoop self)
			)
			(11
				(client
					setLoop: 5
					cycleSpeed: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(12
				(client
					setLoop: 2
					setCycle: Walk
					setMotion: rayPath4 self
				)
				(= register rayPath4)
			)
			(13
				(client setLoop: 0 setCycle: BegLoop self)
				(= register 0)
			)
			(14
				(= seconds 10)
			)
			(15
				(client setLoop: 0 setCycle: EndLoop self)
			)
			(16
				(client setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(17
				(self init:)
			)
		)
	)
)

(instance electricGlow of Prop
	(properties
		view 159
	)
	
	(method (init theX theY thePri)
		(super init:)
		(self
			posn: theX theY
			setPri: thePri
			ignoreActors: TRUE
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self setLoop: 1 setCycle: Forward)
	)
)

(instance electricuteScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(zap number: (SoundFX 58) play:)
				((Clone electricGlow)
					init: (torpedoRay x?) (torpedoRay y?) (+ (torpedoRay priority?) 2)
				)
				(= cycles 15)
			)
			(1
				((Clone electricGlow)
					init: (ego x?) (ego y?) (+ (ego priority?) 2)
				)
				(= cycles 50)
			)
			(2 (EgoDead 951 2 0 305 27))
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

(instance zap of Sound
	(properties
		priority 2
	)
)
