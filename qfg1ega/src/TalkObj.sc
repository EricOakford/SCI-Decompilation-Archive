;;; Sierra Script 1.0 - (do not remove this comment)
(script# TALKOBJ) ;803
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use Motion)
(use System)

(public
	Say 0
)

(procedure (Say whom)
	(whom messages: &rest)
)

(class TalkObj of Script
	(properties
		tWindow 0
		actor 0
		tLoop 0
		cSpeed 0
		isHead 0
		s1 0
		s2 0
		s3 0
		s4 0
		s5 0
		s6 0
		s7 0
		s8 0
		tCount 0
		oldLoop 0
		oldCel 0
		oldSpeed 0
	)
	
	(method (cue)
		(++ register)
		(if (<= register tCount)
			(cls)
			(self talk:)
		else
			(self endTalk:)
		)
	)
	
	(method (handleEvent event &tmp ret)
		(cond 
			((= ret (event claimed?)))
			((not (if s1 else s2)))
			((== (event type?) mouseDown)
				(event claimed: TRUE)
				(self cue:)
				(= ret TRUE)
			)
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
				(event claimed: TRUE)
				(self cue:)
				(= ret TRUE)
			)
		)
		(return ret)
	)
	
	(method (initTalk)
		(= seconds 0)
		(= register 1)
		(= tCount
			(cond 
				((or s7 s8) 4)
				((or s5 s6) 3)
				((or s3 s4) 2)
				((or s1 s2) 1)
				(else 0)
			)
		)
		(if actor
			(= oldLoop (actor loop?))
			(= oldCel (actor cel?))
			(= oldSpeed (actor cycleSpeed?))
			(if isHead (actor show:))
			(actor loop: tLoop cycleSpeed: cSpeed setCycle: Forward)
		)
		(cls)
		(self talk:)
	)
	
;;;	(method (talk &tmp len curStr1 curStr2 [buffer 500])
;;;		(asm
;;;			pTos     register
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_0146
;;;			pToa     s1
;;;			sat      curStr1
;;;			pToa     s2
;;;			sat      curStr2
;;;			jmp      code_0179
;;;code_0146:
;;;			dup     
;;;			ldi      2
;;;			eq?     
;;;			bnt      code_0158
;;;			pToa     s3
;;;			sat      curStr1
;;;			pToa     s4
;;;			sat      curStr2
;;;			jmp      code_0179
;;;code_0158:
;;;			dup     
;;;			ldi      3
;;;			eq?     
;;;			bnt      code_016a
;;;			pToa     s5
;;;			sat      curStr1
;;;			pToa     s6
;;;			sat      curStr2
;;;			jmp      code_0179
;;;code_016a:
;;;			dup     
;;;			ldi      4
;;;			eq?     
;;;			bnt      code_0179
;;;			pToa     s7
;;;			sat      curStr1
;;;			pToa     s8
;;;			sat      curStr2
;;;code_0179:
;;;			toss    
;;;			lst      curStr1
;;;			ldi      1000
;;;			ult?    
;;;			bnt      code_0193
;;;			pushi    3
;;;			lst      curStr1
;;;			lst      curStr2
;;;			lea      @buffer
;;;			push    
;;;			callk    GetFarText,  6
;;;			jmp      code_01a2
;;;code_0193:
;;;			lat      curStr1
;;;			bnt      code_01a2
;;;			pushi    2
;;;			lea      @buffer
;;;			push    
;;;			lst      curStr1
;;;			callk    StrCpy,  4
;;;code_01a2:
;;;			pushi    10
;;;			lea      @buffer
;;;			push    
;;;			pushi    33
;;;			lsg      userFont
;;;			pushi    80
;;;			pToa     tWindow
;;;;			bnt      code_01ba
;;;			dup     
;;;			pushi    0
;;;			send     4
;;;			jmp      code_01bc
;;;;EO: code_01ba would not decompile
;;;;code_01ba:
;;;;			ldi      0
;;;code_01bc:
;;;			push    
;;;			pushi    35
;;;			pToa     tWindow
;;;			bnt      code_01c7
;;;			jmp      code_01c9
;;;code_01c7:
;;;			class    SysWindow
;;;code_01c9:
;;;			push    
;;;			pushi    70
;;;			pToa     tWindow
;;;			bnt      code_01e6
;;;			pushi    #brRight
;;;			pushi    0
;;;			send     4
;;;			push    
;;;			pushi    #brLeft
;;;			pushi    0
;;;			pToa     tWindow
;;;			send     4
;;;			push    
;;;			ldi      16
;;;			add     
;;;			sub     
;;;			jmp      code_01e8
;;;code_01e6:
;;;			ldi      65535
;;;code_01e8:
;;;			push    
;;;			pushi    88
;;;			calle    Print,  20
;;;			pushi    1
;;;			lea      @buffer
;;;			push    
;;;			callk    StrLen,  2
;;;			push    
;;;			ldi      9
;;;			div     
;;;			sat      len
;;;			push    
;;;			ldi      3
;;;			lt?     
;;;			bnt      code_0208
;;;			ldi      3
;;;			sat      len
;;;code_0208:
;;;			lat      len
;;;			aTop     seconds
;;;			ret     
;;;		)
;;;	)

	(method (talk &tmp len curStr1 curStr2 [buffer 500])
		;EO: method was able to decompile when commenting out code_01ba and a reference to it
		(switch register
			(1 (= curStr1 s1) (= curStr2 s2))
			(2 (= curStr1 s3) (= curStr2 s4))
			(3 (= curStr1 s5) (= curStr2 s6))
			(4 (= curStr1 s7) (= curStr2 s8))
		)
		(cond 
			((u< curStr1 1000) (GetFarText curStr1 curStr2 @buffer))
			(curStr1 (StrCpy @buffer curStr1))
		)
		(Print
			@buffer
			#font userFont
			#title (tWindow title?)
			#window (if tWindow else SysWindow)
			#width (if tWindow (- (tWindow brRight?) (+ (tWindow brLeft?) 16)) else -1)
			#dispose
		)
		(if (< (= len (/ (StrLen @buffer) 9)) 3)
			(= len 3)
		)
		(= seconds len)
	)
	
	(method (endTalk)
		(if (or s1 s2)
			(if actor
				(actor
					setCycle: 0
					loop: oldLoop
					cel: oldCel
					cycleSpeed: oldSpeed
				)
				(if isHead (actor hide:))
			)
			(cls)
			(= register 0)
			(self
				tCount: 0
				s1: 0
				s2: 0
				s3: 0
				s4: 0
				s5: 0
				s6: 0
				s7: 0
				s8: 0
				seconds: 0
			)
			(if caller (caller cue:))
		)
	)

;;;	(method (messages args &tmp i str1 str2 inc)
;;;		(asm
;;;			ldi      0
;;;			aTop     s8
;;;			aTop     s7
;;;			aTop     s6
;;;			aTop     s5
;;;			aTop     s4
;;;			aTop     s3
;;;			aTop     s2
;;;			aTop     s1
;;;			sat      inc
;;;			ldi      0
;;;			aTop     tCount
;;;			sat      i
;;;code_02a1:
;;;			lst      i
;;;			lap      argc
;;;			lt?     
;;;			bnt      code_0339
;;;			lat      i
;;;			lspi     args
;;;			ldi      1000
;;;			ult?    
;;;			bnt      code_02ca
;;;			lat      i
;;;			lapi     args
;;;			sat      str1
;;;			lst      i
;;;			ldi      1
;;;			add     
;;;			lapi     args
;;;			sat      str2
;;;			ldi      2
;;;			sat      inc
;;;			jmp      code_02e5
;EO: code_02ca could not be decompiled
;;;code_02ca:
;;;			lat      i
;;;			lapi     args
;;;			bnt      code_0339
;;;			lat      i
;;;			lapi     args
;;;			sat      str1
;;;			ldi      0
;;;			sat      str2
;;;			ldi      1
;;;			sat      inc
;;;			jmp      code_02e5
;;;			jmp      code_0339
;;;code_02e5:
;;;			pTos     tCount
;;;			dup     
;;;			ldi      0
;;;			eq?     
;;;			bnt      code_02f9
;;;			lat      str1
;;;			aTop     s1
;;;			lat      str2
;;;			aTop     s2
;;;			jmp      code_032c
;;;code_02f9:
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_030b
;;;			lat      str1
;;;			aTop     s3
;;;			lat      str2
;;;			aTop     s4
;;;			jmp      code_032c
;;;code_030b:
;;;			dup     
;;;			ldi      2
;;;			eq?     
;;;			bnt      code_031d
;;;			lat      str1
;;;			aTop     s5
;;;			lat      str2
;;;			aTop     s6
;;;			jmp      code_032c
;;;code_031d:
;;;			dup     
;;;			ldi      3
;;;			eq?     
;;;			bnt      code_032c
;;;			lat      str1
;;;			aTop     s7
;;;			lat      str2
;;;			aTop     s8
;;;code_032c:
;;;			toss    
;;;			ipToa    tCount
;;;			lst      i
;;;			lat      inc
;;;			add     
;;;			sat      i
;;;			jmp      code_02a1
;;;code_0339:
;;;			pushi    #initTalk
;;;			pushi    0
;;;			self     4
;;;			ret     
;;;		)
;;;	)


	(method (messages args &tmp i str1 str2 inc)
		(= inc
			(= s1
				(= s2 (= s3 (= s4 (= s5 (= s6 (= s7 (= s8 0)))))))
			)
		)		
		(= i (= tCount 0))
		(while (< i argc)
			(cond
				((u< [args i] 1000)
					(= str1 [args i])
					(= str2 [args (+ i 1)])
					(= inc 2)
				)
				([args i]
					(= str1 [args i])
					(= str2 0)
					(= inc 1)
				)
			)
			(switch tCount
				(0 (= s1 str1) (= s2 str2))
				(1 (= s3 str1) (= s4 str2))
				(2 (= s5 str1) (= s6 str2))
				(3 (= s7 str1) (= s8 str2))
			)
			(++ tCount)
			(= i (+ i inc))
		)
		(self initTalk:)
	)
)