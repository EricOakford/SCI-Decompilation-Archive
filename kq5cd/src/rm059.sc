;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include sci.sh)
(use Main)
(use Intrface)
(use castle)
(use KQ5Room)
(use Polygon)
(use Block)
(use RFeature)
(use Motion)
(use System)

(public
	rm059 0
)

(local
	[local0 8] = [0 173 19 173 5 189 0 189]
	[local8 30] = [0 0 319 0 319 189 309 189 309 181 289 161 268 157 243 163 227 156 94 156 68 162 52 157 38 158 31 165 0 165]
)
(instance rm059 of KQ5Room
	(properties
		picture 59
		south 60
		west 58
	)
	
	(method (init)
		(super init:)
		(= global357 285)
		(= global358 174)
		(= global355 68)
		(= global356 171)
		(= magicDoorX 296)
		(= magicDoorY 172)
		(Load rsVIEW 896)
		(self
			setFeatures: firePlace birdMan1 birdMan2 table doorWay room
			setRegions: 550
			addObstacle: poly1 poly2
		)
		(switch prevRoomNum
			(south
				(cond 
					((< (ego x?) 150) (ego x: 67))
					((> (ego x?) 255) (ego x: 292))
					((> (ego x?) 220) (ego x: 256))
					(else (ego x: 155))
				)
				(curRoom setScript: enterBottom)
			)
			(else 
				(curRoom setScript: enterLeft)
			)
		)
		(ego init:)
		(if (and (== prevRoomNum 60) (== henchmanState 3))
			(= henchmanState 5)
		)
		(cond 
			((== henchmanState 5)
				(Load rsVIEW 896)
				(Load rsVIEW 904)
				(Load rsSOUND 135)
				(curRoom setScript: caughtScript)
			)
			((== henchmanState 8)
				((ScriptID 550 3)
					init:
					posn: gTheHenchManX gTheHenchManY
					setScript: 0
					view: 894
					setLoop: 2
					setCel: 255
					show:
					stopUpd:
				)
			)
			(
				(and
					(not wizardTimer)
					(not henchmanTimer)
					(> (Random 0 100) 25)
				)
				((ScriptID 550 3) init:)
			)
		)
		(poly1 points: @local0 size: 4)
		(poly2 points: @local8 size: 15)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(== wizardState 3)
					(not ((ScriptID 550 7) script?))
					(ego inRect: 97 158 224 187)
				)
				(if (Random 0 1)
					(= wizardX 43)
					(= wizardY 168)
					(= wizardAngle 135)
					(= global354 225)
				else
					(= wizardX 288)
					(= wizardY 169)
					(= wizardAngle 225)
					(= global354 135)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			((ego inRect: -10 160 12 173) (curRoom setScript: exitLeft))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom setScript: exitDown)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance firePlace of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 627)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 628)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay of RFeature
	(properties
		nsTop 40
		nsLeft 5
		nsBottom 170
		nsRight 27
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 607)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance birdMan1 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 625)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 629)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance birdMan2 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 625)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 629)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 626)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance table of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 627)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 630)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance exitLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo -10 168 self
				)
			)
			(1
				(if
					(or
						(> (ego distanceTo: (ScriptID 550 3)) 50)
						(< henchmanState 3)
						(== henchmanState 8)
					)
					(= henchmanState 0)
					(curRoom newRoom: 58)
				)
			)
		)
	)
)

(instance enterLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					illegalBits: 0
					posn: -17 170
					setMotion: MoveTo 20 170 self
				)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterBottom of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					illegalBits: 0
					posn: (ego x?) 249
					setMotion: MoveTo (ego x?) 188 self
				)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance caughtScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= henchmanState 4)
				(CastleHandsOff)
				(ego normal: 0 hide:)
				((ego head?) hide:)
				((ScriptID 550 3)
					init:
					setScript: 0
					show:
					posn: (ego x?) (ego y?)
					view: 896
					setLoop: -1
					setCycle: Walk
					setStep: 4 2
					cycleSpeed: 0
					illegalBits: 0
					ignoreActors: 1
					moveSpeed: 1
					setMotion: MoveTo magicDoorX magicDoorY self
				)
				(theMusic number: 135 loop: -1 playBed:)
				((ScriptID 550 4)
					init:
					show:
					setPri: 4
					posn: 310 179
					setLoop: 1
					cycleSpeed: 1
					setCycle: EndLoop
				)
				((ScriptID 550 5)
					init:
					setLoop: 1
					setPri: 5
					posn: 310 131
					hide:
				)
				(theAudio number: 8018 loop: 1 play:)
			)
			(1
				((ScriptID 550 3)
					view: 904
					setLoop: 1
					cel: 0
					cycleSpeed: 3
					setPri: 8
					setCycle: EndLoop self
				)
				((ScriptID 550 5)
					show:
					cycleSpeed: 1
					setScript: ringsScript
				)
			)
			(2
				(theMusic fade:)
				(= henchmanState 4)
				((ScriptID 550 3) hide:)
				((ScriptID 550 5) hide:)
				((ScriptID 550 4)
					setCel: 255
					cycleSpeed: 2
					setCycle: BegLoop self
				)
				(theAudio number: 8018 loop: 1 play:)
			)
			(3
				(theMusic3 stop:)
				((ScriptID 550 4) hide:)
				(curRoom newRoom: 67)
			)
		)
	)
)

(instance blockOne of Block
	(properties
		top 1000
		left -1000
		bottom 1002
		right -1002
	)
)

(instance exitDown of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(if
					(and
						(== henchmanState 3)
						(> ((ScriptID 550 3) y?) 200)
					)
					(= henchmanState 6)
				)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo (ego x?) 239 self
				)
			)
			(1
				(cond 
					((< (ego x?) 113) (ego x: 119))
					((> (ego x?) 220) (ego x: 243))
					(else (ego x: 190))
				)
				(if (!= henchmanState 4)
					(if (!= henchmanState 8) (= henchmanState 0))
					(curRoom newRoom: 60)
				)
			)
		)
	)
)

(instance ringsScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (< (DoSound sndDISPOSE) 8)
					((ScriptID 550 5) setCycle: EndLoop self)
				else
					(theAudio number: 8071 loop: 1 play: self)
					((ScriptID 550 5) setCycle: EndLoop)
				)
			)
			(1 (= cycles 5))
			(2 (self init:))
		)
	)
)
