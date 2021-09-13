;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include sci.sh)
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
	aGreenAgent
	local2
	linesCleared
)
(instance theSound of Sound
	(properties
		number 5
		loop -1
	)
)

(instance rm52 of Rm
	(properties
		picture 52
		horizon 5
		east 53
		south 50
		west 51
	)
	
	(method (init)
		(Load rsVIEW 508)
		(Load rsVIEW 509)
		(super init:)
		(addToPics
			add:
				aLcomputer
				aCcomputer
				aRcomputer
				aCounter1
				aCounter2
				aMonitor1
				aMonitor2
				aMonitor3
				aMonitor4
				aCustomsSign
				aPlants
				aRaisins
				aTicket1
				aTicket2
				aTicket3
				aTicket4
				aTicket5
			doit:
		)
		(cond 
			((== prevRoomNum 53) (ego posn: 273 127))
			((== prevRoomNum 50) (ego posn: 133 184))
			((== prevRoomNum 0) (ego posn: 133 184))
		)
		(self setRegions: 500 setScript: rm52Script)
		(if ((inventory at: 23) ownedBy: curRoomNum)
			(= linesCleared 1)
		)
		(if (< suitcaseBombState 2)
			(Load rsVIEW 510)
			(Load rsVIEW 608)
			(aBlueAgent init:)
			((= aGreenAgent (Extra new:))
				view: 509
				setLoop: 2
				posn: 167 92
				init:
			)
			(aCyanAgent init:)
			(aBlueLine
				posn: 109 140
				cycleSpeed: 1
				setPri: 10
				stopUpd:
				init:
			)
			(aGreenLine
				posn: 151 140
				cycleSpeed: 1
				setPri: 10
				stopUpd:
				init:
			)
			(aCyanLine
				posn: 197 140
				setPri: 10
				cycleSpeed: 1
				stopUpd:
				init:
			)
			(if (< suitcaseBombState 1)
				(NormalEgo)
				(ego observeControl: 16384)
				(aCustomer
					posn: 161 227
					setCycle: Walk
					setStep: 3 2
					illegalBits: 0
					init:
					setScript: ticketScript
				)
			else
				(Load rsSOUND 5)
				(theSound play:)
				(= currentStatus 7)
				(HandsOff)
				(rm52Script changeState: 1)
			)
		else
			(if (== suitcaseBombState 2)
				(= suitcaseBombState 3)
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
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego inRect: 279 100 333 137) (curRoom newRoom: 53))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 255 129 self)
			)
			(2
				(Print 52 20)
				(Print 52 21 #at -1 130)
				(= seconds 9)
			)
			(3
				(Print 52 22)
				(Print 52 23 #at -1 15 #width 280)
				(Print 52 24 #at -1 15 #width 280)
				(Print 52 25 #at -1 130)
				(ego ignoreControl: 16384 setMotion: MoveTo 184 178 self)
			)
			(4
				(Print 52 26)
				(ego setMotion: MoveTo 159 188 self)
			)
			(5
				(ego put: 22 -1)
				(curRoom newRoom: 152)
			)
			(6 (= seconds 5))
			(7
				(theGame changeScore: 15)
				(Print 52 27)
				(Print 52 28)
				(ego setCycle: End self)
			)
			(8
				(Print 52 29 #draw)
				(NormalEgo 2)
			)
			(9
				(HandsOff)
				(ego loop: 3)
				(Print 52 30 #draw)
				(aGreenAgent setCycle: Fwd)
				(= seconds 3)
			)
			(10
				(Print 52 31 #at -1 20)
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(11
				(Print 52 32)
				(aGreenAgent setCycle: Fwd)
				(= seconds 3)
			)
			(12
				(Print 52 33 #at -1 20)
				(Print 52 34)
				(Print 52 35 #at -1 20)
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(13
				(Print 52 36)
				(= seconds 3)
			)
			(14
				(aGreenAgent setCycle: Fwd)
				(= seconds 3)
			)
			(15
				(Print 52 37 #at -1 20)
				(Print 52 38)
				(Print 52 39 #at -1 20)
				(Print 52 40)
				(Print 52 41 #at -1 20)
				(Print 52 42)
				(Print 52 43 #at -1 20)
				(Print 52 44)
				(Print 52 45 #at -1 20)
				(Print 52 44)
				(Print 52 46 #at -1 20)
				(Print 52 44)
				(Print 52 47 #at -1 20)
				(Print 52 44)
				(Print 52 48 #at -1 20)
				(Print 52 49)
				(Print 52 50 #at -1 20)
				(Print 52 44)
				(Print 52 51 #at -1 20)
				(Print 52 52)
				(Print 52 53 #at -1 20)
				(Print 52 54)
				(Print 52 55 #at -1 20)
				(Print 52 44)
				(Print 52 56 #at -1 20)
				(Print 52 44)
				(Print 52 57 #at -1 20)
				(Print 52 58)
				(Print 52 59 #at -1 20)
				(Print 52 44)
				(Print 52 60 #at -1 20)
				(Print 52 44)
				(Print 52 61 #at -1 20)
				(Print 52 62)
				(Print 52 63 #at -1 20)
				(Print 52 64)
				(Print 52 65 #at -1 20)
				(Print 52 66)
				(Print 52 67 #at -1 20)
				(Print 52 44)
				(Print 52 68 #at -1 20)
				(Print 52 69)
				(Print 52 70 #at -1 20 #time 4)
				(Print 52 71 #at -1 20 #time 3)
				(Print 52 72 #at -1 20 #time 2 #dispose)
				(if (> filthLevel 4) (Print 52 73) else (Print 52 74))
				(aGreenAgent setCel: 0)
				(= seconds 3)
			)
			(16
				(aGreenAgent setCycle: Fwd)
				(= seconds 3)
			)
			(17
				(Print 52 75 #at -1 15 #width 280)
				(Print 52 76 #at -1 20)
				(Print (Format @str 52 77 tritePhrase) #at -1 20)
				(aGreenAgent setCel: 0 stopUpd:)
				(ego get: 23)
				(theGame changeScore: 5)
				(NormalEgo 3)
				(SetRegionTimer 4 5 10)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/man,children')
				(cond 
					(aBlueLine (Print 52 0))
					(linesCleared (Print 52 1))
					(else (Print 52 2))
				)
			)
			(if (and linesCleared (Said '/bimbo,agent'))
				(Print 52 3)
			)
			(if (Said '/computer,schedule')
				(Print 52 4)
				(Print 52 5 #at -1 130)
			)
			(if (Said '/raisin,(art<raisin)')
				(Print 52 6)
				(Print 52 7 #at -1 130)
			)
			(if (Said '/cup,art')
				(Print 52 6)
				(Print 52 8 #at -1 130)
			)
			(if (Said '[/airport,buffet]')
				(cond 
					(aBlueLine (Print 52 9))
					(linesCleared (Print 52 10))
					(else (Print 52 2))
				)
			)
		)
		(if
		(or (Said 'call/bimbo,agent') (Said 'get,buy/ticket'))
			(cond 
				((not linesCleared) (Print 52 11))
				((!= suitcaseBombState 3) (Print 52 12))
				((not ((inventory at: 23) ownedBy: curRoomNum)) (Print 52 13))
				((not (ego inRect: 154 118 181 129)) (NotClose))
				(else (self changeState: 9))
			)
		)
		(if (Said 'call/man,children')
			(cond 
				((not linesCleared) (Print 52 11))
				((!= suitcaseBombState 3)
					(Print (Format @str 52 14 introductoryPhrase))
					(if (> filthLevel 10) (Print 52 15) else (Print 52 16))
				)
				((ego inRect: 154 118 181 129)
					(Print (Format @str 52 17 introductoryPhrase))
					(Print 52 18)
				)
				(else (NotClose))
			)
		)
		(if (and (!= suitcaseBombState 3) (Said '/line'))
			(Print 52 19)
		)
	)
)

(instance ticketScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(aCustomer
					posn: 161 227
					show:
					setMotion: MoveTo 161 165 self
				)
			)
			(2
				(= temp0 0)
				(while (not temp0)
					(switch (++ local2)
						(1
							(if (not (& (ego onControl:) $0002))
								(self changeState: 9)
								(= temp0 1)
							)
						)
						(2
							(if (not (& (ego onControl:) $0004))
								(self changeState: 3)
								(= temp0 1)
							)
						)
						(3
							(if (not (& (ego onControl:) $0008))
								(self changeState: 15)
								(= temp0 1)
							)
						)
						(else  (= local2 0))
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
				(if (& (ego onControl:) $0004)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 164 124 setMotion: MoveTo 177 124 self)
					(aGreenLine cel: 0 setCycle: End)
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
				(if (& (ego onControl:) $0002)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 122 124 setMotion: MoveTo 135 124 self)
					(aBlueLine cel: 0 setCycle: End)
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
				(if (& (ego onControl:) $0008)
					(-- state)
					(= cycles 10)
				else
					(aCustomer posn: 210 124 setMotion: MoveTo 223 124 self)
					(aCyanLine cel: 0 setCycle: End)
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

(instance aLcomputer of PV
	(properties
		y 98
		x 105
		view 508
		cel 1
		priority 8
	)
)

(instance aCcomputer of PV
	(properties
		y 98
		x 157
		view 508
		cel 1
		priority 8
	)
)

(instance aRcomputer of PV
	(properties
		y 98
		x 210
		view 508
		priority 8
	)
)

(instance aCounter1 of PV
	(properties
		y 98
		x 131
		view 508
		cel 2
		priority 8
	)
)

(instance aCounter2 of PV
	(properties
		y 98
		x 185
		view 508
		cel 3
		priority 8
	)
)

(instance aMonitor1 of PV
	(properties
		y 63
		x 128
		view 508
		loop 1
		priority 5
	)
)

(instance aMonitor2 of PV
	(properties
		y 63
		x 149
		view 508
		loop 1
		priority 4
	)
)

(instance aMonitor3 of PV
	(properties
		y 63
		x 171
		view 508
		loop 1
		priority 3
	)
)

(instance aMonitor4 of PV
	(properties
		y 63
		x 192
		view 508
		loop 1
		priority 2
	)
)

(instance aCustomsSign of PV
	(properties
		y 61
		x 249
		view 508
		loop 1
		cel 1
		priority 10
	)
)

(instance aPlants of PV
	(properties
		y 53
		x 247
		view 508
		loop 1
		cel 3
		priority 11
	)
)

(instance aRaisins of PV
	(properties
		y 147
		x 295
		view 508
		loop 1
		cel 2
		priority 10
	)
)

(instance aTicket1 of PV
	(properties
		y 102
		x 92
		view 508
		loop 2
		priority 8
	)
)

(instance aTicket2 of PV
	(properties
		y 102
		x 125
		view 508
		loop 2
		priority 8
	)
)

(instance aTicket3 of PV
	(properties
		y 102
		x 157
		view 508
		loop 2
		priority 8
	)
)

(instance aTicket4 of PV
	(properties
		y 102
		x 189
		view 508
		loop 2
		priority 8
	)
)

(instance aTicket5 of PV
	(properties
		y 102
		x 221
		view 508
		loop 2
		priority 8
	)
)

(instance aBlueAgent of Extra
	(properties
		y 93
		x 116
		view 509
	)
)

(instance aCyanAgent of Extra
	(properties
		y 94
		x 194
		view 509
		loop 1
	)
)

(instance aBlueLine of Prop
	(properties
		view 510
		cel 4
	)
)

(instance aGreenLine of Prop
	(properties
		view 510
		loop 1
		cel 4
	)
)

(instance aCyanLine of Prop
	(properties
		view 510
		cel 4
	)
)

(instance aCustomer of Act
	(properties
		view 608
	)
)
