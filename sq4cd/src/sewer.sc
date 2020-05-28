;;; Sierra Script 1.0 - (do not remove this comment)
(script# SEWER) ;702
(include game.sh)
(use Main)
(use Sq4Feature)
(use IconBar)
(use PolyPath)
(use Reverse)
(use Motion)
(use Game)
(use Invent)
(use System)

(public
	sewer 0
	theDeathScript 1
	theSlime 2
	theDrip 3
	theSlimeScript 4
	theDrip1 5
	theDrip2 6
)

(local
	local0
	egoScoopX
	egoScoopY
	[local3 52]
	local55
	local56
	local57
	local58
	egoX
	egoY
	local61
	slimeX
	slimeY
	local64
	local65
	local66
	local67
)
(procedure (localproc_0406)
	(cond 
		((== (theSlime loop?) 3)
			(= local55 (- (theSlime x?) 9))
			(= local56 (- (theSlime y?) 27))
			(= local57 (- (theSlime x?) 9))
			(= local58 (+ (theSlime y?) 26))
			(sewer distance: (localproc_0922))
			(= local55 (- (theSlime x?) 9))
			(= local56 (- (theSlime y?) 27))
			(= local57 (+ (theSlime x?) 9))
			(= local58 (- (theSlime y?) 27))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (+ (theSlime x?) 9))
			(= local56 (- (theSlime y?) 27))
			(= local57 (+ (theSlime x?) 9))
			(= local58 (+ (theSlime y?) 26))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (- (theSlime x?) 9))
			(= local56 (+ (theSlime y?) 26))
			(= local57 (+ (theSlime x?) 9))
			(= local58 (+ (theSlime y?) 26))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
		)
		((== (theSlime loop?) 2)
			(= local55 (- (theSlime x?) 35))
			(= local56 (- (theSlime y?) 5))
			(= local57 (- (theSlime x?) 35))
			(= local58 (+ (theSlime y?) 6))
			(sewer distance: (localproc_0922))
			(= local55 (- (theSlime x?) 35))
			(= local56 (- (theSlime y?) 5))
			(= local57 (+ (theSlime x?) 35))
			(= local58 (- (theSlime y?) 5))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (+ (theSlime x?) 35))
			(= local56 (- (theSlime y?) 5))
			(= local57 (+ (theSlime x?) 35))
			(= local58 (+ (theSlime y?) 6))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (+ (theSlime x?) 35))
			(= local56 (+ (theSlime y?) 6))
			(= local57 (- (theSlime x?) 35))
			(= local58 (+ (theSlime y?) 6))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
		)
		((== (theSlime loop?) 0)
			(= local55 (- (theSlime x?) 28))
			(= local56 (+ (theSlime y?) 15))
			(= local57 (+ (theSlime x?) 9))
			(= local58 (- (theSlime y?) 17))
			(sewer distance: (localproc_0922))
			(= local55 (- (theSlime x?) 28))
			(= local56 (+ (theSlime y?) 15))
			(= local57 (- (theSlime x?) 10))
			(= local58 (+ (theSlime y?) 21))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (- (theSlime x?) 10))
			(= local56 (+ (theSlime y?) 21))
			(= local57 (+ (theSlime x?) 23))
			(= local58 (- (theSlime y?) 9))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (+ (theSlime x?) 9))
			(= local56 (- (theSlime y?) 17))
			(= local57 (+ (theSlime x?) 23))
			(= local58 (- (theSlime y?) 9))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
		)
		((== (theSlime loop?) 1)
			(= local55 (- (theSlime x?) 24))
			(= local56 (- (theSlime y?) 9))
			(= local57 (+ (theSlime x?) 13))
			(= local58 (+ (theSlime y?) 23))
			(sewer distance: (localproc_0922))
			(= local55 (- (theSlime x?) 24))
			(= local56 (- (theSlime y?) 9))
			(= local57 (- (theSlime x?) 8))
			(= local58 (- (theSlime y?) 17))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (- (theSlime x?) 8))
			(= local56 (- (theSlime y?) 17))
			(= local57 (+ (theSlime x?) 28))
			(= local58 (+ (theSlime y?) 16))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
			(= local55 (+ (theSlime x?) 13))
			(= local56 (+ (theSlime y?) 23))
			(= local57 (+ (theSlime x?) 28))
			(= local58 (+ (theSlime y?) 16))
			(if
			(< (= local61 (localproc_0922)) (sewer distance?))
				(sewer distance: local61)
			)
		)
	)
)

(procedure (localproc_0922 &tmp temp0)
	(return
		(if
			(and
				(<=
					0
					(localproc_09cc
						(- local57 local55)
						(- local58 local56)
						(- egoX local55)
						(- egoY local56)
					)
				)
				(<=
					0
					(localproc_09cc
						(- local55 local57)
						(- local56 local58)
						(- egoX local57)
						(- egoY local58)
					)
				)
			)
			(return
				(if
				(= temp0 (GetDistance local55 local56 local57 local58))
					(/
						(Abs
							(localproc_09cc
								(- local58 local56)
								(- local55 local57)
								(- egoX local55)
								(- egoY local56)
							)
						)
						temp0
					)
				else
					0
				)
			)
		else
			(return
				(Min
					(GetDistance egoX egoY local55 local56)
					(GetDistance egoX egoY local57 local58)
				)
			)
		)
	)
)

(procedure (localproc_09cc param1 param2 param3 param4)
	(return
		(if (< (sewer distance?) 200)
			(return (+ (* param1 param3) (* param2 param4)))
		else
			(return
				(+
					(* (+ (/ param1 2) 1) (+ (/ param3 2) 1))
					(* (+ (/ param2 2) 1) (+ (/ param4 2) 1))
				)
			)
		)
	)
)

(class sewer of Region
	(properties
		regionVer 22891
		roomVer 0
		location 0
		cantDie 0
		status 0
		distance 0
		prevDistance 0
		oldDistance 0
		angle 0
		scoopLoop 0
		rmTimer 0
		dbg 0
	)
	
	(method (init)
		(super init: &rest)
		(Load VIEW 75)
		(Load VIEW 76)
		(Load VIEW 4)
		(Load SOUND 806)
		(Load SOUND 805)
		(Load SOUND 819)
		(sewer cantDie: FALSE)
		(if (OneOf curRoomNum 75 80 85 105 115)
			(thePipes init:)
			(theConduit init:)
			(theVent
				init:
				x:
				(switch curRoomNum
					(75 77)
					(80 158)
					(85 242)
					(105 131)
					(115 188)
				)
				y:
				(switch curRoomNum
					(75 81)
					(80 40)
					(85 80)
					(else  2)
				)
			)
		else
			(thePipes init:)
			(theConduit init:)
		)
		(if (not (sewer location?)) (theSlime posn: 1000 1000))
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (!= local64 (GetTime 1))
			(= local64 (GetTime 1))
			(= local65 1)
		)
		(if local65
			(= local65 0)
			(++ local67)
			(self rmTimer: local67)
			(if (== (self status?) 4)
				(++ local66)
				(if (and (not (sewer cantDie?)) (> local66 5))
					(HandsOff)
					(narrator modNum: SEWER say: 1)
					(curRoom setScript: theDeathScript)
				)
			)
		)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(if
		(and (!= (theSlime x?) 1000) (!= (theSlime y?) 1000))
			(localproc_0406)
		)
		(sewer
			angle: (GetAngle
				(ego x?)
				(ego y?)
				(theSlime x?)
				(theSlime y?)
			)
		)
		(if
			(and
				(< (sewer distance?) 5)
				(== (sewer status?) 4)
				(not local0)
				(not (sewer cantDie?))
			)
			(curRoom setScript: theDeathScript)
			(sewer status: 6)
		)
		(if
		(and (> (sewer distance?) 17) (== (sewer status?) 4))
			(sewer status: 3)
			(theSlime setScript: theSlimeScript)
			(= local66 0)
		)
		(if
			(and
				(< (sewer status?) 7)
				(> (sewer distance?) 0)
				(< (sewer distance?) 9)
				(== (sewer status?) 3)
				(== (sewer location?) curRoomNum)
				(not (sewer cantDie?))
			)
			(sewer status: 4)
			(theSlime setScript: 0 setCycle: Forward setMotion: 0)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= keep
			(OneOf newRoomNumber 75 80 85 90 95 100 105 110 115)
		)
		(= initialized 0)
		(theSlime setCycle: 0 setMotion: 0)
		(super newRoom: newRoomNumber &rest)
		(= local67 0)
		(self
			oldDistance: (sewer prevDistance?)
			prevDistance: (sewer distance?)
		)
	)
)

(instance theDrip of Sq4Actor
	(properties
		yStep 1
		view 75
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
		xStep 1
		lookStr 2
	)
	
	(method (init)
		(super init:)
		(Load SCRIPT REVERSE)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: SEWER say: 2)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance theDeathScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (self state?) 4) (<= (music prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(sewer status: 7)
				(globalSound vol: 0 loop: 1 fade: 0 10 10 0)
				(music vol: 127 number: 818 loop: 1 playBed:)
				(ego
					illegalBits: 0
					ignoreActors: 1
					cycleSpeed: 6
					setMotion: 0
					setLoop: -1
					setHeading: 180 self
				)
			)
			(1
				(theSlime
					moveSpeed: (- speed 2)
					cycleSpeed: 3
					setStep: 3 3
				)
				(switch (theSlime loop?)
					(2
						(theSlime
							setMotion: MoveTo (ego x?) (- (ego y?) 3) self
						)
					)
					(else 
						(theSlime setMotion: MoveTo (ego x?) (ego y?) self)
					)
				)
				(ego view: 76 setLoop: 0 setCel: 0)
			)
			(2
				(theSlime cycleSpeed: 4)
				(ego cycleSpeed: 5 setCycle: EndLoop self)
			)
			(3
				(ego cycleSpeed: 7 setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(theSlime setCycle: Forward)
				(ego hide:)
			)
			(5
				(music number: 0 vol: 0 stop:)
				(globalSound number: 0 vol: 0 stop:)
				(self dispose:)
				(EgoDead iconSLIME deathSLIME)
			)
		)
	)
)

(instance theSlime of Sq4Actor
	(properties
		view 75
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
		xStep 2
		lookStr 3
	)
	
	(method (init)
		(if (== (globalSound vol?) 0)
			(music vol: 0 fade: 0 10 10 0)
			(globalSound vol: 127 play:)
		)
		(self
			setCycle: Forward
			cycleSpeed: 6
			moveSpeed: (- speed 2)
			script: 0
		)
		(super init:)
	)
	
	(method (dispose)
		(super dispose:)
		(= local67 0)
		(if
			(and
				(!= (sewer location?) curRoomNum)
				(== (sewer status?) 3)
			)
			(if (globalSound number?)
				(globalSound vol: 0 fade: 0 10 10 0)
			)
			(if (music number?) (music vol: 127 playBed:))
		)
		(if (!= (sewer status?) 3)
			(if (globalSound number?)
				(globalSound vol: 0 fade: 0 10 10 0)
			)
			(if (music number?) (music vol: 127 playBed:))
		)
		(self setMotion: 0 setCycle: 0)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(narrator modNum: SEWER say: 3)
			)
			(V_DO
				(switch (sewer status?)
					(4
						(HandsOff)
						(narrator modNum: SEWER say: 4)
						(ego setMotion: PolyPath (theSlime x?) (theSlime y?))
					)
					(3
						(narrator modNum: SEWER say: 5)
					)
					(else 
						(narrator modNum: SEWER say: 6)
					)
				)
			)
			(V_JAR
				(if (== ((Inventory at: iJar) cel?) 2)
					(narrator modNum: SEWER say: 8)
				else
					(Load VIEW 81)
					(Load SOUND 820)
					(switch (sewer status?)
						(4
							(curRoom setScript: scoopScript)
						)
						(3
							(switch curRoomNum
								(90
									(cond 
										(
											(and
												(& (ego onControl: origin) cBLUE)
												(== (theSlime loop?) 0)
											)
											(cond 
												((< (theSlime y?) 70) (narrator modNum: SEWER say: 9))
												((> (theSlime y?) 100) (narrator modNum: SEWER say: 10))
												(else (curRoom setScript: scoopScript))
											)
										)
										((> (sewer distance?) 10) (narrator modNum: SEWER say: 11))
										(else
											(switch (Random 0 1)
												(0
													(narrator modNum: SEWER say: 13)
												)
												(1
													(narrator modNum: SEWER say: 12)
												)
											)
										)
									)
								)
								(95
									(cond 
										(
											(and
												(& (ego onControl: origin) cBLUE)
												(== (theSlime loop?) 3)
											)
											(cond 
												((< (theSlime y?) 60) (narrator modNum: SEWER say: 9))
												((> (theSlime y?) 110) (narrator modNum: SEWER say: 10))
												(else (curRoom setScript: scoopScript))
											)
										)
										((> (sewer distance?) 10) (narrator modNum: SEWER say: 11))
										(else
											(switch (Random 0 1)
												(0
													(narrator modNum: SEWER say: 13)
												)
												(1
													(narrator modNum: SEWER say: 12)
												)
											)
										)
									)
								)
								(100
									(cond 
										(
											(and
												(& (ego onControl: origin) cBLUE)
												(== (theSlime loop?) 1)
											)
											(cond 
												((< (theSlime y?) 75) (narrator modNum: SEWER say: 9))
												((> (theSlime y?) 95) (narrator modNum: SEWER say: 10))
												(else (curRoom setScript: scoopScript))
											)
										)
										((> (sewer distance?) 10) (narrator modNum: SEWER say: 11))
										(else
											(switch (Random 0 1)
												(0
													(narrator modNum: SEWER say: 13)
												)
												(1
													(narrator modNum: SEWER say: 12)
												)
											)
										)
									)
								)
								(115
									(if
										(and
											(& (ego onControl: origin) cBLUE)
											(> (theSlime y?) 105)
											(== (theSlime loop?) 1)
										)
										(curRoom setScript: scoopScript)
									else
										(narrator modNum: SEWER say: 14)
									)
								)
								(105
									(if
										(and
											(& (ego onControl: origin) cBLUE)
											(> (theSlime y?) 105)
											(== (theSlime loop?) 0)
										)
										(curRoom setScript: scoopScript)
									else
										(narrator modNum: SEWER say: 14)
									)
								)
								(else 
									(switch (Random 1 4)
										(1
											(narrator modNum: SEWER say: 15)
										)
										(2
											(narrator modNum: SEWER say: 16)
										)
										(3
											(narrator modNum: SEWER say: 17)
										)
										(4
											(narrator modNum: SEWER say: 18)
										)
									)
								)
							)
						)
						(else 
							(narrator modNum: SEWER say: 19)
						)
					)
				)
			)
			(7
				(narrator modNum: SEWER say: 20)
			)
			(6
				(narrator modNum: SEWER say: 21)
			)
			((OneOf theVerb 8 9 10 11 12 14 15 16 17 18 19 20 21 22 23)
				(narrator modNum: SEWER say: 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setSpeed)
		(= moveSpeed (- speed 2))
	)
)

(instance scoopScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(narrator modNum: 706 say: 12 self)
			)
			(1
				(theSlime moveSpeed: 5)
				(= local0 1)
				(sewer status: 5)
				(= egoScoopX (ego x?))
				(= egoScoopY (ego y?))
				(ego
					normal: 0
					cycleSpeed: 12
					illegalBits: 0
					ignoreActors: 1
				)
				(cond 
					(
					(or (> (sewer angle?) 270) (< (sewer angle?) 20))
						(sewer scoopLoop: 3)
						(ego setLoop: -1 setHeading: 315 self)
					)
					(
					(and (> (sewer angle?) 19) (< (sewer angle?) 100))
						(sewer scoopLoop: 4)
						(ego setLoop: -1 setHeading: 45 self)
					)
					(
					(and (> (sewer angle?) 99) (< (sewer angle?) 190))
						(sewer scoopLoop: 1)
						(ego setLoop: -1 setHeading: 135 self)
					)
					(
					(and (> (sewer angle?) 189) (< (sewer angle?) 271))
						(sewer scoopLoop: 0)
						(ego setLoop: -1 setHeading: 225 self)
					)
				)
			)
			(2
				(= register (ego loop?))
				(ego
					view: 81
					cel: 0
					cycleSpeed: 18
					setLoop: (sewer scoopLoop?)
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(music vol: 127 number: 820 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(4
				(ego setMotion: MoveTo egoScoopX egoScoopY self)
			)
			(5 (= cycles 5))
			(6
				(SolvePuzzle fGetSlime 5)
				(= cycles 5)
			)
			(7
				(music number: 805 loop: -1 vol: 0 fade: 0 10 10 0)
				(= local66 0)
				(HandsOn)
				(narrator modNum: SEWER say: 22)
				((inventory at: iJar) cel: 2 cursor: 953)
				((IconBar at: ICON_ITEM) cursor: 953)
				(NormalEgo register 0)
				(theSlime ignoreActors: 0 moveSpeed: (- speed 2))
				(globalSound vol: 127 number: 819 loop: -1 playBed:)
				(= seconds 3)
			)
			(8
				(= local0 0)
				(theSlime ignoreActors: TRUE)
				(sewer status: 4)
				(self dispose:)
			)
		)
	)
)

(instance thePipes of Sq4Feature
	(properties
		y 10
		nsBottom 200
		nsRight 320
		onMeCheck $0010
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(1
				(narrator modNum: SEWER say: 23)
			)
			(4
				(narrator modNum: SEWER say: 24)
			)
			(7
				(switch (Random 0 1)
					(0
						(narrator modNum: SEWER say: 25)
					)
					(1
						(narrator modNum: SEWER say: 26)
					)
				)
			)
			(6
				(narrator modNum: SEWER say: 27)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theConduit of Sq4Feature
	(properties
		y 10
		nsBottom 200
		nsRight 320
		onMeCheck $0022
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(1
				(narrator modNum: SEWER say: 28)
			)
			(7
				(narrator modNum: SEWER say: 29)
			)
			(6
				(switch (Random 0 1)
					(0
						(narrator modNum: SEWER say: 30)
					)
					(1
						(narrator modNum: SEWER say: 31)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theVent of Sq4Feature
	(properties
		y 10
		nsBottom 200
		nsRight 320
		onMeCheck $0040
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(1 (narrator modNum: 80 say: 1))
			(7
				(narrator modNum: SEWER say: 32)
			)
			(6
				(narrator modNum: SEWER say: 33)
			)
			(4
				(narrator modNum: SEWER say: 34)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDrip1 of Sq4Prop
	(properties
		view 80
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(narrator modNum: SEWER say: 35)
			)
			(V_DO
				(narrator modNum: SEWER say: 36)
			)
			(V_TASTE
				(narrator modNum: SEWER say: 38)
			)
			(V_SMELL
				(narrator modNum: SEWER say: 39)
			)
			(
				(OneOf theVerb
					8 9 10 11 12 13 14 15
					16 17 18 19 20 21 22 23
				)
				(narrator modNum: SEWER say: 37)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDrip2 of Sq4Prop
	(properties
		view 80
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(narrator modNum: SEWER say: 35)
			)
			(V_DO
				(narrator modNum: SEWER say: 36)
			)
			(V_TASTE
				(narrator modNum: SEWER say: 38)
			)
			(V_SMELL
				(narrator modNum: SEWER say: 39)
			)
			(
				(OneOf theVerb
					8 9 10 11 12 13 14 15 16
					17 18 19 20 21 22 23
				)
				(narrator modNum: SEWER say: 37)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theSlimeScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== register 1) (< (sewer distance?) 20))
			(= register 0)
			(self init:)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(switch curRoomNum
					(75
						(= slimeX 8)
						(= slimeY 207)
					)
					(80
						(if (== (theSlime loop?) 2)
							(= slimeX 360)
							(= slimeY 91)
						else
							(= slimeX 158)
							(= slimeY 227)
						)
					)
					(85
						(= slimeX 311)
						(= slimeY 209)
					)
					(90
						(switch (theSlime loop?)
							(2
								(if (> (ego x?) (theSlime x?))
									(= slimeX 358)
									(= slimeY 89)
								else
									(= slimeX 192)
									(= slimeY 89)
								)
							)
							(else 
								(if
								(and (< (ego y?) 50) (< (ego y?) (theSlime y?)))
									(= slimeX 274)
									(= slimeY -8)
								else
									(= slimeX 30)
									(= slimeY 195)
								)
							)
						)
					)
					(95
						(switch (theSlime loop?)
							(2
								(switch prevRoomNum
									(100
										(if (< (ego x?) (theSlime x?))
											(= slimeX 218)
											(= slimeY 89)
										else
											(= slimeX 359)
											(= slimeY 89)
										)
									)
									(90
										(if (< (ego x?) (theSlime x?))
											(= slimeX -40)
											(= slimeY 89)
										else
											(= slimeX 98)
											(= slimeY 89)
										)
									)
								)
							)
							(else 
								(= slimeX 158)
								(= slimeY 220)
							)
						)
					)
					(100
						(switch (theSlime loop?)
							(2
								(if (< (ego x?) (theSlime x?))
									(= slimeX -39)
									(= slimeY 91)
								else
									(= slimeX 120)
									(= slimeY 89)
								)
							)
							(else 
								(= slimeX 312)
								(= slimeY 211)
							)
						)
					)
					(105
						(switch (theSlime loop?)
							(0
								(if (< (ego y?) (theSlime y?))
									(= slimeX 225)
									(= slimeY -7)
								else
									(= slimeX 94)
									(= slimeY 111)
								)
							)
							(2
								(switch prevRoomNum
									(110
										(if (> (ego x?) (theSlime x?))
											(= slimeX 337)
											(= slimeY 125)
										else
											(= slimeX 118)
											(= slimeY 125)
										)
									)
									(else 
										(= slimeX 337)
										(= slimeY 125)
									)
								)
							)
						)
					)
					(110
						(switch (theSlime loop?)
							(2
								(if (< (ego x?) (theSlime x?))
									(= slimeX -30)
									(= slimeY 125)
								else
									(= slimeX 337)
									(= slimeY 125)
								)
							)
							(3
								(if (< (ego y?) (theSlime y?))
									(= slimeX 158)
									(= slimeY -18)
								else
									(= slimeX 158)
									(= slimeY 109)
								)
							)
						)
					)
					(115
						(switch (theSlime loop?)
							(1
								(if (< (ego x?) (theSlime x?))
									(= slimeX 85)
									(= slimeY -10)
								else
									(= slimeX 233)
									(= slimeY 113)
								)
							)
							(2
								(if (< (ego x?) (theSlime x?))
									(= slimeX -36)
									(= slimeY 127)
								else
									(= slimeX 183)
									(= slimeY 127)
								)
							)
						)
					)
				)
				(theSlime
					setPri: 1
					setCycle: Forward
					setMotion: MoveTo slimeX slimeY self
				)
			)
			(1
				(theSlime setCycle: Reverse)
				(switch curRoomNum
					(80 (self dispose:))
					(90
						(cond 
							((< (theSlime y?) 0) (sewer location: 75))
							((== (theSlime loop?) 2) (theSlime setMotion: MoveTo 358 89 self))
							(else
								(sewer location: 105)
								(theSlime dispose:)
								(self dispose:)
							)
						)
					)
					(95
						(if (== (theSlime loop?) 2)
							(if (== prevRoomNum 100)
								(theSlime setMotion: MoveTo 352 91 self)
							else
								(= register 1)
								(theSlime setMotion: MoveTo -32 89 self)
							)
						else
							(sewer location: 110)
							(theSlime posn: 1000 1000 dispose:)
							(self dispose:)
						)
					)
					(100
						(if (== (theSlime loop?) 2)
							(theSlime setMotion: MoveTo -30 89 self)
						else
							(if (< (ego y?) 100)
								(sewer location: 0 status: 0)
							else
								(sewer location: 115)
							)
							(theSlime dispose:)
							(self dispose:)
						)
					)
					(105
						(switch (theSlime loop?)
							(0
								(theSlime setMotion: MoveTo 139 70 self)
							)
							(2
								(switch prevRoomNum
									(110
										(if (== slimeX 118)
											(self init:)
										else
											(sewer location: 110 status: 6)
											(theSlime dispose:)
											(self dispose:)
										)
									)
									(else 
										(= register 1)
										(theSlime setMotion: MoveTo 118 125 self)
									)
								)
							)
						)
					)
					(110
						(if (== (theSlime loop?) 3)
							(= register 1)
							(theSlime setMotion: MoveTo 158 -21 self)
						else
							(sewer location: 115 status: 6)
							(theSlime posn: 1000 1000 dispose:)
							(self dispose:)
						)
					)
					(115
						(switch (theSlime loop?)
							(2
								(theSlime setMotion: MoveTo -27 125 self)
							)
							(else 
								(theSlime setMotion: MoveTo 191 82 self)
							)
						)
					)
					(else 
						(theSlime dispose:)
						(self dispose:)
					)
				)
			)
			(2
				(switch curRoomNum
					(90
						(sewer location: 95)
						(theSlime dispose:)
						(self dispose:)
					)
					(100
						(sewer location: 95 status: 6)
						(theSlime dispose:)
						(self dispose:)
					)
					(105
						(= register 1)
						(theSlime setMotion: MoveTo 225 -7 self)
					)
					(110
						(if (== (theSlime loop?) 3)
							(if (or (< (ego x?) 60) (> (ego x?) 250))
								(sewer location: 0 status: 0)
							else
								(if (< (sewer distance?) 60) (sewer status: 6))
								(sewer location: 95 rmTimer: 0)
							)
							(= register 0)
							(= cycles 1)
						)
					)
					(115
						(switch (theSlime loop?)
							(2
								(if (or (> (ego x?) 206) (< (ego y?) 114))
									(sewer location: 0 status: 0)
								else
									(sewer location: 110 prevDistance: (+ (ego x?) 25))
								)
								(theSlime dispose:)
								(self dispose:)
							)
							(else 
								(= register 1)
								(theSlime setMotion: MoveTo 87 -7 self)
							)
						)
					)
					(else  (self cue:))
				)
			)
			(3
				(= register 0)
				(switch curRoomNum
					(95
						(sewer location: prevRoomNum)
					)
					(105
						(switch (theSlime loop?)
							(0
								(sewer location: 90 rmTimer: 0)
								(theSlime posn: 1000 1000)
							)
							(2 (sewer location: 110))
						)
					)
					(115
						(sewer location: 100)
						(cond 
							((< (ego y?) 70) (sewer prevDistance: 1))
							((< (ego y?) 105) (sewer prevDistance: 2))
							((> (ego x?) 190) (sewer prevDistance: 3))
							((> (Random 0 100) 50) (sewer status: 8))
							(else (sewer prevDistance: 4))
						)
					)
				)
				(theSlime dispose:)
				(self dispose:)
			)
		)
	)
)
