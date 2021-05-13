;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include sci.sh)
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm18 0
)

(local
	clientCel
	local1
	local2
	local3 =  25
	[local4 25] = [0 1 1 2 3 4 5 5 5 6 6 6 7 7 7 7 8 8 8 9 9 10 10 10 11]
	local29 =  1
)
(instance tempList of List
	(properties)
)

(instance goodList of List
	(properties)
)

(instance rm18 of LBRoom
	(properties
		picture 18
	)
	
	(method (init &tmp temp0 temp1 temp2 temp3)
		(asm
			pushi    2
			pushi    128
			pushi    18
			calle    LoadMany,  4
			pushi    #init
			pushi    0
			super    LBRoom,  4
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    #disable
			pushi    7
			pushi    0
			pushi    1
			pushi    3
			pushi    4
			pushi    5
			pushi    6
			pushi    7
			lag      theIconBar
			send     18
			pushi    #add
			pushi    0
			lofsa    goodList
			send     4
			pushi    #add
			pushi    0
			lofsa    tempList
			send     4
			ldi      65504
			sat      temp2
			ldi      46
			sat      temp3
			ldi      0
			sat      temp0
code_0087:
			lst      temp0
			ldi      12
			lt?     
			bnt      code_00ad
			pushi    #add
			pushi    1
			pushi    #cel
			pushi    1
			lst      temp0
			pushi    117
			pushi    0
			pushi    #new
			pushi    0
			lofsa    egyptProp
			send     4
			send     10
			push    
			lofsa    tempList
			send     6
			+at      temp0
			jmp      code_0087
code_00ad:
			pushi    #size
			pushi    0
			lofsa    tempList
			send     4
			bnt      code_0116
			pushi    2
			pushi    0
			pushi    #size
			pushi    0
			lofsa    tempList
			send     4
			push    
			ldi      1
			sub     
			push    
			callk    Random,  4
			sat      temp0
			pushi    #at
			pushi    1
			push    
			lofsa    tempList
			send     6
			sat      temp1
			pushi    118
			pushi    #x
			pushi    1
			pushi    #x
			lst      temp2
			ldi      48
			add     
			sat      temp2
			push    
			pushi    0
			pushi    1
			lst      temp3
			pushi    117
			pushi    0
			lat      temp1
			send     16
			push    
			lofsa    goodList
			send     6
			pushi    #size
			pushi    0
			lofsa    goodList
			send     4
			push    
			ldi      6
			eq?     
			bnt      code_010a
			ldi      65504
			sat      temp2
			ldi      111
			sat      temp3
code_010a:
			pushi    #delete
			pushi    1
			lst      temp1
			lofsa    tempList
			send     6
			jmp      code_00ad
code_0116:
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    #setScript
			pushi    1
			lofsa    sInitEm
			push    
			self     6
			ret     
		)
	)
	
	(method (dispose)
		(goodList release: dispose:)
		(tempList release: dispose:)
		(super dispose:)
	)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance sInitEm of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				((goodList firstTrue: #perform checkCel register)
					init:
					setScript: sFlipIt self register
				)
			)
			(2
				(if (< (++ register) (goodList size?))
					(= state 0)
					(= cycles 1)
				else
					(= next sAskIt)
					(self dispose:)
				)
			)
		)
	)
)

(instance sFlipIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= clientCel (client cel?))
				(client loop: 1 cel: 0)
				(client setCycle: End self)
			)
			(1
				(theMusic2 number: 55 flags: 1 loop: 1 play:)
				(client loop: 0 cel: clientCel addToPic:)
				(if (== register 11) (theGame handsOn: 1))
				(self dispose:)
			)
		)
	)
)

(instance sAskIt of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if
					(or
						(== (= local1 (Random 1 local3)) global146)
						(== local1 global147)
					)
					(-- state)
					(self cue:)
				else
					(switch currentAct
						(1
							(= global146 local1)
							(= local2 230)
						)
						(3
							(= global147 local1)
							(= local2 355)
						)
						(5 (= local2 420))
					)
				)
				(= seconds 2)
			)
			(2
				(Message msgGET 18 2 0 0 local1 @temp0)
				(Display
					@temp0
					dsCOORD
					15
					15
					dsFONT
					61
					dsWIDTH
					280
					dsALIGN
					1
					dsCOLOR
					global151
				)
				(Display
					@temp0
					dsCOORD
					15
					15
					dsFONT
					60
					dsWIDTH
					280
					dsALIGN
					1
					dsCOLOR
					23
				)
			)
			(3
				(if local29 (Bset 34) else (Bclr 34))
				(curRoom newRoom: local2)
			)
		)
	)
)

(instance egyptProp of Prop
	(properties
		view 18
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== cel [local4 (- local1 1)])
					(sAskIt cue:)
				else
					(= local29 0)
					(sAskIt cue:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance checkCel of Code
	(properties)
	
	(method (doit param1 param2)
		(return (== (param1 cel?) param2))
	)
)
