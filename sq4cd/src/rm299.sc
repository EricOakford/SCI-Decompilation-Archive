;;; Sierra Script 1.0 - (do not remove this comment)
(script# 299)
(include game.sh)
(use Main)
(use butte)
(use MoveToCoords)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use LoadMany)
(use DPath)
(use Motion)
(use System)

(public
	rm299 0
)

(instance rm299 of SQRoom
	(properties
		picture 299
		east 300
		south 306
	)
	
	(method (init)
		(if (> (butte policeLanded?) 0)
			(Load VIEW 305)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(LoadMany VIEW 0 300)
		(self setRegions: BUTTE)
		(theRoom init:)
		(switch prevRoomNum
			(south
				(HandsOff)
				(ego init: setPri: 15)
				(self setScript: enterScript style: 14)
			)
			(east
				(HandsOff)
				(ego init: hide: setPri: 8)
				(self setScript: enterScript style: 11)
			)
			(else 
				(HandsOn)
				(ego init: view: 0 posn: 205 130 setPri: 8)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189 262 189 232 186 231 169 250 162 222 149 220 143
						229 137 265 143 268 147 300 147 308 122 319 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						207 165 200 173 213 178 221 189 208 189 169 189
						116 187 108 186 90 173 85 165 85 144 184 144
						193 148 205 159
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 94 315 96 303 96 296 105 270 118 217 118
						212 122 93 119 69 128 57 140 61 153 108 189
						0 189 0 0 319 0
					yourself:
				)
		)
		(super init:)
		(if
			(or
				(== (butte curPolice1Room?) 299)
				(== (butte curPolice2Room?) 299)
			)
			(butte junctioned: TRUE)
			((ScriptID BUTTE 4)
				posn: 190 134
				setCycle: Walk
				init:
				setScript: copEnters
			)
		else
			(butte junctioned: FALSE)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((== (ego onControl: 0) 2048) (HandsOff) (self setScript: exitScript))
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(script 0)
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				((ScriptID 703 4)
					posn: 315 107
					init:
					setScript: copEnters
				)
			)
			(
				(or
					(& temp0 $0010)
					(& temp0 $0040)
					(& temp0 $0080)
					(& temp0 $0100)
					(& temp0 $1000)
					(& temp0 $2000)
					(& temp0 $0002)
					(& temp0 $0004)
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
		)
		(cond 
			(
				(and
					(not script)
					(or
						(and (> (ego y?) 179) (> (ego x?) 230))
						(and (> (ego y?) 148) (< (ego x?) 152))
					)
				)
				(ego setPri: 15)
			)
			((not script) (ego setPri: 8))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
		(and (== script fallScript) (== (ego edgeHit?) 3))
			(= newRoomNumber 0)
		else
			(super newRoom: newRoomNumber)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					((& register $0010) (ego setPri: 5))
					((& register $0080) (ego setPri: 3))
					((& register $1000) (ego setPri: 5 x: (- (ego x?) 7)))
					((& register $2000) (ego x: (+ (ego x?) 7) setPri: 5))
					((& register $0002) (ego setPri: 5))
					((& register $0040) (ego setPri: 6))
					((& register $0004) (ego setPri: 10))
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
					((== prevRoomNum 306)
						(if (< (ego x?) 190)
							(ego
								posn: 182 247
								setLoop: 2
								setPri: 15
								setMotion: MoveTo 182 225 self
							)
						else
							(ego
								x: (if (> (ego x?) 250) 250 else (ego x?))
								y: 186
							)
							(= cycles 1)
						)
					)
					((== prevRoomNum 300) (= cycles 1))
				)
			)
			(1
				(cond 
					((and (== prevRoomNum 306) (< (ego x?) 190)) (= cycles 1))
					((== prevRoomNum 300)
						(ego
							init:
							show:
							illegalBits: 0
							x: 322
							y: 101
							setMotion: MoveTo 308 101 self
						)
					)
					(else
						(HandsOn)
						(ego illegalBits: cWHITE)
						(client setScript: 0)
					)
				)
			)
			(2
				(if (== prevRoomNum 300)
					(HandsOn)
					(NormalEgo 1 0 4)
					(client setScript: 0)
				else
					(ego setLoop: 1 setPri: 15 setMotion: MoveTo 152 205 self)
				)
			)
			(3
				(ego setPri: 15 setMotion: MoveTo 118 186 self)
			)
			(4
				(ego setMotion: MoveTo 97 180 self)
			)
			(5
				(ego illegalBits: cWHITE setLoop: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (< (ego x?) 190)
					(ego setLoop: -1 setHeading: 134 self)
				else
					(curRoom newRoom: (curRoom south?))
				)
			)
			(1
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(ego setMotion: MoveToY (+ 190 temp0) self)
			)
			(2
				(if (> (ego x?) 189) (ego x: 189))
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance copEnters of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(if
			(and
				(< ((ScriptID BUTTE 4) distanceTo: ego) 80)
				(not (curRoom script?))
				(not (== (ego onControl:) 16384))
			)
			((ScriptID BUTTE 4) setScript: (ScriptID 703 5))
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID BUTTE 4)
					setCycle: Walk
					setMotion: DPath 218 137 211 169 227 154 182 123 320 115 self
				)
			)
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 160
		y 90
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
