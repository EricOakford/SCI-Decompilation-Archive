;;; Sierra Script 1.0 - (do not remove this comment)
(script# 114)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm114 0
)

(local
	local0
	moneyOwed
	local2
	aGulpCup
	aSpigots
	aClerk
)
(instance rm114 of Room
	(properties
		picture 114
		horizon 5
		south 14
	)
	
	(method (init)
		(Load VIEW 218)
		(Load VIEW 219)
		(if (ego has: iDollarBill)
			(Load VIEW 1)
		)
		(super init:)
		((View new:)
			view: 218
			loop: 0
			cel: 2
			posn: 40 100
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 218
			loop: 1
			cel: 0
			posn: 167 45
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 218
			loop: 0
			cel: 1
			posn: 59 83
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 218
			loop: 0
			cel: 0
			posn: 140 128
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((= aSpigots (Prop new:))
			view: 218
			setLoop: 2
			setCel: 0
			setPri: 9
			posn: 140 107
			setCycle: Forward
			init:
			hide:
		)
		(if ((inventory at: iGrotesqueGulp) ownedBy: curRoomNum)
			((= aGulpCup (View new:))
				view: 218
				ignoreActors:
				setLoop: 0
				setCel: 3
				setPri: 9
				posn: 140 129
				init:
				stopUpd:
			)
		)
		((= aClerk (Prop new:))
			view: 219
			setLoop: 0
			cel: 0
			setPri: 5
			posn: 53 92
			stopUpd:
			init:
			setScript: girlScript
		)
		(aBigClerk
			view: 219
			setLoop: 3
			posn: 273 1038
			setPri: 15
			init:
		)
		(NormalEgo)
		(ego posn: 35 154 init:)
		(self setRegions: CITY setScript: rm114Script)
	)
)

(instance rm114Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(if (and moneyOwed (ego has: iGrotesqueGulp)) ;Shoplifters will be executed
				(if (< state 8)
					(self changeState: 8)
				)
			else
				(curRoom newRoom: 14)
			)
		)
	)
	
	(method (changeState newState &tmp lottoNum)
		(switch (= state newState)
			(1
				(HandsOff)
				(Ok)
				(ego setMotion: MoveTo 155 133 self)
			)
			(2
				(ego setLoop: loopN)
				(aSpigots show: setCycle: Forward)
				(= seconds 6)
			)
			(3
				(Print 114 31)
				(= seconds 6)
			)
			(4
				(Print 114 32)
				(= seconds 6)
			)
			(5
				(aSpigots hide:)
				(Print 114 33 #draw)
				(= seconds 3)
			)
			(6
				(if (> filthLevel 4)
					(Print 114 34)
				else
					(Print 114 35)
				)
				(ego
					view: 218
					setLoop: 3
					cel: 0
					setCycle: EndLoop self
					cycleSpeed: 2
				)
				(aGulpCup dispose:)
			)
			(7
				(NormalEgo loopW)
				(ego get: iGrotesqueGulp)
				(theGame changeScore: 5)
				(= moneyOwed TRUE)
				(= state 0)
			)
			(8
				(= seconds (= cycles 0))
				(= currentStatus egoSTOPPED)
				(Print 114 36)
				(Print 114 37)
				(HandsOff)
				(aClerk
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(9
				(aClerk setCycle: CycleTo 8 -1)
				(ego
					view: 219
					setLoop: 2
					cel: 0
					posn: 30 149
					illegalBits: 0
					setPri: 3
					cycleSpeed: 1
					setCycle: EndLoop self
					setMotion: MoveTo 23 184
				)
			)
			(10
				(ego stopUpd:)
				(aClerk stopUpd:)
				(= seconds 3)
			)
			(11
				(Print 114 38)
				(Print 114 39)
				(= currentStatus egoDYING)
			)
			(12
				(HandsOff)
				(Print 114 40 #icon 1 0 0)
				(Print 114 41)
				(Print (Format @str 114 42 tritePhrase))
				(ego put: iDollarBill -1 setMotion: MoveTo (ego x?) 123 self)
			)
			(13
				(ego setMotion: MoveTo 42 123 self)
			)
			(14
				(ego loop: loopN)
				(Print 114 43 #draw)
				(= seconds 3)
			)
			(15
				(Print 114 44 #icon 218 0 2 #at -1 20 #dispose)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #1}))
				)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #2}))
				)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #3}))
				)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #4}))
				)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #5}))
				)
				(= lottoNum 0)
				(while (or (< lottoNum 100) (> lottoNum 999))
					(= lottoNum (GetNumber {Pick #6}))
				)
				(cls)
				(for ((= lottoNum 0)) (< lottoNum 3) ((++ lottoNum))
					(Print 114 45 #icon 218 0 2)
				)
				(Print 114 46)
				(ego get: iLotteryTicket)
				(theGame changeScore: 3)
				(= state 0)
				(HandsOn)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/coke,cup,(dispenser<coke),(dispenser<coke)')
				(if (not ((inventory at: iGrotesqueGulp) ownedBy: curRoomNum))
					(Print 114 0)
				else
					(Print 114 1)
					(Print 114 2)
				)
			)
			(if (Said '/bucko,ticket,(dispenser<bucko)')
				(cond 
					((ego has: iLotteryTicket)
						(event claimed: FALSE)
						(return)
					)
					((not ((inventory at: iLotteryTicket) ownedBy: curRoomNum))
						(Print 114 3)
					)
					(else
						(Print 114 4)
					)
				)
			)
			(if (Said '/dispenser')
				(Print 114 5)
			)
			(if (Said '/agent,children,bimbo')
				(aBigClerk posn: 273 38 stopUpd:)
				(Timer setReal: aBigClerk 5)
				(HandsOff)
			)
			(if (Said '[/building,box,buffet,rack,building,airport]')
				(Print 114 6)
			)
		)
		(if (Said 'call/agent,children,bimbo')
			(girlScript cue:)
		)
		(if
			(and
				(ego has: iGrotesqueGulp)
				(or
					(Said 'return,conceal/coke/rack<back')
					(Said 'return,conceal/coke/back')
					(Said 'return,conceal/coke<back')
					(Said 'return,conceal/coke/dispenser')
				)
			)
			(Print 114 7)
		)
		(if
			(and
				(ego has: iGrotesqueGulp)
				(or
					(Said 'return,give,conceal/coke/buffet')
					(Said 'return,give/coke')
					(Said 'return,give/coke/agent,bimbo')
				)
			)
			(Print 114 8)
		)
		(if
			(or
				(Said 'ask/agent,children,bimbo/ticket')
				(Said 'apply,play/(dispenser<bucko),bucko')
				(Said 'ask,get,buy/ticket')
			)
			(cond 
				((not ((inventory at: iLotteryTicket) ownedBy: curRoomNum))
					(Print 114 9)
				)
				((not (ego inRect: 0 105 90 120))
					(Print 114 10)
				)
				((ego has: iDollarBill)
					(self changeState: 12)
				)
				(else
					(Print 114 11)
					(Print 114 12)
					(Print 114 13)
				)
			)
		)
		(if (Said 'look/ticket')
			(cond 
				((not (ego has: iLotteryTicket))
					(DontHave)
				)
				((not (ego inRect: 0 105 90 120))
					(NotClose)
				)
				(else
					(Print 114 14)
					(Print 114 15)
				)
			)
		)
		(if (Said 'get/cup')
			(if (not ((inventory at: iGrotesqueGulp) ownedBy: curRoomNum))
				(Print 114 0)
			else
				(Print 114 16)
			)
		)
		(if
			(or
				(Said 'drain,fill/cup,coke')
				(Said 'apply/dispenser<coke')
				(Said 'get/coke,coke')
			)
			(cond 
				((not ((inventory at: iGrotesqueGulp) ownedBy: curRoomNum))
					(Print 114 0)
				)
				((not (ego inRect: 110 128 170 138))
					(NotClose)
				)
				(else
					(self changeState: 1)
				)
			)
		)
		(if
			(or
				(Said 'give,finger/agent,children,bimbo')
				(Said 'finger,give/buck')
				(Said 'buy')
			)
			(cond 
				((not moneyOwed)
					(Print 114 17)
				)
				((not (ego inRect: 0 105 90 120))
					(NotClose)
				)
				((ego has: iMillionDollarBill)
					(Print 114 18)
				)
				((ego has: iLotteryTicket)
					(Print 114 19)
				)
				((ego has: iDollarBill)
					(Print 114 20)
					(Print 114 21)
					(Print 114 22)
					(Print 114 23)
				)
				((not (ego has: iWadODough))
					(Print 114 24)
				)
				(else
					(Print 114 25)
					(Print 114 26)
					(Print 114 27)
					(Print (Format @str 114 28 tritePhrase))
					(theGame changeScore: 3)
					(= moneyOwed FALSE)
				)
			)
		)
		(if (Said '/condom')
			(Print 114 29)
		)
		(if (Said 'buy,open,(ask<for)/door,anyword')
			(Print 114 30)
		)
	)
)

(instance girlScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(User canInput: FALSE)
				(Print (Format @str 114 47 introductoryPhrase))
				(aClerk setLoop: 0 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(aClerk setLoop: 0 setCel: 0)
				(Print 114 48)
				(= seconds 3)
			)
			(3
				(Print 114 49)
				(User canInput: TRUE)
			)
			(4
				(User canInput: FALSE)
				(Print 114 50)
				(aClerk setLoop: 0 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(User canInput: TRUE)
				(aClerk setLoop: 0 setCel: 0)
				(Print 114 51)
			)
			(6
				(User canInput: FALSE)
				(Print 114 52)
				(aClerk setLoop: 0 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(User canInput: TRUE)
				(Print 114 53)
				(aClerk setLoop: 0 setCel: 0)
				(= state 6)
				(-- state)
			)
		)
	)
)

(instance aBigClerk of Prop
	(method (cue)
		(Print 114 54)
		(Print 114 55 #at 55 155 #width 210)
		(self posn: 273 1038)
		(HandsOn)
	)
)
