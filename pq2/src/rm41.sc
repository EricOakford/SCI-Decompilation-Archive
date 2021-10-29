;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include sci.sh)
(use Main)
(use jet)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm41 0
)

(local
	local0
	[str 100]
	local101
	local102
	local103
	newProp
)
(instance hijackMusic of Sound
	(properties
		number 36
		priority 2
		loop -1
	)
)

(instance hijackGunFire of Sound
	(properties
		number 41
		priority 5
	)
)

(instance hijacker1Timer of Timer
	(properties)
)

(instance hijacker2Timer of Timer
	(properties)
)

(instance willKillTimer of Timer
	(properties)
)

(instance rm41 of Room
	(properties
		picture 40
		style $0000
	)
	
	(method (init)
		(Load rsVIEW 0)
		(Load rsVIEW 4)
		(Load rsVIEW 6)
		(Load rsVIEW 26)
		(Load rsVIEW 82)
		(Load rsVIEW 83)
		(Load rsVIEW 84)
		(Load rsVIEW 23)
		(Load rsVIEW 24)
		(Load rsVIEW 20)
		(Load rsSOUND 36)
		(Load rsSOUND 41)
		(Load rsSOUND 41)
		(super init:)
		(self setLocales: 154)
		(ego
			view: 82
			setLoop: 3
			setCel: 255
			ignoreActors:
			illegalBits: 0
			posn: 212 72
			setPri: 3
			init:
		)
		((= keith (Actor new:))
			view: 82
			illegalBits: 0
			ignoreActors:
			setPri: 0
			setLoop: 4
			setCel: 255
			posn: 200 68
			init:
		)
		((= stewardess (Actor new:))
			view: 26
			posn: 255 77
			loop: 2
			cel: 0
			setCycle: Walk
			init:
		)
		((= newProp (Prop new:))
			view: 82
			posn: 271 55
			loop: 8
			cel: 0
			setPri: 0
			ignoreActors:
			stopUpd:
			cycleSpeed: 1
			init:
		)
		((Prop new:)
			view: 82
			posn: 51 191
			loop: 2
			cel: 0
			setPri: 15
			addToPic:
		)
		((= hijacker1 (Actor new:))
			view: 24
			posn: 60 1060
			loop: 1
			cel: 0
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
			init:
		)
		((= hijacker2 (Actor new:))
			view: 23
			posn: 70 1060
			loop: 1
			cel: 0
			setCycle: Walk
			illegalBits: 0
			ignoreActors:
			init:
		)
		(GoToBathroom)
		(= sittingInPlane 1)
		(= local101 1)
		(= gunNotNeeded 0)
		(= gunFireState 0)
		(HandsOn)
		(User canControl: 0)
		(curRoom setScript: hijackersApproach)
	)
	
	(method (dispose)
		(hijacker1Timer dispose: delete:)
		(hijacker2Timer dispose: delete:)
		(willKillTimer dispose: delete:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
				(cond 
					((== (= temp0 (event message?)) 16896)
						(event claimed: 1)
						(cond 
							((not (ego has: 0)) (Print 41 0))
							((== local101 1) (Print 41 1))
							((== local101 0) 0)
							(wearingSeatbelt (AirplanePrint 41 2))
							(sittingInPlane
								(= local102 1)
								(= gunDrawn 1)
								(= global205 0)
								(ego setScript: egoAction)
								(egoAction changeState: 1)
							)
							(else (event claimed: 0))
						)
					)
					((== temp0 KEY_F10)
						(event claimed: 1)
						(cond 
							((not (ego has: 0)) (DontHaveGun))
							((not gunDrawn) (Print 41 3))
							((== local101 1) (Print 41 1))
							((or (== local101 0) sittingInPlane) (event claimed: 1))
							((== [numAmmoClips bulletsInGun] 0) (Print 41 4 #time 3))
							(else (event claimed: 0))
						)
					)
				)
			)
		)
	)
)

(instance hijackersApproach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hijacker2 setScript: hijacker2Approach)
				(= cycles 10)
			)
			(1
				(hijacker1 posn: 60 160 setMotion: MoveTo 235 72 self)
			)
			(2
				(hijacker1 setMotion: MoveTo 262 72 self)
			)
			(3
				(hijacker1 loop: 2)
				(hijacker1 setScript: hijacker1Actions)
				(curRoom setScript: StageThree)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '/hijacker') (AirplanePrint 41 5))
							((Said '/attendant') (AirplanePrint 41 6))
							((Said '/men,dude') (AirplanePrint 41 7))
							((Said '/friend') (AirplanePrint 41 8))
							((Said '/passenger') (AirplanePrint 41 9))
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/attendant') (AirplanePrint 41 10))
							((Said '/friend') (AirplanePrint 41 11))
							((Said '/passenger') (AirplanePrint 41 12))
						)
					)
				)
			)
		)
	)
)

(instance hijacker2Approach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hijacker2 posn: 70 160 setMotion: MoveTo 78 148 self)
			)
			(1
				(hijacker2 setMotion: MoveTo 245 72 self)
			)
			(2
				(hijacker2 setMotion: MoveTo 268 60 self)
			)
			(3
				(newProp startUpd: setCycle: EndLoop self)
			)
			(4
				(newProp stopUpd:)
				(hijacker2 setPri: 0 setMotion: MoveTo 280 50 self)
			)
			(5
				(newProp startUpd: setCycle: BegLoop self)
				(hijacker2 posn: 280 1050)
				(hijacker2 setScript: 0)
			)
		)
	)
)

(instance StageThree of Script
	(properties)
	
	(method (init)
		(Load rsVIEW 24)
		(Load rsVIEW 23)
		(Load rsVIEW 26)
		(Load rsVIEW 27)
	)
	
	(method (doit &tmp temp0)
		(User canControl: 0)
		(if global205
			(= temp0 global205)
			(= global205 0)
			(= local103 1)
			(if (== temp0 2)
				(switch local101
					(3
						(if (not (stewardess script?))
							(stewardess setScript: stewardessDies)
						)
					)
					(4
						(= local101 2)
						(cond 
							((or egoDrunk (not gunSightsAligned)) (egoKilledByOne changeState: 0))
							((< (hijacker1Actions state?) 8) (hijacker1Actions changeState: 8))
						)
					)
					(5
						(cond 
							((or egoDrunk (not gunSightsAligned)) (hijacker2Actions changeState: 5))
							((< (hijacker2Actions state?) 8) (hijacker2Actions changeState: 8))
						)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'kill,beat') (AirplanePrint 41 13))
					((Said 'fire/hijacker,dude') (Print 41 14))
					((Said 'stand,(get<up)')
						(cond 
							((not sittingInPlane) (AirplanePrint 41 15))
							(wearingSeatbelt (AirplanePrint 41 2))
							(else (ego setScript: egoAction) (egoAction changeState: 1))
						)
					)
					((Said 'sat')
						(if (not sittingInPlane)
							(ego setScript: egoAction)
							(egoAction changeState: 3)
						else
							(AirplanePrint 41 16 67 20 20 33 smallFont)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/hijacker,dude') (AirplanePrint 41 17))
							((Said '/attendant') (AirplanePrint 41 18))
							((Said '/9mm') (AirplanePrint 41 19))
							((Said '/friend') (AirplanePrint 41 20))
							((Said '/passenger') (AirplanePrint 41 21))
							((Said '[<at,around][/(!*,chamber,airplane)]') (AirplanePrint 41 22))
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/hijacker') (AirplanePrint 41 23))
							((Said '/attendant') (AirplanePrint 41 24))
							((Said '/friend') (AirplanePrint 41 25))
							((Said '/passenger') (AirplanePrint 41 26))
						)
					)
				)
			)
		)
	)
)

(instance egoAction of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego
					view: 82
					ignoreActors:
					posn: 212 72
					setLoop: 3
					setCel: 4
					cycleSpeed: 1
					setMotion: 0
					setPri: 3
					setCycle: BegLoop self
				)
			)
			(2
				(ego
					view: (if (not local102) 0 else 6)
					posn: 229 59
					setLoop: -1
					loop: 0
					setCel: 0
					setPri: -1
					cycleSpeed: 0
					ignoreActors: 0
					illegalBits: -32768
					setCycle: Walk
				)
				(if
					(and
						(== (hijacker1 script?) hijacker1Actions)
						(< (hijacker1Actions state?) 7)
					)
					(willKillTimer setCycle: egoKilledByOne 35)
				)
				(= local102 0)
				(= sittingInPlane 0)
				(HandsOn)
				(RedrawCast)
				(User canControl: 0)
				(client setScript: 0)
			)
			(3
				(= sittingInPlane 1)
				(HandsOff)
				(willKillTimer dispose: delete:)
				(ego
					view: 82
					setLoop: 3
					setCel: 0
					ignoreActors:
					illegalBits: 0
					posn: 212 72
					cycleSpeed: 1
					setMotion: 0
					setPri: 3
					setCycle: EndLoop self
				)
			)
			(4
				(HandsOn)
				(ego cycleSpeed: 0)
				(User canControl: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance stewardessDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 3)
				(hijacker1Timer dispose: delete:)
				(willKillTimer dispose: delete:)
				(egoKilledByOne changeState: 0)
				(cond 
					((== (stewardess loop?) 1)
						(stewardess
							view: 83
							loop: 4
							cel: 0
							posn: 251 76
							setCycle: EndLoop self
						)
					)
					((!= local0 0)
						(stewardess
							view: 83
							loop: 4
							posn: 251 76
							setCycle: EndLoop self
						)
					)
					(else (Print 41 27) (self cue:))
				)
			)
			(1
				(stewardess posn: 251 76 loop: 4 setCel: 255)
				(User canControl: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance egoIsDying of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 83
					loop: 6
					cel: 0
					illegalBits: 0
					ignoreActors:
					posn: (- (ego x?) 15) (+ (ego y?) 2)
					setPri: 3
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(ego setPri: 0 setCycle: CycleTo 4 1 self)
			)
			(2 (Timer setReal: self 2))
			(3 (EgoDead @str))
		)
	)
)

(instance egoKilledByOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local101 0)
				(hijacker1Timer dispose: delete:)
				(willKillTimer dispose: delete:)
				(ego cel: 7)
				(hijacker1
					view: 83
					loop: 5
					cel: 0
					posn: (- (hijacker1 x?) 14) (hijacker1 y?)
					setCycle: EndLoop self
				)
			)
			(1
				(hijackMusic stop:)
				(hijackGunFire play:)
				(if local103
					(cond 
						((== local0 3) (Format @str 41 28))
						(egoDrunk (Format @str 41 29))
						(else (Format @str 41 30))
					)
				else
					(Format @str 41 31)
				)
				(ego setScript: egoIsDying)
			)
		)
	)
)

(instance hijacker1Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stewardess posn: (stewardess x?) 1000)
				(hijacker1
					view: 83
					loop: 0
					cel: 0
					posn: 256 76
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(hijacker1
					view: 83
					loop: 7
					cel: 0
					posn: 273 75
					setCycle: Forward
					cycleSpeed: 2
				)
				(stewardess
					view: 83
					loop: 1
					cel: 0
					posn: 253 76
					show:
					cycleSpeed: 2
					setCycle: Forward
				)
				(= cycles 10)
			)
			(2
				(= local101 3)
				(hijackMusic play:)
				(AirplanePrint 41 32)
				(AirplanePrint 41 33)
				(AirplanePrint 41 34)
				(AirplanePrint 41 35)
				(AirplanePrint 41 36)
				(AirplanePrint 41 37)
				(AirplanePrint 41 38)
				(hijacker1Timer setCycle: self 40)
			)
			(3
				(= local101 4)
				(stewardess
					loop: 2
					cel: 0
					posn: 251 76
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(= local0 1)
			)
			(4
				(= local0 0)
				(hijacker1Timer setCycle: self 50)
			)
			(5
				(cond 
					((> (egoKilledByOne state?) 0) 0)
					((not sittingInPlane) (egoKilledByOne changeState: 0))
					(else
						(= local0 2)
						(stewardess
							view: 84
							loop: 3
							cel: 0
							posn: 256 76
							cycleSpeed: 0
							setCycle: EndLoop self
						)
						(hijacker1
							view: 84
							loop: 2
							cel: 0
							posn: 256 76
							cycleSpeed: 0
							setCycle: EndLoop
						)
						(= local101 3)
					)
				)
			)
			(6
				(= local0 0)
				(stewardess
					view: 83
					posn: 253 76
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(hijacker1
					view: 83
					posn: 273 75
					loop: 7
					cel: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(hijacker1Timer setCycle: self 80)
			)
			(7
				(= local101 0)
				(HandsOff)
				(hijackMusic fade:)
				(EgoDead
					{ Because you failed to take action, the hijackers have succeeded with their sinister plot. You will live out the rest of your life in Bum Suk Egypt.}
				)
			)
			(8
				(hijackMusic stop:)
				(= local101 2)
				(hijacker1Timer dispose: delete:)
				(willKillTimer dispose: delete:)
				(hijacker1
					view: 84
					illegalBits: 0
					ignoreActors:
					loop: 0
					cel: 0
					posn: 263 75
					setCycle: EndLoop self
				)
			)
			(9
				(hijacker1 setPri: 1)
				(hijacker2 setScript: hijacker2Actions)
				(Bset 125)
				(SolvePuzzle 6)
				(client setScript: 0)
			)
		)
	)
)

(instance hijacker2Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hijacker2Timer setReal: self 1)
			)
			(1 (newProp setCycle: EndLoop self))
			(2
				(= local101 5)
				(newProp setCycle: BegLoop)
				(hijacker2
					view: 27
					loop: 2
					illegalBits: 0
					ignoreActors:
					posn: 280 60
					setCycle: Walk
					setMotion: MoveTo 268 60 self
				)
			)
			(3
				(hijacker2 loop: 0)
				(hijacker2Timer setReal: self 2)
			)
			(4
				(hijacker2 loop: 5)
				(hijacker2Timer setCycle: self 4)
			)
			(5
				(HandsOff)
				(= local101 0)
				(hijacker2 setCycle: EndLoop self)
			)
			(6
				(hijackGunFire play:)
				(hijacker2 setCycle: BegLoop)
				(Format @str 41 39)
				(if sittingInPlane
					(ego
						view: 83
						loop: 6
						setCel: 255
						illegalBits: 0
						ignoreActors:
						posn: (+ (ego x?) 8) (- (ego y?) 5)
						setPri: 2
					)
					(ego setScript: egoIsDying)
				else
					(ego setScript: egoIsDying)
				)
			)
			(8
				(HandsOff)
				(= local101 2)
				(hijacker1Timer dispose: delete:)
				(hijacker2Timer dispose: delete:)
				(hijacker2
					view: 84
					loop: 1
					cel: 0
					setPri: (+ (hijacker2 priority?) 1)
					setCycle: EndLoop self
				)
			)
			(9
				(hijacker2 setPri: 2)
				(hijacker2Timer setReal: self 3)
			)
			(10
				(AirplanePrint 41 40)
				(AirplanePrint 41 41)
				(SolvePuzzle 3)
				(cSound number: 25 loop: -1 play:)
				(client setScript: 0)
				(curRoom setScript: intoCockpit)
			)
		)
	)
)

(instance intoCockpit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(HandsOff)
				(= local101 0)
				(if gunDrawn
					(= gunDrawn 0)
					(ego
						view: 4
						loop: (mod (ego loop?) 4)
						setCel: 255
						setCycle: BegLoop self
					)
				else
					(self cue:)
				)
			)
			(2
				(ego view: 0)
				(AirplanePrint 41 42)
				(AirplanePrint 41 43)
				(= seconds 2)
			)
			(3
				(stewardess
					view: 84
					loop: 4
					cel: 0
					posn: 251 76
					setCycle: EndLoop self
				)
			)
			(4
				(stewardess
					view: 26
					loop: 1
					setCel: 0
					ignoreActors:
					illegalBits: 0
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 238 66 self
				)
			)
			(5
				(AirplanePrint 41 44)
				(= seconds 2)
			)
			(6
				(stewardess setMotion: MoveTo 268 55 self)
			)
			(7
				(newProp setPri: 0 setCycle: EndLoop self)
			)
			(8
				(newProp stopUpd:)
				(stewardess setPri: 0 setMotion: MoveTo 275 50 self)
			)
			(9
				(stewardess hide:)
				(newProp setCycle: BegLoop)
				(keith setCycle: BegLoop self)
			)
			(10
				(keith
					view: 20
					loop: 0
					setPri: 2
					cycleSpeed: 0
					posn: 223 57
					ignoreActors:
					illegalBits: 0
					setCycle: Walk
				)
				(RedrawCast)
				(AirplanePrint 41 45)
				(AirplanePrint 41 46)
				(= seconds 1)
			)
			(11
				(keith setMotion: MoveTo 265 58 self)
			)
			(12
				(newProp setCycle: EndLoop self)
			)
			(13
				(newProp stopUpd:)
				(keith setPri: 0 setMotion: MoveTo 275 50 self)
			)
			(14
				(keith hide:)
				(newProp setCycle: BegLoop self)
			)
			(15
				(HandsOn)
				(curRoom newRoom: 42)
			)
		)
	)
)
