;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm52 0
)

(local
	local0
	aBlueAgent
	aGreenAgent
	aCyanAgent
	aBlueLine
	aGreenLine
	aCyanLine
	aCustomer
	ticketNum
	linesCleared
)
(instance theSound of Sound
	(properties
		number 5
		loop -1
	)
)

(instance rm52 of Room
	(properties
		picture 52
		horizon 5
		east 53
		south 50
		west 51
	)
	
	(method (init)
		(Load VIEW 508)
		(Load VIEW 509)
		(super init:)
		((View new:)
			view: 508
			loop: 0
			cel: 1
			posn: 105 98
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 0
			cel: 1
			posn: 157 98
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 0
			cel: 0
			posn: 210 98
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 0
			cel: 2
			posn: 131 98
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 0
			cel: 3
			posn: 185 98
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 0
			posn: 128 63
			setPri: 5
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 0
			posn: 149 63
			setPri: 4
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 0
			posn: 171 63
			setPri: 3
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 0
			posn: 192 63
			setPri: 2
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 1
			posn: 249 61
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 3
			posn: 247 53
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 508
			loop: 1
			cel: 2
			posn: 295 147
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 508
			loop: 2
			cel: 0
			posn: 92 102
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 2
			cel: 0
			posn: 125 102
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 2
			cel: 0
			posn: 157 102
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 2
			cel: 0
			posn: 189 102
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 508
			loop: 2
			cel: 0
			posn: 221 102
			setPri: 8
			addToPic:
		)
		(cond 
			((== prevRoomNum 53)
				(ego posn: 273 127)
			)
			((== prevRoomNum 50)
				(ego posn: 133 184)
			)
			((== prevRoomNum 0)
				(ego posn: 133 184)
			)
		)
		(self setRegions: AIRPORT setScript: rm52Script)
		(if ((inventory at: iAirlineTicket) ownedBy: curRoomNum)
			(= linesCleared TRUE)
		)
		(if (< suitcaseBombState bombEXPLODING)
			(Load VIEW 510)
			(Load VIEW 608)
			((= aBlueAgent (Extra new:))
				view: 509
				setLoop: 0
				posn: 116 93
				init:
			)
			((= aGreenAgent (Extra new:))
				view: 509
				setLoop: 2
				posn: 167 92
				init:
			)
			((= aCyanAgent (Extra new:))
				view: 509
				setLoop: 1
				posn: 194 94
				init:
			)
			((= aBlueLine (Prop new:))
				view: 510
				loop: 0
				cel: 4
				posn: 109 140
				cycleSpeed: 1
				setPri: 10
				stopUpd:
				init:
			)
			((= aGreenLine (Prop new:))
				view: 510
				loop: 1
				cel: 4
				posn: 151 140
				cycleSpeed: 1
				setPri: 10
				stopUpd:
				init:
			)
			((= aCyanLine (Prop new:))
				view: 510
				loop: 0
				cel: 4
				posn: 197 140
				setPri: 10
				cycleSpeed: 1
				stopUpd:
				init:
			)
			(if (< suitcaseBombState bombHOLDING)
				(NormalEgo)
				(ego observeControl: cYELLOW)
				((= aCustomer (Actor new:))
					view: 608
					posn: 161 227
					setCycle: Walk
					setStep: 3 2
					illegalBits: 0
					init:
					setScript: ticketScript
				)
			else
				(Load SOUND 5)
				(theSound play:)
				(= currentStatus egoSUITCASEBOMB)
				(HandsOff)
				(rm52Script changeState: 1)
			)
		else
			(if (== suitcaseBombState bombEXPLODING)
				(= suitcaseBombState bombAFTEREXPLOSION)
				(HandsOff)
				(ego
					view: 155
					setLoop: 2
					cel: 0
					cycleSpeed: 2
					posn: 159 186
					setStep: 3 2
				)
				(rm52Script changeState: 6)
			else
				(NormalEgo)
			)
			(if linesCleared
				((= aGreenAgent (Prop new:))
					view: 509
					setLoop: 2
					setCel: 0
					posn: 167 92
					stopUpd:
					init:
				)
			)
		)
		(ego init:)
	)
)

(instance rm52Script of Script
	(method (doit)
		(super doit:)
		(if (ego inRect: 279 100 333 137)
			(curRoom newRoom: 53)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 255 129 self)
			)
			(2
				(Print 52 20)
				(Print 52 21 #at -1 152)
				(= seconds 9)
			)
			(3
				(Print 52 22)
				(Print 52 23 #at -1 15 #width 280)
				(Print 52 24 #at -1 152)
				(ego ignoreControl: cYELLOW setMotion: MoveTo 184 178 self)
			)
			(4
				(Print 52 25)
				(ego setMotion: MoveTo 159 188 self)
			)
			(5
				(ego put: iSuitcase -1)
				(curRoom newRoom: 152)
			)
			(6
				(= seconds 5)
			)
			(7
				(theGame changeScore: 15)
				(Print 52 26)
				(Print 52 27)
				(ego setCycle: EndLoop self)
			)
			(8
				(Print 52 28 #draw)
				(NormalEgo loopS)
			)
			(9
				(HandsOff)
				(ego loop: loopN)
				(Print 52 29 #draw)
				(aGreenAgent setCycle: Forward)
				(= seconds 3)
			)
			(10
				(Print 52 30 #at -1 20)
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(11
				(Print 52 31)
				(aGreenAgent setCycle: Forward)
				(= seconds 3)
			)
			(12
				(Print 52 32 #at -1 20)
				(Print 52 33)
				(Print 52 34 #at -1 20)
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(13
				(Print 52 35)
				(= seconds 3)
			)
			(14
				(aGreenAgent setCycle: Forward)
				(= seconds 3)
			)
			(15
				(Print 52 36 #at -1 20)
				(Print 52 37)
				(Print 52 38 #at -1 20)
				(Print 52 39)
				(Print 52 40 #at -1 20)
				(Print 52 41)
				(Print 52 42 #at -1 20)
				(Print 52 43)
				(Print 52 44 #at -1 20)
				(Print 52 43)
				(Print 52 45 #at -1 20)
				(Print 52 43)
				(Print 52 46 #at -1 20)
				(Print 52 43)
				(Print 52 47 #at -1 20)
				(Print 52 48)
				(Print 52 49 #at -1 20)
				(Print 52 43)
				(Print 52 50 #at -1 20)
				(Print 52 51)
				(Print 52 52 #at -1 20)
				(Print 52 53)
				(Print 52 54 #at -1 20)
				(Print 52 43)
				(Print 52 55 #at -1 20)
				(Print 52 43)
				(Print 52 56 #at -1 20)
				(Print 52 57)
				(Print 52 58 #at -1 20)
				(Print 52 43)
				(Print 52 59 #at -1 20)
				(Print 52 43)
				(Print 52 60 #at -1 20)
				(Print 52 61)
				(Print 52 62 #at -1 20)
				(Print 52 63)
				(Print 52 64 #at -1 20)
				(Print 52 65)
				(Print 52 66 #at -1 20)
				(Print 52 43)
				(Print 52 67 #at -1 20)
				(Print 52 68)
				(Print 52 69 #at -1 20 #time 4)
				(Print 52 70 #at -1 20 #time 3)
				(Print 52 71 #at -1 20 #time 2 #dispose)
				(if (> filthLevel 4)
					(Print 52 72)
				else
					(Print 52 73)
				)
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(16
				(aGreenAgent setCycle: Forward)
				(= seconds 3)
			)
			(17
				(Print 52 74 #at -1 15 #width 280)
				(Print 52 75 #at -1 20)
				(Print (Format @str 52 76 tritePhrase) #at -1 20)
				(aGreenAgent setCel: 0 stopUpd:)
				(ego get: iAirlineTicket)
				(theGame changeScore: 5)
				(NormalEgo loopN)
				(SetRegionTimer rgAIRPORT 5 10)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children')
				(cond 
					(aBlueLine
						(Print 52 0)
					)
					(linesCleared
						(Print 52 1)
					)
					(else
						(Print 52 2)
					)
				)
			)
			(if (and linesCleared (Said '/bimbo,agent'))
				(Print 52 3)
			)
			(if (Said '/computer,schedule')
				(Print 52 4)
				(Print 52 5 #at -1 152)
			)
			(if (Said '/raisin,(art<raisin)')
				(Print 52 6)
				(Print 52 7 #at -1 152)
			)
			(if (Said '/cup,art')
				(Print 52 6)
				(Print 52 8 #at -1 152)
			)
			(if (Said '[/airport,buffet]')
				(cond 
					(aBlueLine
						(Print 52 9)
					)
					(linesCleared
						(Print 52 10)
					)
					(else
						(Print 52 2)
					)
				)
			)
		)
		(if (or (Said 'call/bimbo,agent') (Said 'get,buy/ticket'))
			(cond 
				((not linesCleared)
					(Print 52 11)
				)
				((!= suitcaseBombState bombAFTEREXPLOSION)
					(Print 52 12)
				)
				((not ((inventory at: iAirlineTicket) ownedBy: curRoomNum))
					(Print 52 13)
				)
				((not (ego inRect: 154 118 181 129))
					(NotClose)
				)
				(else
					(self changeState: 9)
				)
			)
		)
		(if (Said 'call/man,children')
			(cond 
				((not linesCleared)
					(Print 52 11)
				)
				((!= suitcaseBombState bombAFTEREXPLOSION)
					(Print (Format @str 52 14 introductoryPhrase))
					(if (> filthLevel 10)
						(Print 52 15)
					else
						(Print 52 16)
					)
				)
				((ego inRect: 154 118 181 129)
					(Print (Format @str 52 17 introductoryPhrase))
					(Print 52 18)
				)
				(else
					(NotClose)
				)
			)
		)
		(if (and (!= suitcaseBombState bombAFTEREXPLOSION) (Said '/line'))
			(Print 52 19)
		)
	)
)

(instance ticketScript of Script
	(method (changeState newState &tmp cmonIn)
		(switch (= state newState)
			(0
				(= seconds (Random 2 5))
			)
			(1
				(aCustomer
					posn: 161 227
					show:
					setMotion: MoveTo 161 165 self
				)
			)
			(2
				(= cmonIn FALSE)
				(while (not cmonIn)
					(switch (++ ticketNum)
						(1
							(if (not (& (ego onControl:) cBLUE))
								(self changeState: 9)
								(= cmonIn TRUE)
							)
						)
						(2
							(if (not (& (ego onControl:) cGREEN))
								(self changeState: 3)
								(= cmonIn TRUE)
							)
						)
						(3
							(if (not (& (ego onControl:) cCYAN))
								(self changeState: 15)
								(= cmonIn TRUE)
							)
						)
						(else
							(= ticketNum 0)
						)
					)
				)
			)
			(3
				(aCustomer setMotion: MoveTo 161 148 self)
			)
			(4
				(aCustomer loop: 3)
				(= seconds 3)
			)
			(5
				(if (& (ego onControl:) cGREEN)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 164 124 setMotion: MoveTo 177 124 self)
					(aGreenLine cel: 0 setCycle: EndLoop)
				)
			)
			(6
				(aCustomer setMotion: MoveTo 177 153 self)
			)
			(7
				(aGreenLine stopUpd:)
				(aCustomer setMotion: MoveTo 220 153 self)
			)
			(8
				(aCustomer setMotion: MoveTo 282 128 self)
				(= state 19)
			)
			(9
				(aCustomer setMotion: MoveTo 119 148 self)
			)
			(10
				(aCustomer loop: 3)
				(= seconds 3)
			)
			(11
				(if (& (ego onControl:) cBLUE)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 122 124 setMotion: MoveTo 135 124 self)
					(aBlueLine cel: 0 setCycle: EndLoop)
				)
			)
			(12
				(aCustomer setMotion: MoveTo 135 153 self)
			)
			(13
				(aBlueLine stopUpd:)
				(aCustomer setMotion: MoveTo 220 153 self)
			)
			(14
				(aCustomer setMotion: MoveTo 282 128 self)
				(= state 19)
			)
			(15
				(aCustomer setMotion: MoveTo 207 148 self)
			)
			(16
				(aCustomer loop: 3)
				(= seconds 3)
			)
			(17
				(if (& (ego onControl:) cCYAN)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 210 124 setMotion: MoveTo 223 124 self)
					(aCyanLine cel: 0 setCycle: EndLoop)
				)
			)
			(18
				(aCustomer setMotion: MoveTo 282 128 self)
				(= state 19)
			)
			(20
				(aCustomer hide:)
				(self changeState: 0)
			)
		)
	)
)
