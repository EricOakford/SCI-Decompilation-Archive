;;; Sierra Script 1.0 - (do not remove this comment)
(script# 118)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm118 0
)

(local
	local0
	triedToStealSunscreen
	moneyOwed
	aClerk
)
(instance rm118 of Room
	(properties
		picture 118
		horizon 5
	)
	
	(method (init)
		(Load VIEW 229)
		(Load VIEW 228)
		(super init:)
		((View new:)
			view: 228
			loop: 0
			cel: 0
			posn: 144 36
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 228
			loop: 0
			cel: 1
			posn: 144 83
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 228
			loop: 1
			cel: 0
			posn: 90 135
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 228
			loop: 1
			cel: 0
			posn: 171 135
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 228
			loop: 1
			cel: 1
			posn: 283 113
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 228
			loop: 1
			cel: 2
			posn: 279 136
			setPri: 6
			ignoreActors:
			addToPic:
		)
		(aBigClerk
			view: 229
			setLoop: 4
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		((= aClerk (Prop new:))
			view: 229
			setLoop: 0
			setPri: 2
			posn: 261 107
			init:
		)
		(NormalEgo)
		(ego posn: 216 161 init:)
		(self setRegions: 200 setScript: rm118Script)
	)
)

(instance rm118Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: origin) $0002)
			(if timer (timer dispose:))
			(curRoom newRoom: 18)
		)
		(cond 
			((not (& (ego onControl:) $0004)) (= triedToStealSunscreen 0))
			((and moneyOwed (not triedToStealSunscreen)) (= triedToStealSunscreen 1) (Print 118 0))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 40)))
			(1
				(aClerk setLoop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(aClerk setLoop: 0 cel: 0)
				(= seconds (Random 10 20))
				(= state 0)
			)
			(3
				(= cycles 0)
				(if (or (ego has: iMillionDollarBill) (ego has: iWadODough))
					(Print 118 17)
				else
					(Print 118 18)
				)
				(aClerk setLoop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(if (or (ego has: iMillionDollarBill) (ego has: iWadODough))
					(Print 118 19)
				else
					(Print 118 20)
				)
				(self changeState: 2)
			)
			(5
				(= seconds (= cycles 0))
				(HandsOff)
				(Print 118 21)
				(cond 
					((ego has: iMillionDollarBill) (Print 118 22))
					((ego has: iWadODough) (Print 118 23))
					((ego has: iDollarBill) (Print 118 24))
					(else (Print 118 25))
				)
				(aClerk setLoop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(6
				(cond 
					((ego has: iMillionDollarBill) (Print 118 26) (aClerk setLoop: 0))
					((ego has: iWadODough)
						(Print 118 27)
						(Print 118 28)
						(aClerk setLoop: 2 cycleSpeed: 1 cel: 0 setCycle: EndLoop)
					)
					(else (Print 118 29) (aClerk setLoop: 0))
				)
				(= seconds 4)
			)
			(7
				(NormalEgo)
				(if (ego has: iWadODough)
					(Print 118 30)
					(Print (Format @str 118 31 tritePhrase))
					(= moneyOwed FALSE)
					(if (not boughtSunscreen)
						(= boughtSunscreen TRUE)
						(theGame changeScore: 9)
					)
				else
					(Print 118 32)
					(ego observeControl: cBLUE)
				)
				(self changeState: 2)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/brick,ceiling') (Print 118 1))
			(if (Said '/sign') (Print 118 2))
			(if (Said '/buffet') (Print 118 3))
			(if (Said '/agent,children,man')
				(aBigClerk posn: 273 58)
				(HandsOff)
				(Timer setReal: aBigClerk 5)
			)
			(if (Said '/sale') (Print 118 4))
			(if (Said '/rack,bottle,buffet,cabinet')
				(cond 
					((ego has: iSunscreen) (Print 118 5))
					((not (ego inRect: 0 115 105 138)) (Print 118 6))
					((not (ego has: iCruiseTicket)) (Print 118 7))
					(else (Print 118 8))
				)
			)
			(if (Said '[/airport,building,building]') (Print 118 9))
		)
		(if (Said 'get/lotion,(lotion<suntan)')
			(cond 
				((not (ego inRect: 66 115 90 134)) (NotClose))
				((ego has: iSunscreen) (Print 118 10))
				(else
					(if (ego has: iCruiseTicket) (Print 118 11) else (Print 118 12))
					(= moneyOwed TRUE)
					(ego get: iSunscreen)
					(ego observeControl: cBLUE)
				)
			)
		)
		(if
			(or
				(Said 'conceal/lotion,(lotion<suntan)/rack<back')
				(Said 'conceal,give/lotion,(lotion<suntan)/back')
				(Said 'conceal,give/lotion,(lotion<suntan)<back')
				(Said 'return,conceal/lotion,(lotion<suntan)')
			)
			(cond 
				((not (ego inRect: 66 115 90 134)) (Print 118 13))
				((not (ego has: iSunscreen)) (DontHave))
				(else
					(Ok)
					(= moneyOwed FALSE)
					(ego put: iSunscreen curRoomNum)
					(ego ignoreControl: 2)
				)
			)
		)
		(if
			(or
				(Said 'give,finger/agent,children,man/buck')
				(Said 'give/buck')
				(Said 'buy')
			)
			(cond 
				((not moneyOwed) (Print 118 14))
				((not (ego inRect: 216 118 237 134)) (NotClose))
				(else (self changeState: 5))
			)
		)
		(if (Said 'explore,explore/lotion,(lotion<suntan)')
			(Print 118 15)
		)
		(if (Said '/condom') (Print 118 16))
		(if (Said 'call/man,lagoon,children,agent')
			(self changeState: 3)
		)
	)
)

(instance aBigClerk of Prop
	(properties)
	
	(method (cue)
		(Print 118 33)
		(Print 118 34 #at 75 155 #width 170)
		(self posn: 273 1058)
		(HandsOn)
	)
)
