;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Polygon)
(use Motion)
(use System)

(public
	rm115 0
)

(instance rm115 of SQRoom
	(properties
		picture 115
		horizon 50
		west 110
		picAngle 70
		vanishingX 0
		vanishingY -65
	)
	
	(method (init)
		(sewer roomVer: 22291)
		(Load VIEW 80)
		(switch prevRoomNum
			(100
				(ego posn: 88 -1)
				(self setScript: enterNorth style: 10)
				(cond 
					((not (OneOf (sewer location?) 100 115)) (sewer status: 1))
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 115)
						)
						((ScriptID SEWER 2)
							init:
							posn: 189 75
							setLoop: 1
							setCycle: Forward
							cycleSpeed: 6
							setScript: (ScriptID SEWER 4)
						)
						(sewer status: 4)
					)
				)
			)
			(west
				(if
					(and
						(== (sewer location?) 115)
						(== (sewer status?) 6)
					)
					(Load SOUND 818)
					(Load VIEW 76)
					(sewer status: 4)
					((ScriptID SEWER 2) init: posn: 61 127 hide: setCycle: Forward)
				)
				(self style: 12 setScript: enterLeft)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(ego init: posn: 18 128)
				(NormalEgo)
			)
		)
		((ScriptID SEWER 5)
			init:
			setLoop: 14
			posn: 85 80
			cycleSpeed: 12
			setCycle: Forward
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 15
			posn: 232 78
			cycleSpeed: 12
			setCycle: Forward
		)
		(super init:)
		(ego init: show:)
		((ScriptID SEWER 2) show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 81 0 233 121 227 129 0 129
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 0 319 189 0 189 0 136 241 136 248 129 243 118 95 0
					yourself:
				)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) NORTH)
				(HandsOff)
				((ScriptID SEWER 2) setScript: 0)
				(self setScript: exitNorth)
			)
			((== (ego edgeHit?) WEST)
				(if (> (sewer distance?) 39)
					(globalSound vol: 0 fade: 0 10 10 0)
				)
				(if
					(or
						(< ((ScriptID SEWER 2) y?) 80)
						(== (sewer location?) 100)
					)
					(globalSound vol: 0 fade: 0 10 10 0)
					(sewer location: 0 status: 0)
				)
			)
		)
		(super doit: &rest)
		(if
			(and
				(== (dripScript state?) -1)
				(!= prevRoomNum 110)
				(== (sewer status?) 1)
			)
			((ScriptID SEWER 3) init: hide: setScript: dripScript)
		)
		(if
			(and
				(== (sewer location?) 110)
				(> (ego x?) 121)
				(> (sewer prevDistance?) 90)
				(> (sewer rmTimer?) 5)
			)
			(sewer location: 0 status: 0)
		)
		(if (< (ego y?) 60)
			(ego setPri: 4)
		else
			(ego setPri: -1)
		)
	)
	
	(method (doVerb)
		(sewer doVerb: &rest)
	)
)

(instance exitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 4
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: 7
					setMotion: MoveTo 89 0 self
				)
			)
			(1
				(if
					(and
						(== (sewer location?) 100)
						(> (sewer rmTimer?) 30)
					)
					(sewer location: 0 status: 0)
				)
				(if
					(or
						(== ((ScriptID SEWER 2) loop?) 2)
						(== (sewer location?) 110)
					)
					(sewer location: 0 status: 0)
				)
				((ScriptID SEWER 2) posn: 1000 1000)
				(curRoom newRoom: 100)
			)
		)
	)
)

(instance dripScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 3 8)))
			(1
				(if (< (ego y?) 105) (self init:) else (self cue:))
			)
			(2
				(sewer status: 2 location: 115)
				(music vol: 0 fade: 0 10 10 0)
				(globalSound vol: 127 number: 806 loop: 1 play:)
				((ScriptID SEWER 3)
					show:
					setLoop: 6
					posn: 186 14
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 9 1 self
				)
			)
			(3
				((ScriptID SEWER 3) cycleSpeed: 12 setCycle: EndLoop)
				((ScriptID SEWER 2)
					init:
					setLoop: 8
					cycleSpeed: 12
					setCycle: EndLoop self
					posn: 161 48
				)
			)
			(4
				(globalSound vol: 127 number: 819 loop: -1 playBed:)
				(sewer status: 3)
				((ScriptID SEWER 2)
					setLoop: 1
					setCycle: Forward
					cycleSpeed: 6
					setScript: (ScriptID SEWER 4)
				)
				(self dispose:)
			)
		)
	)
)

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreHorizon: TRUE
					setLoop: 4
					setMotion: MoveTo 123 29 self
				)
			)
			(1
				(if
					(and
						(== (sewer location?) 115)
						(== (sewer status?) 3)
					)
					((ScriptID SEWER 2) setScript: (ScriptID SEWER 4))
				)
				(if
					(and
						(== (sewer status?) 3)
						(== (sewer location?) 100)
					)
					(if (< (sewer prevDistance?) 60)
						((ScriptID SEWER 2) posn: 79 -16)
					else
						((ScriptID SEWER 2) posn: 73 -22)
					)
					((ScriptID SEWER 2)
						init:
						setLoop: 1
						setCycle: Forward
						cycleSpeed: 6
						setScript: (ScriptID SEWER 4)
					)
					(sewer location: 115)
				)
				(ego setMotion: MoveTo 159 59 self)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance enterLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					hide:
					posn: 10 133
					setMotion: MoveTo 30 133 self
				)
				(cond 
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 110)
						)
						(if (< (sewer prevDistance?) 50)
							((ScriptID SEWER 2)
								init:
								hide:
								posn: -32 125
								setLoop: 2
								moveSpeed: (- speed 2)
								setScript: (ScriptID SEWER 4)
							)
							(sewer location: 115)
						else
							(music vol: 127 playBed:)
							(globalSound vol: 0 fade: 0 10 10 0)
						)
					)
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 115)
						)
						(if (> (sewer oldDistance?) 140)
							((ScriptID SEWER 2) posn: 203 127)
						else
							((ScriptID SEWER 2) posn: (sewer oldDistance?) 127)
						)
						(sewer status: 4)
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
				)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)
