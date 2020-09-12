;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include game.sh)
(use Main)
(use LLRoom)
(use PrintD)
(use Sound)
(use User)
(use Actor)
(use System)

(public
	rm720 0
)

(local
	theGSpeed
	local1
	[local2 5]
	[local7 15]
	[local22 4]
	[local26 10] = [0 1 2 3 4 5 10 20 50 -1]
	local36
	local37
	theTheGPassword
	theGPassword
	local40
	local41
	local42
	local43
	local44
	saveBits
	saveBits2
)
(procedure (localproc_020c &tmp i temp1 temp2 [str 4])
	(= i 0)
	(while (< i 5)
		(= [local2 i] 1)
		(++ i)
	)
	(= i 0)
	(while (< i 15)
		(= [local7 i] (* i 10))
		(++ i)
	)
	(= i 0)
	(while (< i 4)
		(= [local22 i] (* i 10))
		(++ i)
	)
	(hand
		eachElementDo: #dispose
		eachElementDo: #perform clearCode
	)
	(deck eachElementDo: #perform clearCode)
	(= local41 0)
	(= i 0)
	(while (< i 10)
		(= temp1 (= temp2 0))
		(while
		(deck firstTrue: #perform uniqueCode temp1 temp2)
			(if debugging
				(Format @str 720 2 i)
				(= temp1
					(PrintD @str
						#new
						#button {2} 2
						#button {3} 3
						#button {4} 4
						#button {5} 5
						#button {6} 6
						#button {7} 7
						#button {8} 8
						#button {9} 9
						#new
						#button {10} 10
						#button {Jack} 11
						#button {Queen} 12
						#button {King} 13
						#button {Ace} 14
					)
				)
				(= temp2
					(PrintD @str
						#new
						#button {Clubs} 1
						#new
						#button {Spades} 2
						#new
						#button {Diamonds} 3
						#new
						#button {Hearts} 4
					)
				)
				(-- temp2)
			else
				(= temp1 (Random 2 14))
				(= temp2 (Random 0 3))
			)
		)
		((deck at: i) view: 722 loop: temp2 cel: temp1)
		(++ i)
	)
)

(procedure (localproc_03a4 param1 &tmp temp0 temp1)
	(= temp0 (hand at: param1))
	(= temp1 (deck at: local41))
	(dealSound play:)
	(temp0
		view: 722
		setLoop: (temp1 loop?)
		setCel: (temp1 cel?)
		x: (+ 55 (* param1 44))
		y: 50
		init:
	)
	(return (++ local41))
)

(procedure (localproc_03ff &tmp i temp1 temp2 temp3 temp4)
	(= i 0)
	(while (< i 5)
		(++ [local7 ((hand at: i) cel?)])
		(++ [local22 ((hand at: i) loop?)])
		(++ i)
	)
	(localproc_056f)
	(switch (mod [local7 0] 10)
		(4 (= local36 7))
		(3
			(if (== (mod [local7 1] 10) 2)
				(= local36 6)
			else
				(= local36 3)
			)
		)
		(2
			(cond 
				((== (mod [local7 1] 10) 2) (= local36 2))
				((>= (/ [local7 0] 10) 11) (= local36 1))
				(else (= local36 0))
			)
		)
		(else 
			(Sort hand sortHand sortCode)
			(= temp1 ((sortHand at: 0) cel?))
			(= temp2 ((sortHand at: 4) cel?))
			(= temp3 ((sortHand at: 3) cel?))
			(sortHand release: dispose:)
			(if (and (== temp2 14) (== temp1 2) (== temp3 5))
				(= temp4 1)
			else
				(= temp4 (if (== (- temp2 temp1) 4) 1 else 0))
			)
			(cond 
				((== (mod [local22 0] 10) 5)
					(cond 
						((not temp4) (= local36 5))
						((== temp1 10) (= local36 9))
						(else (= local36 8))
					)
				)
				(temp4 (= local36 4))
				(else (= local36 0))
			)
		)
	)
)

(procedure (localproc_056f &tmp temp0 temp1 temp2 [temp3 100])
	(= temp0 0)
	(while (< temp0 14)
		(= temp1 (+ temp0 1))
		(while (< temp1 15)
			(if
			(> (mod [local7 temp1] 10) (mod [local7 temp0] 10))
				(= temp2 [local7 temp0])
				(= [local7 temp0] [local7 temp1])
				(= [local7 temp1] temp2)
			)
			(++ temp1)
		)
		(++ temp0)
	)
	(= temp0 0)
	(while (< temp0 3)
		(= temp1 (+ temp0 1))
		(while (< temp1 4)
			(if
			(> (mod [local22 temp1] 10) (mod [local22 temp0] 10))
				(= temp2 [local22 temp0])
				(= [local22 temp0] [local22 temp1])
				(= [local22 temp1] temp2)
			)
			(++ temp1)
		)
		(++ temp0)
	)
)

(procedure (localproc_060a)
	(= theGPassword (- theGPassword theTheGPassword))
	(= local40 1)
	(localproc_061b)
)

(procedure (localproc_061b &tmp [temp0 50])
	(Display 720 3
		p_at 60 25
		p_font smallFont
		p_color myDisplayColor
		p_back myShadowColor
	)
	(Display 720 4 108 local44)
	(Format @temp0 720 5 local37)
	(= local44
		(Display @temp0
			p_at 80 25
			p_font smallFont
			p_color myDisplayColor
			p_back myShadowColor
		)
	)
	(Display 720 4 108 saveBits)
	(Format @temp0 720 6 theTheGPassword)
	(= saveBits
		(Display @temp0
			p_at 153 25
			p_font smallFont
			p_color myDisplayColor
			p_back myShadowColor
		)
	)
	(Display 720 4 108 saveBits2)
	(Format @temp0 720 6 theGPassword)
	(= saveBits2
		(Display @temp0
			p_at 242 25
			p_font smallFont
			p_color myDisplayColor
			p_back myShadowColor
		)
	)
)

(procedure (localproc_06ed param1)
	(param1 setLoop: -1 play:)
	(User canInput: 0)
	(while local37
		(localproc_061b)
		(if (> local37 10)
			(= local37 (- local37 10))
			(= theGPassword (+ theGPassword 10))
		else
			(-- local37)
			(++ theGPassword)
		)
		(Wait 0)
		(Wait 10)
	)
	(param1 setLoop: 1)
	(User canInput: 1)
)

(instance rm720 of LLRoom
	(properties
		picture 720
	)
	
	(method (init)
		(InFirstPerson 1)
		(HandsOn)
		(theIconBar disable: 3 6 7 curIcon: (theIconBar at: 2))
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(super init:)
		(= theGSpeed speed)
		(= speed 6)
		(incBet init:)
		(decBet init:)
		(cashout init:)
		(hold1 init:)
		(hold2 init:)
		(hold3 init:)
		(hold4 init:)
		(hold5 init:)
		(deal init:)
		(deck
			add: cardD0 cardD1 cardD2 cardD3 cardD4 cardD5 cardD6 cardD7 cardD8 cardD9
		)
		(hand add: cardH0 cardH1 cardH2 cardH3 cardH4)
		(= theGPassword silverDollars)
		(= silverDollars 0)
		(if theGPassword (= theTheGPassword 1))
		(ego put: 17 0)
		(localproc_061b)
		(self setScript: sPlayPoker)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (GameIsRestarting) (localproc_061b))
	)
	
	(method (dispose)
		(hand dispose:)
		(deck dispose:)
		(sortHand dispose:)
		(theIconBar enable: 0 3 6 7)
		(= speed theGSpeed)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp [temp0 200])
		(switch theVerb
			(verbLook
				(Format @temp0 720 0 pokerJackpot)
				(TimePrint @temp0
					#title {Welcome to Tramp's Casino}
					#font resumeFont
					#at -1 15
					#width 260
				)
			)
			(verbWalk
				(cashout doVerb: verbDo)
			)
			(else
				(TimePrint 720 1)
			)
		)
	)
)

(instance deck of Set)

(instance hand of Set)

(instance sortHand of Set)

(instance sortCode of Code
	
	(method (doit param1 &tmp temp0)
		(if (not (= temp0 (param1 cel?))) (= temp0 13))
		(return temp0)
	)
)

(instance clearCode of Code
	
	(method (doit param1)
		(param1 view: 722 setLoop: 0 setCel: 0)
	)
)

(instance cardH0 of View)

(instance cardH1 of View)

(instance cardH2 of View)

(instance cardH3 of View)

(instance cardH4 of View)

(instance cardD0 of View)

(instance cardD1 of View)

(instance cardD2 of View)

(instance cardD3 of View)

(instance cardD4 of View)

(instance cardD5 of View)

(instance cardD6 of View)

(instance cardD7 of View)

(instance cardD8 of View)

(instance cardD9 of View)

(instance uniqueCode of Code
	
	(method (doit param1 param2 param3)
		(return
			(if (== (param1 loop?) param3)
				(== (param1 cel?) param2)
			else
				0
			)
		)
	)
)

(instance incBet of Prop
	(properties
		x 132
		y 176
		description {the Increase button}
		lookStr {Click the Hand here to increase your bet.}
		view 720
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp newEvent temp1)
		(if (self onMe: event)
			(if
				(and
					(== (event type?) mouseDown)
					(not (event modifiers?))
					(== theCursor 2)
				)
				(while
					(and
						(!= ((= newEvent (Event new:)) type?) 2)
						(self onMe: newEvent)
					)
					(= temp1 (GetTime))
					(while (< (GetTime) (+ 7 temp1))
					)
					(self doVerb: 3)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(event claimed: 1)
			else
				(super handleEvent: event)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 10])
		(switch theVerb
			(3
				(if local43
					(if (< theTheGPassword 100)
						(cond 
							(local40 (TimePrint 720 7))
							((< theTheGPassword theGPassword)
								(buttonSound play:)
								(++ theTheGPassword)
								(= local37 0)
								(localproc_061b)
							)
							(else (TimePrint 720 8))
						)
					else
						(TimePrint 720 9)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance decBet of Prop
	(properties
		x 175
		y 172
		description {the Decrease button}
		lookStr {Click the Hand here to decrease your bet.}
		view 720
		loop 1
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp newEvent temp1)
		(if (self onMe: event)
			(if
				(and
					(== (event type?) mouseDown)
					(not (event modifiers?))
					(== theCursor 2)
				)
				(while
					(and
						(!= ((= newEvent (Event new:)) type?) 2)
						(self onMe: newEvent)
					)
					(= temp1 (GetTime))
					(while (< (GetTime) (+ 7 temp1))
					)
					(self doVerb: 3)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(event claimed: 1)
			else
				(super handleEvent: event)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 10])
		(switch theVerb
			(3
				(if local43
					(cond 
						(local40 (TimePrint 720 7))
						((> theTheGPassword 1)
							(buttonSound play:)
							(if
							(and (not (-- theTheGPassword)) modelessDialog)
								(modelessDialog dispose:)
							)
							(= local37 0)
							(localproc_061b)
						)
						(else (TimePrint 720 10))
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cashout of View
	(properties
		x 287
		y 151
		description {the Cash Out button}
		lookStr {Click the Hand here to stop playing this machine. Your winnings will be given to you automatically.}
		view 720
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(buttonSound play:)
				(if
					(and
						local43
						(not (& ((theIconBar at: 8) signal?) $0004))
					)
					(self setCel: 1)
					(if theGPassword
						(theMusic2 number: 723 loop: 1 flags: 1 play:)
						(TimePrint 720 11)
						(= silverDollars theGPassword)
						(ego get: 17)
					)
					(InFirstPerson 0)
					(curRoom newRoom: 710)
				else
					(TimePrint 720 12)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance deal of View
	(properties
		x 29
		y 151
		description {the Deal button}
		sightAngle 40
		lookStr {Click the Hand here to begin a new hand of poker.}
		view 720
		loop 3
	)
	
	(method (doVerb theVerb theItem &tmp temp0)
		(switch theVerb
			(3
				(if local43
					(buttonSound play:)
					(localproc_061b)
					(if modelessDialog (modelessDialog dispose:))
					(if theTheGPassword
						(if (not local40) (++ pokerJackpot) (localproc_060a))
						(self setCel: 1)
						(= local43 0)
						(= local42 0)
						(sPlayPoker cue:)
					else
						(TimePrint 720 13)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance hold1 of View
	(properties
		x 68
		y 151
		description {the Hold Card 1 button}
		sightAngle 40
		lookStr {Click the Hand here to hold card #1.}
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local42
					(buttonSound play:)
					(if cel
						(= [local2 0] 1)
						(self setCel: 0)
					else
						(= [local2 0] 0)
						(self setCel: 1)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 0))
		(cond 
			((super onMe: &rest))
			((IsObject temp0) (if (cast contains: temp0) (temp0 onMe: &rest)))
		)
	)
)

(instance hold2 of View
	(properties
		x 114
		y 151
		description {the Hold Card 2 button}
		sightAngle 40
		lookStr {Click the Hand here to hold card #2.}
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local42
					(buttonSound play:)
					(if cel
						(= [local2 1] 1)
						(self setCel: 0)
					else
						(= [local2 1] 0)
						(self setCel: 1)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 1))
		(cond 
			((super onMe: &rest))
			((IsObject temp0) (if (cast contains: temp0) (temp0 onMe: &rest)))
		)
	)
)

(instance hold3 of View
	(properties
		x 159
		y 151
		description {the Hold Card 3 button}
		sightAngle 40
		lookStr {Click the Hand here to hold card #3.}
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local42
					(buttonSound play:)
					(if cel
						(= [local2 2] 1)
						(self setCel: 0)
					else
						(= [local2 2] 0)
						(self setCel: 1)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 2))
		(cond 
			((super onMe: &rest))
			((IsObject temp0) (if (cast contains: temp0) (temp0 onMe: &rest)))
		)
	)
)

(instance hold4 of View
	(properties
		x 203
		y 151
		description {the Hold Card 4 button}
		sightAngle 40
		lookStr {Click the Hand here to hold card #4.}
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local42
					(buttonSound play:)
					(if cel
						(= [local2 3] 1)
						(self setCel: 0)
					else
						(= [local2 3] 0)
						(self setCel: 1)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 3))
		(cond 
			((super onMe: &rest))
			((IsObject temp0) (if (cast contains: temp0) (temp0 onMe: &rest)))
		)
	)
)

(instance hold5 of View
	(properties
		x 246
		y 151
		description {the Hold Card 5 button}
		sightAngle 40
		lookStr {Click the Hand here to hold card #5.}
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local42
					(buttonSound play:)
					(if cel
						(= [local2 4] 1)
						(self setCel: 0)
					else
						(= [local2 4] 0)
						(self setCel: 1)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 4))
		(cond 
			((super onMe: &rest))
			((IsObject temp0) (if (cast contains: temp0) (temp0 onMe: &rest)))
		)
	)
)

(instance dealSound of Sound
	(properties
		number 721
	)
)

(instance buttonSound of Sound
	(properties
		number 722
	)
)

(instance cashOutSound of Sound
	(properties
		number 723
	)
)

(instance winSound of Sound
	(properties
		number 724
		loop -1
	)
)

(instance bigWinSound of Sound
	(properties
		number 724
		loop -1
	)
)

(instance loseSound of Sound
	(properties
		flags $0001
		number 726
	)
)

(instance sPlayPoker of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 50])
		(switch (= state newState)
			(0
				(= local43 1)
				(theIconBar enable: 8)
			)
			(1
				(localproc_020c)
				(theIconBar disable: 8)
				(= cycles 2)
			)
			(2
				(localproc_03a4 register)
				(= cycles 4)
			)
			(3
				(if (< (++ register) 5) (= state (- state 2)))
				(= cycles 1)
			)
			(4
				(deal setCel: 0)
				(hold1 setCel: 0)
				(hold2 setCel: 0)
				(hold3 setCel: 0)
				(hold4 setCel: 0)
				(hold5 setCel: 0)
				(= local43 1)
				(= local42 1)
			)
			(5
				(= temp0 0)
				(while (< temp0 5)
					(if [local2 temp0] ((hand at: temp0) dispose:))
					(++ temp0)
				)
				(= register 0)
				(= cycles 2)
			)
			(6
				(if [local2 register]
					(localproc_03a4 register)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(7
				(if (< (++ register) 5) (= state (- state 2)))
				(= cycles 1)
			)
			(8
				(deal setCel: 0)
				(hold1 setCel: 0)
				(hold2 setCel: 0)
				(hold3 setCel: 0)
				(hold4 setCel: 0)
				(hold5 setCel: 0)
				(++ local1)
				(localproc_03ff)
				(if (== local36 9)
					(Format @temp1 720 14 pokerJackpot)
					(TimePrint
						@temp1
						70
						200
						80
						{Another Lucky Winner}
						33
						bigFont
						30
						1
					)
					(= silverDollars pokerJackpot)
					(= pokerJackpot 1000)
					(ego get: 17)
					(InFirstPerson 0)
					(curRoom newRoom: 710)
				else
					(= local37 (* theTheGPassword [local26 local36]))
					(localproc_061b)
					(= cycles 5)
				)
			)
			(9
				(if local37
					(localproc_06ed winSound)
				else
					(loseSound play:)
				)
				(= cycles 1)
			)
			(10
				(if (> theTheGPassword theGPassword)
					(= theTheGPassword theGPassword)
				)
				(localproc_061b)
				(if theGPassword
					(= local40 (= register 0))
					(self init:)
				else
					(TimePrint 720 15)
					(theIconBar enable: 8)
					(InFirstPerson 0)
					(curRoom newRoom: 710)
				)
			)
		)
	)
)
