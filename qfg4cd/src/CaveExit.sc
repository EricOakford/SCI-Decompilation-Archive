;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include sci.sh)
(use Main)
(use GloryRm)
(use EgoDead)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm720 0
)

(local
	local0
	local1
	theBloodExitF
	theExit2
	local4
	theGGGTheObj_2CycleSpeed
	local6
	local7
)
(instance rm720 of GloryRm
	(properties
		noun 10
		picture 720
	)
	
	(method (init)
		(Bclr 6)
		(= local4
			(cond 
				((not (Btst 101)) 1)
				((not (Btst 453)) 2)
				((not (Btst 455)) 3)
				((not (Btst 457)) 4)
				((not (Btst 459)) 5)
				((not (Btst 464)) 6)
				(else 0)
			)
		)
		(ego init: normalize: setScaler: Scaler 88 37 166 53)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						25
						138
						55
						174
						115
						179
						115
						489
						-300
						489
						-300
						-300
						619
						-300
						619
						489
						150
						489
						283
						171
						313
						109
						296
						109
						284
						123
						257
						120
						234
						102
						257
						90
						259
						82
						245
						73
						228
						73
						215
						80
						224
						97
						197
						103
						140
						89
						144
						79
						113
						61
						122
						54
						108
						54
						100
						60
						105
						82
						78
						82
						68
						79
						56
						79
						77
						92
						68
						98
						52
						100
						56
						110
						36
						118
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						113
						109
						186
						109
						231
						119
						231
						125
						257
						125
						257
						140
						231
						140
						231
						144
						110
						144
						94
						128
						109
						118
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 249 151 249 168 216 168 216 151
					yourself:
				)
		)
		(if (and (== prevRoomNum 810) (Btst 368))
			(theMusic number: 200 play:)
			(badder2
				init:
				approachX: 182
				approachY: 159
				approachVerbs: 4
			)
			(badder1
				init:
				approachX: 165
				approachY: 176
				approachVerbs: 4
			)
			(if gABad2Health
				(badder2
					cel: 3
					posn: 227 109
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow2
				)
			else
				(badder2 view: 827 loop: 0 cel: 5 posn: 182 159)
			)
			(if monsterHealth
				(badder1
					posn: 279 135
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow1
				)
			else
				(badder1 view: 827 loop: 0 cel: 5 posn: 165 176)
			)
		)
		(if (== heroType 2) (tSign init:))
		(if (== local4 1)
			(cond 
				((OneOf heroType 0 3)
					(theDoits add: CaveExit)
					(if (not (ego has: 19)) (swordNShield init:))
				)
				((and (== heroType 1) (not (ego has: 35))) (cloth init:))
			)
		)
		(torchEff init: setScaler: ego setCycle: RandCycle)
		(exit2 init: approachVerbs: 4)
		(exit3 init: approachVerbs: 4)
		(exit4 init: approachVerbs: 4)
		(exit5 init: approachVerbs: 4)
		(heart init: approachVerbs: 4)
		(senseExitF init: approachVerbs: 4)
		(boneExitF init: approachVerbs: 4)
		(breathExitF init: approachVerbs: 4)
		(bloodExitF init: approachVerbs: 4)
		(altar init:)
		(altarStem init:)
		(essenceExit init:)
		(CaveExit init:)
		(switch prevRoomNum
			(740
				(ego x: 41 y: 61)
				(= theExit2 exit2)
				(= theBloodExitF bloodExitF)
			)
			(750
				(ego x: 116 y: 55 loop: 2)
				(= theExit2 exit3)
				(= theBloodExitF breathExitF)
			)
			(760
				(ego x: 277 y: 52)
				(= theExit2 exit4)
				(= theBloodExitF senseExitF)
			)
			(810
				(ego x: 105 y: 160 loop: 0)
			)
			(else 
				(ego x: 340 y: 100)
				(= theExit2 exit5)
				(= theBloodExitF boneExitF)
			)
		)
		(super init: &rest)
		(if
			(or
				(and (OneOf heroType 0 3) (Btst 368))
				(OneOf heroType 1 2)
			)
			(= south 710)
		)
		(if
			(and
				(== local4 1)
				(not (Btst 368))
				(OneOf heroType 1 2)
			)
			(ego setScript: sCounter)
		)
		(RemapColors 1 253 112 175 62)
		(switch prevRoomNum
			(710
				(curRoom setScript: fromSouth)
			)
			(810
				(cond 
					((and (not monsterHealth) (not gABad2Health)) (curRoom setScript: sBackFromCombat))
					((== battleResult 1) (curRoom setScript: sBadderWins))
					(else (theGame handsOn:))
				)
			)
			(else 
				(curRoom setScript: sEnter)
			)
		)
		(if (and (== local4 1) (not (Btst 371)))
			(theGame save: 1)
		)
	)
	
	(method (dispose)
		(theDoits delete: CaveExit)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(89
				((ScriptID 31 0) init: 164 155 100)
			)
			(81 (messager say: 0 81 0))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance levitateExit of Code
	(properties)
	
	(method (doit)
		(if (> (ego z?) 95) (curRoom setScript: toEndGame))
	)
)

(instance boneCode of Code
	(properties)
	
	(method (doit)
		(if
			(ego
				inRect:
					(boneExitF nsLeft?)
					(boneExitF nsTop?)
					(boneExitF nsRight?)
					(boneExitF nsBottom?)
			)
			(= local0 310)
			(= local1 115)
			(ego setMotion: 0)
			(curRoom setScript: sExit 0 770)
		)
	)
)

(instance bloodCode of Code
	(properties)
	
	(method (doit)
		(if
			(ego
				inRect:
					(bloodExitF nsLeft?)
					(bloodExitF nsTop?)
					(bloodExitF nsRight?)
					(bloodExitF nsBottom?)
			)
			(= local0 35)
			(= local1 56)
			(ego setMotion: 0)
			(curRoom setScript: sExit 0 740)
		)
	)
)

(instance breathCode of Code
	(properties)
	
	(method (doit)
		(if
			(ego
				inRect:
					(breathExitF nsLeft?)
					(breathExitF nsTop?)
					(breathExitF nsRight?)
					(breathExitF nsBottom?)
			)
			(= local0 116)
			(= local1 55)
			(ego setMotion: 0)
			(curRoom setScript: sExit 0 750)
		)
	)
)

(instance senseCode of Code
	(properties)
	
	(method (doit)
		(if
			(ego
				inRect:
					(senseExitF nsLeft?)
					(senseExitF nsTop?)
					(senseExitF nsRight?)
					(senseExitF nsBottom?)
			)
			(= local0 277)
			(= local1 52)
			(ego setMotion: 0)
			(curRoom setScript: sExit 0 760)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego code: 0)
				(switch register
					(740
						(exit2
							setLoop: (+ (exit2 loop?) 1)
							setCycle: hideEndLoop self
						)
					)
					(750
						(exit3
							setLoop: (+ (exit3 loop?) 1)
							setCycle: hideEndLoop self
						)
					)
					(760
						(exit4
							setLoop: (+ (exit4 loop?) 1)
							setCycle: hideEndLoop self
						)
					)
					(else 
						(exit5
							setLoop: (+ (exit5 loop?) 1)
							setCycle: hideEndLoop self
						)
					)
				)
			)
			(1
				(ego setMotion: MoveTo local0 local1 self)
			)
			(2 (curRoom newRoom: register))
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theExit2
					setLoop: (+ (theExit2 loop?) 1)
					setCycle: End self
				)
			)
			(1
				(ego
					setMotion:
						MoveTo
						(theBloodExitF approachX?)
						(theBloodExitF approachY?)
						self
				)
			)
			(2
				(theExit2 setCycle: Beg self)
			)
			(3
				(if (not (Btst 371))
					(Bset 371)
					(messager say: 16 6 10 0 self)
				else
					(= cycles 1)
				)
			)
			(4
				(ego
					code:
						(switch local4
							(3 bloodCode)
							(4 breathCode)
							(5 senseCode)
							(6 levitateExit)
							(else  0)
						)
				)
				(theExit2 setLoop: (- (theExit2 loop?) 1))
				(switch local4
					(1 0)
					(3 (exit2 setCycle: Fwd))
					(4 (exit3 setCycle: Fwd))
					(5 (exit4 setCycle: Fwd))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local4 2)
					(ego code: boneCode)
					(exit5 setCycle: Fwd)
				)
				(ego x: 145 y: 240 setMotion: PolyPath 145 172 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 4 setLoop: 0 1 cel: 0 setCycle: End self)
			)
			(1
				(ego get: 0 3)
				(= kopeks (+ kopeks 17))
				(switch register
					(swordNShield
						((inventory at: 18) state: 0)
						((inventory at: 19) state: 0)
						(ego get: 19 get: 18)
						(swordNShield hide:)
					)
					(else 
						(ego get: 35)
						(cloth hide:)
					)
				)
				(ego setCycle: Beg self)
			)
			(2
				(if (== register swordNShield)
					(if local6
						(messager say: 16 6 7 0 self)
					else
						(messager say: 11 4 0 0 self)
					)
				else
					(messager say: 12 4 0 0 self)
				)
			)
			(3
				(register dispose:)
				(ego normalize: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 7 setLoop: 3 1 setPri: 185 setCycle: Walk)
				(if (ego trySkill: 11 100)
					(= register 1)
					(ego
						setScale: 0
						scaleSignal: 1
						scaleX: 99
						scaleY: 99
						cycleSpeed: 6
						moveSpeed: 6
						setMotion: MoveTo (ego x?) (- (ego y?) 50) self
					)
				else
					(= register 0)
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 5) self)
				)
			)
			(1
				(if register
					(curRoom setScript: toEndGame)
				else
					(messager say: 16 6 4 0 self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toHook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 113 148 self)
			)
			(1
				(ego
					view: 8
					setLoop: 0 1
					cel: 0
					x: (+ (ego x?) 4)
					y: (+ (ego y?) 1)
					setCycle: End self
				)
			)
			(2
				(aRope init: setCycle: End)
				(ego setLoop: 7 1 cel: 0 setCycle: End self)
			)
			(3
				(ego drop: 16)
				(ego normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBadderAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom south: 710)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(if
				(and (not (ego has: 19)) (OneOf heroType 0 3))
					(ego changeGait: 1 setMotion: PolyPath 209 176 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego changeGait: theGGGTheObj_2CycleSpeed)
				(badder1
					x: 109
					y: 187
					init:
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow1
				)
				(badder2
					x: 116
					y: 194
					cel: 3
					init:
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow2
				)
				(= seconds 4)
			)
			(2
				(messager say: 16 6 6 0 self)
			)
			(3
				(= next sCombat)
				(self dispose:)
			)
		)
	)
)

(instance sCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Btst 368))
					(Bset 368)
					(= global365 825)
					(= monsterHealth 50)
					(= gABad2Health 50)
				)
				(curRoom south: 710)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(if
				(and (not (ego has: 19)) (OneOf heroType 0 3))
					(= local6 1)
					(ego changeGait: 1 setMotion: PolyPath 242 170 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if local6
					(self setScript: pickUp self swordNShield)
				else
					(= cycles 1)
				)
			)
			(2
				(Face ego (badder2 x?) (badder2 y?) self)
			)
			(3
				(ego changeGait: theGGGTheObj_2CycleSpeed)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance toEndGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= cycles 2)
			)
			(1
				(ego solvePuzzle: 463 6)
				(curRoom newRoom: 730)
			)
		)
	)
)

(instance showSign of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 50)
				(= local7
					(theGame
						setCursor: ((theIconBar getCursor:)
							view: 999
							loop: 0
							cel: 0
							yourself:
						)
					)
				)
				(markCloseUp
					init:
					moveSpeed: 0
					setStep: 16 16
					setLoop: 0 1
					setScaler: Scaler 7 100 155 5
					setMotion: MoveTo 55 5 self
				)
			)
			(1
				(narrator y: 130)
				(messager say: 15 1 0 0 self)
			)
			(2
				(narrator y: -1)
				(mouseDownHandler addToFront: markCloseUp)
				(keyDownHandler addToFront: markCloseUp)
				(theGame handsOn:)
			)
			(3
				(theGame handsOff:)
				(mouseDownHandler delete: markCloseUp)
				(keyDownHandler delete: markCloseUp)
				(markCloseUp setMotion: MoveTo 226 155 self)
			)
			(4
				(markCloseUp dispose:)
				(theGame setCursor: local7)
				(Bclr 50)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance climbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: defaultCycles
					setScale: 0
					scaleSignal: 1
					scaleX: 99
					scaleY: 99
					view: 7
					setLoop: 0 1
					cel: 0
					setPri: 230
					setCycle: End self
				)
			)
			(1
				(ego x: 156 y: 145 setLoop: 1 1 cel: 0 setCycle: End self)
			)
			(2
				(ego x: 155 y: 131 cel: 0 setCycle: End self)
			)
			(3
				(ego x: 158 y: 85 setLoop: 5 1 cel: 0 setCycle: End self)
			)
			(4
				(ego get: 16)
				(curRoom setScript: toEndGame)
			)
		)
	)
)

(instance sFollow1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(badder1 setMotion: PolyPath (ego x?) (- (ego y?) 15))
				(= cycles 4)
			)
			(2
				(if
					(and
						(<
							(GetDistance
								(badder1 x?)
								(badder1 y?)
								(ego x?)
								(- (ego y?) 15)
							)
							35
						)
						(not (curRoom script?))
					)
					(curRoom setScript: sCombat)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance sFollow2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(badder2 setMotion: PolyPath (ego x?) (- (ego y?) 20))
				(= cycles 5)
			)
			(2
				(if
					(and
						(<
							(GetDistance
								(badder2 x?)
								(badder2 y?)
								(ego x?)
								(- (ego y?) 20)
							)
							35
						)
						(not (curRoom script?))
					)
					(curRoom setScript: sCombat)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance sRitOnHeart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego use: 56 solvePuzzle: 462 6)
				(heart setCycle: Fwd)
				(= seconds 2)
			)
			(1
				(messager say: 2 74 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBackFromCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (== heroType 0) (not (Btst 480)))
					(ego solvePuzzle: 480 2 1)
				)
				(= cycles 2)
			)
			(1
				(messager say: 16 6 9 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBadderWins of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (EgoDead 12 0 937 1 912))
		)
	)
)

(instance sCounter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 70))
			(1
				(messager say: 16 6 11 0 self)
			)
			(2
				(badder1
					x: 109
					y: 187
					init:
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow1
				)
				(badder2
					x: 116
					y: 194
					cel: 4
					init:
					setPri: 230
					setStep: 6 4
					setCycle: Fwd
					setLoop: 2 1
					setScript: sFollow2
				)
				(self dispose:)
			)
		)
	)
)

(instance markCloseUp of Actor
	(properties
		x 226
		y 155
		priority 196
		fixPriority 1
		view 723
		signal $4001
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) 1 32 4)
			((curRoom script?) cue:)
			(return (event claimed: 1))
		)
		(return 0)
	)
)

(instance badder1 of Actor
	(properties
		noun 14
		scaleX 64
		scaleY 64
		priority 152
		fixPriority 1
		view 825
		signal $6001
		scaleSignal $0001
		cycleSpeed 1
		moveSpeed 1
	)
	
	(method (doVerb theVerb)
		(if (Btst 368)
			(switch theVerb
				(1 (messager say: 14 1 8))
				(4 (messager say: 14 4 8))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance badder2 of Actor
	(properties
		noun 14
		scaleX 64
		scaleY 64
		priority 152
		fixPriority 1
		view 825
		signal $6001
		scaleSignal $0001
		cycleSpeed 1
		moveSpeed 1
	)
	
	(method (doVerb theVerb)
		(badder1 doVerb: theVerb &rest)
	)
)

(instance torchEff of Prop
	(properties
		view 775
		signal $4001
	)
	
	(method (doit)
		(super doit: &rest)
		(= x (ego x?))
		(= y (ego y?))
		(= z (+ (ego z?) 1))
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance exit2 of Prop
	(properties
		noun 7
		x 62
		y 58
		priority 75
		fixPriority 1
		view 721
		loop 2
		cel 1
		signal $4001
		cycleSpeed 30
	)
	
	(method (init)
		(super init:)
		(if (== local4 3) (self setCycle: Fwd))
	)
	
	(method (handleEvent event)
		(if (== local4 3)
			(= approachX 64)
			(= approachY 79)
		else
			(= approachX 70)
			(= approachY 83)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 3) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exit3 of Prop
	(properties
		noun 8
		x 114
		y 42
		priority 50
		fixPriority 1
		view 721
		loop 4
		signal $4001
		cycleSpeed 30
	)
	
	(method (init)
		(super init:)
		(if (== local4 4) (self setCycle: Fwd))
	)
	
	(method (handleEvent event)
		(if (== local4 4)
			(= approachX 114)
			(= approachY 54)
		else
			(= approachX 113)
			(= approachY 67)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 4) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exit4 of Prop
	(properties
		noun 6
		x 245
		y 58
		priority 60
		fixPriority 1
		view 721
		loop 6
		cel 1
		signal $4001
		cycleSpeed 30
	)
	
	(method (init)
		(super init:)
		(if (== local4 5) (self setCycle: Fwd))
	)
	
	(method (handleEvent event)
		(if (== local4 5)
			(= approachX 241)
			(= approachY 73)
		else
			(= approachX 240)
			(= approachY 82)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 5) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exit5 of Prop
	(properties
		noun 5
		x 303
		y 94
		view 721
		loop 8
		signal $4001
	)
	
	(method (init)
		(super init:)
		(if (== local4 2) (self setCycle: Fwd))
	)
	
	(method (handleEvent event)
		(if (== local4 2)
			(= approachX 306)
			(= approachY 111)
		else
			(= approachX 295)
			(= approachY 126)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 2) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance heart of Prop
	(properties
		noun 2
		approachX 153
		approachY 144
		x 173
		y 61
		priority 152
		fixPriority 1
		view 720
		cel 2
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 1 33 74 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if (and (== local4 6) (Btst 462))
					(curRoom setScript: toHook)
				else
					(super doVerb: theVerb)
				)
			)
			(74
				(if (== local4 6)
					(curRoom setScript: sRitOnHeart)
				else
					(messager say: 2 74 5)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance aRope of Prop
	(properties
		approachX 157
		approachY 145
		x 145
		y 106
		priority 174
		fixPriority 1
		view 8
		loop 6
		cel 10
		signal $4001
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: climbRope)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance swordNShield of View
	(properties
		noun 11
		approachX 242
		approachY 170
		x 281
		y 153
		view 724
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: pickUp 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cloth of View
	(properties
		noun 12
		approachX 242
		approachY 170
		x 260
		y 164
		view 724
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: pickUp 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tSign of View
	(properties
		noun 15
		approachX 250
		approachY 174
		x 232
		y 162
		view 723
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= nsLeft (- nsLeft 5))
		(= nsTop (- nsTop 3))
		(= nsRight (+ nsRight 5))
		(= nsBottom (+ nsBottom 3))
		(self approachVerbs: 4 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: showSign)
			)
			(4
				(curRoom setScript: showSign)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bloodExitF of Feature
	(properties
		noun 7
		nsLeft 40
		nsTop 34
		nsRight 84
		nsBottom 80
		approachX 70
		approachY 83
		x 62
		y 57
	)
	
	(method (handleEvent event)
		(if (== local4 3)
			(= approachX 64)
			(= approachY 79)
		else
			(= approachX 70)
			(= approachY 83)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 3) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance breathExitF of Feature
	(properties
		noun 8
		nsLeft 88
		nsTop 22
		nsRight 136
		nsBottom 64
		approachX 113
		approachY 67
		x 112
		y 43
	)
	
	(method (handleEvent event)
		(if (== local4 4)
			(= approachX 114)
			(= approachY 54)
		else
			(= approachX 113)
			(= approachY 67)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 4) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance senseExitF of Feature
	(properties
		noun 6
		nsLeft 215
		nsTop 29
		nsRight 266
		nsBottom 79
		approachX 240
		approachY 82
		x 240
		y 54
	)
	
	(method (handleEvent event)
		(if (== local4 5)
			(= approachX 241)
			(= approachY 73)
		else
			(= approachX 240)
			(= approachY 82)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 5) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance boneExitF of Feature
	(properties
		noun 5
		nsLeft 284
		nsTop 71
		nsRight 319
		nsBottom 123
		approachX 295
		approachY 126
		x 301
		y 97
	)
	
	(method (handleEvent event)
		(if (== local4 2)
			(= approachX 306)
			(= approachY 111)
		else
			(= approachX 295)
			(= approachY 126)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local4 2) 0 else (super doVerb: theVerb &rest))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance altar of Feature
	(properties
		noun 2
		nsLeft 145
		nsTop 46
		nsRight 193
		nsBottom 85
		approachX 153
		approachY 144
		x 169
		y 65
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 1 33 74 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and (cast contains: aRope) (== local4 6)) (aRope doVerb: theVerb))
					((and (== local4 6) (Btst 462)) (curRoom setScript: toClimb))
					(else (super doVerb: theVerb))
				)
			)
			(33
				(if (and (== local4 6) (Btst 462))
					(curRoom setScript: toHook)
				else
					(super doVerb: theVerb)
				)
			)
			(74
				(if (== local4 6)
					(curRoom setScript: sRitOnHeart)
				else
					(messager say: 2 74 5)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance altarStem of Feature
	(properties
		noun 3
		nsLeft 134
		nsTop 85
		nsRight 187
		nsBottom 135
		approachX 153
		approachY 144
		x 160
		y 110
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and (cast contains: aRope) (== local4 6)) (aRope doVerb: theVerb))
					((== local4 720) (curRoom setScript: toClimb))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance essenceExit of Feature
	(properties
		noun 4
		nsLeft 137
		nsTop 3
		nsRight 196
		nsBottom 35
		approachX 92
		approachY 148
		x 166
		y 19
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 33)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 462)
					(messager say: 4 1 3)
				else
					(messager say: 4 1 2)
				)
			)
			(33
				(if (Btst 462)
					(curRoom setScript: toHook)
				else
					(messager say: 4 33 2)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(class CaveExit of Feature
	(properties
		scratch 0
		heading 0
		noun 9
		case 0
		modNum -1
		nsLeft 90
		nsTop 173
		nsRight 190
		nsBottom 189
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 140
		y 182
		z 0
		alreadyGone 0
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not (curRoom script?))
					(not (Btst 368))
					(self onMe: ego)
					(not (cast contains: badder1))
				)
				(curRoom setScript: sBadderAttack)
			)
			(
				(and
					(self onMe: ego)
					(not (curRoom script?))
					(not alreadyGone)
				)
				(= alreadyGone 1)
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 50) self)
			)
		)
	)
	
	(method (cue)
		(curRoom newRoom: 710)
	)
)

(instance hideEndLoop of End
	(properties)
	
	(method (cycleDone)
		(client hide:)
		(FrameOut)
		(super cycleDone:)
	)
)
