;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryTalker)
(use rm740)
(use String)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Actor)
(use System)

(public
	TTDialer 0
	Class_610_17 1
	proc610_2 2
	proc610_3 3
)

(local
	local0
	local1
	newStr
	local3
	local4
	local5
	[local6 14] = [0 1 -16 1 19 -16 1 19 -16 1 19 -16 19 -1]
	[local20 20] = [0 -15 -69 -69 -69 -52 -52 -52 -34 -34 -34 -15 -15 -3 551 552 553 554 555 556]
	local40
	local41
	newStrAsInteger
	theNewStrAsInteger
	[local44 69] = [96 1 5 75 2 12 98 3 20 74 4 28 73 5 36 72 6 45 71 7 49 94 8 69 93 9 85 92 10 100 76 11 60 90 12 85 89 8 69 18724 13 100 23292 14 100 -6832 15 103 -24594 15 103 8837 15 103 -30981 16 100 -17883 17 100 16659 18 100 275 19 100 -15684 20 100]
	local113 =  68
)
(procedure (proc610_2)
	(if (Class_610_17 name?)
		((Class_610_17 name?) dispose:)
		(Class_610_17 name: 0)
	)
	(if newStr (newStr dispose:) (= newStr 0))
)

(procedure (proc610_3 param1 &tmp temp0 temp1)
	(= temp1 param1)
	(= local4 (Str new: 30))
	(if (Btst 57) (= temp1 71))
	(= temp0 0)
	(while (<= temp0 local113)
		(if (== temp1 [local44 temp0])
			(Message
				0
				610
				1
				0
				1
				[local44 (+ temp0 1)]
				(local4 data?)
			)
			(= local3 (Str copy: local4))
			(Class_610_17 showTitle: 1 name: local3)
		)
		(= temp0 (+ temp0 3))
	)
	(local4 dispose:)
	(= local4 0)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance TTDialer of Prop
	(properties
		x 217
		y 118
		priority 189
		fixPriority 1
		view 610
	)
	
	(method (init param1)
		(proc79_11 10 11 12 13 14 15 16 17 18 19 20 21 22)
		(roomFeat init:)
		(= local41 param1)
		(= newStr (Str new:))
		(= gGButtonBarCurIcon (ScriptID 0 5))
		(theGame handsOn:)
		(user canControl: 0 canInput: 1)
		(theIconBar
			disableIcon:
				(ScriptID 0 3)
				(ScriptID 0 6)
				(ScriptID 0 4)
				(ScriptID 0 7)
				(ScriptID 0 9)
			show:
		)
		((ScriptID 0 11) init: exitCue)
		(dialTone number: 22 loop: -1 flags: -1 play:)
		(super init:)
		(btn0 init:)
		(btn1 init:)
		(btn2 init:)
		(btn3 init:)
		(btn4 init:)
		(btn5 init:)
		(btn6 init:)
		(btn7 init:)
		(btn8 init:)
		(btn9 init:)
		(btnStar init:)
		(btnCross init:)
		(hangUp init:)
	)
	
	(method (dispose &tmp temp0)
		(roomFeat dispose:)
		(theGame handsOff:)
		(if (not newStrAsInteger)
			(= newStrAsInteger (newStr asInteger:))
		)
		(if (not (Class_610_17 name?))
			(proc610_3 newStrAsInteger)
		)
		(btn0 hide:)
		(btn1 hide:)
		(btn2 hide:)
		(btn3 hide:)
		(btn4 hide:)
		(btn5 hide:)
		(btn6 hide:)
		(btn7 hide:)
		(btn8 hide:)
		(btn9 hide:)
		(btnStar hide:)
		(btnCross hide:)
		(hangUp hide:)
		(= local5 254)
		(self hide:)
		(hangUpTimer setCycle: self 2)
	)
	
	(method (cue param1 param2 &tmp temp0)
		(if (!= local5 254)
			((param2 script?) dispose:)
			(param2 script: 0)
		)
		(switch (++ local5)
			(1
				(= temp0 (newStr asInteger:))
				(cond 
					((== param1 (btnCross keyValue?)) (= newStrAsInteger -2))
					((== param1 (btnStar keyValue?)) (= newStrAsInteger -3))
					((or (Btst 97) (Btst 57)) (= newStrAsInteger temp0))
					((or (== temp0 8) (== temp0 1)) (= theNewStrAsInteger 93) (newStr dispose:) (= newStr 0))
					((not temp0) (= newStrAsInteger 91))
					((== temp0 9) (= theNewStrAsInteger 92))
				)
				(if
				(or (== newStrAsInteger 91) (Btst 97) (Btst 57))
					(self dispose:)
				)
			)
			(2
				(cond 
					((OneOf (newStr asInteger:) 72 73 74 75 76) (self dispose:))
					((== (newStr asInteger:) 71) (Bset 57) (self dispose:))
				)
			)
			(3
				(switch (newStr asInteger:)
					(411
						(= newStrAsInteger 96)
						(self dispose:)
					)
					(911
						(= newStrAsInteger 98)
						(self dispose:)
					)
					(976 (= theNewStrAsInteger 94))
					(209 (= theNewStrAsInteger 209))
					(805 (= theNewStrAsInteger 805))
				)
			)
			(4
				(cond 
					((== (newStr asInteger:) 1900) (= theNewStrAsInteger 89))
					((== (newStr asInteger:) 1800) (= theNewStrAsInteger 90))
					((== (newStr asInteger:) 1209) (= theNewStrAsInteger 88) (newStr dispose:) (= newStr 0))
					((== (newStr asInteger:) 1805) (= theNewStrAsInteger 87) (newStr dispose:) (= newStr 0))
				)
			)
			(5 0)
			(6 0)
			(7
				(cond 
					((== theNewStrAsInteger 93) (= newStrAsInteger (newStr asInteger:)) (self dispose:))
					(theNewStrAsInteger (= newStrAsInteger theNewStrAsInteger))
					(else (= newStrAsInteger (newStr asInteger:)))
				)
				(if
				(not (OneOf theNewStrAsInteger 209 805 93 87 88 90 89))
					(self dispose:)
				)
			)
			(8 0)
			(9 0)
			(10
				(if (OneOf theNewStrAsInteger 209 805)
					(= newStrAsInteger (newStr asInteger:))
					(self dispose:)
				)
			)
			(11
				(if (OneOf newStrAsInteger 87 88)
					(= newStrAsInteger (newStr asInteger:))
				)
				(self dispose:)
			)
			(255
				(if (not local0) ((ScriptID 0 11) dispose:))
				(hangUpTimer dispose: delete:)
				(theGame setCursor: waitCursor)
				(= theNewStrAsInteger 0)
				(= local1 0)
				(= local3 0)
				(= local4 0)
				(= local5 0)
				(= local40 0)
				(= local0 0)
				(dialTone number: 0 dispose:)
				(sfx number: 0 dispose:)
				(proc79_12 10 11 12 13 14 15 16 17 18 19 20 21 22)
				(super dispose:)
				(btn0 dispose:)
				(btn1 dispose:)
				(btn2 dispose:)
				(btn3 dispose:)
				(btn4 dispose:)
				(btn5 dispose:)
				(btn6 dispose:)
				(btn7 dispose:)
				(btn8 dispose:)
				(btn9 dispose:)
				(btnStar dispose:)
				(btnCross dispose:)
				(hangUp dispose:)
				(curRoom notify: newStrAsInteger)
				(if local41 (local41 cue:))
				(= local41 0)
				(= newStrAsInteger 0)
			)
			(else  (= quit 1))
		)
	)
)

(instance hangUpTimer of Timer
	(properties)
)

(class PushButton of Prop
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
		priority 190
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 610
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		keyValue 45
		scanValue 0
		keyStr 0
		tone 0
	)
	
	(method (init &tmp temp0)
		(self cel: 0)
		(= x (+ (TTDialer x?) [local6 loop] 5))
		(= y (+ (TTDialer y?) [local20 loop] 1000))
		(= keyStr (Str with: {x}))
		(super init:)
	)
	
	(method (dispose)
		(if script (script dispose:) (= script 0))
		(keyStr dispose:)
		(self keyStr: 0)
		(super dispose:)
	)
	
	(method (doVerb)
		(if (not (sButton client?))
			(dialTone stop:)
			(switch loop
				(13 (= newStrAsInteger -1))
				(11 (keyStr at: 1 42))
				(12 (keyStr at: 1 14848))
				(else 
					(keyStr format: {%d} (- loop 1))
				)
			)
			(self setScript: sButton 0 tone)
		)
	)
)

(instance btn0 of PushButton
	(properties
		z 1000
		loop 1
		keyValue 48
		scanValue 20992
		tone 10
	)
)

(instance btn1 of PushButton
	(properties
		z 1000
		loop 2
		keyValue 49
		scanValue 20224
		tone 11
	)
)

(instance btn2 of PushButton
	(properties
		z 1000
		loop 3
		keyValue 50
		scanValue 20480
		tone 12
	)
)

(instance btn3 of PushButton
	(properties
		z 1000
		loop 4
		keyValue 51
		scanValue 20736
		tone 13
	)
)

(instance btn4 of PushButton
	(properties
		z 1000
		loop 5
		keyValue 52
		scanValue 19200
		tone 14
	)
)

(instance btn5 of PushButton
	(properties
		z 1000
		loop 6
		keyValue 53
		scanValue 19456
		tone 15
	)
)

(instance btn6 of PushButton
	(properties
		z 1000
		loop 7
		keyValue 54
		scanValue 19712
		tone 16
	)
)

(instance btn7 of PushButton
	(properties
		z 1000
		loop 8
		keyValue 55
		scanValue 18176
		tone 17
	)
)

(instance btn8 of PushButton
	(properties
		z 1000
		loop 9
		keyValue 56
		scanValue 18432
		tone 18
	)
)

(instance btn9 of PushButton
	(properties
		z 1000
		loop 10
		keyValue 57
		scanValue 18688
		tone 19
	)
)

(instance btnCross of PushButton
	(properties
		z 1000
		loop 12
		keyValue 42
		tone 21
	)
)

(instance btnStar of PushButton
	(properties
		z 1000
		loop 11
		keyValue 35
		tone 20
	)
)

(instance hangUp of PushButton
	(properties
		z 1000
		loop 13
		keyValue 32
	)
)

(instance Class_610_17 of LarryTalker
	(properties)
)

(instance dialTone of Sound
	(properties)
)

(instance sfx of Sound
	(properties)
)

(instance sButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCel: 1)
				(UpdateScreenItem client)
				(if register
					(sfx number: register loop: 1 play: self)
				else
					(= cycles 2)
				)
			)
			(1
				(if (not newStr) (= newStr (Str new:)))
				(newStr cat: (client keyStr?))
				(if
					(and
						(not theNewStrAsInteger)
						(OneOf (newStr asInteger:) 72 73 74 75 76)
					)
					(= local1 1)
				)
				(client setCel: 0)
				(UpdateScreenItem client)
				(= cycles 2)
			)
			(2
				(sfx number: 0 dispose:)
				(= register 0)
				(if (== newStrAsInteger -1)
					(TTDialer dispose:)
				else
					(TTDialer cue: (client keyValue?) client)
				)
			)
		)
	)
)

(instance roomFeat of Feature
	(properties
		y 200
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 0 320 0 320 154 0 154
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
	)
)

(instance exitCue of cObj
	(properties)
	
	(method (cue)
		(= local0 1)
		(= newStrAsInteger -1)
		((ScriptID 0 11) dispose:)
		(TTDialer dispose:)
	)
)
