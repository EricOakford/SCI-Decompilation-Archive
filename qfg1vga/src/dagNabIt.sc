;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh) (include "340.shm")
(use Main)
(use Intrface)
(use Procs)
(use Print)
(use Feature)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	talkCount =  60
	lookCount =  52
	local2
	local3
	local4 =  61
	local5 =  61
	local6
	local7
	local8
	local9
	handX
	handY
	knifeX
	knifeY
	local14
	local15
	egoScore
	chiefScore
	local18
	local19
	local20 =  1
	silverBet =  5
	local22
	forceCel =  5
	angleCel =  7
	[knife 12]
	i
	oldSpeed
	[str 50]
)
(procedure (localproc_0080)
	(curRoom drawPic: 340)
	(forcePanel init: addToPic:)
	(anglePanel init: addToPic:)
	(= local20 1)
	(scoreBoard
		init:
		signal: ignrAct
		setLoop: 1
		setCel: 0
		forceUpd:
		startUpd:
		addToPic:
	)
	(= local4 61)
	(= local5 61)
	(= egoScore 0)
	(forceGauge setCel: 5)
	(angleGauge setCel: 7)
	(localproc_00f6 egoScore talkCount)
)

(procedure (localproc_00f6 param1 theColor)
	(Display 340 0
		p_at 277 168
		p_mode teJustLeft
		p_font 2107
		p_color 0
		p_back 0
	)
	(if (< param1 10)
		(Display
			(Format @str 340 1 param1)
			p_at 277 168
			p_mode teJustLeft
			p_font 2107
			p_color theColor
			p_back 0
		)
	else
		(Display
			(Format @str 340 2 param1)
			p_at 277 168
			p_mode teJustLeft
			p_font 2107
			p_color theColor
			p_back 0
		)
	)
)

(procedure (localproc_0177)
	(= handX (Random 100 229))
	(= handY (Random 189 203))
)

(procedure (DisposeKnife)
	(while (>= (-- i) 0)
		([knife i] dispose:)
	)
	(= i 0)
)

(procedure (localproc_01a7 &tmp temp0 temp1)
	(= knifeX (+ handX (* 12 (- (angleGauge cel?) 7))))
	(= knifeY (+ handY (* -8 (forceGauge cel?))))
	(= temp0 (/ (- 100 [egoStats LUCK]) 4))
	(TrySkill LUCK 0 0)
	(if (and (TrySkill THROW 0 0) (> temp0 6)) (= temp0 6))
	(= knifeX
		(+ knifeX (- (= temp1 (Random 0 temp0)) (/ temp0 2)))
	)
	(if temp0
		(= knifeY
			(+ knifeY (- (Random 0 temp0) (/ temp0 2)))
		)
	)
	(= knifeY
		(+
			(= knifeY (- 135 (* (forceGauge cel?) 10)))
			(- 203 handY)
		)
	)
)

(procedure (localproc_0247 &tmp temp0 temp1)
	(if
		(<
			(= temp0 (- local22 (+ dartsBonus (* local18 3))))
			6
		)
		(= temp0 6)
	)
	(if (>= totalDagNabItBet 100) (= temp0 (Random 2 5)))
	(= knifeX
		(+ 159 (- (= temp1 (Random 0 temp0)) (/ temp0 2)))
	)
	(= temp0 (- temp0 temp1))
	(if
		(<
			(= knifeY (+ 76 (- (Random 0 temp0) (/ temp0 2))))
			handX
		)
		(angleGauge cel: (Random 0 6))
	else
		(angleGauge cel: (Random 8 14))
	)
	(forceGauge cel: (Random 0 11))
	(= knifeY
		(-
			(= knifeY (- 135 (* (forceGauge cel?) 10)))
			(- 203 handY)
		)
	)
)

(instance dagTimer of Timer)

(instance rm340 of Room
	(properties
		noun N_ROOM
		picture 340
		style IRISOUT
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany VIEW 340 339 341)
		(LoadMany SOUND (SoundFX 31) (SoundFX 29) (SoundFX 30))
		(SolvePuzzle f340PlayDagNabIt 3 THIEF)
		(scoreBoard init: stopUpd: addToPic:)
		(forcePanel init: addToPic:)
		(anglePanel init: addToPic:)
		(turnMarker init:)
		(dagMusic number: (SoundFX 31) init:)
		(theIconBar disable: ICON_WALK ICON_ACTIONS)
		(self setScript: dagnabitScript)
		(theBoard init:)
		(angleF init:)
		(forceF init:)
		(bead init: hide:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(theIconBar enable: ICON_WALK ICON_ACTIONS ICON_CONTROL)
		(super dispose:)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(dagTimer dispose: delete:)
	)
)

(instance dagnabitScript of Script
	(properties)
	
	(method (init)
		(forceGauge init: cel: forceCel)
		(angleGauge init: cel: angleCel)
		(localproc_0177)
		(hand init:)
		(thrownKnife init:)
		(super init: &rest)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(if local20
			(localproc_00f6 egoScore talkCount)
		else
			(localproc_00f6 chiefScore lookCount)
		)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 30))
			(1
				(= silverBet
					(GetNumber {"How much will you bet?"} silverBet)
				)
				(= ticks 180)
			)
			(2
				(cond 
					((<= silverBet 0)
						(client setScript: backToGuild)
						(return)
					)
					((> silverBet 51)
						(messager say: N_CHIEF NULL C_TOOHIGH)
						(self changeState: 0)
					)
					((not (GiveMoney silverBet))
						(messager say: N_CHIEF NULL C_NOTENOUGHMONEY)
						(self changeState: 0)
					)
					(else
						(self cue:)
					)
				)
			)
			(3
				(if (>= totalDagNabItBet 100)
					(messager say: N_CHIEF NULL C_HOTSTUFF)
				)
				(++ dartsBonus)
				(= local18 (= local14 (= egoScore (= chiefScore 0))))
				(= local22 (- (Random 60 90) silverBet))
				(= i 0)
				(= seconds 2)
			)
			(4
				(= local20 1)
				(HandsOff)
				(theIconBar disable: ICON_CONTROL)
				(= local15 0)
				(localproc_0177)
				(if (!= (turnMarker cel?) 0)
					(turnMarker setCycle: BegLoop self)
				else
					(= cycles 2)
				)
			)
			(5
				(DisposeKnife)
				(self setScript: throwScript)
				(self cue:)
			)
			(6
				(hand cel: 0 posn: handX handY forceUpd:)
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS ICON_CONTROL)
				(= local19 1)
			)
			(7
				(= local19 0)
				(HandsOff)
				(localproc_01a7)
				(script changeState: 1)
			)
			(8
				(if (< (++ local15) 3)
					(localproc_0177)
					(self changeState: 6)
				else
					(hand posn: 900 0)
					(self cue:)
				)
			)
			(9
				(= local20 0)
				(= local15 0)
				(localproc_0177)
				(turnMarker setCycle: EndLoop self)
			)
			(10
				(self setScript: throwScript)
				(self cue:)
			)
			(11
				(hand cel: 0 posn: handX handY forceUpd:)
				(= cycles 20)
			)
			(12
				(localproc_0247)
				(++ local18)
				(script changeState: 1)
			)
			(13
				(if (< (++ local15) 3)
					(self changeState: 11)
				else
					(hand posn: 900 0)
					(++ local14)
					(= cycles 5)
				)
			)
			(14
				(if (< local14 3)
					(self changeState: 4)
				else
					(theIconBar enable: 9)
					(self cue:)
				)
			)
			(15
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS)
				(cond 
					((> egoScore chiefScore)
						(messager say: N_CHIEF NULL C_EGOWINS 0 self)
						(if (>= silverBet 25)
							(SolvePuzzle f340WinBigBet 5 THIEF)
						)
						(= totalDagNabItBet (+ totalDagNabItBet silverBet))
					)
					((< egoScore chiefScore)
						(messager say: N_CHIEF NULL C_CHIEFWINS 0 self)
						(= totalDagNabItBet (- totalDagNabItBet silverBet))
					)
					((<= silverBet 25)
						(messager say: N_CHIEF NULL C_TIEDOUBLE 0 self)
						(= silverBet (+ silverBet silverBet))
					)
					(else
						(messager say: N_CHIEF NULL C_TIED 0 self)
					)
				)
			)
			(16
				(cond 
					((> egoScore chiefScore)
						(ego get: iSilver silverBet)
						(ego get: iSilver silverBet)
						(self cue:)
					)
					((< egoScore chiefScore)
						(self cue:)
					)
					(else
						(ego get: iSilver silverBet)
					)
				)
			)
			(17
				(DisposeKnife)
				(if
					(Print
						addText: N_ROOM NULL C_PLAYAGAIN 1 0 0 340
						addButton: 1 N_ROOM NULL C_SAYYES 1 0 30 340
						addButton: 0 N_ROOM NULL C_SAYNO 1 0 60 340
						init:
					)
					(localproc_0080)
					(= cycles 2)
				else
					(curRoom setScript: leavinBoard)
				)
			)
			(18
				(self changeState: 1)
			)
		)
	)
)

(instance leavinBoard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
			)
			(1
				(curRoom drawPic: 0 IRISIN newRoom: 332)
			)
		)
	)
)

(instance throwScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(hand view: (if local20 340 else 339))
				(thrownKnife view: (if local20 340 else 339))
			)
			(1
				(= local9 0)
				(= local8 0)
				(= oldSpeed speed)
				(theGame setSpeed: 0)
				(hand
					view: (if local20 340 else 339)
					cycleSpeed: 2
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(hand cycleSpeed: 0 setCycle: CycleTo 9 1 self)
				(dagMusic number: (SoundFX 31) play:)
			)
			(3
				(hand setCel: 10 stopUpd:)
				(thrownKnife
					view: (if local20 340 else 339)
					posn: (+ handX 1) (- handY 90)
					setPri: 4
					setLoop: 0
					setCel: 1
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors:
					illegalBits: 0
					setCycle: EndLoop self
					setMotion: MoveTo knifeX knifeY
				)
			)
			(4
				(thrownKnife
					setPri: 3
					setLoop: 1
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo knifeX knifeY self
				)
			)
			(5
				(thrownKnife posn: 1000 1000 stopUpd:)
				(hand setCel: 0)
				(dagMusic stop:)
				(dagMusic
					number: (if local20 (SoundFX 29) else (SoundFX 30))
					play:
				)
				(cond 
					((> knifeX (+ handX 10)) (= temp0 0))
					((< knifeX (- handX 10)) (= temp0 2))
					(else (= temp0 1))
				)
				((= [knife i] (View new:))
					view: (if local20 340 else 339)
					loop: 3
					cel: temp0
					posn: knifeX knifeY
					ignoreActors:
					setPri: 1
					init:
					stopUpd:
					onMeCheck: -32768
				)
				(++ i)
				(= local3
					(switch (OnControl CMAP knifeX knifeY)
						(2 1)
						(4 2)
						(8 3)
						(16 4)
						(else  0)
					)
				)
				(if local20
					(= egoScore (+ egoScore local3))
				else
					(= chiefScore (+ chiefScore local3))
				)
				(theGame setSpeed: oldSpeed)
				(cond 
					((and local20 local3) (= cycles 2))
					((and (not local20) local3) (++ state) (= cycles 2))
					(else (client cue:))
				)
			)
			(6
				(-- state)
				(if local3
					(localproc_00f6 egoScore talkCount)
					(= local6 local4)
					(= local7 181)
					(bead show: setLoop: 6 setCel: 1 cue:)
				else
					(client cue:)
				)
			)
			(7
				(-- state)
				(if local3
					(localproc_00f6 chiefScore lookCount)
					(= local6 local5)
					(= local7 170)
					(bead show: setLoop: 6 setCel: 0 cue:)
				else
					(client cue:)
				)
			)
		)
	)
)

(instance dagMusic of Sound
	(properties
		number 31
	)
)

(instance theBoard of Feature
	(properties
		x 159
		y 75
		noun N_BOARD
		nsTop 3
		nsLeft 70
		nsBottom 148
		nsRight 249
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if local19
					(dagnabitScript cue:)
				)
			)
			(V_LOOK
				(messager say: N_BOARD V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance forceF of Feature
	(properties
		x 34
		y 139
		nsTop 128
		nsBottom 157
		nsRight 63
		sightAngle 40
		onMeCheck ISNOTHIDDEN
	)
	
	(method (doVerb theVerb)
		(forceGauge doVerb: theVerb)
	)
)

(instance angleF of Feature
	(properties
		x 284
		y 137
		nsTop 124
		nsLeft 262
		nsBottom 151
		nsRight 307
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(angleGauge doVerb: theVerb)
	)
)

(instance forceGauge of View
	(properties
		x 12
		y 132
		nsTop 130
		nsLeft 7
		nsBottom 152
		nsRight 62
		view 341
		loop 3
		priority 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doit &tmp temp0 [temp1 2])
		(super doit:)
		(if (and local8 (> mouseY 135) (< mouseY 158))
			(= temp0 (+ (- mouseX (mod mouseX 4)) 4))
			(self setCel: (/ (- temp0 8) 4) stopUpd:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= local8 (not local8))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance anglePanel of View
	(properties
		x 259
		y 18
		z -100
		noun N_ANGLEPANEL
		nsTop 111
		nsLeft 255
		nsBottom 153
		nsRight 312
		view 341
		loop 2
		cel 1
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb &tmp [temp0 30])
		(switch theVerb
			(V_DO
				(messager say: N_ANGLEPANEL V_DO)
			)
			(V_LOOK
				(++ lookCount)
				(messager say: N_ANGLEPANEL V_LOOK)
			)
			(V_TALK
				(++ talkCount)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance angleGauge of View
	(properties
		x 284
		y 128
		nsTop 124
		nsLeft 263
		nsBottom 150
		nsRight 308
		view 341
		loop 4
		priority 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
			(and local9 (> mouseX 254) (> mouseY 130) (< mouseY 158))
			(= temp0
				(+ (- (= mouseX (- mouseX 255)) (mod mouseX 3)) 3)
			)
			(self setCel: (/ (- temp0 8) 3) stopUpd:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(++ lookCount)
				(messager say: N_ANGLEPANEL V_LOOK)
			)
			(V_DO
				(= local9 (not local9))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance scoreBoard of View
	(properties
		x 257
		y 157
		view 341
		loop 1
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance forcePanel of View
	(properties
		x 10
		y 18
		z -100
		noun N_FORCEPANEL
		nsTop 110
		nsLeft 6
		nsBottom 152
		nsRight 63
		view 341
		loop 2
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_FORCEPANEL V_DO)
			)
			(V_LOOK
				(messager say: N_FORCEPANEL V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance turnMarker of Prop
	(properties
		x 23
		y 169
		view 341
		loop 5
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		cycleSpeed 10
	)
)

(instance thrownKnife of Actor
	(properties
		x 900
		y 100
		view 340
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance hand of Prop
	(properties
		x 1000
		y 1000
		view 340
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
	)
	
	(method (init)
		(= nightPalette 1340)
		(PalVary PALVARYTARGET 1340)
		(kernel_128 340)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM NULL C_YOURHAND)
			)
			(V_DO
				(theBoard doVerb: V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bead of Actor
	(properties
		x 1000
		y 170
		yStep 4
		view 341
		loop 6
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 4
		moveSpeed 0
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local2)
			(1
				(self setPri: (- (hand priority?) 1) posn: 259 local7)
				(hand posn: 229 203)
				(dagTimer setCycle: self 3)
			)
			(2
				(self setMotion: MoveTo local6 local7 self)
			)
			(3
				(if local20
					(= local4 (+ local4 4))
				else
					(= local5 (+ local5 4))
				)
				(-- local3)
				(self setPri: (hand priority?))
				(dagTimer setCycle: self 2)
			)
			(4
				(DrawCel
					(self view?)
					(self loop?)
					(self cel?)
					(self nsLeft?)
					(self nsTop?)
					(self priority?)
				)
				(dagTimer setCycle: self 2)
			)
			(5
				(hand stopUpd:)
				(= local2 0)
				(self hide:)
				(throwScript cue:)
			)
		)
	)
)

(instance backToGuild of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_NOBETNOPLAY 1 self)
			)
			(1
				(curRoom newRoom: 332)
			)
		)
	)
)
