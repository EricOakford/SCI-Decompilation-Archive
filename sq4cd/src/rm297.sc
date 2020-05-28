;;; Sierra Script 1.0 - (do not remove this comment)
(script# 297)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm297 0
)

(local
	[egoFallCycle 41] = [0 2 202 19 0 2 198 28 0 3 194 35 0 2 191 55 0 3 188 69 0 2 185 90 0 3 182 104 0 2 178 125 0 3 175 141 0 6 169 106 -32768]
	[egoLandCycle 21] = [0 6 166 88 0 5 159 99 0 4 158 132 0 5 156 124 0 6 157 127 -32768]
	[spFallCycle 29] = [0 0 75 9 0 0 77 32 0 0 78 56 0 0 78 77 0 1 84 79 0 2 89 103 0 3 89 98 -32768]
	local91
)
(instance rm297 of SQRoom
	(properties
		picture 297
	)
	
	(method (init &tmp [temp0 50])
		(LoadMany SOUND 129 130 131 134)
		(LoadMany VIEW 392 304)
		(Load SCRIPT MOVECYC)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 233 189 220 154
						227 133 191 124 152 118 112 121 92 128
						79 129 93 154 206 154 218 189
					yourself:
				)
		)
		(hole init:)
		(theSkulls init:)
		(theNest init:)
		(theRoom init:)
		(ego
			init:
			view: 392
			normal: 0
			setLoop: 1
			x: 202
			y: -20
			setPri: 6
		)
		(if (Btst fSpFellInNest)
			(sp init: loop: 4 cel: 0 posn: 89 98 stopUpd:)
		)
		(super init:)
		(self setScript: fallScript)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script (script doit:))
			(
				(and
					(not local91)
					(not (ego mover?))
					(Btst fSpFellInNest)
					(== (Random 1 3000) 50)
				)
				(= local91 1)
				(maharg init:)
			)
			((IsObjectOnControl ego 1024) (HandsOff) (curRoom setScript: exitNest))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(== script exitNest)
				(< (exitNest state?) 3)
				(== (ego edgeHit?) SOUTH)
			)
			(= newRoomNumber 0)
		else
			(super newRoom: newRoomNumber)
		)
	)
)

(instance maharg of Sq4Prop
	(properties
		y 50
		view 304
		loop 3
		signal fixedLoop
		lookStr 1
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward doit:)
	)
	
	(method (doit)
		(super doit:)
		(if (<= x 320)
			(self x: (+ x 3) y: (- y 1))
		else
			(self dispose:)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(music stop:)
				(aSound number: 131 loop: 1 play:)
				(ego setCycle: MoveCycle @egoFallCycle self)
			)
			(1
				(aSound number: 129 play:)
				(ego setCycle: MoveCycle @egoLandCycle self)
			)
			(2
				(aSound number: 130 play:)
				(= cycles 10)
			)
			(3
				(ego
					view: 0
					normal: 1
					setLoop: -1
					loop: 2
					x: 157
					y: 127
					setCycle: StopWalk 4
					setPri: -1
					setStep: 3 2
				)
				(music number: 53 loop: -1 vol: 127 playBed:)
				(= cycles 1)
			)
			(4
				(if (not (Btst fMeetLatexBabes)) (narrator say: 2))
				(= cycles 1)
			)
			(5
				(if (not (Btst fSpFellInNest)) (sp init: setScript: spFallScript))
				(= cycles 1)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance spFallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fMeetLatexBabes) (self cue:) else (= cycles 75))
			)
			(1
				(Bset fSpFellInNest)
				(spSound play:)
				(= cycles 3)
			)
			(2
				(sp setCycle: MoveCycle @spFallCycle self)
			)
			(3
				(spSound number: 134 vol: 127 play:)
				(sp stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance searchBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 105 130 self)
			)
			(1
				(ego
					normal: 0
					view: 304
					loop: 1
					cel: 0
					x: (- (ego x?) 5)
					cycleSpeed: 18
					setCycle: EndLoop
				)
				(= cycles 20)
			)
			(2
				(ego setCycle: BegLoop)
				(= cycles 20)
			)
			(3
				(narrator say: 3)
				(SolvePuzzle fGetGum 5)
				(ego
					normal: TRUE
					view: 0
					cycleSpeed: 6
					setCycle: StopWalk 4
					setMotion: MoveTo (ego x?) (+ (ego y?) 2) self
				)
			)
			(4
				(HandsOn)
				(ego illegalBits: cWHITE)
				(client setScript: 0)
			)
		)
	)
)

(instance exitNest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 212 158 self)
			)
			(1
				(ego
					normal: 0
					view: 304
					setLoop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: CycleTo 10 1 self
				)
			)
			(2
				(ego setPri: 14 setCycle: EndLoop self)
			)
			(3
				(globalSound number: 131 vol: 127 loop: 1 play:)
				(ego yStep: 20 setMotion: MoveTo (ego x?) 240 self)
			)
			(4 (= seconds 3))
			(5
				(globalSound number: 143 loop: 0 setVol: 127 play: self)
			)
			(6 (music fade: self))
			(7
				(ego illegalBits: cWHITE)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance sp of Sq4Prop
	(properties
		x 75
		y -20
		sightAngle 45
		view 304
		priority 6
		signal (| ignrAct fixPriOn)
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_DO
				(if (== ((inventory at: iPaperWithGum) owner?) 297)
					(ego get: iPaperWithGum)
					(HandsOff)
					(curRoom setScript: searchBody)
				else
					(narrator say: 5)
				)
			)
			(V_TALK
				(tRogNar say: 1)
				(narrator say: 6)
			)
			(V_SMELL (narrator say: 7))
			(V_TASTE (narrator say: 8))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hole of Sq4Feature
	(properties
		x 211
		y 152
		nsTop 146
		nsLeft 200
		nsBottom 158
		nsRight 223
		sightAngle 45
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: exitNest)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 45
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(switch theVerb
			(V_TALK
				(if (not (curRoom script?))
					(curRoom setScript: sEcho)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sEcho of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 3])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setCycle: 0)
				(= cycles 2)
			)
			(1
				(ego view: 297 cel: 0)
				(= ticks 30)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (tRogNar say: 2 self))
			(4 (ego setCycle: BegLoop self))
			(5
				(NormalEgo 3 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance theSkulls of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $4000
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(switch theVerb
			(V_TALK (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theNest of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 45
		onMeCheck $0020
		lookStr 13
	)
)

(instance aSound of Sound
	(properties)
)

(instance spSound of Sound
	(properties
		number 131
		vol 85
	)
)

(instance tRogNar of Sq4Narrator
	(properties
		noun ROGER
		modNum 297
		disposeWhenDone FALSE
		talkerNum ROGER
	)
)
