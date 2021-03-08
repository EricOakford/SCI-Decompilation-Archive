;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1035)
(include game.sh) (include "1035.shm")
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
	rm1035 0
)

(local
	[pathX 4] = [123 131 99 123]
	[pathY 4] = [114 116 155 162]
	local8
	local9
	local10
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

(instance rm1035 of Room
	(properties
		noun N_ROOM
		picture 125
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 670)
		(ego y: 189)
		(switch goliathFloor
			(1 (= local8 1) (= local9 2))
			(2 (= local8 1) (= local9 2))
			(3 (= local8 1) (= local9 2))
			(4
				(if (== global134 3)
					(= local8 1)
					(= local9 2)
				else
					(= local8 4)
					(= local9 5)
				)
			)
			(5 (= local8 4) (= local9 5))
			(6
				(if (== global134 2)
					(= local8 4)
					(= local9 5)
				else
					(= local8 6)
					(= local9 7)
				)
			)
			(7
				(cond 
					((== global134 3) (= local8 0) (= local9 0))
					((== global134 2) (= local8 4) (= local9 5))
					(else (= local8 6) (= local9 7))
				)
			)
			(8
				(if (== global134 3)
					(= local8 0)
					(= local9 0)
				else
					(= local8 6)
					(= local9 7)
				)
			)
			(9 (= local8 6) (= local9 7))
		)
		(switch prevRoomNum
			(1020
				(if
				(or (== goliathFloor 1) (== goliathFloor 4) (== goliathFloor 6))
					(localproc_0052)
				else
					(localproc_0080)
				)
			)
			(else 
				(ego
					view: 670
					setLoop: -1
					setLoop: 1
					cel: 0
					x: 94
					y: 165
					init:
					setScale: Scaler 111 26 158 96
					setCycle: Walk
				)
			)
		)
		(doortop init:)
		(doorbtm init:)
		(turbtop init:)
		(turboDoors init: setOnMeCheck: ftrControl cYELLOW)
		(ladder init: setOnMeCheck: ftrControl cBROWN)
		(switch global143
			(3
				(liftBtm scaleX: 90 scaleY: 90 init: setScript: sLift)
			)
			(2
				(liftBtm scaleX: 160 scaleY: 160 init: setScript: sLift)
			)
			(else 
				(liftBtm
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
						88 180
						133 100
						129 102
						86 173
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
			((and (!= global144 global143) (== local10 0))
				(theMusic3 number: 128 setLoop: -1 play:)
				(= local10 1)
			)
			((and (== global144 global143) (== local10 1))
				(theMusic3 stop:)
				(= local10 0)
			)
		)
		(if
			(and
				(> (ego y?) 166)
				(not (curRoom script?))
				(not (ego script?))
			)
			(curRoom newRoom: 1030)
		)
	)
	
	(method (dispose)
		(theMusic3 dispose:)
		(directionHandler delete: doorbtm)
		(if (!= global144 global143)
			(= global144 3)
			(= global143 3)
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
					setMotion: PolyPath [pathX (* register 2)] [pathY (* register 2)] self
				)
			)
			(1
				(ego
					setLoop: 3
					cel: 0
					x: [pathX (+ (* register 2) 1)]
					y: [pathY (+ (* register 2) 1)]
					setCycle: CycleTo 3 1 self
				)
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
					(= goliathFloor local8)
				else
					(= goliathFloor local9)
				)
				(curRoom newRoom: 1020)
				(self dispose:)
			)
		)
	)
)

(instance sExitSubfloor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 670
					setLoop: -1
					setLoop: 3
					setCel: (ego lastCel:)
					setScale: Scaler 111 26 158 96
					x: [pathX (+ (* register 2) 1)]
					y: [pathY (+ (* register 2) 1)]
					init:
					setCycle: CycleTo 3 -1 self
				)
			)
			(1
				(if (== register 0)
					(doortop setCycle: BegLoop self)
				else
					(doorbtm setCycle: BegLoop self)
				)
				(theMusic2 number: 103 setLoop: 1 play:)
				(ego setCycle: BegLoop self)
			)
			(2
				(ego
					view: 670
					setLoop: -1
					setLoop: 1
					setCycle: Walk
					x: [pathX (* register 2)]
					y: [pathY (* register 2)]
					cel: 0
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLift of Script
	
	(method (doit)
		(if
			(and
				(> (liftBtm nsBottom?) (- (ego y?) 5))
				(not (ego script?))
			)
			(if (== global144 global143)
				(= global144 0)
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
					(3 (= global144 (Random 0 2)))
					(2
						(if (Random 0 1)
							(= state 8)
							(= global144 3)
						else
							(= state 3)
							(= global144 (Random 0 1))
						)
					)
					(else 
						(= state 5)
						(= global144 (Random 2 3))
					)
				)
				(= ticks 1)
			)
			(2
				(if (< (liftBtm scaleX?) 160)
					(liftBtm scaleX: (+ (liftBtm scaleX?) 10))
					(liftBtm scaleY: (+ (liftBtm scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(3
				(if (== (= global143 2) global144) (= state -1))
				(= cycles 1)
			)
			(4
				(if (< (liftBtm scaleX?) 320)
					(liftBtm scaleX: (+ (liftBtm scaleX?) 10))
					(liftBtm scaleY: (+ (liftBtm scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(5
				(= global143 global144)
				(liftBtm hide:)
				(= state -1)
				(= cycles 1)
			)
			(6
				(liftBtm show: scaleX: 320 scaleY: 320)
				(= cycles 1)
			)
			(7
				(if (> (liftBtm scaleX?) 160)
					(liftBtm scaleX: (- (liftBtm scaleX?) 10))
					(liftBtm scaleY: (- (liftBtm scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(8
				(if (== (= global143 2) global144) (= state -1))
				(= cycles 1)
			)
			(9
				(if (> (liftBtm scaleX?) 90)
					(liftBtm scaleX: (- (liftBtm scaleX?) 10))
					(liftBtm scaleY: (- (liftBtm scaleY?) 10))
					(-- state)
				)
				(= ticks 10)
			)
			(10
				(= global143 3)
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
				(if (< (liftBtm nsBottom?) (ego y?))
					(-- state)
				)
				(= cycles 1)
			)
			(2
				(ego view: 671 setLoop: -1 setLoop: 1 cel: 0 setCycle: 0)
				(= ticks 1)
			)
			(3
				(if (liftBtm script?)
					(-- state)
				)
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

(instance liftBtm of Actor
	(properties
		x 138
		y 72
		noun N_LIFT_BOTTOM
		view 670
		loop 6
		scaleSignal scalable
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
		x 154
		y 106
		noun N_VENT
		view 670
		loop 10
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
		x 161
		y 134
		noun N_VENT
		view 670
		loop 8
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
							(ego setMotion: PolyPath 129 100)
						)
					)
					(dirNE
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 129 100)
						)
					)
					(dirSE
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 94 167)
						)
					)
					(dirS
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 94 167)
						)
					)
					(dirSW
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 94 167)
						)
					)
					(dirNW
						(if (ego mover?)
							(ego setMotion: 0)
						else
							(ego setMotion: PolyPath 129 100)
						)
					)
				)
				(event claimed: TRUE)
				(return TRUE)
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

(instance turbtop of View
	(properties
		x 143
		y 80
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
