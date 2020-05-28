;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include game.sh)
(use Main)
(use butte)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm300 0
)

(instance rm300 of SQRoom
	(properties
		picture 300
		east 305
		south 310
		west 299
	)
	
	(method (init)
		(LoadMany VIEW 0 300)
		(if (not (butte sawShadow?)) (Load VIEW 301))
		(self setRegions: BUTTE)
		(theStairs init:)
		(theRoom init:)
		(ego setPri: 8)
		(switch prevRoomNum
			(south
				(HandsOn)
				(self style: SCROLLDOWN setScript: enterScript)
			)
			(east
				(HandsOn)
				(self style: SCROLLRIGHT)
				(ego
					y:
						(cond 
							((< (ego y?) 67) 67)
							((> (ego y?) 88) 88)
							(else (ego y?))
						)
					setHeading: 270 self
				)
			)
			(west
				(HandsOff)
				(self style: SCROLLLEFT setScript: enterScript)
			)
			(else 
				(HandsOn)
				(ego view: 0 illegalBits: 0 posn: 150 79)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PNearestAccess
					init: 0 0 319 0 319 65 146 65 136 45 40 65 40 82 23 91 0 92
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init:
						319 189 286 189 239 152 221 137 198 133
						191 124 176 121 154 95 162 88 319 88
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init:
						0 189 0 103 16 96 49 94 61 99 87 99
						105 109 98 124 104 133 95 151 129 189
					yourself:
				)
		)
		(ego init:)
		(super init:)
		(if (not (butte onLedgeFlag?)) (ego illegalBits: 0))
		(if (< (butte policeLanded?) 0)
			(LoadMany VIEW 305 7 5)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 144 51 163 80 115 92 62 97 43 87 46 70
						yourself:
					)
			)
			(policePod cel: 10 init: stopUpd:)
			(if
				(or
					(== (butte curPolice1Room?) 300)
					(== (butte curPolice2Room?) 300)
				)
				(butte junctioned: TRUE)
				((ScriptID BUTTE 4)
					posn: 147 90
					init:
					setScript: copEnters
				)
			else
				(butte junctioned: FALSE)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 130 52 143 68 93 78 83 83 59 84 45 80 46 70
						yourself:
					)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouthScript))
			((== (ego edgeHit?) EAST) (HandsOff) (self setScript: exitEastScript))
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(script)
			(
			(and (not (butte sawShadow?)) (& temp0 $0800))
				(if (IsObject policePod) (policePod setPri: 15))
				(ego setPri: 15)
				(butte sawShadow: (+ (butte sawShadow?) 1))
				(HandsOff)
				(curRoom overlay: 301 100)
				(self setScript: shadowScript)
			)
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				(cond 
					((== (butte oldPoliceRoom?) 305) ((ScriptID BUTTE 4) posn: 330 76))
					((== (butte oldPoliceRoom?) 310) ((ScriptID BUTTE 4) posn: 179 250))
					((== (butte oldPoliceRoom?) 299) ((ScriptID BUTTE 4) posn: 0 95))
				)
				((ScriptID BUTTE 4) init:)
				((ScriptID BUTTE 4) setScript: copEnters)
			)
			(
				(or
					(& temp0 $0004)
					(& temp0 $0200)
					(& temp0 $0010)
					(& temp0 $2000)
					(and (& temp0 $0008) (not (butte onLedgeFlag?)))
					(and (& temp0 $0020) (butte onLedgeFlag?))
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((& register $0200) 0)
					((& register $0010) (ego setPri: 4))
					((& register $0008) (ego setPri: 4))
					((& register $0020) (ego setPri: 13))
					((& register $2000)
						(ego
							x: (if (> (ego x?) 150)
								(+ (ego x?) 7)
							else
								(- (ego x?) 7)
							)
							setPri: 4
						)
					)
					((& register $0004) (ego setPri: 3))
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
					((== prevRoomNum 299) (ego posn: 8 94 setMotion: MoveTo 37 94 self))
					((butte onLedgeFlag?) (ego illegalBits: -32704 setPri: 15) (self cue:))
					(else (self cue:))
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance exitSouthScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (butte onLedgeFlag?)
					(curRoom newRoom: (curRoom south?))
				else
					(ego setMotion: MoveTo 180 250 self)
				)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance exitEastScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setHeading: 90 setMotion: MoveTo 335 (ego y?) self)
			)
			(1
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance policePod of Sq4View
	(properties
		x 149
		y 83
		view 305
		loop 1
		priority 5
		signal (| ignrAct fixPriOn)
		lookStr 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_DO (narrator say: 2))
			(else  (super doVerb: theVerb))
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
					setMotion: MoveTo 147 90 self
				)
			)
			(2
				((ScriptID BUTTE 4)
					setMotion: MoveTo (ego x?) (ego y?) self
				)
			)
		)
	)
)

(instance ptera of Sq4Actor
	(properties
		x 207
		y 68
		view 301
		loop 3
		priority 6
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
)

(instance shadowScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(ego setHeading: 44)
				(ptera init: setCycle: EndLoop self)
				(= seconds 3)
			)
			(1 (ego setHeading: 90))
			(2
				(ptera dispose:)
				(ego setHeading: 136)
				(= cycles 2)
			)
			(3
				(curRoom drawPic: 300 PLAIN)
				(= cycles 1)
			)
			(4
				(tROGER say: 1 self 0 64 5 160)
				(if (IsObject policePod) (policePod setPri: 5))
				(ego setPri: 8)
			)
			(5
				(if modelessDialog (modelessDialog dispose:))
				(theIconBar enable:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance theStairs of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $4000
		lookStr 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 3))
			(else  (super doVerb: theVerb))
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
			(V_LOOK
				(narrator modNum: 310 say: 3)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance tROGER of FaceTalker
	(properties
		noun ROGER
		modeless FALSE
		talkerNum ROGER
	)
)
