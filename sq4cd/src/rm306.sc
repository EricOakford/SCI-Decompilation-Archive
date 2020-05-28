;;; Sierra Script 1.0 - (do not remove this comment)
(script# 306)
(include game.sh)
(use Main)
(use butte)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm306 0
)

(instance rm306 of SQRoom
	(properties
		picture 306
		horizon 15
		north 299
		east 310
	)
	
	(method (init)
		(LoadMany VIEW 0 300)
		(if (> (butte policeLanded?) 0)
			(Load VIEW 305)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(self setRegions: BUTTE)
		(theRoom init:)
		(ego
			illegalBits: 0
			setPri:
				(cond 
					((== prevRoomNum north) 4)
					((> (ego y?) 115) 15)
					(else 8)
				)
			init:
		)
		(switch prevRoomNum
			(north
				(HandsOff)
				(self setScript: enterScript style: 13)
			)
			(east
				(HandsOff)
				(self style: 11 setScript: enterScript)
			)
			(else 
				(HandsOn)
				(ego posn: 181 135)
			)
		)
		(super init:)
		(butte junctioned: FALSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 202 0 252 52 189 101 193 114 136 109 88 67 148 0
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init:
						111 0 112 37 95 46 82 71 95 99 74 118 82 124
						93 124 125 141 202 143 253 149 283 140 301 139
						319 135 319 189 0 189 0 0
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init: 319 52 319 94 269 99 255 124 197 115 197 105 268 48
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 124 274 115 272 101 319 103
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 285 0 319 0 319 22 285 14
					yourself:
				)
		)
		(if
			(or
				(== (butte curPolice1Room?) 306)
				(== (butte curPolice2Room?) 306)
			)
			(butte junctioned: TRUE)
			(copEnters start: 3)
			((ScriptID BUTTE 4)
				posn: 132 131
				setCycle: Walk
				init:
				setScript: copEnters
			)
		else
			(butte junctioned: 0)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			(
			(and (== script fallScript) (== (ego edgeHit?) SOUTH)) 0)
			((and (not script) (== (ego edgeHit?) 1)) (HandsOff) (self setScript: exitScript))
		)
		(super doit:)
		(= temp0 (ego onControl: 1))
		(cond 
			(script)
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				((ScriptID BUTTE 4) setScript: copEnters)
			)
			(
				(or
					(& temp0 $0010)
					(& temp0 $0020)
					(& temp0 $0040)
					(& temp0 $0080)
					(& temp0 $0100)
					(& temp0 $0002)
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
		)
		(cond 
			(script)
			((and (< (ego x?) 167) (< (ego y?) 67)) (ego setPri: 4))
			((and (> (ego x?) 270) (> (ego y?) 113)) (ego setPri: 11))
			(else (ego setPri: 8))
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					((& register $0002) (ego setPri: 15))
					((& register $0010) 0)
					((& register $0020) (ego setPri: 3))
					((& register $0040) (ego setPri: 2))
					((& register $0080) (ego x: (- (ego x?) 7) setPri: 1))
					((& register $0100) (ego setPri: 3))
				)
				(curRoom setScript: (ScriptID BUTTE 1))
				(self dispose:)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== prevRoomNum 299)
						(if (< (ego x?) 190)
							(ego x: 120 y: 30 setMotion: MoveTo 99 50 self)
						else
							(ego
								x: (ego x?)
								y: -5
								setMotion: MoveTo (+ (ego x?) 23) 30 self
							)
						)
					)
					((and (< 75 (ego y?)) (< (ego y?) 110))
						(ego
							x: (-
								319
								(/ (CelWide (ego view?) (ego loop?) (ego cel?)) 2)
							)
							setMotion: MoveTo 283 99 self
						)
					)
					(else
						(ego
							x: (-
								319
								(/ (CelWide (ego view?) (ego loop?) (ego cel?)) 2)
							)
						)
						(= cycles 1)
					)
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) -3 self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance copEnters of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(< ((ScriptID BUTTE 4) distanceTo: ego) 80)
				(not (curRoom script?))
				state
			)
			((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (butte oldPoliceRoom?) 299)
					((ScriptID BUTTE 4) posn: 121 50)
				)
				((ScriptID BUTTE 4)
					setPri: 4
					init:
					illegalBits: 0
					setCycle: Walk
					setMotion: PolyPath 101 57 self
				)
			)
			(1
				((ScriptID BUTTE 4)
					setPri: 8
					setMotion: PolyPath 97 78 self
				)
			)
			(2
				((ScriptID BUTTE 4) setMotion: PolyPath 132 131 self)
			)
			(3
				((ScriptID BUTTE 4)
					setMotion: PolyPath (ego x?) (ego y?) self
				)
			)
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				((ScriptID BUTTE 6) doVerb: theVerb)
			)
			(V_LOOK (narrator say: 1))
			(else  (super doVerb:))
		)
	)
)
