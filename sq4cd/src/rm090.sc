;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use Reverse)
(use Motion)
(use System)

(public
	rm090 0
)

(instance rm090 of SQRoom
	(properties
		picture 90
		horizon 47
		north 75
		east 95
		south 105
		west 105
		picAngle 70
		vanishingX 319
		vanishingY -50
	)
	
	(method (init)
		(sewer roomVer: 3591)
		(HandsOff)
		(Load VIEW 80)
		(Load VIEW 0)
		(Load VIEW 100)
		(switch prevRoomNum
			(72
				(self setScript: climbDOWNscript)
			)
			(95
				(ego posn: 309 96)
				(if
				(and (== (sewer status?) 3) (== (sewer location?) 90))
					(if (> (sewer oldDistance?) 87)
						(sewer oldDistance: 87)
					)
					((ScriptID SEWER 2)
						posn: (- 271 (sewer oldDistance?)) 89
					)
					((ScriptID SEWER 2)
						init:
						hide:
						setLoop: 2
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(globalSound vol: 127 loop: -1 playBed:)
					(music vol: 0 fade: 0 10 10 0)
				)
				(self style: 11 setScript: enterRight)
			)
			(105
				(if
				(or (== (sewer status?) 1) (== (sewer status?) 2))
					(sewer status: 0 location: 0)
				)
				(if
				(and (== (sewer location?) 90) (== (sewer status?) 6))
					(Load SOUND 818)
					(Load VIEW 76)
					(sewer status: 3 prevDistance: 20)
				)
				(if
					(and
						(== (sewer status?) 3)
						(== (sewer location?) 105)
						(== ((ScriptID SEWER 2) loop?) 2)
					)
					(if (> ((ScriptID SEWER 2) x?) 180)
						(sewer status: 0 location: 0)
					else
						(sewer status: 6 rmTimer: 0)
					)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
				(self setScript: enterSouth style: 10)
			)
			(north
				(if
				(and (== (sewer status?) 3) (== (sewer location?) 75))
					(cond 
						((> (sewer prevDistance?) 70) ((ScriptID SEWER 2) posn: 305 -32))
						((> (sewer prevDistance?) 50) ((ScriptID SEWER 2) posn: 300 -24))
						((> (sewer prevDistance?) 20) ((ScriptID SEWER 2) posn: 280 -10))
						(else ((ScriptID SEWER 2) posn: 274 -8))
					)
					((ScriptID SEWER 2)
						init:
						hide:
						setLoop: 0
						setCycle: Forward
						cycleSpeed: 6
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(ego init: hide: posn: 244 29)
					(sewer location: 90)
				else
					(ego init: hide: posn: 283 -8)
				)
				(self setScript: enterNorth style: 10)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(ego init: hide: posn: 283 -8)
				(self setScript: enterNorth style: 10)
			)
		)
		(super init:)
		((ScriptID SEWER 5)
			init:
			setLoop: 6
			posn: 229 44
			setPri: 5
			ignoreActors: TRUE
			cycleSpeed: 12
			setCycle: Forward
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 7
			posn: 27 130
			setScript: daDripScript
		)
		(ego show:)
		((ScriptID SEWER 2) show:)
		(theLadder init:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 189 0 0 263 0 38 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 48 189 150 105 163 101 319 101 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 0 319 94 179 94 170 88 181 78 275 0
					yourself:
				)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) EAST)
				(if (== (sewer location?) 105)
					(sewer status: 0 location: 0)
				)
				(if
					(and
						(== (sewer location?) 90)
						(== (sewer status?) 3)
						(> ((ScriptID SEWER 2) y?) 135)
					)
					(sewer status: 8)
				)
				(if (> (sewer distance?) 30)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
			)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitUp))
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitDown))
			(
				(and
					(== (sewer status?) 6)
					(== (sewer location?) 105)
				)
				(Load SOUND 818)
				(Load VIEW 76)
				(if (or (< (ego y?) 143) (> (sewer rmTimer?) 7))
					(sewer location: 0 status: 0)
				)
			)
		)
		(if
			(and
				(& (ego onControl: origin) cBLUE)
				(== ((ScriptID SEWER 2) loop?) 0)
			)
			(sewer cantDie: 1)
		else
			(sewer cantDie: 0)
		)
		(if (< (ego y?) 50)
			(ego setPri: 4)
		else
			(ego setPri: -1)
		)
		(super doit: &rest)
	)
)

(instance exitUp of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setLoop: 6
					setMotion: PolyPath 283 -8 self
				)
			)
			(1
				(if (== (sewer location?) 105)
					(sewer status: 0 location: 0)
				)
				(if
				(and (== (sewer location?) 90) (== (sewer status?) 3))
					(sewer status: 6)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
				(curRoom newRoom: 75)
			)
		)
	)
)

(instance theLadder of Sq4Feature
	(properties
		x 75
		y 107
		nsTop 18
		nsLeft 73
		nsBottom 121
		nsRight 100
		sightAngle 45
		lookStr 1
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO
				(HandsOff)
				(cond 
					((!= (sewer status?) 4) (curRoom setScript: climbUPscript))
					(
						(and
							(> ((ScriptID SEWER 2) y?) 100)
							(== ((ScriptID SEWER 2) loop?) 0)
							(== (sewer status?) 3)
						)
						(narrator say: 2)
					)
					(else (curRoom setScript: climbUPscript))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance climbUPscript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 98 129 self)
			)
			(1
				(ego
					posn: 98 129
					view: 100
					setLoop: 1
					setCel: 0
					cycleSpeed: 6
					ignoreActors: TRUE
					setCycle: Forward
					setMotion: MoveTo 98 72 self
				)
				(music fade:)
				(globalSound fade:)
			)
			(2
				(music number: 0)
				(globalSound number: 0)
				(ego setCel: 255 ignoreActors: 0)
				(curRoom newRoom: 72)
			)
		)
	)
)

(instance climbDOWNscript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(music vol: 127 number: 805 loop: -1 playBed:)
				(ego
					init:
					view: 100
					posn: 98 69
					illegalBits: 0
					ignoreActors: TRUE
					setCel: 255
					setLoop: 1
					cycleSpeed: 6
				)
				(= cycles 1)
			)
			(1
				(ego setCycle: Reverse setMotion: MoveTo 98 129 self)
			)
			(2
				(ego view: 0)
				(NormalEgo)
				(= cycles 1)
			)
			(3
				(ego setLoop: -1 setHeading: 90 self)
			)
			(4
				(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance enterRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: hide: setMotion: MoveTo 290 96 self)
				(if
					(and
						(== (sewer status?) 3)
						(== (sewer location?) 95)
						(< (sewer prevDistance?) 30)
					)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 357 89
						setLoop: 2
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(globalSound vol: 127 loop: -1 playBed:)
					(music vol: 0 fade: 0 10 10 0)
					(sewer location: 90)
				)
				(if
				(and (== (sewer location?) 90) (== (sewer status?) 8))
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 53 173
						setLoop: 0
						moveSpeed: (- speed 2)
						setScript: slimeLeaving
					)
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterSouth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					hide:
					posn: -9 246
					setLoop: 6
					setMotion: PolyPath 53 180 self
				)
				(if
				(and (== (sewer status?) 3) (== (sewer location?) 90))
					((ScriptID SEWER 2) posn: 75 156)
					(if (> (sewer prevDistance?) 30)
						((ScriptID SEWER 2) posn: 90 144)
					)
					(if (> (sewer prevDistance?) 50)
						((ScriptID SEWER 2) posn: 107 128)
					)
					(if (> (sewer prevDistance?) 70)
						((ScriptID SEWER 2) posn: 140 100)
					)
					(if (> (sewer prevDistance?) 90)
						((ScriptID SEWER 2) posn: 176 72)
					)
					(if (> (sewer prevDistance?) 110)
						((ScriptID SEWER 2) posn: 239 21)
					)
					((ScriptID SEWER 2)
						init:
						hide:
						setLoop: 0
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(sewer status: 4)
					(globalSound vol: 127 playBed:)
					(music vol: 0 fade: 0 10 10 0)
				)
			)
			(1
				(if
					(and
						(== (sewer location?) 105)
						(!= ((ScriptID SEWER 2) loop?) 2)
					)
					((ScriptID SEWER 2)
						init:
						posn: 22 199
						setLoop: 0
						setScript: (ScriptID SEWER 4)
					)
					(sewer location: 90)
				)
				(if
					(and
						(== (sewer location?) 90)
						(== (sewer status?) 3)
						(== ((ScriptID SEWER 2) loop?) 0)
					)
					((ScriptID SEWER 2) moveSpeed: (- speed 2))
				)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 5 setMotion: PolyPath 208 56 self)
				(if
				(and (== (sewer status?) 6) (== (sewer location?) 90))
					(Load SOUND 818)
					(Load VIEW 76)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 171 79
						setLoop: 0
						setCycle: Forward
						cycleSpeed: 6
						setScript: (ScriptID SEWER 4)
					)
					(sewer status: 4)
				)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitDown of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego setHeading: 225 self setMotion: 0)
			)
			(1
				(ego setLoop: 5 setMotion: PolyPath -9 246 self)
			)
			(2
				(if (< ((ScriptID SEWER 2) y?) 105)
					(sewer location: 0 status: 0)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
				(curRoom newRoom: 105)
			)
		)
	)
)

(instance daDripScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID SEWER 6) setCycle: EndLoop self)
			)
			(1 (= cycles (Random 5 20)))
			(2 (self init:))
		)
	)
)

(instance slimeLeaving of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID SEWER 2)
					setCycle: Forward
					setMotion: MoveTo 19 207 self
				)
			)
			(1
				(sewer location: 105 status: 3)
				((ScriptID SEWER 2) posn: 1000 1000 dispose:)
				(self dispose:)
			)
		)
	)
)
