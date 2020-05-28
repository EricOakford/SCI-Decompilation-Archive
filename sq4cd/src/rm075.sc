;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Polygon)
(use Motion)
(use System)

(public
	rm075 0
)

(instance rm075 of SQRoom
	(properties
		picture 75
		east 80
		south 90
		picAngle 70
		vanishingX 319
		vanishingY -40
	)
	
	(method (init)
		(sewer roomVer: 22891)
		(Load VIEW 80)
		(switch prevRoomNum
			(east
				(HandsOn)
				(ego y: 100)
				(self style: SCROLLRIGHT)
			)
			(south
				(HandsOff)
				(if
				(and (== (sewer status?) 3) (== (sewer location?) 75))
					((ScriptID SEWER 2)
						init:
						hide:
						posn: 120 116
						setLoop: 0
						setScript: (ScriptID SEWER 4)
					)
				)
				(self setScript: enterScript style: SCROLLLEFT)
			)
			(else 
				(music number: 805 vol: 127 loop: -1 playBed:)
				(self setScript: enterScript style: 10)
			)
		)
		(ego init:)
		((ScriptID SEWER 5)
			init:
			setLoop: 0
			posn: 37 122
			setScript: daDripScript
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 1
			posn: 219 46
			cycleSpeed: 12
			setCycle: Forward
		)
		(super init:)
		((ScriptID SEWER 2) show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 153 95 36 189 0 189 0 0 319 0 319 95
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 101 319 189 50 189 159 101
					yourself:
				)
		)
		(if
		(and (!= (sewer location?) 75) (!= prevRoomNum 90))
			(sewer status: 1)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) SOUTH) (HandsOff) (curRoom setScript: exitScript))
			(
				(and
					(== (sewer location?) 90)
					(or (> (sewer rmTimer?) 7) (< (ego y?) 129))
				)
				(sewer location: 0 status: 0)
			)
		)
		(super doit: &rest)
		(if
			(and
				(> (ego y?) 170)
				(== (sewer status?) 1)
				(!= (sewer location?) 75)
			)
			((ScriptID SEWER 3) init: hide: setScript: dripScript)
			(sewer location: 75)
		)
	)
	
	(method (doVerb)
		(sewer doVerb: &rest)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: -16
					y: 246
					setLoop: 6
					setMotion: MoveTo 50 181 self
				)
				(sewer rmTimer: 0)
			)
			(1
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego setHeading: 225 self setMotion: 0)
			)
			(1
				(ego setMotion: MoveTo -16 246 self)
			)
			(2
				(if (== (sewer status?) 2) (sewer status: 3))
				(curRoom newRoom: 90)
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
				((ScriptID SEWER 3)
					show:
					setLoop: 5
					posn: 82 90
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 9 1 self
				)
				(music vol: 0 changeState:)
				(globalSound vol: 127 number: 806 loop: 1 play:)
				((ScriptID SEWER 2) posn: 109 125)
				(sewer status: 2)
			)
			(2
				((ScriptID SEWER 3) setCycle: EndLoop)
				((ScriptID SEWER 2)
					init:
					setLoop: 7
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(globalSound vol: 127 number: 819 loop: -1 playBed:)
				(sewer status: 3)
				((ScriptID SEWER 2)
					setLoop: 0
					setCycle: Forward
					cycleSpeed: 6
					moveSpeed: (- speed 2)
					setScript: (ScriptID SEWER 4)
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
