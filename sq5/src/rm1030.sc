;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1030)
(include game.sh) (include "1030.shm")
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1030 0
)

(local
	[pathX 2] = [210 192]
	[pathY 2] = [141 108]
	local4
	local5
	local6
)
(procedure (localproc_0052)
	(= global144 (= global143 (Random 0 3)))
	(doortop setCel: (doortop lastCel:))
	(curRoom setScript: sExitSubfloor 0 0)
)

(procedure (localproc_0080)
	(= global144 (= global143 (* 2 (Random 0 1))))
	(doorbtm setCel: (doorbtm lastCel:))
	(curRoom setScript: sExitSubfloor 0 1)
)

(instance rm1030 of Room
	(properties
		noun N_ROOM
		picture 124
		style (| BLACKOUT FADEOUT)
		vanishingX 179
		vanishingY 83
	)
	
	(method (init)
		(LoadMany RES_VIEW 670)
		(ego y: 189)
		(switch goliathFloor
			(1 (= local4 3) (= local5 4))
			(2 (= local4 3) (= local5 4))
			(3 (= local4 3) (= local5 4))
			(4
				(if (== global134 3)
					(= local4 3)
					(= local5 4)
				else
					(= local4 6)
					(= local5 7)
				)
			)
			(5 (= local4 6) (= local5 7))
			(6
				(if (== global134 2)
					(= local4 6)
					(= local5 7)
				else
					(= local4 8)
					(= local5 9)
				)
			)
			(7
				(cond 
					((== global134 3) (= local4 7) (= local5 8))
					((== global134 2) (= local4 6) (= local5 7))
					(else (= local4 8) (= local5 9))
				)
			)
			(8
				(if (== global134 3)
					(= local4 7)
					(= local5 8)
				else
					(= local4 8)
					(= local5 9)
				)
			)
			(9 (= local4 8) (= local5 9))
		)
		(switch prevRoomNum
			(1020
				(switch goliathFloor
					(3 (localproc_0052))
					(4 (localproc_0080))
					(6 (localproc_0052))
					(7
						(if (== global134 3)
							(localproc_0052)
						else
							(localproc_0080)
						)
					)
					(8
						(if (== global134 3)
							(localproc_0080)
						else
							(localproc_0052)
						)
					)
					(9 (localproc_0080))
				)
			)
			(else 
				(ego
					view: 670
					setLoop: -1
					setLoop: 0
					cel: 0
					x: 218
					y: 159
					init:
					setScale: Scaler 111 26 158 96
					setCycle: Walk
				)
			)
		)
		(doortop init:)
		(doorbtm init:)
		(turbbtm init:)
		(turboDoors init: setOnMeCheck: ftrControl cYELLOW)
		(ladder init: setOnMeCheck: ftrControl cBROWN)
		(switch global143
			(0
				(liftTop scaleX: 90 scaleY: 90 init: setScript: sLift)
			)
			(1
				(liftTop scaleX: 160 scaleY: 160 init: setScript: sLift)
			)
			(else 
				(liftTop
					scaleX: 320
					scaleY: 320
					init:
					hide:
					setScript: sLift
				)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						228 171
						179 84
						181 91
						227 178
					yourself:
				)
		)
		(super init:)
		(directionHandler addToFront: doorbtm)
	)
	
	(method (doit)
		(super doit: &rest)
		(Palette PALCycle 78 82 1 231 235 1)
		(cond 
			((and (!= global144 global143) (== local6 0))
				(theMusic3 number: 128 setLoop: -1 play:)
				(= local6 1)
			)
			((and (== global144 global143) (== local6 1))
				(theMusic3 stop:)
				(= local6 0)
			)
		)
		(cond 
			((IsObjectOnControl ego cLMAGENTA)
				(ego y: (+ (ego y?) 1) setMotion: 0)
				(messager say: N_AT_BOTTOM NULL NULL 0)
			)
			(
				(and
					(> (ego y?) 160)
					(not (curRoom script?))
					(not (ego script?))
				)
				(if (and (== global134 3) (> goliathFloor 6))
					(messager say: N_AT_TOP NULL NULL 0)
					(ego setMotion: 0 x: 215 y: 151)
				else
					(curRoom newRoom: 1035)
				)
			)
		)
	)
	
	(method (dispose)
		(theMusic3 dispose:)
		(directionHandler delete: doorbtm)
		(if (!= global144 global143)
			(= global144 0)
			(= global143 0)
		)
		(super dispose: &rest)
	)
)

(instance sEnterSubfloor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath [pathX register] [pathY register] self
				)
			)
			(1
				(ego setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2
				(if (== register 0)
					(doortop setCycle: EndLoop self)
				else
					(doorbtm setCycle: EndLoop self)
				)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(3 (ego setCycle: EndLoop self))
			(4
				(if (== register 0)
					(= goliathFloor local4)
				else
					(= goliathFloor local5)
				)
				(curRoom newRoom: 1020)
				(self dispose:)
			)
		)
	)
)

(instance sExitSubfloor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(ego
					view: 670
					setLoop: -1
					setLoop: 2
					setCel: (ego lastCel:)
					x: [pathX register]
					y: [pathY register]
					setScale: Scaler 111 26 158 96
					init:
					setCycle: CycleTo 3 -1 self
				)
			)
			(2
				(if (== register 0)
					(doortop setCycle: BegLoop self)
				else
					(doorbtm setCycle: BegLoop self)
				)
				(theMusic2 number: 103 setLoop: 1 play:)
				(ego setCycle: BegLoop self)
			)
			(3
				(theGame handsOn:)
				(ego
					view: 670
					setLoop: -1
					setLoop: 0
					setCycle: Walk
					cel: 0
				)
				(self dispose:)
			)
		)
	)
)

(instance sLift of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(>
					(liftTop nsBottom?)
					(- (ego y?) (/ (ego scaleX?) 18))
				)
				(not (ego script?))
			)
			(if (== global144 global143)
				(= global144 3)
				(= seconds 0)
				(= state 1)
				(self cue:)
			)
			(ego setScript: sSquishRog)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego script?) (self dispose:) else (= seconds 15))
			)
			(1
				(switch global143
					(0 (= global144 (Random 1 3)))
					(1
						(if (Random 0 1)
							(= state 8)
							(= global144 0)
						else
							(= global144 (Random 2 (= state 3)))
						)
					)
					(else 
						(= state 5)
						(= global144 (Random 0 1))
					)
				)
				(= ticks 1)
			)
			(2
				(if (< (liftTop scaleX?) 160)
					(liftTop scaleX: (+ (liftTop scaleX?) 10))
					(liftTop scaleY: (+ (liftTop scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(3
				(if (== (= global143 1) global144) (= state -1))
				(= cycles 1)
			)
			(4
				(if (< (liftTop scaleX?) 320)
					(liftTop scaleX: (+ (liftTop scaleX?) 10))
					(liftTop scaleY: (+ (liftTop scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(5
				(= global143 global144)
				(liftTop hide:)
				(= state -1)
				(= cycles 1)
			)
			(6
				(liftTop show: scaleX: 320 scaleY: 320)
				(= cycles 1)
			)
			(7
				(if (> (liftTop scaleX?) 160)
					(liftTop scaleX: (- (liftTop scaleX?) 10))
					(liftTop scaleY: (- (liftTop scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(8
				(if (== (= global143 1) global144) (= state -1))
				(= cycles 1)
			)
			(9
				(if (> (liftTop scaleX?) 90)
					(liftTop scaleX: (- (liftTop scaleX?) 10))
					(liftTop scaleY: (- (liftTop scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(10
				(= global143 0)
				(= state -1)
				(= cycles 1)
			)
			(11 (self dispose:))
		)
	)
)

(instance sSquishRog of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> global143 global144)
					(= global144 0)
				else
					(= global144 3)
					(= state 1)
				)
				(= cycles 1)
			)
			(1
				(if (< (liftTop nsBottom?) (ego y?)) (-- state))
				(= cycles 1)
			)
			(2
				(ego view: 671 setLoop: -1 setLoop: 0 cel: 0 setCycle: 0)
				(= ticks 1)
			)
			(3
				(if (liftTop script?) (-- state))
				(= cycles 1)
			)
			(4 (ego setCycle: EndLoop self))
			(5
				(EgoDead deathSQUISHED)
				(self dispose:)
			)
		)
	)
)

(instance liftTop of Actor
	(properties
		x 167
		y 70
		noun N_LIFT_TOP
		view 670
		loop 5
		scaleSignal $0001
		scaleX 90
		scaleY 90
	)
	
	(method (doit)
		(super doit: &rest)
		(if (ego script?)
			(self setPri: 14)
		else
			(self setPri: (CoordPri nsBottom))
		)
	)
)

(instance doortop of Actor
	(properties
		x 157
		y 134
		noun N_VENT
		view 670
		loop 7
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (ego script?))
					(curRoom setScript: sEnterSubfloor 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorbtm of Actor
	(properties
		x 165
		y 106
		noun N_VENT
		view 670
		loop 9
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(user canControl:)
					(& (event type?) direction)
					(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
				)
				(switch (event message?)
					(dirStop
						(ego setMotion: 0)
					)
					(dirN
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 186 98)
						)
					)
					(dirNE
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 186 98)
						)
					)
					(dirSE
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 221 161)
						)
					)
					(dirS
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 221 161)
						)
					)
					(dirSW
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 221 161)
						)
					)
					(dirNW
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 186 98)
						)
					)
				)
				(event claimed: 1)
				(return 1)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (ego script?))
					(curRoom setScript: sEnterSubfloor 0 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance turbbtm of View
	(properties
		x 176
		y 81
		view 670
		loop 11
	)
)

(instance turboDoors of Feature
	(properties
		x 160
		y 120
		noun N_DOOR
		onMeCheck cYELLOW
	)
)

(instance ladder of Feature
	(properties
		x 220
		y 130
		noun N_LADDER
		onMeCheck cBROWN
	)
)

(instance theMusic3 of Sound
	(properties
		flags mNOPAUSE
	)
)
