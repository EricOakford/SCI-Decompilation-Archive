;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include game.sh)
(use Main)
(use butte)
(use MoveToCoords)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm310 0
)

(instance rm310 of SQRoom
	(properties
		picture 310
		horizon 15
		north 300
		east 315
		south 320
		west 306
	)
	
	(method (init)
		(LoadMany VIEW 0 300)
		(if (> (butte policeLanded?) 0)
			(Load VIEW 305)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(switch prevRoomNum
			(north
				(HandsOff)
				(self setScript: enterScript style: SCROLLUP)
			)
			(south
				(HandsOff)
				(self setScript: enterScript style: SCROLLDOWN)
				(ego illegalBits: 0 setPri: 6)
			)
			(east
				(self setScript: enterScript style: SCROLLRIGHT)
				(ego
					setPri: 13
					cel: 4
					x: 305
					y: (if (< (ego y?) 103) 103 else (ego y?))
				)
			)
			(west
				(if (< (ego y?) 110)
					(ego setPri: 12)
				else
					(ego setPri: 14)
				)
				(self style: 12 setScript: enterScript)
			)
			(else 
				(ego illegalBits: 0 posn: 155 92 setPri: 6)
				(HandsOn)
			)
		)
		(if
			(and
				(== prevRoomNum 300)
				(not (butte sawGirlShadow?))
				(not (Btst fMeetLatexBabes))
			)
			(shadow init: setScript: shadowMoves)
		)
		(ego init:)
		(super init:)
		(if (or (== prevRoomNum 300) (== prevRoomNum 320))
			(self
				addObstacle:
					((Polygon new:)
						type: PNearestAccess
						init:
							157 0 157 11 128 13 124 30 89 49 60 78 69 94
							109 107 195 175 199 189 0 189 0 0
						yourself:
					)
					((Polygon new:)
						type: PNearestAccess
						init:
							319 0 319 189 273 189 259 173 265 150 236 105
							185 76 185 46 225 41 278 12 280 0
						yourself:
					)
			)
			(theBridge init:)
			(theRoom init:)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 102 34 105 32 115 0 123
						yourself:
					)
					((Polygon new:)
						type: PNearestAccess
						init:
							319 189 0 189 0 129 20 123 44 121 55 112 54 104 21 95
							0 94 0 43 17 41 57 21 167 0 319 0 319 100 272 103 260 106
							196 116 180 128 162 136 155 145 213 141 236 152 226 159
							259 158 287 154 285 149 227 145 226 135 288 123 319 120
						yourself:
					)
			)
			(theStairs init:)
			(theRoom init:)
			(ego illegalBits: cWHITE)
		)
		(cond 
			((== (butte curPolice1Room?) 310)
				(butte junctioned: TRUE)
				((ScriptID BUTTE 4)
					loop: 1
					setPri: 13
					posn: 240 120
					init:
					setScript:
						(cond 
							((or (== prevRoomNum 300) (== prevRoomNum 320)) copOnLedge)
							((== prevRoomNum 306) egoOnLedge)
							(else copEnters)
						)
				)
			)
			((== (butte curPolice2Room?) 310)
				(butte junctioned: TRUE)
				((ScriptID BUTTE 4)
					posn: 136 70
					setPri: 8
					init:
					loop: (if (== (butte oldPoliceRoom?) 320) 3 else 2)
					setScript:
						(if (or (== prevRoomNum 300) (== prevRoomNum 320))
							copEnters
						else
							copOnStairs
						)
				)
			)
			(else (butte junctioned: 0))
		)
		(self setRegions: BUTTE)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitNorthScript))
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(
				(and
					(== script (ScriptID BUTTE 1))
					(== (ego edgeHit?) SOUTH)
					(< (ego x?) 160)
					(> (ego y?) 38)
				)
				(butte egoSwims: TRUE)
				(HandsOff)
				(self setScript: exitScript)
			)
			(script)
			((== (ego edgeHit?) 3) (HandsOff) (self setScript: exitScript))
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				(cond 
					((== (butte curPolice1Room?) 310)
						(cond 
							((or (== prevRoomNum 300) (== prevRoomNum 320))
								((ScriptID BUTTE 4)
									posn: 325 120
									setPri: 13
									init:
									setScript: copOnLedge
								)
							)
							((== prevRoomNum 306)
								((ScriptID BUTTE 4)
									posn: 325 120
									setPri: 13
									init:
									setScript: egoOnLedge
								)
							)
							(else
								(cond 
									((== (butte oldPoliceRoom?) 300) ((ScriptID BUTTE 4) posn: 177 0))
									((== (butte oldPoliceRoom?) 315) ((ScriptID BUTTE 4) posn: 325 110))
									((== (butte oldPoliceRoom?) 320) ((ScriptID BUTTE 4) posn: 195 250))
								)
								((ScriptID BUTTE 4) init: setScript: copEnters)
							)
						)
					)
					((== (butte curPolice2Room?) 310)
						(butte junctioned: TRUE)
						(if (or (== prevRoomNum 300) (== prevRoomNum 320))
							(cond 
								((== (butte oldPoliceRoom?) 300) ((ScriptID BUTTE 4) posn: 177 0))
								((== (butte oldPoliceRoom?) 315) ((ScriptID BUTTE 4) posn: 325 110))
								((== (butte oldPoliceRoom?) 320) ((ScriptID BUTTE 4) posn: 195 250))
							)
							((ScriptID BUTTE 4) init: setScript: copEnters)
						else
							(cond 
								((== (butte oldPoliceRoom?) 300) ((ScriptID BUTTE 4) posn: 177 0))
								((== (butte oldPoliceRoom?) 320) ((ScriptID BUTTE 4) posn: 195 200))
							)
							((ScriptID BUTTE 4) init: setScript: copOnStairs)
						)
					)
				)
			)
			(
				(or
					(& temp0 $0002)
					(& temp0 $0004)
					(& temp0 $0040)
					(& temp0 $0080)
					(& temp0 $0008)
				)
				(if (or (== prevRoomNum 300) (== prevRoomNum 320))
					(HandsOff)
					(self setScript: fallScript 0 temp0)
				)
			)
			(
				(and
					(or
						(& temp0 $0010)
						(& temp0 $0100)
						(& temp0 $2000)
						(& temp0 $0020)
					)
					(or (== prevRoomNum 306) (== prevRoomNum 315))
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
		)
		(return
			(cond 
				(script)
				((and (== prevRoomNum 315) (> (ego y?) 150)) (ego setPri: 14))
				((and (== prevRoomNum 315) (ego setPri: 13)))
				((and (== prevRoomNum 306) (> (ego y?) 110)) (ego setPri: 14))
				((and (== prevRoomNum 306) (ego setPri: 12)))
				(else 0)
			)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					((& register $0100) (ego setPri: (- (ego priority?) 3)))
					((& register $0002)
						(ego
							posn: (+ (ego x?) 7) (- (ego y?) 5)
							setPri: (- (ego priority?) 3)
						)
					)
					((& register $0004) (ego x: (+ (ego x?) 10) setPri: (- (ego priority?) 3)))
					((& register $0040) (ego x: (- (ego x?) 10)))
					((& register $0080) (ego x: (- (ego x?) 7) setPri: (- (ego priority?) 3)))
					((& register $0010)
						(if (< (ego y?) 150)
							(ego setPri: (- (ego priority?) 1))
						else
							(ego setPri: 15)
						)
					)
					((& register $2000) (ego x: (+ (ego x?) 6) setPri: (- (ego priority?) 1)))
					((& register $0008)
						(ego
							posn: (+ (ego x?) 4) (- (ego y?) 5)
							setPri: (- (ego priority?) 3)
						)
					)
					((& register $0020) (ego setPri: 9))
				)
				(curRoom setScript: (ScriptID BUTTE 1))
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
				(if (not (butte egoSwims?))
					(ego setHeading: 180 self)
				else
					(self cue:)
				)
			)
			(1
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(ego setMotion: MoveToY (+ 189 temp0) self)
			)
			(2
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance exitNorthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(not
						(if (== prevRoomNum 300) else (== prevRoomNum 320))
					)
					(butte onLedgeFlag: 1)
					(ego illegalBits: 0 setMotion: MoveTo 133 -2 self)
				else
					(ego illegalBits: 0 setMotion: MoveTo (ego x?) -2 self)
				)
			)
			(1
				(curRoom newRoom: (curRoom north?))
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
					((== prevRoomNum 300)
						(if (butte onLedgeFlag?)
							(butte onLedgeFlag: 0)
							(= prevRoomNum 306)
							(ego
								illegalBits: 0
								setPri: 9
								posn: (ego x?) 0
								setMotion: MoveTo (- (ego x?) 50) 17 self
							)
						else
							(ego
								view: 0
								illegalBits: 0
								setPri: 6
								x: (if (<= (ego x?) 203) (ego x?) else 203)
								y: 48
								setMotion: MoveTo 180 49 self
							)
						)
					)
					((== prevRoomNum 320)
						(ego
							posn: (if (> (ego x?) 190)
								(- (ego x?) 5)
							else
								(+ (ego x?) 5)
							) 189
							setMotion: MoveTo 192 140 self
						)
					)
					(
						(and
							(== prevRoomNum 306)
							(< 75 (ego y?))
							(< (ego y?) 110)
						)
						(ego
							x: (+
								0
								(/ (CelWide (ego view?) (ego loop?) (ego cel?)) 2)
							)
							setMotion: MoveTo 21 99 self
						)
					)
					((== prevRoomNum 315)
						(ego
							x: (-
								319
								(/ (CelWide (ego view?) (ego loop?) (ego cel?)) 2)
							)
						)
						(= cycles 1)
					)
					(else
						(ego
							x: (+
								0
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

(instance copOnStairs of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID BUTTE 4)
					setCycle: Walk
					setMotion: MoveTo 136 70 self
				)
			)
			(2
				(if (== ((ScriptID BUTTE 4) loop?) 2)
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
					(self dispose:)
				else
					((ScriptID BUTTE 4) setMotion: MoveTo 182 -1 self)
				)
			)
		)
	)
)

(instance copOnLedge of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID BUTTE 4)
					setCycle: Walk
					setMotion: MoveTo 240 ((ScriptID BUTTE 4) y?) self
				)
			)
			(2
				((ScriptID BUTTE 4)
					setMotion: MoveTo ((ScriptID BUTTE 4) x?) (- ((ScriptID BUTTE 4) y?) 3)
				)
				(if
				(and (< (ego y?) 140) (not (curRoom script?)))
					(ScriptID BUTTE 3)
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(3
				((ScriptID BUTTE 4)
					setMotion:
						MoveTo
						(- ((ScriptID BUTTE 4) x?) 3)
						((ScriptID BUTTE 4) y?)
						self
				)
			)
			(4
				((ScriptID BUTTE 4)
					setMotion:
						MoveTo
						(+ ((ScriptID BUTTE 4) x?) 3)
						(- ((ScriptID BUTTE 4) y?) 3)
				)
				(if
				(and (< (ego y?) 110) (not (curRoom script?)))
					(ScriptID BUTTE 3)
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(5
				((ScriptID BUTTE 4)
					setMotion: MoveTo 330 ((ScriptID BUTTE 4) y?) self
				)
			)
		)
	)
)

(instance egoOnLedge of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				1
				((ScriptID BUTTE 4)
					setCycle: Walk
					setMotion: MoveTo 169 135 self
				)
			)
			(2
				2
				(if (not (curRoom script?))
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
				)
				(self dispose:)
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
				(< ((ScriptID BUTTE 4) distanceTo: ego) 60)
				(not (curRoom script?))
			)
			((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
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
					setMotion: PolyPath (ego x?) (ego y?) self
				)
			)
		)
	)
)

(instance shadow of Sq4Actor
	(properties
		x 38
		y 187
		view 310
		lookStr 1
	)
)

(instance shadowMoves of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not script)
				(not (curRoom script?))
				(== prevRoomNum 300)
				(not (butte sawGirlShadow?))
			)
			(butte sawGirlShadow: TRUE)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				1
				(shadow setCycle: EndLoop self)
			)
			(2
				2
				(shadow
					setMotion:
						MoveTo
						(-
							(shadow x?)
							(CelWide (shadow view?) (shadow loop?) (shadow cel?))
						)
						(+
							(shadow y?)
							(CelHigh (shadow view?) (shadow loop?) (shadow cel?))
						)
						self
				)
			)
			(3
				3
				(narrator say: 2)
				(shadow dispose:)
				(self dispose:)
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
			(V_LOOK (narrator say: 3))
			(else  (super doVerb:))
		)
	)
)

(instance theStairs of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $4000
		lookStr 4
	)
)

(instance theBridge of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0800
		lookStr 5
	)
)
