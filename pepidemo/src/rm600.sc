;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh)
(use Main)
(use TWRoom)
(use Print)
(use Scaler)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0 =  1
	local1 =  1
	local2 =  1
	local3 =  1
	[local4 60]
	[local64 60]
	[local124 60]
	[local184 60]
	[local244 60]
	[local304 60]
	[local364 60]
	[local424 60]
	local484
	local485
	local486
	local487
	local488
	theFA
	local490 = [0 3 2 4 1 3 0 4 2 2 4 3 0 4 3 2 4 2 0 3 4 2 1 3 0 3 2 1 4 3 0 1 3 2 4 3]
	local526
	local527
)
(procedure (localproc_0ab0 param1 param2 &tmp [len 4] temp4)
	(= temp4 (if param2 11 else 0))
	(switch param1
		(fA
			(TextSize @[len 0] @local4 11 0 0)
			(Display @local4 p_at 105 92 p_font 11 p_color temp4)
			(Display @local64 p_at 121 99 p_font 11 p_color temp4)
		)
		(fB
			(TextSize @[len 0] @local124 11 0 0)
			(Display
				@local124
				p_at
				105
				107
				p_font
				11
				p_color
				temp4
			)
			(Display
				@local184
				p_at
				121
				114
				p_font
				11
				p_color
				temp4
			)
		)
		(fC
			(TextSize @[len 0] @local244 11 0 0)
			(Display
				@local244
				p_at
				105
				122
				p_font
				11
				p_color
				temp4
			)
			(Display
				@local304
				p_at
				121
				129
				p_font
				11
				p_color
				temp4
			)
		)
		(fD
			(TextSize @[len 0] @local364 11 0 0)
			(Display
				@local364
				p_at
				105
				137
				p_font
				11
				p_color
				temp4
			)
			(Display
				@local424
				p_at
				121
				144
				p_font
				11
				p_color
				temp4
			)
		)
	)
)

(procedure (localproc_0bfa &tmp [temp0 40] [temp40 40] temp80 temp81)
	(= local484 (+ 1 (* 3 (- local0 1))))
	(= temp80 92)
	(= temp81 99)
	(= local1 1)
	(while (< local1 5)
		(= local485 (+ 130 local1))
		(if
		(Message MsgGet local487 local484 local485 1 1 @temp0)
			(Display @temp0 p_at 105 temp80 p_color 0 p_font 11)
		else
			(Printf {Didn't find Answer %d..L1.. :-} local1)
		)
		(if
		(Message MsgGet local487 local484 local485 2 1 @temp40)
			(Display @temp40 p_at 121 temp81 p_color 0 p_font 11)
		else
			(Printf {Didn't find Answer %d..L2.. :-} local1)
		)
		(switch local1
			(1
				(StrCpy @local4 @temp0)
				(StrCpy @local64 @temp40)
			)
			(2
				(StrCpy @local124 @temp0)
				(StrCpy @local184 @temp40)
			)
			(3
				(StrCpy @local244 @temp0)
				(StrCpy @local304 @temp40)
			)
			(4
				(StrCpy @local364 @temp0)
				(StrCpy @local424 @temp40)
			)
		)
		(= temp80 (+ temp80 15))
		(= temp81 (+ temp81 15))
		(++ local1)
	)
)

(procedure (localproc_0d3e param1 &tmp [temp0 100])
	(= local484 (+ 2 (* 3 (- local0 1))))
	(= local485 0)
	(if
	(Message MsgGet local487 local484 local485 1 1 @temp0)
		(Display @temp0 p_at 105 60 p_color 0 p_font 69)
	else
		(Prints {Didn't find question..L1.. :-})
	)
	(if
	(Message MsgGet local487 local484 local485 2 1 @temp0)
		(Display @temp0 p_at 105 72 p_color 0 p_font 69)
	else
		(Prints {Didn't find question..L2.. :-})
	)
	(localproc_0bfa)
	(++ local0)
	(param1 cue:)
)

(instance rm600 of ADRoom
	(properties
		picture 600
		style $0009
		horizon 75
		vanishingY -60
	)
	
	(method (init)
		(LoadMany 128 827 822 823 600)
		(Bset 2)
		(if (== gameAct 0)
			(= gameAct 1)
			(Printf {ACT %d questions_} gameAct)
		)
		(super init: &rest)
		(theGame handsOn:)
		(theIconBar disable:)
		(switch prevRoomNum
			(345 (= prevRoomNum 330))
			(340 (= prevRoomNum 320))
		)
		(fA init:)
		(fB init:)
		(fC init:)
		(fD init:)
		(whiteOut1 init:)
		(whiteOut2 init:)
		(whiteOut3 init:)
		(whiteOut4 init:)
		(whiteOut5 init:)
		(whiteOut6 init:)
		(therm1 init: stopUpd:)
		(therm2 init: stopUpd:)
		(therm3 init: stopUpd:)
		(ben init: stopUpd:)
		(lj init: stopUpd:)
		(kite init: setCycle: Forward)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(= local487 (+ gameAct 600))
		(user canInput: 1 canControl: 1)
		(if (> global214 0)
			(self setScript: sPutUpTherm)
		else
			(self setScript: sDoQuestions)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if script
		else
			(= temp0
				(features firstTrue: #onMe mouseX (- mouseY 10))
			)
			(if (and (IsObject temp0) (!= temp0 theFA))
				(localproc_0ab0 theFA 0)
				(localproc_0ab0 temp0 1)
				(= theFA temp0)
			)
		)
	)
	
	(method (handleEvent event &tmp eMsg eType temp2)
		(= eType (event type?))
		(= eMsg (event message?))
		(return
			(cond 
				(
					(and
						local3
						(or
							(and (== eType 1) (not (event modifiers?)))
							(and (== eType 4) (== eMsg 13))
						)
					)
					(event claimed: 1)
					(switch theFA
						(fA (= local2 1) (= theFA fA))
						(fB (= local2 2) (= theFA fB))
						(fC (= local2 3) (= theFA fC))
						(fD (= local2 4) (= theFA fD))
					)
					(curRoom setScript: replys)
				)
				(
				(or (== eMsg 1) (and (== eType 4) (== eMsg 3840)))
					(localproc_0ab0 theFA 0)
					(if
					(>= (= temp2 (- (features indexOf: theFA) 1)) 0)
						(= theFA (features at: temp2))
					else
						(= theFA (features at: (- (features size?) 1)))
					)
					(localproc_0ab0 theFA 1)
				)
				(
				(or (== eMsg 5) (and (== eType 4) (== eMsg 9)))
					(localproc_0ab0 theFA 0)
					(if
						(<
							(= temp2 (+ (features indexOf: theFA) 1))
							(features size?)
						)
						(= theFA (features at: temp2))
					else
						(= theFA (features at: 0))
					)
					(localproc_0ab0 theFA 1)
				)
				(else (not (& eType $0040)))
			)
		)
	)
	
	(method (newRoom)
		(Bclr fCantSave)
		(+= global214 local488)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(user canInput: FALSE canControl: FALSE)
		(super newRoom: &rest)
	)
)

(instance replys of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local3 0)
				(= cycles 1)
			)
			(1
				(whiteOut1 hide:)
				(whiteOut2 hide:)
				(whiteOut3 hide:)
				(whiteOut4 hide:)
				(whiteOut5 hide:)
				(whiteOut6 hide:)
				(= local526
					[local490 (= local527 (+ (* (- gameAct 1) 6) (- local0 1)))]
				)
				(= local484 (* (- local0 1) 3))
				(= local485 (+ local2 130))
				(if (== local2 local526) (++ local488))
				(= cycles 1)
			)
			(2
				(messager say: local484 local485 1 0 self local487)
			)
			(3
				(if (== local2 local526)
					(if (== local488 5)
						(self setScript: sDoZap)
					else
						(self setScript: sDoHappyAnimation self)
					)
				else
					(self setScript: sDoSadAnimation self)
				)
			)
			(4
				(if (> local0 5)
					(RestartGame)
				else
					(curRoom setScript: sDoQuestions)
				)
			)
		)
	)
)

(instance sPutUpTherm of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(therm1 setCycle: CycleTo global214 1 self)
			)
			(1
				(if (> global214 10)
					(therm2 setCycle: CycleTo (- global214 10) 1 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (> global214 20)
					(therm3 setCycle: CycleTo (- global214 20) 1 self)
				else
					(= cycles 1)
				)
			)
			(3
				(client setScript: sDoQuestions)
			)
		)
	)
)

(instance sDoQuestions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(whiteOut1 show:)
				(whiteOut2 show:)
				(whiteOut3 show:)
				(whiteOut4 show:)
				(whiteOut5 show:)
				(whiteOut6 show:)
				(= ticks 5)
			)
			(1 (localproc_0d3e self))
			(2
				(localproc_0ab0 fA 1)
				(= theFA fA)
				(theGame handsOn:)
				(= local3 1)
				(theGame setCursor: ARROW_CURSOR TRUE 160 172)
				(self dispose:)
			)
		)
	)
)

(instance sDoZap of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(+= score 5)
				(sPointsSnd play:)
				((ScriptID 0 9) doit:)
				(theGame handsOff:)
				(= local3 0)
				(= cycles 1)
			)
			(1
				(lightning
					setScale: Scaler 100 205 189 15
					init:
					setPri: 15
					setCycle: Forward
				)
				(= ticks 3)
			)
			(2
				(sFX number: 3452 play:)
				(Palette PALIntensity 0 255 (Random 300 500))
				(= ticks 3)
			)
			(3
				(Palette PALIntensity 0 255 100)
				(= ticks 30)
			)
			(4
				(lightning setCycle: 0 hide:)
				(lj view: 823 loop: 2 setCycle: EndLoop self)
				(ben view: 823 loop: 0 setCycle: EndLoop self)
			)
			(5 0)
			(6
				(lightning show: setCycle: Forward)
				(= ticks 3)
			)
			(7
				(sFX number: 3452 play:)
				(Palette PALIntensity 0 255 (Random 300 500))
				(= ticks 3)
			)
			(8
				(Palette PALIntensity 0 255 100)
				(= ticks 30)
			)
			(9
				(lightning dispose:)
				(lj view: 823 loop: 2 setCycle: EndLoop self)
				(ben view: 823 loop: 0 setCycle: EndLoop self)
			)
			(10 0)
			(11
				(lj stopUpd:)
				(ben stopUpd:)
				(= cycles 1)
			)
			(12
				(self setScript: sIncreaseTherm self)
			)
			(13 (RestartGame))
		)
	)
)

(instance sIncreaseTherm of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((< (therm1 cel?) 10) (therm1 cel: (+ (therm1 cel?) 1)) (therm1 stopUpd:))
					((< (therm2 cel?) 10) (therm2 cel: (+ (therm2 cel?) 1)) (therm2 stopUpd:))
					((< (therm3 cel?) 10) (therm3 cel: (+ (therm3 cel?) 1)) (therm3 stopUpd:))
				)
				(= cycles 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance sDoSadAnimation of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (sFX number: 951 play: self))
			(1 (= ticks 60))
			(2
				(ben view: 822 loop: 0 cel: 0 setCycle: EndLoop self)
				(sFX number: 905 play:)
				(lj view: 822 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3 0)
			(4 (= ticks 60))
			(5 (ben setCycle: BegLoop self))
			(6
				(lj cel: 0 stopUpd:)
				(ben loop: 2 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sDoHappyAnimation of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= score (+ score 5))
				(sPointsSnd play:)
				((ScriptID 0 9) doit:)
				(ben view: 827 loop: 0 cel: 0 setCycle: EndLoop self)
				(lj view: 827 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1 (sFX number: 950 play:) 0)
			(2 (lj setCycle: CycleTo 11 1 self))
			(3
				(sFX number: 909 loop: -1 play:)
				(lj setCycle: EndLoop self)
			)
			(4 (ben setCycle: BegLoop self))
			(5 (lj setCycle: CycleTo 11 -1 self))
			(6 (lj setCycle: CycleTo 7 -1 self))
			(7
				(sFX number: 950 loop: 1 play:)
				(lj setCycle: CycleTo 0 -1 self)
			)
			(8
				(lj stopUpd:)
				(ben loop: 2 stopUpd:)
				(= cycles 1)
			)
			(9
				(self setScript: sIncreaseTherm self)
			)
			(10 (self dispose:))
		)
	)
)

(instance therm1 of Prop
	(properties
		x 16
		y 150
		view 824
	)
)

(instance therm2 of Prop
	(properties
		x 16
		y 110
		view 824
	)
)

(instance therm3 of Prop
	(properties
		x 16
		y 70
		view 824
		priority 5
		signal fixPriOn
	)
)

(instance lj of Prop
	(properties
		x 61
		y 188
		view 822
		loop 1
		cel 12
	)
)

(instance ben of Prop
	(properties
		x 297
		y 188
		view 822
		cel 12
	)
)

(instance kite of Prop
	(properties
		x 44
		y 160
		view 824
		loop 1
	)
)

(instance zap of Prop
	(properties
		x 92
		y 135
		view 850
	)
)

(instance lightning of Prop
	(properties
		x 11
		y -4
		view 346
		scaleSignal scalable
	)
)

(instance fA of Feature
	(properties
		y 170
		nsTop 92
		nsLeft 101
		nsBottom 106
		nsRight 276
		sightAngle 40
	)
)

(instance fB of Feature
	(properties
		y 170
		nsTop 106
		nsLeft 101
		nsBottom 121
		nsRight 276
	)
)

(instance fC of Feature
	(properties
		y 170
		nsTop 121
		nsLeft 101
		nsBottom 136
		nsRight 276
		sightAngle 40
	)
)

(instance fD of Feature
	(properties
		y 170
		nsTop 136
		nsLeft 101
		nsBottom 154
		nsRight 276
		sightAngle 40
	)
)

(instance whiteOut1 of View
	(properties
		x 101
		y 74
		view 600
	)
)

(instance whiteOut2 of View
	(properties
		x 101
		y 93
		view 600
	)
)

(instance whiteOut3 of View
	(properties
		x 101
		y 112
		view 600
	)
)

(instance whiteOut4 of View
	(properties
		x 101
		y 131
		view 600
	)
)

(instance whiteOut5 of View
	(properties
		x 101
		y 144
		view 600
	)
)

(instance whiteOut6 of View
	(properties
		x 109
		y 152
		view 600
		loop 1
	)
)

(instance sFX of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance sPointsSnd of Sound
	(properties
		flags mNOPAUSE
		number 938
	)
)
