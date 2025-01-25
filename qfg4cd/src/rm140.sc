;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include sci.sh)
(use Main)
(use GloryRm)
(use ConfirmPrompt)
(use DText)
(use Plane)
(use String)
(use Array)
(use IconBar)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
	local1
	userNameSize_2
	local3 =  100
	local4
	local5
	newCast
	[local7 2]
	local9
	local10
	newStr
)
(procedure (localproc_2303 &tmp temp0)
	(switch heroType
		(1
			((inventory at: 17) state: 0)
			(= [egoStats 0] 200)
			(= [egoStats 1] 250)
			(= [egoStats 2] 200)
			(= [egoStats 3] 200)
			(= [egoStats 4] 200)
			(= [egoStats 12] 250)
			(= [egoStats 13] 225)
			(= [egoStats 14] 200)
			(= [egoStats 5] 200)
			(= [egoStats 6] 0)
			(= [egoStats 7] 200)
			(= [egoStats 10] 0)
			(= [egoStats 8] 0)
			(= [egoStats 9] 0)
			(= [egoStats 11] 0)
			(= [egoStats 15] 0)
			(= [egoStats 25] 200)
			(= [egoStats 23] 200)
			(= [egoStats 21] 200)
			(= [egoStats 27] 200)
			(= [egoStats 26] 250)
			(= [egoStats 28] 150)
			(= [egoStats 31] 150)
			(= [egoStats 29] 200)
			(= [egoStats 33] 150)
			(= [egoStats 32] 0)
			(= [egoStats 20] 200)
			(= [egoStats 30] 150)
			(= [egoStats 22] 200)
			(= [egoStats 24] 250)
		)
		(2
			((inventory at: 17) state: 0)
			(= [egoStats 0] 200)
			(= [egoStats 1] 200)
			(= [egoStats 2] 250)
			(= [egoStats 3] 200)
			(= [egoStats 4] 200)
			(= [egoStats 12] 0)
			(= [egoStats 13] 200)
			(= [egoStats 14] 150)
			(= [egoStats 5] 200)
			(= [egoStats 6] 0)
			(= [egoStats 7] 225)
			(= [egoStats 10] 200)
			(= [egoStats 8] 200)
			(= [egoStats 9] 200)
			(= [egoStats 11] 200)
			(= [egoStats 15] 150)
			(= [egoStats 25] 0)
			(= [egoStats 23] 0)
			(= [egoStats 21] 0)
			(= [egoStats 27] 0)
			(= [egoStats 26] 0)
			(= [egoStats 28] 0)
			(= [egoStats 31] 0)
			(= [egoStats 29] 0)
			(= [egoStats 33] 0)
			(= [egoStats 32] 0)
			(= [egoStats 20] 0)
			(= [egoStats 30] 0)
			(= [egoStats 22] 0)
			(= [egoStats 24] 0)
		)
		(else 
			((inventory at: 17) state: 1)
			(= [egoStats 0] 250)
			(= [egoStats 1] 200)
			(= [egoStats 2] 225)
			(= [egoStats 3] 225)
			(= [egoStats 4] 200)
			(= [egoStats 12] 0)
			(= [egoStats 13] 200)
			(= [egoStats 14] 200)
			(= [egoStats 5] 250)
			(= [egoStats 6] 250)
			(= [egoStats 7] 200)
			(= [egoStats 10] 200)
			(= [egoStats 8] 0)
			(= [egoStats 9] 0)
			(= [egoStats 11] 0)
			(= [egoStats 15] 0)
			(= [egoStats 25] 0)
			(= [egoStats 23] 0)
			(= [egoStats 21] 0)
			(= [egoStats 27] 0)
			(= [egoStats 26] 0)
			(= [egoStats 28] 0)
			(= [egoStats 31] 0)
			(= [egoStats 29] 0)
			(= [egoStats 33] 0)
			(= [egoStats 32] 0)
			(= [egoStats 20] 0)
			(= [egoStats 30] 0)
			(= [egoStats 22] 0)
			(= [egoStats 24] 0)
		)
	)
	(= [egoStats 17]
		(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
	)
	(= [egoStats 18] (/ (+ [egoStats 2] [egoStats 3]) 2))
	(= [egoStats 19]
		(if [egoStats 12]
			(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
		else
			0
		)
	)
	(ego get: 0 get: 17)
	(= [egoStats 34] 0)
	(= [egoStats 35] 0)
	(= [egoStats 36] 0)
	(= [egoStats 37] 0)
	(= [egoStats 38] 0)
	(= [egoStats 39] 0)
	(= [egoStats 40] 0)
	(= [egoStats 41] 0)
	(= paladinStat 0)
	(= paladinPoints [ego 14])
)

(procedure (localproc_26b8)
	(switch heroType
		(1
			((inventory at: 17) state: 0)
			(if (< [egoStats 0] 200) (= [egoStats 0] 200))
			(if (< [egoStats 1] 250) (= [egoStats 1] 250))
			(if (< [egoStats 2] 200) (= [egoStats 2] 200))
			(if (< [egoStats 3] 200) (= [egoStats 3] 200))
			(if (< [egoStats 4] 200) (= [egoStats 4] 200))
			(if (< [egoStats 12] 250) (= [egoStats 12] 250))
			(if (< [egoStats 13] 225) (= [egoStats 13] 225))
			(if (< [egoStats 14] 200) (= [egoStats 14] 200))
			(if (< [egoStats 5] 200) (= [egoStats 5] 200))
			(if (< [egoStats 7] 200) (= [egoStats 7] 200))
			(if (< [egoStats 25] 200) (= [egoStats 25] 200))
			(if (< [egoStats 23] 200) (= [egoStats 23] 200))
			(if (< [egoStats 21] 200) (= [egoStats 21] 200))
			(if (< [egoStats 27] 200) (= [egoStats 27] 200))
			(if (< [egoStats 26] 250) (= [egoStats 26] 250))
			(if (< [egoStats 31] 150) (= [egoStats 31] 150))
			(if (< [egoStats 29] 200) (= [egoStats 29] 200))
			(if (< [egoStats 33] 150) (= [egoStats 33] 150))
			(if (< [egoStats 32] 0) (= [egoStats 32] 0))
			(if (< [egoStats 20] 200) (= [egoStats 20] 200))
			(if (< [egoStats 30] 150) (= [egoStats 30] 150))
			(if (< [egoStats 22] 200) (= [egoStats 22] 200))
			(if (< [egoStats 24] 250) (= [egoStats 24] 250))
		)
		(2
			((inventory at: 17) state: 0)
			(if (< [egoStats 0] 200) (= [egoStats 0] 200))
			(if (< [egoStats 1] 200) (= [egoStats 1] 200))
			(if (< [egoStats 2] 250) (= [egoStats 2] 250))
			(if (< [egoStats 3] 200) (= [egoStats 3] 200))
			(if (< [egoStats 4] 200) (= [egoStats 4] 200))
			(if (< [egoStats 13] 200) (= [egoStats 13] 200))
			(if (< [egoStats 14] 150) (= [egoStats 14] 150))
			(if (< [egoStats 5] 200) (= [egoStats 5] 200))
			(if (< [egoStats 7] 225) (= [egoStats 7] 225))
			(if (< [egoStats 10] 200) (= [egoStats 10] 200))
			(if (< [egoStats 8] 200) (= [egoStats 8] 200))
			(if (< [egoStats 9] 200) (= [egoStats 9] 200))
			(if (< [egoStats 11] 200) (= [egoStats 11] 200))
			(if (< [egoStats 15] 150) (= [egoStats 15] 150))
		)
		(else 
			((inventory at: 17) state: 1)
			(if (< [egoStats 0] 250) (= [egoStats 0] 250))
			(if (< [egoStats 1] 200) (= [egoStats 1] 200))
			(if (< [egoStats 2] 225) (= [egoStats 2] 225))
			(if (< [egoStats 3] 225) (= [egoStats 3] 225))
			(if (< [egoStats 4] 200) (= [egoStats 4] 200))
			(if (< [egoStats 13] 200) (= [egoStats 13] 200))
			(if (< [egoStats 14] 200) (= [egoStats 14] 200))
			(if (< [egoStats 5] 250) (= [egoStats 5] 250))
			(if (< [egoStats 6] 250) (= [egoStats 6] 250))
			(if (< [egoStats 7] 200) (= [egoStats 7] 200))
			(if (< [egoStats 10] 200) (= [egoStats 10] 200))
		)
	)
	(= [egoStats 17]
		(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
	)
	(= [egoStats 18] (/ (+ [egoStats 2] [egoStats 3]) 2))
	(= [egoStats 19]
		(if [egoStats 12]
			(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
		else
			0
		)
	)
	(ego get: 0 get: 17)
	(= paladinPoints [egoStats 14])
	(if (== heroType 3) (= paladinStat 1))
)

(instance rm140 of GloryRm
	(properties
		picture 140
	)
	
	(method (init)
		(Bclr 6)
		(Load rsSCRIPT 91)
		(Load rsHEAP 91)
		(= local0
			(IntArray with: 53 16 38 22 48 35 35 63 111 77 -32768)
		)
		(= newStr (Str new:))
		(if (== prevRoomNum 54)
			(theGame setCursor: (normalCursor view: 999 yourself:))
			(= picture 130)
			(Bset 50)
			(Bset 51)
			(super init: &rest)
			(charInitScreen init: show: dispose:)
		else
			(Palette palSET_FLAG 0 255 0)
			(LoadMany 128 140 141 142 143 147)
			(myChar init:)
			(fire1 setCycle: Fwd init:)
			(fire2 setCycle: Fwd init:)
			(door2 init:)
			(door3 init:)
			(bat1 init: hide:)
			(bat2 init: hide:)
			(bat3 init: hide:)
			(super init: &rest)
			(theGame handsOff:)
			(User canInput: 1)
			(self setScript: showOff)
		)
	)
	
	(method (dispose)
		(Bclr 50)
		(Bclr 51)
		(newStr dispose:)
		(local0 dispose:)
		(super dispose:)
	)
	
	(method (doVerb)
	)
)

(instance showOff of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (<= local1 100)
			(Palette palSET_FLAG 0 255 (= local1 (+ local1 1)))
			(if (== local1 100)
				(RemapColors RemapByPct 254 60)
				(= local1 101)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: (normalCursor view: 999 yourself:))
				(Bset 50)
				(Bset 51)
				(RemapColors 0)
				(= register 30)
				(torchFX number: 965 setLoop: -1 play:)
				(= seconds 1)
			)
			(1
				(bat2
					signal: (| (bat2 signal?) $0001)
					x: 80
					y: 39
					show:
					setCycle: Fwd
					setLoop: 0
				)
				(bat3
					signal: (| (bat3 signal?) $0001)
					x: 80
					y: 39
					show:
					setCycle: Fwd
					setLoop: 0
				)
				(bat1
					signal: (| (bat1 signal?) $0001)
					x: 80
					y: 39
					show:
					setCycle: Fwd
					setLoop: 0
					setMotion: MoveTo 53 16 self
				)
				(soundFX number: 132 setLoop: 1 play:)
			)
			(2
				(self setScript: batFly self)
			)
			(3
				(bat1 dispose:)
				(bat2 dispose:)
				(bat3 dispose:)
				(myChar
					signal: (| (myChar signal?) $0001)
					cycleSpeed: 1
					setCycle: End self
				)
				(soundFX number: 141 setLoop: 1 play:)
			)
			(4
				(fighterSign init:)
				(door1
					view: 142
					loop: 0
					cel: 6
					x: (myChar x?)
					y: (myChar y?)
					init:
				)
				(myChar hide:)
				(= cycles 2)
			)
			(5
				(myChar
					view: 141
					setLoop: 0
					show:
					cel: 0
					x: (door2 x?)
					y: (door2 y?)
				)
				(= seconds 4)
			)
			(6
				(= register 2)
				(myChar cycleSpeed: 6 setCycle: CT 12 1 self)
			)
			(7
				(soundFX number: 142 setLoop: 1 play:)
				(door2 hide:)
				(myChar setCycle: End self)
			)
			(8
				(myChar setLoop: 1 cel: 0 setCycle: End self)
			)
			(9
				(wizardSign init:)
				(door2 view: 141 loop: 1 cel: 9 show:)
				(door3 hide:)
				(myChar
					view: 143
					setLoop: 0
					cycleSpeed: 10
					cel: 0
					x: (door3 x?)
					y: (door3 y?)
				)
				(= seconds 2)
			)
			(10
				(= register 3)
				(soundFX number: 143 setLoop: 1 play:)
				(myChar setCycle: CT 3 1 self)
			)
			(11 (= ticks 30))
			(12
				(myChar setCycle: CT 6 1 self)
			)
			(13 (= ticks 30))
			(14
				(myChar setCycle: CT 10 1 self)
			)
			(15 (= ticks 30))
			(16 (myChar setCycle: End self))
			(17
				(robberSign init:)
				(door3 loop: 0 cel: 13 show:)
				(= cycles 2)
			)
			(18
				(myChar dispose:)
				(= cycles 1)
			)
			(19
				(bigBat
					init:
					x: 159
					y: 300
					setMotion: MoveTo 159 185 self
				)
				(bigBat2 init: x: 160 y: 300 setMotion: MoveTo 160 185)
			)
			(20
				(soundFX number: 132 setLoop: 1 play:)
				(bat2
					signal: (| (bat2 signal?) $0001)
					x: 80
					y: 39
					init:
					setCycle: Fwd
					setLoop: 0
				)
				(bat3
					signal: (| (bat3 signal?) $0001)
					x: 80
					y: 39
					init:
					setCycle: Fwd
					setLoop: 0
				)
				(bat1
					signal: (| (bat1 signal?) $0001)
					x: 80
					y: 39
					init:
					setCycle: Fwd
					setLoop: 0
					setMotion: MoveTo 53 16 self
				)
			)
			(21
				(self setScript: batFly self)
				(soundFX play:)
			)
			(22
				(soundFX stop:)
				(= seconds (Random 4 7))
			)
			(23 (self changeState: 20))
		)
	)
)

(instance bigBat of Actor
	(properties
		view 146
		signal $4001
	)
	
	(method (doit)
		(super doit: &rest)
		(if (<= y 186)
			(if (== y 185)
				(= y 186)
				(= z 1)
			else
				(= y 185)
				(= z 0)
			)
		)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bigBat2 of Actor
	(properties
		view 146
		cel 1
		signal $4001
	)
	
	(method (doit)
		(super doit: &rest)
		(if (<= y 186)
			(if (== y 185)
				(= y 186)
				(= z 1)
			else
				(= y 185)
				(= z 0)
			)
		)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance batFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 5 22 self
				)
			)
			(1
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 61 25 self
				)
			)
			(2
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 35 33 self
				)
			)
			(3
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 10 8 self
				)
			)
			(4
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 57 14 self
				)
			)
			(5
				(bat1
					moveSpeed: (Random 4 7)
					setMotion: MoveTo 80 39 self
				)
			)
			(6 (self dispose:))
		)
	)
)

(instance bat1 of Actor
	(properties
		view 912
		signal $6000
		illegalBits $0000
		xStep 5
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (setMotion mType &tmp temp0)
		(super setMotion: mType &rest)
		(if mType
			(bat3
				moveSpeed: (= temp0 (Random 4 7))
				cycleSpeed:
					(bat2
						moveSpeed: (= temp0 (Random 4 7))
						cycleSpeed: temp0
						setMotion:
							MoveTo
							(+ (mover x?) (Random 5 15))
							(+ (mover y?) (Random 5 15))
					)
				setMotion:
					MoveTo
					(+ (mover x?) (Random 5 10))
					(+ (mover y?) (Random 5 10))
			)
		)
	)
)

(instance bat2 of Actor
	(properties
		view 912
		signal $6000
		illegalBits $0000
		xStep 5
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance bat3 of Actor
	(properties
		view 912
		signal $6000
		illegalBits $0000
		xStep 5
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance myChar of Prop
	(properties
		x 100
		y 142
		view 142
		signal $4000
	)
	
	(method (doVerb)
		(switch (showOff register?)
			(1 (= heroType 0))
			(2 (= heroType 1))
			(3 (= heroType 2))
		)
		(curRoom setScript: sDoScreen)
	)
)

(instance sDoScreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch heroType
					(0 (fighterSign setCel: 1))
					(1 (wizardSign setCel: 1))
					(2 (robberSign setCel: 1))
				)
				(= cycles 1)
			)
			(1
				(charInitScreen init: show: dispose:)
			)
		)
	)
)

(instance fire1 of Prop
	(properties
		x 144
		y 81
		view 140
		cycleSpeed 10
		detailLevel 3
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance fire2 of Prop
	(properties
		x 231
		y 84
		view 140
		loop 1
		cycleSpeed 10
		detailLevel 3
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance door1 of View
	(properties
		x 101
		y 144
		view 142
		loop 2
		signal $4000
	)
	
	(method (doVerb)
		(= heroType 0)
		(curRoom setScript: sDoScreen)
	)
)

(instance door2 of View
	(properties
		x 189
		y 137
		view 140
		loop 6
	)
	
	(method (doVerb)
		(= heroType 1)
		(curRoom setScript: sDoScreen)
	)
)

(instance door3 of View
	(properties
		x 243
		y 132
		view 143
	)
	
	(method (doVerb)
		(= heroType 2)
		(curRoom setScript: sDoScreen)
	)
)

(instance fighterSign of View
	(properties
		x 77
		y 139
		view 147
		cel 2
		signal $4000
	)
	
	(method (doVerb)
		(= heroType 0)
		(curRoom setScript: sDoScreen)
	)
)

(instance wizardSign of View
	(properties
		x 156
		y 143
		view 147
		loop 1
		cel 2
		signal $4000
	)
	
	(method (doVerb)
		(= heroType 1)
		(curRoom setScript: sDoScreen)
	)
)

(instance robberSign of View
	(properties
		x 261
		y 140
		view 147
		loop 2
		cel 2
		signal $4000
	)
	
	(method (doVerb)
		(= heroType 2)
		(curRoom setScript: sDoScreen)
	)
)

(class SelectIcon of Obj
	(properties
		scratch 0
		text1 0
		text2 0
		nameBack 0
		nameLow 0
		nameHigh 0
		valueBack 0
		valueLow 0
		valueHigh 0
		case 0
		stat 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		signal $0000
		state $0000
		maskView 0
		maskLoop 0
	)
	
	(method (init)
		(= text1 (Str new:))
		(= text2 (Str new:))
		(= nameBack (myDText new:))
		(= nameHigh (myDText new:))
		(= nameLow (myDText new:))
		(= valueBack (myDText new:))
		(= valueHigh (myDText new:))
		(= valueLow (myDText new:))
		(= maskView [egoStats stat])
		(= maskLoop 0)
		(Message msgGET 140 1 0 case 1 (text1 data?))
		(nameBack
			posn: (- nsLeft 83) nsTop
			text: (text1 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			setSize:
			setPri: 242
			init: newCast
		)
		(nameHigh
			posn: (- nsLeft 84) nsTop
			text: (text1 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			setPri: 0
			setSize:
			init: newCast
		)
		(nameLow
			posn: (- nsLeft 84) nsTop
			text: (text1 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			setSize:
			setPri: 243
			init: newCast
		)
		(if (== stat -1)
			(text2 format: {%d} local3)
		else
			(text2 format: {%d} [egoStats stat])
		)
		(valueBack
			posn: nsLeft nsTop
			text: (text2 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: 242
			init: newCast
		)
		(valueHigh
			posn: (- nsLeft 1) nsTop
			text: (text2 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: 0
			init: newCast
		)
		(valueLow
			posn: (- nsLeft 1) nsTop
			text: (text2 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: 243
			init: newCast
		)
	)
	
	(method (dispose)
		(text1 dispose:)
		(if text2 (text2 dispose:))
		(super dispose:)
	)
	
	(method (show)
		(= state (| state $0020))
		(= nsBottom (+ nsTop 8))
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< (- nsLeft 83) (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select param1 &tmp temp0 temp1)
		(= temp1 0)
		(switch param1
			(1
				(cond 
					((not [egoStats stat])
						(if (== local3 100)
							(= [egoStats stat] 100)
							(= local3 0)
							(= temp1 (= maskLoop 1))
						)
					)
					((< local3 5)
						(= [egoStats stat] (+ [egoStats stat] local3))
						(= local3 0)
						(= temp1 1)
					)
					(local3
						(= [egoStats stat] (+ [egoStats stat] 5))
						(= local3 (- local3 5))
						(= temp1 1)
					)
				)
			)
			(0
				(cond 
					((and maskLoop [egoStats stat])
						(= [egoStats stat] 0)
						(= local3 100)
						(= maskLoop 0)
						(= temp1 1)
					)
					((< [egoStats stat] 5)
						(= local3 (+ local3 [egoStats stat]))
						(= [egoStats stat] 0)
						(= temp1 1)
					)
					(
					(and [egoStats stat] (> [egoStats stat] maskView))
						(if (< (= temp0 (- [egoStats stat] maskView)) 5)
							(= [egoStats stat] (- [egoStats stat] temp0))
							(= local3 (+ local3 temp0))
						else
							(= [egoStats stat] (- [egoStats stat] 5))
							(= local3 (+ local3 5))
						)
						(= temp1 1)
					)
				)
			)
			(3
				(cond 
					((not [egoStats stat])
						(if (== local3 100)
							(= [egoStats stat] 100)
							(= local3 0)
							(= temp1 (= maskLoop 1))
						)
					)
					(local3 (++ [egoStats stat]) (-- local3) (= temp1 1))
				)
			)
			(2
				(cond 
					((and maskLoop [egoStats stat])
						(= [egoStats stat] 0)
						(= local3 100)
						(= maskLoop 0)
						(= temp1 1)
					)
					(
					(and [egoStats stat] (> [egoStats stat] maskView)) (-- [egoStats stat]) (++ local3) (= temp1 1))
				)
			)
		)
		(if temp1
			(self update: 1)
			(pointIcon update: 0)
			(switch stat
				(1
					(= [egoStats 19]
						(if [egoStats 12]
							(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
						else
							0
						)
					)
					(manaIcon update: 0)
				)
				(12
					(= [egoStats 19]
						(if [egoStats 12]
							(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
						else
							0
						)
					)
					(manaIcon update: 0)
				)
				(3
					(= [egoStats 17]
						(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
					)
					(= [egoStats 18] (/ (+ [egoStats 2] [egoStats 3]) 2))
					(healthIcon update: 0)
					(staminaIcon update: 0)
				)
				(0
					(= [egoStats 17]
						(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
					)
					(healthIcon update: 0)
				)
				(2
					(= [egoStats 18] (/ (+ [egoStats 2] [egoStats 3]) 2))
					(staminaIcon update: 0)
				)
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(nameHigh priority: 244)
			(valueHigh priority: 244)
		else
			(nameHigh priority: 0)
			(valueHigh priority: 0)
		)
		(UpdateScreenItem nameHigh)
		(UpdateScreenItem valueHigh)
		(FrameOut)
	)
	
	(method (update param1 &tmp theValueBack theValueHigh theValueLow)
		(= theValueBack valueBack)
		(= theValueHigh valueHigh)
		(= theValueLow valueLow)
		(theValueBack priority: 100)
		(theValueHigh priority: 100)
		(theValueLow priority: 0)
		(if text2 (text2 dispose:))
		(= text2 (Str new:))
		(if (== stat -1)
			(text2 format: {%d} local3)
		else
			(text2 format: {%d} [egoStats stat])
		)
		(= valueBack (myDText new:))
		(= valueHigh (myDText new:))
		(= valueLow (myDText new:))
		(valueBack
			posn: nsLeft nsTop
			text: (text2 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: 242
			init: newCast
		)
		(valueHigh
			posn: (- nsLeft 1) nsTop
			text: (text2 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: (if param1 244 else 0)
			init: newCast
		)
		(valueLow
			posn: (- nsLeft 1) nsTop
			text: (text2 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			mode: -1
			setSize:
			setPri: 243
			init: newCast
		)
		(UpdateScreenItem valueBack)
		(UpdateScreenItem valueHigh)
		(UpdateScreenItem valueLow)
		(theValueBack dispose:)
		(theValueHigh dispose:)
		(theValueLow dispose:)
		(FrameOut)
	)
)

(instance strengthIcon of SelectIcon
	(properties
		case 6
		nsLeft 93
		nsTop 34
		nsRight 113
	)
)

(instance intgenIcon of SelectIcon
	(properties
		case 7
		stat 1
		nsLeft 93
		nsTop 45
		nsRight 113
	)
)

(instance agilityIcon of SelectIcon
	(properties
		case 8
		stat 2
		nsLeft 93
		nsTop 56
		nsRight 113
	)
)

(instance vitalIcon of SelectIcon
	(properties
		case 9
		stat 3
		nsLeft 93
		nsTop 67
		nsRight 113
	)
)

(instance luckIcon of SelectIcon
	(properties
		case 10
		stat 4
		nsLeft 93
		nsTop 78
		nsRight 113
	)
)

(instance magicIcon of SelectIcon
	(properties
		case 11
		stat 12
		nsLeft 93
		nsTop 89
		nsRight 113
	)
)

(instance commIcon of SelectIcon
	(properties
		case 13
		stat 13
		nsLeft 93
		nsTop 100
		nsRight 113
	)
)

(instance weaponIcon of SelectIcon
	(properties
		case 14
		stat 5
		nsLeft 286
		nsTop 35
		nsRight 313
	)
)

(instance parryIcon of SelectIcon
	(properties
		case 15
		stat 6
		nsLeft 286
		nsTop 44
		nsRight 313
	)
)

(instance dodgeIcon of SelectIcon
	(properties
		case 16
		stat 7
		nsLeft 286
		nsTop 54
		nsRight 313
	)
)

(instance stealthIcon of SelectIcon
	(properties
		case 17
		stat 8
		nsLeft 286
		nsTop 64
		nsRight 313
	)
)

(instance pickIcon of SelectIcon
	(properties
		case 18
		stat 9
		nsLeft 286
		nsTop 74
		nsRight 313
	)
)

(instance throwIcon of SelectIcon
	(properties
		case 19
		stat 10
		nsLeft 286
		nsTop 84
		nsRight 313
	)
)

(instance climbIcon of SelectIcon
	(properties
		case 20
		stat 11
		nsLeft 286
		nsTop 94
		nsRight 313
	)
)

(instance acrobIcon of SelectIcon
	(properties
		case 21
		stat 15
		nsLeft 286
		nsTop 104
		nsRight 313
	)
)

(instance honorIcon of SelectIcon
	(properties
		case 22
		stat 14
		nsLeft 286
		nsTop 114
		nsRight 313
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance pointIcon of SelectIcon
	(properties
		case 24
		stat -1
		nsLeft 93
		nsTop 137
		nsRight 113
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance healthIcon of SelectIcon
	(properties
		case 29
		stat 17
		nsLeft 93
		nsTop 146
		nsRight 113
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance staminaIcon of SelectIcon
	(properties
		case 25
		stat 18
		nsLeft 286
		nsTop 137
		nsRight 313
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance manaIcon of SelectIcon
	(properties
		case 26
		stat 19
		nsLeft 286
		nsTop 146
		nsRight 313
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance startIcon of SelectIcon
	(properties
		case 27
		nsLeft 5
		nsTop 172
		nsRight 70
	)
	
	(method (init)
		(= text1 (Str new:))
		(= nameBack (myDText new:))
		(= nameHigh (myDText new:))
		(= nameLow (myDText new:))
		(Message msgGET 140 1 0 case 1 (text1 data?))
		(nameBack
			posn: nsLeft nsTop
			text: (text1 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			setSize:
			setPri: 242
			init: newCast
		)
		(nameHigh
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			setPri: 0
			setSize:
			init: newCast
		)
		(nameLow
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			setSize:
			setPri: 243
			init: newCast
		)
	)
	
	(method (show)
		(= nsBottom (+ nsTop 8))
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(not (& signal $0004))
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select)
		(return
			(if (and local3 (ConfirmPrompt 140 1 6 1 1))
				(DisposeScript 91)
				(return 0)
			else
				(DisposeScript 91)
				(= local9 1)
				(charInitScreen
					state: (& (charInitScreen state?) $ffdf)
				)
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(nameHigh priority: 244)
		else
			(nameHigh priority: 0)
		)
		(UpdateScreenItem nameHigh)
		(FrameOut)
	)
)

(instance cancelIcon of SelectIcon
	(properties
		case 4
		nsLeft 250
		nsTop 172
		nsRight 310
	)
	
	(method (init)
		(= text1 (Str new:))
		(= nameBack (myDText new:))
		(= nameHigh (myDText new:))
		(= nameLow (myDText new:))
		(Message msgGET 140 1 0 case 1 (text1 data?))
		(nameBack
			posn: nsLeft nsTop
			text: (text1 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			setSize:
			setPri: 242
			init: newCast
		)
		(nameHigh
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			setPri: 0
			setSize:
			init: newCast
		)
		(nameLow
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			setSize:
			setPri: 243
			init: newCast
		)
	)
	
	(method (show)
		(= nsBottom (+ nsTop 8))
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select)
		(return
			(if (not (ConfirmPrompt 140 1 6 4 1))
				(DisposeScript 91)
				(return 0)
			else
				(DisposeScript 91)
				(= local9 0)
				(ego drop: 17)
				(charInitScreen
					state: (& (charInitScreen state?) $ffdf)
				)
			)
		)
	)
	
	(method (highlight param1)
		(if param1
			(nameHigh priority: 244)
		else
			(nameHigh priority: 0)
		)
		(UpdateScreenItem nameHigh)
		(FrameOut)
	)
)

(instance namePlate of SelectIcon
	(properties
		case 28
		nsLeft 81
		nsTop 172
		nsRight 235
	)
	
	(method (init)
		(= text1 (Str new:))
		(= nameBack (myDText new:))
		(= nameHigh (myDText new:))
		(= nameLow (myDText new:))
		(= valueHigh (myDText new:))
		(= valueLow (myDText new:))
		(Message msgGET 140 1 0 case 1 (text1 data?))
		(nameBack
			posn: nsLeft nsTop
			text: (text1 data?)
			font: 300
			fore: 174
			back: 254
			skip: 254
			setSize:
			setPri: 242
			init: newCast
		)
		(nameHigh
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 106
			back: 254
			skip: 254
			setPri: 0
			setSize:
			init: newCast
		)
		(nameLow
			posn: (- nsLeft 1) nsTop
			text: (text1 data?)
			font: 300
			fore: 227
			back: 254
			skip: 254
			setSize:
			setPri: 243
			init: newCast
		)
		(valueHigh
			text: (userName data?)
			posn: (+ nsLeft 47) (+ nsTop 2)
			fore: 106
			mode: 0
			font: smallFont
			setPri: 0
			setSize:
			init: newCast
		)
		(valueLow
			text: (userName data?)
			posn: (+ nsLeft 47) (+ nsTop 2)
			fore: 227
			mode: 0
			font: smallFont
			setPri: 243
			setSize:
			init: newCast
		)
	)
	
	(method (show)
		(= nsBottom (+ nsTop 8))
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< (- nsTop 2) (theObjOrX y?))
				)
				(< (theObjOrX y?) (+ nsBottom 4))
			else
				0
			)
		)
	)
	
	(method (select param1 &tmp temp0 theValueHigh theValueLow)
		(= temp0 (IntArray with: 0 0 0 0))
		(TextSize (temp0 data?) (userName data?) smallFont 0)
		(cond 
			((== param1 8)
				(if userNameSize_2
					(userName at: (-- userNameSize_2) 0)
					(= theValueHigh valueHigh)
					(= theValueLow valueLow)
					(theValueHigh priority: 100)
					(theValueLow priority: 0)
					(= valueHigh (myDText new:))
					(= valueLow (myDText new:))
					(valueHigh
						text: (userName data?)
						posn: (+ nsLeft 47) (+ nsTop 2)
						fore: 106
						mode: 0
						font: smallFont
						setPri: 244
						setSize:
						init: newCast
					)
					(valueLow
						text: (userName data?)
						posn: (+ nsLeft 47) (+ nsTop 2)
						fore: 227
						mode: 0
						font: smallFont
						setPri: 243
						setSize:
						init: newCast
					)
					(UpdateScreenItem valueHigh)
					(UpdateScreenItem valueLow)
					(theValueHigh dispose:)
					(theValueLow dispose:)
					(FrameOut)
				)
			)
			((>= userNameSize_2 40) (return 1))
			((<= (temp0 at: 2) 105)
				(userName at: userNameSize_2 param1)
				(userName at: (++ userNameSize_2) 0)
				(= theValueHigh valueHigh)
				(= theValueLow valueLow)
				(theValueHigh priority: 100)
				(theValueLow priority: 0)
				(= valueHigh (myDText new:))
				(= valueLow (myDText new:))
				(valueHigh
					text: (userName data?)
					posn: (+ nsLeft 47) (+ nsTop 2)
					fore: 106
					mode: 0
					font: smallFont
					setPri: 244
					setSize: 172
					init: newCast
				)
				(valueLow
					text: (userName data?)
					posn: (+ nsLeft 47) (+ nsTop 2)
					fore: 227
					mode: 0
					font: smallFont
					setPri: 243
					setSize: 172
					init: newCast
				)
				(UpdateScreenItem valueHigh)
				(UpdateScreenItem valueLow)
				(theValueHigh dispose:)
				(theValueLow dispose:)
				(FrameOut)
			)
			(else (return 1))
		)
		(return (temp0 dispose:))
	)
	
	(method (highlight param1)
		(if param1
			(nameHigh priority: 244)
			(valueHigh priority: 244)
		else
			(nameHigh priority: 0)
			(valueHigh priority: 0)
		)
		(UpdateScreenItem nameHigh)
		(UpdateScreenItem valueHigh)
		(FrameOut)
	)
)

(instance charInitScreen of IconBar
	(properties
		state $0000
	)
	
	(method (init &tmp temp0 temp1 userNameSize)
		(torchFX stop:)
		(cast eachElementDo: #dispose)
		(= newCast (Cast new:))
		(= plane (Plane new:))
		(plane
			priority: (+ (GetHighPlanePri) 1)
			setRect: 0 10 319 199
			init:
			drawPic: 170 0
			addCast: newCast
		)
		(Palette palSET_FLAG 0 255 100)
		(= temp1 (IntArray new: 4))
		(= local4 (IntArray with: 132 143 134))
		(= local5 (IntArray with: 50 52 62))
		(if (!= prevRoomNum 54)
			(localproc_2303)
		else
			(localproc_26b8)
		)
		(if (== prevRoomNum 54)
			(TextSize (temp1 data?) (userName data?) smallFont 0)
			(while (> (temp1 at: 2) 105)
				(= userNameSize (userName size:))
				(userName at: (- userNameSize 1) 0)
				(TextSize (temp1 data?) (userName data?) smallFont 0)
			)
			(= userNameSize_2 (userName size:))
		else
			(userName copy: {})
			(= userNameSize_2 (userName size:))
		)
		(temp1 dispose:)
		(self
			add:
				strengthIcon
				intgenIcon
				agilityIcon
				vitalIcon
				luckIcon
				magicIcon
				commIcon
				weaponIcon
				parryIcon
				dodgeIcon
				stealthIcon
				pickIcon
				throwIcon
				climbIcon
				acrobIcon
				honorIcon
				pointIcon
				healthIcon
				staminaIcon
				manaIcon
				startIcon
				cancelIcon
				namePlate
		)
		(self eachElementDo: #init self)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4)
		(while
			(and
				(& state $0020)
				(= temp0 ((user curEvent?) new:))
			)
			(= temp1 (temp0 type?))
			(= temp2 (temp0 message?))
			(= temp3 (temp0 modifiers?))
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(if (== temp1 32)
				(= temp1 4)
				(= temp2 (if (& temp3 $0003) 27 else 13))
				(= temp3 0)
				(temp0 type: temp1 message: temp2 modifiers: temp3)
			)
			(temp0 localize: plane)
			(MapKeyToDir temp0)
			(breakif (self dispatchEvent: temp0))
		)
	)
	
	(method (dispose)
		(if
		(and (!= heroType 1) [egoStats 12] (!= prevRoomNum 54))
			(= [egoStats 25] 0)
			(= [egoStats 23] 0)
			(= [egoStats 21] 100)
			(= [egoStats 27] 0)
			(= [egoStats 26] 100)
			(= [egoStats 28] 100)
			(= [egoStats 31] 0)
			(= [egoStats 29] 0)
			(= [egoStats 33] 0)
			(= [egoStats 32] 0)
			(= [egoStats 20] 0)
			(= [egoStats 30] 0)
			(= [egoStats 22] 0)
			(= [egoStats 24] 150)
		)
		(if (and (== heroType 1) (== prevRoomNum 54))
			(if (< [egoStats 28] 100) (= [egoStats 28] 100))
			(if (< [egoStats 33] 100) (= [egoStats 33] 100))
			(if (< [egoStats 29] 100) (= [egoStats 29] 100))
		)
		(= [egoStats 32] 0)
		(local4 dispose:)
		(local5 dispose:)
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements
			(self eachElementDo: #dispose)
			(DisposeList elements)
		)
		(= size (= elements 0))
		(DisposeClone self)
		(if local9 (= paladinPoints [egoStats 14]))
		(curRoom newRoom: (if local9 770 else 100))
	)
	
	(method (show &tmp temp0 temp1 theNextNode temp3)
		(= state (| state $0020))
		(Message
			msgGET
			140
			(switch heroType
				(0 2)
				(1 3)
				(2 4)
				(3 5)
			)
			0
			0
			1
			(newStr data?)
		)
		((myDText new:)
			posn: 135 140
			text: (newStr data?)
			font: 123
			fore: 174
			back: 254
			skip: 254
			setSize:
			setPri: 242
			init: newCast
		)
		((myDText new:)
			posn: 134 140
			text: (newStr data?)
			font: 123
			fore: 227
			back: 254
			skip: 254
			setSize:
			setPri: 243
			init: newCast
		)
		(= [egoStats 17]
			(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
		)
		(= [egoStats 18] (/ (+ [egoStats 2] [egoStats 3]) 2))
		(= [egoStats 19]
			(if [egoStats 12]
				(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
			else
				0
			)
		)
		(if (== heroType 3)
			(aHero
				loop: 0
				cel: 0
				posn: (local4 at: 0) (local5 at: 0)
				init: newCast
			)
		else
			(aHero
				loop: heroType
				cel: 0
				posn: (local4 at: heroType) (local5 at: heroType)
				init: newCast
			)
		)
		(sounds pause:)
		(= state (| state $0020))
		(if curIcon
			(theGame
				setCursor:
					theCursor
					1
					(+
						(curIcon nsLeft?)
						(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
					)
					(- (curIcon nsBottom?) 3)
			)
		)
		(= temp0 30)
		(= temp1 30)
		(= theNextNode (FirstNode elements))
		(while theNextNode
			(= nextNode (NextNode theNextNode))
			(if (not (= temp3 (NodeValue theNextNode))) (return))
			(if
				(and
					(not (& (temp3 signal?) $0080))
					(<= (temp3 nsRight?) 0)
				)
				(temp3 show: temp0 temp1)
				(= temp0 (+ 20 (temp3 nsRight?)))
			else
				(temp3 show:)
			)
			(= theNextNode nextNode)
		)
		(UpdatePlane plane)
		(self doit: hide:)
	)
	
	(method (select theCurIcon param2)
		(return
			(if (theCurIcon select: (if (>= argc 2) param2))
				(if (not (& (theCurIcon signal?) $0002))
					(= curIcon theCurIcon)
				)
				1
			else
				0
			)
		)
	)
	
	(method (highlight theHighlightedIcon param2)
		(if highlightedIcon
			(highlightedIcon highlight: 0)
			(= highlightedIcon 0)
		)
		(cond 
			((not (= highlightedIcon theHighlightedIcon)) 0)
			((& (theHighlightedIcon signal?) $0004) (= highlightedIcon 0))
			(else
				(theHighlightedIcon highlight: 1)
				(if (and (>= argc 2) param2)
					(theGame
						setCursor:
							theCursor
							1
							(+
								(theHighlightedIcon nsLeft?)
								(/
									(-
										(theHighlightedIcon nsRight?)
										(theHighlightedIcon nsLeft?)
									)
									2
								)
							)
							(- (theHighlightedIcon nsBottom?) 3)
					)
				)
			)
		)
	)
	
	(method (swapCurIcon)
	)
	
	(method (advanceCurIcon)
	)
	
	(method (dispatchEvent event &tmp eventY eventX eventType eventMessage eventModifiers temp5 temp6 temp7)
		(if userNameSize_2
			(startIcon signal: 0)
		else
			(startIcon signal: 4)
		)
		(= eventX (event x?))
		(= eventY (event y?))
		(= eventType (event type?))
		(= temp6 0)
		(= eventMessage (event message?))
		(= eventModifiers (event modifiers?))
		(curRoom north: (= temp5 (self firstTrue: #onMe event)))
		(cond 
			((& eventType evMENUSTART)
				(switch eventMessage
					(JOY_RIGHT
						(if (and temp5 (temp5 state?)) (self select: temp5 1))
					)
					(JOY_LEFT
						(if (and highlightedIcon (highlightedIcon state?))
							(self select: temp5 0)
						)
					)
				)
			)
			((== eventType evNULL)
				(cond 
					((not temp5)
						(if highlightedIcon
							(highlightedIcon highlight: 0)
							(= highlightedIcon 0)
						)
					)
					((and temp5 (!= temp5 highlightedIcon)) (= oldMouseY 0) (self highlight: temp5))
				)
			)
			((not highlightedIcon) 0)
			((== eventType evMOUSEBUTTON)
				(if temp5
					(cond 
						((== temp5 namePlate) 0)
						((== eventModifiers emSHIFT) (self select: temp5 1))
						(else (self select: temp5 0))
					)
				)
			)
			((== eventType evKEYBOARD)
				(switch eventMessage
					(KEY_RETURN
						(if
							(or
								(== highlightedIcon startIcon)
								(== highlightedIcon cancelIcon)
							)
							(self select: highlightedIcon 1)
						)
					)
					(else 
						(cond 
							((!= highlightedIcon namePlate) 0)
							(
								(or
									(and (<= KEY_a eventMessage) (<= eventMessage KEY_z))
									(and (<= KEY_A eventMessage) (<= eventMessage KEY_Z))
									(and (<= KEY_0 eventMessage) (<= eventMessage KEY_9))
								)
								(self select: namePlate eventMessage)
							)
							((== eventMessage KEY_SPACE) (self select: namePlate eventMessage))
							((== eventMessage JOY_UPLEFT) (self select: namePlate eventMessage))
						)
					)
				)
			)
		)
		(return temp6)
	)
)

(instance aHero of View
	(properties
		view 170
	)
)

(instance myDText of DText
	(properties)
	
	(method (dispose &tmp planeCasts theBitmap)
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(cast delete: self)
		(if (self isNotHidden:) (DeleteScreenItem self))
		((= planeCasts (plane casts?))
			eachElementDo: #delete self
		)
		(= plane 0)
		(DisposeClone self)
		(if theBitmap (Bitmap 1 theBitmap))
	)
)

(instance soundFX of Sound
	(properties)
)

(instance torchFX of Sound
	(properties)
)
