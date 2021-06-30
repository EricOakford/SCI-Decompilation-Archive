;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include game.sh) (include "335.shm")
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Scaler)
(use PolyPath)
(use Feature)
(use StopWalk)
(use Motion)
(use System)

(public
	rm335 0
)

(instance rm335 of LBRoom
	(properties
		noun N_ROOM
		picture 335
		north 350
		south 330
	)
	
	(method (init)
		(ego
			init:
			normalize: (if (ego wearingGown?) 831 else 830)
			setScale: Scaler 100 75 190 160
		)
		(self setRegions: 90 93)
		(switch prevRoomNum
			(north
				(Palette PALIntensity 0 255 60)
				(if (and (TriggerEvent 8) (not (Btst 133)))
					(frontDoor
						cel: 255
						doorState: 2
						enterType: 2
						forceClose: 0
					)
					(self setScript: sOffToSmooch)
				else
					(frontDoor forceOpen: 1)
					(ego
						posn: (frontDoor moveToX?) (frontDoor moveToY?)
						edgeHit: 0
					)
				)
			)
			(south
				(self setScript: sEnterSouth)
				(cond 
					((!= currentAct 2))
					((Btst 25) (theMusic number: 335 flags: 1 setLoop: -1 play: 50))
					(else (theMusic number: 19 flags: 1 setLoop: -1 play:))
				)
			)
			(else 
				(ego posn: 150 180)
				(theGame handsOn:)
			)
		)
		(super init:)
		(southExitFeature init:)
		(frontDoor init: setPri: 12)
		(otherDoor init:)
		(banner init: approachVerbs: 4 8)
		(glass setOnMeCheck: 1 8192 init:)
		(column setOnMeCheck: 1 16384 init:)
		(if (and (== currentAct 2) (not (Btst 25)))
			((ScriptID 32 0)
				init:
				view: 341
				room: curRoomNum
				posn: 131 173 0
				setLoop: 0
				actions: doorActions
				approachX: 137
				approachY: 180
				approachVerbs: 4 11 2 6
			)
		else
			(frontDoor locked: (!= currentAct 2))
		)
	)
	
	(method (newRoom)
		(theMusic fade:)
		(super newRoom: &rest)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 165 240 setMotion: PolyPath 165 185 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(frontDoor caller: self open:)
			)
			(1
				(ego
					setMotion: PolyPath (frontDoor moveToX?) (frontDoor moveToY?) self
				)
			)
			(2
				(DrawPic 780 FADEOUT)
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(3
				(Palette PALIntensity 0 255 100)
				((ScriptID 21 0) doit: 265)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sGiveInvite of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 32 0)
					setLoop: 0
					cycleSpeed: 6
					setCycle: CycleTo 8 1 self
				)
			)
			(1 (= ticks 15))
			(2
				((ScriptID 32 0) setCycle: EndLoop self)
			)
			(3
				((ScriptID 32 0) setCel: 0)
				(= cycles 2)
			)
			(4
				(messager say: 6 11 0 0 self)
			)
			(5
				(Bset 23)
				(ego put: 6)
				((ScriptID 21 1) doit: 775)
				((ScriptID 22 0) doit: 28673)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOffToSmooch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 153 160
					setScale: 0
					setStep: 3 2
					setMotion: MoveTo 160 250 self
				)
				(= cycles 1)
			)
			(1
				(DrawPic 335)
				(Palette PALIntensity 0 255 60)
				(= ticks 30)
			)
			(2
				((ScriptID 93 8)
					init:
					setCycle: StopWalk -1
					posn: 153 160
					setStep: 3 2
					setSpeed: 6
					setMotion: MoveTo 130 250
				)
			)
			(3 (curRoom newRoom: 330))
		)
	)
)

(instance frontDoor of LbDoor
	(properties
		x 164
		y 162
		noun 3
		approachX 152
		approachY 173
		view 336
		moveToX 152
		moveToY 152
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((not (ego wearingGown?)) (messager say: noun 4 6))
					((not (Btst 23)) (messager say: noun 4 7))
					(else (curRoom setScript: sExitNorth))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 80
		nsBottom 189
		nsRight 290
		cursor 11
		exitDir 3
		noun 4
	)
)

(instance banner of Feature
	(properties
		x 215
		y 123
		noun 1
		nsTop 80
		nsLeft 203
		nsBottom 137
		nsRight 258
		sightAngle 40
	)
)

(instance glass of Feature
	(properties
		x 140
		y 70
		noun 2
		sightAngle 40
	)
)

(instance otherDoor of Feature
	(properties
		x 131
		y 141
		noun 954
		nsTop 115
		nsLeft 121
		nsBottom 167
		nsRight 142
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(frontDoor doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance column of Feature
	(properties
		x 30
		y 170
		noun 8
		sightAngle 40
	)
)

(instance doorActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (messager say: 6 1) 1)
				(4 (messager say: 6 4) 1)
				(11
					(curRoom setScript: sGiveInvite)
				)
				(2
					(if (ego has: 6)
						(messager say: 6 2 7)
					else
						(messager say: 6 2)
					)
					1
				)
				(6 (messager say: 6 2) 1)
				(else  (return 0))
			)
		)
	)
)
