;;; Sierra Script 1.0 - (do not remove this comment)
(script# 922)
(include sci.sh)
(use Main)
(use Procs)
(use Plane)
(use String)
(use Array)
(use Print)
(use File)
(use Game)
(use Actor)
(use System)

(public
	newGame 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance buttonCast of Cast
	(properties)
)

(instance newGame of Room
	(properties)
	
	(method (init &tmp newStr)
		(= local2 0)
		(= local4 (IntArray new: 21))
		(= newStr (Str new:))
		(= local3
			(SaveGame 5 {SHIVER} (newStr data?) (local4 data?))
		)
		(newStr dispose:)
		(theGame handsOn:)
		(keyDownHandler addToFront: self)
		(= local1
			((Plane new:)
				picture: 922
				priority: 40
				init: 0 0 320 200
				addCast: buttonCast
				yourself:
			)
		)
		(buttonCast plane: local1)
		(A init:)
		(B init:)
		(C init:)
		(D init:)
		(E init:)
		(F init:)
		(G init:)
		(H init:)
		(I init:)
		(J init:)
		(K init:)
		(L init:)
		(M init:)
		(N init:)
		(O init:)
		(P init:)
		(Q init:)
		(R init:)
		(S init:)
		(T init:)
		(U init:)
		(V init:)
		(W init:)
		(X init:)
		(Y init:)
		(Z init:)
		(backspaceBtn init:)
		(playBtn init:)
		(cancelBtn init:)
		(autosaveBtn init:)
		(playBtn hide:)
		(super init:)
		(switch (Random 0 7)
			(0
				(proc951_9 -30505)
				(sounds play: -30505 0 92 0)
			)
			(1
				(proc951_9 -30503)
				(sounds play: -30503 0 92 0)
			)
			(2
				(proc951_9 -30502)
				(sounds play: -30502 0 92 0)
			)
			(3
				(proc951_9 -30500)
				(sounds play: -30500 0 92 0)
			)
			(4
				(proc951_9 -30499)
				(sounds play: -30499 0 92 0)
			)
			(5
				(proc951_9 -30498)
				(sounds play: -30498 0 92 0)
			)
			(6
				(proc951_9 -30497)
				(sounds play: -30497 0 92 0)
			)
			(7
				(proc951_9 -30496)
				(sounds play: -30496 0 92 0)
			)
		)
	)
	
	(method (dispose)
		(sounds stop: -30505)
		(sounds stop: -30503)
		(sounds stop: -30502)
		(sounds stop: -30500)
		(sounds stop: -30499)
		(sounds stop: -30498)
		(sounds stop: -30497)
		(sounds stop: -30496)
		(local4 dispose:)
		(keyDownHandler delete: self)
		(local1 dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(switch (event type?)
			(evKEYBOARD
				(cond 
					((OneOf (event message?) 65 97) (addLetter doit: 0) (event claimed: 1))
					((OneOf (event message?) 66 98) (addLetter doit: 1) (event claimed: 1))
					((OneOf (event message?) 67 99) (addLetter doit: 2) (event claimed: 1))
					((OneOf (event message?) 68 100) (addLetter doit: 3) (event claimed: 1))
					((OneOf (event message?) 69 101) (addLetter doit: 4) (event claimed: 1))
					((OneOf (event message?) 70 102) (addLetter doit: 5) (event claimed: 1))
					((OneOf (event message?) 71 103) (addLetter doit: 6) (event claimed: 1))
					((OneOf (event message?) 72 104) (addLetter doit: 7) (event claimed: 1))
					((OneOf (event message?) 73 105) (addLetter doit: 8) (event claimed: 1))
					((OneOf (event message?) 74 106) (addLetter doit: 9) (event claimed: 1))
					((OneOf (event message?) 75 107) (addLetter doit: 10) (event claimed: 1))
					((OneOf (event message?) 76 108) (addLetter doit: 11) (event claimed: 1))
					((OneOf (event message?) 77 109) (addLetter doit: 12) (event claimed: 1))
					((OneOf (event message?) 78 110) (addLetter doit: 13) (event claimed: 1))
					((OneOf (event message?) 79 111) (addLetter doit: 14) (event claimed: 1))
					((OneOf (event message?) 80 112) (addLetter doit: 15) (event claimed: 1))
					((OneOf (event message?) 81 113) (addLetter doit: 16) (event claimed: 1))
					((OneOf (event message?) 82 114) (addLetter doit: 17) (event claimed: 1))
					((OneOf (event message?) 83 115) (addLetter doit: 18) (event claimed: 1))
					((OneOf (event message?) 84 116) (addLetter doit: 19) (event claimed: 1))
					((OneOf (event message?) 85 117) (addLetter doit: 20) (event claimed: 1))
					((OneOf (event message?) 86 118) (addLetter doit: 21) (event claimed: 1))
					((OneOf (event message?) 87 119) (addLetter doit: 22) (event claimed: 1))
					((OneOf (event message?) 88 120) (addLetter doit: 23) (event claimed: 1))
					((OneOf (event message?) 89 121) (addLetter doit: 24) (event claimed: 1))
					((OneOf (event message?) 90 122) (addLetter doit: 25) (event claimed: 1))
					((== (event message?) JOY_UPLEFT) (removeLetter doit:))
					(
					(and (== (event message?) KEY_RETURN) (> local2 0)) (composeName doit:))
				)
			)
		)
		(event claimed: 1)
		(super handleEvent: event &rest)
	)
)

(class Alphabet of Prop
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
		view 922
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
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local1)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(theGame handsOff:)
			(event claimed: 1)
			(self setScript: sButton)
		)
	)
)

(instance A of Alphabet
	(properties
		x 66
		y 111
	)
)

(instance B of Alphabet
	(properties
		x 157
		y 132
		loop 1
	)
)

(instance C of Alphabet
	(properties
		x 121
		y 132
		loop 2
	)
)

(instance D of Alphabet
	(properties
		x 102
		y 111
		loop 3
	)
)

(instance E of Alphabet
	(properties
		x 93
		y 89
		loop 4
	)
)

(instance F of Alphabet
	(properties
		x 120
		y 111
		loop 5
	)
)

(instance G of Alphabet
	(properties
		x 138
		y 111
		loop 6
	)
)

(instance H of Alphabet
	(properties
		x 156
		y 111
		loop 7
	)
)

(instance I of Alphabet
	(properties
		x 183
		y 89
		loop 8
	)
)

(instance J of Alphabet
	(properties
		x 174
		y 111
		loop 9
	)
)

(instance K of Alphabet
	(properties
		x 192
		y 111
		loop 10
	)
)

(instance L of Alphabet
	(properties
		x 211
		y 111
		loop 11
	)
)

(instance M of Alphabet
	(properties
		x 193
		y 132
		loop 12
	)
)

(instance N of Alphabet
	(properties
		x 175
		y 132
		loop 13
	)
)

(instance O of Alphabet
	(properties
		x 201
		y 89
		loop 14
	)
)

(instance P of Alphabet
	(properties
		x 219
		y 89
		loop 15
	)
)

(instance Q of Alphabet
	(properties
		x 57
		y 89
		loop 16
	)
)

(instance R of Alphabet
	(properties
		x 111
		y 89
		loop 17
	)
)

(instance S of Alphabet
	(properties
		x 84
		y 111
		loop 18
	)
)

(instance T of Alphabet
	(properties
		x 129
		y 89
		loop 19
	)
)

(instance U of Alphabet
	(properties
		x 165
		y 89
		loop 20
	)
)

(instance V of Alphabet
	(properties
		x 139
		y 132
		loop 21
	)
)

(instance W of Alphabet
	(properties
		x 75
		y 89
		loop 22
	)
)

(instance X of Alphabet
	(properties
		x 103
		y 132
		loop 23
	)
)

(instance Y of Alphabet
	(properties
		x 147
		y 89
		loop 24
	)
)

(instance Z of Alphabet
	(properties
		x 85
		y 132
		loop 25
	)
)

(instance backspaceBtn of Alphabet
	(properties
		x 124
		y 153
		loop 26
	)
)

(instance playBtn of Alphabet
	(properties
		x 40
		y 184
		loop 27
	)
)

(instance cancelBtn of Alphabet
	(properties
		x 90
		y 184
		loop 28
	)
)

(instance autosaveBtn of Alphabet
	(properties
		x 151
		y 184
		loop 29
	)
	
	(method (init)
		(if (proc951_5 60)
			(self loop: 30)
		else
			(self loop: 29)
		)
		(super init: &rest)
	)
)

(instance letter1 of Actor
	(properties
		x 94
		y 79
		priority 240
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter2 of Actor
	(properties
		y 79
		priority 241
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter3 of Actor
	(properties
		y 79
		priority 242
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter4 of Actor
	(properties
		y 79
		priority 243
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter5 of Actor
	(properties
		y 79
		priority 244
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter6 of Actor
	(properties
		y 79
		priority 245
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter7 of Actor
	(properties
		y 79
		priority 246
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance letter8 of Actor
	(properties
		y 79
		priority 247
		fixPriority 1
		view 922
		loop -65
		cel 2
	)
	
	(method (init)
		(super init: buttonCast)
	)
)

(instance sButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 1)
				(UpdateScreenItem client)
				(switch (client loop?)
					(26
						(sounds play: 15032 0 90 self)
					)
					(27
						(sounds play: 15038 0 90 self)
					)
					(28
						(sounds play: 15026 0 90 self)
					)
					(29
						(sounds play: 15026 0 90 self)
					)
					(30
						(sounds play: 15026 0 90 self)
					)
					(else 
						(sounds play: 15032 0 90 self)
					)
				)
			)
			(1
				(theGame handsOn:)
				(if (and (< local2 8) (< (client loop?) 26))
					(addLetter doit: (client loop?))
				)
				(if (and (> local2 0) (== (client loop?) 26))
					(removeLetter doit:)
				)
				(if (== (client loop?) 27) (composeName doit:))
				(if (== (client loop?) 28) (curRoom newRoom: 910))
				(if
				(or (== (client loop?) 29) (== (client loop?) 30))
					(if (proc951_5 60)
						(proc951_4 60)
						(client loop: 29)
					else
						(proc951_3 60)
						(client loop: 30)
					)
				)
				(client cel: 0)
				(UpdateScreenItem client)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance addLetter of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (and (< local2 8) (< param1 27))
			(switch local2
				(0 (letter1 loop: param1 init:))
				(1
					(letter2
						loop: param1
						x: (+ (CelWide 922 (letter1 loop?) 2) (letter1 x?))
						init:
					)
				)
				(2
					(letter3
						loop: param1
						x: (+ (CelWide 922 (letter2 loop?) 2) (letter2 x?))
						init:
					)
				)
				(3
					(letter4
						loop: param1
						x: (+ (CelWide 922 (letter3 loop?) 2) (letter3 x?))
						init:
					)
				)
				(4
					(letter5
						loop: param1
						x: (+ (CelWide 922 (letter4 loop?) 2) (letter4 x?))
						init:
					)
				)
				(5
					(letter6
						loop: param1
						x: (+ (CelWide 922 (letter5 loop?) 2) (letter5 x?))
						init:
					)
				)
				(6
					(letter7
						loop: param1
						x: (+ (CelWide 922 (letter6 loop?) 2) (letter6 x?))
						init:
					)
				)
				(7
					(letter8
						loop: param1
						x: (+ (CelWide 922 (letter7 loop?) 2) (letter7 x?))
						init:
					)
				)
			)
			(++ local2)
			(playBtn show:)
		)
	)
)

(instance removeLetter of Code
	(properties)
	
	(method (doit)
		(if (> local2 0)
			(switch (- local2 1)
				(0 (letter1 loop: -65 hide:))
				(1 (letter2 loop: -65 hide:))
				(2 (letter3 loop: -65 hide:))
				(3 (letter4 loop: -65 hide:))
				(4 (letter5 loop: -65 hide:))
				(5 (letter6 loop: -65 hide:))
				(6 (letter7 loop: -65 hide:))
				(7 (letter8 loop: -65 hide:))
			)
			(if (== (-- local2) 0) (playBtn hide:))
		)
	)
)

(instance composeName of Code
	(properties)
	
	(method (doit &tmp temp0 newFile newStr temp3 temp4 temp5)
		(= temp4 1)
		(global528 at: 0 (+ (letter1 loop?) 65))
		(global528 at: 1 (+ (letter2 loop?) 65))
		(global528 at: 2 (+ (letter3 loop?) 65))
		(global528 at: 3 (+ (letter4 loop?) 65))
		(global528 at: 4 (+ (letter5 loop?) 65))
		(global528 at: 5 (+ (letter6 loop?) 65))
		(global528 at: 6 (+ (letter7 loop?) 65))
		(global528 at: 7 (+ (letter8 loop?) 65))
		(global528 at: 8 0)
		(= global539 local3)
		(= temp4 1)
		(= temp0 0)
		(while (< temp0 local3)
			(= newFile (File new:))
			(= newStr (Str new:))
			(= temp3
				(Str format: {%s%d.SG} curSaveDir (local4 at: temp0))
			)
			(newFile name: (temp3 data?))
			(newFile open: 1 readString: newStr 10 close:)
			(newStr strip:)
			(if
			(== (KString 7 (newStr data?) (global528 data?)) 0)
				(sounds stop: -30505)
				(sounds stop: -30503)
				(sounds stop: -30502)
				(sounds stop: -30500)
				(sounds stop: -30499)
				(sounds stop: -30498)
				(sounds stop: -30497)
				(sounds stop: -30496)
				(theGame setCursor: normalCursor)
				(switch
					(Print
						addBitmap: 928 7 0
						addButtonBM: 928 4 0 0 {} 130 40
						addButtonBM: 928 5 0 1 {} 80 40
						init:
					)
					(0
						(newFile dispose:)
						(temp3 dispose:)
						(newStr dispose:)
						(return)
					)
					(1
						(= global539 (local4 at: temp0))
						(= temp4 2)
					)
				)
			)
			(newFile dispose:)
			(temp3 dispose:)
			(newStr dispose:)
			(++ temp0)
		)
		(if (== temp4 1)
			(if (> (+ local3 1) 20)
				(sounds stop: -30505)
				(sounds stop: -30503)
				(sounds stop: -30502)
				(sounds stop: -30500)
				(sounds stop: -30499)
				(sounds stop: -30498)
				(sounds stop: -30497)
				(sounds stop: -30496)
				(theGame setCursor: normalCursor)
				(switch
					(Print
						addBitmap: 928 0 0
						addButtonBM: 928 2 0 0 {} 105 40
						init:
					)
					(0 (return))
				)
			)
			(if (not (FileIO 17 2 (KString 9 curSaveDir)))
				(sounds stop: -30505)
				(sounds stop: -30503)
				(sounds stop: -30502)
				(sounds stop: -30500)
				(sounds stop: -30499)
				(sounds stop: -30498)
				(sounds stop: -30497)
				(sounds stop: -30496)
				(theGame setCursor: normalCursor)
				(switch
					(Print
						addBitmap: 928 6 0
						addButtonBM: 928 2 0 0 {} 105 40
						init:
					)
					(0 (return))
				)
			)
			(= temp5 0)
			(repeat
				(= temp0 0)
				(while (< temp0 local3)
					(breakif (== temp5 (local4 at: temp0)))
					(++ temp0)
				)
				(if (== temp0 local3) (break))
				(++ temp5)
			)
			(= global539 temp5)
		)
		(curRoom newRoom: 930)
	)
)
