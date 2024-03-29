;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh)
(use Main)
(use Intrface)
(use DLetter)
(use CodeCue)
(use n770)
(use PolyPath)
(use Polygon)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	boatRegion 0
	sailIn 1
)

(local
	pts1 = [
		319 143
		319 154
		211 154
		218 143
		]
	pts2 = [
		319 164
		319 175
		212 175
		218 164
		]
	pts3 = [
		319 164
		319 175
		212 175
		224 164
		]
	local24 = [1000 100 62 4 11 24 19 23 30]
	local33 = [1003 131 62 4 11 25 23 31 31]
	local42 = [1003 235 105 4 11 25 23 31 31]
)
(class boatRegion of Region
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(!= gGNumber_2 45)
				(!= gGNumber_2 46)
				(!= gGNumber_2 44)
			)
			(= gGNumber_2 45)
		)
		(cond 
			((== prevRoomNum 47)
				(= gGNumber_2 curRoomNum)
				(poly5
					points:
					(switch curRoomNum
						(45 @pts2)
						(46 @pts3)
						(44 @pts1)
					)
					size: 4
				)
				(curRoom addObstacle: poly5)
				(curRoom setScript: sailIn)
			)
			((== curRoomNum gGNumber_2)
				(if (or (== curRoomNum 45) (== curRoomNum 46))
					(poly5 points: @pts2 size: 4)
				else
					(poly5 points: @pts1 size: 4)
				)
				(curRoom addObstacle: poly5)
				(sailBoat
					init:
					y: (if (== curRoomNum 44) 153 else 173)
					stopUpd:
					ignoreActors:
				)
				(sail
					posn: (+ (sailBoat x?) 8) (sailBoat y?)
					setCycle: (if (== (theGame detailLevel:) 3) 0 else Forward)
					cycleSpeed: 30
					ignoreActors:
					setPri: (sailBoat priority?)
					init:
				)
				(wake
					init:
					posn: (sailBoat x?) (sailBoat y?)
					setCycle: Forward
					cycleSpeed: 5
				)
				(if (!= (theGame detailLevel:) 3)
					(wake setCycle: 0)
				)
				(if (Btst 105)
					(curRoom setScript: leave)
				)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(not
					(OneOf (ego view?) 628 624 615 616 0 56 100 97 618)
				)
				(not (& (ego onControl: origin) $2650))
			)
			(ego view: 0)
		)
		(if script
			(script doit:)
		)
	)
)

(instance flyIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bird
					view: 618
					setLoop: 9
					posn: (- (bird x?) 20) (- (bird y?) 17)
					setCycle: Forward
					moveSpeed: 0
					cycleSpeed: 0
					setPri: 15
					setStep: 4 3
					setMotion: MoveTo cedricX (- cedricY 10) self
				)
			)
			(1
				(bird
					view: 138
					setLoop: 1
					posn: (bird x?) (+ (bird y?) 10)
					setCycle: EndLoop self
				)
			)
			(2
				(bird view: 138 setLoop: 4 cel: 3 setCycle: BegLoop self)
			)
			(3
				(bird dispose:)
				(curRoom setRegions: 202)
				(if (== curRoomNum 45)
					(globalCedric setPri: 8)
				)
				(proc770_0 @local24 globalCedric)
				(proc762_1 @local24 3001 self)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance sailIn of Script
	
	(method (doit)
		(super doit:)
		(if (cast contains: globalCedric) (globalCedric hide:))
		(if
			(and
				(& (ego onControl: origin) cLMAGENTA)
				(== (ego view?) 0)
				(not (sailBoat mover?))
			)
			(ego view: 28)
		)
		(if (sailBoat mover?)
			(ego x: (+ (sailBoat x?) 22) y: (- (sailBoat y?) 15))
			(bird x: (- (sailBoat x?) 35) y: (- (sailBoat y?) 17))
		else
			(sail stopUpd:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (not (Btst 54)) (not (Btst fCedricInjured)))
					(bird
						init:
						view: 138
						setLoop: 4
						setCel: 3
						setPri: 10
						ignoreActors:
						illegalBits: 0
					)
				)
				(wake
					init:
					posn: (sailBoat x?) (sailBoat y?)
					setCycle: Forward
					cycleSpeed: 5
				)
				(if (!= (theGame detailLevel:) 3) (wake setCycle: 0))
				(sailBoat
					x: 320
					y: (if (== curRoomNum 44) 153 else 173)
					setLoop: 0
					illegalBits: 0
					setPri: -1
					ignoreActors:
					init:
				)
				(ego
					view: 618
					loop: 5
					z: 0
					init:
					posn: (+ (sailBoat x?) 22) (- (sailBoat y?) 15)
					show:
				)
				((ego head?) hide:)
				(= cycles 1)
			)
			(1
				(sail
					init:
					setCycle: Forward
					ignoreActors:
					cycleSpeed: 20
					setPri: (+ (sailBoat priority?) 2)
					show:
				)
				(sailBoat setMotion: MoveTo 280 (sailBoat y?) self)
			)
			(2
				(ego
					view: 624
					setLoop: 0
					posn: (ego x?) (- (sailBoat y?) 17)
					normal: 0
					cel: 0
					setPri: (- (sailBoat priority?) 1)
					ignoreActors: 1
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(sailBoat stopUpd:)
			)
			(3
				((ego head?) show:)
				(ego
					view: 28
					setPri: -1
					loop: 1
					posn: (ego x?) (+ (ego y?) 5)
				)
				(sailBoat setPri: -1)
				(sail setPri: -1 stopUpd:)
				(wake
					init:
					posn: (sailBoat x?) (sailBoat y?)
					setCycle: Forward
					cycleSpeed: 5
				)
				(if (!= (theGame detailLevel:) 3) (wake setCycle: 0))
				(NormalEgo)
				(= cycles 1)
			)
			(4
				(if (and (not (Btst fCedricInjured)) (not (Btst 54)))
					(proc770_0 @local33 ego)
					(proc762_1 @local33 7042 self)
				else
					(= cycles 1)
				)
			)
			(5
				((ego head?) show:)
				(if (and (not (Btst fCedricInjured)) (not (Btst 54)))
					(client setScript: flyIn)
				else
					(HandsOn)
					(client setScript: 0)
				)
				(wake cycleSpeed: 16)
			)
		)
	)
)

(instance leave of Script
	
	(method (doit &tmp thisControl)
		(if
			(and
				(or
					(& (= thisControl (ego onControl: origin)) cBROWN)
					(& thisControl cLMAGENTA)
				)
				(!= (ego view?) 26)
				(== state 1)
			)
			(ego view: 26)
		)
		(if (and (!= (ego view?) 0) (== state 4))
			(ego view: 0)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Bclr 107)
				(ego
					setCycle: KQ5SyncWalk
					setStep: 3 2
					setLoop: -1
					moveSpeed: (theGame egoMoveSpeed?)
					illegalBits: 0
					ignoreActors:
					normal: 0
				)
				(HandsOff)
				(= cycles 1)
			)
			(1
				(if (and (== (ego view?) 2) (== curRoomNum 44))
					(ego setMotion: MoveTo 114 45 self)
				else
					(= state 3)
					(= cycles 1)
				)
			)
			(2
				(ego
					view: 9
					setPri: 15
					setLoop: 0
					setCycle: EndLoop self
					cel: 0
					moveSpeed: 0
					illegalBits: 0
					posn: 113 44
					setMotion: MoveTo 68 55
				)
				((ego head?) hide:)
			)
			(3
				(ego
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: -1
					illegalBits: cWHITE
					moveSpeed: (theGame egoMoveSpeed?)
					setMotion: MoveTo 68 55 self
				)
				((ego head?) show:)
			)
			(4
				(ego
					setMotion: PolyPath (- (sailBoat x?) 86) (+ (sailBoat y?) 2) self
				)
			)
			(5
				((ego head?) hide:)
				(ego
					view: 615
					loop: 2
					setPri: (+ (sailBoat priority?) 1)
					cel: 0
					setCycle: EndLoop self
				)
				(sail setPri: (+ (ego priority?) 1))
			)
			(6
				(ego
					setLoop: 3
					posn: (+ (ego x?) 31) (ego y?)
					setCycle: Forward
				)
				(= seconds 2)
			)
			(7
				(sailBoat
					setStep: 1 1
					moveSpeed: (ego moveSpeed?)
					setMotion: MoveTo 400 (sailBoat y?)
				)
				(ego setStep: 1 1 setMotion: MoveTo 400 (ego y?))
				(= seconds 2)
			)
			(8
				(ego
					posn: (ego x?) (- (ego y?) 18)
					setLoop: 0
					setCycle: EndLoop self
				)
				(sailBoat setStep: 2 1)
				(ego setStep: 2 1 setMotion: MoveTo 400 (ego y?))
			)
			(9
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(10
				(ego
					view: 618
					loop: 4
					posn: (+ (ego x?) 20) (+ (ego y?) 2)
				)
				(= seconds 2)
			)
			(11
				(if
					(and
						(cast contains: globalCedric)
						(not (Btst 55))
						(not (Btst 54))
					)
					(proc770_0 @local33 ego)
					(proc762_1 @local42 7015 self)
				else
					(= cycles 1)
				)
			)
			(12
				(if
					(and
						(cast contains: globalCedric)
						(not (Btst fCedricInjured))
						(not (Btst 54))
					)
					(proc770_0 @local24 globalCedric)
					(proc762_1 @local24 3002 self)
				else
					(= cycles 1)
				)
			)
			(13
				(ego setMotion: 0)
				(sailBoat setMotion: 0)
				(sail setMotion: 0)
				(if (cast contains: globalCedric)
					(cls)
					(globalCedric view: 138 setLoop: 6 setCycle: EndLoop self)
					(= cycles 5)
				else
					(= cycles 1)
				)
			)
			(14
				(if (Btst 105)
					(curRoom newRoom: 113)
				else
					(curRoom newRoom: 47)
				)
			)
		)
	)
)

(instance fixBoat of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: iWax)
				(SolvePuzzle 5)
				(HandsOff)
				(ego
					setMotion:
						PolyPath
						(if (and (< (ego x?) 319) (< (ego y?) 170))
							(- (sailBoat x?) 41)
						else
							(- (sailBoat x?) 73)
						)
						(if (and (< (ego x?) 319) (< (ego y?) 170))
							(- (sailBoat y?) 10)
						else
							(sailBoat y?)
						)
						self
				)
			)
			(1
				(ego
					setPri: (- (sailBoat priority?) 1)
					loop: 2
					setMotion: PolyPath (- (sailBoat x?) 41) (- (sailBoat y?) 10) self
				)
			)
			(2
				((ego head?) hide:)
				(ego
					view: 56
					loop: 3
					setPri: (- (sailBoat priority?) 1)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3 (= seconds 3))
			(4 (ego setCycle: BegLoop self))
			(5
				((ego head?) show:)
				(ego
					view: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setPri: -1
					loop: 2
				)
				(= cycles 1)
			)
			(6
				(SpeakAudio 30)
				(HandsOn)
				(= cycles 1)
			)
			(7 (client setScript: 0))
		)
	)
)

(instance sailBoat of Actor
	(properties
		x 280
		y 173
		view 618
		cel 1
		priority -1
		signal (| ignrAct stopUpdOn)
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(cond 
						((and (not (Btst fCedricInjured)) (Btst 54) (Btst 71)) (SpeakAudio 24))
						((and (not (Btst fCedricInjured)) (Btst 54)) (SpeakAudio 25))
						(
						(or (> (ego distanceTo: self) 40) (not (ego has: 18))) (SpeakAudio 26))
						(else (SpeakAudio 27))
					)
					(event claimed: 1)
				)
				(verbDo
					(if (not (Btst fFailedBoatCP))
						(curRoom setScript: leave)
						(event claimed: true)
					else
						(KQPrint 220 0)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iWax
							(event claimed: TRUE)
							(curRoom setScript: fixBoat)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 31)
							(event claimed: TRUE)
						)
					)
				)
				(verbTalk
					(if (and (not (Btst fCedricInjured)) (Btst 54))
						(SpeakAudio 3003)
						(event claimed: TRUE)
					else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance sail of Actor
	(properties
		z 15
		view 618
		loop 3
		detailLevel 3
	)
	
	(method (doit)
		(if (sailBoat mover?)
			(sail posn: (+ (sailBoat x?) 8) (sailBoat y?))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(cond 
						((and (not (Btst fCedricInjured)) (Btst 54))
							(SpeakAudio 28)
						)
						((or (> (ego distanceTo: self) 40) (not (ego has: iWax)))
							(SpeakAudio 26)
						)
						(else
							(SpeakAudio 27)
						)
					)
					(event claimed: TRUE)
				)
				(verbDo
					(if (not (Btst fFailedBoatCP))
						(curRoom setScript: leave)
						(event claimed: TRUE)
					else
						(SpeakAudio 29)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iWax
							(event claimed: TRUE)
							(curRoom setScript: fixBoat)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 31)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance poly5 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance wake of Prop
	(properties
		view 618
		loop 8
	)
	
	(method (doit)
		(super doit:)
		(if (sailBoat mover?)
			(self x: (sailBoat x?) y: (sailBoat y?))
		)
	)
)

(instance bird of Actor)

(instance flunkedProtection of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					view: 615
					posn: (- (ego x?) 31) (ego y?)
					loop: 2
					setPri: (+ (sailBoat priority?) 1)
				)
				(ego
					cel: (- (NumCels ego) 1)
					cycleSpeed: 2
					setCycle: BegLoop self
				)
				(sail setPri: (sailBoat priority?))
			)
			(1
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					setPri: -1
					ignoreActors: 0
					setLoop: -1
					illegalBits: cWHITE
					cycleSpeed: 0
					setStep: 3 2
				)
				((ego head?) show:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
