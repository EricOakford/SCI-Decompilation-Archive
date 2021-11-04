;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include system.sh)
(include game.sh)
(include keys.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm10 0
)

(local
	theDoor
	local1
	oldEgoX
	shooterX
	local4
	[shooter 4]
	[local9 4]
	local13
	target
	local15
	targetShots
	bulletHole1
	bulletHole2
	bulletHole3
	bulletHole4
	bulletHole5
	bulletHole6
	bulletHole7
	eventMessage_2
	[local25 22]
	[local47 22]
	local69
	sightAdjuster
	windageScrewdriver
	elevationScrewdriver
	local73 ;isAdjustingSights?
	talkedToKen
	wearingEarProtectors
	viewingTarget
	local77
	inBooth
)
(procedure (CreateShooter param1)
	(= shooterX (- 207 (* local4 28)))
	((= [shooter param1] (Prop new:)) ;body
		posn: shooterX 127
		setPri: 9
		loop: (Random 1 2)
		view: 73
		cycleSpeed: 1
		init:
		stopUpd:
	)
	((View new:) ;random head
		posn: shooterX 98
		setPri: 9
		loop: 0
		cel: (Random 0 3)
		view: 73
		init:
		addToPic:
	)
)

(procedure (FaceObject param1 param2 &tmp temp0)
	(= temp0 (param1 loop?))
	(DirLoop
		param1
		(GetAngle
			(param1 x?)
			(param1 y?)
			(param2 x?)
			(param2 y?)
		)
	)
	(if (== argc 3)
		(DirLoop
			param2
			(GetAngle
				(param2 x?)
				(param2 y?)
				(param1 x?)
				(param1 y?)
			)
		)
	)
	(if (!= temp0 (param1 loop?)) (param1 forceUpd:))
)

(instance shot of Sound
	(properties)
)

(instance leftArm of Prop
	(properties
		view 70
		loop 1
		priority 12
		signal $6810 ;???
	)
	
	(method (doit)
		(self posn: (ego x?) (ego y?))
	)
)

(instance rightArm of Prop
	(properties
		view 70
		loop 1
		cel 1
		priority 12
		signal $6810
	)
	
	(method (doit)
		(self posn: (ego x?) (ego y?))
	)
)

(instance rm10 of Room
	(properties
		picture 10
		style VSHUTTER
	)
	
	(method (init)
		(super init:)
		(= local77 1)
		(NormalEgo)
		(HandsOn)
		(Load VIEW 1)
		(Load VIEW 72)
		(Load VIEW 0)
		(Load VIEW 58)
		(Load VIEW 73)
		(Load SOUND 41)
		(= talkedToKen 0)
		(= wearingEarProtectors 0)
		(= gunFireState 3)
		(= gunNotNeeded 1)
		(self setScript: rm10Script)
	)
)

(instance door of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== (ego onControl: 1) cLRED)
					(< state 3)
				)
				(= state 3)
				(theDoor setMotion: MoveTo 270 137 self)
			)
			(
				(and
					(!= (ego onControl: 1) cLRED)
					(== state 4)
				)
				(= state 1)
				(theDoor setMotion: MoveTo 242 137 self)
			)
		)
	)
	
	(method (cue)
		(client stopUpd:)
		(++ state)
	)
)

(instance rm10Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((< (ego y?) 137)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1) (ego view: (+ (ego view?) 1)))
		)
		(cond 
			((& cBROWN (ego onControl:))
				(curRoom newRoom: 2)
			)
			(
				(and
					(== (ego onControl: 1) cYELLOW)
					(== (ego loop?) 3)
				)
				(self changeState: 3)
			)
			((!= (self state?) 3)
				(self changeState: 1)
			)
		)
		(if (not (< howFast 30))
			(if (== local13 30)
				(= local4 (Random 0 3))
				(if (!= (cast indexOf: [shooter local4]) -1)
					(= local13 (Random 1 12))
					(shooterScript changeState: 0)
				)
			else
				(++ local13)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= [local25 1] 0)
				(= [local25 2] -2)
				(= [local25 3] 2)
				(= [local25 4] 0)
				(= [local25 5] 0)
				(= [local25 6] -1)
				(= [local25 7] 1)
				(= [local25 8] 0)
				(= [local25 9] 0)
				(= [local25 10] -1)
				(= [local25 11] 1)
				(= [local25 12] -2)
				(= [local25 13] 2)
				(= [local25 14] -1)
				(= [local25 15] 1)
				(= [local25 16] -1)
				(= [local25 17] 1)
				(= [local25 18] -2)
				(= [local25 19] 2)
				(= [local25 20] 1)
				(= [local25 21] -1)
				(= [local47 1] 0)
				(= [local47 2] -1)
				(= [local47 3] 1)
				(= [local47 4] -1)
				(= [local47 5] 1)
				(= [local47 6] -1)
				(= [local47 7] 1)
				(= [local47 8] -2)
				(= [local47 9] 2)
				(= [local47 10] 1)
				(= [local47 11] -1)
				(= [local47 12] 0)
				(= [local47 13] 0)
				(= [local47 14] 2)
				(= [local47 15] -2)
				(= [local47 16] 0)
				(= [local47 17] 0)
				(= [local47 18] 1)
				(= [local47 19] -1)
				(= [local47 20] 2)
				(= [local47 21] -2)
				((= theDoor (Actor new:))
					view: 58
					setCycle: 0
					setCel: 0
					yStep: 1
					xStep: 1
					illegalBits: 0
					posn: 242 137
					init:
					stopUpd:
					setScript: door
				)
				(attendant
					view: 72
					loop: 1
					cel: 0
					posn: 161 156
					stopUpd:
					init:
				)
				(= local1 0)
				(ego
					posn: 20 145
					setMotion: MoveTo 161 140
					setCycle: Walk
					init:
				)
				(= local4 0)
				(while (<= local4 3)
					(cond 
						((not local77)
							(if [local9 local4]
								(CreateShooter local4)
							)
						)
						((== (Random 1 2) 1)
							(CreateShooter local4)
							(= [local9 local4] 1)
						)
					)
					(= local4 (+ local4 1))
				)
				(= local77 0)
			)
			(1 (= local1 0))
			(2
				(if (== local1 0)
					(Print 10 0)
				)
				(= local1 1)
			)
			(3
				(if
					(or
						(and
							(!= (cast indexOf: [shooter 0]) -1)
							(< (ego distanceTo: [shooter 0]) 20)
						)
						(and
							(!= (cast indexOf: [shooter 1]) -1)
							(< (ego distanceTo: [shooter 1]) 20)
						)
						(and
							(!= (cast indexOf: [shooter 2]) -1)
							(< (ego distanceTo: [shooter 2]) 20)
						)
						(and
							(!= (cast indexOf: [shooter 3]) -1)
							(< (ego distanceTo: [shooter 3]) 20)
						)
					)
					(Print 10 1)
					(Print 10 2)
					(ego
						setMotion: MoveTo
							(if (== (Random 1 2) 1)
								320
							else
								0
							)
							(ego y?)
					)
				else
					(= oldEgoX (ego x?))
					(++ global210)
					(rm10 setScript: boothScript)
				)
			)
			(4
				(rightArm dispose:)
				(leftArm dispose:)
				(= gunDrawn 0)
				(ego
					view: 0
					setCycle: Walk
					setStep: 3 2
					setMotion: 0
					setLoop: -1
					illegalBits: cWHITE
					ignoreActors: 0
					setPri: -1
					posn: oldEgoX 130
					init:
				)
				(= local1 0)
				(if (== wearingEarProtectors 1)
					(Print 10 3)
					(= wearingEarProtectors 0)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,chamber,range]')
								(Print 10 4)
								(Print 10 5)
							)
							((Said '/counter,bookcase,coatrack')
								(Print 10 6)
							)
							((Said '/glass')
								(cond 
									(
										(and
											(>= (ego x?) 47)
											(>= (ego y?) 137)
											(== (ego loop?) 3)
										)
										(Print 10 7)
									)
									((<= (ego y?) 135)
										(Print 10 8)
									)
									(else
										(event claimed: 0)
									)
								)
							)
							(
								(or (Said '/cop[<weapons]')
									(Said '/dude,ken,ken')
								)
								(Print 10 9)
							)
							((Said '/bullseye')
								(Print 10 10)
							)
							((Said '/*')
								(if (ego inRect: 152 153 210 158)
									(Print 10 11)
								else
									(event claimed: 0)
								)
							)
						)
					)
					((Said 'chat/dude,cop,ken')
						(cond 
							((< (ego y?) 139)
								(Print 10 12)
							)
							((ego inRect: 152 153 210 158)
								(Print 10 13)
							)
							(talkedToKen
								(Print 10 14)
							)
							(else
								(switch (Random 0 2)
									(0
										(Print 10 15)
									)
									(1
										(Print 10 16)
									)
									(2
										(Print 10 17)
									)
								)
								(= talkedToKen 1)
							)
						)
					)
					(
						(or
							(Said 'ask,use/range[<shooting]')
							(Said 'use/booth[<shooting]')
							(Said 'fire/range,bullseye')
							(Said 'bullseye/fire')
						)
						(if (== (ego onControl: 1) cLMAGENTA)
							(Print 10 18)
						else
							(NotClose)
						)
					)
					(
						(or
							(Said 'gave/9mm')
							(Said 'display/9mm')
						)
						(if (== (ego onControl: 1) cLMAGENTA)
							(if (ego has: iHandGun)
								(Print 10 19)
								(Print 10 20)
								(Print 10 21)
							else
								(DontHaveGun)
							)
						else
							(NotClose)
						)
					)
					((Said '(hand<in),exit,replace,gave/ep[<ear]')
						(cond 
							((not (ego has: iEarProtectors))
								(DontHave)
							)
							((!= (ego onControl: 1) cLMAGENTA)
								(NotClose)
							)
							(else
								(Print 10 22)
								(PutInRoom iEarProtectors)
							)
						)
					)
					(
					(or (Said 'get/ep[<ear]') (Said 'ask/ep[<ear]'))
						(if (== (ego onControl: 1) cLMAGENTA)
							(if (== (ego has: iEarProtectors) 0)
								(Print 10 23)
								(ego get: iEarProtectors)
								(SolvePuzzle 2 66)
							else
								(Print 10 24)
							)
						else
							(NotClose)
						)
					)
					(
						(or
							(Said 'get,replace,exchange,ask/ammo,clip')
							(Said 'ask//ammo,clip')
						)
						(cond 
							((!= (ego onControl: 1) cLMAGENTA)
								(Print 10 25)
							)
							(
								(and
									(not (ego has: 1))
									(not bulletsInGun)
								)
								(Print 10 26)
							)
							(
								(and
									(not (ego has: iAmmoClips))
									bulletsInGun
								)
								(if [numAmmoClips bulletsInGun]
									(Print 10 27)
								else
									(Print 10 28)
									(SolvePuzzle 2 68)
									(= [numAmmoClips bulletsInGun] 7)
								)
							)
							(
								(and
									(== [numAmmoClips 1] [numAmmoClips 2])
									(== [numAmmoClips 2] 0)
								)
								(Print 10 29)
								(SolvePuzzle 2 68)
								(= [numAmmoClips 1] 7)
								(= [numAmmoClips 2] 7)
								(= bulletsInGun 0)
							)
							(
								(or
									(not [numAmmoClips 1])
									(not [numAmmoClips 2])
								)
								(Print 10 28)
								(SolvePuzzle 2 68)
								(if (not [numAmmoClips bulletsInGun])
									(= bulletsInGun 0)
								)
								(if (== [numAmmoClips 1] 0)
									(= [numAmmoClips 1] 7)
								else
									(= [numAmmoClips 2] 7)
								)
							)
							(else (Print 10 27))
						)
					)
					(
						(or
							(Said 'wear/ep[<ear]')
							(Said 'deposit/ep[<ear]')
							(Said 'use/ep[<ear]')
							(Said 'deposit[<in]/ep')
						)
						(if (ego has: iEarProtectors)
							(Print 10 30)
						else
							(Print 10 31)
						)
					)
					(
						(or
							(Said 'get<off/ep[<ear]')
							(Said 'remove/ep[<ear]')
						)
						(if (== wearingEarProtectors 0)
							(Print 10 32)
						else
							(Print 10 33)
							(= wearingEarProtectors 0)
						)
					)
				)
			)
		)
	)
)

(instance shooterScript of Script
	(properties)
	
	(method (init)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				([shooter local4] setCycle: BegLoop self)
			)
			(1
				([shooter local4] stopUpd:)
			)
		)
	)
)

(instance boothScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((<= (ego y?) 130)
				(if (ego mover?)
					((ego mover?) init: ego ((ego mover?) x?) 130)
				)
			)
			(
				(and
					(>= (ego y?) 250)
					(ego mover?)
				)
				((ego mover?) init: ego ((ego mover?) x?) 250)
			)
		)
		(cond 
			((<= (ego x?) 94)
				(if (ego mover?)
					((ego mover?) init: ego 94 ((ego mover?) y?))
				)
			)
			(
				(and
					(>= (ego x?) 222)
					(ego mover?)
				)
				((ego mover?) init: ego 222 ((ego mover?) y?))
			)
		)
		(if
			(and
				(not (ego has: 0))
				(<= (ego y?) 245)
			)
			(ego setMotion: MoveTo (ego x?) 245)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(= inBooth 1)
		(switch (= state newState)
			(0
				(= gunDrawn 0)
				(if (not local73)
					(Load VIEW 70)
					(Load PICTURE 11)
					(curRoom drawPic: 11)
					(cast eachElementDo: #dispose)
					(cast eachElementDo: #delete)
					(= targetShots 0)
					(= local69 (Random 1 21))
					(ego
						setPri: 12
						posn: 160 (if (not gunDrawn) 242 else 150)
						view: 70
						setStep: 1 1
						setMotion: 0
						illegalBits: 0
						init:
						ignoreHorizon:
						ignoreActors:
						setCel: 0
						setLoop: 0
					)
					(rightArm init: signal: 26640 doit:) ;26640 doesnt match signal defines
					(leftArm init: signal: 26640 doit:)
					((= target (Prop new:))
						view: 70
						posn: 159 59
						setLoop: 2
						cycleSpeed: 3
						cel: 4
						setPri: 4
						init:
						stopUpd:
					)
					(if (>= oldEgoX 107)
						((View new:)
							view: 70
							posn: 97 59
							loop: 2
							cel: 4
							setPri: 4
							init:
							addToPic:
						)
					)
					(if (<= oldEgoX 195)
						((View new:)
							view: 70
							posn: 225 59
							loop: 2
							cel: 4
							setPri: 4
							init:
							addToPic:
						)
					)
					((= bulletHole1 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole2 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole3 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole4 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole5 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole6 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					((= bulletHole7 (View new:))
						view: 70
						posn: 160 70
						setLoop: 3
						setCel: 0
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
				else
					(= local73 0)
				)
				(= inBooth 0)
			)
			(1
				(HandsOff)
				(if (not wearingEarProtectors)
					(shot number: 41 play:)
				)
				(ego
					setCel: 255
					setCycle: EndLoop self
				)
				([bulletHole1 targetShots]
					posn:
						(+
							(- (/ (* (ego x?) 5) 2) 237)
							gunWindageScrew
							[local25 local69]
						)
						(+
							(- (/ (* (ego y?) 8) 3) 285)
							gunElevationScrew
							[local47 local69]
						)
				)
				(++ targetShots)
				(= viewingTarget 0)
				(-- [numAmmoClips bulletsInGun])
				(if (== wearingEarProtectors 0) (++ unprotectedShots))
				(if (== local69 21) (= local69 1) else (++ local69))
			)
			(2
				(HandsOn)
				(ego setCel: 0)
				(if (> (ego y?) 208)
					(Print 10 34 #time 5 #draw)
					(Print 10 35 #time 6 #draw)
					(EgoDead 10 36)
				)
				(if
					(and
						(== [numAmmoClips bulletsInGun] 6)
						(== wearingEarProtectors 0)
					)
					(Print 10 37 #draw)
				)
				(if
					(or
						(and
							(>= global210 3)
							(== [numAmmoClips bulletsInGun] 4)
							(== wearingEarProtectors 0)
						)
						(== unprotectedShots 6)
					)
					(RedrawCast)
					(EgoDead 10 38)
				)
				(= inBooth 0)
			)
			(3
				(HandsOff)
				(ego
					yStep: (if (< howFast 30) 8 else 4)
					setCel: 0
					setMotion: MoveTo 160 150 self
				)
			)
			(4
				(ego setStep: 1 1)
				(HandsOn)
				(= gunDrawn 1)
				(= inBooth 0)
			)
			(5
				(HandsOff)
				(ego
					yStep: (if (< howFast 30) 8 else 4)
					setMotion: MoveTo 160 245 self
					setCel: 0
				)
			)
			(6
				(ego setStep: 1 1)
				(HandsOn)
				(= gunDrawn 0)
				(= inBooth 0)
			)
			(7
				(target startUpd: setCycle: BegLoop self)
			)
			(8
				(target cel: 0 stopUpd:)
				(if targetShots
					(= eventMessage_2 0)
					(while (< eventMessage_2 targetShots)
						(if
							(and
								(< 132 ([bulletHole1 eventMessage_2] x?))
								(< ([bulletHole1 eventMessage_2] x?) 190)
								(< 48 ([bulletHole1 eventMessage_2] y?))
								(< ([bulletHole1 eventMessage_2] y?) 130)
							)
							(++ local15)
							([bulletHole1 eventMessage_2] setPri: 11)
						)
						(++ eventMessage_2)
					)
					(if
						(and
							(<= -6 gunWindageScrew)
							(<= gunWindageScrew 6)
							(<= -4 gunElevationScrew)
							(<= gunElevationScrew 4)
						)
						(SolvePuzzle 5 67)
						(= gunSightsAligned 1)
					)
				)
				(= inBooth 0)
			)
			(9
				(Print 10 39 #draw)
				(= eventMessage_2 0)
				(while (< eventMessage_2 7)
					([bulletHole1 eventMessage_2] setPri: 0 stopUpd:)
					(++ eventMessage_2)
				)
				(= local15 0)
				(= targetShots 0)
				(= local69 (Random 1 21))
				(= inBooth 0)
			)
			(10
				(= local15 0)
				(= targetShots 0)
				(= local69 (Random 1 21))
				(= inBooth 0)
				(target setCycle: EndLoop self)
			)
			(11
				(target stopUpd:)
				(User canControl: 1 canInput: 1)
				(= inBooth 0)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 40])
		(if (event claimed?) (return))
		(if (>= (ego y?) 240)
			(= gunDrawn 0)
		else
			(= gunDrawn 1)
		)
		(switch (event type?)
			(keyDown
				(= temp0 (event message?))
				(event claimed: 1)
				(cond 
					((== temp0 `#a) ;17408 or KEY_F10 doesn't match here. Use the statusMenu designation I guess? F8 below matches fine.
						(cond 
							(isHandsOff
								1
							)
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							((!= (ego yStep?) 1)
								(Print 10 40)
							)
							((not [numAmmoClips bulletsInGun])
								(Print 10 41 #time 2)
							)
							((> targetShots 6)
								(Print 10 42)
							)
							(
								(and
									(!= (target cel?) 4)
									(<= (ego y?) 208)
								)
								(Print 10 43) ;target too close
							)
							((not inBooth)
								(boothScript changeState: 1)
							)
						)
					)
					((== temp0 KEY_F8)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(inBooth
								(return)
							)
							(gunDrawn
								(self changeState: 5)
							)
							(else
								(self changeState: 3)
							)
						)
					)
					(else
						(event claimed: 0)
					)
				)
			)
			(saidEvent
				(cond 
					((Said 'look[<at,around][/noword,booth,chamber]')
						(Print 10 44)
					)
					((Said 'look/bookcase') (Print 10 45))
					((Said 'hoist/9mm')
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(gunDrawn
								(Print 10 46)
							)
							(inBooth
								(Print 10 47)
							)
							(else (= gunDrawn 1)
								(self changeState: 3)
							)
						)
					)
					((Said 'lower/9mm')
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(inBooth (Print 10 47))
							(gunDrawn
								(= gunDrawn 0)
								(self changeState: 5)
							)
							(else
								(Print 10 48)
							)
						)
					)
					((Said 'look/bullseye,hole')
						(cond 
							((== (target cel?) 4)
								(Print 10 49)
							)
							((not targetShots)
								(Print 10 50)
							)
							(viewingTarget
								(Print 10 51)
							)
							((not local15)
								(Print 10 52)
							)
							(
								(and
									(<= -6 gunWindageScrew)
									(<= gunWindageScrew 6)
									(<= -4 gunElevationScrew)
									(<= gunElevationScrew 4)
								)
								(Print 10 53)
							)
							((< gunElevationScrew -4)
								(Print
									(Format
										@temp1 10 54
										(cond 
											((> gunWindageScrew 6) { and to the right})
											((< gunWindageScrew -6) { and to the left})
											(else {})
										)
									)
								)
							)
							((> gunElevationScrew 4)
								(Print
									(Format
										@temp1 10 55
										(cond 
											((> gunWindageScrew 6) { and to the right})
											((< gunWindageScrew -6) { and to the left})
											(else {})
										)
									)
								)
							)
							(else
								(Print
									(Format
										@temp1 10 56
										(cond 
											((> gunWindageScrew 6) {right})
											((< gunWindageScrew 6) {left})
										)
									)
								)
							)
						)
					)
					((Said 'look/button,wall')
						(Print 10 57)
					)
					(
						(or
							(Said 'press/view,(button<view)')
							(Said 'view')
						)
						(cond 
							(gunDrawn
								(Print 10 58)
							)
							(inBooth
								(Print 10 47)
							)
							((== (target cel?) 4)
								(self changeState: 7)
							)
							(else
								(Print 10 59)
							)
						)
					)
					(
						(or
							(Said 'press<back[/button]')
							(Said 'press/button<back')
							(Said '/back')
						)
						(cond 
							((== (target cel?) 4)
								(Print 10 60)
							)
							(gunDrawn
								(Print 10 61)
							)
							(
								(and
									(> targetShots 0)
									local15
								)
								(Print 10 62)
							)
							(inBooth
								(Print 10 47)
							)
							(else
								(self changeState: 10)
							)
						)
					)
					((Said 'press/button')
						(Print 10 63)
					)
					(
						(or
							(Said 'get,change,replace/bullseye')
							(Said '/bullseye<new')
						)
						(cond 
							((== (target cel?) 4)
								(Print 10 64)
							)
							(gunDrawn
								(Print 10 61)
							)
							(inBooth
								(Print 10 47)
							)
							(else
								(self changeState: 9)
							)
						)
					)
					((Said 'adjust/bullseye')
						(Print 10 65)
					)
					((Said 'adjust/sight,9mm,windage,elevation')
						(if (not gunDrawn)
							(if (ego has: iHandGun)
								(User canControl: 0)
								(= viewingTarget 1)
								(rm10 setScript: sightScript)
							else
								(DontHaveGun)
							)
						else
							(Print 10 66)
						)
					)
					((Said 'exit,(walk<out)[/booth,chamber,range]')
						(if inBooth
							(Print 10 47)
						else
							(curRoom drawPic: 10)
							(cast eachElementDo: #dispose)
							(cast eachElementDo: #delete)
							(rm10 setScript: rm10Script)
							(rm10Script changeState: 4)
						)
					)
					(
						(or
							(Said '(deposit<on),wear,use/ep[<ear]')
							(Said 'deposit[<in]/ep')
						)
						(cond 
							((not (ego has: iEarProtectors))
								(DontHave)
							)
							(wearingEarProtectors
								(Print 10 67)
							)
							(gunDrawn
								(Print 10 61)
							)
							(else
								(= global210 0)
								(Print 10 33)
								(= wearingEarProtectors 1)
							)
						)
					)
					((Said '(get<off),remove/ep[<ear]')
						(cond 
							((not wearingEarProtectors)
								(Print 10 68)
							)
							((<= (ego y?) 236)
								(Print 10 61)
							)
							(else
								(Print 10 33)
								(= wearingEarProtectors 0)
							)
						)
					)
				)
			)
		)
	)
)

(instance sightScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((= sightAdjuster (View new:))
					view: 70
					loop: 4
					cel: 0
					posn: 160 150
					init:
				)
				((= windageScrewdriver (Prop new:))
					view: 70
					loop: 5
					cel: 0
					posn: 175 144
					setPri: 0
					init:
				)
				((= elevationScrewdriver (Prop new:))
					view: 70
					loop: 6
					cel: 0
					setPri: 0
					posn: 146 108
					init:
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(direction
				(event claimed: 1)
				(switch (= eventMessage_2 (event message?))
					(1
						(elevationScrewdriver
							cel:
								(if (== (elevationScrewdriver cel?) 0)
									(elevationScrewdriver lastCel:)
								else
									(- (elevationScrewdriver cel?) 1)
								)
							setPri: 15
						)
						(windageScrewdriver setPri: 0)
						(-- gunElevationScrew)
					)
					(3
						(windageScrewdriver
							cel:
								(if
									(==
										(windageScrewdriver cel?)
										(windageScrewdriver lastCel:)
									)
									0
								else
									(+ (windageScrewdriver cel?) 1)
								)
							setPri: 15
						)
						(elevationScrewdriver setPri: 0)
						(++ gunWindageScrew)
					)
					(5
						(elevationScrewdriver
							cel:
								(if
									(==
										(elevationScrewdriver cel?)
										(- (NumCels elevationScrewdriver) 1)
									)
									0
								else
									(+ (elevationScrewdriver cel?) 1)
								)
							setPri: 12
						)
						(windageScrewdriver setPri: 0)
						(++ gunElevationScrew)
					)
					(7
						(windageScrewdriver
							cel:
								(if (== (windageScrewdriver cel?) 0)
									(- (NumCels windageScrewdriver) 1)
								else
									(- (windageScrewdriver cel?) 1)
								)
							setPri: 12
						)
						(elevationScrewdriver setPri: 0)
						(-- gunWindageScrew)
					)
				)
			)
			(keyDown
				(= local73 1)
				(sightAdjuster dispose:)
				(windageScrewdriver dispose:)
				(elevationScrewdriver dispose:)
				(rm10 setScript: boothScript)
				(event claimed: 1)
				(HandsOn)
			)
		)
	)
)

(instance attendant of Prop
	(properties)
	
	(method (doit)
		(if (< howFast 30) (return))
		(if (ego inRect: 100 139 220 160)
			(FaceObject self ego)
		)
	)
)
