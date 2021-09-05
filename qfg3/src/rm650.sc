;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include game.sh)
(use Main)
(use EgoDead)
(use JumpX)
(use Feature)
(use LoadMany)
(use Timer)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
	local1
	local2 =  72
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17 =  2
	local18
	local19
	local20
	newActor
	local22
	local23
)
(procedure (localproc_0ff0 param1 param2)
	(switch arcadeDifficulty
		(1
			((Timer new:) setReal: param1 (* param2 10))
		)
		(2
			((Timer new:) setReal: param1 (* param2 7))
		)
		(3
			((Timer new:) setReal: param1 (* param2 4))
		)
	)
)

(procedure (localproc_104a)
	(DrawPic (curRoom picture?) PIXELDISSOLVE)
	(Animate (cast elements?) FALSE)
)

(class Fcycler of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		head 0
		tail 10
	)
	
	(method (init param1 param2 param3)
		(super init: param1)
		(self head: param2)
		(if (> param3 (client lastCel:))
			(self tail: (client lastCel:))
		else
			(self tail: param3)
		)
	)
	
	(method (doit &tmp fcyclerNextCel)
		(if
		(> (= fcyclerNextCel (self nextCel:)) (self tail?))
			(self cycleDone:)
		else
			(client cel: fcyclerNextCel)
		)
	)
	
	(method (cycleDone)
		(client cel: head)
	)
)

(class Sspell of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 10
		view 21
		loop 0
		cel 0
		priority 15
		underBits 0
		signal $0010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 12
		origStep 770
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		tim 0
	)
	
	(method (init param1)
		(super init: &rest)
		(self
			x: (ego x?)
			y: (- (ego y?) 40)
			setLoop: param1
			setCycle: Forward
			setMotion: MoveTo local15 local16 self
		)
		(if (== param1 4)
			(self scaleX: 88 scaleY: 88 origStep: 6672)
		)
		(globalSound
			number:
			(switch param1
				(0 943)
				(2 13)
				(else  11)
			)
			play:
			setLoop: 1
		)
	)
	
	(method (cue)
		(if tim
			(= tim 0)
			(self dispose:)
		else
			(if (== loop 4) (= loop 10) else (= loop 9))
			(= tim 1)
			(globalSound number: 930 play: setLoop: 1)
			(self setCycle: EndLoop self)
		)
	)
)

(class Fspell of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 6
		view 21
		loop 0
		cel 0
		priority 15
		underBits 0
		signal $0010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 8
		origStep 770
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		rx 0
		ry 0
		qvalue 0
	)
	
	(method (init)
		(= x (ego x?))
		(= y (- (ego y?) 30))
		(super init: &rest)
		(self
			rx: (ego x?)
			ry: (- (ego y?) 30)
			setLoop: 7
			setCycle: Forward
			setMotion: JumpX local15 local16 15 self
		)
		(globalSound number: 900 play: setLoop: 1)
	)
	
	(method (cue)
		(if qvalue
			(self dispose:)
		else
			(= qvalue 1)
			(if local22 (messager say: 5 6 35) (= local22 0))
			(self
				setPri: (- (ego priority?) 1)
				setMotion: MoveTo rx ry self
			)
			(globalSound number: 900 play: setLoop: 1)
		)
	)
)

(instance rm650 of Room
	(properties
		noun 7
		picture 650
		vanishingY -180
	)
	
	(method (init)
		((ScriptID 36 0) x: 200 y: 2 textX: -175 textY: 150)
		((ScriptID 43 0) x: 200 y: 2 textX: -175 textY: 150)
		(LoadMany 128 653 654 14)
		(if (== prevRoomNum 550)
			(ego
				view: 5
				init:
				setScale: 0
				setLoop: 7
				solvePuzzle: 314 5 2
				addHonor: 20
			)
			(Bset 165)
			(judge init:)
			(super init: &rest)
			(Bset 79)
			(switch battleResult
				(0
					(ego view: 43 cel: 0 loop: 1)
					(sHaman view: 656 loop: 0 x: 102 cel: 9 init:)
					(self setScript: toDead)
				)
				(else 
					(ego view: 5 init: setScale: 0 setLoop: 7)
					(sHaman view: 656 loop: 0 x: 102 cel: 9 init:)
					(curRoom setScript: leave)
				)
			)
		else
			(ego
				view: 5
				loop: 6
				cel: 0
				x: 110
				y: 151
				init:
				get: 44
				setScale: 0
			)
			(judge init: stopUpd:)
			(judgeplat init:)
			(mainplatform init:)
			(sHaman init:)
			(juggleLight init: setScale: 0 stopUpd: hide:)
			(fire init: stopUpd: hide:)
			(cage init: stopUpd: hide:)
			(burst init: stopUpd:)
			(spell init: stopUpd:)
			(HandsOff)
			(Bset 69)
			(super init: &rest)
			(self setScript: sayMessage)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(Palette PALCycle 217 219 5)
	)
	
	(method (dispose)
		(LoadMany FALSE 36 43 44 57)
		(UnLoad RES_VIEW 653)
		(UnLoad RES_VIEW 654)
		(UnLoad RES_VIEW 14)
		(timers
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(if
			(and (> 88 theVerb) (> theVerb 74) (== local1 8))
				(messager say: 5 6 12)
			else
				(switch theVerb
					(82
						(if (ego castSpell: 26 local10)
							(= local15 ((user curEvent?) x?))
							(= local16 ((user curEvent?) y?))
							(if (& local0 $0080)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0080))
								(ego setScript: castPersonalSpell 0 1)
								((Fspell new:) init:)
							)
						)
					)
					(78
						(if (ego castSpell: 22 local10)
							(if (& local0 $0008)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0008))
								(if (& local1 $0200)
									(= local17 3)
									(turnFacing dispose:)
									(= local8 1)
									(sHaman setScript: buffScript 0 34)
									(judge setScript: reactDazz combat)
								else
									(= local8 1)
									(sHaman setScript: reactDazz)
								)
								(ego setScript: castPersonalSpell 0 0)
								(globalSound number: 900 play: setLoop: 1)
							)
						)
					)
					(85
						(if (ego castSpell: 29 local10)
							(if (& local0 $0400)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local4 1)
								(= local0 (| local0 $0400))
								(= local8 1)
								(ego setScript: castPersonalSpell 0 0)
								(globalSound number: 944 play: setLoop: 1)
								(switch local1
									(0
										(sHaman setScript: buffScript 0 13)
									)
									(1
										(if local3
											(sHaman setScript: buffScript 0 13)
										else
											(sHaman setScript: buffScript 0 4)
										)
									)
									(4
										(sHaman setScript: buffScript 0 10)
										(ego solvePuzzle: 309 4 2)
									)
									(else 
										(sHaman setScript: buffScript 0 30583)
									)
								)
							)
						)
					)
					(80
						(if (ego castSpell: 24 local10)
							(if (& local0 $0020)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0020))
								(switch local1
									(64
										(= temp0 0)
										(judge setScript: noFire 0 0)
										(sHaman setScript: buffScript 0 23)
										(ego solvePuzzle: 307 4 2)
									)
									(8
										(= temp0 0)
										(judge setScript: noFire 0 0)
										(sHaman setScript: buffScript 0 4)
									)
									(else  (= temp0 1))
								)
								(ego setScript: castPersonalSpell 0 temp0)
								(globalSound number: 942 play: setLoop: 1)
							)
						)
					)
					(84
						(if (ego castSpell: 28 local10)
							(if (& local0 $0200)
								(curRoom setScript: disQualify 0 (++ local7))
							else
								(= local0 (| local0 $0200))
								(switch local1
									(64
										(= temp0 0)
										(judge setScript: noFire 0 1)
										(ego setPri: 11)
										(sHaman setScript: buffScript 0 36)
									)
									(1024
										(= temp0 0)
										(= local8 1)
										(sHaman setScript: buffScript 0 40)
										(ego solvePuzzle: 312 4 2)
									)
									(else  (= temp0 1))
								)
								(if local10
									(ego setScript: summonLeviSpell 0 temp0)
								else
									(ego setScript: castLeviSpell 0 temp0)
								)
								(globalSound number: 281 setLoop: 1 play:)
							)
						)
					)
					(86
						(if (ego castSpell: 30 local10)
							(if (& local0 $0800)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0800))
								(= local8 1)
								(switch local1
									(256
										(if (IsObject ((curRoom script?) timer?))
											(((curRoom script?) timer?) dispose:)
										)
										(fire setScript: waitLight 0 31)
										(juggleLight setScript: lightUp 0 1)
										(ego solvePuzzle: 308 4 2)
									)
									(else 
										(juggleLight setScript: lightUp 0 0)
										(ego setScript: castJuggleLight 0 1)
									)
								)
							)
						)
					)
					(77
						(if (ego castSpell: 21 local10)
							(if (& local0 $0004)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0004))
								(= local8 1)
								(if local13
									(self setScript: exPlode)
								else
									(ego setScript: castPersonalSpell 0 1)
									(globalSound number: 900 play: setLoop: 1)
								)
							)
						)
					)
					(87
						(cond 
							(local23 (messager say: 13 6 37))
							((ego castSpell: 31 local10)
								(if (& local0 $1000)
									(curRoom setScript: disQualify 0 2)
								else
									(= local8 1)
									(= local0 (| local0 $1000))
									(ego
										setScript: castGetSummonSpell 0 0
										solvePuzzle: 306 4 2
									)
									(globalSound number: 900 play: setLoop: 1)
									(switch local1
										(0
											(sHaman setScript: buffScript 0 17)
										)
										(1
											(if local3
												(sHaman setScript: buffScript 0 17)
											else
												(sHaman setScript: buffScript 0 4)
											)
										)
										(2
											(sHaman setScript: buffScript 0 10)
										)
										(else 
											(sHaman setScript: buffScript 0 30583)
										)
									)
								)
							)
						)
					)
					(75
						(if (ego castSpell: 19 local10)
							(if (& local0 $0001)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0001))
								(= local8 1)
								(ego setScript: castPersonalSpell 0 1)
								(globalSound number: 900 play: setLoop: 1)
							)
						)
					)
					(76
						(if (ego castSpell: 20 local10)
							(if (& local0 $0002)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0002))
								(ego setScript: castDectMagic)
								(globalSound number: 900 play: setLoop: 1)
								(switch local1
									(0
										(sHaman setScript: buffScript 0 1)
									)
									(1
										(sHaman setScript: buffScript 0 4)
									)
									(else 
										(sHaman setScript: buffScript 0 30583)
									)
								)
							)
						)
					)
					(79
						(if (ego castSpell: 23 local10)
							(if (& local0 $0010)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0010))
								(= local8 1)
								(ego setScript: castPersonalSpell 0 1)
								(globalSound number: 900 play: setLoop: 1)
							)
						)
					)
					(81
						(if (ego castSpell: 25 local10)
							(= local15 ((user curEvent?) x?))
							(= local16 ((user curEvent?) y?))
							(if (& local0 $0040)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0040))
								(if (and (& local1 $0200) (not local11))
									(ego setScript: shootIt)
									(return 1)
								else
									(ego setScript: castPersonalSpell 0 1)
									((Sspell new:) init: 2)
								)
							)
						)
					)
					(83
						(if (ego castSpell: 27 local10)
							(= local15 ((user curEvent?) x?))
							(= local16 ((user curEvent?) y?))
							(if (& local0 $0100)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $0100))
								(if (and (& local1 $0200) (not local11))
									(ego setScript: shootIt)
									(return 1)
								else
									(ego setScript: castPersonalSpell 0 1)
									((Sspell new:) init: 0)
								)
							)
						)
					)
					(88
						(if (ego castSpell: 32 local10)
							(= local15 ((user curEvent?) x?))
							(= local16 ((user curEvent?) y?))
							(if (& local0 $2000)
								(curRoom setScript: disQualify 0 local7)
							else
								(= local0 (| local0 $2000))
								(if (and (& local1 $0200) (not local11))
									(ego setScript: shootIt)
									(return 1)
								else
									(ego setScript: castPersonalSpell 0 1)
									((Sspell new:) init: 4)
								)
							)
						)
					)
					(33
						(ego drop: 23 1)
						(super doVerb: theVerb)
					)
					(20 (ego drop: 10 1))
					(16 (ego drop: 6 1))
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance exPlode of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local10
					(ego
						x: (if (!= (ego view?) 18)
							(+ (ego x?) 12)
						else
							(ego x?)
						)
						y: (if (!= (ego view?) 18) (+ (ego y?) 1) else (ego y?))
						view: 18
						setLoop: local17
						cel: 0
						setCycle: BegLoop self
					)
				else
					(ego view: 14 loop: local17 setCycle: BegLoop self)
				)
				(globalSound number: 900 setLoop: 1 play:)
			)
			(1
				(Palette PALIntensity 0 255 800)
				(globalSound number: 930 play: setLoop: 1)
				(= cycles 5)
			)
			(2
				(cast eachElementDo: #hide)
				(= seconds 2)
			)
			(3 (EgoDead 34))
		)
	)
)

(instance buffScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(cond 
					(local3
						(= local3 0)
						(if (== register 30583)
							(localproc_0ff0 combat 1)
						else
							(combat changeState: register)
						)
					)
					((== register 30583) (combat cue:))
					(else (combat changeState: register))
				)
				(self dispose:)
			)
		)
	)
)

(instance waitLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(combat changeState: register)
				(self dispose:)
			)
		)
	)
)

(instance noFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff))
			(1
				(fire setLoop: 0 setCycle: CycleTo 0 -1 self)
				(cage setLoop: 2 setCycle: CycleTo 0 -1 self)
			)
			(2 0)
			(3
				(cage hide: setPri: -1)
				(fire hide: setPri: -1)
				(= cycles 5)
			)
			(4
				(if register 0 else (self cue:))
			)
			(5 (messager say: 3 6 9 0 self))
			(6
				(if (sHaman script?) (sHaman cue:))
				(++ local5)
				(self dispose:)
			)
		)
	)
)

(instance shootIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 14 setLoop: 3 setCycle: EndLoop self)
				(cage
					view: 651
					x: (ego x?)
					y: (- (ego y?) 50)
					setLoop: 0
					origStep: 6671
					moveSpeed: 0
					cycleSpeed: 0
					setCycle: Forward
					setStep: 15 10
				)
				(globalSound number: 13 play: setLoop: 1)
			)
			(1
				(cage show: setMotion: MoveTo local15 local16 self)
			)
			(2
				(ego cel: 0)
				(cage setCycle: 0 hide:)
				(burst x: local15 y: local16 show: setCycle: EndLoop self)
			)
			(3
				(globalSound number: 930 play: setLoop: 1)
				(if
					(and
						(>= local15 (- (juggleLight x?) 10))
						(<= local15 (+ (juggleLight x?) 10))
						(>= local16 (- (juggleLight y?) 18))
						(<= local16 (+ (juggleLight y?) 18))
					)
					(juggleLight dispose:)
					(turnFacing dispose:)
					(ego solvePuzzle: 311 4 2)
					(= register 1)
				else
					(self dispose:)
				)
				(burst hide:)
				(= cycles 5)
			)
			(4
				(combat changeState: 35)
				(burst hide:)
				(self dispose:)
			)
		)
	)
)

(instance castJuggleLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(if local10
					(ego
						view: 19
						setLoop: local17
						cel: 0
						setCycle: CycleTo 2 1 self
					)
				else
					(ego view: 15 loop: local17 setCycle: CycleTo 4 1 self)
				)
			)
			(1
				(juggleLight
					view: 111
					loop: 0
					cel: 0
					x: (ego x?)
					y: (ego nsTop?)
					priority: (+ (ego priority?) 1)
					signal: 16
					show:
					setCycle: Forward
				)
				(= seconds 2)
			)
			(2
				(if local10
					(ego view: 18 setLoop: local17 cel: 0)
				else
					(ego cel: 0)
				)
				(juggleLight hide:)
				(if (juggleLight script?)
					(= cycles 70)
				else
					(self cue:)
				)
			)
			(3
				(HandsOn 5 1 3 8 7)
				(= cycles 5)
			)
			(4
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(if (judge script?) (judge cue:))
				(if register
					(cond 
						(local3 (= local3 0) (localproc_0ff0 combat 1))
						((& local1 $1000) 0)
						(else (combat cue:))
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance castPersonalSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(if local10
					(ego
						x: (if (!= (ego view?) 18) (- (ego x?) 5) else (ego x?))
						y: (if (!= (ego view?) 18) (- (ego y?) 2) else (ego y?))
						view: 18
						setLoop: local17
						cel: 0
						setCycle: BegLoop self
					)
				else
					(ego view: 14 loop: local17 setCycle: BegLoop self)
				)
			)
			(1
				(if (juggleLight script?)
					(= cycles 70)
				else
					(self cue:)
				)
			)
			(2
				(HandsOn 5 1 3 8 7)
				(= cycles 5)
			)
			(3
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(if (judge script?) (judge cue:))
				(if register
					(cond 
						(local3 (= local3 0) (localproc_0ff0 combat 1))
						((& local1 $1000) 0)
						(else (combat cue:))
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance summonLeviSpell of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (IsObject newActor) (& local1 $fdff))
			(newActor y: (ego y?))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(ego view: 19 setLoop: local17 setCycle: CycleTo 2 1 self)
			)
			(1
				((= newActor (Actor new:))
					view: 17
					loop: 4
					cel: 0
					x: (ego x?)
					y: (ego y?)
					priority: (ego priority?)
					signal: 16400
					init:
					setCycle: Forward
				)
				(= cycles 3)
			)
			(2
				(ego
					setMotion: MoveTo (ego x?) (- (ego y?) local2) self
				)
			)
			(3
				(if (judge script?) (judge cue:))
				(if (& local1 $0400)
					(self cue:)
				else
					(ego
						setMotion: MoveTo (ego x?) (+ (ego y?) local2) self
					)
				)
			)
			(4
				(ego view: 18 cel: 0 loop: local17)
				(if (IsObject newActor) (newActor dispose:))
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(if (judge script?) (judge cue:))
				(if register
					(if local3
						(= local3 0)
						(localproc_0ff0 combat 1)
					else
						(combat cue:)
					)
				)
				(ego setPri: -1)
				(self dispose:)
			)
		)
	)
)

(instance castLeviSpell of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (IsObject newActor) (& local1 $fdff))
			(newActor y: (ego y?))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(ego
					view: 17
					setLoop: local17
					cel: 0
					cycleSpeed: 10
					setStep: 5 3
					setCycle: EndLoop self
				)
			)
			(1
				((= newActor (Actor new:))
					view: 17
					loop: 4
					cel: 0
					x: (ego x?)
					y: (ego y?)
					priority: (ego priority?)
					signal: 16400
					init:
					setCycle: Forward
				)
				(= cycles 3)
			)
			(2
				(ego
					setMotion: MoveTo (ego x?) (- (ego y?) local2) self
				)
			)
			(3
				(if (judge script?) (judge cue:))
				(if (& local1 $0400)
					(cage hide:)
					(ego setMotion: MoveTo (ego x?) (+ (ego y?) 1) self)
				else
					(ego
						setMotion: MoveTo (ego x?) (+ (ego y?) local2) self
					)
				)
			)
			(4
				(if (IsObject newActor) (newActor dispose:))
				(ego setCycle: CycleTo 0 -1 self)
			)
			(5
				(if (judge script?) (judge cue:))
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(if register
					(if local3
						(= local3 0)
						(localproc_0ff0 combat 1)
					else
						(combat cue:)
					)
				)
				(ego setPri: -1 cycleSpeed: 6)
				(self dispose:)
			)
		)
	)
)

(instance castGetSummonSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(ego view: 20 x: 123 setLoop: 4 setCycle: EndLoop self)
			)
			(1
				(ego x: 116 setLoop: 6 setCycle: EndLoop self)
			)
			(2
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(= local7 (+ local7 3))
				(if register
					(if local3
						(= local3 0)
						(localproc_0ff0 combat 1)
					else
						(combat cue:)
					)
				)
				(= local10 1)
				(= local20 12)
				(self dispose:)
			)
		)
	)
)

(instance castSummonSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject ((curRoom script?) timer?))
					(((curRoom script?) timer?) dispose:)
				)
				(ego view: 19 loop: 2 setCycle: BegLoop self)
			)
			(1
				(if (and (sHaman script?) local8)
					(= local8 0)
					(sHaman cue:)
				)
				(if register
					(if local3
						(= local3 0)
						(localproc_0ff0 combat 1)
					else
						(combat cue:)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance castDectMagic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: castPersonalSpell self 0)
			)
			(1 (messager say: 5 6 6 0 self))
			(2
				(if (sHaman script?) (sHaman cue:))
				(self dispose:)
			)
		)
	)
)

(instance lightUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(juggleLight
					view: 111
					x: (ego x?)
					y: (ego nsTop?)
					show:
					setLoop: 0
					setPri: (- (ego priority?) 1)
					setCycle: Forward
				)
				(if register
					(ego show:)
					(sHaman show:)
					(judge show: stopUpd:)
					(PalVary PALVARYREVERSE 4)
					(= seconds 5)
				else
					(self cue:)
				)
			)
			(1
				(if register
					(ego setCycle: CycleTo 0 -1 self)
				else
					(self cue:)
				)
			)
			(2
				(if register (PalVary PALVARYSTART 650 1) (waitLight cue:))
				(juggleLight hide:)
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance openCage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local8 0)
				(self setScript: castPersonalSpell self 0)
			)
			(1
				(cage hide: setPri: -1)
				(if (IsObject newActor) (newActor dispose:))
				(localproc_104a)
				(globalSound number: 916 play: setLoop: 1)
				(= cycles 5)
			)
			(2
				(messager say: 3 6 14 0 self)
			)
			(3
				(ego solvePuzzle: 310 4 2)
				(combat changeState: 27)
				(self dispose:)
			)
		)
	)
)

(instance reactDazz of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== local1 0) (= local18 8))
					((& local1 $0f00) (= local18 7))
					(else (= local18 0))
				)
			)
			(1 (HandsOff) (= cycles 5))
			(2
				(Palette PALIntensity 0 255 1000)
				(Palette PALIntensity 0 255 100)
				(sHaman view: 654 loop: local18 setCycle: EndLoop self)
				(if (== local1 512) (juggleLight dispose:))
			)
			(3
				(sHaman cel: 0)
				(if (!= (ego trySkill: 22 150) -1)
					(= local3 1)
					(localproc_0ff0 combat 2)
				else
					(localproc_0ff0 combat 1)
				)
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance disQualify of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(0
						(self setScript: castPersonalSpell self 0)
					)
					(1
						(self setScript: castLeviSpell self 0)
					)
					(2
						(self setScript: castSummonSpell self 0)
					)
					(3
						(self setScript: castPersonalSpell self 0)
					)
					(4
						(self setScript: summonLeviSpell self 0)
					)
				)
			)
			(1 (messager say: 3 6 4 0 self))
			(2
				(curRoom newRoom: 600)
				(self dispose:)
			)
		)
	)
)

(instance sayMessage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1 (messager say: 1 6 1 0 self))
			(2 (messager say: 2 6 2 0 self))
			(3 (messager say: 3 6 3 0 self))
			(4
				(HandsOn 5 1 3 8 7)
				(curRoom setScript: combat)
			)
		)
	)
)

(instance castSummonStaff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sHaman
					view: 653
					cycleSpeed: 10
					setLoop: 0
					setCycle: EndLoop self
				)
				(globalSound number: 944 play: setLoop: 1)
				(= local13 1)
			)
			(1
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance castWallFlame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not local9)
					(= local9 1)
					(fire
						x: 189
						y: 58
						show:
						setLoop: 0
						moveSpeed: 0
						setCycle: Forward
					)
				)
				(sHaman setLoop: 2 cel: 0)
				(= cycles 30)
			)
			(1
				(sHaman setCycle: EndLoop)
				(globalSound number: 913 setLoop: 1 play:)
				(fire
					view: 650
					x: 69
					y: 146
					noun: 9
					setLoop: 0
					setPri: 14
					setCycle: EndLoop self
					show:
				)
				(cage
					view: 650
					x: 68
					y: 144
					noun: 9
					setLoop: 2
					setPri: (- (ego priority?) 1)
					show:
					setCycle: EndLoop self
				)
			)
			(2 0)
			(3
				(fire setLoop: 1 setCycle: Forward)
				(cage setLoop: 3 setCycle: Forward)
				(= cycles 5)
			)
			(4
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance castFlameDart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sHaman view: 653 setLoop: 1 setCycle: CycleTo 2 1 self)
				(fire
					view: 651
					setLoop: 0
					x: 182
					y: 60
					setStep: 10 3
					moveSpeed: 0
					show:
				)
			)
			(1
				(globalSound number: 13 play: setLoop: 1)
				(fire
					setCycle: Forward
					setMotion: MoveTo (+ (fire x?) 5) (fire y?)
				)
				(sHaman view: 653 setLoop: 1 setCycle: CycleTo 5 1 self)
			)
			(2
				(fire setMotion: MoveTo (ego x?) 101 self)
			)
			(3
				(fire hide:)
				(burst x: 118 y: 101 setCycle: EndLoop self show:)
			)
			(4
				(globalSound number: 11 play: setLoop: 1)
				(fire show:)
				(burst hide:)
				(if register
					(= local9 1)
					(fire setMotion: MoveTo 189 58 self)
				else
					(messager say: 13 6 36 0 self)
				)
			)
			(5
				(fire view: 340 setLoop: 0 setCycle: Forward)
				(if register (sHaman setLoop: 2 cel: 0))
				(= cycles 2)
			)
			(6
				(= local9 1)
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance castCage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sHaman view: 653 setLoop: 2 setCycle: BegLoop self)
			)
			(1
				(globalSound number: 7 play: setLoop: 1)
				(cage
					view: 651
					setLoop: 1
					show:
					x: (+ 112 local20)
					y: 78
					noun: 10
					setPri: 13
					setCycle: EndLoop self
				)
				((= newActor (Prop new:))
					view: 651
					loop: 4
					cel: 0
					x: (+ 113 local20)
					y: 48
					z: -30
					noun: 10
					signal: 16384
					init:
					setCycle: EndLoop self
				)
			)
			(2 0)
			(3
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance castSmoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sHaman view: 654 setLoop: 2 setCycle: BegLoop self)
				(if (PalVary PALVARYINFO)
					(PalVary PALVARYREVERSE 0)
				)
			)
			(1 (= seconds 1))
			(2
				(PalVary PALVARYSTART 651 2)
				(= seconds 3)
			)
			(3
				(globalSound number: 939 play: setLoop: 1)
				(= local10 0)
				(= local17 3)
				(ego view: 15 setLoop: 3 cel: 2 x: 201 y: 134)
				(sHaman view: 654 setLoop: 4 x: 102 y: 122)
				(cast eachElementDo: #hide)
				(= cycles 5)
			)
			(4
				(= local12 1)
				(= local13 0)
				(= local23 1)
				(messager say: 5 6 27 0 self)
			)
			(5
				(HandsOn 5 1 3 8 7)
				(self dispose:)
			)
		)
	)
)

(instance turnFacing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				(judge setLoop: 1 setCycle: CycleTo 2 1 self)
			)
			(2
				(messager say: 3 6 17 0 self)
				(HandsOn 5 1 3 8 7)
				(++ local5)
			)
			(3
				(HandsOff)
				(= local1 512)
				(sHaman view: 654 setLoop: 4 setCycle: EndLoop self)
			)
			(4
				(HandsOn 5 1 3 8 7)
				(juggleLight
					view: 651
					cel: 0
					x: (+ (sHaman x?) 35)
					y: (- (sHaman y?) 40)
					xStep: 20
					noun: 11
					cycleSpeed: 3
					show:
					setLoop: 2
					setCycle: Fcycler 0 6
				)
				(localproc_0ff0 self 1)
			)
			(5
				(juggleLight setCycle: CycleTo 10 1 self)
			)
			(6
				(juggleLight
					setCycle: 0
					moveSpeed: 0
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 30) self
				)
			)
			(7
				(= local11 1)
				(juggleLight
					setMotion: MoveTo (ego x?) (- (ego y?) 30) self
				)
			)
			(8
				(juggleLight cycleSpeed: 6 setCycle: Fcycler 0 6)
				(localproc_0ff0 self 2)
			)
			(9
				(combat cue:)
				(self dispose:)
			)
		)
	)
)

(instance castPit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (& local1 $0200)
					(sHaman view: 654 setLoop: 5)
				else
					(sHaman view: 653 setLoop: 2)
				)
				(sHaman setCycle: BegLoop self)
			)
			(1
				(if (< (ego x?) 140)
					(cage
						view: 651
						loop: 3
						cel: 0
						noun: 12
						x: (- (ego x?) 24)
						y: (- (ego y?) 8)
						show:
					)
				else
					(cage
						view: 651
						loop: 3
						cel: 1
						x: (- (ego x?) 24)
						y: (- (ego y?) 5)
						noun: 12
						show:
					)
				)
				(= cycles 5)
			)
			(2
				(if local10
					(self changeState: 4)
				else
					(ego view: 5 setLoop: 0)
					(= cycles 10)
				)
			)
			(3
				(ego setLoop: 4)
				(= cycles 10)
			)
			(4
				(ego setLoop: 2)
				(= cycles 10)
			)
			(5
				(cage setPri: 1)
				(ego
					setPri: 2
					moveSpeed: 0
					yStep: 6
					setCycle: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 70) self
				)
			)
			(6
				(HandsOn 5 1 3 8 7)
				(ego moveSpeed: 6)
				(self dispose:)
			)
		)
	)
)

(instance finalScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= monsterHealth 180)
				(messager say: 2 6 22 0 self)
			)
			(1
				(sHaman view: 656 cel: 0 setLoop: 0 setCycle: EndLoop self)
				(judge setLoop: 2 setCycle: Forward)
				(burst cue:)
			)
			(2 (= cycles 35))
			(3
				(messager say: 1 6 23 0 self)
			)
			(4
				(HandsOn 5 1 3)
				(switch arcadeDifficulty
					(3 (= seconds 5))
					(else  (= seconds 9))
				)
			)
			(5
				(HandsOff)
				(= monsterNum 595)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance combat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound number: 157 setLoop: -1 play:)
				(= local1 0)
				(localproc_0ff0 self 1)
			)
			(1
				(self setScript: castSummonStaff self)
			)
			(2
				(messager say: 3 6 5 0 self)
				(++ local6)
			)
			(3
				(= local1 1)
				(localproc_0ff0 self 1)
			)
			(4
				(sHaman setScript: castWallFlame self)
			)
			(5
				(= local1 64)
				(localproc_0ff0 self 1)
			)
			(6
				(= local1 16)
				(if local9 (fire dispose:))
				(= cycles 1)
			)
			(7
				(= cycles 0)
				(ego view: 43 setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(juggleLight dispose:)
			)
			(8 (messager say: 3 6 7 0 self))
			(9
				(if (ego takeDamage: 25)
					(curRoom newRoom: 600)
				else
					(EgoDead 24)
				)
			)
			(13
				(if local3
					(= local3 0)
					(= cycles 1)
				else
					(self setScript: castSummonStaff self)
				)
			)
			(14
				(if local3
					(self cue:)
				else
					(messager say: 3 6 5 0 self)
					(++ local6)
				)
			)
			(15
				(= local1 2)
				(localproc_0ff0 self 1)
			)
			(16 (self changeState: 4))
			(10
				(self setScript: castFlameDart self 1)
			)
			(11
				(= local1 32)
				(++ local5)
				(messager say: 3 6 13 0 self)
			)
			(12 (self changeState: 4))
			(17
				(self setScript: castSummonStaff self)
			)
			(18
				(messager say: 3 6 11 0 self)
			)
			(19
				(= local1 4)
				(localproc_0ff0 self 1)
			)
			(20
				(self setScript: castFlameDart self 0)
			)
			(21
				(= local1 8)
				(localproc_0ff0 self 1)
			)
			(22 (self changeState: 6))
			(23
				(sHaman setScript: castCage self)
			)
			(24
				(= local1 128)
				(= local2 5)
				(localproc_0ff0 self 1)
			)
			(25
				(messager say: 3 6 15 0 self)
			)
			(26 (curRoom newRoom: 600))
			(27
				(juggleLight hide:)
				(sHaman setScript: castSmoke self)
			)
			(28
				(= local1 256)
				(localproc_0ff0 self 1)
			)
			(29
				(HandsOff)
				(messager say: 3 6 16 0 self)
			)
			(30 (curRoom newRoom: 600))
			(31
				(fire dispose:)
				(self setScript: turnFacing)
			)
			(32
				(messager say: 3 6 18 0 self)
			)
			(33 (self changeState: 6))
			(34 0)
			(35
				(= local2 72)
				(++ local5)
				(= cycles 0)
				(messager say: 3 6 19 0 self)
			)
			(36
				(self setScript: castPit self)
			)
			(37
				(= local1 1024)
				(localproc_0ff0 self 1)
			)
			(38
				(messager say: 3 6 20 0 self)
			)
			(39 (curRoom newRoom: 600))
			(40
				(= local1 2048)
				(messager say: 3 6 21 0 self)
			)
			(41
				(= local1 4096)
				(self setScript: finalScript self)
			)
			(42
				(curRoom setScript: doneRoom)
			)
		)
	)
)

(instance judgeKill of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: castPersonalSpell)
				(globalSound number: 13 setLoop: 1 play:)
				(juggleLight
					view: (if (== register 20) 46 else 21)
					setLoop:
					(switch register
						(20 2)
						(81 2)
						(83 0)
						(88 4)
					)
					x: (ego x?)
					y: (- (ego y?) 35)
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 12 10
					setPri: 15
					show:
					setCycle: Forward
					setMotion: MoveTo (judge x?) (- (judge y?) 20)
				)
				(= cycles 20)
			)
			(1
				(HandsOff)
				(spell
					view: 651
					x: 64
					y: 66
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: 0
					setPri: 15
					setStep: 12 10
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 30) self
					show:
				)
				(globalSound number: 13 setLoop: 1 play:)
				(juggleLight
					setMotion: 0
					view: 21
					setLoop: 9
					setCycle: EndLoop juggleLight
				)
			)
			(2
				(spell hide:)
				(if (!= register 20)
					(burst
						x: (- (ego x?) 5)
						y: (- (ego y?) 20)
						show:
						setCycle: EndLoop self
					)
				else
					(burst dispose:)
					(= cycles 5)
				)
				(globalSound number: 930 setLoop: 1 play:)
			)
			(3
				(burst dispose:)
				(= local14 5)
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance judgeShow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: castPersonalSpell)
				(globalSound number: 13 setLoop: 1 play:)
				(sHaman
					view: 653
					cel: 0
					setLoop: (if local12 3 else 0)
					setCycle: EndLoop
				)
				(juggleLight
					view: (if (== register 20) 46 else 21)
					setLoop:
					(switch register
						(20 2)
						(81 2)
						(83 0)
						(88 4)
					)
					x: (ego x?)
					y: (- (ego y?) 35)
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 12 10
					setPri: 15
					show:
					setCycle: Forward
					setMotion: MoveTo (sHaman x?) (- (sHaman y?) 30)
				)
				(= cycles 20)
			)
			(1
				(HandsOff)
				(spell
					view: 651
					x: 64
					y: 66
					moveSpeed: 0
					cycleSpeed: 0
					setLoop: 0
					setPri: 14
					setStep: 12 10
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 30) self
					show:
				)
				(globalSound number: 13 setLoop: 1 play:)
				(juggleLight
					setMotion: 0
					view: 21
					setLoop: 9
					setCycle: EndLoop juggleLight
				)
			)
			(2
				(spell hide:)
				(if (!= register 20)
					(burst
						x: (- (ego x?) 5)
						y: (- (ego y?) 20)
						show:
						setCycle: EndLoop self
					)
				else
					(burst dispose:)
					(= cycles 5)
				)
				(globalSound number: 930 setLoop: 1 play:)
			)
			(3
				(burst dispose:)
				(curRoom setScript: egoDead)
			)
		)
	)
)

(instance egoDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 43 setLoop: 2 setCycle: EndLoop self)
			)
			(1
				(switch local14
					(1 (EgoDead 25))
					(5 (EgoDead 31))
					(else  (EgoDead 24))
				)
			)
		)
	)
)

(instance leave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound number: 159 setLoop: 1 play: self)
			)
			(1
				(sHaman setCycle: CycleTo 0 -1 self)
			)
			(2
				(sHaman
					view: 592
					loop: 0
					scaleX: 110
					scaleY: 110
					setCycle: EndLoop self
				)
			)
			(3
				(ego solvePuzzle: 305 10 2)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance toDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: EndLoop self))
			(1 (EgoDead 28))
		)
	)
)

(instance doneRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(judge setCycle: 0)
				(spell dispose:)
				(burst dispose:)
				(= cycles 5)
			)
			(1
				(sHaman cycleSpeed: 12 setCycle: CycleTo 0 -1 self)
			)
			(2 (= cycles 10))
			(3
				(messager say: 2 6 30 0 self)
			)
			(4
				(ego solvePuzzle: 305 10 2)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance sHaman of Actor
	(properties
		x 198
		y 110
		noun 2
		view 653
		signal $4000
	)
	
	(method (doVerb theVerb &tmp [temp0 30])
		(if
		(and (> 88 theVerb) (> theVerb 74) (== local1 8))
			(messager say: 5 6 12)
		else
			(switch theVerb
				(26
					(if (& local1 $1000)
						(ego drop: 16 1)
						(globalSound number: 932 play: setLoop: 1)
						(ego solvePuzzle: 313 8 2 addHonor: 50)
						(if (IsObject ((curRoom script?) timer?))
							(((curRoom script?) timer?) dispose:)
						)
						(finalScript dispose:)
					else
						(super doVerb: theVerb)
					)
				)
				(82
					(= local22 1)
					(curRoom doVerb: theVerb)
				)
				(20
					(if (& local1 $1000)
						(= monsterHealth (- monsterHealth 8))
						(curRoom doVerb: theVerb)
					else
						(curRoom setScript: judgeShow 0 20)
					)
				)
				(81
					(if (ego castSpell: 25 local10)
						(if (& local1 $1000)
							(= monsterHealth (- monsterHealth 10))
							(= local0 (& local0 $ffaf))
							(curRoom doVerb: theVerb)
						else
							(curRoom setScript: judgeShow 0 81)
						)
					)
				)
				(83
					(if (ego castSpell: 27 local10)
						(if (& local1 $1000)
							(= monsterHealth (- monsterHealth 8))
							(= local0 (& local0 $feff))
							(curRoom doVerb: theVerb)
						else
							(curRoom setScript: judgeShow 0 83)
						)
					)
				)
				(88
					(if (ego castSpell: 32 local10)
						(if (& local1 $1000)
							(= monsterHealth (- monsterHealth 12))
							(= local0 (& local0 $dfff))
							(curRoom doVerb: theVerb)
						else
							(curRoom setScript: judgeShow 0 88)
						)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance judge of Prop
	(properties
		x 64
		y 80
		noun 8
		view 652
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20
				(curRoom setScript: judgeKill 0 20)
			)
			(81
				(curRoom setScript: judgeKill 0 81)
			)
			(83
				(curRoom setScript: judgeKill 0 83)
			)
			(88
				(curRoom setScript: judgeKill 0 88)
			)
			(82
				(= local22 1)
				(curRoom doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance juggleLight of Actor
	(properties
		x 116
		y 88
		view 21
		loop 4
		signal $4000
		xStep 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(88 (curRoom doVerb: theVerb))
			(20 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance fire of Actor
	(properties
		x 184
		y 59
		sightAngle 90
		view 340
		signal $4000
	)
)

(instance cage of Actor
	(properties
		x 123
		y 76
		sightAngle 90
		view 651
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(75
				(ego castSpell: 19 local10)
				(cond 
					((& local0 $0001) (curRoom setScript: disQualify 0 local7))
					((& local1 $0080)
						(= local0 (| local0 $0001))
						(ego setScript: openCage)
						(globalSound number: 900 play: setLoop: 1)
					)
					(else (curRoom doVerb: theVerb))
				)
			)
			(1 (super doVerb: theVerb))
			(83
				(ego castSpell: 27 local10)
				(if (& local1 $0080)
					(ego setScript: openCage)
				else
					(curRoom doVerb: theVerb)
				)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance judgeplat of Feature
	(properties
		x 54
		y 71
		noun 4
		nsTop 59
		nsLeft 1
		nsBottom 84
		nsRight 108
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(82 (curRoom doVerb: theVerb))
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(88 (curRoom doVerb: theVerb))
			(20 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mainplatform of Feature
	(properties
		x 150
		y 75
		noun 6
		nsTop 88
		nsLeft 31
		nsBottom 170
		nsRight 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(82 (curRoom doVerb: theVerb))
			(81 (curRoom doVerb: theVerb))
			(83 (curRoom doVerb: theVerb))
			(88 (curRoom doVerb: theVerb))
			(20 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance burst of Prop
	(properties
		view 21
		loop 9
		priority 15
		signal $4010
		cycleSpeed 1
	)
	
	(method (init)
		(super init: &rest)
		(self hide:)
	)
	
	(method (cue)
		(globalSound number: 13 setLoop: 1 play:)
		(spell
			x: 64
			y: 66
			show:
			setStep: 12 10
			setMotion: MoveTo 101 92 spell
		)
		(self hide:)
	)
)

(instance spell of Actor
	(properties
		x 184
		y 59
		view 21
		loop 9
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self hide:)
	)
	
	(method (cue)
		(globalSound number: 930 setLoop: 1 play:)
		(self view: 21 loop: 9 cel: 0 show: setCycle: EndLoop burst)
	)
)
