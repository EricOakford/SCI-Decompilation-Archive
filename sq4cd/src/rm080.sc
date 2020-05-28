;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Polygon)
(use Motion)
(use System)

(public
	rm080 0
)

(instance rm080 of SQRoom
	(properties
		picture 80
		east 85
		south 95
		west 75
		picAngle 70
	)
	
	(method (init &tmp temp0)
		(sewer roomVer: 3591)
		(HandsOn)
		(Load VIEW 80)
		(switch prevRoomNum
			(west
				(ego y: 100)
				(= temp0 12)
			)
			(south
				(if (< (ego x?) 145) (ego x: 146))
				(if (> (ego x?) 172) (ego x: 171))
				(= temp0 14)
				(if
				(and (== (sewer location?) 80) (== (sewer status?) 6))
					(HandsOff)
					(Load SOUND 818)
					(Load VIEW 76)
					((ScriptID SEWER 2)
						init:
						hide:
						setCycle: Forward
						cycleSpeed: 6
						moveSpeed: (- speed 2)
						posn: 157 160
					)
					(sewer status: 4)
				)
			)
			(east
				(if
				(and (== (sewer location?) 80) (== (sewer status?) 6))
					(Load SOUND 818)
					(Load VIEW 76)
					((ScriptID SEWER 2)
						init:
						hide:
						setCycle: Forward
						setLoop: 2
						posn: 255 91
						setScript: (ScriptID SEWER 4)
					)
					(sewer status: 4)
				else
					(sewer status: 0 location: 0)
				)
				(self setScript: enterRight)
				(= temp0 11)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(ego posn: 27 94)
			)
		)
		(self style: temp0)
		((ScriptID SEWER 5)
			init:
			setLoop: 2
			posn: 113 62
			setScript: daDripScript
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 3
			posn: 250 45
			cycleSpeed: 12
			setCycle: Forward
		)
		(ego init: setPri: -1)
		(super init:)
		((ScriptID SEWER 2) show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 101 132 101 152 123 151 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 166 189 166 123 187 101 319 102 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 319 0 319 94 0 94
					yourself:
				)
		)
		(if
		(and (!= prevRoomNum 95) (!= (sewer location?) 80))
			(sewer status: 1)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouth))
			(
			(or (== (ego edgeHit?) EAST) (== (ego edgeHit?) WEST)) (sewer location: 0 status: 0))
		)
		(super doit: &rest)
		(if
			(and
				(== (dripScript state?) -1)
				(> (ego y?) 170)
				(== (sewer status?) 1)
			)
			((ScriptID SEWER 3) init: hide: setScript: dripScript)
		)
		(if
			(and
				(== (sewer status?) 3)
				(== ((ScriptID SEWER 2) loop?) 3)
				(> ((ScriptID SEWER 2) y?) 163)
			)
			(if (< (ego y?) ((ScriptID SEWER 2) y?))
				((ScriptID SEWER 2) setScript: slimeLeaving)
				(sewer status: 8)
			else
				((ScriptID SEWER 2) setMotion: 0 setScript: 0)
			)
		)
	)
	
	(method (doVerb)
		(sewer doVerb: &rest)
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
						(or (== (sewer status?) 3) (== (sewer status?) 4))
						(> ((ScriptID SEWER 2) y?) 160)
					)
					((ScriptID SEWER 2) setMotion: 0 setScript: 0)
				)
				(ego setMotion: MoveTo (ego x?) 239 self)
			)
			(2
				(if (== (sewer status?) 2)
					(sewer status: 3)
					(globalSound vol: 127 number: 819 loop: -1 playBed:)
				)
				(if
					(and
						(== (sewer location?) 95)
						(== (sewer status?) 6)
						(> (sewer rmTimer?) 7)
					)
					(sewer status: 8)
				)
				(curRoom newRoom: 95)
			)
		)
	)
)

(instance dripScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(music fade: 0 10 10 0)
				(globalSound vol: 127 number: 806 loop: 1 play:)
				(sewer location: 80)
				((ScriptID SEWER 3)
					show:
					setPri: 5
					setLoop: 4
					posn: 159 38
					cel: 0
					cycleSpeed: 12
					setCycle: CycleTo 10 1 self
				)
				((ScriptID SEWER 2)
					init:
					hide:
					setLoop: 3
					setPri: 3
					setCycle: 0
					posn: 159 60
					cycleSpeed: 6
					moveSpeed: (- speed 2)
				)
			)
			(2
				((ScriptID SEWER 3) setCel: 11)
				((ScriptID SEWER 2) show: posn: 159 65)
				(= cycles 3)
			)
			(3
				((ScriptID SEWER 3) setCel: 12)
				((ScriptID SEWER 2) posn: 159 70)
				(= cycles 3)
			)
			(4
				((ScriptID SEWER 3) setCel: 13)
				((ScriptID SEWER 2) posn: 159 75)
				(= cycles 3)
			)
			(5
				((ScriptID SEWER 3) setCel: 14)
				((ScriptID SEWER 2) posn: 159 80)
				(= cycles 3)
			)
			(6
				(globalSound vol: 127 number: 819 loop: -1 playBed:)
				((ScriptID SEWER 3) hide:)
				((ScriptID SEWER 2) posn: 159 85)
				(sewer status: 3)
				((ScriptID SEWER 2)
					setScript: (ScriptID SEWER 4)
					moveSpeed: (- speed 2)
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
				((ScriptID SEWER 5) setCycle: EndLoop self)
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
				((ScriptID SEWER 2) setMotion: MoveTo 158 210 self)
			)
			(1
				((ScriptID SEWER 2) posn: 1000 1000 dispose:)
				(sewer location: 95 status: 6 rmTimer: 0)
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
				(ego posn: 303 99 setMotion: MoveTo 294 99 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)
