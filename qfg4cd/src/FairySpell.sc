;;; Sierra Script 1.0 - (do not remove this comment)
(script# 580)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use EgoDead)
(use ForestView)
(use GloryTalker)
(use Intrface)
(use String)
(use Array)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm580 0
	queenTalker 1
)

(local
	local0
	gTheObj_2MoveSpeed
	local2 =  300
	local3 =  100
	local4 =  100
	local5
	local6
	local7
	newStr
	newStr_2
	local10
	local11
)
(procedure (localproc_01a6)
	(= isHandsOff 0)
	(User canInput: 1 canControl: 0)
	(theIconBar disable: 0 4)
	(theIconBar enable: 1 2 3 5 6 7 8)
	(theIconBar curIcon: oldCurs)
	(theGame setCursor: (theIconBar getCursor:))
	(= oldCurs 0)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: 6)
	)
)

(instance rm580 of GloryRm
	(properties
		picture 410
		horizon 70
		north 440
		east 587
		south 581
		west 573
		topX 181
		rightY 135
	)
	
	(method (init)
		(= gTheObj_2MoveSpeed (ego moveSpeed?))
		(if (and (== heroType 1) Night (not global365))
			(switch arcadeLevel
				(1
					(= local2 200)
					(= local3 75)
					(= local4 75)
				)
				(2
					(= local2 300)
					(= local3 100)
					(= local4 100)
				)
				(3
					(= local2 500)
					(= local3 200)
					(= local4 200)
				)
			)
			(cond 
				(debugging (= local0 (GetNumber {Fairy Event? (1 - 3)})) (Bset 35))
				((not (Btst 269)) (= local0 1) (Bset 35))
				(
					(and
						(Btst 269)
						[egoStats 34]
						[egoStats 40]
						[egoStats 38]
						[egoStats 36]
						[egoStats 37]
						(not (Btst 364))
					)
					(= local0 2)
					(Bset 35)
				)
				(
					(and
						(not (Btst 365))
						(Btst 364)
						(Btst 450)
						(!= prevRoomNum 440)
						(!= prevRoomNum 587)
						[egoStats 34]
						[egoStats 35]
						[egoStats 40]
						[egoStats 38]
						[egoStats 36]
						[egoStats 37]
					)
					(Bset 365)
					(= local0 3)
					(Bset 35)
				)
			)
			(if local0
				(RemapColors 2 253 140)
				(theMusic number: 580 setLoop: -1 play:)
			)
			(switch local0
				(1
					(fountain init: approachVerbs: 4)
					(fountainTeller init: fountain 580 15 4 4)
					((ScriptID 50 1) caller: self)
				)
				(2
					(aStaff
						init:
						cel: 11
						signal: (| (aStaff signal?) $0001)
						approachVerbs: 4
					)
				)
				(3
					((ScriptID 50 1) caller: self)
				)
			)
		)
		(self setRegions: 50)
		(super init: &rest)
		(if (== local0 1)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 189 150 189 167 177 206 166 206 145 319 145
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 122 255 122 201 93 238 72 238 0 319 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 109 0 109 72 159 80 159 94 99 109 115 131 0 131 0 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 147 107 209 107 209 117 147 117
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 319 189 150 189 167 177 206 166 206 145 319 145
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 122 255 122 201 93 238 72 238 0 319 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 109 0 109 72 159 80 159 94 99 109 115 131 0 131 0 0
						yourself:
					)
			)
			(if (== local0 2) (aStaff setScaler: ego))
		)
		(atp1 init:)
		(atp2 init:)
		(atp4 init: setPri: 127)
		(atp5 init: setPri: 148)
		(if Night
			(atp3Night init: setPri: 249)
		else
			(atp3 init:)
		)
		(if (or (Btst 380) (== local0 3)) (theGame save: 1))
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((== theVerb 1) (messager say: 0 1 0))
				(local0
					(cond 
						(
						(and (== local0 3) (OneOf theVerb 81 85 91 87 83)) (messager say: 14 6 13))
						((== theVerb 81) (messager say: 0 81 0))
						((OneOf theVerb 4 80 82 94) ((ScriptID 50) doVerb: theVerb))
						((OneOf theVerb 21 86 88 93 79)
							(if (== local0 3)
								(curRoom setScript: (ScriptID 32) self theVerb)
							else
								((ScriptID 50) doVerb: theVerb)
							)
						)
						((== theVerb 37)
							(if (== local0 3)
								(curRoom setScript: (ScriptID 32) self 37)
							else
								((ScriptID 50) doVerb: theVerb)
							)
						)
						((== theVerb 83) (curRoom setScript: (ScriptID 12) 0 83))
						((== theVerb 87) (curRoom setScript: (ScriptID 37)))
						((== theVerb 89)
							(if (== local0 3)
								(messager say: 14 6 31)
							else
								((ScriptID 50) doVerb: theVerb)
								(return 1)
							)
						)
						(else ((ScriptID 50) doVerb: theVerb) (return 1))
					)
				)
				(else ((ScriptID 50) doVerb: theVerb) (return 1))
			)
		)
	)
	
	(method (cue)
		(if local6
			(if (== local0 3)
				(localproc_01a6)
			else
				(theGame handsOn:)
			)
		else
			(= local6 1)
			(switch local0
				(1
					(if (not local10)
						(= local10 1)
						(fountain setScript: sStartFountain)
					)
				)
				(3
					(if (not local11)
						(= local11 1)
						(curRoom setScript: sEvent3)
					)
				)
				(2
					(if (cast contains: aStaff) (theGame handsOn:))
				)
				(else  (theGame handsOn:))
			)
		)
	)
	
	(method (newRoom n)
		(Bclr 9)
		(if local0 (Bclr 35))
		(super newRoom: n)
	)
)

(instance aSound of Sound
	(properties)
)

(instance qReverseFX of Sound
	(properties)
)

(instance atp1 of ForestView
	(properties
		x 251
		y 7
		view 414
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance atp2 of ForestView
	(properties
		x 155
		y 91
		view 415
		cel 5
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance atp3 of ForestView
	(properties
		x 175
		y 167
		view 415
		cel 3
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance atp4 of ForestView
	(properties
		x 71
		y 6
		view 414
		cel 5
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance atp3Night of ForestView
	(properties
		x 232
		y 189
		view 418
		cel 2
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance atp5 of ForestView
	(properties
		x 210
		y 119
		view 414
		cel 6
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local0 3) (OneOf theVerb 37 21 86 88 93 79))
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fountain of Prop
	(properties
		noun 4
		approachX 167
		approachY 111
		x 179
		y 115
		view 580
		signal $4000
	)
	
	(method (dispose)
		(fountainFX stop:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (fountainTeller doVerb: theVerb))
			((Message msgSIZE 580 4 theVerb 0 1) (super doVerb: theVerb))
			(else ((ScriptID 50) doVerb: theVerb))
		)
	)
)

(instance water of Prop
	(properties
		approachX 167
		approachY 111
		x 189
		y 116
		z 31
		view 580
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(fountain doVerb: theVerb)
	)
)

(instance aStaff of TargActor
	(properties
		noun 5
		approachX 115
		approachY 170
		x 135
		y 170
		z 45
		view 585
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sTouchStaff)
			)
			(87
				(curRoom setScript: sMoveToFetch)
			)
			(-87
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setStep: 6 6
					setCel: 11
					setMotion: MoveTo (ego x?) (ego y?)
				)
				((curRoom script?) caller: self)
			)
			(82
				(AutoTarget
					(aStaff x?)
					(- (- (aStaff y?) 25) (aStaff z?))
				)
				(self setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sTriggerStaff)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(curRoom setScript: sEvent2)
	)
	
	(method (getHurt)
		(self setScript: sStaffHurt)
	)
)

(class FairySpell of Actor
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 21
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4001
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		cuedOnce 0
		whichType 0
		curDamage 0
	)
	
	(method (init param1 param2 param3)
		(self
			cuedOnce: 0
			whichType: param1
			curDamage: param2
			x: (param3 x?)
			y: (- (param3 y?) 35)
			setStep: 8 5
			setLoop:
			(switch param1
				(88 0)
				(86 2)
				(93 4)
				(79 13)
			) 1
			moveSpeed: 0
			setCycle: Fwd
			setMotion: MoveTo (ego x?) (- (ego y?) 40) self
		)
		(super init: &rest)
	)
	
	(method (cue)
		(if (not cuedOnce)
			(= cuedOnce 1)
			(self setScript: (sCheckLoop new:) self)
		else
			(if resistTimer
				(ego takeDamage: 2)
			else
				(ego takeDamage: curDamage)
			)
			(if (<= [egoStats 17] 0) (EgoDead 25 580 43 End))
			(self setMotion: 0 dispose:)
		)
	)
)

(instance queenFairy of TargProp
	(properties
		noun 2
		x 169
		y 144
		view 450
		signal $5001
	)
	
	(method (doVerb theVerb)
		(if (Message msgSIZE 580 2 theVerb 0 1)
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
	
	(method (getHurt param1 param2)
		(cond 
			((> local2 1000))
			((and (not local5) (OneOf param1 21 37 33)) (messager say: 14 6 22))
			((== param1 79)
				(if (<= (= local2 (- local2 param2)) 0)
					(= local2 3000)
					(theGame handsOff:)
					(queenFairy setScript: 0)
					(curRoom setScript: sQueenDies)
				)
			)
			(else ((FairySpell new:) init: param1 param2 self))
		)
	)
)

(instance rightFairy of TargProp
	(properties
		noun 3
		x 202
		y 159
		view 450
		loop 1
		signal $5001
	)
	
	(method (cue)
		(self hide:)
	)
	
	(method (getHurt param1 param2)
		(cond 
			((> local4 1000))
			((and (not local5) (OneOf param1 21 37 33)) (messager say: 14 6 22))
			(
			(and (= local4 (- local4 param2)) (<= local4 0))
				(= local4 3000)
				(self
					setScript: 0
					view: 450
					setLoop: 1 1
					setCel: 15
					setCycle: Beg self
				)
			)
		)
	)
)

(instance leftFairy of TargProp
	(properties
		noun 3
		x 52
		y 166
		view 450
		loop 2
		signal $5001
	)
	
	(method (cue)
		(self hide:)
	)
	
	(method (getHurt param1 param2)
		(cond 
			((> local3 1000))
			((and (not local5) (OneOf param1 21 37 33)) (messager say: 14 6 22))
			(
			(and (= local3 (- local3 param2)) (<= local3 0))
				(= local3 3000)
				(self
					setScript: 0
					view: 450
					setLoop: 2 1
					setCel: 14
					setCycle: Beg self
				)
			)
		)
	)
)

(instance fountainTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(cond 
			((== iconValue 3) (self clean:) (curRoom setScript: sDrink 0 3))
			((== iconValue 4) (self clean:) (curRoom setScript: sDrink 0 4))
			(else (super sayMessage: &rest))
		)
	)
)

(instance fountainFX of Sound
	(properties
		number 982
	)
)

(instance queenTalker of GloryTalker
	(properties
		x 168
		y 0
		talkWidth 130
		view 456
		textX -159
		textY 10
	)
	
	(method (init)
		(super init: queenMouth 0 queenEyes queenFrame &rest)
		(ear2 init:)
		(ear1 init:)
		(queenMouth setScript: sTwitchEm)
	)
	
	(method (dispose param1)
		(if (or (not argc) param1)
			(ear1 dispose:)
			(ear2 dispose:)
		)
		(super dispose: param1)
	)
)

(instance sTwitchEm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (self cue:))
			(1 (= seconds (Random 1 3)))
			(2
				(ear1 setCycle: End)
				(ear2 setCycle: End)
				(= seconds 1)
			)
			(3
				(ear1 setCycle: Beg)
				(ear2 setCycle: Beg)
				(= seconds 1)
			)
			(4
				(if (Random 0 1)
					(self changeState: 0)
				else
					(if (Random 0 1)
						(ear1 setCycle: End)
					else
						(ear2 setCycle: End)
					)
					(= seconds 1)
				)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance queenFrame of View
	(properties
		x 168
		view 456
	)
)

(instance queenMouth of Prop
	(properties
		x 208
		y 63
		view 456
		loop 1
	)
)

(instance queenEyes of Prop
	(properties
		x 203
		y 42
		view 456
		loop 2
	)
)

(instance ear1 of Prop
	(properties
		x 194
		y 22
		view 456
		loop 3
	)
)

(instance ear2 of Prop
	(properties
		x 265
		y 22
		view 456
		loop 4
	)
)

(instance sStaffHurt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (aStaff cel?) 15)
					(aStaff
						signal: (| (aStaff signal?) $0001)
						setCel: 11
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1
				(if (!= (aStaff cel?) 11)
					(aStaff
						signal: (| (aStaff signal?) $0001)
						setCycle: CT 11 -1 self
					)
				else
					(self dispose:)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sTriggerStaff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setCel: 11
					setCycle: End self
				)
			)
			(1
				(aStaff setCycle: CT 11 -1 self)
			)
			(2
				(= register 100)
				(while (< register 1000)
					(Palette palSET_FLAG 0 255 register)
					(= register (+ register 10))
				)
				(= register 500)
				(while (> register 0)
					(Palette palSET_FLAG 0 255 register)
					(= register (- register 10))
				)
				(aStaff setCycle: Beg self)
			)
			(3
				(aStaff dispose:)
				(ego learn: 35 100)
				(messager say: 2 6 11 0 self)
			)
			(4
				(= register 0)
				(while (< register 100)
					(Palette palSET_FLAG 0 255 register)
					(= register (+ register 5))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMoveToFetch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 364)
				(ego
					setMotion: PolyPath (aStaff approachX?) (aStaff approachY?) self
				)
			)
			(1
				((User curEvent?) x: (aStaff x?))
				((User curEvent?)
					y: (- (- (aStaff y?) 15) (aStaff z?))
				)
				(curRoom setScript: (ScriptID 37) aStaff aStaff)
			)
		)
	)
)

(instance sTouchStaff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 364)
				(ego
					view: 31
					setLoop: 0 1
					cycleSpeed: 6
					setCel: 0
					setCycle: End self
				)
			)
			(1 (messager say: 5 4 0 0 self))
			(2 (curRoom setScript: sEvent2))
		)
	)
)

(instance sEvent2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setCel: 15
					setCycle: Beg self
				)
				(ego normalize: 6)
			)
			(1
				(aStaff dispose:)
				(self setScript: sFaeriesAppear self)
			)
			(2 (= ticks 120))
			(3
				(messager say: 2 6 10 0 self)
			)
			(4
				(ego learn: 35 100)
				(self setScript: sFaeriesGo self)
			)
			(5
				(ego
					view: 6
					setLoop: 4 1
					setCel: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
			)
			(6 (= ticks 120))
			(7
				(ego setLoop: 6 1 setCel: 0 setCycle: End self)
			)
			(8
				(ego normalize: 4 setSpeed: gTheObj_2MoveSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFaeriesGo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(leftFairy
					view: 450
					setLoop: 2 1
					setCel: 14
					setCycle: CT 5 -1 self
				)
			)
			(1
				(aSound number: 934 play:)
				(leftFairy setCycle: Beg)
				(rightFairy
					view: 450
					setLoop: 1 1
					setCel: 15
					setCycle: CT 5 -1 self
				)
			)
			(2
				(aSound number: 934 play:)
				(leftFairy dispose:)
				(rightFairy setCycle: Beg)
				(queenFairy
					view: 450
					setLoop: 0 1
					setCel: 15
					setCycle: Beg self
				)
			)
			(3
				(aSound number: 934 play:)
				(rightFairy dispose:)
				(queenFairy dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFaeriesAppear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(leftFairy init: setCycle: CT 10 1 self)
				(aSound number: 934 play:)
			)
			(1
				(ego setHeading: 270)
				(leftFairy setCycle: CT 14 1)
				(rightFairy init: setCycle: CT 10 1 self)
				(aSound number: 934 play:)
			)
			(2
				(ego setHeading: 90)
				(rightFairy setCycle: End)
				(queenFairy init: setCycle: CT 10 1 self)
				(aSound number: 934 play:)
			)
			(3
				(ego setHeading: 45)
				(queenFairy setCycle: End self)
			)
			(4 (self dispose:))
		)
	)
)

(instance sStartFountain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cast contains: water))
					(water approachVerbs: 4 init:)
				)
				(water
					setLoop: 1 1
					setCel: 0
					signal: (| (water signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(fountainFX setLoop: -1 play:)
				(water setCel: 0 setLoop: 2 1 setCycle: Fwd)
				(messager say: 14 6 2 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDrink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego
					view: 4
					setLoop: 2 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(1 (= ticks 120))
			(2
				(messager say: 15 4 register 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4 (= ticks 60))
			(5
				(ego
					view: 6
					x: (- (ego x?) 3)
					y: (- (ego y?) 2)
					setLoop: 1 1
					setCel: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
				(water setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(6
				(water signal: (& (water signal?) $fffe))
				(fountainFX stop:)
			)
			(7
				(= register 100)
				(while (> register 0)
					(Palette palSET_FLAG 0 255 register)
					(-- register)
				)
				(Bset 269)
				(if (>= global474 5)
					(messager say: 2 6 7 0 self)
				else
					(messager say: 2 6 8 0 self)
				)
				(fountain dispose:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 319 189 150 189 167 177 206 166 206 145 319 145
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 319 122 255 122 201 93 238 72 238 0 319 0
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 109 0 109 72 159 80 159 94 99 109 115 131 0 131 0 0
							yourself:
						)
				)
				(water dispose:)
			)
			(8
				(= register 0)
				(while (< register 100)
					(Palette palSET_FLAG 0 255 register)
					(++ register)
				)
				(= ticks 60)
			)
			(9
				(ego setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(10
				(messager say: 14 6 9 0 self)
			)
			(11
				(theMusic number: 557 setLoop: -1 play:)
				(ego
					x: (- (ego x?) 8)
					y: (+ (ego y?) 5)
					setSpeed: gTheObj_2MoveSpeed
					normalize: 5
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent3 of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 115 170 self)
			)
			(1
				(= cycles (+ (ego cycleSpeed?) 1))
			)
			(2
				(messager say: 14 6 12 0 self)
			)
			(3
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego setSpeed: defaultCycles)
				(self setScript: sFaeriesAppear self)
			)
			(4
				(leftFairy
					view: 451
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
				(aSound number: 940 play:)
			)
			(5
				(leftFairy setLoop: 5 1 setCycle: Fwd)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego
					view: 581
					setLoop: 0 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(6
				(aSound number: 940 play:)
				(rightFairy
					view: 451
					setLoop: 1 1
					setCel: 0
					setCycle: End self
				)
			)
			(7
				(rightFairy setLoop: 4 1 setCycle: Fwd)
				(ego setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(8
				(aSound number: 940 play:)
				(queenFairy
					view: 451
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
			)
			(9
				(queenFairy setLoop: 3 1 setCycle: Fwd)
				(leftFairy setLoop: 2 1 setCel: 9 setCycle: 0)
				(= cycles defaultCycles)
			)
			(10
				(leftFairy setCel: 1)
				(= cycles defaultCycles)
			)
			(11
				(rightFairy setLoop: 1 1 setCel: 9 setCycle: 0)
				(= cycles defaultCycles)
			)
			(12
				(rightFairy setCel: 0)
				(= cycles defaultCycles)
			)
			(13
				(queenFairy setLoop: 0 1 setCel: 5 setCycle: 0)
				(= cycles defaultCycles)
			)
			(14
				(queenFairy setCel: 0)
				(= ticks 120)
			)
			(15
				(messager say: 2 6 14 0 self)
			)
			(16
				(aSound number: 940 play:)
				(queenFairy setCycle: End self)
			)
			(17
				(aStaff
					signal: (| (aStaff signal?) $0001)
					init:
					x: (ego x?)
					y: (- (ego y?) 15)
					setCycle: End self
				)
				(queenFairy setLoop: 3 1 setCycle: Fwd)
			)
			(18
				(aStaff signal: (& (aStaff signal?) $fffe))
				(= ticks 60)
			)
			(19
				(messager say: 2 6 15 0 self)
			)
			(20
				(aSound number: 940 play:)
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setCycle: CT 11 -1 self
				)
			)
			(21
				(aStaff signal: (& (aStaff signal?) $fffe))
				(= ticks 30)
			)
			(22
				(= [egoStats 17] (ego maxHealth:))
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 19] (ego maxMana:))
				(messager say: 14 6 18 0 self)
			)
			(23
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setCycle: End self
				)
			)
			(24
				(aStaff signal: (& (aStaff signal?) $fffe))
				(queenFairy setLoop: 0 1 setCel: 0 setCycle: 0)
				(ego setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(25
				(ego normalize: 6)
				(messager say: 5 6 17 1 self)
			)
			(26
				(= newStr (Str new:))
				(Message msgGET 580 5 6 29 1 (newStr data?))
				(= newStr_2 (Str new:))
				(newStr_2 format: {%s} userName)
				(newStr format: (newStr data?) newStr_2)
				(if (& msgType $0002)
					(= temp0 (IntArray with: 0 0 0 0 0))
					(Message 9 (temp0 data?))
				)
				(narrator say: newStr self)
			)
			(27
				(newStr dispose:)
				(newStr_2 dispose:)
				(ego learn: 32 200 learn: 39 100)
				(messager say: 5 6 30 1 self)
			)
			(28
				(aStaff
					signal: (| (aStaff signal?) $0001)
					setCycle: Beg self
				)
			)
			(29
				(aStaff dispose:)
				(messager say: 2 6 16 0 self)
			)
			(30 (= ticks 60))
			(31
				(queenFairy setScript: (sCastSpell new:) 0 0)
				(rightFairy setScript: (sCastSpell new:) 0 1)
				(leftFairy setScript: (sCastSpell new:) 0 2)
				(Bset 9)
				(theMusic number: 581 setLoop: -1 play:)
				(localproc_01a6)
				(self dispose:)
			)
		)
	)
)

(instance sCheckLoop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (client loop?)
					(0
						(client setLoop: 14 1 setMotion: 0 setCycle: End self)
						(qReverseFX number: 930 play:)
					)
					(2
						(client setLoop: 9 1 setMotion: 0 setCycle: End self)
						(qReverseFX number: 930 play:)
					)
					(4
						(client setLoop: 10 1 setMotion: 0 setCycle: End self)
					)
					(13
						(client setLoop: 14 1 setMotion: 0 setCycle: End self)
					)
					(else  (self cue:))
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sQueenDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego normalize: 6)
				(queenFairy
					setScript: 0
					view: 450
					setLoop: 0 1
					setCel: 15
					setCycle: Beg self
				)
			)
			(1
				(queenFairy hide:)
				(theMusic fade:)
				(theBall
					x: (queenFairy x?)
					y: (queenFairy y?)
					z: 30
					setCycle: Fwd
					init:
				)
				(if (< local4 1000)
					(= local4 3000)
					(rightFairy
						setScript: 0
						view: 450
						setLoop: 1 1
						setCel: 15
						setCycle: Beg rightFairy
					)
				)
				(if (< local3 1000)
					(= local3 3000)
					(leftFairy
						setScript: 0
						view: 450
						setLoop: 1 1
						setCel: 15
						setCycle: Beg leftFairy
					)
				)
				(= seconds 3)
			)
			(2
				(messager say: 5 6 26 0 self)
			)
			(3
				(theBall
					setMotion: MoveTo (ego x?) (- (ego y?) 3) self
				)
			)
			(4
				(theMusic number: 557 play:)
				(ego get: 56 1 solvePuzzle: 500 15 2)
				(messager say: 6 87 28 0 self)
			)
			(5
				(theBall hide:)
				(theGame handsOn:)
				(= local0 0)
				(Bclr 35)
				(self dispose:)
			)
		)
	)
)

(instance theBall of Actor
	(properties
		view 582
		loop 2
		signal $4801
	)
)

(instance sCastSpell of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(client
					view: 451
					setLoop: register 1
					setCel: 0
					setCycle: CT (- (client lastCel:) 1) 1 self
				)
			)
			(1
				(if (and (Btst 8) (not local7))
					(= local7 1)
					(messager say: 2 6 19 0 self)
				else
					(self cue:)
				)
			)
			(2
				(if
				(and (== client queenFairy) (< [egoStats 19] 5))
					(curRoom setScript: sHelpless)
				)
				(client setLoop: (+ register 3) 1 setCycle: Fwd)
				(if (Btst 8)
					(if (== client queenFairy)
						(= temp0 56)
					else
						(= temp0 36)
					)
					((FairySpell new:) init: 79 temp0 client)
					(= ticks
						(switch arcadeLevel
							(1 (Random 300 360))
							(2 (Random 240 300))
							(3 (Random 180 240))
						)
					)
				else
					(if (== client queenFairy)
						(= temp0 50)
					else
						(= temp0 30)
					)
					((FairySpell new:) init: 86 temp0 client)
					(= ticks
						(switch arcadeLevel
							(1 (Random 300 360))
							(2 (Random 240 300))
							(3 (Random 180 240))
						)
					)
				)
			)
			(3
				(client
					setLoop: register 1
					setCel: (if (== client leftFairy) 1 else 0)
					setCycle: 0
				)
				(= ticks
					(switch arcadeLevel
						(1 (Random 300 360))
						(2 (Random 240 300))
						(3 (Random 180 240))
					)
				)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance sHelpless of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(queenFairy
					setLoop: (+ (queenFairy loop?) 3) 1
					setCycle: Fwd
				)
				(messager say: 2 6 24 0 self)
			)
			(1 (= seconds 3))
			(2 (ego hide:) (= seconds 2))
			(3 (EgoDead 25 580 43 End))
		)
	)
)
