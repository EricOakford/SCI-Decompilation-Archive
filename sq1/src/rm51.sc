;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use Intrface)
(use Inertia)
(use SQRoom)
(use RandCyc)
(use Feature)
(use LoadMany)
(use Smooper)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm51 0
)

(local
	local0
	local1
	saveBits
	local3
)
(procedure (localproc_0164 param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3)
	(cond 
		((or (== param5 1) (== param5 2))
			(= temp0 (- param4 5))
			(= temp1 (- param1 5))
			(= temp2 (+ param2 5))
			(= temp3 (+ param3 5))
		)
		((== param5 3)
			(= temp0 (- param2 5))
			(= temp1 (- param1 5))
			(= temp2 (+ param4 5))
			(= temp3 (+ param3 5))
		)
		((== param5 4)
			(= temp0 (- param4 5))
			(= temp1 (- param3 5))
			(= temp2 (+ param2 5))
			(= temp3 (+ param1 5))
		)
	)
	(soundFx number: 312 loop: 1 play:)
	(= saveBits (Graph GSaveBits temp0 temp1 temp2 temp3 1))
	(Graph GDrawLine
		param2
		param1
		param4
		param3
		global152
		0
		0
	)
	(Graph GReAnimate temp0 temp1 temp2 temp3)
	(Graph GRestoreBits saveBits)
	(Graph GReAnimate temp0 temp1 temp2 temp3)
	(= saveBits 0)
)

(instance rm51 of SQRoom
	(properties
		lookStr {You're next to a large hatch in the side of this ship.}
		picture 51
		north 52
		east 52
		south 52
		west 52
	)
	
	(method (init)
		(LoadMany VIEW 34 64 152 82 83 151 54)
		(Load SCRIPT SMOOPER INERTIA)
		(= useObstacles FALSE)
		(ego
			view: 34
			ignoreActors: TRUE
			setLoop: -1
			setCel: -1
			cycleSpeed: 24
			ignoreHorizon:
			normal: 0
			setPri: 14
			posn: 327 -10
			init:
		)
		(shadow init:)
		(HandsOff)
		(door init: stopUpd:)
		(airLockHandle init:)
		(self setScript: zoomIn)
		(super init:)
		(if (!= (theMusic number?) 501)
			(theMusic number: 501 loop: -1 play:)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 ((User alterEgo?) edgeHit?)) (HandsOff) (self setScript: whatGun 0 temp0))
		)
	)
	
	(method (dispose)
		(DisposeScript SMOOPER)
		(DisposeScript INERTIA)
		(super dispose: &rest)
	)
)

(instance changeMyDirection of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 34
					setLoop:
						(switch (ego loop?)
							(loopE 2)
							(loopW 2)
							(loopS 0)
							(loopN 1)
							(loopSE 0)
							(loopSW 1)
							(loopNE 3)
							(loopNW 3)
						)
					cel: 0
				)
				(= cycles 7)
			)
			(1 (self dispose:))
		)
	)
)

(instance southExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (== (ego loop?) 2) (== (ego loop?) 3))
					(= state 4)
				)
				(officer init: x: 158 y: 90 setPri: 1 cel: 0 setLoop: 7)
				(trooper
					init:
					x: 121
					y: 98
					setPri: 1
					cel: 4
					setLoop: 5
					setCycle: BegLoop
				)
				(= cycles 3)
			)
			(1
				(ego
					show:
					x: 123
					y: -10
					setCycle: RandCycle
					setMotion: MoveTo 105 2
				)
				(= cycles 3)
			)
			(2 (officer setCycle: EndLoop self))
			(3
				(localproc_0164 117 11 (ego x?) (ego y?) 4)
				(= cycles 3)
			)
			(4
				(if (== (ego loop?) 0)
					(ego setLoop: 7 cel: 0)
				else
					(ego setLoop: 10 cel: 0)
				)
				(ego cycleSpeed: 9 setCycle: EndLoop self)
				(= state 8)
			)
			(5
				(ego
					show:
					x: 204
					y: -10
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 280 33
				)
				(= cycles 39)
			)
			(6 (officer setCycle: EndLoop self))
			(7
				(localproc_0164 236 43 (ego x?) (ego y?) 1)
				(= cycles 3)
			)
			(8
				(if (== (ego loop?) 2)
					(ego setLoop: 9 cel: 0)
				else
					(ego setLoop: 8 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
			)
			(9 (officer setCycle: BegLoop self))
			(10 (self dispose:))
		)
	)
)

(instance eastExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (== (ego loop?) 2) (== (ego loop?) 3))
					(= state 4)
				)
				(officer init: x: 122 y: 101 setPri: 1 cel: 0 setLoop: 8)
				(trooper
					init:
					x: 162
					y: 93
					setPri: 1
					cel: 0
					setLoop: 5
					setCycle: CycleTo 2 1
				)
				(= cycles 3)
			)
			(1
				(ego
					show:
					x: 0
					y: 90
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 23 64
				)
				(= cycles 12)
			)
			(2 (officer setCycle: EndLoop self))
			(3
				(localproc_0164 60 62 (ego x?) (ego y?) 4)
				(= cycles 3)
			)
			(4
				(if (== (ego loop?) 0)
					(ego setLoop: 7 cel: 0)
				else
					(ego setLoop: 10 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
				(= state 8)
			)
			(5
				(ego
					show:
					x: 0
					y: 30
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 23 64
				)
				(= cycles 12)
			)
			(6 (officer setCycle: EndLoop self))
			(7
				(localproc_0164 60 62 (ego x?) (ego y?) 4)
				(= cycles 3)
			)
			(8
				(if (== (ego loop?) 2)
					(ego setLoop: 9 cel: 0)
				else
					(ego setLoop: 8 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
			)
			(9 (officer setCycle: BegLoop self))
			(10 (self dispose:))
		)
	)
)

(instance westExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (== (ego loop?) 2) (== (ego loop?) 3))
					(= state 4)
				)
				(officer init: x: 185 y: 94 setPri: 1 cel: 0 setLoop: 8)
				(trooper
					init:
					x: 133
					y: 93
					setPri: 1
					cel: 0
					setLoop: 4
					setCycle: CycleTo 2 1
				)
				(= cycles 3)
			)
			(1
				(ego
					show:
					x: 317
					y: -4
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 280 33
				)
				(= cycles 24)
			)
			(2 (officer setCycle: EndLoop self))
			(3
				(localproc_0164 237 42 (ego x?) (ego y?) 1)
				(= cycles 3)
			)
			(4
				(if (== (ego loop?) 0)
					(ego setLoop: 7 cel: 0)
				else
					(ego setLoop: 10 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
				(= state 8)
			)
			(5
				(ego
					show:
					x: 317
					y: 76
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 280 33
				)
				(= cycles 24)
			)
			(6 (officer setCycle: EndLoop self))
			(7
				(localproc_0164 237 42 (ego x?) (ego y?) 1)
				(= cycles 3)
			)
			(8
				(if (== (ego loop?) 2)
					(ego setLoop: 9 cel: 0)
				else
					(ego setLoop: 8 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
			)
			(9 (officer setCycle: BegLoop self))
			(10 (self dispose:))
		)
	)
)

(instance northExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (== (ego loop?) 2) (== (ego loop?) 3))
					(= state 4)
				)
				(officer init: x: 147 y: 116 setPri: 1 cel: 0 setLoop: 8)
				(trooper
					init:
					x: 124
					y: 97
					setPri: 1
					cel: 4
					setLoop: 5
					setCycle: BegLoop
				)
				(= cycles 3)
			)
			(1
				(ego
					show:
					x: 179
					y: 189
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 250 156
				)
				(= cycles 24)
			)
			(2 (officer setCycle: EndLoop self))
			(3
				(localproc_0164 184 91 (ego x?) (ego y?) 3)
				(= cycles 3)
			)
			(4
				(if (== (ego loop?) 0)
					(ego setLoop: 7 cel: 0)
				else
					(ego setLoop: 10 cel: 0)
				)
				(ego cycleSpeed: 9 setCycle: EndLoop self)
				(= state 8)
			)
			(5
				(ego
					show:
					x: 275
					y: 189
					cel: 0
					setCycle: RandCycle
					setMotion: MoveTo 250 156
				)
				(= cycles 24)
			)
			(6 (officer setCycle: EndLoop self))
			(7
				(localproc_0164 184 91 (ego x?) (ego y?) 3)
				(= cycles 3)
			)
			(8
				(if (== (ego loop?) 2)
					(ego setLoop: 9 cel: 0)
				else
					(ego setLoop: 8 cel: 0)
				)
				(ego cycleSpeed: 14 moveSpeed: 8 setCycle: EndLoop self)
			)
			(9 (officer setCycle: BegLoop self))
			(10 (self dispose:))
		)
	)
)

(instance whatGun of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(spaceInertia dispose:)
				(theMusic fade:)
				(ego
					xStep: 4
					yStep: 3
					looper: 0
					setMotion: 0
					setLoop: (ego loop?)
				)
				(cast eachElementDo: #hide)
				(ego posn: -100 -100)
				(gun1 init:)
				(gun2 init:)
				(gun3 init:)
				(gun4 init:)
				(if (== (ego view?) 64)
					(ego setScript: changeMyDirection)
				)
				(curRoom drawPic: 52 -32758)
				(shadow show:)
				(self
					setScript:
						(switch register
							(1 northExit)
							(2 eastExit)
							(3 southExit)
							(else  westExit)
						)
						self
				)
			)
			(1 (= seconds 2))
			(2
				(EgoDead 948 0 0 51 0)
				(self dispose:)
			)
		)
	)
)

(instance officer of Prop
	(properties
		x 121
		y 98
		view 152
		loop 5
		cel 5
		cycleSpeed 8
	)
)

(instance trooper of Prop
	(properties
		x 151
		y 87
		view 152
		loop 6
		cycleSpeed 9
	)
)

(instance shadow of Actor
	(properties
		priority 13
		signal $6010
	)
	
	(method (doit &tmp temp0)
		(if local0
			(= temp0
				(switch (ego onControl: origin)
					(8 28)
					(2 35)
					(else  22)
				)
			)
			(= x (ego x?))
			(= y (+ (ego y?) temp0))
		)
		(= view
			(switch (ego view?)
				(34 82)
				(64 83)
			))
		(= loop (ego loop?))
		(= cel (ego cel?))
		(super doit: &rest)
	)
)

(instance gun1 of View
	(properties
		x 214
		y 79
		view 152
	)
)

(instance gun2 of View
	(properties
		x 86
		y 96
		view 152
		loop 1
	)
)

(instance gun3 of View
	(properties
		x 170
		y 107
		view 152
		loop 2
	)
)

(instance gun4 of View
	(properties
		x 134
		y 61
		view 152
		loop 3
	)
)

(instance zoomIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 1)
				(= local0 0)
			)
			(1
				(shadow
					posn: 300 180
					moveSpeed: 6
					setStep: 8 8
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 210 100
				)
				(ego setLoop: 4 cel: 1 setMotion: MoveTo 308 70 self)
			)
			(2
				(ego cel: 2 setMotion: MoveTo 284 86 self)
			)
			(3
				(ego cel: 0 setMotion: MoveTo 262 98 self)
			)
			(4
				(ego setLoop: 5 cel: 1 setMotion: MoveTo 244 84 self)
			)
			(5
				(ego cel: 2 setMotion: MoveTo 236 80 self)
			)
			(6
				(ego cel: 3 setMotion: MoveTo 224 77 self)
			)
			(7
				(ego setMotion: MoveTo 210 70 self)
			)
			(8
				(= local0 1)
				(ego
					setLoop: 3
					setStep: 2 2
					posn: 177 78
					cycleSpeed: 24
					setCycle: Forward
					setMotion: MoveTo 149 64 self
				)
			)
			(9
				(Print 51 1)
				(HandsOn)
				(spaceInertia init: ego)
				(ego
					setLoop: -1
					setStep: 1 1
					moveSpeed: 5
					cycleSpeed: 40
					looper: flyingLooper
				)
				(self dispose:)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 110
		y 99
		sightAngle 45
		onMeCheck NEARCHECK
		lookStr {airlock door}
		view 151
		priority 8
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 2)
			)
			(verbDo
				(Print 51 3)
			)
			(verbTaste
				(Print 51 4)
			)
			(verbSmell
				(Print 51 5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flyingLooper of SmoothLooper
	(properties
		vNormal 34
		vChangeDir 64
	)
)

(instance spaceInertia of Inertia
	(properties
		maxInertia 2
	)
)

(instance airLockHandle of Feature
	(properties
		x 52
		y 115
		z 4
		nsTop 103
		nsLeft 40
		nsBottom 120
		nsRight 65
		description {airlock handle}
		sightAngle 45
		onMeCheck FARCHECK
		lookStr {airlock handle}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 51 6)
			)
			(verbDo
				(curRoom setScript: goInside)
			)
			(verbTaste
				(Print 51 4)
			)
			(verbSmell
				(Print 51 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance goInside of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(spaceInertia dispose:)
				(= cycles 15)
			)
			(1
				(ego setMotion: MoveTo 45 100 self)
			)
			(2
				(cond 
					((== (ego loop?) 1) (ego view: 64 setLoop: 1 cel: 0 setCycle: EndLoop self))
					((== (ego loop?) 3) (ego view: 64 setLoop: 4 cel: 0 setCycle: EndLoop self))
					(else (= cycles 3))
				)
			)
			(3
				(if (or (== (ego loop?) 1) (== (ego loop?) 2))
					(ego view: 64 setLoop: 2 cel: 0 setCycle: EndLoop self)
				else
					(= cycles 3)
				)
			)
			(4
				(ego
					view: 34
					loop: 6
					cel: 0
					setPri: 14
					cycleSpeed: 18
					moveSpeed: 4
					setCycle: CycleTo 6 1 self
				)
			)
			(5
				(theMusic2 number: 504 loop: 1 play: hold: 1)
				(soundFx number: 503 loop: 1 play:)
				(ego cel: 2 setCycle: CycleTo 6 1 self)
			)
			(6
				(door cel: 1 forceUpd:)
				(ego cel: 2 setCycle: CycleTo 6 1 self)
			)
			(7
				(door cel: 2 forceUpd:)
				(ego cel: 2 setCycle: CycleTo 6 1 self)
			)
			(8
				(door cel: 3 forceUpd:)
				(ego cel: 2 setCycle: CycleTo 6 1 self)
			)
			(9
				(door dispose:)
				(theMusic2 hold: 0)
				(ego setCycle: EndLoop self)
			)
			(10
				(shadow setPri: 6)
				(ego
					setLoop: 0
					setCycle: RandCycle 4
					setPri: 10
					setMotion: MoveTo 112 76 self
				)
			)
			(11
				(soundFx number: 505 loop: 1 play:)
				(ego setLoop: 11 setCycle: EndLoop self)
			)
			(12
				(theMusic fade:)
				(ego
					x: 140
					y: 112
					view: 54
					setLoop: 0
					cel: 0
					setPri: 8
					moveSpeed: 2
					setCycle: Forward
					setMotion: MoveTo 177 173 self
				)
				(shadow dispose:)
			)
			(13
				(HandsOn)
				(= useObstacles TRUE)
				(curRoom newRoom: 53)
			)
		)
	)
)
