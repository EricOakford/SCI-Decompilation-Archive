;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include system.sh)
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
(procedure (RingPhone param1)
	(Ring loop: param1 play:)
)

(procedure (BusySignal)
	(Busy loop: 6 play:)
)

(procedure (localproc_1a28 param1 &tmp temp0)
	(if (== argc 1)
		(= temp0 param1)
	else
		(= temp0 (Random 0 100))
	)
	(cond 
		((<= temp0 40)
			(BusySignal)
			(Print 12 104) ;the phone is busy
			(Busy stop:)
		)
		((<= temp0 95)
			(RingPhone 5)
			(Print 12 105) ;no one is there to answer.
			(Ring stop:)
		)
		(else
			(Print 12 106) ;call cannot be completed as dialed
		) 
	)
	(curRoom setScript: phoneNumber)
)

(procedure (PersonHangUp)
	(person posn: 30 1000)
	(personMouth posn: 60 1000)
	(RedrawCast)
	(cls)
	(= local9 4)
	(Format @str 12 107) ;click
	(AssignObjectToScript person doTalk 2)
)

(procedure (BondsSpeak)
	(cls)
	(Format @str &rest)
	(AssignObjectToScript sonnyMouth doEgoTalk)
)

(procedure (PersonSpeak) ;(localproc_1af6)
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
		call     BondsSpeak,  4
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
		style IRISIN
	)
	
	(method (init)
		(Load VIEW 444)
		(Load VIEW 445)
		(Load SOUND 44)
		(Load SOUND 45)
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
			(keyDown
				(super handleEvent: event)
			)
			(saidEvent
				(cond 
					((Said '/bye')
						(switch (Random 0 2)
							(0 (BondsSpeak 12 0))
							(1 (BondsSpeak 12 1))
							(else  (BondsSpeak 12 2))
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
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said '/411,0') (curRoom setScript: Information))
			((Said '/5558723')
				(PersonSpeak 12 6)
				(PersonSpeak 12 7)
				(PersonHangUp)
				(curRoom setScript: phoneNumber)
			)
			((Said '/5552222') (localproc_1a28))
			((Said '/4075556844') (localproc_1a28))
			((Said '/5551699') (localproc_1a28))
			((Said '/5552052') (localproc_1a28))
			((Said '/5554495')
				(PersonSpeak 12 8)
				(PersonHangUp)
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
					(PersonSpeak 12 9)
					(PersonSpeak 12 10)
					(PersonHangUp)
					(curRoom setScript: phoneNumber)
				else
					(PersonSpeak 12 11)
					(PersonSpeak 12 12)
					(PersonSpeak 12 13)
					(PersonHangUp)
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
				(PersonSpeak 12 15)
			)
			(2
				(= str 0)
				(PersonSpeak 12 16)
			)
			(3
				(PersonSpeak 12 17)
				(= state 1)
				(= cycles 2)
			)
			(4
				(PersonSpeak 12 18)
				(= state 0)
				(= cycles 2)
			)
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(switch state
			(1
				(cond 
					((Said '/steelton') (= infoLocation 1) (self changeState: 2))
					((Said '/lytton') (= infoLocation 2) (self changeState: 2))
					((Said '/coarsegold') (= infoLocation 3) (self changeState: 2))
					((Said '/houston') (= infoLocation 4) (self changeState: 2))
					(else
						(event claimed: 1)
						(PersonSpeak 12 19)
						(PersonSpeak 12 20)
						(self changeState: 999)
					)
				)
			)
			(2
				(switch infoLocation
					(2
						(cond 
							((Said '/police') (PersonSpeak 12 21))
							((Said '/cheeks<cheeks') (PersonSpeak 12 22))
							((Said '/cheeks,(cheeks<!*)') (self changeState: 3) (return))
							((Said '/cove<cotton') (PersonSpeak 12 23))
							((Said '/arnie,cafe') (PersonSpeak 12 24))
							((Said '/jail') (PersonSpeak 12 25))
							((Said '/airport') (PersonSpeak 12 26))
							((Said '/inn') (PersonSpeak 12 27))
							(else (event claimed: 1) (self changeState: 4) (return))
						)
						(if (== state 2) (self changeState: 999))
					)
					(1
						(cond 
							((Said '/police,lpd,(department<police)') (PersonSpeak 12 28))
							((Said '/park<burt') (PersonSpeak 12 29))
							(else (event claimed: 1) (self changeState: 4) (return))
						)
						(if (== state 2) (self changeState: 999))
					)
					(3
						(if
						(Said '/sierra,(online<sierra),(line<on<sierra)')
							(PersonSpeak 12 30)
							(PersonSpeak 12 31)
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
			(1 (PersonSpeak 12 32))
			(2 (PersonSpeak 12 33))
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			(
				(or
					(Said '/hello')
					(and (== state 1) (or (Said 'chat') (Said '//bonds')))
				)
				(switch state
					(1
						(BondsSpeak 12 34)
						(self changeState: 2)
					)
					(else  (BondsSpeak 12 32))
				)
			)
			(
				(or
					(Said 'chat/bains,escape,death,(list<beat)')
					(Said 'chat//bains,escape,death,(list<beat)')
					(Said 'warn/bains,colby')
				)
				(switch state
					(1 (PersonSpeak 12 35))
					(else 
						(BondsSpeak 12 36)
						(PersonSpeak 12 37)
						(PersonSpeak 12 38)
						(SolvePuzzle 4 95)
						(self changeState: 999)
					)
				)
			)
			(else
				(if (Said 'chat')
					(switch (Random 0 1)
						(0 (BondsSpeak 12 39))
						(1 (BondsSpeak 12 40))
					)
				else
					(event claimed: 1)
				)
				(switch state
					(1
						(PersonSpeak 12 41)
						(++ local6)
					)
					(2
						(switch (Random 0 4)
							(0 (PersonSpeak 12 42))
							(1 (PersonSpeak 12 43))
							(else  (PersonSpeak 12 44))
						)
						(if (> (++ local6) 2)
							(PersonSpeak 12 45)
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
			(1 (PersonSpeak 12 32))
			(2
				(PersonSpeak 12 46)
				(PersonSpeak 12 47)
			)
			(999
				(PersonSpeak 12 48)
				(PersonHangUp)
				(= gamePhase 7)
				(SolvePuzzle 3)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			(
			(or (Said '/hello,bonds,cheeks') (Said '//bonds')) (BondsSpeak 12 49) (self changeState: 2))
			((or (Said 'chat') (Said 'affirmative'))
				(if (== state 2)
					(self changeState: 999)
				else
					(BondsSpeak 12 49)
					(self changeState: 2)
				)
			)
			((Said 'n')
				(if (== state 2)
					(PersonSpeak 12 50)
					(PersonHangUp)
					(client setScript: phoneNumber)
				else
					(PersonSpeak 12 51)
				)
			)
			((== state 1) (event claimed: 1) (self changeState: 1))
			(else
				(event claimed: 1)
				(PersonSpeak 12 52)
				(PersonSpeak 12 53)
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
			(1 (PersonSpeak 12 54))
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			(
				(or
					(Said '/hello')
					(and (== state 1) (or (Said 'chat') (Said '//bonds')))
				)
				(if (== state 1)
					(BondsSpeak 12 55)
					(PersonSpeak 12 56)
					(= local5 1)
					(= state 2)
				else
					(PersonSpeak 12 57)
				)
			)
			(
				(or
					(Said '(chat,warn)[/bains,colby,death]')
					(Said '(chat,warn)/*[/bains,colby,death]')
				)
				(= local5 0)
				(switch state
					(1 (PersonSpeak 12 58))
					(2
						(if (Btst 94)
							(PersonSpeak 12 59)
							(PersonSpeak 12 60)
							(self changeState: 999)
						else
							(BondsSpeak 12 61)
							(BondsSpeak 12 62)
							(PersonSpeak 12 63)
							(SolvePuzzle 4 94)
							(self changeState: 999)
						)
					)
				)
			)
			((Said 'affirmative')
				(if (and (== state 2) local5)
					(PersonSpeak 12 64)
				else
					(PersonSpeak 12 51)
				)
			)
			((Said 'n')
				(if (and (== state 2) local5)
					(PersonSpeak 12 65)
				else
					(PersonSpeak 12 51)
				)
			)
			(else
				(event claimed: 1)
				(= local5 0)
				(switch state
					(1 (PersonSpeak 12 41))
					(2
						(if local7
							(BondsSpeak 12 66)
							(PersonSpeak 12 67)
							(PersonHangUp)
							(client setScript: phoneNumber)
						else
							(BondsSpeak 12 68)
							(PersonSpeak 12 69)
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
			(1 (PersonSpeak 12 70))
			(3
				(PersonSpeak 12 71)
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
				(PersonHangUp)
				(= seconds 0)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			((or (== state 3) (== state 4)) (event claimed: 1) (Print 12 73))
			((Said '/burglary,narcotics,homicide')
				(if (== state 1)
					(switch (Random 0 1)
						(0 (PersonSpeak 12 74))
						(else  (PersonSpeak 12 75))
					)
					(PersonSpeak 12 76)
					(= local5 1)
				else
					(PersonSpeak 12 57)
				)
			)
			((Said 'affirmative,affirmative')
				(if (== local5 1)
					(= local5 0)
					(self changeState: 3)
				else
					(PersonSpeak 12 51)
				)
			)
			((Said 'n')
				(if (== local5 1)
					(= local5 0)
					(PersonSpeak 12 77)
					(self changeState: 999)
				else
					(PersonSpeak 12 51)
				)
			)
			((== state 1)
				(event claimed: 1)
				(PersonSpeak 12 78)
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
				(PersonSpeak 12 79)
				(PersonSpeak 12 80)
			)
			(2
				(PersonSpeak 12 81)
				(PersonSpeak 12 82)
				(self changeState: 999)
			)
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
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
			(1 (PersonSpeak 12 83))
			(2
				(PersonSpeak 12 84)
				(PersonSpeak 12 85)
				(PersonSpeak 12 86)
				(PersonSpeak 12 87)
				(PersonSpeak 12 88)
			)
			(3
				(PersonSpeak 12 89)
				(PersonSpeak 12 90)
				(PersonSpeak 12 91)
				(PersonSpeak 12 92)
				(PersonSpeak 12 93)
				(self changeState: 999)
			)
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(if (Said '/dumb,dumb')
			(PersonSpeak 12 94)
			(PersonSpeak 12 95)
			(PersonSpeak 12 96)
			(PersonHangUp)
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
						(PersonSpeak 12 97)
						(PersonSpeak 12 97)
					)
					(1 (PersonSpeak 12 98))
					(2 (PersonSpeak 12 99))
				)
			)
			(2
				(switch local8
					(0 (= cycles 1) (= state 0))
					(1
						(PersonSpeak 12 100)
						(self changeState: 999)
					)
					(2
						(PersonSpeak 12 101)
						(self changeState: 999)
					)
				)
			)
			(999
				(PersonHangUp)
				(client setScript: phoneNumber)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) keyDown)
			(localproc_1b13 event)
			(return)
		)
		(if (!= (event type?) saidEvent) (return))
		(cond 
			((Said 'is<who') (PersonSpeak 12 102))
			((Said 'fuck') (PersonSpeak 12 103))
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
