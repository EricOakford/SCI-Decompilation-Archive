;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include game.sh)
(use Main)
(use sewer)
(use MoveToCoords)
(use SQRoom)
(use PolyPath)
(use Polygon)
(use Motion)
(use System)

(public
	rm105 0
)

(local
	local0
)
(instance rm105 of SQRoom
	(properties
		picture 105
		horizon 50
		east 110
		picAngle 70
		vanishingX 319
		vanishingY -90
	)
	
	(method (init)
		(sewer roomVer: 22691)
		(Load VIEW 80)
		(switch prevRoomNum
			(110
				(if
				(or (== (sewer location?) 115) (== (sewer status?) 2))
					(sewer status: 0 location: 0)
				)
				(if
					(and
						(== (sewer status?) 8)
						(== (sewer location?) 105)
					)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 181 32
						setLoop: 0
						setCycle: Forward
						setScript: slimeLeavingUp
					)
				)
				(self setScript: enterRight style: 11)
			)
			(90
				(if
					(and
						(== (sewer location?) 105)
						(== (sewer status?) 6)
					)
					(Load VIEW 76)
					(Load SOUND 818)
					(if (not (Random 0 2))
						(sewer status: 3)
					else
						((ScriptID SEWER 2)
							posn: (Random 286 334) 125
							init:
							setCycle: Forward
							setScript: slimeLeavingR
						)
					)
				)
				(self setScript: enterNorth style: 10)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(self setScript: enterNorth style: FADEOUT)
			)
		)
		((ScriptID SEWER 5)
			init:
			setLoop: 12
			posn: 233 114
			setPri: 5
			cycleSpeed: 12
			setCycle: Forward
		)
		(super init:)
		(ego init:)
		((ScriptID SEWER 2) show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 225 0 85 111 78 124 77 132 83 135 319 135 319 189 0 189 0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 0 319 132 101 132 94 124 99 115 237 0
					yourself:
				)
		)
		(if
			(and
				(== (sewer location?) 0)
				(== (sewer status?) 0)
				(!= prevRoomNum 110)
			)
			(sewer status: 1)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitScript))
			((== (ego edgeHit?) SOUTH)
				(if
					(and
						(< ((ScriptID SEWER 2) y?) 85)
						(== (sewer location?) 105)
					)
					(sewer status: 8)
				)
				(if (== ((ScriptID SEWER 2) loop?) 0)
					(music vol: 127 playBed:)
					(globalSound vol: 0 fade: 0 10 10 0)
				)
			)
			(
				(and
					(== (sewer status?) 3)
					(!= (sewer location?) 105)
					(> (sewer rmTimer?) 11)
				)
				(sewer location: 0 status: 0)
			)
		)
		(super doit:)
		(if
			(and
				(< (ego y?) 171)
				(> (ego y?) 126)
				(== (sewer status?) 1)
			)
			(sewer status: 2)
			((ScriptID SEWER 3) init: hide: setScript: dripScript)
		)
		(if
			(and
				(== (sewer status?) 3)
				(== (sewer location?) 110)
				(< (ego x?) 245)
				(< (sewer rmTimer?) 12)
			)
			(sewer location: 105)
			((ScriptID SEWER 2)
				init:
				show:
				setLoop: 2
				moveSpeed: (- speed 2)
				posn: 347 125
				setCycle: Forward
				cycleSpeed: 6
				setScript: (ScriptID SEWER 4)
			)
		)
		(if
			(and
				(== (sewer status?) 6)
				(== (sewer location?) 90)
				(> (sewer rmTimer?) 8)
			)
			(Load VIEW 76)
			(Load SOUND 818)
			(sewer location: 0 status: 0)
		)
		(if
			(and
				(< (ego x?) 128)
				(== (sewer location?) 110)
				(== (sewer status?) 3)
			)
			(sewer status: 0 location: 0)
		)
		(if (< (ego y?) 58)
			(ego setPri: 4)
		else
			(ego setPri: -1)
		)
	)
	
	(method (doVerb)
		(sewer doVerb: &rest)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 4
					setLoop: -1
					setHeading: 46
					setMotion: MoveToY -6 self
				)
			)
			(1
				(if (== (sewer location?) 110)
					(sewer status: 0 location: 0)
				)
				(curRoom newRoom: 90)
			)
		)
	)
)

(instance dripScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 2 8)))
			(1
				(if (or (> (ego x?) 265) (< (ego y?) 110))
					(self init:)
				else
					(self cue:)
				)
			)
			(2
				(music vol: 0 fade: 0 10 10 0)
				(globalSound vol: 127 number: 806 loop: 1 play:)
				((ScriptID SEWER 3)
					show:
					setLoop: 5
					posn: 139 11
					cel: 0
					cycleSpeed: 12
					setCycle: CycleTo 9 1 self
				)
			)
			(3
				((ScriptID SEWER 3) setCycle: EndLoop)
				((ScriptID SEWER 2)
					init:
					show:
					setLoop: 7
					cycleSpeed: 12
					setCycle: EndLoop self
					posn: 167 41
				)
			)
			(4
				(globalSound vol: 127 number: 819 loop: -1 play:)
				(sewer location: 105 status: 3)
				((ScriptID SEWER 2)
					setLoop: 0
					setCycle: Forward
					cycleSpeed: 6
					setScript: (ScriptID SEWER 4)
					moveSpeed: (- speed 2)
				)
				(self dispose:)
			)
		)
	)
)

(instance enterRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 309 134 setMotion: MoveTo 297 135 self)
				(if
					(and
						(== (sewer status?) 3)
						(== (sewer location?) 105)
					)
					(if (> (sewer oldDistance?) 160)
						(sewer oldDistance: 160)
					)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: (- 320 (sewer oldDistance?)) 125
						moveSpeed: (- speed 2)
						setLoop: 2
						setScript: (ScriptID SEWER 4)
					)
					(music vol: 0 fade: 0 10 10 0)
					(globalSound vol: 127 number: 819 loop: -1 play:)
				)
				(if
					(and
						(== (sewer status?) 6)
						(== (sewer location?) 105)
					)
					(Load VIEW 76)
					(Load SOUND 818)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 359 125
						setLoop: 2
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(sewer status: 4)
				)
				(cond 
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 110)
							(< (sewer prevDistance?) 40)
						)
						((ScriptID SEWER 2)
							init:
							hide:
							posn: 359 125
							setLoop: 2
							moveSpeed: (- speed 2)
							setScript: (ScriptID SEWER 4)
						)
						(sewer location: 105)
					)
					((!= (sewer location?) 105)
						(globalSound vol: 0 fade: 0 10 10 0)
						(music vol: 127 playBed:)
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

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 194 32
					setLoop: 5
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: PolyPath 156 62 self
				)
				(if (== (sewer status?) 3)
					(switch (sewer location?)
						(105
							((ScriptID SEWER 2)
								init:
								hide:
								setLoop: 0
								posn: 118 85
								setScript: (ScriptID SEWER 4)
							)
							(= state 1)
						)
						(90
							((ScriptID SEWER 2) init: hide: setLoop: 0 posn: 256 -26)
							(= seconds 2)
							(= local0 1)
						)
					)
				else
					(= state 1)
				)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(if local0
					((ScriptID SEWER 2)
						posn: 240 -16
						setScript: (ScriptID SEWER 4)
					)
					(sewer location: 105)
				)
				(self dispose:)
			)
		)
	)
)

(instance slimeLeavingR of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID SEWER 2) setMotion: MoveTo 360 125 self)
			)
			(1
				((ScriptID SEWER 2) dispose:)
				(sewer location: 110)
				(self dispose:)
			)
		)
	)
)

(instance slimeLeavingUp of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID SEWER 2) setMotion: MoveTo 225 -7 self)
				(music vol: 0 fade: 0 10 10 0)
				(globalSound vol: 127 number: 819 loop: 1 play:)
			)
			(1
				((ScriptID SEWER 2) dispose:)
				(sewer location: 90 status: 6 rmTimer: 0)
				(music vol: 127 playBed:)
				(globalSound vol: 0 fade: 0 10 10 0)
				(self dispose:)
			)
		)
	)
)
