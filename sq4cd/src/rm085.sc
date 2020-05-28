;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh)
(use Main)
(use sewer)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use Motion)
(use System)

(public
	rm085 0
)

(instance rm085 of SQRoom
	(properties
		picture 85
		south 100
		west 80
		picAngle 70
		vanishingX 0
		vanishingY -40
	)
	
	(method (init)
		(sewer roomVer: 22891)
		(Load VIEW 85)
		(Load VIEW 80)
		(Load SOUND 810)
		(Load SOUND 811)
		(switch prevRoomNum
			(west
				(HandsOn)
				(ego y: 100)
				(self style: SCROLLLEFT)
				((ScriptID SEWER 3) init: hide: setScript: dripScript)
			)
			(south
				(cond 
					(
						(and
							(!= prevRoomNum 100)
							(== (sewer location?) 0)
							(== (sewer status?) 0)
							(> (Random 0 100) 50)
						)
						((ScriptID SEWER 3) init: hide: setScript: dripScript)
					)
					((== (sewer location?) 85)
						(if (== (sewer status?) 6)
							(Load SOUND 818)
							(Load VIEW 76)
							((ScriptID SEWER 2)
								init:
								hide:
								setLoop: 1
								posn: 190 109
								setScript: (ScriptID SEWER 4)
							)
							(sewer status: 4)
						)
						(if (== (sewer status?) 8)
							(if (> (Random 0 100) 50)
								((ScriptID SEWER 2)
									init:
									hide:
									setLoop: 2
									posn: -15 91
									setScript: slimeLeaving
								)
							else
								((ScriptID SEWER 2)
									init:
									hide:
									setLoop: 1
									posn: 176 97
									setScript: (ScriptID SEWER 4)
								)
								(sewer status: 4)
							)
						)
					)
				)
				(self setScript: enterScript style: 10)
			)
			(else 
				((ScriptID SEWER 3) init: hide: setScript: dripScript)
				(ego view: 0)
				(music flags: 1)
				(globalSound flags: 1)
				(self setScript: enterDoor)
			)
		)
		(door init: setPri: 10)
		(ego init: setPri: -1)
		((ScriptID SEWER 5)
			init:
			setLoop: 5
			posn: 311 144
			setScript: daDripScript
		)
		((ScriptID SEWER 6)
			init:
			setLoop: 4
			posn: 177 69
			setScript: daDripScript2
		)
		(super init:)
		(ego show:)
		((ScriptID SEWER 2) show:)
		(self
			setRegions: SEWER
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 100 160 100 271 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 319 0 319 189 286 189 181 102 165 92 151 89 0 89
					yourself:
				)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouth))
		)
		(super doit: &rest)
	)
)

(instance door of Sq4Prop
	(properties
		x 277
		y 134
		sightAngle 90
		view 85
		signal (| ignrAct ignrHrz fixedLoop)
		lookStr 1
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					x: 334
					y: 240
					setLoop: 7
					setMotion: MoveTo 280 186 self
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

(instance dripScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(if (> (ego y?) 162)
					(sewer location: 85 status: 2 distance: 300)
					(music vol: 0 fade: 0 10 10 0)
					(globalSound vol: 127 number: 806 loop: 1 play:)
					((ScriptID SEWER 3)
						show:
						setLoop: 6
						posn: 238 91
						cel: 0
						cycleSpeed: 12
						setCycle: CycleTo 9 1 self
					)
				else
					(self init:)
				)
			)
			(2
				((ScriptID SEWER 3) setCycle: EndLoop)
				((ScriptID SEWER 2)
					init:
					setLoop: 8
					cycleSpeed: 12
					setCycle: EndLoop self
					posn: 214 124
				)
			)
			(3
				(sewer status: 3)
				(globalSound vol: 127 number: 819 loop: -1 playBed:)
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

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: -1 setHeading: 130 self)
			)
			(1
				(ego setLoop: 4 setMotion: MoveTo 327 237 self)
			)
			(2
				(if (== (sewer location?) 80)
					(sewer status: 0 location: 0)
				)
				(if
					(and
						(> (Random 0 100) 60)
						(== (sewer location?) 85)
						(== (sewer status?) 3)
						(< ((ScriptID SEWER 2) y?) 135)
					)
					(sewer status: 8)
				)
				(curRoom newRoom: 100)
			)
		)
	)
)

(instance enterDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(music vol: 127 number: 805 loop: -1 playBed:)
				(ego
					init:
					hide:
					posn: 240 158
					setCycle: 0
					setLoop: -1
					setHeading: 270
				)
				(door setCel: 255 cycleSpeed: 12)
				(= cycles 1)
			)
			(1
				(globalSound vol: 127 number: 810 loop: 1 play:)
				(= cycles 5)
			)
			(2 (door setCycle: BegLoop self))
			(3
				(NormalEgo)
				(globalSound vol: 127 number: 811 play: self)
			)
			(4 (narrator say: 4 self))
			(5 (HandsOn) (self dispose:))
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

(instance daDripScript2 of Script
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
				((ScriptID SEWER 2) setMotion: MoveTo -39 91 self)
			)
			(1
				(sewer location: 80 status: 6)
				((ScriptID SEWER 2) posn: 1000 1000 dispose:)
				(self dispose:)
			)
		)
	)
)
