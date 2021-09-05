;;; Sierra Script 1.0 - (do not remove this comment)
(script# 490)
(include game.sh)
(use Main)
(use GloryWindow)
(use TellerIcon)
(use Print)
(use Talker)
(use IconBar)
(use GControl)
(use Game)
(use Actor)
(use System)

(public
	rm490 0
	yesuTalker 1
)

(local
	local0 = [0 16 17 -18 24 25 26 999]
	local8 = [0 -19 21 22 23 999]
	local14 = [0 20 999]
	local17 = [0 16 27 28 29 30 31 32 999]
	local26 = [0 16 33 40 34 35 39 42 999]
	[local35 5]
	local78_4 = [0 -18 -19 999]
	local76_3 = [0 44 45 999]
	theHeading = [0 44 46 34 999]
	local78_2 = [0 44 -47 999]
	[local76_2 4]
	theHeading_2
	[local78_3 14]
	local76_3_2
	theHeading_3
	local78_4_2
	theArcadeDifficulty
	local80
	theGNewSpeed
	local82
	local83 = [209 181 154 130 104 77 56 86 113 141 167 194 221 238]
	local97 = [62 69 75 82 89 95 115 121 115 108 100 93 87 68]
	local111 = [3 3 3 3 3 3 0 3 3 3 3 3 3 0 143 129 103 94 68 57 36 65 77 101 115 141 156 178 13 15 20 17 24 26 45 50 49 42 47 38 37 20 220 209 190 168 159 132 110 142 166 173 195 211 222 238 152 162 163 171 173 179 197 205 199 196 193 183 177 157 5 22 34 36 55 55 48 49 -27 -15 -3 -5 20 47 57 59 58 61 51 42]
	[local201 14]
	local215
	local216
	local217
	local218
)
(procedure (localproc_0f53 param1 param2 param3 param4 param5 &tmp theTheTheHeading_2 temp1 theTheTheTheHeading_2 temp3 [theTheHeading_2 6] [temp10 14] temp24 temp25 temp26 temp27 temp28)
	(= temp24 1)
	(= temp25 -50)
	(= theTheTheHeading_2 0)
	(while (< theTheTheHeading_2 6)
		(if [param5 theTheTheHeading_2]
			(= temp1 0)
			(while (< temp1 14)
				(= [temp10 temp1] [param5 temp1])
				(++ temp1)
			)
			(= temp3 0)
			(= temp28 [temp10 theTheTheHeading_2])
			(= [temp10 theTheTheHeading_2] 0)
			(= temp1 temp28)
			(= theTheTheTheHeading_2 theTheTheHeading_2)
			(while (> temp1 0)
				(if (> (++ theTheTheTheHeading_2) 13)
					(= theTheTheTheHeading_2 0)
				)
				(switch theTheTheTheHeading_2
					(6 (++ temp3))
					(13 (-- temp3))
				)
				(++ [temp10 theTheTheTheHeading_2])
				(-- temp1)
			)
			(if
			(and (== theTheTheTheHeading_2 6) (not param4))
				(= temp3
					(+
						temp3
						(localproc_0f53
							param1
							param2
							0
							1
							[temp10 0]
							[temp10 1]
							[temp10 2]
							[temp10 3]
							[temp10 4]
							[temp10 5]
							[temp10 6]
							[temp10 7]
							[temp10 8]
							[temp10 9]
							[temp10 10]
							[temp10 11]
							[temp10 12]
							[temp10 13]
						)
					)
				)
			else
				(if
					(and
						(!= theTheTheTheHeading_2 6)
						(!= theTheTheTheHeading_2 13)
						(== [temp10 theTheTheTheHeading_2] 1)
						(> [temp10 (- 12 theTheTheTheHeading_2)] 0)
					)
					(= temp3 (+ [temp10 (- 12 theTheTheTheHeading_2)] 1))
					(= [temp10 theTheTheTheHeading_2] 0)
					(= [temp10 (- 12 theTheTheTheHeading_2)] 0)
				)
				(if (> param2 1)
					(if
						(==
							(= temp27
								(localproc_0f53
									(- 1 param1)
									(- param2 1)
									0
									0
									[temp10 7]
									[temp10 8]
									[temp10 9]
									[temp10 10]
									[temp10 11]
									[temp10 12]
									[temp10 13]
									[temp10 0]
									[temp10 1]
									[temp10 2]
									[temp10 3]
									[temp10 4]
									[temp10 5]
									[temp10 6]
								)
							)
							-50
						)
						(= temp27 0)
					)
					(= temp3 (- temp3 temp27))
				)
			)
			(cond 
				(
					(or
						(and param1 (> temp3 temp25))
						(and (not param1) (< temp3 temp25))
					)
					(= temp25 temp3)
					(= temp24 1)
					(= [theTheHeading_2 0] theTheTheHeading_2)
				)
				((== temp3 temp25)
					(++ temp24)
					(= [theTheHeading_2 (- temp24 1)] theTheTheHeading_2)
				)
			)
		)
		(++ theTheTheHeading_2)
	)
	(if (== param2 theArcadeDifficulty)
		(= theHeading_2
			[theTheHeading_2 (Random 0 (- temp24 1))]
		)
	)
	(return temp25)
)

(procedure (localproc_1126)
	(return
		(not
			(if
				(+
					[local78_3 0]
					[local78_3 1]
					[local78_3 2]
					[local78_3 3]
					[local78_3 4]
					[local78_3 5]
				)
				(+
					[local78_3 7]
					[local78_3 8]
					[local78_3 9]
					[local78_3 10]
					[local78_3 11]
					[local78_3 12]
				)
			else
				0
			)
		)
	)
)

(procedure (localproc_116d param1 param2)
	(if local82 (local82 stopUpd:))
	(= [local78_3 param1] param2)
	([local201 param1]
		startUpd:
		cel: (if (> param2 14) 14 else param2)
	)
	(= local82 [local201 param1])
)

(procedure (localproc_1267)
	(HandsOn)
	(theIconBar disable: 5 6 7 8)
	(theIconBar enable: 9)
	(tray setScript: moveScript)
)

(instance rm490 of Room
	(properties
		picture 490
		style PIXELDISSOLVE
	)
	
	(method (init)
		(HandsOff)
		(theIconBar disable: 9)
		(ego noun: 3)
		(= theGNewSpeed speed)
		(= speed 1)
		(= local76_3_2 0)
		(while (< local76_3_2 14)
			(= [local201 local76_3_2]
				((tray new:)
					cel: [local111 local76_3_2]
					x: (+
						[local83 (= [local78_3 local76_3_2] [local111 local76_3_2])]
						48
					)
					y: (+ [local97 local76_3_2] 37)
					heading: local76_3_2
					init:
					stopUpd:
					yourself:
				)
			)
			(++ local76_3_2)
		)
		(= theArcadeDifficulty arcadeDifficulty)
		(= local80 0)
		(super init:)
		(cSound setLoop: -1 changeTo: 490)
		(yesufuHead init:)
		(self setScript: startGame)
		(walkHandler add: self)
		(ego solvePuzzle: 285 3)
	)
	
	(method (doit)
		(super doit:)
		(if (tray script?) ((tray script?) doit:))
		(if (GameIsRestarting) (yesufuHead init:))
	)
	
	(method (dispose)
		(= speed theGNewSpeed)
		(walkHandler delete: self)
		((ego actions?) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(ego addHonor: -20)
				(curRoom newRoom: 480)
			)
			(2
				((ego actions?) doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tray of Prop
	(properties
		view 490
		signal $4100
	)
	
	(method (doVerb theVerb &tmp [temp0 40])
		(cond 
			((== theVerb 2) ((ego actions?) doVerb: theVerb))
			((== theVerb 1)
				(Message MsgGet 490 4 1 52 1 @temp0)
				(Print addTextF: @temp0 [local78_3 heading] init:)
			)
			((== theVerb 3) (curRoom doVerb: 3))
			((!= theVerb 4) (super doVerb: theVerb &rest))
			((< heading 6) (messager say: 4 4 53))
			((== heading 6) (messager say: 4 4 54))
			((== heading 13) (messager say: 4 4 55))
			([local78_3 heading]
				(tray setScript: 0)
				(= theHeading_3 heading)
				(curRoom setScript: playersMove)
			)
			(else (messager say: 4 4 56))
		)
	)
)

(instance startGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== global454 0)
						(= [local76_2 0] @local76_3)
						(= [local35 0] @local0)
						(= [local35 1] @local8)
						(= [local35 2] @local14)
						(egoTeller init: ego @local76_3 @local76_2)
						(yesuTeller init: yesufuHead @local0 @local35 @local78_4)
						(= seconds 2)
					)
					((== global454 1)
						(= [local76_2 0] @theHeading)
						(= [local35 0] @local17)
						(egoTeller init: ego @theHeading @local76_2)
						(yesuTeller init: yesufuHead @local17 @local35 @local78_4)
						(= seconds 2)
					)
					((== global454 2)
						(= [local76_2 0] @local78_2)
						(= [local35 0] @local26)
						(egoTeller init: ego @local78_2 @local76_2)
						(yesuTeller init: yesufuHead @local26 @local35 @local78_4)
						(= seconds 2)
					)
					(else
						(++ global454)
						(= [local76_2 0] @local78_2)
						(= [local35 0] @local26)
						(egoTeller init: ego @local78_2 @local76_2)
						(yesuTeller init: yesufuHead @local26 @local35 @local78_4)
						(localproc_1267)
						(self dispose:)
					)
				)
			)
			(1
				(cond 
					((== global454 0) (messager say: 2 6 15 0 self))
					((== global454 1) (messager say: 2 6 36 0 self))
					((== global454 2) (messager say: 2 6 34 0 self))
				)
				(++ global454)
			)
			(2
				(localproc_1267)
				(self dispose:)
			)
		)
	)
)

(instance playersMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local216 0)
				(HandsOff)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(1
				(= local78_4_2 [local78_3 theHeading_3])
				(globalSound number: 491 setLoop: 1 play: 127)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
				(localproc_116d theHeading_3 0)
			)
			(2
				(if (> (++ theHeading_3) 13) (= theHeading_3 0))
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(3
				(globalSound number: 491 setLoop: 1 play: 127)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
				(localproc_116d
					theHeading_3
					(++ [local78_3 theHeading_3])
				)
			)
			(4
				(= register 0)
				(cond 
					((-- local78_4_2) (= state (- state 3)) (= cycles 1))
					(
						(not
							(if (== theHeading_3 6) else (== theHeading_3 13))
						)
						(= cycles 1)
					)
					((localproc_1126) (curRoom setScript: gameDoneSequence))
					((and (== theHeading_3 13) (not local215))
						(++ local215)
						(= register 1)
						(messager say: 2 6 13 0 self)
					)
					((localproc_1126) (curRoom setScript: gameDoneSequence))
					(else (= register 2) (messager say: 2 6 2 0 self))
				)
			)
			(5
				(cond 
					((== register 1) (localproc_1267) (self dispose:))
					((== register 2) (curRoom setScript: computersMove))
					(
						(and
							(== [local78_3 theHeading_3] 1)
							[local78_3 (- 12 theHeading_3)]
						)
						(= ticks (+ (* (ego moveSpeed?) 3) 1))
						(= local78_4_2 (+ local78_4_2 [local78_3 theHeading_3]))
						(localproc_116d theHeading_3 0)
					)
					((localproc_1126) (curRoom setScript: gameDoneSequence))
					(else (= register 2) (messager say: 2 6 2 0 self))
				)
			)
			(6
				(if (== register 2)
					(curRoom setScript: computersMove)
				else
					(= theHeading_3 (- 12 theHeading_3))
					(= ticks (+ (* (ego moveSpeed?) 3) 1))
				)
			)
			(7
				(= local78_4_2 (+ local78_4_2 [local78_3 theHeading_3]))
				(localproc_116d theHeading_3 0)
				(if (< theHeading_3 6)
					(messager say: 2 6 6 0 self)
				else
					(= ticks (+ (* (ego moveSpeed?) 3) 1))
				)
			)
			(8
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(9
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
				(localproc_116d 13 (+ [local78_3 13] local78_4_2))
			)
			(10
				(if (localproc_1126)
					(curRoom setScript: gameDoneSequence)
				else
					(messager say: 2 6 2 0 self)
				)
			)
			(11
				(curRoom setScript: computersMove)
			)
		)
	)
)

(instance computersMove of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= local215 0) (= cycles 1))
			(1
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(2
				(HandsOff)
				(if local80
					(localproc_0f53
						1
						theArcadeDifficulty
						1
						0
						[local78_3 0]
						[local78_3 1]
						[local78_3 2]
						[local78_3 3]
						[local78_3 4]
						[local78_3 5]
						[local78_3 6]
						[local78_3 7]
						[local78_3 8]
						[local78_3 9]
						[local78_3 10]
						[local78_3 11]
						[local78_3 12]
						[local78_3 13]
					)
				else
					(= local80 1)
					(if (< (Random 0 9) 7)
						(= theHeading_2 (Random 3 5))
					else
						(= theHeading_2 (Random 0 1))
					)
					(Wait 0)
					(Wait 6)
				)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(3
				(= local78_4_2 [local78_3 theHeading_2])
				(localproc_116d theHeading_2 0)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(4
				(if (> (++ theHeading_2) 13) (= theHeading_2 0))
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(5
				(globalSound number: 491 setLoop: 1 play: 127)
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
				(localproc_116d
					theHeading_2
					(++ [local78_3 theHeading_2])
				)
			)
			(6
				(= register 0)
				(cond 
					((-- local78_4_2) (= state (- state 3)) (= cycles 1))
					(
						(not
							(if (== theHeading_2 6) else (== theHeading_2 13))
						)
						(= cycles 1)
					)
					((localproc_1126) (curRoom setScript: gameDoneSequence))
					((and (== theHeading_2 6) (not local216)) (= local216 1) (curRoom setScript: self))
					(else (= register 3) (messager say: 2 6 4 0 self))
				)
			)
			(7
				(cond 
					((== register 3) (localproc_1267) (self dispose:))
					(
						(and
							(== [local78_3 theHeading_2] 1)
							[local78_3 (- 12 theHeading_2)]
						)
						(= ticks (+ (* (ego moveSpeed?) 3) 1))
						(= local78_4_2 (+ local78_4_2 [local78_3 theHeading_2]))
						(localproc_116d theHeading_2 0)
					)
					((localproc_1126) (curRoom setScript: gameDoneSequence))
					(else (= register 3) (messager say: 2 6 4 0 self))
				)
			)
			(8
				(if (== register 3)
					(localproc_1267)
					(self dispose:)
				else
					(= theHeading_2 (- 12 theHeading_2))
					(= ticks (+ (* (ego moveSpeed?) 3) 1))
				)
			)
			(9
				(if (and (< 6 theHeading_2) (< theHeading_2 13))
					(messager say: 2 6 3 1 self)
				else
					(= ticks (+ (* (ego moveSpeed?) 3) 1))
				)
				(= local78_4_2 (+ local78_4_2 [local78_3 theHeading_2]))
				(localproc_116d theHeading_2 0)
			)
			(10
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
			)
			(11
				(= ticks (+ (* (ego moveSpeed?) 3) 1))
				(localproc_116d 6 (+ [local78_3 6] local78_4_2))
			)
			(12
				(if (localproc_1126)
					(curRoom setScript: gameDoneSequence)
				else
					(messager say: 2 6 4 0 self)
				)
			)
			(13
				(localproc_1267)
				(self dispose:)
			)
		)
	)
)

(instance gameDoneSequence of Script
	(properties)
	
	(method (dispose)
		(localproc_1267)
		(super dispose: &rest)
	)
	
	(method (changeState newState &tmp [temp0 70] newIconI temp71)
		(switch (= state newState)
			(0
				(ego useSkill: 1 20)
				(HandsOff)
				(messager say: 2 6 7 0 self)
			)
			(1
				(cond 
					((< [local78_3 6] [local78_3 13]) (messager say: 2 6 9 0 self))
					((> [local78_3 6] [local78_3 13]) (messager say: 2 6 8 0 self))
					(else (messager say: 2 6 50 0 self))
				)
			)
			(2 (= cycles 8))
			(3
				(if (== local217 5)
					(= register 1)
					(messager say: 2 6 43 0 self)
				else
					(messager say: 2 6 10 0 self)
				)
			)
			(4
				(if register
					(curRoom newRoom: 480)
				else
					(= temp71 (theGame setCursor: 999))
					((= qg3Controls (GameControls new:))
						window:
							((GloryWindow new:)
								top: 51
								left: 64
								bottom: 143
								right: 257
								priority: 15
								yourself:
							)
					)
					((= newIconI ((ScriptID 0 17) new: 1 0 51 1 490))
						view: 935
						loop: 1
						cel: 0
						nsTop: 2
						nsLeft: 2
						modifiers: 1
						signal: 132
					)
					(qg3Controls add: newIconI)
					((= newIconI ((ScriptID 0 17) new: 1 0 0 1 490))
						nsTop: 70
						nsLeft: 29
						cursor: 1
					)
					(qg3Controls add: newIconI)
					((= newIconI ((ScriptID 0 17) new: 1 0 0 2 490))
						nsTop: 70
						nsLeft: 129
						cursor: 0
					)
					(qg3Controls add: newIconI)
					(= newIconI (IconItem new:))
					(newIconI
						nsTop: 25
						nsLeft: 29
						view: 490
						loop: 0
						cel: [local78_3 6]
						maskView: 490
						maskLoop: 0
						maskCel: [local78_3 newIconI]
						signal: 132
					)
					(qg3Controls add: newIconI)
					(= newIconI (IconItem new:))
					(newIconI
						nsTop: 25
						nsLeft: 129
						view: 490
						loop: 0
						cel: [local78_3 13]
						maskView: 490
						maskLoop: 0
						maskCel: [local78_3 newIconI]
						signal: 132
					)
					(qg3Controls add: newIconI)
					((= newIconI (textI new: 1 0 49 1 490 [local78_3 6]))
						nsTop: 50
						nsLeft: 19
						signal: 132
					)
					(qg3Controls add: newIconI)
					((= newIconI (textI new: 1 0 48 1 490 [local78_3 13]))
						nsTop: 50
						nsLeft: 125
						signal: 132
					)
					(qg3Controls add: newIconI)
					(qg3Controls show: dispose:)
					(theGame setCursor: temp71)
					(if controlRet
						(++ local217)
						(messager say: 2 6 11 0 self)
						(= local76_3_2 0)
						(while (< local76_3_2 14)
							([local201 (= [local78_3 local76_3_2] [local111 local76_3_2])]
								cel: [local111 local76_3_2]
								startUpd:
							)
							(++ local76_3_2)
						)
						(= local80 0)
					else
						(= register 1)
						(messager say: 2 6 12 0 self)
					)
				)
			)
			(5
				(if register
					(curRoom newRoom: 480)
				else
					(= local76_3_2 0)
					(while (< local76_3_2 14)
						([local201 local76_3_2] stopUpd:)
						(++ local76_3_2)
					)
					(if (== local217 1)
						(cond 
							((== global454 0) (messager say: 2 6 37 0 self))
							((== global454 1) (messager say: 2 6 38 0 self))
							((== global454 2) (messager say: 2 6 41 0 self))
							(else (= ticks (+ (* (ego moveSpeed?) 3) 1)))
						)
					else
						(= ticks (+ (* (ego moveSpeed?) 3) 1))
					)
				)
			)
			(6
				(if local218
					(= local218 0)
					(self dispose:)
				else
					(= local218 1)
					(curRoom setScript: computersMove)
				)
			)
		)
	)
)

(instance textI of IconItem
	(properties
		view 935
		loop 2
		cel 0
	)
	
	(method (new param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 [temp3 30])
		(= temp0 (Clone self))
		(if argc
			(= temp1
				(Message MsgSize param5 param1 param2 param3 param4)
			)
			(temp0 message: (Memory MNewPtr temp1))
			(Message MsgGet param5 param1 param2 param3 param4 @temp3)
			(Format (temp0 message?) @temp3 param6)
		)
		(return temp0)
	)
	
	(method (dispose)
		(Memory MDisposePtr message)
		(super dispose:)
	)
	
	(method (show)
		(= nsRight nsLeft)
		(Display message
			p_at nsLeft (= nsBottom nsTop)
			p_font 0
			p_color 17
			p_mode modifiers
			p_width 189
		)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
	
	(method (mask)
	)
)

(instance yesufuHead of View
	(properties
		x 5
		y 8
		noun 2
		view 981
		loop 1
		priority 1
		signal $0010
	)
	
	(method (init)
		(Graph GFillRect 10 10 98 126 1 12 -1)
		(Graph GShowBits 10 10 98 126 1)
		(super init:)
		(DrawCel 934 2 0 10 10 2)
	)
)

(instance yesuTeller of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				25
				local217
				26
				local217
				31
				local217
				32
				local217
				39
				local217
				42
				local217
		)
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if (== query 44) (HandsOff) (messager caller: self))
		(return 1)
	)
	
	(method (doChild)
		(if (not (Btst 286)) (ego addHonor: 20))
		(ego solvePuzzle: 286 3)
		(return 1)
	)
	
	(method (cue)
		(messager caller: 0)
		(curRoom newRoom: 480)
	)
)

(instance yesuTalker of Talker
	(properties
		x 5
		y 8
		view 981
		loop 1
		talkWidth 260
		back 57
		textX 20
		textY 100
	)
	
	(method (init)
		(super init: yesuBust yesuEyes yesuMouth &rest)
	)
)

(instance yesuMouth of Prop
	(properties
		nsTop 54
		nsLeft 39
		view 981
	)
)

(instance yesuEyes of Prop
	(properties
		nsTop 38
		nsLeft 34
		view 981
		loop 2
	)
)

(instance yesuBust of View
	(properties
		nsTop 21
		nsLeft 31
		view 981
		loop 3
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1 (messager say: 2 6 5 1 self))
			(2 (self init:))
		)
	)
)
