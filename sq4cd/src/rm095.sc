;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Polygon)
(use Motion)
(use System)

(public
	rm095 0
)

(local
	local0
)
(instance rm095 of SQRoom
	(properties
		picture 95
		horizon 40
		north 80
		east 100
		south 110
		west 90
		picAngle 70
	)
	
	(method (init &tmp temp0)
		(Load VIEW 80)
		(if
			(and
				(== ((ScriptID SEWER 2) loop?) 3)
				(== (sewer status?) 8)
				(== (sewer location?) 95)
			)
			((ScriptID SEWER 2)
				init:
				hide:
				posn: 158 187
				setLoop: 3
				setCycle: Forward
				moveSpeed: (- speed 2)
				setScript: slimeLeaving
			)
		)
		(switch prevRoomNum
			(north
				(if
				(and (== (sewer status?) 3) (== (sewer location?) 80))
					(cond 
						((> (sewer prevDistance?) 100) ((ScriptID SEWER 2) posn: 158 -50))
						((> (sewer prevDistance?) 50) ((ScriptID SEWER 2) posn: 158 -40))
						(else ((ScriptID SEWER 2) posn: 158 -25))
					)
					((ScriptID SEWER 2)
						init:
						hide:
						setLoop: 3
						setCycle: Forward
						moveSpeed: (- speed 2)
						setScript: (ScriptID SEWER 4)
					)
					(sewer cantDie: 1 location: 95)
				)
				(if
				(and (== (sewer status?) 6) (== (sewer location?) 95))
					(Load VIEW 76)
					(Load SOUND 818)
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 158 92
						setLoop: 3
						setCycle: Forward
						moveSpeed: 0
					)
					(sewer status: 4 location: 95)
				)
				(ego x: 156)
				(= temp0 13)
			)
			(south
				(self setScript: enterSouth)
				(= temp0 14)
			)
			(east
				(= temp0 11)
				(self setScript: enterRight)
			)
			(west
				(if (OneOf (sewer location?) 105 75)
					(sewer location: 0 status: 0)
				)
				(= temp0 12)
				(self setScript: enterLeft)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(ego posn: 157 174)
			)
		)
		(self style: temp0)
		((ScriptID SEWER 5)
			init:
			setLoop: 8
			posn: 69 44
			cycleSpeed: 12
			setCycle: Forward
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 9
			posn: 250 79
			setScript: daDripScript
		)
		(ego init:)
		(super init:)
		((ScriptID SEWER 2) show:)
		(ego show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 145 0 145 83 139 88 128 93 0 93
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 102 130 102 139 106 145 116 145 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 0 319 94 179 94 170 89 165 83 165 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 167 189 167 112 172 105 177 102 319 102 319 189
					yourself:
				)
		)
		(if (not (OneOf prevRoomNum 100 90)) (HandsOn))
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitNorth))
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouth))
			((== (ego edgeHit?) EAST)
				(if (== (sewer location?) 95)
					(if (> ((ScriptID SEWER 2) y?) 120) (sewer status: 8))
					(if (> (sewer distance?) 35)
						(globalSound vol: 0 fade: 0 10 10 0)
						(music vol: 127 playBed:)
					)
				)
			)
			((== (ego edgeHit?) WEST)
				(if (== (sewer location?) 95)
					(if (> ((ScriptID SEWER 2) y?) 120) (sewer status: 8))
					(if (> (sewer distance?) 35)
						(globalSound vol: 0 fade: 0 10 10 0)
						(music vol: 127 playBed:)
					)
				)
				(if (OneOf (sewer location?) 110 100)
					(sewer location: 0 status: 0)
				)
			)
			(
				(and
					(== prevRoomNum 110)
					(== (sewer location?) 110)
					(> (sewer rmTimer?) 10)
				)
				(sewer location: 0 status: 0)
			)
			(
				(and
					(== prevRoomNum 100)
					(== (sewer location?) 100)
					(> (sewer rmTimer?) 10)
				)
				(sewer location: 0 status: 0)
			)
		)
		(super doit: &rest)
		(if
			(and
				(& (ego onControl: origin) cBLUE)
				(== ((ScriptID SEWER 2) loop?) 3)
			)
			(sewer cantDie: 1)
		else
			(sewer cantDie: 0)
		)
		(if
			(and
				(== (sewer status?) 3)
				(== ((ScriptID SEWER 2) loop?) 3)
				(> ((ScriptID SEWER 2) y?) 160)
				(== (ego heading?) 180)
				(> (ego y?) 160)
			)
			((ScriptID SEWER 2) setMotion: 0 setScript: 0)
		)
		(if (< (ego y?) 50)
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
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< ((ScriptID SEWER 2) y?) -22)
					(sewer status: 6)
					((ScriptID SEWER 2) posn: 1000 1000)
				)
				(ego setMotion: MoveTo 156 10 self)
			)
			(1
				(ego setMotion: MoveTo 156 -6 self)
			)
			(2
				(if (OneOf (sewer location?) 110 100)
					(sewer location: 0 status: 0)
				)
				(if
				(and (== (sewer location?) 95) (== (sewer status?) 3))
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
					(if (== ((ScriptID SEWER 2) loop?) 2)
						((ScriptID SEWER 2) setLoop: 3)
						(sewer status: 6)
					)
				)
				(curRoom newRoom: 80)
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
				(ego posn: 309 99 setMotion: MoveTo 292 99 self)
				(cond 
					(
					(and (== (sewer status?) 6) (== (sewer location?) 95))
						(Load SOUND 818)
						(Load VIEW 76)
						((ScriptID SEWER 2)
							init:
							hide:
							posn: 260 89
							setLoop: 2
							moveSpeed: (- speed 2)
							setScript: (ScriptID SEWER 4)
						)
						(music vol: 0 fade: 0 10 10 0)
						(globalSound vol: 127 playBed:)
						(sewer status: 4)
					)
					(
					(and (== (sewer status?) 3) (== (sewer location?) 95))
						((ScriptID SEWER 2)
							init:
							hide:
							posn: 227 89
							setLoop: 2
							moveSpeed: (- speed 2)
							setScript: (ScriptID SEWER 4)
						)
						(globalSound vol: 127 playBed:)
						(music vol: 0 fade: 0 10 10 0)
					)
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 100)
						)
						(if (< (sewer prevDistance?) 50)
							((ScriptID SEWER 2)
								init:
								hide:
								posn: 352 89
								setLoop: 2
								moveSpeed: (- speed 2)
								setScript: (ScriptID SEWER 4)
							)
							(sewer location: 95)
						else
							(globalSound vol: 0 fade: 0 10 10 0)
							(music vol: 127 playBed:)
						)
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

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(if
					(and
						(== ((ScriptID SEWER 2) loop?) 2)
						(== (sewer status?) 3)
						(== (sewer location?) 95)
					)
					(sewer location: 0 status: 0)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
				(if
					(and
						(== ((ScriptID SEWER 2) loop?) 3)
						(< ((ScriptID SEWER 2) y?) 100)
						(== (sewer status?) 3)
						(== (sewer location?) 95)
					)
					(sewer status: 6)
					(globalSound vol: 0 fade: 0 10 10 0)
					(music vol: 127 playBed:)
				)
				(ego setMotion: MoveTo (ego x?) 239 self)
			)
			(2
				(if (== (sewer location?) 110)
					(globalSound vol: 127 playBed:)
					(music vol: 0 fade: 0 10 10 0)
				)
				(if (== (sewer location?) 100)
					(sewer status: 0 location: 0)
				)
				(curRoom newRoom: 110)
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
				(cond 
					(
						(and
							(== (sewer status?) 3)
							(== (sewer location?) 90)
							(< (sewer prevDistance?) 40)
						)
						((ScriptID SEWER 2)
							init:
							hide:
							posn: -32 89
							setLoop: 2
							moveSpeed: (- speed 2)
							setScript: (ScriptID SEWER 4)
						)
						(sewer location: 95)
					)
					(
					(and (== (sewer status?) 3) (== (sewer location?) 95))
						(if (> (sewer oldDistance?) 109)
							(sewer oldDistance: 109)
						)
						((ScriptID SEWER 2)
							init:
							hide:
							posn: (+ (sewer oldDistance?) 5) 89
							setLoop: 2
							moveSpeed: (- speed 2)
							setScript: (ScriptID SEWER 4)
						)
						(globalSound vol: 127 playBed:)
						(music vol: 0 fade: 0 10 10 0)
					)
				)
				(ego posn: 11 98 setMotion: MoveTo 29 98 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
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
				(sewer rmTimer: 0)
				(ego init: posn: 162 185)
				(if
					(and
						(== (sewer location?) 95)
						(< (sewer prevDistance?) 60)
					)
					(sewer status: 6)
				)
				(cond 
					(
					(and (== (sewer status?) 3) (== (sewer location?) 95))
						((ScriptID SEWER 2)
							init:
							hide:
							posn: 158 (- 190 (sewer prevDistance?))
							setLoop: 3
							setCycle: Forward
							setScript: (ScriptID SEWER 4)
						)
						(music vol: 0 fade: 0 10 10 0)
						(globalSound vol: 127 playBed:)
					)
					(
					(and (== (sewer status?) 6) (== (sewer location?) 95))
						(Load SOUND 818)
						(Load VIEW 76)
						((ScriptID SEWER 2)
							init:
							show:
							posn: 158 144
							setLoop: 3
							setCycle: Forward
							setScript: (ScriptID SEWER 4)
						)
						(sewer status: 4)
						(music vol: 0 fade: 0 10 10 0)
						(globalSound vol: 127 playBed:)
						(= register 1)
					)
				)
				(= cycles 5)
			)
			(1
				(if (== register 1)
					((ScriptID SEWER 2) setScript: (ScriptID SEWER 1))
				else
					(NormalEgo)
					(HandsOn)
				)
				(self dispose:)
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
				((ScriptID SEWER 2) setMotion: MoveTo 158 209 self)
			)
			(1
				(sewer status: 3 location: 110)
				((ScriptID SEWER 2) posn: 1000 1000 dispose:)
			)
		)
	)
)
