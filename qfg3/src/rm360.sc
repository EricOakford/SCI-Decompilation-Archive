;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include sci.sh)
(use Main)
(use GloryWindow)
(use Intrface)
(use Print)
(use Talker)
(use IconBar)
(use GControl)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm360 0
	anubisTalker 1
	desTalker 2
)

(local
	[local0 6] = [5 2 1 7 8 11]
	[local6 6] = [6 4 9 3 1]
	[local12 6] = [8 0 11 2 10 5]
	theActor_2
	local19
	local20
	local21
	local22
	local23
	local24
	local25
	local26
	[theTheTheActor 3]
	[theActor 3]
	local33
	theTheActor
	local35
	[local36 200]
)
(procedure (localproc_01d1)
	(= local33 (Palette palSAVE))
	(DrawPic 360 dpOPEN_PIXELATION)
	(Palette palRESTORE local33)
	(Animate (cast elements?) 1)
)

(procedure (localproc_01f8)
	(return
		(switch theTheActor
			(0 (return 60))
			(1 (return 61))
			(2 (return 62))
			(else  (return 63))
		)
	)
)

(procedure (localproc_0220 &tmp temp0)
	(return
		(switch
			(= temp0
				(+
					(= temp0
						(+
							(= temp0 (+ (= temp0 0) [theTheTheActor 0]))
							[theTheTheActor 1]
						)
					)
					[theTheTheActor 2]
				)
			)
			(3 (return 68))
			(4 (return 67))
			(5 (return 66))
			(else  (return 65))
		)
	)
)

(procedure (localproc_0265)
	(return
		(switch [theActor 0]
			(keyObj (return 35))
			(pentagram (return 36))
			(sword (return 34))
			(heart (return 37))
			(cup (return 38))
			(ankh (return 39))
		)
	)
)

(procedure (localproc_02b1)
	(return
		(switch [theActor 1]
			(ringObj (return 48))
			(keyObj (return 47))
			(infinity (return 46))
			(yinYang (return 44))
			(raisedFist (return 43))
			(hourglass (return 45))
		)
	)
)

(procedure (localproc_02fd)
	(return
		(switch [theActor 2]
			(ringObj (return 50))
			(pentagram (return 52))
			(sword (return 54))
			(cup (return 49))
			(candle (return 53))
			(ankh (return 51))
		)
	)
)

(procedure (localproc_0349 &tmp theTheCursor temp1)
	(= temp1 (Message msgSIZE 360 2 6 local19 2))
	(= local22 (Memory memALLOC_NONCRIT temp1))
	(= temp1 (Message msgSIZE 360 2 6 local19 3))
	(= local23 (Memory memALLOC_NONCRIT temp1))
	(= temp1 (Message msgSIZE 360 2 6 local19 4))
	(= local24 (Memory memALLOC_NONCRIT temp1))
	(= temp1 (Message msgSIZE 360 2 6 local19 5))
	(= local25 (Memory memALLOC_NONCRIT temp1))
	(= temp1 (Message msgSIZE 360 2 6 local19 6))
	(= local26 (Memory memALLOC_NONCRIT temp1))
	(Message msgGET 360 2 6 local19 1 @local36)
	(Message msgGET 360 2 6 local19 2 local22)
	(Message msgGET 360 2 6 local19 3 local23)
	(Message msgGET 360 2 6 local19 4 local24)
	(Message msgGET 360 2 6 local19 5 local25)
	(Message msgGET 360 2 6 local19 6 local26)
	(myPrint addText: @local36 init:)
	(= theTheCursor theCursor)
	(quest init: show: dispose:)
	(while local35
		(myPrint addText: @local36 init:)
		(quest init: show: dispose:)
	)
	(Memory memFREE local22)
	(Memory memFREE local23)
	(Memory memFREE local24)
	(Memory memFREE local25)
	(Memory memFREE local26)
	(++ local21)
	(theGame setCursor: theTheCursor)
	(choices cycles: 5)
)

(instance rm360 of Rm
	(properties
		noun 13
		picture 360
	)
	
	(method (init)
		(cSound fade:)
		(Palette palSET_FLAG 72 255 4)
		(ringObj init:)
		(keyObj init:)
		(pentagram init:)
		(infinity init:)
		(yinYang init:)
		(sword init:)
		(raisedFist init:)
		(heart init:)
		(cup init:)
		(hourglass init:)
		(candle init:)
		(ankh init:)
		(anubis init:)
		(cast eachElementDo: #hide)
		(soundFx number: 360 setLoop: -1 play: 127)
		(super init:)
		(self setScript: choices)
	)
	
	(method (doit)
		(super doit:)
		(Palette palANIMATE 72 255 1)
	)
)

(instance choices of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				(messager say: 2 6 10 0 self)
			)
			(2 (= seconds 2))
			(3
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local0 temp0]) show:)
					(++ temp0)
				)
				(localproc_01d1)
				(= cycles 5)
			)
			(4
				(messager say: 2 6 11 0 self)
			)
			(5
				(HandsOn 1 4 6 5 8)
				(User canControl: 1 canInput: 1)
				(theIconBar enable: 3 curIcon: (theIconBar at: 3))
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
			)
			(6
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local0 temp0]) hide:)
					(++ temp0)
				)
				(theActor_2 show:)
				(localproc_01d1)
				(= cycles 5)
			)
			(7 (= cycles 20))
			(8 (messager say: 2 6 4 0 self))
			(9 (localproc_0349))
			(10
				(messager say: 2 6 13 0 self)
			)
			(11
				(theActor_2 hide:)
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local6 temp0]) show:)
					(++ temp0)
				)
				(localproc_01d1)
				(= cycles 5)
			)
			(12
				(HandsOn 1 4 6 5 8)
				(User canControl: 1 canInput: 1)
				(theIconBar enable: 3 curIcon: (theIconBar at: 3))
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
			)
			(13
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local6 temp0]) hide:)
					(++ temp0)
				)
				(theActor_2 show:)
				(localproc_01d1)
				(= cycles 5)
			)
			(14 (= seconds 2))
			(15
				(messager say: 2 6 14 0 self)
			)
			(16 (localproc_0349))
			(17
				(theActor_2 hide:)
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local12 temp0]) show:)
					(++ temp0)
				)
				(localproc_01d1)
				(= cycles 5)
			)
			(18
				(messager say: 2 6 2 0 self)
			)
			(19
				(HandsOn 1 4 6 5 8)
				(User canControl: 1 canInput: 1)
				(theIconBar enable: 3 curIcon: (theIconBar at: 3))
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
			)
			(20
				(= temp0 0)
				(while (< temp0 6)
					((cast at: [local12 temp0]) hide:)
					(++ temp0)
				)
				(theActor_2 show:)
				(localproc_01d1)
				(= cycles 5)
			)
			(21 (= seconds 2))
			(22
				(messager say: 2 6 14 0 self)
			)
			(23 (localproc_0349))
			(24
				(theActor_2 hide:)
				(localproc_01d1)
				(= cycles 3)
			)
			(25
				(anubis show:)
				(localproc_01d1)
				(= cycles 3)
			)
			(26
				(messager say: 3 6 30 0 self)
			)
			(27
				(if
					(or
						(== [theTheTheActor 0] 4)
						(== [theTheTheActor 1] 4)
						(== [theTheTheActor 2] 4)
					)
					(curRoom setScript: leave 0 0)
				else
					(ego addHonor: 30 solvePuzzle: 254 10)
					(self cue:)
				)
			)
			(28
				(messager say: 3 6 32 0 self)
			)
			(29
				(messager say: 2 6 41 0 self)
			)
			(30
				([theActor 0] show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(31 (= seconds 2))
			(32
				(messager say: 2 6 (localproc_0265) 0 self)
			)
			(33
				([theActor 0] hide:)
				(localproc_01d1)
				(= cycles 3)
			)
			(34
				(messager say: 2 6 40 0 self)
			)
			(35
				([theActor 1] show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(36 (= seconds 2))
			(37
				(messager say: 2 6 (localproc_02b1) 0 self)
			)
			(38
				([theActor 1] hide:)
				(localproc_01d1)
				(= cycles 3)
			)
			(39
				(messager say: 2 6 42 0 self)
			)
			(40
				([theActor 2] show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(41 (= seconds 2))
			(42
				(messager say: 2 6 (localproc_02fd) 0 self)
			)
			(43 (= seconds 2))
			(44
				(anubis hide:)
				([theActor 2] hide:)
				(yinYang show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(45
				(switch heroType
					(0
						(if
							(or
								(== [theActor 0] sword)
								(== [theActor 1] raisedFist)
								(== [theActor 2] sword)
							)
							(ego solvePuzzle: 255 5)
							(messager say: 2 6 55 0 self)
						else
							(messager say: 2 6 57 0 self)
						)
					)
					(1
						(if
							(or
								(== [theActor 0] pentagram)
								(== [theActor 1] infinity)
								(== [theActor 2] pentagram)
							)
							(ego solvePuzzle: 255 5)
							(messager say: 2 6 55 0 self)
						else
							(messager say: 2 6 58 0 self)
						)
					)
					(2
						(if
							(or
								(== [theActor 0] keyObj)
								(== [theActor 1] keyObj)
								(== [theActor 1] ringObj)
								(== [theActor 2] ringObj)
							)
							(ego solvePuzzle: 255 5)
							(messager say: 2 6 55 0 self)
						else
							(messager say: 2 6 56 0 self)
						)
					)
					(else 
						(if
							(or
								(== [theActor 0] ankh)
								(== [theActor 0] heart)
								(== [theActor 1] yinYang)
								(== [theActor 2] ankh)
							)
							(ego solvePuzzle: 255 5)
							(messager say: 2 6 55 0 self)
						else
							(messager say: 2 6 59 0 self)
						)
					)
				)
			)
			(46
				(yinYang hide:)
				(localproc_01d1)
				(= cycles 5)
			)
			(47 (= seconds 2))
			(48
				(hourglass show: 1)
				(localproc_01d1)
				(= cycles 5)
			)
			(49 (= seconds 1))
			(50
				(cond 
					(
						(or
							(== [theTheTheActor 0] [theTheTheActor 1])
							(== [theTheTheActor 0] [theTheTheActor 2])
						)
						(= theTheActor [theTheTheActor 0])
					)
					((== [theTheTheActor 1] [theTheTheActor 2]) (= theTheActor [theTheTheActor 1]))
					(else (= theTheActor 999))
				)
				(if (!= theTheActor 999)
					(messager say: 2 6 (localproc_01f8) 0 self)
				else
					(messager say: 2 6 64 0 self)
				)
			)
			(51
				(if (!= theTheActor 999)
					(self cue:)
				else
					(messager say: 2 6 (localproc_0220) 0 self)
				)
			)
			(52
				(hourglass hide:)
				(localproc_01d1)
				(= cycles 3)
			)
			(53 (= seconds 2))
			(54
				(anubis show:)
				(localproc_01d1)
				(= cycles 3)
			)
			(55 (= seconds 1))
			(56
				(messager say: 3 6 69 0 self)
			)
			(57
				(anubis hide:)
				(heart show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(58
				(messager say: 2 6 70 0 self)
			)
			(59
				(heart hide:)
				(sword show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(60
				(messager say: 2 6 71 0 self)
			)
			(61
				(sword hide:)
				(keyObj show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(62
				(messager say: 2 6 72 0 self)
			)
			(63
				(keyObj hide:)
				(pentagram show: 1)
				(localproc_01d1)
				(= cycles 3)
			)
			(64
				(switch heroType
					(0
						(messager say: 2 6 73 0 self)
					)
					(1
						(messager say: 2 6 74 0 self)
					)
					(2
						(messager say: 2 6 75 0 self)
					)
					(else 
						(messager say: 2 6 76 0 self)
					)
				)
			)
			(65
				(pentagram hide:)
				(anubis show:)
				(localproc_01d1)
				(= cycles 3)
			)
			(66
				(messager say: 3 6 77 0 self)
			)
			(67
				(curRoom setScript: leave 0 1)
			)
		)
	)
)

(instance leave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 12)
				(if register
					(self cue:)
				else
					(messager say: 3 6 31 0 self)
				)
			)
			(1
				(anubis hide:)
				(curRoom drawPic: 0 10)
				(= seconds 3)
			)
			(2
				(soundFx fade:)
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance ringObj of Actor
	(properties
		x 75
		y 142
		noun 15
		view 361
		loop 2
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= [theActor local20] self)
			(switch local20
				(0 (= local19 20) (++ local20))
				(1 (= local19 21) (++ local20))
				(else  (= local19 29))
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 145) (= y 30))
		(super show:)
		(self stopUpd:)
	)
)

(instance keyObj of Actor
	(properties
		x 106
		y 57
		noun 5
		view 361
		loop 2
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 6) (++ local20))
				(else 
					(= local19 17)
					(++ local20)
				)
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 150) (= y 18))
		(super show:)
		(self stopUpd:)
	)
)

(instance pentagram of Actor
	(properties
		x 213
		y 47
		noun 6
		view 361
		loop 2
		cel 1
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 5) (++ local20))
				(else  (= local19 26))
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 139) (= y 16))
		(super show:)
		(self stopUpd:)
	)
)

(instance infinity of Actor
	(properties
		x 208
		y 137
		noun 14
		view 361
		loop 1
		cel 2
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(= local19 19)
			(++ local20)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 133) (= y 23))
		(super show:)
		(self stopUpd:)
	)
)

(instance yinYang of Actor
	(properties
		x 133
		y 90
		noun 8
		view 361
		cel 4
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(= local19 18)
			(++ local20)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 134) (= y 14))
		(super show:)
		(self stopUpd:)
	)
)

(instance sword of Actor
	(properties
		x 230
		y 94
		noun 17
		view 361
		loop 2
		cel 3
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 3) (++ local20))
				(else  (= local19 28))
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 156) (= y 20))
		(super show:)
		(self stopUpd:)
	)
)

(instance raisedFist of Actor
	(properties
		x 267
		y 73
		noun 7
		view 361
		cel 3
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(= local19 15)
			(++ local20)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 144) (= y 9))
		(super show:)
		(self stopUpd:)
	)
)

(instance cup of Actor
	(properties
		x 137
		y 130
		noun 9
		view 361
		cel 2
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 12) (++ local20))
				(1 (= local19 22) (++ local20))
				(else  (= local19 23))
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 137) (= y 16))
		(super show:)
		(self stopUpd:)
	)
)

(instance heart of Actor
	(properties
		x 21
		y 93
		noun 10
		view 361
		loop 1
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(= local19 7)
			(++ local20)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 136) (= y 19))
		(super show:)
		(self stopUpd:)
	)
)

(instance hourglass of Actor
	(properties
		x 74
		y 38
		noun 11
		view 361
		loop 1
		cel 1
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 9) (++ local20))
				(else 
					(= local19 16)
					(++ local20)
				)
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 146) (= y 15))
		(super show:)
		(self stopUpd:)
	)
)

(instance candle of Actor
	(properties
		x 191
		y 59
		noun 12
		view 361
		cel 1
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(= local19 24)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 153) (= y 19))
		(super show:)
		(self stopUpd:)
	)
)

(instance ankh of Actor
	(properties
		x 141
		y 34
		noun 4
		view 361
	)
	
	(method (doVerb theVerb)
		(= [theActor local20] self)
		(if (== theVerb 4)
			(switch local20
				(0 (= local19 8) (++ local20))
				(else  (= local19 25))
			)
			(= theActor_2 self)
			(HandsOff)
			(globalSound number: 361 play: 127 setLoop: 1)
			(choices cue:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (show)
		(if argc (= x 144) (= y 13))
		(super show:)
		(self stopUpd:)
	)
)

(instance anubis of Actor
	(properties
		x 10
		y 4
		noun 3
		view 361
		loop 4
	)
)

(instance quest of GameControls
	(properties)
	
	(method (init)
		(theGame setCursor: 999)
		(= window
			((GloryWindow new:)
				top: 0
				left: 30
				bottom: 175
				right: 295
				priority: -1
				yourself:
			)
		)
		(self add: upIcon aIcon bIcon cIcon xIcon eIcon)
		(super init: &rest)
	)
)

(instance aIcon of IconI
	(properties
		view 361
		loop 3
		cel 0
		nsTop 15
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= nsRight 240)
		(= nsBottom (+ nsTop 30))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display
			local22
			dsFONT
			smallFont
			dsCOORD
			20
			15
			dsCOLOR
			1
			dsWIDTH
			245
		)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= [theTheTheActor local21] 0)
		(quest state: (& (quest state?) $ffdf))
		(= local35 0)
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 1)
		)
		(Display
			local22
			dsFONT
			smallFont
			dsCOORD
			20
			15
			dsCOLOR
			temp0
			dsWIDTH
			245
		)
	)
)

(instance bIcon of IconI
	(properties
		view 361
		loop 3
		cel 0
		nsTop 45
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= nsRight 240)
		(= nsBottom (+ nsTop 30))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display
			local23
			dsFONT
			smallFont
			dsCOORD
			20
			45
			dsCOLOR
			1
			dsWIDTH
			245
		)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= [theTheTheActor local21] 1)
		(quest state: (& (quest state?) $ffdf))
		(= local35 0)
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 1)
		)
		(Display
			local23
			dsFONT
			smallFont
			dsCOORD
			20
			45
			dsCOLOR
			temp0
			dsWIDTH
			245
		)
	)
)

(instance cIcon of IconI
	(properties
		view 361
		loop 3
		cel 0
		nsTop 75
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= nsRight 240)
		(= nsBottom (+ nsTop 30))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display
			local24
			dsFONT
			smallFont
			dsCOORD
			20
			75
			dsCOLOR
			1
			dsWIDTH
			245
		)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= [theTheTheActor local21] 2)
		(quest state: (& (quest state?) $ffdf))
		(= local35 0)
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 1)
		)
		(Display
			local24
			dsFONT
			smallFont
			dsCOORD
			20
			75
			dsCOLOR
			temp0
			dsWIDTH
			245
		)
	)
)

(instance xIcon of IconI
	(properties
		view 361
		loop 3
		cel 0
		nsTop 105
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= nsRight 240)
		(= nsBottom (+ nsTop 30))
		(Display
			local25
			dsFONT
			smallFont
			dsCOORD
			20
			105
			dsCOLOR
			1
			dsWIDTH
			245
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= [theTheTheActor local21] 3)
		(quest state: (& (quest state?) $ffdf))
		(= local35 0)
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= temp0 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 1)
		)
		(Display
			local25
			dsFONT
			smallFont
			dsCOORD
			20
			105
			dsCOLOR
			temp0
			dsWIDTH
			245
		)
	)
)

(instance eIcon of IconI
	(properties
		view 361
		loop 3
		cel 0
		nsTop 135
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= nsRight 240)
		(= nsBottom (+ nsTop 30))
		(Display
			local26
			dsFONT
			smallFont
			dsCOORD
			20
			135
			dsCOLOR
			1
			dsWIDTH
			245
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= [theTheTheActor local21] 4)
		(quest state: (& (quest state?) $ffdf))
		(= local35 0)
	)
	
	(method (highlight param1 &tmp temp0)
		(if param1
			(= temp0 46)
			(DrawCel view loop 1 nsLeft nsTop -1)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= temp0 1)
		)
		(Display
			local26
			dsFONT
			smallFont
			dsCOORD
			20
			135
			dsCOLOR
			temp0
			dsWIDTH
			245
		)
	)
)

(instance upIcon of IconI
	(properties
		view 361
		loop 3
		cel 2
		nsLeft 250
		nsTop 0
		signal $0101
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 7])
		(= signal (| signal $0020))
		(if argc
			(= nsRight (+ (= nsLeft 250) (CelWide view loop cel)))
			(= nsTop 0)
			(= nsBottom (CelHigh view loop cel))
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= local35 1)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 3 nsLeft nsTop -1)
		else
			(DrawCel view loop 2 nsLeft nsTop -1)
		)
	)
)

(instance anubisTalker of Narrator
	(properties
		back 57
	)
	
	(method (doit)
		(Palette palANIMATE 72 255 1)
		(super doit:)
	)
)

(instance desTalker of Narrator
	(properties
		back 57
	)
	
	(method (doit)
		(Palette palANIMATE 72 255 1)
		(super doit:)
	)
)

(instance myPrint of Print
	(properties)
	
	(method (addText theTheCurRoomNum &tmp theTheTheCurRoomNum theTheTheCurRoomNum_2 theTheTheCurRoomNum_3 temp3 theTheTheCurRoomNum_4 theTheTheCurRoomNum_5 theCurRoomNum temp7 temp8)
		(if (not dialog) (= dialog (myDialog new:)))
		(if (> argc 3)
			(= theTheTheCurRoomNum [theTheCurRoomNum 0])
			(= theTheTheCurRoomNum_2 [theTheCurRoomNum 1])
			(= theTheTheCurRoomNum_3 [theTheCurRoomNum 2])
			(= temp3
				(if [theTheCurRoomNum 3] [theTheCurRoomNum 3] else 1)
			)
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(= theCurRoomNum curRoomNum)
			(if (>= argc 5)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 4])
				(if (>= argc 6)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 5])
					(if (>= argc 7) (= theCurRoomNum [theTheCurRoomNum 6]))
				)
			)
			(if
				(= temp8
					(Message
						msgSIZE
						theCurRoomNum
						theTheTheCurRoomNum
						theTheTheCurRoomNum_2
						theTheTheCurRoomNum_3
						temp3
					)
				)
				(= temp7 (Memory memALLOC_CRIT temp8))
				(if
					(Message
						msgGET
						theCurRoomNum
						theTheTheCurRoomNum
						theTheTheCurRoomNum_2
						theTheTheCurRoomNum_3
						temp3
						temp7
					)
					(dialog
						add:
							((DText new:)
								text: temp7
								font: font
								mode: mode
								setSize: width
								moveTo: (+ 4 theTheTheCurRoomNum_4) (+ 4 theTheTheCurRoomNum_5)
								yourself:
							)
						setSize:
					)
				)
			)
		else
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(if (>= argc 2)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 1])
				(if (>= argc 3)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 2])
				)
			)
			(= temp7
				(Memory
					memALLOC_CRIT
					(+ (StrLen [theTheCurRoomNum 0]) 1)
				)
			)
			(StrCpy temp7 [theTheCurRoomNum 0])
			(dialog
				add:
					((DText new:)
						text: temp7
						font: font
						mode: mode
						setSize: width
						moveTo: (+ 4 theTheTheCurRoomNum_4) (+ 4 theTheTheCurRoomNum_5)
						yourself:
					)
				setSize:
			)
		)
	)
)

(instance myDialog of Dialog
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 temp2)
		(= gameTime (+ tickOffset (GetTime)))
		(= temp2 0)
		(self eachElementDo: #init)
		(if theItem (theItem select: 0))
		(= theItem
			(if (and argc param1)
				param1
			else
				(self firstTrue: #checkState 1)
			)
		)
		(if theItem (theItem select: 1))
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTicks (GetTime))
		else
			(= eatTheMice 0)
		)
		(= temp1 0)
		(while (not temp1)
			(Palette palANIMATE 72 255 1)
			(Animate (cast elements?) 1)
			(= gameTime (+ tickOffset (GetTime)))
			(self eachElementDo: #cycle)
			(= temp0 ((Event new:) localize:))
			(if eatTheMice
				(-- eatTheMice)
				(if (== (temp0 type?) 1) (temp0 type: 0))
				(while (== lastTicks (GetTime))
				)
				(= lastTicks (GetTime))
			)
			(= temp1 (self handleEvent: temp0))
			(temp0 dispose:)
			(if (self check:) (break))
			(if (== temp1 -2)
				(= temp1 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(return temp1)
	)
)
