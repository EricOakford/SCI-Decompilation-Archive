;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include game.sh)
(use Main)
(use Intrface)
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
	saveSpeed
	local1
	[local2 5]
	[local7 15]
	[local22 4]
	local26 = [0 1 2 3 4 5 10 20 50 -1]
	local36
	local37
	theTheDollars
	theDollars
	local40
	local41
	local42
	local43
	saveBits
	saveBits2
	saveBits3
)
(procedure (localproc_025d &tmp i theCel theLoop [str 4])
	(for ((= i 0)) (< i 5) ((++ i))
		(= [local2 i] 1)
	)
	(for ((= i 0)) (< i 15) ((++ i))
		(= [local7 i] (* i 10))
	)
	(for ((= i 0)) (< i 4) ((++ i))
		(= [local22 i] (* i 10))
	)
	(hand
		eachElementDo: #dispose
		eachElementDo: #perform clearCode
	)
	(deck eachElementDo: #perform clearCode)
	(= local41 0)
	(for ((= i 0)) (< i 10) ((++ i))
		(= theCel (= theLoop 0))
		(while (deck firstTrue: #perform uniqueCode theCel theLoop)
			(if debugging
				(Format @str 720 2 i)
				(= theCel
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
				(= theLoop
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
				(-- theLoop)
			else
				(= theCel (Random 2 14))
				(= theLoop (Random 0 3))
			)
		)
		((deck at: i) view: 722 loop: theLoop cel: theCel)
	)
)

(procedure (localproc_03ee param1 &tmp temp0 temp1)
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

(procedure (localproc_0446 &tmp i temp1 temp2 temp3 temp4)
	(for ((= i 0)) (< i 5) ((++ i))
		(++ [local7 ((hand at: i) cel?)])
		(++ [local22 ((hand at: i) loop?)])
	)
	(localproc_05a0)
	(switch (mod [local7 0] 10)
		(4
			(= local36 7)
		)
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

(procedure (localproc_05a0 &tmp i j temp2 [temp3 100])
	(for ((= i 0)) (< i 14) ((++ i))
		(= j (+ i 1))
		(while (< j 15)
			(if (> (mod [local7 j] 10) (mod [local7 i] 10))
				(= temp2 [local7 i])
				(= [local7 i] [local7 j])
				(= [local7 j] temp2)
			)
			(++ j)
		)
	)
	(for ((= i 0)) (< i 3) ((++ i))
		(= j (+ i 1))
		(while (< j 4)
			(if (> (mod [local22 j] 10) (mod [local22 i] 10))
				(= temp2 [local22 i])
				(= [local22 i] [local22 j])
				(= [local22 j] temp2)
			)
			(++ j)
		)
	)
)

(procedure (localproc_0631)
	(-= theDollars theTheDollars)
	(= local40 1)
	(localproc_0641)
)

(procedure (localproc_0641 &tmp [str 50])
	(Display 720 3
		p_at 60 25
		p_font smallFont
		p_color global129
		p_back global123
	)
	(Display 720 4 p_restore saveBits)
	(Format @str 720 5 local37)
	(= saveBits
		(Display @str
			p_at 80 25
			p_font smallFont
			p_color global129
			p_back global123
		)
	)
	(Display 720 4 p_restore saveBits2)
	(Format @str 720 6 theTheDollars)
	(= saveBits2
		(Display @str
			p_at 153 25
			p_font smallFont
			p_color global129
			p_back global123
		)
	)
	(Display 720 4 p_restore saveBits3)
	(Format @str 720 6 theDollars)
	(= saveBits3
		(Display @str
			p_at 242 25
			p_font smallFont
			p_color global129
			p_back global123
		)
	)
)

(procedure (localproc_0713 param1)
	(param1 setLoop: -1 play:)
	(User canInput: 0)
	(while local37
		(localproc_0641)
		(if (> local37 10)
			(-= local37 10)
			(+= theDollars 10)
		else
			(-- local37)
			(++ theDollars)
		)
		(Wait 0)
		(Wait 10)
	)
	(param1 setLoop: 1)
	(User canInput: TRUE)
)

(instance rm720 of LLRoom
	(properties
		picture 720
		style FADEOUT
	)
	
	(method (init)
		(theIconBar
			disable: 3 6 7 4 0 8 6
			curIcon: (theIconBar at: 2)
		)
		(super init:)
		(= saveSpeed speed)
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
		(if (= theDollars dollars)
			(= theTheDollars 1)
		)
		(localproc_0641)
		(self setScript: waitASecScript self)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (GameIsRestarting)
			(localproc_0641)
		)
	)
	
	(method (dispose)
		(hand dispose:)
		(deck dispose:)
		(sortHand dispose:)
		(theIconBar enable: ICON_WALK)
		(HandsOff)
		(= speed saveSpeed)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp [str 200])
		(switch theVerb
			(verbWalk
				(Format @str 720 0 pokerJackpot)
				(TimePrint @str
					#title {Welcome to Tramp's Casino}
					#font resumeFont
					#at -1 15
					#width 260
				)
			)
			(else
				(TimePrint 720 1)
			)
		)
	)
	
	(method (cue)
		(self setScript: sPlayPokerScript)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(HandsOff)
		(DontMove waitCursor)
	)
)

(instance deck of Set)

(instance hand of Set)

(instance sortHand of Set)

(instance sortCode of Code
	
	(method (doit obj &tmp theCel)
		(if (not (= theCel (obj cel?)))
			(= theCel 13)
		)
		(return theCel)
	)
)

(instance clearCode of Code
	
	(method (doit obj)
		(obj view: 722 setLoop: 0 setCel: 0)
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

	(method (doit obj theCel theLoop)
		(return
			(if (== (obj loop?) theLoop)
				(== (obj cel?) theCel)
			else
				FALSE
			)
		)
	)
)

(instance incBet of Prop
	(properties
		x 132
		y 176
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
	
	(method (handleEvent event &tmp evt theTime)
		(if (self onMe: event)
			(if
				(and
					(== (event type?) mouseDown)
					(not (event modifiers?))
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(while
					(and
						(!= ((= evt (Event new:)) type?) 2)
						(self onMe: evt)
					)
					(= theTime (GetTime))
					(while (< (GetTime) (+ 6 theTime))
					)
					(self doVerb: 2)
					(evt dispose:)
				)
				(evt dispose:)
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
			(2
				(if local43
					(if (< theTheDollars 100)
						(cond 
							(local40 (TimePrint 720 7))
							((< theTheDollars theDollars)
								(buttonSound play:)
								(++ theTheDollars)
								(= local37 0)
								(localproc_0641)
							)
							(else (TimePrint 720 8))
						)
					else
						(TimePrint 720 9)
					)
				)
			)
			(1 (Print 720 10))
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
	
	(method (handleEvent event &tmp evt theTime)
		(if (self onMe: event)
			(if
				(and
					(== (event type?) mouseDown)
					(not (event modifiers?))
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(while
					(and
						(!= ((= evt (Event new:)) type?) mouseUp)
						(self onMe: evt)
					)
					(= theTime (GetTime))
					(while (< (GetTime) (+ 6 theTime)))
					(self doVerb: 2)
					(evt dispose:)
				)
				(evt dispose:)
				(event claimed: TRUE)
			else
				(super handleEvent: event)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 10])
		(switch theVerb
			(2
				(if local43
					(cond 
						(local40 (TimePrint 720 7))
						((> theTheDollars 1)
							(buttonSound play:)
							(if (and (not (-- theTheDollars)) modelessDialog)
								(modelessDialog dispose:)
							)
							(= local37 0)
							(localproc_0641)
						)
						(else
							(TimePrint 720 11)
						)
					)
				)
			)
			(1
				(Print 720 12)
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
		view 720
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(= dollars theDollars)
		(switch theVerb
			(2
				(buttonSound play:)
				(if (and local43 (Btst 102))
					(self setCel: 1)
					(Animate (cast elements?) FALSE)
					(Animate (cast elements?) FALSE)
					(if theDollars
						(theMusic2 number: 723 loop: 1 flags: mNOPAUSE play:)
						(Print 720 13)
					else
						(Print 720 14)
					)
					(curRoom newRoom: 100)
				else
					(TimePrint 720 15)
				)
			)
			(1 (Print 720 16))
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
		sightAngle 40
		view 720
		loop 3
	)
	
	(method (doVerb theVerb theItem &tmp temp0)
		(switch theVerb
			(2
				(if local43
					(buttonSound play:)
					(localproc_0641)
					(if modelessDialog
						(modelessDialog dispose:)
					)
					(if theTheDollars
						(if (not local40)
							(++ pokerJackpot)
							(localproc_0631)
						)
						(self setCel: 1)
						(= local43 0)
						(= local42 0)
						(sPlayPokerScript cue:)
					else
						(TimePrint 720 17)
					)
				)
			)
			(1
				(Print 720 18)
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
		sightAngle 40
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
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
			(1 (Print 720 19))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 0))
		(cond 
			((super onMe: &rest))
			((IsObject temp0)
				(if (cast contains: temp0)
					(temp0 onMe: &rest)
				)
			)
		)
	)
)

(instance hold2 of View
	(properties
		x 114
		y 151
		sightAngle 40
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
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
			(1 (Print 720 20))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 1))
		(cond 
			((super onMe: &rest))
			((IsObject temp0)
				(if (cast contains: temp0)
					(temp0 onMe: &rest)
				)
			)
		)
	)
)

(instance hold3 of View
	(properties
		x 159
		y 151
		sightAngle 40
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
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
			(1 (Print 720 21))
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
		sightAngle 40
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
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
			(1 (Print 720 22))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 3))
		(cond 
			((super onMe: &rest))
			((IsObject temp0)
				(if (cast contains: temp0)
					(temp0 onMe: &rest)
				)
			)
		)
	)
)

(instance hold5 of View
	(properties
		x 246
		y 151
		sightAngle 40
		view 720
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
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
			(1 (Print 720 23))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0 (hand at: 4))
		(cond 
			((super onMe: &rest))
			((IsObject temp0)
				(if (cast contains: temp0)
					(temp0 onMe: &rest)
				)
			)
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
		flags mNOPAUSE
		number 726
	)
)

(instance sPlayPokerScript of Script
	
	(method (changeState newState &tmp temp0 [temp1 50])
		(switch (= state newState)
			(0
				(HandsOn)
				(= local43 1)
				(Bset 102)
			)
			(1
				(localproc_025d)
				(Bclr 102)
				(theIconBar disable: 8)
				(= cycles 2)
			)
			(2
				(localproc_03ee register)
				(= cycles 4)
			)
			(3
				(if (< (++ register) 5)
					(-= state 2)
				)
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
					(localproc_03ee register)
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
				(localproc_0446)
				(if (== local36 9)
					(Format @temp1 720 24 pokerJackpot)
					(TimePrint @temp1
						#width 200
						#title {Another Lucky Winner}
						#font bigFont
						#mode teJustCenter
					)
					(+= dollars (= pokerJackpot 1000))
					(curRoom newRoom: 100)
				else
					(= local37 (* theTheDollars [local26 local36]))
					(localproc_0641)
					(= cycles 5)
				)
			)
			(9
				(if local37
					(localproc_0713 winSound)
				else
					(loseSound play:)
				)
				(= cycles 1)
			)
			(10
				(if (> theTheDollars theDollars)
					(= theTheDollars theDollars)
				)
				(localproc_0641)
				(if theDollars
					(= local40 (= register 0))
					(self init:)
				else
					(TimePrint 720 25)
					(Bset 102)
					(= dollars 0)
					(curRoom newRoom: 100)
				)
			)
		)
	)
)

(instance waitASecScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: waitCursor)
				(= seconds 2)
			)
			(1
				(HandsOn)
				(proc0_31)
				(self dispose:)
			)
		)
	)
)
