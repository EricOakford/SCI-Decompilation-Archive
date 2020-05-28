;;; Sierra Script 1.0 - (do not remove this comment)
(script# 315)
(include game.sh)
(use Main)
(use butte)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm315 0
)

(local
	[poly1Pts 14] = [59 0 118 52 120 79 96 91 51 99 0 101]
	[poly2Pts 34] = [0 119 67 119 72 125 116 125 119 129 161 129 220 119 251 109 251 102 267 94 277 89 240 72 192 15 192 0 319 0 319 189 0 189]
)
(instance rm315 of SQRoom
	(properties
		picture 315
		horizon 15
		north 305
		west 310
	)
	
	(method (init)
		(LoadMany VIEW 0 303 300)
		(Load SOUND 52)
		(if (> (butte policeLanded?) 0)
			(Load VIEW 305)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(self setRegions: BUTTE)
		(theRoom init:)
		(switch prevRoomNum
			(north
				(HandsOn)
				(butte entered315: (+ (butte entered315?) 1))
				(self setScript: enterScript style: 13)
			)
			(west
				(HandsOn)
				(self style: SCROLLLEFT)
			)
			(else 
				(HandsOn)
				(ego view: 0 posn: 155 110)
			)
		)
		(poly1 points: @poly1Pts size: 7)
		(poly2 points: @poly2Pts size: 17)
		(self addObstacle: poly1 poly2)
		(ego setPri: 8 init:)
		(super init:)
		(if
			(or
				(== (butte curPolice1Room?) 315)
				(== (butte curPolice2Room?) 315)
			)
			(butte junctioned: TRUE)
			((ScriptID BUTTE 4)
				posn: 150 110
				init:
				setScript: copEnters
			)
		else
			(butte junctioned: FALSE)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			(
			(and (== script fallScript) (== (ego edgeHit?) 3)) 0)
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitScript))
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(script)
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				(cond 
					((== (butte oldPoliceRoom?) 305) ((ScriptID BUTTE 4) posn: 150 -5))
					((== (butte oldPoliceRoom?) 310) ((ScriptID BUTTE 4) posn: -5 110))
				)
				((ScriptID BUTTE 4) init:)
				((ScriptID BUTTE 4) setScript: copEnters)
			)
			(
				(or
					(& temp0 $0004)
					(& temp0 $0010)
					(& temp0 $0040)
					(& temp0 $0080)
					(& temp0 $0100)
					(& temp0 $0200)
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
			(
				(and
					(butte sawShadow?)
					(< 48 (ego y?))
					(< (ego y?) 51)
					(not (Btst fMeetLatexBabes))
				)
				(butte beenCaptured: TRUE)
				(self setScript: pteraDives)
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
					((& register $0004) (ego setPri: 1))
					((& register $0010) (ego setPri: 6))
					(else (ego setPri: 3))
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
				(ego
					x:
						(cond 
							((< (ego x?) 112) 112)
							((> (ego x?) 214) 214)
							(else (ego x?))
						)
					y: 50
				)
				(= cycles 1)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) -2 self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance ptera of Sq4Actor
	(properties
		x 220
		y -40
		yStep 15
		view 303
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
		xStep 35
		lookStr 1
	)
)

(instance pteraDives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ptera
					x: (+ (ego x?) 40)
					init:
					setMotion: pteraChase ego 5 self
				)
			)
			(1
				(music number: 52 loop: -1 vol: 127 flags: mNOPAUSE playBed:)
				(HandsOff)
				(ego hide:)
				(ptera x: (+ (ptera x?) 28) setCel: 1)
				(= cycles 2)
			)
			(2
				(ptera x: (- (ptera x?) 35) setCel: 2)
				(= cycles 2)
			)
			(3
				(ptera xStep: 10 setMotion: MoveTo -20 -60 self)
			)
			(4
				(ptera dispose:)
				(curRoom newRoom: 298)
				(client setScript: 0)
			)
		)
	)
)

(class pteraChase of Motion
	(properties
		who 0
		distance 0
	)
	
	(method (init theClient theWho theDistance theCaller)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= who theWho)
				(if (>= argc 3)
					(= distance theDistance)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(super init: client (who x?) (- (who y?) 55) caller)
	)
	
	(method (doit)
		(if (self onTarget:)
			(self moveDone:)
		else
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (- (who y?) 55) caller)
			)
		)
	)
	
	(method (onTarget)
		(return
			(if (<= (Abs (- (client x?) (who x?))) distance)
				(<= (Abs (- (client y?) (- (who y?) 55))) distance)
			else
				0
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
					setMotion: MoveTo 150 110 self
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

(instance poly1 of Polygon
	(properties)
)

(instance poly2 of Polygon
	(properties)
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
			(V_LOOK (narrator say: 2))
			(else  (super doVerb:))
		)
	)
)
