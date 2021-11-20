;;; Sierra Script 1.0 - (do not remove this comment)
(script# 810)
(include sci.sh)
(use Main)
(use GloryRm)
(use SView)
(use IconBar)
(use Sound)
(use Motion)
(use System)

(public
	combat 0
	proc810_1 1
	proc810_2 2
	bloodDrop 3
	jumpUp 4
	jumpForward 5
	jumpAttack 6
	jumpVAttack 7
	throwDagger 8
	toBomb 9
	highSlash 10
	lowSlash 11
	proc810_12 12
	proc810_13 13
)

(local
	local0
	local1
	local2
	local3
	local4
	local5 =  5
	local6
	local7
	local8
	theTicks
	local10
	local11
	local12
	local13
	local14
	local15 =  15
	theGABad2Health
	theGABad3Health
	theGABad4Health
	local19
	theHeroType
)
(procedure (proc810_1 param1 &tmp temp0 temp1)
	(switch param1
		(18
			(= temp1 80)
			(= temp0 local2)
		)
		(19
			(= temp1 80)
			(= temp0 local3)
		)
		(17
			(= temp1 104)
			(= temp0 local4)
		)
		(-1
			(return
				(if monsterHealth
					(/ (* 104 (/ (* monsterHealth 80) global180)) 80)
				else
					0
				)
			)
		)
	)
	(return
		(if (> [egoStats param1] 0)
			(/ (* temp1 (/ (* [egoStats param1] 80) temp0)) 80)
		else
			0
		)
	)
)

(procedure (proc810_2 &tmp temp0)
	(if
		(<=
			(= temp0
				(-
					(+ (/ [egoStats 5] 10) zapPower local7)
					(+ local8 (Random 1 10))
				)
			)
			0
		)
		(= temp0 3)
	)
	(if zapPower
		(= zapPower 0)
		((ScriptID 40 8) cel: 2)
		(UpdateScreenItem (ScriptID 40 8))
	)
	(return temp0)
)

(procedure (proc810_12 param1)
	(return
		(switch param1
			(0 (return 0))
			(10 (return 1))
			(else 
				(return (>= param1 (Random 1 10)))
			)
		)
	)
)

(procedure (proc810_13 param1 param2 &tmp theTheMusic2)
	(if (< (theGame _detailLevel?) 5) (return))
	(switch param1
		(0 (= theTheMusic2 theMusic2))
		(1 (= theTheMusic2 theMusic3))
		(2 (= theTheMusic2 spellSound1))
		(3 (= theTheMusic2 spellSound2))
		(4 (= theTheMusic2 spellSound3))
		(else  (return))
	)
	(theTheMusic2 flags: 5 setLoop: 1 number: param2 play:)
)

(procedure (localproc_0277 param1 param2 &tmp temp0)
	(asm
		lap      param1
		lagi     egoStats
		not     
		bnt      code_0283
		ldi      0
		ret     
code_0283:
		lsp      param1
		ldi      3
		eq?     
		bnt      code_0296
		pushi    1
		pushi    14
		callb    Btst,  2
		bnt      code_0296
		ldi      0
		ret     
code_0296:
		lsp      param1
		ldi      1
		ne?     
		bnt      code_02a2
		lsp      param2
		ldi      3
		mul     
code_02a2:
		lsp      param2
		ldi      0
		ge?     
		bnt      code_02ad
		ldi      1
		jmp      code_02af
code_02ad:
		ldi      65535
code_02af:
		sat      temp0
		pushi    1
		lsp      param2
		callk    Abs,  2
		push    
		lap      param1
		lagi     egoStats
		gt?     
		bnt      code_02c9
		lap      param1
		lsgi     egoStats
		lat      temp0
		mul     
		sap      param2
code_02c9:
		ldi      16
		lsgi     egoStats
		pushi    1
		lsp      param2
		callk    Abs,  2
		push    
		ldi      19
		add     
		push    
		ldi      20
		div     
		add     
		push    
		ldi      16
		sagi     egoStats
		lap      param1
		lsgi     skillTicks
		lap      param2
		add     
		push    
		lap      param1
		sagi     skillTicks
		lap      param1
		lsgi     skillTicks
		lagi     egoStats
		ge?     
		bnt      code_032a
		lap      param1
		lsgi     skillTicks
		lagi     egoStats
		sub     
		push    
		lap      param1
		sagi     skillTicks
		lap      param1
		lsgi     egoStats
		pushi    2
		pushi    2
		pushi    4
		callk    Random,  4
		add     
		push    
		lap      param1
		sagi     egoStats
		push    
		ldi      400
		gt?     
		bnt      code_0365
		pushi    400
		lap      param1
		sagi     egoStats
		jmp      code_0365
code_032a:
		lap      param1
		lsgi     skillTicks
		ldi      0
		lt?     
		bnt      code_0362
		lap      param1
		lsgi     skillTicks
		lagi     egoStats
		add     
		push    
		lap      param1
		sagi     skillTicks
		lap      param1
		lsgi     egoStats
		pushi    2
		pushi    2
		pushi    4
		callk    Random,  4
		sub     
		push    
		lap      param1
		sagi     egoStats
		push    
		ldi      5
		lt?     
		bnt      code_0365
		pushi    5
		lap      param1
		sagi     egoStats
		jmp      code_0365
code_0362:
		ldi      0
		ret     
code_0365:
		ret     
	)
)

(procedure (localproc_0366 param1 param2)
	(localproc_0277 param1 param2)
	(return (* [egoStats param1] param2))
)

(procedure (localproc_0378 param1 &tmp theEgoStats temp1 temp2)
	(= theEgoStats [egoStats param1])
	(if (>= (localproc_0366 4 1) (Random 1 500))
		(= theEgoStats (+ theEgoStats (Random 1 20)))
	)
	(= temp1 2)
	(cond 
		((== param1 5) (localproc_0277 0 3) (localproc_0277 2 2))
		(
		(or (== param1 6) (== param1 7) (== param1 8)) (localproc_0277 2 4) (localproc_0277 1 2))
		((== param1 9) (localproc_0277 2 8) (localproc_0277 1 5))
		((or (== param1 10) (== param1 11)) (localproc_0277 0 5) (localproc_0277 2 4))
		((== param1 13) (localproc_0277 1 6))
		((>= param1 20) (localproc_0277 12 8) (localproc_0277 1 4))
	)
	(= temp2 100)
	(if (or (< param1 13) (== param1 5)) (= temp2 25))
	(localproc_0277 param1 (Abs (/ temp2 temp1)))
)

(instance combat of GloryRm
	(properties)
	
	(method (init &tmp temp0)
		(= local19 global365)
		(= theHeroType heroType)
		(= battleResult 0)
		(if (not global365) (= global365 855))
		(Load rsSCRIPT 40)
		(Load rsSCRIPT 41)
		(cond 
			((OneOf heroType 2 1)
				(Load rsVIEW 45)
				(= global182 88)
				(= global183 13)
				(= local11 70)
				(= local12 0)
				(= local13 86)
				(= local14 31)
			)
			((== ((inventory at: 19) state?) 2)
				(Load rsVIEW 25)
				(= global182 94)
				(= global183 19)
				(= local11 83)
				(= local12 1)
				(= local13 114)
				(= local14 40)
			)
			(else
				(Load rsVIEW 23)
				(= global182 113)
				(= global183 12)
				(= local11 74)
				(= local12 4)
				(= local13 115)
				(= local14 40)
			)
		)
		(RemapColors 2 254 60)
		(cond 
			((== global365 855) (= picture 700))
			(
				(OneOf
					prevRoomNum
					620
					621
					622
					623
					624
					625
					626
					627
					628
					629
					630
					631
					632
					633
					634
					640
					641
					642
					644
					660
					661
					662
					663
					664
					670
					680
					610
				)
				(= picture 690)
			)
			((OneOf prevRoomNum 530 535 541 542 543 545) (= picture 550))
			((== prevRoomNum 720) (= picture 700))
			(else (= picture 430))
		)
		(if (and (< 2700 Clock) (< Clock 2801))
			(PalVary 9 (+ picture 1))
		)
		(CyclePalette)
		(CyclePalette_13)
		(Palette palSET_FLAG 66 85 100)
		(theGame
			setCursor:
				(IconBarCursor
					view: (if (Btst 374) 997 else 994)
					loop: 0
					cel: 0
					yourself:
				)
		)
		(= local2 (/ (+ [egoStats 2] [egoStats 3]) 2))
		(= local3
			(/ (+ [egoStats 1] [egoStats 12] [egoStats 12]) 3)
		)
		(= local4
			(/ (+ [egoStats 0] [egoStats 3] [egoStats 3]) 3)
		)
		(= local1 local2)
		(= theTicks 0)
		(if (== prevRoomNum 575) (Bset 381))
		(= global167 0)
		(= global168 0)
		(= global169 0)
		(= global170 0)
		(= global171 0)
		(= global172 0)
		(= global173 0)
		(= global174 0)
		(= global175 0)
		(Bclr 376)
		(= local10 ((inventory at: 5) amount?))
		(= temp0 (/ [egoStats 3] 100))
		(= local0 (= local15 (- 19 (* temp0 temp0))))
		(= gCombatSpell_3 0)
		(= gCombatSpell_2 0)
		(= global187 0)
		(= global189 0)
		(= global192 0)
		((ScriptID 0 21) doit: 100)
		(= global185 (ScriptID global365 0))
		(theMusic
			number:
				(switch global365
					(820 821)
					(825 825)
					(830 831)
					(835 836)
					(840 841)
					(850 851)
					(870 871)
					(855 856)
				)
		)
		(if (!= global365 830) (theMusic setLoop: -1 play:))
		(= global180
			(switch global365
				(820 100)
				(830 250)
				(835 300)
				(840 300)
				(850
					(if (Btst 381) 400 else 300)
				)
				(870 350)
				(855 400)
				(825
					(cond 
						(
							(and
								(> (= theGABad2Health gABad2Health) 0)
								(> (= theGABad3Health gABad3Health) 0)
								(> (= theGABad4Health gABad4Health) 0)
							)
							(= monsterHealth
								(+ monsterHealth gABad2Health gABad3Health gABad4Health)
							)
							200
						)
						((and (> gABad2Health 0) (> gABad3Health 0))
							(= monsterHealth
								(+ monsterHealth gABad2Health gABad3Health)
							)
							150
						)
						((and (> gABad2Health 0) (> gABad4Health 0))
							(= monsterHealth
								(+ monsterHealth gABad2Health gABad4Health)
							)
							150
						)
						((and (> gABad3Health 0) (> gABad4Health 0))
							(= monsterHealth
								(+ monsterHealth gABad3Health gABad4Health)
							)
							150
						)
						((> gABad2Health 0) (= monsterHealth (+ monsterHealth gABad2Health)) 100)
						((> gABad3Health 0) (= monsterHealth (+ monsterHealth gABad3Health)) 100)
						((> gABad4Health 0) (= monsterHealth (+ monsterHealth gABad4Health)) 100)
						(else 50)
					)
				)
			)
		)
		(pCombat init: show: dispose:)
		(UpdatePlane (thePlane back: 0 picture: -1 yourself:))
		(FrameOut)
		(self newRoom: prevRoomNum)
	)
	
	(method (dispose &tmp temp0)
		(= temp0 0)
		(while (< temp0 global166)
			(localproc_0378 24)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global167)
			(localproc_0378 5)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global168)
			(localproc_0378 7)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global169)
			(localproc_0378 6)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global170)
			(localproc_0378 10)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global171)
			(localproc_0378 15)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global172)
			(localproc_0378 26)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global173)
			(localproc_0378 28)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global174)
			(localproc_0378 33)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 global175)
			(localproc_0378 34)
			(++ temp0)
		)
		((ScriptID 0 21) doit:)
		((inventory at: 5) amount: local10)
		(Bclr 381)
		(Bclr 386)
		(DisposeScript 38)
		(DisposeScript 40)
		(DisposeScript 41)
		(switch global365
			(850 (DisposeScript 850))
			(820 (DisposeScript 820))
			(830 (DisposeScript 830))
			(870 (DisposeScript 870))
			(840 (DisposeScript 840))
			(835 (DisposeScript 835))
			(855 (DisposeScript 855))
			(825 (DisposeScript 825))
		)
		(if (< [egoStats 18] 0) (= [egoStats 18] 0))
		(if (< [egoStats 19] 0) (= [egoStats 19] 0))
		(if
			(and
				(Btst 14)
				(> [egoStats 17] 0)
				(< [egoStats 17] 10)
			)
			(= [egoStats 17] 10)
		)
		(Bclr 378)
		(if (== global365 825)
			(if (> monsterHealth 0)
				(cond 
					((> monsterHealth 150)
						(= gABad4Health (- monsterHealth 150))
						(= gABad2Health 50)
						(= gABad3Health 50)
						(= monsterHealth 50)
					)
					((> monsterHealth 100)
						(= gABad3Health (- monsterHealth 100))
						(= gABad2Health 50)
						(= gABad4Health 0)
						(= monsterHealth 50)
					)
					((> monsterHealth 50)
						(= gABad2Health (- monsterHealth 50))
						(= gABad3Health 0)
						(= gABad4Health 0)
						(= monsterHealth 50)
					)
					(else (= gABad2Health 0) (= gABad3Health 0) (= gABad4Health 0))
				)
			else
				(= gABad2Health 0)
				(= gABad3Health 0)
				(= gABad4Health 0)
				(= monsterHealth 0)
			)
		)
		(if (not battleResult)
			(if (<= [egoStats 17] 0)
				(= battleResult 1)
			else
				(= battleResult 2)
			)
		)
		(= global365 local19)
		(= heroType theHeroType)
		(theMusic2 stop:)
		(theMusic3 stop:)
		(spellSound1 stop:)
		(spellSound2 stop:)
		(spellSound3 stop:)
		(sounds eachElementDo: #clean self)
		(DisposeScript curRoomNum)
	)
)

(instance pCombat of CombatBar
	(properties)
	
	(method (init)
		(super init: &rest)
		((ScriptID 40 0) init: self)
		((ScriptID 40 1) init: self)
		((ScriptID 40 2) init: self)
		(egoSpell init: self)
		(bloodDrop init: self)
		((ScriptID 40 3) init: self)
		(if [egoStats 12] ((ScriptID 40 4) init: self))
		((ScriptID 40 5) init: self)
		((ScriptID 40 6) init: self)
		(if (== heroType 2) (dagger init: self))
		(self add: ego1 global185)
		(if (not (Btst 378)) (self add: (ScriptID 40 7)))
		(if [egoStats 24] (self add: (ScriptID 40 8)))
		(if [egoStats 26] (self add: (ScriptID 40 9)))
		(if [egoStats 33] (self add: (ScriptID 40 10)))
		(if [egoStats 28] (self add: (ScriptID 40 11)))
		(if [egoStats 34] (self add: (ScriptID 40 12)))
		(self add: (ScriptID 40 14))
	)
	
	(method (dispatchEvent &tmp temp0)
		(if
		(and (< [egoStats 18] local1) (not (-- local0)))
			(++ [egoStats 18])
			(= local0 local15)
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
		(super dispatchEvent:)
	)
	
	(method (resetPuzzle)
		(= local6 (+ (ego1 width:) (global185 width:) 10))
		(switch global365
			(850 (bloodDrop setLoop: 11 1))
			(else 
				(bloodDrop setLoop: 10 1)
			)
		)
		(= local7
			(cond 
				((OneOf heroType 2 1) 7)
				((== ((inventory at: 19) state?) 2) 18)
				(else 12)
			)
		)
		(= local8
			(switch global365
				(850 4)
				(820 1)
				(830 5)
				(870 7)
				(840 4)
				(835 9)
				(855 9)
				(825 3)
			)
		)
	)
	
	(method (move param1 &tmp temp0)
		(cond 
			(argc
				(switch param1
					(0
						(if (not (ego1 script?))
							(ego1 setScript: (ScriptID 41 7))
						)
					)
					(1
						(if (not (ego1 script?))
							(ego1 setScript: (ScriptID 41 8))
						)
					)
					(4
						(if
						(and (not (ego1 script?)) (> [egoStats 18] 7))
							(switch global365
								(820 (ego1 setScript: lowSlash))
								(825
									(ego1 setScript: highSlash)
								)
								(else  (ego1 setScript: slash))
							)
						)
					)
					(3
						(if
						(and (not (ego1 script?)) (> [egoStats 18] 7))
							(SetNowSeen ego1)
							(SetNowSeen global185)
							(if
							(< (+ (ego1 nsRight?) 25) (global185 nsLeft?))
								(ego1 setScript: jumpAttack)
							else
								(ego1 setScript: jumpVAttack)
							)
						)
					)
					(6
						(if (not (ego1 script?))
							(if (== global365 820)
								(ego1 setScript: toParryLow)
							else
								(ego1 setScript: toDuck)
							)
						)
					)
					(7
						(if (not (Btst 378))
							(= battleResult 3)
							(= state (& state $ffdf))
						)
					)
					(8 ((ScriptID 40 8) select:))
					(9 ((ScriptID 40 9) select:))
					(11 ((ScriptID 40 11) select:))
					(10 ((ScriptID 40 10) select:))
					(12 ((ScriptID 40 12) select:))
					(13
						(if
							(and
								(not (dagger active?))
								(> [egoStats 18] 0)
								(not (ego1 script?))
								(> ((inventory at: 5) amount?) 1)
							)
							(= [egoStats 18] (- [egoStats 18] (Random 6 12)))
							(= temp0 (proc810_1 18))
							(UpdateScreenItem
								((ScriptID 40 3)
									x: (- temp0 46)
									setInsetRect: (- 80 temp0) 0 80 2
									yourself:
								)
							)
							(= global187 16)
							(ego1 setScript: throwDagger)
						)
					)
					(2
						(if
						(and (> [egoStats 18] 0) (not (ego1 script?)))
							(ego1 setScript: jumpUp)
						)
					)
					(14
						(if
						(and (not (ego1 script?)) (OneOf heroType 2 1))
							(ego1 setScript: jabHigh)
						)
					)
					(15
						(if
							(and
								(not (ego1 script?))
								(!= ((inventory at: 19) state?) 2)
							)
							(ego1 setScript: jabLow)
						)
					)
					(16
						(if (not (ego1 script?)) (ego1 setScript: jumpVAttack))
					)
					(17
						(if
						(and (> [egoStats 18] 0) (not (ego1 script?)))
							(SetNowSeen ego1)
							(SetNowSeen global185)
							(if
							(< (+ (ego1 nsRight?) 42) (global185 nsLeft?))
								(ego1 setScript: jumpForward)
							else
								(ego1 setScript: jumpUp)
							)
						)
					)
					(18
						(if (and (> [egoStats 18] 0) (> (ego1 x?) 50))
							(if (> (ego x?) 40)
								(ego1 setScript: flipBack)
							else
								(ego1 setScript: jumpUp)
							)
						)
					)
					(19
						(if (not (ego1 script?)) (ego1 setScript: toParryHigh))
					)
					(20
						(if (not (ego1 script?)) (ego1 setScript: toParryLow))
					)
				)
			)
			((not (ego1 script?))
				(if (> (combatEvent x?) (ego1 x?))
					(ego1 setScript: (ScriptID 41 7))
				else
					(ego1 setScript: (ScriptID 41 8))
				)
			)
		)
	)
)

(instance jabHigh of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego1
					view: (ego1 typeView?)
					setLoop: 6 1
					cycleSpeed: 6
					cel: 0
					attackCode: 14
					setCycle: End self
				)
				(++ global167)
			)
			(1
				(if (<= (Abs (- (ego1 x?) (global185 x?))) 10)
					(global185 getHurt: 2)
					(= temp0 (proc810_1 -1))
					(UpdateScreenItem
						((ScriptID 40 6)
							x: (+ 100 temp0)
							setInsetRect: (- 104 temp0) 0 104 2
							yourself:
						)
					)
				)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance jabLow of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego1
					view: (ego1 typeView?)
					setLoop: 5 1
					cel: 0
					cycleSpeed: 6
					attackCode: 15
					setCycle: End self
				)
				(++ global167)
			)
			(1 (ego1 setCycle: Beg self))
			(2
				(if (<= (Abs (- (ego1 x?) (global185 x?))) 10)
					(global185 getHurt: 2)
					(= temp0 (proc810_1 -1))
					(UpdateScreenItem
						((ScriptID 40 6)
							x: (+ 100 temp0)
							setInsetRect: (- 104 temp0) 0 104 2
							yourself:
						)
					)
				)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance toBomb of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(SetNowSeen global185)
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 9 1
					cel: 0
					xStep: 15
					cycleSpeed: 1
					moveSpeed: 0
					setCycle: Fwd
					attackCode: 22
					setMotion: MoveTo (global185 nsLeft?) (ego1 y?) self
				)
				(++ global171)
			)
			(1
				(if (ObjectIntersect global185 ego1)
					(if (< (= temp1 (/ monsterHealth 3)) 20) (= temp1 20))
					(if
					(> (= monsterHealth (- monsterHealth temp1)) 0)
						(= temp0 (proc810_1 -1))
						(UpdateScreenItem
							((ScriptID 40 6)
								x: (+ 100 temp0)
								setInsetRect: (- 104 temp0) 0 104 2
								yourself:
							)
						)
						(ego1 setMotion: MoveTo 45 (ego1 y?) self)
					else
						(= battleResult 2)
						(pCombat state: (& (pCombat state?) $ffdf))
					)
				else
					(self cue:)
				)
			)
			(2 (ego1 cue:) (self dispose:))
		)
	)
)

(instance toDuck of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
			(and
				(not state)
				(or
					(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64)
					(and
						(& ((gPuzzleBar combatEvent?) type?) $0200)
						(>= ((gPuzzleBar combatEvent?) z?) 0)
					)
				)
			)
			(if
				(and
					(== heroType 2)
					(> [egoStats 18] 35)
					(global185
						onMe: ((pCombat combatEvent?) x?) ((pCombat combatEvent?) y?)
					)
				)
				(= [egoStats 18] (- [egoStats 18] 35))
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				(client setScript: toBomb)
			else
				(self cue:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theTicks 0)
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 6 1
					cel: 0
					attackCode: 6
				)
				(++ global168)
			)
			(1 (ego1 cue:) (self dispose:))
		)
	)
)

(instance toParryHigh of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not state)
				(or
					(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64)
					(and
						(& ((gPuzzleBar combatEvent?) type?) $0200)
						(>= ((gPuzzleBar combatEvent?) z?) 0)
					)
				)
			)
			(= global192 (& global192 $fffe))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global192 (| global192 $0001))
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: (if (== (ego1 typeView?) 44) 2 else 0) 1
					cel: 0
					attackCode: 19
				)
				(++ global169)
			)
			(1
				(ego1 cue:)
				(= global192 (& global192 $fffe))
				(self dispose:)
			)
		)
	)
)

(instance toParryLow of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not state)
				(or
					(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64)
					(and
						(& ((gPuzzleBar combatEvent?) type?) $0200)
						(>= ((gPuzzleBar combatEvent?) z?) 0)
					)
				)
			)
			(= global192 (& global192 $fffd))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global192 (| global192 $0002))
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: (if (== (ego1 typeView?) 44) 3 else 1) 1
					cel: 0
					attackCode: 20
				)
				(++ global169)
			)
			(1
				(ego1 cue:)
				(= global192 (& global192 $fffd))
				(self dispose:)
			)
		)
	)
)

(instance slash of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen ego1)
		(if
			(and
				register
				(or
					(global185
						onMe: (+ (ego1 nsLeft?) global182) (+ (ego1 nsTop?) global183)
					)
					(and
						(== state 2)
						(| (SetNowSeen global185) $0001)
						(>= (ego1 nsRight?) (global185 nsLeft?))
					)
				)
			)
			(= register 0)
			(bloodDrop
				x: (+ (ego1 nsLeft?) global182)
				y: (+ (ego1 nsTop?) global183)
				cel: 0
				setCycle: End bloodDrop
				setPri: (+ (global185 priority?) 2)
				show:
			)
			(global185 getHurt: 2)
			(ego1 dedectPts: (proc810_2))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= register 0)
				(if theTicks
					(= ticks theTicks)
					(= theTicks 0)
				else
					(self cue:)
				)
			)
			(1
				(ego1
					view: (ego1 typeView?)
					setLoop: 7 1
					cel: 0
					cycleSpeed: 5
					attackCode: 4
					setCycle: CT 3 1 self
				)
				(++ global167)
				(proc810_13 0 901)
			)
			(2 (= register 1) (= ticks 5))
			(3 (ego1 setCycle: End self))
			(4
				(= [egoStats 18] (- [egoStats 18] (Random 5 15)))
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				(= theTicks 5)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance highSlash of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit &tmp theTheGABad2Health)
		(super doit:)
		(SetNowSeen ego1)
		(if
			(and
				register
				(global185
					onMe: (+ (ego1 nsLeft?) local11) (+ (ego1 nsTop?) local12)
				)
			)
			(= register 0)
			(bloodDrop
				x: (+ (ego1 nsLeft?) global182)
				y: (+ (ego1 nsTop?) global183)
				cel: 0
				setCycle: End bloodDrop
				setPri: (+ (global185 priority?) 2)
				show:
			)
			(= theTheGABad2Health (proc810_2))
			(if (== global365 825)
				(cond 
					((> theGABad4Health 0)
						(if (> theTheGABad2Health theGABad2Health)
							(= theTheGABad2Health theGABad2Health)
							(= theGABad4Health 0)
						)
					)
					((> theGABad3Health 0)
						(if (> theTheGABad2Health theGABad3Health)
							(= theTheGABad2Health theGABad3Health)
							(= theGABad3Health 0)
						)
					)
					(
						(and
							(> theGABad2Health 0)
							(> theTheGABad2Health theGABad2Health)
						)
						(= theTheGABad2Health theGABad2Health)
						(= theGABad2Health 0)
					)
				)
			)
			(ego1 dedectPts: theTheGABad2Health)
			(global185 getHurt: 2)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego1
					view: (ego1 typeView?)
					setLoop: 8 1
					cel: 0
					cycleSpeed: 5
					attackCode: 4
					setCycle: CT 3 1 self
				)
				(proc810_13 0 901)
				(++ global167)
			)
			(1 (= ticks 5))
			(2 (ego1 setCycle: End self))
			(3
				(= [egoStats 18] (- [egoStats 18] (Random 5 15)))
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance lowSlash of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen ego1)
		(if
			(and
				register
				(global185
					onMe: (+ (ego1 nsLeft?) local13) (+ (ego1 nsTop?) local14)
				)
			)
			(= global184 0)
			(= register 0)
			(bloodDrop
				x: (+ (ego1 nsLeft?) local13)
				y: (+ (ego1 nsTop?) local14)
				cel: 0
				setCycle: End bloodDrop
				setPri: (+ (global185 priority?) 2)
				show:
			)
			(global185 getHurt: 2)
			(ego1 dedectPts: (proc810_2))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= global184 1)
				(= register 1)
				(ego1
					view: (ego1 typeView?)
					setLoop: 9 1
					cel: 0
					cycleSpeed: 5
					attackCode: 4
					setCycle: CT 2 1 self
				)
				(proc810_13 0 901)
				(++ global167)
			)
			(1 (= ticks 10))
			(2 (ego1 setCycle: End self))
			(3
				(= global184 0)
				(= [egoStats 18] (- [egoStats 18] (Random 5 15)))
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				(= theTicks 5)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance jumpAttack of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (OneOf state 1 2) (> [egoStats 18] 0))
			(-- [egoStats 18])
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if theTicks
					(= ticks theTicks)
					(= theTicks 0)
				else
					(self cue:)
				)
			)
			(1
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 7 1
					cel: 0
					cycleSpeed: 3
					xStep: 6
					attackCode: 3
					setCycle: CT 5 1
					moveSpeed: 0
					setMotion: ShotTo (+ (ego1 x?) 42) (ego1 y?) self
				)
				(proc810_13 0 903)
				(++ global167)
			)
			(2
				(ego1
					setCycle: CombatAttack 7 1 self global185 local6 6 (proc810_2)
				)
				(proc810_13 0 901)
			)
			(3
				(if
					(and
						(> (- (ego1 x?) 18) (ego1 width:))
						(not (& (bloodDrop signal?) $0008))
					)
					(ego1
						setMotion: ShotTo (- (ego1 x?) 18) (ego1 y?) self
					)
				else
					(self cue:)
				)
			)
			(4
				(proc810_13 0 910)
				(= theTicks 10)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance jumpVAttack of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (OneOf state 1 2) (> [egoStats 18] 0))
			(-- [egoStats 18])
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if theTicks
					(= ticks theTicks)
					(= theTicks 0)
				else
					(self cue:)
				)
			)
			(1
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 7 1
					cel: 0
					cycleSpeed: 4
					attackCode: 16
					xStep: 6
					setCycle: CT 5 1 self
					moveSpeed: 0
				)
				(proc810_13 0 903)
			)
			(2
				(++ global167)
				(ego1
					setCycle: CombatAttack 7 1 self global185 local6 6 (proc810_2)
				)
				(proc810_13 0 901)
			)
			(3
				(if
					(and
						(> (- (ego1 x?) 18) (ego1 width:))
						(not (& (bloodDrop signal?) $0008))
					)
					(ego1
						setMotion: ShotTo (- (ego1 x?) 18) (ego1 y?) self
					)
				else
					(self cue:)
				)
			)
			(4
				(= theTicks 10)
				(proc810_13 0 910)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance jumpUp of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (OneOf state 0 1) (> [egoStats 18] 0))
			(-- [egoStats 18])
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 5 1
					cycleSpeed: 3
					cel: 0
					attackCode: 2
					setCycle: CT 4 1 self
				)
				(proc810_13 0 903)
			)
			(1 (= ticks 10))
			(2 (ego1 setCycle: End self))
			(3
				(proc810_13 0 910)
				(++ global168)
				(ego1 cue:)
				(self dispose:)
			)
		)
	)
)

(instance jumpForward of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (== state 1) (> [egoStats 18] 0))
			(-- [egoStats 18])
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if theTicks
					(= ticks theTicks)
					(= theTicks 0)
				else
					(self cue:)
				)
			)
			(1
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 5 1
					cycleSpeed: 6
					cel: 0
					attackCode: 3
					setCycle: End self
					setMotion: ShotTo (+ (ego1 x?) 42) (ego1 y?)
				)
				(proc810_13 0 903)
			)
			(2
				(= theTicks 5)
				(++ global168)
				(proc810_13 0 910)
				(ego1 cue: setMotion: 0)
				(self dispose:)
			)
		)
	)
)

(instance flipBack of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (== state 1) (> [egoStats 18] 0))
			(-- [egoStats 18])
			(= temp0 (proc810_1 18))
			(UpdateScreenItem
				((ScriptID 40 3)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if theTicks
					(= ticks theTicks)
					(= theTicks 0)
				else
					(self cue:)
				)
			)
			(1
				(ego1
					view: (+ (ego1 typeView?) 1)
					setLoop: 5 1
					cycleSpeed: 6
					cel: 0
					attackCode: 18
					setCycle: End self
					setMotion: ShotTo (- (ego1 x?) 42) (ego1 y?)
				)
				(proc810_13 0 903)
			)
			(2
				(= theTicks 5)
				(proc810_13 0 910)
				(ego1 cue: setMotion: 0)
				(self dispose:)
			)
		)
	)
)

(instance throwDagger of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego1
					view: (ego1 typeView?)
					setLoop: 10 1
					cel: 0
					cycleSpeed: 6
					attackCode: 13
					setCycle: End self
				)
			)
			(1
				(dagger
					myTarget: global185
					x: (+ (ego1 x?) 45)
					y: (= temp0 (- (ego1 y?) 35))
					setLoop: 13 1
					setPri: (+ (ego1 priority?) 5)
					cycleSpeed: 1
					setCycle: Fwd
					setMotion: ShotTo 330 temp0 dagger
					show:
				)
				(++ global170)
				(ego1 setCycle: Beg self)
			)
			(2 (ego1 cue:) (self dispose:))
		)
	)
)

(instance ego1 of SActor
	(properties
		x 50
		y 138
	)
	
	(method (init)
		(= gSActor self)
		(= typeView
			(cond 
				((OneOf heroType 2 1) 44)
				((== ((inventory at: 19) state?) 2) 24)
				((== ((inventory at: 19) state?) 3) 27)
				(else 22)
			)
		)
		(self cue:)
		(super init: &rest)
		(= signal (| signal $1000))
		(if (Btst 374)
			(self setScript: (ScriptID global365 1))
		)
	)
	
	(method (doVerb)
		(if (not script)
			(if (== global365 820)
				(ego1 setScript: toParryLow)
			else
				(switch ((pCombat combatEvent?) modifiers?)
					(3 (self setScript: toParryLow))
					(4 (self setScript: toParryLow))
					(8
						(self setScript: toParryHigh)
					)
					(else  (self setScript: toDuck))
				)
			)
		)
	)
	
	(method (cue)
		(= view typeView)
		(= cel (= loop 0))
		(= y 138)
		(= attackCode 0)
		(= cycleSpeed 16)
		(= global192 0)
		(self setCycle: Fwd)
		(cond 
			((Btst 376)
				(Bclr 376)
				(if (Btst 374)
					(Bclr 374)
					((ScriptID 40 14) show:)
					(theGame
						setCursor: (IconBarCursor view: 994 loop: 0 cel: 0 yourself:)
					)
					(self setScript: 0)
				else
					(Bset 374)
					(theGame
						setCursor: (IconBarCursor view: 997 loop: 0 cel: 0 yourself:)
					)
					(self setScript: (ScriptID global365 1))
				)
			)
			((Btst 374) (self setScript: (ScriptID global365 1)))
		)
	)
	
	(method (getHurt param1 &tmp temp0 temp1)
		(= temp1 param1)
		(if protectTimer (= temp1 (/ temp1 2)))
		(if
		(<= (= [egoStats 17] (- [egoStats 17] temp1)) 0)
			(= [egoStats 17] 0)
			(= battleResult 1)
			(pCombat noHands: 1)
			(Bset 386)
		)
		(= temp0 (proc810_1 17))
		(UpdateScreenItem
			((ScriptID 40 5)
				x: (- temp0 94)
				setInsetRect: (- 104 temp0) 0 104 2
				yourself:
			)
		)
		(if global186
			(= global186 0)
			(UpdateScreenItem
				((ScriptID 40 1) x: 159 setInsetRect: 0 0 0 0 yourself:)
			)
			(UpdateScreenItem
				((ScriptID 40 2) x: 119 setInsetRect: 0 0 0 -1 yourself:)
			)
		)
	)
	
	(method (dedectPts param1 &tmp temp0)
		(if
		(<= (= monsterHealth (- monsterHealth param1)) 0)
			(= monsterHealth 0)
			(= battleResult 2)
			(pCombat noHands: 1)
			(Bset 386)
		)
		(if (& (bloodDrop signal?) $0008)
			(bloodDrop
				x: (+ (ego1 nsLeft?) local13)
				y: (+ (ego1 nsTop?) local14)
				cel: 0
				setCycle: End bloodDrop
				setPri: (+ (global185 priority?) 2)
				show:
			)
		)
		(= temp0 (proc810_1 -1))
		(UpdateScreenItem
			((ScriptID 40 6)
				x: (+ 100 temp0)
				setInsetRect: (- 104 temp0) 0 104 2
				yourself:
			)
		)
	)
)

(instance egoSpell of CombatSpell
	(properties
		signal $4000
		xStep 10
		moveSpeed 0
	)
	
	(method (init)
		(= gCombatSpell self)
		(super init: &rest)
		(self cue:)
	)
	
	(method (doit)
		(if (super doit:)
			(cond 
				(global189
					(if
						(and
							gCombatSpell_3
							(gCombatSpell_3 active?)
							(self onMe: (gCombatSpell_3 x?) (gCombatSpell_3 y?))
						)
						(gCombatSpell_3 active: 0 cue:)
					)
				)
				(
					(and
						gCombatSpell_3
						(gCombatSpell_3 active?)
						(self onMe: (gCombatSpell_3 x?) (gCombatSpell_3 y?))
					)
					(= myTarget 0)
					(gCombatSpell_3 active: 0 cue:)
					(self
						active: 0
						setPri: 240
						setMotion: 0
						setLoop: (+ loop (if (> loop 5) 1 else 2)) 1
						cel: 0
						setCycle: End self
					)
				)
			)
		)
	)
	
	(method (setCycle cType)
		(if (== cType End) (proc810_13 2 944))
		(super setCycle: cType &rest)
	)
	
	(method (cue)
		(= global189 0)
		(self hide:)
	)
	
	(method (setMotion mType &tmp temp0)
		(if mType
			(= [egoStats 19] (- [egoStats 19] local5))
			(= temp0 (proc810_1 19))
			(UpdateScreenItem
				((ScriptID 40 4)
					x: (- temp0 46)
					setInsetRect: (- 80 temp0) 0 80 2
					yourself:
				)
			)
		)
		(switch type
			(26 (proc810_13 2 933))
			(28 (proc810_13 2 938))
			(33 (proc810_13 2 934))
			(34 (proc810_13 2 940))
		)
		(super setMotion: mType &rest)
	)
	
	(method (toDamage &tmp temp0 temp1)
		(if (or global189 (!= (global185 attackCode?) 23))
			(if
				(<
					(= temp1
						(- (/ [egoStats type] global165) (Random 1 9))
					)
					5
				)
				(= temp1 5)
			)
			(switch global365
				(855
					(if (== type 34)
						(= temp1 (* temp1 2))
					else
						(= temp1 0)
					)
				)
				(830
					(switch type
						(34 (= temp1 0))
						(26 (= temp1 (* temp1 2)))
					)
				)
				(850
					(if (== type 34)
						(= temp1 0)
					else
						(= temp1 (/ temp1 2))
					)
				)
			)
			(if
			(<= (= monsterHealth (- monsterHealth temp1)) 0)
				(= monsterHealth 0)
				(= battleResult 2)
				(pCombat noHands: 1)
				(Bset 386)
			)
			(= temp0 (proc810_1 -1))
			(UpdateScreenItem
				((ScriptID 40 6)
					x: (+ 100 temp0)
					setInsetRect: (- 104 temp0) 0 104 2
					yourself:
				)
			)
			(SetNowSeen global185)
			(self
				setPri: 240
				setMotion: 0
				setLoop: (+ loop (if (> loop 5) 1 else 2)) 1
				cel: 0
				x: (+ (global185 nsLeft?) (global185 explodeX?))
				setCycle: End self
			)
			(if (> temp1 0)
				(oldTarget setMotion: 0 setCycle: 0)
				(oldTarget getHurt: 0)
			)
			(= hit 1)
		)
	)
)

(instance dagger of CombatSpell
	(properties
		view 44
		loop 13
		signal $4000
		moveSpeed 0
		type $000a
	)
	
	(method (init)
		(super init: &rest)
		(= gCombatSpell_2 self)
		(self cue:)
	)
	
	(method (show)
		(super show: &rest)
		((inventory at: 5)
			amount: (- ((inventory at: 5) amount?) 1)
		)
	)
	
	(method (cue)
		(self hide:)
	)
	
	(method (toDamage &tmp temp0 temp1)
		(if
			(<=
				(= temp1
					(-
						(+ (/ [egoStats 5] 10) zapPower local7)
						(+ local8 (Random 1 10))
					)
				)
				0
			)
			(= temp1 1)
		)
		(if zapPower
			(= zapPower 0)
			((ScriptID 40 8) cel: 2)
			(UpdateScreenItem (ScriptID 40 8))
		)
		(if
		(>= (= monsterHealth (- monsterHealth temp1)) 0)
			(oldTarget setMotion: 0 setCycle: 0 getHurt: 2)
			(= temp0 (proc810_1 -1))
			(UpdateScreenItem
				((ScriptID 40 6)
					x: (+ 100 temp0)
					setInsetRect: (- 104 temp0) 0 104 2
					yourself:
				)
			)
			(self
				setMotion: 0
				setLoop: 14 1
				cel: 0
				setPri: (+ (global185 priority?) 1)
				setCycle: End self
			)
		else
			(= battleResult 2)
			(pCombat noHands: 1)
			(Bset 386)
		)
	)
)

(instance bloodDrop of SActor
	(properties
		view 26
		loop 10
	)
	
	(method (init)
		(super init: &rest)
		(self cue:)
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance spellSound1 of Sound
	(properties)
)

(instance spellSound2 of Sound
	(properties)
)

(instance spellSound3 of Sound
	(properties)
)
