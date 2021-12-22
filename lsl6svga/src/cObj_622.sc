;;; Sierra Script 1.0 - (do not remove this comment)
(script# 622)
(include sci.sh)
(use Main)
(use fileScr)
(use cObj_610)
(use rm740)
(use Sound)
(use Motion)
(use System)

(public
	usePhoneScr 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
)
(procedure (localproc_0044 param1)
	(return
		(&
			[global197 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (localproc_0056 param1 &tmp temp0)
	(= temp0 (localproc_0044 param1))
	(= [global197 (/ param1 16)]
		(|
			[global197 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
	(return temp0)
)

(procedure (localproc_007d param1 &tmp temp0)
	(= temp0 (localproc_0044 param1))
	(= [global197 (/ param1 16)]
		(&
			[global197 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
	(return temp0)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance hangUpPhoneScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (theMusic number?) 627)
					(theMusic number: 620 loop: -1 play:)
				else
					(theMusic fade: 127 10 10 0)
				)
				(= cycles 2)
			)
			(1
				(ego
					view: 621
					setLoop: 1 1
					cel: 6
					cycleSpeed: 6
					setCycle: CT 4 -1 self
				)
			)
			(2
				(sfx number: 623 loop: 1 play:)
				(ego setCycle: Beg self)
			)
			(3
				((ScriptID 620 1) show:)
				(ego
					view: 621
					setLoop: 0 1
					cel: 5
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(4
				(sfx number: 0 dispose:)
				(= ticks 30)
			)
			(5
				(Lock local8 623 0)
				(UnLoad local8 623)
				(ego normalize: 900 4 heading: 125)
				(Bclr 57)
				(Bclr 76)
				(Bclr 86)
				(= gGButtonBarCurIcon (ScriptID 0 5))
				(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance frontDeskScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (OneOf global171 1 9)) (messager say: 1 4 19 1 self 610))
					((Btst 73) (messager say: 1 4 69 0 self 610))
					(else (Bset 73) (messager say: 1 4 9 0 self 610))
				)
			)
			(1
				(if global205 (proc79_7))
				(self dispose:)
			)
		)
	)
)

(instance insultScr of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 30] temp30)
		(switch (= state newState)
			(0
				((ScriptID 0 11) init: insultExitCue)
				(if register
					(messager say: 1 4 68 1 self 610)
				else
					(messager say: 1 4 73 1 self 610)
				)
			)
			(1
				(theGame setCursor: normalCursor 1)
				(if (> howFast 4) (= ticks 90) else (= ticks 60))
			)
			(2
				(theGame setCursor: waitCursor 1)
				(= global201 (Random 1 61))
				(if (localproc_0044 global201)
					(if (> (++ local1) 50)
						(= temp30 0)
						(while (<= temp30 4)
							(= [global197 temp30] 0)
							(++ temp30)
						)
						(= temp30 1)
						(while (<= temp30 61)
							(localproc_007d temp30)
							(++ temp30)
						)
						(= local1 0)
					)
					(= state 0)
					(= cycles 2)
				else
					(localproc_0056 global201)
					(if (and (not local0) (!= global201 43)) (= state 0))
					(if
					(or local0 (== global201 9) ((user curEvent?) type?))
						(theIconBar handleEvent: (user curEvent?))
						(= ticks 60)
					else
						(messager say: 1 4 global201 1 self 611)
					)
				)
			)
			(3
				(if (and (not local0) (== global201 43))
					(= state 0)
					(messager say: 1 4 global201 3 self 611)
				else
					(= ticks 60)
				)
			)
			(4
				(messager say: 1 4 68 3 self 610)
			)
			(5
				(if global205 (proc79_7))
				(= local0 (= register 0))
				(self dispose:)
			)
		)
	)
)

(instance insultExitCue of cObj
	(properties)
	
	(method (cue)
		(theGame setCursor: waitCursor 1)
		(= local0 1)
		(insultScr state: 2 cue:)
		((ScriptID 0 11) dispose:)
	)
)

(instance sfx of Sound
	(properties)
)

(instance usePhoneScr of Script
	(properties)
	
	(method (dispose)
		(proc610_2)
		(= next hangUpPhoneScr)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((ResCheck 140 622) (= local7 140))
					((ResCheck 141 622) (= local7 141))
					(else (= local7 132))
				)
				(Load local7 622)
				(Lock local7 622 1)
				(cond 
					((ResCheck 140 623) (= local8 140))
					((ResCheck 141 623) (= local8 141))
					(else (= local8 132))
				)
				(Load local8 623)
				(Lock local8 623 1)
				(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
				(if (not (Btst 76))
					(ego setLoop: 8 1 cel: 4 heading: 125)
				else
					(= state 5)
				)
				(= ticks 30)
			)
			(1
				(ego
					view: 621
					setLoop: 0 1
					cel: 0
					setCycle: End self
					cycleSpeed: 6
				)
			)
			(2
				(ego setLoop: 1 1 cel: 0 setCycle: 0)
				((ScriptID 620 1) hide:)
				(= ticks 20)
			)
			(3 (ego setCycle: CT 3 1 self))
			(4
				(sfx number: 622 loop: 1 play:)
				(ego cel: 4 setCycle: End self)
			)
			(5
				(ego view: 623 setLoop: 0 1 cel: 0)
				(= cycles 2)
			)
			(6
				(sfx number: 0 dispose:)
				(Lock local7 622 0)
				(UnLoad local7 622)
				(theMusic fade: 80 10 10 0)
				(Bset 86)
				((ScriptID 610 0) init: self)
			)
			(7
				(theGame handsOff:)
				(= local5 1)
				(= ticks 30)
			)
			(8
				(switch register
					(98)
					(91)
					(93)
					(92)
					(94)
					(89)
					(71
						(theGame changeScore: 1 242)
					)
					(72)
					(73)
					(74)
					(75)
					(76
						(theGame changeScore: 8 244)
					)
				)
				(= seconds 3)
			)
			(9
				(= local3 0)
				(switch register
					(98
						(messager say: 1 4 7 0 self 610)
					)
					(91
						(= local3 1)
						(self setScript: frontDeskScr self)
					)
					(93
						(messager say: 1 4 13 0 self 610)
					)
					(92
						(messager say: 1 4 12 0 self 610)
					)
					(94
						(self setScript: (ScriptID 623 1) self)
					)
					(89
						(self setScript: (ScriptID 623 1) self)
					)
					(71
						(self setScript: (ScriptID 621 0) self)
					)
					(72
						(messager say: 1 4 4 0 self 610)
					)
					(73
						(self setScript: insultScr self 1)
					)
					(74
						(messager say: 1 4 8 0 self 610)
					)
					(75
						(cond 
							((and (not (Btst 240)) (Btst 236))
								(theGame changeScore: 9 240)
								(messager say: 1 4 11 0 self 610)
							)
							((Btst 79) (messager say: 1 4 87 0 self 610))
							(
								(and
									(Btst 77)
									(not (Btst 82))
									(or (not (ego has: 34)) (not (ego has: 37)))
								)
								(Bset 82)
								(Bset 83)
								(if (>= global184 7) (-- global184))
								(messager say: 1 4 80 0 self 610)
							)
							(
								(and
									(Btst 82)
									(or (not (ego has: 34)) (not (ego has: 37)))
								)
								(messager say: 1 4 81 0 self 610)
							)
							((not (Btst 236)) (messager say: 1 4 86 0 self 610))
							(
								(and
									(cast contains: (ScriptID 620 6))
									(not (Btst 241))
									(Btst 240)
								)
								(messager say: 1 4 85 0 self 610)
							)
							(
								(and
									(not (cast contains: (ScriptID 620 6)))
									(not (Btst 241))
									(Btst 240)
								)
								(messager say: 1 4 77 0 self 610)
							)
							((and (Btst 240) (Btst 241)) (messager say: 1 4 10 0 self 610))
							(else (messager say: 1 4 84 0 self 610))
						)
					)
					(76
						(cond 
							((and (not (Btst 34)) (Btst 19))
								(theGame changeScore: 8 244)
								(Bset 34)
								(messager say: 1 4 14 0 self 610)
							)
							(
							(and (Btst 34) (not (Btst 12)) (not (Btst 101))) (messager say: 1 4 18 0 self 610))
							((and (not (Btst 79)) (Btst 12)) (messager say: 1 4 15 0 self 610))
							(
							(and (Btst 101) (or (Btst 79) (not (Btst 12)))) (messager say: 1 4 16 0 self 610))
							((and (not (Btst 80)) (Btst 79)) (Bset 80) (messager say: 1 4 78 0 self 610))
							((and (Btst 80) (Btst 79)) (messager say: 1 4 79 0 self 610))
							(else (messager say: 1 4 17 0 self 610))
						)
					)
					(23292
						(messager say: 1 4 72 0 self 610)
					)
					(18724
						(theMusic number: 627 loop: -1 play: setVol: 127)
						(messager say: 1 4 20 0 self 610)
					)
					(9
						(self setScript: insultScr self)
					)
					(-24594
						(self setScript: insultScr self)
					)
					(8837
						(self setScript: insultScr self)
					)
					(275
						(messager say: 1 4 82 0 self 610)
					)
					(-30981
						(messager say: 1 4 74 0 self 610)
					)
					(16659
						(messager say: 1 4 75 0 self 610)
					)
					(-17883
						(messager say: 1 4 76 0 self 610)
					)
					(-15684
						(messager say: 1 4 83 0 self 610)
					)
					(else 
						(= local3 1)
						(if (== register -1)
							(= cycles 2)
						else
							(sfx number: 24 loop: 1 play:)
							(if (not (Random 0 1))
								(messager say: 1 4 3 0 self 610)
							else
								(messager say: 1 4 19 0 self 610)
							)
						)
					)
				)
			)
			(10
				(if (Btst 97)
					(self setScript: (ScriptID 621 0) self)
				else
					(self dispose:)
				)
			)
			(11 (self dispose:))
		)
	)
)
