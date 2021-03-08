;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include game.sh) (include "104.shm")
(use Main)
(use PriorityTalker)
(use Osc)
(use RandCyc)
(use MoveFwd)
(use LoadMany)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm104 0
	quirkTalker 14
	proc104_15 15
)

(local
	starX
	starY
	local2
	local3
	local4
	local5
	local6 =  160
	local7 =  100
	local8 =  4
	local9
	local10 =  6
	local11
	local12
	[str 200]
	[str2 200]
	local413
	local414
	theSeq =  1
	local416 =  6
	local417
	local418
	local419
	local420
	i
	local422
	[cometLoop 15] = [2 2 2 2 2 2 2 2 3 2 2 2 2 2 2]
	[cometCel 15] = [2 2 2 2 2 2 2 0 0 0 1 1 1 1 1]
	[cometX 15] = [146 152 161 170 186 213 230 240 258 253 229 196 154 114 75]
	[cometY 15] = [88 82 77 68 55 32 22 22 20 28 24 45 75 115 134]
	[cometScale 15] = [33 43 38 43 53 78 83 128 128 128 60 80 91 103 128]
)
(procedure (proc104_15)
)

(instance rm104 of Room
	(properties
		picture 1
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 97 99 100 101 102 103)
		(LoadMany RES_PIC 1 5 7 8)
		(super init:)
		(curRoom setScript: sGo)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit)
		(switch local417
			(1
				(Palette PALCycle
					224 226 1
					226 228 1
					228 230 1
					232 234 1
					234 236 1
					240 241 1
					128 144 1
				)
			)
			(2
				(Palette PALCycle 236 238 10)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DrawPic 0 PLAIN TRUE)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (curRoom script?) sCaptainsLog)
				(== (sCaptainsLog state?) 0)
				(& (event type?) (| userEvent mouseDown keyDown))
			)
			(sCaptainsLog changeState: 1)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (newRoom n)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(FlushResources n)
		(super newRoom: n)
	)
)

(instance sWaitNow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 0 7)))
			(1 (self dispose:))
		)
	)
)

(instance sGo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1 number: 1 setLoop: -1 play:)
				(= seconds 3)
			)
			(1
				(space init: setCycle: CycleTo 3 1 self)
			)
			(2 (= seconds 2))
			(3
				(Message MsgGet scriptNumber N_COPYRIGHT NULL NULL 1 @str)
				(Display @str
					p_at 65 180
					p_color 207
					p_font 1605
					p_width 200
				)
				(= seconds 3)
			)
			(4
				(Display @str
					p_at 65 180
					p_color 1
					p_font 1605
					p_width 200
				)
				(= seconds 3)
			)
			(5
				(theComet init:)
				(space setCycle: CycleTo 10 1)
				(= i 1)
				(= cycles 1)
			)
			(6
				(theComet
					setLoop: [cometLoop i]
					setCel: [cometCel i]
					x: [cometX i]
					y: [cometY i]
					scaleX: [cometScale i]
					scaleY: [cometScale i]
				)
				(if (== (++ i) 8) (theComet setPri: 15))
				(if (< i 15)
					(if (== i 8)
						(space setCycle: CycleTo 9 -1)
					)
					(-- state)
				)
				(= ticks 5)
			)
			(7
				(theComet dispose:)
				(space setCycle: EndLoop self)
			)
			(8
				(space dispose:)
				(= local417 1)
				(Palette PALIntensity 1 175 0)
				(Palette PALIntensity 240 242 0)
				(Palette PALIntensity 182 208 0)
				(= cycles 5)
			)
			(9
				(head init:)
				(lArm init:)
				(rArm init:)
				(ego1 init:)
				(ego1 addToPic:)
				(lArm addToPic:)
				(rArm addToPic:)
				(head addToPic:)
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(10
				((medStar new:) init:)
				((slowStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(11
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(12
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(13
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(14
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(15
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(16
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(17
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(18
				((commetStar new:) init:)
				((medStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(19
				((commetStar new:) init:)
				((medStar new:) init:)
				((slowStar new:) init:)
				((fastStar new:) init:)
				(= cycles 1)
			)
			(20 (= seconds 4))
			(21
				(= next sPixelIn)
				(self dispose:)
			)
		)
	)
)

(instance sPixelIn of Script
	
	(method (doit)
		(super doit: &rest)
		(switch local420
			(1
				(Palette PALIntensity 144 152 100)
				(Palette PALIntensity 152 160 0)
				(= local420 2)
			)
			(2
				(Palette PALIntensity 144 152 0)
				(Palette PALIntensity 152 160 100)
				(= local420 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #setPri 1)
				(= local8 2)
				(= local420 1)
				(Palette PALIntensity 1 175 10)
				(Palette PALIntensity 240 242 10)
				(Palette PALIntensity 182 208 10)
				(Palette PALIntensity 1 175 20)
				(Palette PALIntensity 240 242 20)
				(Palette PALIntensity 182 208 20)
				(Palette PALIntensity 1 175 30)
				(Palette PALIntensity 240 242 30)
				(Palette PALIntensity 182 208 30)
				(Palette PALIntensity 1 175 40)
				(Palette PALIntensity 240 242 40)
				(Palette PALIntensity 182 208 40)
				(Palette PALIntensity 1 175 50)
				(Palette PALIntensity 240 242 50)
				(Palette PALIntensity 182 208 50)
				(Palette PALIntensity 1 175 60)
				(Palette PALIntensity 240 242 60)
				(Palette PALIntensity 182 208 60)
				(Palette PALIntensity 1 175 70)
				(Palette PALIntensity 240 242 70)
				(Palette PALIntensity 182 208 70)
				(Palette PALIntensity 1 175 80)
				(Palette PALIntensity 240 242 80)
				(Palette PALIntensity 182 208 80)
				(Palette PALIntensity 1 175 90)
				(Palette PALIntensity 240 242 90)
				(Palette PALIntensity 182 208 90)
				(Palette PALIntensity 1 175 100)
				(Palette PALIntensity 240 242 100)
				(Palette PALIntensity 182 208 100)
				(= cycles 25)
			)
			(1
				(headA init:)
				(lArmA init:)
				(rArmA init:)
				(ego1P init: addToPic:)
				(logo init: addToPic:)
				(mutation init: addToPic:)
				(enext init: addToPic:)
				(DrawPic 1 WIPERIGHT)
				(= cycles 1)
			)
			(2
				(rArmA setCycle: EndLoop cycleSpeed: 10)
				(lArmA setCycle: EndLoop self cycleSpeed: 10)
			)
			(3
				(rArmA setCycle: CycleTo 2 -1)
				(lArmA setCycle: CycleTo 2 -1 self)
			)
			(4
				(rArmA setCycle: CycleTo 3 1)
				(lArmA setCycle: CycleTo 3 1 self)
			)
			(5
				(rArmA setCycle: BegLoop)
				(lArmA setCycle: BegLoop self)
			)
			(6
				(mutating2 init:)
				(theMusic2 number: 708 setLoop: 1 play:)
				(mutating init: setCycle: EndLoop self)
			)
			(7
				(headA cycleSpeed: 10 setCycle: EndLoop self)
			)
			(8
				(headA addToPic:)
				(lArmA addToPic:)
				(rArmA addToPic:)
				(= seconds 6)
			)
			(9
				(Palette PALIntensity 144 160 0)
				(mutating dispose:)
				(mutating2 dispose:)
				(DrawPic 1 WIPERIGHT)
				(= seconds 3)
			)
			(10
				(= local417 0)
				(= next sCredits)
				(self dispose:)
			)
		)
	)
)

(instance sCredits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette PALIntensity 8 16 0)
				(= ticks 5)
			)
			(1
				(creditName setLoop: local9 init:)
				(= cycles 1)
			)
			(2
				(Palette PALIntensity 8 16 local12)
				(if (< (= local12 (+ local12 10)) 101) (-- state))
				(= ticks 5)
			)
			(3 (= seconds 2))
			(4
				(Palette PALIntensity 8 16 local12)
				(if (> (= local12 (- local12 10)) 0) (-- state))
				(= ticks 5)
			)
			(5
				(if (< (++ local9) local10) (= state (- state 6)))
				(= seconds 2)
			)
			(6
				(theMusic1 fade:)
				(creditName dispose:)
				(= seconds 3)
			)
			(7
				(theMusic1 number: 2 loop: -1 play: 0 fade: 127 10 5 0)
				(= next sCaptainsLog)
				(Palette PALIntensity 8 16 100)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (< state 6) (& (event type?) (| userEvent mouseDown keyDown)))
			(self changeState: 6)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
)

(instance sCaptainsLog of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message MsgGet 104 N_CAPTAIN_LOG NULL NULL theSeq @str)
				(= local413 (StrLen @str))
				(= local414 0)
				(= cycles 1)
			)
			(1
				(repeat
					(StrAt @str2 local414 (StrAt @str local414))
					(StrAt @str2 (++ local414) 0)
					(if
						(or
							(== (StrAt @str (- local414 1)) 32)
							(== (StrAt @str (- local414 1)) 0)
						)
						(break)
					)
				)
				(Display @str2
					p_at 40 10
					p_color 254
					p_font 1307
					p_width 250
				)
				(if (< local414 local413) (-- state) (= ticks 15))
			)
			(2
				(= seconds 0)
				(Display @str2
					p_at 40 10
					p_color 0
					p_font 1307
					p_width 250
				)
				(if (> (++ theSeq) local416)
					(= cycles 1)
				else
					(= state -1)
					(= seconds 1)
				)
			)
			(3
				(Palette PALIntensity 0 175 0)
				(Palette PALIntensity 181 255 0)
				(= next sAttack)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (== state 1) (& (event type?) (| userEvent keyDown mouseDown)))
			(self changeState: 2)
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event)
		)
	)
)

(instance sAttack of Script
	
	(method (doit)
		(Palette
			PALIntensity
			0
			175
			(/ (* 5 (- 220 (rogInChair y?))) 4)
		)
		(Palette
			PALIntensity
			181
			255
			(/ (* 5 (- 220 (rogInChair y?))) 4)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rogInChair
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 1
					setMotion: MoveTo 160 140 self
				)
			)
			(1
				(rogInChair loop: 0 cel: 0 setCycle: Oscillate 1 self)
			)
			(2 (= seconds 3))
			(3
				(theMusic1 number: 3 setLoop: -1 play:)
				(talkBubble init:)
				(= seconds 4)
			)
			(4
				(talkBubble hide:)
				(theMusic2 number: 202 setLoop: 1 play:)
				(ShakeScreen 5 2)
				(= cycles 1)
			)
			(5
				(rogInChair loop: 2 cel: 2 setCycle: BegLoop self)
			)
			(6
				(rogInChair hide:)
				(DrawPic 5 PLAIN TRUE)
				(= local417 2)
				(= local419 1)
				(Palette PALIntensity 144 160 100)
				(theMusic2 number: 105 setLoop: -1 play:)
				(= seconds 5)
			)
			(7
				(rogCloseup init:)
				(rogMouth init: setCycle: RTRandCycle)
				(talkBubble view: 101 loop: 2 cel: 0 x: 80 y: 10 show:)
				(= seconds 4)
			)
			(8
				(rogMouth hide:)
				(talkBubble view: 101 loop: 3 cel: 0 x: 20 y: 10)
				(= seconds 4)
			)
			(9
				(rogCloseup view: 97)
				(rogMouth
					view: 97
					x: 123
					y: 89
					show:
					setCycle: RTRandCycle
				)
				(talkBubble view: 97 loop: 3 cel: 0 x: 80 y: 6)
				(= seconds 4)
			)
			(10
				(rogCloseup dispose:)
				(rogMouth dispose:)
				(talkBubble dispose:)
				(theMusic2 number: 202 setLoop: 1 play:)
				(DrawCel 97 2 0 81 38 15)
				(ShakeScreen 10 shakeSRight)
				(= seconds 2)
			)
			(11
				(DrawPic 1 PLAIN TRUE)
				(rogInChair loop: 2 cel: 2 show: stopUpd: ignoreActors:)
				(= local417 0)
				(= seconds 2)
			)
			(12
				(theMusic2 stop:)
				(theMusic1 number: 10 setLoop: -1 play:)
				(DrawPic 7 PIXELDISSOLVE TRUE)
				(= seconds 2)
			)
			(13
				(messager say: N_ATTACK NULL NULL 5 self)
			)
			(14
				(messager say: N_ATTACK NULL NULL 6 self)
			)
			(15
				(messager say: N_ATTACK NULL NULL 7 self)
			)
			(16
				(DrawPic 8 PIXELDISSOLVE TRUE)
				(= local417 0)
				(= seconds 6)
			)
			(17
				(curRoom newRoom: 110)
				(self dispose:)
			)
		)
	)
)

(instance space of Prop
	(properties
		x 120
		y 74
		view 96
	)
	
	(method (init)
		(super init: &rest)
		(quest init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(quest cel: (self cel?))
	)
	
	(method (dispose)
		(super dispose: &rest)
		(quest dispose:)
	)
)

(instance quest of Prop
	(properties
		x 148
		y 79
		view 96
		loop 1
	)
)

(instance rogInChair of Actor
	(properties
		x 160
		y 220
		view 100
		loop 1
	)
)

(instance lArm of Prop
	(properties
		x 125
		y 85
		view 99
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance lArmA of Actor
	(properties
		x 125
		y 85
		view 99
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance rArm of Prop
	(properties
		x 214
		y 75
		view 99
		loop 1
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance rArmA of Actor
	(properties
		x 214
		y 75
		view 99
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance head of Prop
	(properties
		x 162
		y 72
		view 99
		loop 2
		cel 2
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance headA of Actor
	(properties
		x 162
		y 72
		view 99
		loop 2
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance ego1P of Prop
	(properties
		x 41
		y 72
		view 99
		loop 3
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance commetStar of Actor
	(properties
		view 103
		signal $6010
		moveSpeed 0
	)
	
	(method (init)
		(self setScript: sWaitNow)
		(= local4 20)
		(= local5 (Random 0 7))
		(while (or (== local5 local2) (== local5 local3))
			(= local5 (Random 0 7))
		)
		(= local3 local2)
		(= local2 local5)
		(= local5 (* local5 45))
		(= starX (+ local6 (CosMult local5 local4)))
		(= starY (+ local7 (SinMult local5 local4)))
		(self
			setHeading: (+ local5 90)
			posn: starX starY
			cycleSpeed: 10
			setStep: 8 8
			setCycle: EndLoop
			setLoop: GradualLooper
			setCel: 0
			setPri: local8
			setMotion: MoveFwd 150 self
		)
		(super init:)
	)
	
	(method (cue)
		(self setScript: sWaitNow)
		(if (not local419) ((commetStar new:) init:))
		(self dispose:)
	)
)

(instance fastStar of Actor
	(properties
		view 103
		priority 4
		signal $6010
		moveSpeed 0
	)
	
	(method (init)
		(self setScript: sWaitNow)
		(= local4 20)
		(= local5 (Random 0 359))
		(= starX (+ local6 (CosMult local5 local4)))
		(= starY (+ local7 (SinMult local5 local4)))
		(self
			setHeading: (+ local5 90)
			posn: starX starY
			setStep: 5 5
			setLoop: 0
			setCycle: 0
			setCel: 2
			setPri: local8
			setMotion: MoveFwd 150 self
		)
		(super init:)
	)
	
	(method (cue)
		(if (not local419) ((fastStar new:) init:))
		(self dispose:)
	)
)

(instance medStar of Actor
	(properties
		view 103
		priority 4
		signal $6010
		moveSpeed 4
	)
	
	(method (init)
		(self setScript: sWaitNow)
		(= local4 20)
		(= local5 (Random 0 359))
		(= starX (+ local6 (CosMult local5 local4)))
		(= starY (+ local7 (SinMult local5 local4)))
		(self
			setHeading: (+ local5 90)
			posn: starX starY
			setCycle: 0
			setLoop: 0
			setPri: local8
			setCel: 1
			setMotion: MoveFwd 100 self
		)
		(super init:)
	)
	
	(method (cue)
		(if (not local419) ((medStar new:) init:))
		(self dispose:)
	)
)

(instance slowStar of Actor
	(properties
		view 103
		priority 4
		signal $6010
		moveSpeed 10
	)
	
	(method (init)
		(self setScript: sWaitNow)
		(= local4 (Random 30 40))
		(= local5 (Random 0 359))
		(= starX (+ local6 (CosMult local5 local4)))
		(= starY (+ local7 (SinMult local5 local4)))
		(self
			setHeading: (+ local5 90)
			posn: starX starY
			setPri: local8
			setLoop: 0
			setCycle: 0
			setMotion: MoveFwd 100 self
		)
		(super init:)
	)
	
	(method (cue)
		(if (not local419) ((slowStar new:) init:))
		(self dispose:)
	)
)

(instance quirkMouth of Prop
	(properties
		nsTop 69
		nsLeft 14
		view 102
		priority 6
		signal (| ignrAct fixPriOn)
	)
)

(instance quirkTalker of PriorityTalker
	(properties
		x 100
		y 20
		view 102
		loop 2
		priority 6
		talkWidth 280
		textX -160
		textY -80
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 171
			tailY: 38
			xOffset: 0
		)
		(super init: 0 0 quirkMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance talkBubble of View
	(properties
		x 160
		y 8
		view 100
		loop 3
		cel 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance rogCloseup of View
	(properties
		x 81
		y 38
		view 101
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance rogMouth of Prop
	(properties
		x 134
		y 89
		view 101
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance theComet of Prop
	(properties
		x 146
		y 88
		view 96
		loop 2
		cel 2
		priority 1
		signal fixPriOn
		scaleSignal scalable
		scaleX 33
		scaleY 33
	)
)

(instance ego1 of View
	(properties
		x 41
		y 72
		view 99
		loop 3
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance creditName of View
	(properties
		x 160
		y 85
		view 29
		signal $4000
	)
)

(instance logo of View
	(properties
		x 56
		y 5
		view 99
		loop 3
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance enext of Actor
	(properties
		x 50
		y 169
		view 99
		loop 4
		priority 15
		signal (| ignrAct fixPriOn)
		xStep 14
	)
)

(instance mutation of Actor
	(properties
		x 155
		y 172
		view 99
		loop 4
		cel 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance mutating of Prop
	(properties
		x 155
		y 172
		view 99
		loop 5
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance mutating2 of View
	(properties
		x 154
		y 172
		view 99
		loop 6
		priority 14
		signal (| ignrAct fixPriOn)
	)
)
