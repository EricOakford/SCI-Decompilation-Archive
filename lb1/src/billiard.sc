;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	billiard 0
)
(synonyms
	(attorney fellow)
	(actress girl)
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 2]
	gloriaX
	gloriaY
	gloriaLoop
	gloriaCel
	local12
	theState
	local14 =  -1
	local15 =  -1
	local16 =  -1
	local17 = [
		0 0 0 0 0 0 PATHEND
		0 0 105 114 0 0 PATHEND
		0 0 105 128 0 0 PATHEND
		207 114 105 114 0 0 PATHEND
		105 128 207 128 0 0 PATHEND
		0 0 207 114 0 0 PATHEND
		0 0 207 128 0 0 PATHEND
		0 0 0 0 0 0 PATHEND
		]
	local73 = [
		0 0 85 123 PATHEND
		105 114 145 109 PATHEND
		105 128 162 143 PATHEND
		0 0 230 123 PATHEND
		207 114 145 109 PATHEND
		207 128 162 143 PATHEND
		]
	local103
	goSeeXY = [
		207 114
		105 114
		72 120
		PATHEND
		207 128
		105 128
		72 120
		PATHEND
		]
	goSeeIndex
	local119  = [600 0 179 98 500 1 600 1 185 98 191 101 600 2 185 103 -32768 600 0 127 103 500 1 600 1 125 100 158 99 500 2 172 99 600 2 183 98 -32768 600 0 182 98 500 2 600 2 186 101 -32768 600 1 190 102 153 103 600 2 127 103 -32768 600 0 157 103 500 1 600 1 176 104 600 2 182 103 -32768 600 0 134 101 500 2 132 98 600 1 125 100 600 2 129 104 131 103 -32768 600 0 186 102 500 1 600 1 191 100 187 98 600 2 182 100 -32768 600 0 157 101 500 1 143 104 600 1 135 102 600 2 130 100 -32768 700]
	local248 = [600 0 191 101 145 105 600 1 140 104 500 2 600 2 124 103 -32768 600 0 122 103 124 104 144 98 600 1 154 104 600 2 160 102 -32768 600 0 175 98 600 1 188 101 500 2 600 2 190 103 -32768 600 0 191 103 180 104 600 1 165 101 500 2 600 2 163 98 154 100 -32768 600 0 151 98 146 100 500 2 600 1 154 104 168 102 600 2 183 99 -32768]
	local335 = [600 1 133 98 125 100 129 104 600 2 151 98 161 100 -32768 600 1 180 104 191 102 600 2 178 98 -32768 600 0 126 100 600 1 164 104 183 101 500 0 600 2 188 98 191 100 -32768 600 1 192 99 188 98 164 101 137 104 600 2 130 101 -32768 600 0 123 102 128 104 600 1 154 98 600 2 161 101 -32768 600 1 125 100 135 98 600 2 143 101 -32768 600 1 124 102 128 104 600 2 138 103 -32768]
)
(procedure (localproc_000c param1 param2)
	(param1
		setStep: (- 5 param2) (- 4 param2)
		moveSpeed: param2
	)
)

(procedure (localproc_0026 param1 &tmp temp0 temp1 temp2)
	(= local103
		(cond 
			((== (param1 x?) 85) 0)
			((== (param1 y?) 109) 7)
			((== (param1 y?) 143) 14)
			((== (param1 x?) 230) 21)
		)
	)
	(if (> (CueBall x?) 154)
		(+= local103 28)
	)
	(if (== param1 Clarence)
		(= temp0 35)
		(= temp1 32)
		(= temp2 23)
	else
		(= temp0 37)
		(= temp1 36)
		(= temp2 25)
	)
	(= [local17 (+ local103 4)]
		(if (< (CueBall x?) 155)
			(- (CueBall x?) temp0)
		else
			(+ (CueBall x?) temp1)
		)
	)
	(= [local17 (+ local103 5)] (+ (CueBall y?) temp2))
)

(procedure (localproc_00f8 param1 param2 &tmp theGloriaX theGloriaY)
	(= local103
		(*
			(= local103
				(cond 
					((< (param1 x?) 155)
						(if (< (CueBall x?) 155)
							(Random 1 2)
						else
							(Random 0 2)
						)
					)
					((> (CueBall x?) 154) (Random 4 5))
					(else (Random 3 5))
				)
			)
			5
		)
	)
	(if (and local12 (!= theState 18))
		(= theGloriaX gloriaX)
		(= theGloriaY gloriaY)
	else
		(= theGloriaX (param2 x?))
		(= theGloriaY (param2 y?))
	)
	(if
		(and
			(== [local73 (+ local103 2)] theGloriaX)
			(== [local73 (+ local103 3)] theGloriaY)
			(or
				(== (= local103 (- local103 5)) 0)
				(== local103 15)
			)
		)
		(= local103 (+ local103 10))
	)
)

(procedure (localproc_01b8 param1)
	(param1
		loop:
			(= gloriaLoop
				(switch (param1 x?)
					(85 0)
					(145 2)
					(162 3)
					(230 1)
				)
			)
		cel: 0
		forceUpd:
	)
	(if (and (== param1 Gloria) (<= gloriaLoop 1))
		(Gloria loop: (= gloriaLoop (+ gloriaLoop 4)) forceUpd:)
	)
)

(procedure (localproc_0229 who &tmp theX)
	(if (> (who x?) 159)
		(= theX 171)
	else
		(= theX 40)
	)
	(Print &rest
		#at theX 140
		#font 4
		#width 125
		#mode teJustCenter
		#dispose
	)
)

(procedure (localproc_0263 actor1 actor2)
	(actor1 setCycle: Forward)
	(actor2 setCycle: 0 cel: 0 stopUpd:)
	(localproc_0229 actor1 &rest)
)

(procedure (localproc_0287)
	(localproc_0263 Gloria Clarence &rest)
)

(procedure (localproc_0297)
	(localproc_0263 Clarence Gloria &rest)
)

(instance billiard of Region
	
	(method (init)
		(super init:)
		(CueBall setLoop: 9 setCel: 2 setPri: 8 init: stopUpd:)
		(Ball1 setLoop: 9 setCel: 0 setPri: 8 init: stopUpd:)
		(Ball2 setLoop: 9 setCel: 0 setPri: 8 init: stopUpd:)
		(if (not (& global118 $0002))
			(LoadMany FONT 4 41)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
			(Load VIEW 642)
		)
		(Load SCRIPT AVOIDER)
		(LoadMany 143 243 249 248)
		(LoadMany VIEW 405 407 365 364)
		(LoadMany 142 7 3)
		(= global208 68)
		(= [global377 6] 248)
		(= [global377 2] 249)
		(Clarence cel: (Clarence lastCel:) init: stopUpd:)
		(Gloria init: stopUpd:)
		(self setScript: playGame)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'examine/people')
					(Print 245 0)
				)
				((Said 'hear/attorney,actress')
					(Print 245 1)
				)
				((and (Btst fSawDeadGuest) (Said 'tell//gertie<about'))
					(|= global145 $0042)
					(= local12 1)
					(= global202 0)
					(Gloria startUpd: setScript: goSee)
				)
			)
		)
	)
)

(instance CBPath of Script

	(method (changeState)
		(asm
code_046a:
			+al      local14
			lsli     local119
			ldi      32768
			ne?     
			bnt      code_04de
			lal      local14
			lsli     local119
			dup     
			ldi      500
			eq?     
			bnt      code_04a9
			+al      local14
			lsli     local119
			ldi      1
			eq?     
			bnt      code_049a
			pushi    #setScript
			pushi    1
			lofsa    B1Path
			push    
			lofsa    Ball1
			send     6
			jmp      code_04da
code_049a:
			pushi    #setScript
			pushi    1
			lofsa    B2Path
			push    
			lofsa    Ball2
			send     6
			jmp      code_04da
code_04a9:
			dup     
			ldi      600
			eq?     
			bnt      code_04c1
			pushi    2
			lofsa    CueBall
			push    
			+al      local14
			lsli     local119
			call     localproc_000c,  4
			jmp      code_04da
code_04c1:
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lal      local14
			lsli     local119
			+al      local14
			lsli     local119
			pushSelf
			lofsa    CueBall
			send     12
			jmp      code_04ec
code_04da:
			toss    
			jmp      code_046a
code_04de:
			pushi    #setScript
			pushi    1
			pushi    0
			pToa     client
			send     6
			jmp      code_04ec
			jmp      code_046a
code_04ec:
			ret     
		)
	)
)

(instance B1Path of Script

	(method (changeState)
		(asm
code_0522:
			+al      local15
			lsli     local248
			ldi      32768
			ne?     
			bnt      code_0596
			lal      local15
			lsli     local248
			dup     
			ldi      500
			eq?     
			bnt      code_0561
			+al      local15
			lsli     local248
			ldi      0
			eq?     
			bnt      code_0552
			pushi    #setScript
			pushi    1
			lofsa    CBPath
			push    
			lofsa    CueBall
			send     6
			jmp      code_0592
code_0552:
			pushi    #setScript
			pushi    1
			lofsa    B2Path
			push    
			lofsa    Ball2
			send     6
			jmp      code_0592
code_0561:
			dup     
			ldi      600
			eq?     
			bnt      code_0579
			pushi    2
			lofsa    Ball1
			push    
			+al      local15
			lsli     local248
			call     localproc_000c,  4
			jmp      code_0592
code_0579:
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lal      local15
			lsli     local248
			+al      local15
			lsli     local248
			pushSelf
			lofsa    Ball1
			send     12
			jmp      code_05a4
code_0592:
			toss    
			jmp      code_0522
code_0596:
			pushi    #setScript
			pushi    1
			pushi    0
			pToa     client
			send     6
			jmp      code_05a4
			jmp      code_0522
code_05a4:
			ret     
		)
	)
)

(instance B2Path of Script

	(method (changeState)
		(asm
code_05da:
			+al      local16
			lsli     local335
			ldi      32768
			ne?     
			bnt      code_0654
			lal      local16
			lsli     local335
			dup     
			ldi      500
			eq?     
			bnt      code_061c
			+al      local16
			lsli     local335
			ldi      0
			eq?     
			bnt      code_060d
			pushi    #setScript
			pushi    1
			lofsa    CBPath
			push    
			lofsa    CueBall
			send     6
			jmp      code_0650
code_060d:
			pushi    #setScript
			pushi    1
			lofsa    B1Path
			push    
			lofsa    Ball1
			send     6
			jmp      code_0650
code_061c:
			dup     
			ldi      600
			eq?     
			bnt      code_0635
			pushi    2
			lofsa    Ball2
			push    
			+al      local16
			lsli     local335
			call     localproc_000c,  4
			jmp      code_0650
code_0635:
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lal      local16
			lsli     local335
			+al      local16
			lsli     local335
			pushSelf
			lofsa    Ball2
			send     12
			jmp      code_0662
code_0650:
			toss    
			jmp      code_05da
code_0654:
			pushi    #setScript
			pushi    1
			pushi    0
			pToa     client
			send     6
			jmp      code_0662
			jmp      code_05da
code_0662:
			ret     
		)
	)
)

(instance GoSeePath of Script

	(method (changeState)
		(if (!= [goSeeXY (++ goSeeIndex)] PATHEND)
			(Gloria
				setMotion: MoveTo [goSeeXY goSeeIndex] [goSeeXY (++ goSeeIndex)] self
			)
		else
			(client setScript: 0)
		)
	)
)

(instance playGame of Script
	
	(method (doit)
		(super doit:)
		(DisposeScript SAVE)
		(if
			(and
				local0
				(== (CueBall script?) (Ball1 script?))
				(== (Ball1 script?) (Ball2 script?))
			)
			(if (== [local119 (+ local14 1)] 700)
				(= local14 (= local15 (= local16 -1)))
			)
			(= local0 0)
			(= cycles 14)
		)
		(cond 
			(
				(and
					(not local12)
					(or (Clarence isBlocked:) (Gloria isBlocked:))
				)
				(if (not local5)
					(if (Clarence isBlocked:)
						(= theTalker talkCLARENCE)
					else
						(= theTalker talkGLORIA)
					)
					(Say 1 245 2)
					(User canInput: FALSE)
					(= local5 1)
				)
			)
			(local5
				(User canInput: 1)
				(= local5 0)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0002))
						(|= global118 $0002)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(User canInput: FALSE)
				(if (not local4)
					(switch global178
						(0 (localproc_0297 245 3))
						(1 (localproc_0297 245 4))
						(2 (localproc_0297 245 5))
						(3 (localproc_0287 245 6))
						(4 (localproc_0297 245 7))
					)
				)
				(= cycles 20)
			)
			(2
				(if (not local4)
					(switch global178
						(0 (localproc_0287 245 8))
						(1 (localproc_0287 245 9))
						(2 (localproc_0287 245 10))
						(3 (localproc_0297 245 11))
						(4 (localproc_0287 245 12))
					)
				)
				(if (< global178 4)
					(++ global178)
				)
				(if (not local4)
					(= local4 1)
				)
				(= cycles 20)
			)
			(3
				(cls)
				(User canInput: TRUE)
				(Gloria stopUpd:)
				(localproc_01b8 Gloria)
				(Clarence setCycle: Walk)
				(localproc_0026 Clarence)
				(= cycles 1)
			)
			(4
				(if (== [local17 local103] -32768)
					(= cycles 1)
				else
					(if [local17 local103]
						(Clarence
							setMotion: MoveTo [local17 local103] [local17 (+ local103 1)] self
						)
					else
						(= cycles 1)
					)
					(+= local103 2)
					(= state 3)
				)
			)
			(5
				(Clarence
					view: 405
					cel: 0
					loop: (if (< (Clarence x?) 155) 0 else 2)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(Clarence
					cel: 0
					loop: (+ (Clarence loop?) 1)
					setCycle: Forward
				)
				(= cycles 7)
			)
			(7
				(CueBall setScript: CBPath)
				(Clarence loop: (- (Clarence loop?) 1))
				(Clarence
					cel: (Clarence lastCel:)
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(8 (= local0 1))
			(9
				(if (< (Random 1 100) 25)
					(localproc_0229 Clarence 245 13)
				)
				(Clarence view: 407 cycleSpeed: 0 setCycle: Walk)
				(localproc_00f8 Clarence Gloria)
				(= cycles 1)
			)
			(10
				(if (== [local73 local103] -32768)
					(= cycles 1)
				else
					(if [local73 local103]
						(Clarence
							setMotion: MoveTo [local73 local103] [local73 (+ local103 1)] self
						)
					else
						(= cycles 1)
					)
					(= local103 (+ local103 2))
					(= state 9)
				)
			)
			(11
				(cls)
				(Clarence stopUpd:)
				(localproc_01b8 Clarence)
				(if local12
					(= state 19)
					(= theState 10)
				else
					(Gloria view: 364 setCycle: Walk)
					(localproc_0026 Gloria)
				)
				(= cycles 1)
			)
			(12
				(cond 
					(local12 (= state 19) (= theState 11) (= cycles 1))
					((== [local17 local103] -32768) (= cycles 1))
					(else
						(if [local17 local103]
							(Gloria
								setMotion: MoveTo [local17 local103] [local17 (+ local103 1)] self
							)
						else
							(= cycles 1)
						)
						(= local103 (+ local103 2))
						(= state 11)
					)
				)
			)
			(13
				(if local12
					(= state 19)
					(= theState 12)
					(= cycles 1)
				else
					(Gloria
						view: 365
						cel: 0
						loop: (if (< (Gloria x?) 155) 0 else 2)
						cycleSpeed: 1
						setCycle: EndLoop self
					)
				)
			)
			(14
				(if local12
					(= state 19)
					(= theState 12)
				else
					(Gloria
						cel: 0
						loop: (+ (Gloria loop?) 1)
						cycleSpeed: 0
						setCycle: Forward
					)
				)
				(= cycles 7)
			)
			(15
				(if (not local12) (CueBall setScript: CBPath))
				(Gloria loop: (- (Gloria loop?) 1))
				(Gloria
					cel: (Gloria lastCel:)
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(16
				(if local12
					(= state 19)
					(if (CueBall script?)
						(= theState 18)
					else
						(= theState 12)
					)
					(= cycles 1)
				else
					(= local0 1)
				)
			)
			(17
				(if local12
					(= state 19)
					(= theState 18)
				else
					(Gloria view: 364 cycleSpeed: 0 setCycle: Walk)
					(localproc_00f8 Gloria Clarence)
					(if
						(and
							(!= local103 0)
							(!= local103 15)
							(< (Random 1 100) 50)
						)
						(localproc_0229 Gloria 245 14)
					)
				)
				(= cycles 1)
			)
			(18
				(cond 
					(local12 (= state 19) (= theState 18) (= cycles 1))
					((== [local73 local103] -32768) (= cycles 1))
					(else
						(if [local73 local103]
							(Gloria
								setMotion: MoveTo [local73 local103] [local73 (+ local103 1)] self
							)
						else
							(= cycles 1)
						)
						(= local103 (+ local103 2))
						(= state 17)
					)
				)
			)
			(19
				(if local12
					(= state 19)
					(= theState 18)
				else
					(cls)
					(Gloria stopUpd:)
					(localproc_01b8 Gloria)
					(= state 2)
				)
				(= cycles 1)
			)
			(20 (= cycles 1))
			(21
				(if local12 (= state 20) else (= state theState))
				(= cycles 1)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance goSee of Script

	(method (doit)
		(super doit:)
		(if (== global202 3)
			(= global202 0)
			(if (not seconds) (= cycles 1))
		)
	)
	
	(method (changeState newState &tmp gloriaX_2)
		(switch (= state newState)
			(0
				(= saveDisabled TRUE)
				(HandsOff)
				(cls)
				(if
					(and
						(> (playGame state?) 10)
						(< (playGame state?) 20)
					)
					(= state -1)
				else
					(cls)
					(= theTalker talkGLORIA)
					(Say 1 245 15)
					(= gloriaX (Gloria x?))
					(= gloriaY (Gloria y?))
					(= gloriaLoop (Gloria loop?))
					(= gloriaCel (Gloria cel?))
					(if (not (& (ego onControl: FALSE) cBLACK))
						(ego
							setMotion: MoveTo (+ (ego x?) 20) (+ (ego y?) 20)
						)
					)
					(Gloria
						view: 364
						illegalBits: 0
						ignoreActors: TRUE
						setCycle: Walk
					)
					(Clarence ignoreActors: TRUE)
					(cond 
						((< gloriaX 106)
							(= goSeeIndex 3)
						)
						((< gloriaY 115)
							(= goSeeIndex 1)
						)
						((> gloriaY 127)
							(= goSeeIndex 8)
						)
						((> gloriaY 123)
							(= goSeeIndex 6)
						)
						(else
							(= goSeeIndex -1)
						)
					)
					(self setScript: GoSeePath)
				)
				(= cycles 1)
			)
			(1
				(cls)
				(if (self script?)
					(= state 0)
					(= cycles 1)
				else
					(= global202 1)
				)
			)
			(2
				(Gloria setMotion: MoveTo 22 125 self)
			)
			(3
				(= global202 2)
				(= seconds 5)
			)
			(4
				(if (and (& (Clarence signal?) $0004) (not local0))
					(= global202 1)
				else
					(= state 3)
					(= cycles 1)
				)
			)
			(5
				(Clarence ignoreActors: FALSE)
				(Gloria
					loop: -1
					illegalBits: cWHITE
					ignoreActors: 0
					setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: MoveTo 72 125 self
				)
			)
			(6
				(Gloria illegalBits: (| cWHITE cGREEN))
				(= global202 2)
			)
			(7
				(= theTalker talkGLORIA)
				(Say 1 245 16)
				(if (== theState 18)
					(= gloriaX_2 (Gloria x?))
					(Gloria x: gloriaX)
					(localproc_00f8 Gloria Clarence)
					(Gloria
						x: gloriaX_2
						setMotion: MoveTo [local73 (+ local103 2)] [local73 (+ local103 3)] self
					)
				else
					(Gloria setMotion: MoveTo gloriaX gloriaY self)
				)
			)
			(8
				(if (!= theState 18)
					(Gloria loop: gloriaLoop cel: gloriaCel)
				)
				(HandsOn)
				(Gloria illegalBits: 0 setAvoider: 0 setCycle: 0)
				(DisposeScript AVOIDER)
				(= local12 (= saveDisabled FALSE))
				(client setScript: 0)
			)
		)
	)
)

(instance Mouth of Prop
	(properties
		view 364
		loop 6
		signal ignrAct
	)
)

(instance CueBall of Actor
	(properties
		y 100
		x 130
		view 136
		signal ignrAct
		illegalBits $0000
	)
)

(instance Ball1 of Actor
	(properties
		y 99
		x 183
		view 136
		signal ignrAct
		illegalBits $0000
	)
)

(instance Ball2 of Actor
	(properties
		y 103
		x 138
		view 136
		signal ignrAct
		illegalBits $0000
	)
)

(instance Gloria of Actor
	(properties
		y 123
		x 230
		view 364
		loop 5
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if (< (ego distanceTo: Clarence) (ego distanceTo: Gloria))
			(= global214 64)
		else
			(= global214 4)
		)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0004)))
				(event claimed: TRUE)
				(ParseName {gloria})
			)
			(
				(and
					(& global207 $0004)
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/actress[/!*]')
					)
				)
				(event claimed: TRUE)
				(Print 245 0)
			)
			((Said 'converse/actress')
				(= theTalker talkGLORIA)
				(Say 1 245 17)
			)
		)
	)
)

(instance Clarence of Actor
	(properties
		y 123
		x 85
		view 407
		loop 4
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'examine,converse/person,men')
				(Print 245 18)
			)
			((Said 'converse/people')
				(Print 245 19)
			)
			((and (MousedOn self event shiftDown) (not (& global207 $0040)))
				(event claimed: TRUE)
				(ParseName {clarence})
			)
			(
				(and
					(& global207 $0040)
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/attorney,people')
					)
				)
				(event claimed: TRUE)
				(Print 245 0)
			)
			((Said 'converse/attorney')
				(if (< (++ local2) 2)
					(= theTalker talkCLARENCE)
					(Say 1 245 20)
				else
					(Print 245 21)
				)
			)
		)
	)
)
