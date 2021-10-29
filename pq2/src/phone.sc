;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	phone 0
)
(synonyms
	(chat tell interrogate ask chat)
)

(local
	sonny
	sonnyMouth
	person
	personMouth
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	[str 160]
	[local171 10]
	infoLocation
)
(procedure (localproc_0084 param1)
	(Ring loop: param1 play:)
)

(procedure (localproc_0092)
	(Busy loop: 6 play:)
)

(procedure (localproc_1a28 param1 &tmp temp0)
	(if (== argc 1)
		(= temp0 param1)
	else
		(= temp0 (Random 0 100))
	)
	(cond 
		((<= temp0 40) (localproc_0092) (Print 12 104) (Busy stop:))
		((<= temp0 95) (localproc_0084 5) (Print 12 105) (Ring stop:))
		(else (Print 12 106))
	)
	(curRoom setScript: phoneNumber)
)

(procedure (localproc_1a9d)
	(person posn: 30 1000)
	(personMouth posn: 60 1000)
	(RedrawCast)
	(cls)
	(= local9 4)
	(Format @str 12 107)
	(AssignObjectToScript person doTalk 2)
)

(procedure (localproc_1add)
	(cls)
	(Format @str &rest)
	(AssignObjectToScript sonnyMouth doEgoTalk)
)

(procedure (localproc_1af6)
	(cls)
	(= local9 0)
	(Format @str &rest)
	(AssignObjectToScript person doTalk)
)

(procedure (localproc_1b13 param1)
	(asm
		ldi      0
		sal      str
		pushi    #message
		pushi    0
		lap      param1
		send     4
		push    
		ldi      32
		gt?     
		bnt      code_1b3a
		pushi    4
		lea      @str
		push    
		pushi    12
		pushi    108
		pushi    #message
		pushi    0
		lap      param1
		send     4
		push    
		callk    Format,  8
code_1b3a:
		pushi    8
		pushi    12
		pushi    109
		pushi    67
		pushi    20
		pushi    120
		pushi    41
		lea      @str
		push    
		pushi    25
		calle    Print,  16
		bnt      code_1b85
		pushi    2
		lea      @str
		push    
		lofsa    myEvent
		push    
		callk    Parse,  4
		bnt      code_1b3a
		pushi    #type
		pushi    1
		pushi    128
		lap      param1
		send     6
		pushi    #claimed
		pushi    1
		pushi    0
		lap      param1
		send     6
		pushi    #handleEvent
		pushi    1
		lsp      param1
		lag      curRoom
		send     6
		jmp      code_1ba3
		jmp      code_1b3a
code_1b85:
		pushi    2
		pushi    12
		pushi    107
		call     localproc_1add,  4
		pushi    #changeState
		pushi    1
		pushi    999
		pushi    #script
		pushi    0
		lag      curRoom
		send     4
		send     6
		jmp      code_1ba3
		jmp      code_1b3a
code_1ba3:
		ret     
	)
)

(instance myEvent of Event
	(properties)
)

(instance Ring of Sound
	(properties
		number 44
	)
)

(instance Busy of Sound
	(properties
		number 45
	)
)

(instance phone of Room
	(properties
		picture 444
		style $0006
	)
	
	(method (init)
		(Load rsVIEW 444)
		(Load rsVIEW 445)
		(Load rsSOUND 44)
		(Load rsSOUND 45)
		(super init:)
		(= local171 0)
		((= sonny (Actor new:))
			view: 444
			setLoop: 0
			cel: 0
			posn: 245 167
			setPri: 1
			ignoreActors:
			init:
		)
		((= sonnyMouth (Actor new:))
			view: 445
			setLoop: 0
			cel: 0
			ignoreActors:
			posn: 240 151
			init:
		)
		((= person (Actor new:))
			view: 444
			loop: 1
			cel: 0
			setPri: 1
			ignoreActors:
			posn: 60 1000
			init:
		)
		((= personMouth (Actor new:))
			view: 445
			loop: 1
			cel: 0
			ignoreActors:
			posn: 60 1000
			init:
		)
		(HandsOff)
		(curRoom setScript: phoneNumber)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
				(super handleEvent: event)
			)
			(evSAID
				(cond 
					((Said '/bye')
						(switch (Random 0 2)
							(0 (localproc_1add 12 0))
							(1 (localproc_1add 12 1))
							(else  (localproc_1add 12 2))
						)
						((curRoom script?) changeState: 999)
					)
					((Said '(hang<up),disconnect') ((curRoom script?) changeState: 999))
					((or (Said 'fuck,crap') (Said '/fuck,crap')) ((curRoom script?) changeState: 999))
					(else (super handleEvent: event))
				)
			)
		)
	)
)

(instance phoneNumber of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_02c0
			pushi    #canInput
			pushi    1
			pushi    0
			class    User
			send     6
			ldi      2
			aTop     seconds
			jmp      code_03f6
code_02c0:
			dup     
			ldi      1
			eq?     
			bnt      code_03e0
			pushi    #canInput
			pushi    1
			pushi    1
			class    User
			send     6
			ldi      0
			sal      str
code_02d4:
			pushi    8
			pushi    12
			pushi    3
			pushi    67
			pushi    20
			pushi    120
			pushi    41
			lea      @str
			push    
			pushi    18
			calle    Print,  16
			not     
			bnt      code_02fe
			pushi    #changeState
			pushi    1
			pushi    999
			self     6
			jmp      code_03f6
			jmp      code_02d4
code_02fe:
			pushi    1
			lea      @str
			push    
			callk    StrLen,  2
			push    
			ldi      1
			eq?     
			bnt      code_0348
			pushi    35
			pushi    2
			lea      @str
			push    
			pushi    0
			callk    StrAt,  4
			eq?     
			bnt      code_0348
			pushi    2
			lea      @local171
			push    
			lofsa    myEvent
			push    
			callk    Parse,  4
			bnt      code_0348
			pushi    #type
			pushi    1
			pushi    128
			lofsa    myEvent
			send     6
			pushi    #handleEvent
			pushi    1
			lofsa    myEvent
			push    
			lofsa    phoneNumber
			send     6
			jmp      code_03f6
			jmp      code_02d4
code_0348:
			pushi    1
			lea      @str
			push    
			callk    StrLen,  2
			push    
			ldi      7
			lt?     
			bnt      code_0389
			pushi    2
			lea      @str
			push    
			lofsa    {0}
			push    
			callk    StrCmp,  4
			push    
			ldi      0
			ne?     
			bnt      code_0389
			pushi    2
			lea      @str
			push    
			lofsa    {411}
			push    
			callk    StrCmp,  4
			push    
			ldi      0
			ne?     
			bnt      code_0389
			pushi    2
			pushi    12
			pushi    4
			calle    Print,  4
			jmp      code_02d4
code_0389:
			pushi    2
			lea      @str
			push    
			pushi    0
			callk    StrAt,  4
			push    
			ldi      49
			eq?     
			bnt      code_03a5
			pushi    2
			pushi    12
			pushi    5
			calle    Print,  4
			jmp      code_02d4
code_03a5:
			pushi    2
			lea      @str
			push    
			lofsa    myEvent
			push    
			callk    Parse,  4
			bnt      code_02d4
			pushi    2
			lea      @local171
			push    
			lea      @str
			push    
			callk    StrCpy,  4
			pushi    #type
			pushi    1
			pushi    128
			lofsa    myEvent
			send     6
			pushi    #handleEvent
			pushi    1
			lofsa    myEvent
			push    
			lofsa    phoneNumber
			send     6
			jmp      code_03f6
			jmp      code_02d4
			jmp      code_03f6
code_03e0:
			dup     
			ldi      999
			eq?     
			bnt      code_03f6
			pushi    0
			callb    HandsOn,  0
			pushi    #newRoom
			pushi    1
			lsg      prevRoomNum
			lag      curRoom
			send     6
code_03f6:
			toss    
			ret     
		)
	)
	
	(method (handleEvent event)
		(if
		(or (event claimed?) (!= (event type?) evSAID))
			(return)
		)
		(cond 
			((Said '/411,0') (curRoom setScript: Information))
			((Said '/5558723')
				(localproc_1af6 12 6)
				(localproc_1af6 12 7)
				(localproc_1a9d)
				(curRoom setScript: phoneNumber)
			)
			((Said '/5552222') (localproc_1a28))
			((Said '/4075556844') (localproc_1a28))
			((Said '/5551699') (localproc_1a28))
			((Said '/5552052') (localproc_1a28))
			((Said '/5554495')
				(localproc_1af6 12 8)
				(localproc_1a9d)
				(curRoom setScript: phoneNumber)
			)
			((Said '/5554169')
				(cond 
					((== prevRoomNum 32) (localproc_1a28 40))
					((!= gamePhase 6) (localproc_1a28 95))
					(else (curRoom setScript: talkingToMarie))
				)
			)
			((Said '/5553344')
				(if (== prevRoomNum 61)
					(localproc_1a28 40)
				else
					(localproc_1a28)
				)
			)
			((Said '/4075553323')
				(if (or (< gamePhase 8) (Btst 95))
					(localproc_1a28)
				else
					(curRoom setScript: talkingToColby)
				)
			)
			((Said '/5555432')
				(if (== prevRoomNum 4)
					(localproc_1a28 40)
				else
					(curRoom setScript: lyttonPD)
				)
			)
			((Said '/4075552677')
				(if (<= gamePhase 8)
					(localproc_1a28 40)
				else
					(curRoom setScript: steeltonPD)
				)
			)
			((Said '/5550001') (localproc_1a28 40))
			((Said '/2096834463')
				(if (== (Random 1 2) 1)
					(localproc_1af6 12 9)
					(localproc_1af6 12 10)
					(localproc_1a9d)
					(curRoom setScript: phoneNumber)
				else
					(localproc_1af6 12 11)
					(localproc_1af6 12 12)
					(localproc_1af6 12 13)
					(localproc_1a9d)
					(curRoom setScript: phoneNumber)
				)
			)
			((Said '/2096836858')
				(if (< (Random 1 10) 4)
					(curRoom setScript: alLowe)
				else
					(curRoom setScript: sierra)
				)
			)
			((Said '/unknownnumber')
				(if (< (Random 1 10) 3)
					(curRoom setScript: sicko)
				else
					(localproc_1a28)
				)
			)
			(else (Print 12 14) (self changeState: 0))
		)
	)
)

(instance Information of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 6 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 6 cel: 0 posn: 74 65 setCycle: EndLoop)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= str 0)
				(= infoLocation 0)
				(localproc_1af6 12 15)
			)
			(2
				(= str 0)
				(localproc_1af6 12 16)
			)
			(3
				(localproc_1af6 12 17)
				(= state 1)
				(= cycles 2)
			)
			(4
				(localproc_1af6 12 18)
				(= state 0)
				(= cycles 2)
			)
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(switch state
			(1
				(cond 
					((Said '/steelton') (= infoLocation 1) (self changeState: 2))
					((Said '/lytton') (= infoLocation 2) (self changeState: 2))
					((Said '/coarsegold') (= infoLocation 3) (self changeState: 2))
					((Said '/houston') (= infoLocation 4) (self changeState: 2))
					(else
						(event claimed: 1)
						(localproc_1af6 12 19)
						(localproc_1af6 12 20)
						(self changeState: 999)
					)
				)
			)
			(2
				(switch infoLocation
					(2
						(cond 
							((Said '/police') (localproc_1af6 12 21))
							((Said '/cheeks<cheeks') (localproc_1af6 12 22))
							((Said '/cheeks,(cheeks<!*)') (self changeState: 3) (return))
							((Said '/cove<cotton') (localproc_1af6 12 23))
							((Said '/arnie,cafe') (localproc_1af6 12 24))
							((Said '/jail') (localproc_1af6 12 25))
							((Said '/airport') (localproc_1af6 12 26))
							((Said '/inn') (localproc_1af6 12 27))
							(else (event claimed: 1) (self changeState: 4) (return))
						)
						(if (== state 2) (self changeState: 999))
					)
					(1
						(cond 
							((Said '/police,lpd,(department<police)') (localproc_1af6 12 28))
							((Said '/park<burt') (localproc_1af6 12 29))
							(else (event claimed: 1) (self changeState: 4) (return))
						)
						(if (== state 2) (self changeState: 999))
					)
					(3
						(if
						(Said '/sierra,(online<sierra),(line<on<sierra)')
							(localproc_1af6 12 30)
							(localproc_1af6 12 31)
							(self changeState: 999)
						else
							(event claimed: 1)
							(self changeState: 4)
						)
					)
					(else 
						(event claimed: 1)
						(self changeState: 4)
					)
				)
			)
		)
	)
)

(instance talkingToColby of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person setLoop: 2 cel: 0 posn: 63 80 stopUpd:)
		(personMouth setLoop: 2 cel: 0 posn: 69 63 setCycle: EndLoop)
		(RedrawCast)
		(= local6 0)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (localproc_1af6 12 32))
			(2 (localproc_1af6 12 33))
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(cond 
			(
				(or
					(Said '/hello')
					(and (== state 1) (or (Said 'chat') (Said '//bonds')))
				)
				(switch state
					(1
						(localproc_1add 12 34)
						(self changeState: 2)
					)
					(else  (localproc_1add 12 32))
				)
			)
			(
				(or
					(Said 'chat/bains,escape,death,(list<beat)')
					(Said 'chat//bains,escape,death,(list<beat)')
					(Said 'warn/bains,colby')
				)
				(switch state
					(1 (localproc_1af6 12 35))
					(else 
						(localproc_1add 12 36)
						(localproc_1af6 12 37)
						(localproc_1af6 12 38)
						(SolvePuzzle 4 95)
						(self changeState: 999)
					)
				)
			)
			(else
				(if (Said 'chat')
					(switch (Random 0 1)
						(0 (localproc_1add 12 39))
						(1 (localproc_1add 12 40))
					)
				else
					(event claimed: 1)
				)
				(switch state
					(1
						(localproc_1af6 12 41)
						(++ local6)
					)
					(2
						(switch (Random 0 4)
							(0 (localproc_1af6 12 42))
							(1 (localproc_1af6 12 43))
							(else  (localproc_1af6 12 44))
						)
						(if (> (++ local6) 2)
							(localproc_1af6 12 45)
							(self changeState: 999)
						)
					)
				)
			)
		)
	)
)

(instance talkingToMarie of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 1 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 1 cel: 0 posn: 71 63 setCycle: EndLoop)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (localproc_1af6 12 32))
			(2
				(localproc_1af6 12 46)
				(localproc_1af6 12 47)
			)
			(999
				(localproc_1af6 12 48)
				(localproc_1a9d)
				(= gamePhase 7)
				(SolvePuzzle 3)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(cond 
			(
			(or (Said '/hello,bonds,cheeks') (Said '//bonds')) (localproc_1add 12 49) (self changeState: 2))
			((or (Said 'chat') (Said 'affirmative'))
				(if (== state 2)
					(self changeState: 999)
				else
					(localproc_1add 12 49)
					(self changeState: 2)
				)
			)
			((Said 'n')
				(if (== state 2)
					(localproc_1af6 12 50)
					(localproc_1a9d)
					(client setScript: phoneNumber)
				else
					(localproc_1af6 12 51)
				)
			)
			((== state 1) (event claimed: 1) (self changeState: 1))
			(else
				(event claimed: 1)
				(localproc_1af6 12 52)
				(localproc_1af6 12 53)
			)
		)
	)
)

(instance steeltonPD of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 4 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 4 cel: 0 posn: 65 64 setCycle: EndLoop)
		(= local7 0)
		(= local5 0)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (localproc_1af6 12 54))
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(cond 
			(
				(or
					(Said '/hello')
					(and (== state 1) (or (Said 'chat') (Said '//bonds')))
				)
				(if (== state 1)
					(localproc_1add 12 55)
					(localproc_1af6 12 56)
					(= local5 1)
					(= state 2)
				else
					(localproc_1af6 12 57)
				)
			)
			(
				(or
					(Said '(chat,warn)[/bains,colby,death]')
					(Said '(chat,warn)/*[/bains,colby,death]')
				)
				(= local5 0)
				(switch state
					(1 (localproc_1af6 12 58))
					(2
						(if (Btst 94)
							(localproc_1af6 12 59)
							(localproc_1af6 12 60)
							(self changeState: 999)
						else
							(localproc_1add 12 61)
							(localproc_1add 12 62)
							(localproc_1af6 12 63)
							(SolvePuzzle 4 94)
							(self changeState: 999)
						)
					)
				)
			)
			((Said 'affirmative')
				(if (and (== state 2) local5)
					(localproc_1af6 12 64)
				else
					(localproc_1af6 12 51)
				)
			)
			((Said 'n')
				(if (and (== state 2) local5)
					(localproc_1af6 12 65)
				else
					(localproc_1af6 12 51)
				)
			)
			(else
				(event claimed: 1)
				(= local5 0)
				(switch state
					(1 (localproc_1af6 12 41))
					(2
						(if local7
							(localproc_1add 12 66)
							(localproc_1af6 12 67)
							(localproc_1a9d)
							(client setScript: phoneNumber)
						else
							(localproc_1add 12 68)
							(localproc_1af6 12 69)
							(++ local7)
						)
					)
				)
			)
		)
	)
)

(instance lyttonPD of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 5 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 5 cel: 0 posn: 77 67 setCycle: EndLoop)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (localproc_1af6 12 70))
			(3
				(localproc_1af6 12 71)
				(person posn: 30 1000)
				(personMouth posn: 60 1000)
				(RedrawCast)
				(= seconds 20)
			)
			(4
				(Print 12 72)
				(-- state)
				(= seconds 15)
			)
			(999
				(localproc_1a9d)
				(= seconds 0)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(cond 
			((or (== state 3) (== state 4)) (event claimed: 1) (Print 12 73))
			((Said '/burglary,narcotics,homicide')
				(if (== state 1)
					(switch (Random 0 1)
						(0 (localproc_1af6 12 74))
						(else  (localproc_1af6 12 75))
					)
					(localproc_1af6 12 76)
					(= local5 1)
				else
					(localproc_1af6 12 57)
				)
			)
			((Said 'affirmative,affirmative')
				(if (== local5 1)
					(= local5 0)
					(self changeState: 3)
				else
					(localproc_1af6 12 51)
				)
			)
			((Said 'n')
				(if (== local5 1)
					(= local5 0)
					(localproc_1af6 12 77)
					(self changeState: 999)
				else
					(localproc_1af6 12 51)
				)
			)
			((== state 1)
				(event claimed: 1)
				(localproc_1af6 12 78)
				(self changeState: 1)
			)
		)
	)
)

(instance sierra of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 8 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 8 cel: 0 posn: 73 60 setCycle: EndLoop)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(localproc_1af6 12 79)
				(localproc_1af6 12 80)
			)
			(2
				(localproc_1af6 12 81)
				(localproc_1af6 12 82)
				(self changeState: 999)
			)
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(if (and (== state 1) (Said '/hello'))
			(self changeState: 1)
		else
			(event claimed: 1)
			(self changeState: (++ state))
		)
	)
)

(instance alLowe of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 7 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 7 cel: 0 posn: 74 69 setCycle: EndLoop)
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (localproc_1af6 12 83))
			(2
				(localproc_1af6 12 84)
				(localproc_1af6 12 85)
				(localproc_1af6 12 86)
				(localproc_1af6 12 87)
				(localproc_1af6 12 88)
			)
			(3
				(localproc_1af6 12 89)
				(localproc_1af6 12 90)
				(localproc_1af6 12 91)
				(localproc_1af6 12 92)
				(localproc_1af6 12 93)
				(self changeState: 999)
			)
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(if (Said '/dumb,dumb')
			(localproc_1af6 12 94)
			(localproc_1af6 12 95)
			(localproc_1af6 12 96)
			(localproc_1a9d)
			(client setScript: phoneNumber)
		else
			(event claimed: 1)
			(self changeState: (++ state))
		)
	)
)

(instance sicko of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(person loop: 9 cel: 0 posn: 63 80 stopUpd:)
		(personMouth loop: 9 cel: 0 posn: 71 58 setCycle: EndLoop)
		(= local8 (Random 0 2))
		(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(switch local8
					(0
						(localproc_1af6 12 97)
						(localproc_1af6 12 97)
					)
					(1 (localproc_1af6 12 98))
					(2 (localproc_1af6 12 99))
				)
			)
			(2
				(switch local8
					(0 (= cycles 1) (= state 0))
					(1
						(localproc_1af6 12 100)
						(self changeState: 999)
					)
					(2
						(localproc_1af6 12 101)
						(self changeState: 999)
					)
				)
			)
			(999
				(localproc_1a9d)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) evKEYBOARD)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) evSAID) (return))
		(cond 
			((Said 'is<who') (localproc_1af6 12 102))
			((Said 'fuck') (localproc_1af6 12 103))
			(else (event claimed: 1) (self changeState: (++ state)))
		)
	)
)

(instance doTalk of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 4] temp4 temp5 temp6)
		(switch (= state newState)
			(0
				(if (personMouth inRect: 0 0 320 200)
					(personMouth setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(if
					(and
						(> (StrLen @str) 15)
						(personMouth inRect: 0 0 320 200)
					)
					(personMouth setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(= temp4 120)
				(TextSize @[temp0 0] @str smallFont)
				(if (<= [temp0 2] 10)
					(= temp4 (+ temp4 (- 86 (/ [temp0 3] 2))))
					(= temp6 -1)
				else
					(= temp6 180)
				)
				(= temp5 (- 50 (/ (- [temp0 2] 8) 2)))
				(switch local9
					(0
						(Print @str #width temp6 #at temp4 temp5 #font smallFont)
					)
					(else 
						(Print @str #width temp6 #at temp4 temp5 #font smallFont)
					)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance doEgoTalk of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 4] temp4 temp5 temp6)
		(switch (= state newState)
			(0
				(sonnyMouth setCycle: EndLoop self)
			)
			(1
				(if (> (StrLen @str) 15)
					(sonnyMouth setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(= temp4 15)
				(= temp6 180)
				(TextSize @[temp0 0] @str smallFont)
				(if (<= [temp0 2] 10)
					(= temp4 (+ temp4 (- 86 (/ [temp0 3] 2))))
					(= temp6 -1)
				)
				(= temp5 (- 130 (/ (- [temp0 2] 8) 2)))
				(Print @str #width temp6 #at temp4 temp5 #font smallFont)
				(client setScript: 0)
			)
		)
	)
)
